package mdb.project.mobilemirs.Model;

public class PartCenterModel {

    private String partCenterId;
    private String partName;
    private String merk;
    private String tyoe;

    public PartCenterModel(String partCenterId, String partName, String merk, String tyoe) {
        this.partCenterId = partCenterId;
        this.partName = partName;
        this.merk = merk;
        this.tyoe = tyoe;
    }

    public String getPartCenterId() {
        return partCenterId;
    }

    public String getPartName() {
        return partName;
    }

    public String getMerk() {
        return merk;
    }

    public String getTyoe() {
        return tyoe;
    }
}
