package mdb.project.mobilemirs.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mdb.project.mobilemirs.Adapter.ShipAdapter;
import mdb.project.mobilemirs.Interface.IHome;
import mdb.project.mobilemirs.Interface.IShip;
import mdb.project.mobilemirs.Model.ShipModel;
import mdb.project.mobilemirs.Presenter.HomePresenter;
import mdb.project.mobilemirs.R;

public class ShipFragment extends Fragment implements IShip {

    private RecyclerView recycler;
    private HomePresenter presenter;
    private IHome fragmentCallback;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_ship, container, false);
        recycler = view.findViewById(R.id.recycler_ship);
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter = new HomePresenter(getContext(), this);
        presenter.initShipData();
        return view;
    }

    @Override
    public void connectAdapter(ArrayList<ShipModel> shipList) {
        ShipAdapter adapter = new ShipAdapter(getContext(), this, shipList);
        recycler.setAdapter(adapter);
    }

    @Override
    public void connectFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void selectShip(String shipCode, String shipName) {
        presenter.setShipSession(shipCode, shipName);
        fragmentCallback.changeFragment();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof IHome) {
            fragmentCallback = (IHome) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement IHome");
        }
    }
}
