package mdb.project.mobilemirs.View.Fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import mdb.project.mobilemirs.Adapter.DateButtonAdapter;
import mdb.project.mobilemirs.Interface.OnDateButtonListener;
import mdb.project.mobilemirs.R;

public class DateButtonFragment extends Fragment {

    private int[] date;
    private OnDateButtonListener mCallback;

    public DateButtonFragment(int[] date) {
        this.date = date;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_button, container, false);
        GridView gridView = view.findViewById(R.id.grid_view);
        DateButtonAdapter dateButtonAdapter = new DateButtonAdapter(getContext(), date, mCallback);
        gridView.setAdapter(dateButtonAdapter);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnDateButtonListener) {
            mCallback = (OnDateButtonListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnDateButtonListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
    }
}
