package mdb.project.mobilemirs.Presenter;

import android.content.Context;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import mdb.project.mobilemirs.API.APIService;
import mdb.project.mobilemirs.API.MethodKey;
import mdb.project.mobilemirs.API.ParamKey;
import mdb.project.mobilemirs.API.VolleyService;
import mdb.project.mobilemirs.Interface.IResultJSON;
import mdb.project.mobilemirs.Interface.ILogin;
import mdb.project.mobilemirs.Manager.SessionManager;

public class LoginPresenter implements IResultJSON {

    private Context mContext;
    private ILogin loginView;
    private APIService params;

    public LoginPresenter(Context mContext, ILogin loginView) {
        this.mContext = mContext;
        this.loginView = loginView;
        params = new APIService();
    }

    public void onLogin(String username, String password) {
        VolleyService volleyService = new VolleyService(this, mContext);
        volleyService.postDataVolley(MethodKey.KEY_URL_LOGIN, params.login(username, password));
    }

    @Override
    public void notifySuccess(JSONObject response) {
        try {
            if (response.get("message").toString().equals("Logged In")) {
                loginProcess(response.get("message").toString(), response.get("full_name").toString());
            }
        } catch (JSONException e) {
            loginView.onLoginFailed(e.getMessage());
        }
    }

    @Override
    public void notifyError(VolleyError error) {
        loginView.onLoginFailed("Login Failed");
    }

    private void loginProcess(String message, String account) {
        SessionManager session = new SessionManager(mContext);
        session.putSessionString(SessionManager.KEY_ACCOUNT, account);
        session.saveSession(SessionManager.KEY_LOGGED_IN, true);
        loginView.onLoginSuccess(message, account);
    }
}
