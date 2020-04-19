package mdb.project.mobilemirs.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mdb.project.mobilemirs.Adapter.DetailPartRequestAdapter;
import mdb.project.mobilemirs.Interface.IDetailPartRequest;
import mdb.project.mobilemirs.Model.DetailPartRequestModel;
import mdb.project.mobilemirs.Presenter.DetailPartRequestPresenter;
import mdb.project.mobilemirs.R;

public class DetailPartRequestFragment extends Fragment implements IDetailPartRequest, View.OnClickListener {

    private RecyclerView recycler;
    private String documentId;
    private DetailPartRequestPresenter presenter;
    private DetailPartRequestAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_part_request, container, false);
        Bundle bundle = getArguments();
        if (bundle != null) {
            documentId = bundle.getString("Content");
        }
        recycler = view.findViewById(R.id.recycler_detail_part_request);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        Button btnAdd = view.findViewById(R.id.button_add);
        presenter = new DetailPartRequestPresenter(getContext(), this);
        presenter.initDetailPartRequestData(documentId);
        btnAdd.setOnClickListener(this);
        return view;
    }

    @Override
    public void connectAdapter(ArrayList<DetailPartRequestModel> detailPartRequestList) {
        if (detailPartRequestList.isEmpty()) {
            Toast.makeText(getContext(), "There is no data available", Toast.LENGTH_SHORT).show();
        }
        adapter = new DetailPartRequestAdapter(getContext(), detailPartRequestList, this);
        recycler.setAdapter(adapter);
    }

    @Override
    public void restartAdapter(DetailPartRequestModel model) {
        if (adapter.getDetailPartRequestList().isEmpty()) {
            presenter.postDetailPartRequest(model, documentId);
        } else {
            presenter.appendDetailPartRequest(model, documentId);
        }
    }

    @Override
    public void removeAdapter(int position) {
        DetailPartRequestModel currentPartDetail = adapter.getDetailPartRequestList().get(position);
        presenter.deleteDetailPartRequest(currentPartDetail.getParentId(), currentPartDetail.getLineId());
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        DetailPartRequestDialog dialog = new DetailPartRequestDialog();
        dialog.show(this.getChildFragmentManager(), null);
    }

}
