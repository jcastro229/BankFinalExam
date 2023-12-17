package finalExamJC;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.io.Reader;

public class Bank {
	
	private static ArrayList<Account> accounts;

	Scanner input = new Scanner(System.in);
	String fname;
	String lname;
	String ssn;
	String dob;
	int acctNum;
	double dep;
	double overdraftLimit;
	Account checking;
	Account saving;
	int choice;
	String typ;
	private static List<Integer> choices = new ArrayList<Integer>();
	private static List<Double> rates = new ArrayList<Double>();
	private static List<String> code = new ArrayList<String>();
	public static String[] currencyLine;
	boolean loaded = false;
	boolean supported = false;
	String curr;
	String codeC;
	double rateC;
	
	public static Map<String, Double> worldCurrencies=new TreeMap<String, Double>();
	Currency curren=new Currency();
	
	
	public Bank() throws Exception {
		accounts = new ArrayList<>();
		loadCurrencies();
	}
	
	public void run(int c) throws Exception {
		choice = c;
		if(choice==1){
			openChecking();
			choices.add(choice);
		}
		else if(choice==2) {
			openSaving();
			choices.add(choice);
		}else if(choice==3) {
			listAccounts();
			
		}else if(choice==4){
			transactions();
			
		}else if(choice==5) {
			deposit();
			
		}else if(choice==6) {
			withdraw();
			
		}else if(choice==7) {
			closeAcc();
		}
	}
	
	
	
	public void openChecking() throws Exception {
		System.out.println("Enter first name: ");
		fname = input.next();
		System.out.println("Enter last name: ");
		lname = input.next();
		System.out.println("Enter Social Security Number(###-###-####): ");
		ssn = input.next();
		System.out.println("Enter your date of birth(Month-Day-Year): ");
		dob = input.next();
		if(loaded) {
			supportsCurrency(whatCurrency());
		}
		AcctHolder acct = new AcctHolder(fname, lname, dob, ssn);
		long age = acct.getAgeInYears(dob);
		if(age<16) {
			System.out.println( "Account could not be created");
		}else if(age<18) {
			checking = new Account(acct);
			accounts.add(checking);
			System.out.println( "Thank you, the account number is "+checking.getAccountNumber());
		}else {
			System.out.println("Enter your overdraft limit: ");
			overdraftLimit = input.nextDouble();
			checking = new Account(acct,overdraftLimit);
			accounts.add(checking);
			System.out.println(  "Thank you, the account number is "+checking.getAccountNumber());
		}
	}
	public void openSaving() throws Exception {
		System.out.println("Enter first name: ");
		fname = input.next();
		System.out.println("Enter last name: ");
		lname = input.next();
		System.out.println("Enter Social Security Number(###-###-####): ");
		ssn = input.next();
		System.out.println("Enter your date of birth(Month-Day-Year): ");
		dob = input.next();
		if(loaded) {
			supportsCurrency(whatCurrency());
		}
		AcctHolder acct = new AcctHolder(fname, lname, dob, ssn);
		long age = acct.getAgeInYears(dob);
		if(age<5) {
			System.out.println("Account could not be created");
		}else {
		saving = new Account(acct);
		accounts.add(saving);
		System.out.println( "Thank you, the account number is "+saving.getAccountNumber());//System.out.println(;
		}
		
	}
	public void listAccounts() { 
		int o = 0;
		double rate=1;
		//Make to check when rates is nothing, file is not loaded
		//make it so convertToUSd is used
		//use String arrayList
		String co = "USD";
		for(Account i : accounts) {
			int num = i.getAccountNumber();
			typ = typerFind(o);
			AcctHolder fname = i.getOwner();
			double b = i.getBalance();
			String s = i.isOpen();
			if(code.size()>0) {
				co = code.get(o);
				rate = rates.get(o);
			}
			double fb = Currency.convertToUSD(worldCurrencies,co, b);
			System.out.println(num+ "("+typ+") : "+fname+" : "+b+": US Dollar Balance : "+String.format("%,.2f",fb)+" : "+s); 
			o++;
		}
		
	}
	
	
	public String typerFind(int n) {
		String t = "Checking";
			if(choices.get(n)==2) {
				t="Saving";
			}
		return t;
	}
	
	public void deposit() {
		System.out.println("Enter Account Number: ");
		int n = input.nextInt();
		System.out.println("Enter the amount to Deposit: ");
		double d = input.nextDouble();
		Account acc = null;
		for(Account i : accounts) {
			if(i.getAccountNumber()==n) {
				acc=i;
				}
			}
		if(acc==null) {
			System.out.println("Account Not Found");
		}else {
		acc.deposit(d);
		}
	}
	
	public void withdraw() {
		System.out.println("Enter Account Number: ");
		int n = input.nextInt();
		System.out.println("Enter the amount to Withdraw: ");
		double d = input.nextDouble();
		Account acc = null;
		for(Account i : accounts) {
			if(i.getAccountNumber()==n) {
				acc=i;
				}
			}
		if(acc==null) {
			System.out.println("Account Not Found");
		}else {
		acc.withdraw(d);
		}
	}
	
	public void transactions() {
		System.out.println("Enter Account Number For Transactions: ");
		int n = input.nextInt();
		Account acc = null;
		String co = "USD";
		int r =0;
		for(Account i : accounts) {
			if((i.getAccountNumber()==n)&&(code.size()>0)) {
				if(code.get(n-100)==code.get(r)) {
					acc=i;
					co = code.get(r);
					System.out.println(acc.getTransactions());
				
					System.out.println("Balance: "+String.format("%,.2f",acc.getBalance()));
					System.out.println("US Dollar Balance :"+String.format("%,.2f",Currency.convertToUSD(worldCurrencies, co, acc.getBalance())));
				}
			}else if(i.getAccountNumber()==n) {
				acc=i;
				System.out.println(acc.getTransactions());
				
				System.out.println("Balance: "+String.format("%,.2f",acc.getBalance()));
				System.out.println("US Dollar Balance :"+String.format("%,.2f",Currency.convertToUSD(worldCurrencies, co, acc.getBalance())));
			}else {
			r++;
			}
		}
		if(acc==null) {
			System.out.println("Account Not Found");
		}
		
	}
	
	public void closeAcc() {
		System.out.println("Enter Account Number to Close: ");
		int n = input.nextInt();
		Account acc = null;
		for(Account i : accounts) {
			if(i.getAccountNumber()==n) {
				acc=i;
				}
			}
		if(acc==null) {
			System.out.println("Account Not Found");
		}else {
		acc.close();
		System.out.println("Account close, current balance is "+acc.getBalance());
		}
	}
	public void loadCurrencies() throws Exception  {
		Socket client=new Socket("www.usman.cloud",80);
		
		OutputStream out=client.getOutputStream();
		InputStream in=client.getInputStream();
		
		//Sending request
		String request="GET /exchange-rate.csv \r\n";
		out.write(request.getBytes());
		
		Reader r=new InputStreamReader(in);
		BufferedReader br=new BufferedReader(r);
		
		String line;
		while((line=br.readLine())!=null) {
			currencyLine=line.split(",");
			
			Currency cur=new Currency(currencyLine[0],Double.parseDouble(currencyLine[2]));
			
			worldCurrencies.put(cur.getCurrencyCode(currencyLine), cur.getCurrencyRate(currencyLine));
			}
		loaded=true;		
}
	
	public void isCurrencyFileLoaded() {
		if(!loaded) {
			System.out.println("**Currency file could not be loaded, Currency Conversion and Foreign CUrrency accounts are not available");
		}else {
			System.out.println("File loaded");
		}
	}
	//If option 1 or 2 is selected and if the exchange-rate file has been loaded then the following additional 
	//question will be asked when opening any type of account. 
	
	public String whatCurrency() {
		System.out.println("Account Currency: ");
		curr = input.next();
		return curr;
	}
	public void supportsCurrency(String a) {
		for (String key : worldCurrencies.keySet()) {
		    if(worldCurrencies.get(a) !=null) {
            	supported=true;
            }
		}
		if(supported==false) {
			System.out.println("Currency not Supported, enter another");
			supportsCurrency(whatCurrency());
		}else {
			currencyAdd(a);
			codeAdd(a);
		}
	}
	
	public void currencyAdd(String r) {
		if(supported==true) {
			double d = worldCurrencies.get(r);
			rates.add(d);
		}
	}
	public void codeAdd(String r) {
		if(supported ==true) {
			code.add(r);
		}
	}
}