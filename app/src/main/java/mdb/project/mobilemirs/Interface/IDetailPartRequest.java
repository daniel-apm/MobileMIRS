package mdb.project.mobilemirs.Interface;

import java.util.ArrayList;

import mdb.project.mobilemirs.Model.DetailPartRequestModel;

public interface IDetailPartRequest {
    void connectAdapter(ArrayList<DetailPartRequestModel> detailPartRequestList);
    void restartAdapter(DetailPartRequestModel model);
    void removeAdapter(int position);
    void showMessage(String message);
}
