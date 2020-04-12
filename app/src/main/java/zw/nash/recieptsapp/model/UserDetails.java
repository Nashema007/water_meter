package zw.nash.recieptsapp.model;

public class UserDetails {

    private String accountNumber;
    private String username;
    private String name;
    private String address;
    private String nationalId;
    private Account account;
    private User user;

    public UserDetails() {
        setAccountNumber(account.getAccountNumber());
        setUsername(user.getUsername());
    }

    public String getUsername() {
        return username;
    }

    private void setUsername(String username) {
        this.username = username;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    private void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }


    @Override
    public String toString() {
        return "UserDetails{" +
                "accountNumber='" + accountNumber + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", nationalId='" + nationalId + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
