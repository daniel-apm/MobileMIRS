package mdb.project.mobilemirs.View.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mdb.project.mobilemirs.Interface.IHome;
import mdb.project.mobilemirs.Manager.SessionManager;
import mdb.project.mobilemirs.Presenter.HomePresenter;
import mdb.project.mobilemirs.R;
import mdb.project.mobilemirs.View.Fragment.MenuFragment;
import mdb.project.mobilemirs.View.Fragment.ShipFragment;

public class HomeActivity extends AppCompatActivity implements IHome, View.OnClickListener {

    private SessionManager session;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView userText = findViewById(R.id.text_view_user);
        ImageView logoutButton = findViewById(R.id.button_log_out);
        session = new SessionManager(this);
        userText.setText(String.format("Mr. %s", session.getStoredString(SessionManager.KEY_ACCOUNT)));
        HomePresenter presenter = new HomePresenter(this);
        presenter.initDate();
        manager = getSupportFragmentManager();
        ShipFragment fragment = new ShipFragment();
        manager.beginTransaction().add(R.id.container_ship, fragment, null).commit();
        logoutButton.setOnClickListener(this);
    }

    @Override
    public void changeFragment() {
        MenuFragment fragment = new MenuFragment();
        manager.beginTransaction().replace(R.id.container_ship, fragment, null).addToBackStack(null).commit();
    }

    @Override
    public void changeActivity(String menu, int position) {
        Intent intent = new Intent(this, ItemActivity.class);
        intent.putExtra("Fragment", menu);
        intent.putExtra("Position", position);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        showWarning();
    }

    public void showWarning() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm");
        builder.setMessage("Are you sure want to log out?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                session.putSessionString(SessionManager.KEY_ACCOUNT, "");
                session.putSessionString(SessionManager.KEY_DATE, "");
                session.saveSession(SessionManager.KEY_LOGGED_IN, false);
                Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
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
