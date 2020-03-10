package mdb.project.mobilemirs.Model;

public class PartRequestModel {
    private String documentId;
    private String document;
    private String date;
    private String status;

    public PartRequestModel(String documentId, String document, String date, String status) {
        this.documentId = documentId;
        this.document = document;
        this.date = date;
        this.status = status;
    }

    public String getDocumentId() {
        return documentId;
    }

    public String getDocument() {
        return document;
    }

    public String getDate() {
        return date;
    }

    public String getStatus() {
        return status;
    }
}
