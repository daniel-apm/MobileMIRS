package mdb.project.mobilemirs.Interface;

import java.util.ArrayList;

import mdb.project.mobilemirs.Model.PartRequestModel;

public interface IPartRequest {
    void connectAdapter(ArrayList<PartRequestModel> partRequestList);
    void restartAdapter(String document, String date, String shipCode, String shipName, String opinion);
    void showMessage(String message);
    void getDocumentId(String documentId);
    void removeAdapter(int position);
}
