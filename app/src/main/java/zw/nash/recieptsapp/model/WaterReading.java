package zw.nash.recieptsapp.model;

public class WaterReading {

    private int id;
    private String meterNumber;
    private double waterReadingValue;
    private double waterRate;
    private String accountNumber;
    private Account account;

    private String date;

    public WaterReading() {
        setAccountNumber(account.getAccountNumber());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    private double getWaterReadingValue() {
        return waterReadingValue;
    }

    public void setWaterReadingValue(double waterReadingValue) {
        this.waterReadingValue = waterReadingValue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    private double getWaterRate() {
        return waterRate;
    }

    public void setWaterRate(double waterRate) {
        this.waterRate = waterRate;
    }

    public double getWaterValue() {
        return (getWaterRate()*getWaterReadingValue());
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    private void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
}
