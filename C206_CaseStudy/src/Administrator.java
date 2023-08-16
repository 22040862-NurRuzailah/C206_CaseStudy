import java.util.Objects;

public class Administrator {
    private String fullName;
    private String email;
    private String contactNum;
    private String customerID;
    private String accountID;
    private String userRole;

    public Administrator(String fullName, String email, String contactNum, String customerID, String accountID, String userRole) {
        this.fullName = fullName;
        this.email = email;
        this.contactNum = contactNum;
        this.customerID = customerID;
        this.accountID = accountID;
        this.userRole = userRole;
    }

    public String getFullName() {
        return fullName;
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

    public String getUserRole() {
        return userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrator that = (Administrator) o;
        return Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
