package finalExamJC;

public class Transaction {
	private final int transNumber;
    private final double amount;
    private final TransactionType type;
    private static int counter = 1;


    public Transaction(double amount,TransactionType type) {
    	this.transNumber = counter++;
        this.amount = amount;
        this.type = type;
    }

    /**
     * Getter for the amount.
     *
     * @return Returns the amount of the transaction.
     */
    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }
    enum TransactionType {
        DEPOSIT,
        WITHDRAWAL
    }

    public String toString() {
		return "" + transNumber + ": " + type + " : " + amount +"\n";
	}
}


