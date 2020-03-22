package zw.nash.recieptsapp.model;

/**
 * Class that represents values to be printed in e-receipts
 */
public class Transaction {

    private int transactionId;
    private User user;
    private WaterBill waterBill;

    public Transaction() {
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public WaterBill getWaterBill() {
        return waterBill;
    }

    public void setWaterBill(WaterBill waterBill) {
        this.waterBill = waterBill;
    }
}
