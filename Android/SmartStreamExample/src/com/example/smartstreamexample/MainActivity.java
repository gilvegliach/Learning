package com.example.smartstreamexample;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Base64InputStream;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity
{
	final static String URL = "http://some-url";

	LoadImageTask mTask;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTask = LoadImageTask.getInstance(URL);

		Button btn = (Button) findViewById(R.id.button);
		btn.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (!mTask.hasRun())
					mTask.execute();
			}
		});
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		mTask.detach();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		mTask.attach((ImageView) findViewById(R.id.image));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}

class LoadImageTask extends AsyncTask<Void, Void, Bitmap>
{
	private static final String TAG = "LoadImageTask";

	// Guarded by LoadImageTask.class
	private static LoadImageTask sInstance;

	// All guarded by this
	private ImageView mImage;
	private final String mUrl;
	private boolean mHasRun = false;

	private LoadImageTask(String url)
	{
		mUrl = url;
	}

	synchronized static LoadImageTask getInstance(String url)
	{
		if (sInstance == null)
			sInstance = new LoadImageTask(url);
		return sInstance;
	}

	synchronized void attach(ImageView im)
	{
		mImage = im;
		notifyAll();
	}

	synchronized void detach()
	{
		mImage = null;
	}

	synchronized boolean hasRun()
	{
		return mHasRun;
	}

	@Override
	protected Bitmap doInBackground(Void... params)
	{
		HttpURLConnection urlConnection = null;
		BufferedReader br = null;
		try
		{
			URL url = new URL(mUrl);
			urlConnection = (HttpURLConnection) url.openConnection();
			InputStream is = urlConnection.getInputStream();
			return BitmapDecoder.decode(is);

			// br = new BufferedReader(new InputStreamReader(is));
			//
			// String imgData = br.readLine().split(":")[1].trim();
			// imgData.substring(0, imgData.length() - 1);
			// Log.d(TAG, imgData);
			// byte[] data = Base64.decode(imgData, Base64.NO_WRAP);
			// Bitmap b = BitmapFactory.decodeByteArray(data, 0, data.length);
			//
			// waitForImageView();
			//
			// return b;

		}
		catch (IOException e)
		{
			e.printStackTrace();
			return null;
		}
		finally
		{
			if (urlConnection != null)
				urlConnection.disconnect();

			closeQuietly(br);
		}
	}

	@Override
	synchronized protected void onPostExecute(Bitmap result)
	{
		if (result != null)
			mImage.setImageBitmap(result);

		detach();
		mHasRun = true;
	}

	synchronized private void waitForImageView()
	{
		while (mImage == null)
		{
			try
			{
				wait();
			}
			catch (InterruptedException e)
			{
				Thread.currentThread().interrupt();
				cancel(true);
				break;
			}
		}
	}

	private static void closeQuietly(Closeable... cl)
	{
		for (Closeable c : cl)
		{
			try
			{
				if (c != null)
					c.close();
			}
			catch (IOException ignored)
			{
			}
		}
	}
}

class BitmapDecoder
{
	private static final String TAG = "BitmapDecoder";

	static Bitmap decode(InputStream in) throws IOException
	{
		CharsetDecoder decoder = Charset.forName("UTF-8").newDecoder();
		ByteBuffer bbuf = ByteBuffer.allocate(4);
		CharBuffer cbuf = CharBuffer.allocate(1);

		int quoteCounter = 0;

		outer: while (true)
		{
			decoder.reset();
			bbuf.clear();
			cbuf.clear();

			while (true)
			{
				int read = in.read();

				// End of stream
				if (read == -1)
					break outer;

				byte b = (byte) read;
				bbuf.put(b);

				try
				{
					if (CoderResult.UNDERFLOW != decoder.decode(bbuf, cbuf, false))
						break;
				}
				catch (IllegalStateException e)
				{
					break;
				}

				cbuf.clear();
			}

			bbuf.flip();
			cbuf.clear();
			decoder.decode(bbuf, cbuf, true);
			decoder.flush(cbuf);
			cbuf.flip();

			char c = cbuf.get(0);
			if (c == '"')
				quoteCounter++;

			if (quoteCounter == 3)
				break outer;
		}

		Base64InputStream b64in = new Base64InputStream(in, Base64.NO_WRAP);
		return BitmapFactory.decodeStream(b64in);
	}

}
