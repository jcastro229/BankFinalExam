package finalExamJC;
import java.util.ArrayList;

import finalExamJC.Transaction.TransactionType;

public class Account {
	private final ArrayList<Transaction> transactions;
	
	private final int accountNumber;
    private final AcctHolder AccountHolder;
    private double Balance;
    private double overdraftLimit;
    
    private boolean status = true;
    public static int counter = 100;
    public String stat = "Account Open";
	
    public Account(AcctHolder owner, double a) {
        this.accountNumber = counter++;
        this.AccountHolder = owner;
        this.Balance = 0.0;
        transactions = new ArrayList<>();
        this.overdraftLimit = a;
    }
    public Account(AcctHolder owner) {
    	this.accountNumber = counter++;
        this.AccountHolder = owner;
        this.Balance = 0.0;
        transactions = new ArrayList<>();
    }
    public int getAccountNumber() {
        return accountNumber;
    }

    public AcctHolder getOwner() {
        return AccountHolder;
    }
    
    

    public double getBalance() {
        return Balance;
    }
    
    public String isOpen() {
    	if(!status) {
    		stat = "Account Closed";
    		return stat;
    	}
    	return stat;
    }
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
	
    public void withdraw(double amount) {
    	
    	if((status==true && Balance-amount < -overdraftLimit)||(status==false && Balance-amount<0)) {
			System.out.println("Withdrawal failed, the balance is: "+Balance);
		}else {
			Balance=Balance-amount;
			System.out.println("Withdrawal Successful, the new balance is: "+Balance);
	        Transaction withdraw = new Transaction(amount,TransactionType.WITHDRAWAL);
	        transactions.add(withdraw); 
		}
        

    }

    public void deposit(double amount) {
        if(status==true||(status==false && Balance+amount== 0)) {
        	Balance = Balance+amount;
			System.out.println("Your deposit was successful. New Balance is: "+Balance);
			Transaction deposit = new Transaction(amount, TransactionType.DEPOSIT);
	        transactions.add(deposit);
	        
		}else if (status==false||amount <= 0){
			System.out.println("Deposit Failed, the balance is: "+Balance);
		}

        
    }
    
    public boolean close() {
    	return status=false;
    }

}
