package mdb.project.mobilemirs.Model;

public class ShipModel {
    private String shipCode;
    private String shipName;
    private int shipImage;

    public ShipModel(String shipCode, String shipName, int shipImage) {
        this.shipCode = shipCode;
        this.shipName = shipName;
        this.shipImage = shipImage;
    }

    public String getShipCode() {
        return shipCode;
    }

    public String getShipName() {
        return shipName;
    }

    public int getShipImage() {
        return shipImage;
    }
}
