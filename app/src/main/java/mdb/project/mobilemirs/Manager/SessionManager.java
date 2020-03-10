package mdb.project.mobilemirs.Manager;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private static final String KEY_SESSION = "Mobile_MIRS";
    public static final String KEY_ACCOUNT = "account";
    public static final String KEY_LOGGED_IN = "is_logged_in";
    public static final String KEY_SHIP_CODE = "shipCode";
    public static final String KEY_SHIP_NAME = "shipName";
    public static final String KEY_DATE = "date";
    private SharedPreferences sp;
    private SharedPreferences.Editor spEditor;

    public SessionManager(Context context) {
        sp = context.getSharedPreferences(KEY_SESSION, Context.MODE_PRIVATE);
        spEditor = sp.edit();
    }

    public void putSessionString(String keySP, String value) {
        spEditor.putString(keySP, value);
        spEditor.apply();
    }

    public void saveSession(String keySp, boolean value) {
        spEditor.putBoolean(keySp, value);
        spEditor.commit();
    }

    public String getStoredString(String keySp) {
        return sp.getString(keySp, "");
    }

    public Boolean retrieveSession() {
        return sp.getBoolean(KEY_LOGGED_IN, false);
    }
}
