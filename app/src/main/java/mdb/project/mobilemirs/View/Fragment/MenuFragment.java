package mdb.project.mobilemirs.View.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import mdb.project.mobilemirs.Adapter.MenuAdapter;
import mdb.project.mobilemirs.Interface.IHome;
import mdb.project.mobilemirs.Interface.IMenu;
import mdb.project.mobilemirs.Model.MenuModel;
import mdb.project.mobilemirs.Presenter.HomePresenter;
import mdb.project.mobilemirs.R;

public class MenuFragment extends Fragment implements IMenu {

    private IHome fragmentCallback;
    private String[] nameList = {"Daily Check Sheet", "Daily Check Fuel Tank", "Trouble Report", "Daily Production", "Request Part Form", "Delivery Part Form"};
    private int[] imageList = {R.drawable.daily_check_sheet, R.drawable.daily_check_fuel_tank, R.drawable.trouble_report, R.drawable.daily_production, R.drawable.request_part_form, R.drawable.delivery_part_form};
    private RecyclerView recycler;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_menu, container, false);
        recycler = view.findViewById(R.id.recycler_menu);
        recycler.setLayoutManager(new GridLayoutManager(getContext(), 2));
        HomePresenter presenter = new HomePresenter(getContext(), this);
        presenter.initMenuData(nameList, imageList);
        return view;
    }

    @Override
    public void connectAdapter(ArrayList<MenuModel> menuList) {
        MenuAdapter adapter = new MenuAdapter(getContext(), menuList, this);
        recycler.setAdapter(adapter);
    }

    @Override
    public void selectMenu(String menu, int position) {
        fragmentCallback.changeActivity(menu, position);
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
