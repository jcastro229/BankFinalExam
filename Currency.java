package finalExamJC;

import java.util.Map;
import java.util.TreeMap;

public class Currency {

	
	public static String[] currencyLine;


	
	static Map<String, Double> worldCurrencies=new TreeMap<String, Double>();

	public Currency(String string, double parseDouble) {
		// TODO Auto-generated constructor stub
	}
	public Currency() {
		
	}
	

	public static double convertToUSD(Map<String, Double> m, String code, double amount) {
		
		double c = 1;
		if(m.size()>0) {
			c = m.get(code);
		}
		amount = c*amount;
		return amount;
	}
	public static String getCurrencyCode(String[]c) {
		return c[0];
}
	public static String getCurrencyDes() {
		return currencyLine[1];
}
	public static Double getCurrencyRate(String[]c) {
		double d =Double.parseDouble(c[2]);
	return d;
}

	/*public static double convertToUSD(String code, double amount) {
		
		return worldCurrencies.get(code).convertToUSD(amount);
	}*/
	

}