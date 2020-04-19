package mdb.project.mobilemirs.View.Fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mdb.project.mobilemirs.Adapter.PartRequestAdapter;
import mdb.project.mobilemirs.Interface.IItem;
import mdb.project.mobilemirs.Interface.IPartRequest;
import mdb.project.mobilemirs.Model.PartRequestModel;
import mdb.project.mobilemirs.Presenter.PartRequestPresenter;
import mdb.project.mobilemirs.R;

public class PartRequestFragment extends Fragment implements IPartRequest, View.OnClickListener {

    private RecyclerView recycler;
    private PartRequestAdapter adapter;
    private PartRequestPresenter presenter;
    private IItem fragmentCallback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_part_request, container, false);
        Button btnAdd = view.findViewById(R.id.button_add);
        recycler = view.findViewById(R.id.recycler_part_request);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter = new PartRequestPresenter(getContext(), this);
        presenter.initPartRequestData();
        btnAdd.setOnClickListener(this);
        return view;
    }

    @Override
    public void connectAdapter(ArrayList<PartRequestModel> partRequestList) {
        adapter = new PartRequestAdapter(getContext(), this, partRequestList);
        recycler.setAdapter(adapter);
    }

    @Override
    public void restartAdapter(String document, String date, String shipCode, String shipName, String opinion) {
        presenter.postPartRequestData(document, date, shipCode, shipName, opinion);
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDocumentId(String documentId) {
        fragmentCallback.changeFragment(documentId);
    }

    @Override
    public void removeAdapter(int position) {
        showWarning(position);
    }

    @Override
    public void onClick(View v) {
        PartRequestDialog dialog = new PartRequestDialog();
        dialog.show(this.getChildFragmentManager(), null);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IItem) {
            fragmentCallback = (IItem) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement IHome");
        }
    }

    private void showWarning(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Are you sure want to delete this?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.deletePartRequestData(adapter.getPartRequestList().get(position).getDocumentId());
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
