package mdb.project.mobilemirs.Interface;

import com.android.volley.VolleyError;

import org.json.JSONObject;

public interface IResultJSON {
    void notifySuccess(JSONObject response);
    void notifyError(VolleyError error);
}
