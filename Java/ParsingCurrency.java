import java.text.NumberFormat;
import java.util.Locale;
import java.text.ParseException;
import java.text.DecimalFormatSymbols;
import java.text.DecimalFormat;

public class ParsingCurrency {
	public static void main(String[] args) {
		String input = "3,47 €";
		System.out.println(strToCents(input));		
	}
	
	public static int strToCents(String priceLabel) {
       try {
		   NumberFormat nf = new DecimalFormat("#.00 ¤", new DecimalFormatSymbols(Locale.GERMANY));
           Number priceNumber = nf.parse(priceLabel);
           return (int)(priceNumber.doubleValue() * 100);
       } catch (ParseException e) {
           return -2;
       }
	}
}