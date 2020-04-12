package zw.nash.recieptsapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetails {

    @SerializedName("accountNumber")
    @Expose
    private String accountNumber;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("nationalId")
    @Expose
    private String nationalId;


    public UserDetails() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
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
