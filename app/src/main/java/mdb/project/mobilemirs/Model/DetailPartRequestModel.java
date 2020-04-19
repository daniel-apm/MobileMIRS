package mdb.project.mobilemirs.Model;

public class DetailPartRequestModel {

    private String parentId;
    private int lineId;
    private String partId;
    private String partName;
    private String merk;
    private String type;
    private int quantity;
    private String reqDate;

    public DetailPartRequestModel() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setReqDate(String reqDate) {
        this.reqDate = reqDate;
    }

    public String getParentId() {
        return parentId;
    }

    public int getLineId() {
        return lineId;
    }

    public String getPartId() {
        return partId;
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
