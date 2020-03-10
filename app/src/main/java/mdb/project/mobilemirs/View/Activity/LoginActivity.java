package mdb.project.mobilemirs.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import mdb.project.mobilemirs.Interface.ILogin;
import mdb.project.mobilemirs.Manager.SessionManager;
import mdb.project.mobilemirs.Presenter.LoginPresenter;
import mdb.project.mobilemirs.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILogin {

    private LoginPresenter presenterLogin;
    private EditText editTextUsername, editTextPassword;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        Button buttonSignIn = findViewById(R.id.sign_in_button);
        editTextUsername = findViewById(R.id.username_edit_text);
        editTextPassword = findViewById(R.id.password_edit_text);
        SessionManager session = new SessionManager(this);
        if (session.retrieveSession()) {
            intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }
        presenterLogin = new LoginPresenter(this, this);
        buttonSignIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this, "All field must be filled", Toast.LENGTH_SHORT).show();
        } else {
            presenterLogin.onLogin(username, password);
        }
    }

    @Override
    public void onLoginSuccess(String message, String fullName) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
