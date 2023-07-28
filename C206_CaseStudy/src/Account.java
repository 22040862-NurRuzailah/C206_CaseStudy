public class Account {
    private String username;
    private String email;
    private String contactNum;
    private String customerID;
    private String accountID;
    private String password;

    public Account(String username, String email, String password, String contactNum, String customerID, String accountID) {
        this.username = username;
        this.email = email;
        this.contactNum = contactNum;
        this.customerID = customerID;
        this.accountID = accountID;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getContactNum() {
        return contactNum;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getAccountID() {
        return accountID;
    }

    public String getPassword() {
        return password;
    }
}
