package mdb.project.mobilemirs.API;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import mdb.project.mobilemirs.Interface.IResultJSON;

public class VolleyService {

    private IResultJSON parsingJSONPresenter;
    private Context mContext;

    public VolleyService(IResultJSON parsingJSONPresenter, Context mContext) {
        this.parsingJSONPresenter = parsingJSONPresenter;
        this.mContext = mContext;
    }

    public void postDataVolley(String url, final JSONObject params) {
        try {
            JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (parsingJSONPresenter != null) {
                        parsingJSONPresenter.notifySuccess(response);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (parsingJSONPresenter != null) {
                        parsingJSONPresenter.notifyError(error);
                    }
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Authorization", ParamKey.KEY_TOKEN);
                    return params;
                }
            };
            Volley.newRequestQueue(mContext).add(objectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getDataVolley(String url) {
        try {
            JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (parsingJSONPresenter != null)
                        parsingJSONPresenter.notifySuccess(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                        if (parsingJSONPresenter != null)
                            parsingJSONPresenter.notifyError(error);
                }
            }) {
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("Authorization", ParamKey.KEY_TOKEN);
                    return params;
                }
            };
            Volley.newRequestQueue(mContext).add(jr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
