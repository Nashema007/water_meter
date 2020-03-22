package zw.nash.recieptsapp.model;

public class WaterReading {

    private int id;
    private String meterNumber;
    private float waterReadingValue;
    private String date;

    public WaterReading() {
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

    public float getWaterReadingValue() {
        return waterReadingValue;
    }

    public void setWaterReadingValue(float waterReadingValue) {
        this.waterReadingValue = waterReadingValue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
