package mdb.project.mobilemirs.API;

import android.app.ProgressDialog;
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
    private ProgressDialog dialog;

    public VolleyService(IResultJSON parsingJSONPresenter, Context mContext) {
        this.parsingJSONPresenter = parsingJSONPresenter;
        this.mContext = mContext;
    }

    private void showProgress() {
        dialog = new ProgressDialog(mContext);
        dialog.setMessage("Loading...");
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public void postDataVolley(String url, final JSONObject params) {
        try {
            showProgress();
            JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (parsingJSONPresenter != null) {
                        dialog.hide();
                        parsingJSONPresenter.notifySuccess(response);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (parsingJSONPresenter != null) {
                        dialog.hide();
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
            showProgress();
            JsonObjectRequest jr = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (parsingJSONPresenter != null) {
                        dialog.hide();
                        parsingJSONPresenter.notifySuccess(response);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (parsingJSONPresenter != null) {
                        dialog.hide();
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
            Volley.newRequestQueue(mContext).add(jr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void putDataVolley(String url, final JSONObject params) {
        try {
            showProgress();
            JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.PUT, url, params, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if (parsingJSONPresenter != null) {
                        dialog.hide();
                        parsingJSONPresenter.notifySuccess(response);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    if (parsingJSONPresenter != null) {
                        dialog.hide();
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
}
