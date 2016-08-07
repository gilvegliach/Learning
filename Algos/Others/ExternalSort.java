import java.util.Arrays;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class ExternalSort {
    // File size must be smaller than avail memˆ2. To sort any sizes of file
    // just reduce number of buffers that are merger at the same time and
    // repeat the merging until done
    public static void main(String[] args) throws IOException {
        long now = System.currentTimeMillis();
        //File f = new File("values.txt");
        //BufferedWriter br = new BufferedWriter(new FileWriter(f));
        int size = 100 * 1024 * 1024;
        long[] a = new long[size];
        for (int i = 0; i < size; i++) {
            long range = ((long)Integer.MAX_VALUE - 1) - Integer.MIN_VALUE;
            long n = (long) (Math.random() * range + Integer.MIN_VALUE);
            a[i] = n;
            //br.write(String.valueOf(n));
            //br.newLine();
        }
        //br.close();
        long diff = System.currentTimeMillis() - now;
        System.console().format("Time for generating input: %d ms\n", diff); 
        
        now = System.currentTimeMillis();
        Arrays.sort(a);
        //externalSort(f, 10 * 1024 * 1024); 
        diff = System.currentTimeMillis() - now;
        System.console().format("Time for sorting: %d ms\n", diff); 
    }       
        
    public static void externalSort(File in, long maxMem) throws IOException {
        if (in == null || in.isDirectory()) return;
        
        String name = in.getName();
        int index = name.lastIndexOf(".");
        if (index != -1) {
            name = name.substring(0, index) + "-sorted.txt";
        } else {
            name = name + "-sorted.txt";
        }
        File sortedPath = new File(in.getParent(), name);
        // Fails if there is no subdirectory called temp
        File basePath = new File(in.getParent(), "temp");
        
        int size = (int) Math.min(Integer.MAX_VALUE, maxMem / 4L); // max(long, long)

        int i = 1;
        int chunks = 1;
        WBuffer wb = new WBuffer(fileForInt(basePath, chunks - 1), size, true);
        
        BufferedReader br = new BufferedReader(new FileReader(in));
        String line = null;
        while ((line = br.readLine()) != null) {
            if (i == size) {
                wb.close();
                chunks++;
                i = 0;
                wb = new WBuffer(fileForInt(basePath, chunks - 1), size, true);
            }
            // buff can be written at i
            int n = Integer.parseInt(line);
            wb.write(n);
            i++;
        }
        br.close();
        wb.close();
        
        // pc: all chunks on disk
        int buffSize = size / chunks;
       // System.out.println("i: " + i);
       // System.out.println("size: " + size);
       // System.out.println("buffSize: " + buffSize);
       // System.out.println("#chunks: " + chunks);
        
        RBuffer[] rbuffs = new RBuffer[chunks];
        for (int j = 0; j < chunks; j++) {
            rbuffs[j] = new RBuffer(fileForInt(basePath, j), buffSize);
        }
        
        wb = new WBuffer(sortedPath, buffSize);
        while (true) {
            boolean done = true;
            int minIndex = -1;
            int min = Integer.MAX_VALUE;
            for (int j = 0; j < chunks; j++) {
                if (rbuffs[j].isEof()) continue;
                done = false;
                int val = rbuffs[j].peek();
                if (val < min) {
                    minIndex = j;
                    min = val;
                }
            }
            if (done) break;
            wb.write(min);
            rbuffs[minIndex].read();
        }
        wb.close();
    } 
    
    private static File fileForInt(File parent, int number) {
        return new File(parent, "" + number + ".txt");
    }
    
    static class WBuffer {
        private final int[] mBuff;
        private final BufferedWriter mWriter;
        private final boolean mOrdered;
        private int mPosition = 0;
        
        WBuffer(File out, int buffLen) throws IOException {
            this(out, buffLen, false);
        }
        
        WBuffer(File out, int buffLen, boolean ordered) throws IOException {
            mWriter = new BufferedWriter(new FileWriter(out));
            mBuff = new int[buffLen];
            mOrdered = ordered;
        }
        
        void write(int n) throws IOException {
            if (mPosition == mBuff.length) flush();
            mBuff[mPosition++] = n;
        }
        
        void close() throws IOException {
            flush();
            mWriter.close();
        }
        
        private void flush() throws IOException {
            if (mOrdered) Arrays.sort(mBuff, 0, mPosition);
            for (int i = 0; i < mPosition; i++) {
                String line = String.valueOf(mBuff[i]);
                mWriter.write(line);
                mWriter.newLine();
            }
            mPosition = 0;
        }
    }
    
    static class RBuffer {
        private final int[] mBuff;
        private final BufferedReader mReader;
        private int mPosition = 0;
        private int mSize = 0;
        
        RBuffer(File in, int buffLen) throws IOException {
            mReader = new BufferedReader(new FileReader(in));
            mBuff = new int[buffLen];
            reload();
        }
        
        boolean isEof() throws IOException {
            if (mPosition < mSize) return false;
            if (mPosition == mSize) reload();
            return mSize == 0;
        }
        
        // valid only if isEof() == false
        int peek() throws IOException {
            if (isEof()) throw new RuntimeException("EOF");
            return mBuff[mPosition];
        }
        
        // valid only if isEof() == false
        int read() throws IOException {
            if (isEof()) throw new RuntimeException("EOF");
            return mBuff[mPosition++];
        }
        
        void close() throws IOException {
            mReader.close();
        }
        
        private void reload() throws IOException {
            mPosition = mSize = 0;
            String line = null;
            while (mSize < mBuff.length) {
                line = mReader.readLine();
                if (line == null) break;
                int n = Integer.parseInt(line);
                mBuff[mSize++] = n;
            }
        }
    }
}