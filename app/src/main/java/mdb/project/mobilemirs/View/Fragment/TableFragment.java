package mdb.project.mobilemirs.View.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import mdb.project.mobilemirs.Manager.SessionManager;
import mdb.project.mobilemirs.R;

public class TableFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_table, container, false);
        SessionManager session = new SessionManager(getContext());
        String date = session.getStoredString(SessionManager.KEY_DATE);
        String dateInMonth = date.substring(8);
        TextView tvDate = view.findViewById(R.id.text_view_date);
        TextView tvEngine = view.findViewById(R.id.text_view_engine);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String engineName = bundle.getString("Content");
            tvEngine.setText(engineName);
        }
        tvDate.setText(dateInMonth);
        return view;
    }
}
