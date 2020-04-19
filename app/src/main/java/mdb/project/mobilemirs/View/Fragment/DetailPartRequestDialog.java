package mdb.project.mobilemirs.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;

import mdb.project.mobilemirs.Interface.IDetailPartRequest;
import mdb.project.mobilemirs.Manager.SessionManager;
import mdb.project.mobilemirs.Model.DetailPartRequestModel;
import mdb.project.mobilemirs.Presenter.DetailPartRequestPresenter;
import mdb.project.mobilemirs.R;

public class DetailPartRequestDialog extends BottomSheetDialogFragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private String[] partCenterId = {"PC-2020-00011", "PC-2020-00010"};
    private String[] partName = {"BEARING", "BEARING"};
    private String[] merk = {"Timken", "Timken"};
    private String[] type = {"32220", "3307. BD-2 HRS TVH"};

    private Spinner spinner;
    private TextView tvDate, tvPartName, tvMerk, tvType;
    private EditText etQuantity;
    private Button btnSubmit, btnCancel;
    private IDetailPartRequest dialogCallback;
    private ArrayList<DetailPartRequestModel> partCenterList;
    private SessionManager session;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_detail_part_request, container, false);
        btnSubmit = view.findViewById(R.id.button_submit);
        btnCancel = view.findViewById(R.id.button_cancel);
        etQuantity = view.findViewById(R.id.edit_text_quantity);
        spinner = view.findViewById(R.id.spinner_part_center);
        tvDate = view.findViewById(R.id.text_view_req_date);
        tvPartName = view.findViewById(R.id.text_view_part_center);
        tvMerk = view.findViewById(R.id.text_view_merk);
        tvType = view.findViewById(R.id.text_view_type);
        if (getContext() != null) {
            session = new SessionManager(getContext());
        }
        String date = session.getStoredString(SessionManager.KEY_DATE);
        tvDate.setText(date);
        DetailPartRequestPresenter presenter = new DetailPartRequestPresenter(getContext(), null);
        partCenterList = presenter.initPartCenterData(partCenterId, partName, merk, type);
        connectSpinner(partCenterList);
        spinner.setOnItemSelectedListener(this);
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        return view;
    }

    private void connectSpinner(ArrayList<DetailPartRequestModel> partCenterList) {
        ArrayList<String> partCenterId = new ArrayList<>();
        for (int i = 0; i < partCenterList.size(); i++) {
            partCenterId.add(partCenterList.get(i).getPartId());
        }
        if (getContext() != null) {
            ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, partCenterId);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        tvPartName.setText(partCenterList.get(position).getPartName());
        tvMerk.setText(partCenterList.get(position).getMerk());
        tvType.setText(partCenterList.get(position).getType());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_submit:
                if (etQuantity.getText().toString().equals("") || etQuantity.getText().toString().equals("0")) {
                    dialogCallback.showMessage("Please enter the quantity");
                } else {
                    DetailPartRequestModel model = new DetailPartRequestModel();
                    model.setPartId(spinner.getSelectedItem().toString());
                    model.setPartName(tvPartName.getText().toString());
                    model.setMerk(tvMerk.getText().toString());
                    model.setQuantity(Integer.parseInt(etQuantity.getText().toString()));
                    model.setReqDate(tvDate.getText().toString());
                    dialogCallback.restartAdapter(model);
                    dismiss();
                }
                break;
            case R.id.button_cancel:
                dismiss();
                break;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof IDetailPartRequest) {
            dialogCallback = (IDetailPartRequest) getParentFragment();
        } else {
            throw new RuntimeException(context.toString() + " must implement IDetailPartRequest");
        }
    }
}
