package mdb.project.mobilemirs.View.Fragment;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import mdb.project.mobilemirs.Adapter.InputSheetAdapter;
import mdb.project.mobilemirs.Interface.OnInputSheetListener;
import mdb.project.mobilemirs.Model.ItemHolder;
import mdb.project.mobilemirs.R;

public class InputSheetFragment extends Fragment implements View.OnClickListener {

    private ArrayList<ItemHolder> item;
    private OnInputSheetListener mCallback;
    private RecyclerView recyclerView;
    private int datePosition;

    public InputSheetFragment(ArrayList<ItemHolder> item, int datePosition) {
        this.item = item;
        this.datePosition = datePosition;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_input_sheet, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        Button btnSubmit = view.findViewById(R.id.input_button);
        Button btnCheck = view.findViewById(R.id.cancel_button);
        InputSheetAdapter inputSheetAdapter = new InputSheetAdapter(item);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(inputSheetAdapter);
        btnSubmit.setOnClickListener(this);
        btnCheck.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.input_button:
                ArrayList<Integer> dataHolder = new ArrayList<>();
                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    ViewGroup gridChild = (ViewGroup) recyclerView.getChildAt(i);
                    for (int j = 0; j < gridChild.getChildCount(); j++) {
                        if (gridChild.getChildAt(j) instanceof EditText) {
                            EditText editText = (EditText) gridChild.getChildAt(j);
                            if (editText.getText().toString().equals("")) {
                                dataHolder.add(-2);
                            } else {
                                dataHolder.add(Integer.parseInt(editText.getText().toString()));
                            }
                        } else if (gridChild.getChildAt(j) instanceof RadioGroup) {
                            RadioGroup radioGroup = (RadioGroup) gridChild.getChildAt(j);
                            int radioButtonId = radioGroup.getCheckedRadioButtonId();
                            View radioButton = radioGroup.findViewById(radioButtonId);
                            dataHolder.add(radioGroup.indexOfChild(radioButton));
                        }
                    }
                }

                if (dataHolder.contains(-2) || dataHolder.contains(-1)) {
                    showWarning();
                } else {
                    mCallback.onSubmitData(dataHolder, datePosition, R.color.selectedColumn);
                }

                break;
            case R.id.cancel_button:
                mCallback.onCancelInput();
                break;
        }

    }

    private void showWarning() {
        final AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setTitle("Warning");
        alertDialog.setMessage("All Field Required");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnInputSheetListener) {
            mCallback = (OnInputSheetListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnBackButtonListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }

}
