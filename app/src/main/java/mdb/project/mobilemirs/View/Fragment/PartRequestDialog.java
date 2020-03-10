package mdb.project.mobilemirs.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import mdb.project.mobilemirs.Interface.IPartRequest;
import mdb.project.mobilemirs.Manager.SessionManager;
import mdb.project.mobilemirs.R;

public class PartRequestDialog extends BottomSheetDialogFragment implements View.OnClickListener {

    private TextView tvShipCode, tvDate, tvShipName, tvDocument;
    private IPartRequest dialogCallback;
    private SessionManager session;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_part_request, container, false);
        tvShipCode = view.findViewById(R.id.text_view_ship_code);
        tvDate = view.findViewById(R.id.text_view_date);
        tvShipName = view.findViewById(R.id.text_view_ship_name);
        tvDocument = view.findViewById(R.id.text_view_document);
        Button btnSubmit = view.findViewById(R.id.button_submit);
        Button btnCancel = view.findViewById(R.id.button_cancel);
        if (getContext() != null) {
            session = new SessionManager(getContext());
        }
        String shipCode = session.getStoredString(SessionManager.KEY_SHIP_CODE);
        String date = session.getStoredString(SessionManager.KEY_DATE);
        String shipName = session.getStoredString(SessionManager.KEY_SHIP_NAME);
        tvShipCode.setText(shipCode);
        tvDate.setText(date);
        tvShipName.setText(shipName);
        tvDocument.setText(String.format("Order Part Center - %s", shipName));
        btnSubmit.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_submit:
                String document = tvDocument.getText().toString();
                String date = tvDate.getText().toString();
                String shipCode = tvShipCode.getText().toString();
                String shipName = tvShipName.getText().toString();
                String opinion = "-";
                dialogCallback.restartAdapter(document, date, shipCode, shipName, opinion);
                dismiss();
                break;
            case R.id.button_cancel:
                dismiss();
                break;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof IPartRequest) {
            dialogCallback = (IPartRequest) getParentFragment();
        } else {
            throw new RuntimeException(context.toString() + " must implement IPartRequest");
        }
    }
}
