package mdb.project.mobilemirs.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mdb.project.mobilemirs.Adapter.EngineAdapter;
import mdb.project.mobilemirs.Interface.IEngine;
import mdb.project.mobilemirs.Interface.IItem;
import mdb.project.mobilemirs.Manager.SessionManager;
import mdb.project.mobilemirs.Model.EngineModel;
import mdb.project.mobilemirs.Presenter.EnginePresenter;
import mdb.project.mobilemirs.R;

public class EngineFragment extends Fragment implements IEngine {

    private RecyclerView recycler;
    private IItem fragmentCallback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_engine, container, false);
        recycler = view.findViewById(R.id.recycler_engine);
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        TextView dateText = view.findViewById(R.id.text_view_date);
        if (getContext() != null) {
            SessionManager session = new SessionManager(getContext());
            dateText.setText(session.getStoredString(SessionManager.KEY_DATE));
        }
        EnginePresenter presenter = new EnginePresenter(getContext(), this);
        presenter.initEngineData();
        return view;
    }

    @Override
    public void connectAdapter(ArrayList<EngineModel> engineList) {
        EngineAdapter adapter = new EngineAdapter(getContext(), engineList, this);
        recycler.setAdapter(adapter);
    }

    @Override
    public void connectFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void selectEngine(String engineName) {
        fragmentCallback.changeFragment(engineName);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IItem) {
            fragmentCallback = (IItem) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement IItem");
        }
    }
}
