package mdb.project.mobilemirs.Model;

public class DetailPartRequestModel {

    private String partName;
    private String merk;
    private int quantity;
    private String reqDate;

    public DetailPartRequestModel(String partName, String merk, int quantity, String reqDate) {
        this.partName = partName;
        this.merk = merk;
        this.quantity = quantity;
        this.reqDate = reqDate;
    }

    public String getPartName() {
        return partName;
    }

    public String getMerk() {
        return merk;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getReqDate() {
        return reqDate;
    }
}
