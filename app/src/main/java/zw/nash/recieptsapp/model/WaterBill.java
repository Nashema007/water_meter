package zw.nash.recieptsapp.model;

/**
 * Calculates the monetary value of water used per month
 */
public class WaterBill {

    private int id;

    private WaterReading waterReading;


    public WaterBill() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WaterReading getWaterReading() {
        return waterReading;
    }

    public void setWaterReading(WaterReading waterReading) {
        this.waterReading = waterReading;
    }
}
