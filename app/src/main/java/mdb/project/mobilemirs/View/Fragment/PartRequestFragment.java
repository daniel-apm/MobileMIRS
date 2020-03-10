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

import mdb.project.mobilemirs.Adapter.PartRequestAdapter;
import mdb.project.mobilemirs.Interface.IItem;
import mdb.project.mobilemirs.Interface.IPartRequest;
import mdb.project.mobilemirs.Model.PartRequestModel;
import mdb.project.mobilemirs.Presenter.PartRequestPresenter;
import mdb.project.mobilemirs.R;

public class PartRequestFragment extends Fragment implements IPartRequest, View.OnClickListener {

    private RecyclerView recycler;
    private PartRequestPresenter presenter;
    private IItem fragmentCallback;
    private int position;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_part_request, container, false);
        Button btnAdd = view.findViewById(R.id.button_add);
        Bundle bundle = getArguments();
        if (bundle != null) {
            position = bundle.getInt("Position", 0);
        }
        recycler = view.findViewById(R.id.recycler_part_request);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter = new PartRequestPresenter(getContext(), this);
        presenter.initPartRequestData();
        btnAdd.setOnClickListener(this);
        return view;
    }

    @Override
    public void connectAdapter(ArrayList<PartRequestModel> partRequestList) {
        PartRequestAdapter adapter = new PartRequestAdapter(getContext(), this, partRequestList);
        recycler.setAdapter(adapter);
    }

    @Override
    public void restartAdapter(String document, String date, String shipCode, String shipName, String opinion) {
        presenter.postPartRequestData(document, date, shipCode, shipName, opinion);
    }

    @Override
    public void connectFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getDocumentId(String documentId) {
        fragmentCallback.changeFragment(documentId, position);
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
}
