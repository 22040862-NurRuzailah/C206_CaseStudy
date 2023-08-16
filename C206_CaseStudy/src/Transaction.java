public class Transaction {

	private String customerID;
    private String date;
    private int amount;
    private String currency;
    private String transactionID;
    private int balance;

//constructor
    public Transaction(String customerID, String date, int amount, String currency, String transactionID, int balance) {
        this.customerID = customerID;
        this.date = date;
        this.amount = amount;
        this.currency = currency;
        this.balance = balance;
        this.transactionID = transactionID;
    }
    //getters
    public String getCustomerID() {
        return customerID;
    }
    //getters
    public String getDate() {
        return date;
    }
    //getters
    public int getAmount() {
        return amount;
    }
    //getters
    public String getCurrency() {
        return currency;
    }
    // getters
    public String getTransactionID() {
        return transactionID;
    }

    public int getBalance() {
        return balance;
    }
    public void setDate(String date) {
		this.date = date;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}
}
