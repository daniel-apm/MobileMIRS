package mdb.project.mobilemirs.View.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import mdb.project.mobilemirs.Interface.IItem;
import mdb.project.mobilemirs.Manager.SessionManager;
import mdb.project.mobilemirs.R;
import mdb.project.mobilemirs.View.Fragment.DetailPartRequestFragment;
import mdb.project.mobilemirs.View.Fragment.EngineFragment;
import mdb.project.mobilemirs.View.Fragment.PartRequestFragment;
import mdb.project.mobilemirs.View.Fragment.TableFragment;

public class ItemActivity extends AppCompatActivity implements IItem, View.OnClickListener {

    private FragmentManager manager;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        ImageView optionButton = findViewById(R.id.button_option);
        TextView userText = findViewById(R.id.text_view_user);
        SessionManager session = new SessionManager(this);
        userText.setText(String.format("Mr. %s", session.getStoredString(SessionManager.KEY_ACCOUNT)));
        String title = getIntent().getStringExtra("Fragment");
        getSupportActionBar().setTitle(title);
        manager = getSupportFragmentManager();
        position = getIntent().getIntExtra("Position", -1);
        displayFragment();
        optionButton.setOnClickListener(this);
    }

    private void displayFragment() {
        if (position == 0) {
            EngineFragment fragment = new EngineFragment();
            manager.beginTransaction().add(R.id.container_fragment, fragment, null).commit();
        } else if (position == 4) {
            PartRequestFragment fragment = new PartRequestFragment();
            manager.beginTransaction().add(R.id.container_fragment, fragment, null).commit();
        } else {
            Toast.makeText(this, "There is no fragment yet", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Coming soon!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void changeFragment(String content) {
        Bundle bundle = new Bundle();
        bundle.putString("Content", content);
        if (position == 0) {
            TableFragment fragment = new TableFragment();
            fragment.setArguments(bundle);
            manager.beginTransaction().replace(R.id.container_fragment, fragment, null).addToBackStack(null).commit();
        } else if (position == 4) {
            DetailPartRequestFragment fragment = new DetailPartRequestFragment();
            fragment.setArguments(bundle);
            manager.beginTransaction().replace(R.id.container_fragment, fragment, null).addToBackStack(null).commit();
        } else {
            Toast.makeText(this, "No replace fragments", Toast.LENGTH_SHORT).show();
        }
    }
}
