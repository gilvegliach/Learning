import java.util.*;

// ctci 17.10: slightly wrong cause of the typo in the text
public class XmlEncoder {
    static class SymbolTable {
        final HashMap<String, Integer> map = new HashMap<String, Integer>();
        void putSymbolEncoding(String s, int value) {
            map.put(s, value);
        }

        boolean containsSymbol(String s) {
            return map.containsKey(s);
        }

        Integer getSymbolEncoding(String s) {
            return map.get(s);
        }
    }
            
    interface Encodable {
        String encode(SymbolTable symbols);
    }
    
    static class Element implements Encodable {
        String tag;
        List<Attribute> attrs = new ArrayList<>();
        List<Element> children = new ArrayList<>();

        Element() { }

        Element(String t) {
            tag = t;
        }
        
        void addAttribute(Attribute a) {
            attrs.add(a);
        }

        void addChild(Element child) {
            children.add(child);
        }

        public String encode(SymbolTable symbols) {
            String s = symbols.containsSymbol(tag)
                ? String.valueOf(symbols.getSymbolEncoding(tag))
                : tag;
            StringBuilder sb = new StringBuilder(s);
            sb.append(" ");
            for (Attribute attr : attrs) {
                sb.append(attr.encode(symbols));
                sb.append(" ");
            }
            sb.append(0);
            sb.append(" ");
            for (Element elem : children) {
                sb.append(elem.encode(symbols));
                sb.append(" ");
            }
            sb.append(0);
            return sb.toString();
        }
    }

    static class Value extends Element {
        final String value;
        
        Value(String v) {
            super();
            value = v;
        }

        public String encode(SymbolTable symbols) {
            return value + " 0";
        }
    }

    static class Attribute implements Encodable {
        final String name;
        final String value;
    
        Attribute(String n, String v) {
            name = n;
            value = v;
        }

        public String encode(SymbolTable symbols) {
            String s = symbols.containsSymbol(name)
                ? String.valueOf(symbols.getSymbolEncoding(name))
                : name;
            return s + " " + value;
        }
    }
    
    public static void main(String[] args) {
        Element root = new Element("family");
        root.addAttribute(new Attribute("lastName", "McDowell"));
        root.addAttribute(new Attribute("state", "CA"));

        Element child = new Element("person");
        child.addAttribute(new Attribute("firstName", "Gayle"));
        child.addChild(new Value("Some Message"));
        root.addChild(child);
        
        SymbolTable symbols = new SymbolTable();
        symbols.putSymbolEncoding("family", 1);
        symbols.putSymbolEncoding("person", 2);
        symbols.putSymbolEncoding("firstName", 3);
        symbols.putSymbolEncoding("lastName", 4);
        symbols.putSymbolEncoding("state", 5);

        System.out.println(root.encode(symbols));
    }
}

