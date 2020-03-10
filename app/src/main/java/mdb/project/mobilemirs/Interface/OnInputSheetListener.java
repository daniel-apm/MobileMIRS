package mdb.project.mobilemirs.Interface;

import java.util.ArrayList;

public interface OnInputSheetListener {
    void onSubmitData(ArrayList<Integer> dataHolder, int position, int colorId);
    void onCancelInput();
}
