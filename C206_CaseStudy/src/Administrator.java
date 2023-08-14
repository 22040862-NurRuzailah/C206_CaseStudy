import java.util.Objects;

/*
 * I declare that this code was written by me. 
 * I do not copy or allow others to copy my code. 
 * I understand that copying code is considered as plagiarism.
 * 
 * Student Name: Vishnu
 * Student ID: {type your id here}
 * Class: {type your class here}
 * Date/Time created: Monday 31-07-2023 11:23
 */

/**
 * @author Vishnu
 *
 */
public class Administrator {
	private String fullname;
    private String email;
    private String contactNum;
    private String customerID;
    private String accountID;
    private String userrole;

    public Administrator(String fullname, String email, String contactNum, String customerID, String accountID, String userrole) {
        this.fullname = fullname;
        this.email = email;
        this.contactNum = contactNum;
        this.customerID = customerID;
        this.accountID = accountID;
        this.userrole = userrole;
    }
    

    // Getters and setters (you can generate these using your IDE)
    public String getFullName() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }
    public String getUserByEmail() {
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
        return userrole;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        Administrator other = (Administrator) obj;
        return email.equals(other.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
