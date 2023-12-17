package finalExamJC;

public class SavingAccount extends Account{

public int overdraftLimit;
public double balance;
public boolean status;

public SavingAccount(AcctHolder owner, double lim) {
	super(owner, lim);
	// TODO Auto-generated constructor stub
}

	

	public void deposit(double amount) {
		if(status==true||(status==false && balance+amount== 0)) {
			balance = balance+amount;
			System.out.println("Your deposit was successful. New Balance is: "+balance);
		}else {
			System.out.println("Deposit Failed, the balance is: "+balance);
		}
		
	}
	
	public void withdraw(double amount) {
		if((status==true && balance-amount <= overdraftLimit)||(status==false && balance-amount>=0)) {
			System.out.println("Withdrawal failed, the balance is: "+balance);
		}else {
			balance=balance-amount;
			System.out.println("Withdrawal Successful, the new balance is: "+balance);
		}
		
	}
	
}