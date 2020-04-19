package mdb.project.mobilemirs.Presenter;

import android.app.ProgressDialog;
import android.content.Context;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mdb.project.mobilemirs.API.APIService;
import mdb.project.mobilemirs.API.MethodKey;
import mdb.project.mobilemirs.API.VolleyService;
import mdb.project.mobilemirs.Interface.IPartRequest;
import mdb.project.mobilemirs.Interface.IResultJSON;
import mdb.project.mobilemirs.Model.PartRequestModel;

public class PartRequestPresenter {

    private Context context;
    private IPartRequest presenterPartRequestCallback;
    private IResultJSON resultCallback;
    private APIService params;
    private VolleyService service;

    public PartRequestPresenter(Context context, IPartRequest presenterPartRequestCallback) {
        this.context = context;
        this.presenterPartRequestCallback = presenterPartRequestCallback;
        params = new APIService();
    }

    public void initPartRequestData() {
        initPartRequestCallback();
        service = new VolleyService(resultCallback, context);
        service.getDataVolley(MethodKey.KEY_GET_PART_CENTER);
    }

    public void postPartRequestData(String document, String date, String shipCode, String shipName, String opinion) {
        initPostCallback();
        service = new VolleyService(resultCallback, context);
        service.postDataVolley(MethodKey.KEY_POST_PART_CENTER, params.postPartCenter(document, date, shipCode, shipName, opinion));
    }

    public void deletePartRequestData(String documentId) {
        initDeleteCallback();
        service = new VolleyService(resultCallback, context);
        service.postDataVolley(MethodKey.KEY_DELETE_PART_CENTER, params.putDocumentId(documentId));
    }


    private void initPartRequestCallback() {
        resultCallback =  new IResultJSON() {
            @Override
            public void notifySuccess(JSONObject response) {
                try {
                    ArrayList<PartRequestModel> partRequestList = new ArrayList<>();
                    JSONArray jsonArray = new JSONArray(response.get("data").toString());
                    for (int i = 0; i <jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String documentId = jsonObject.getString("name");
                        String document = jsonObject.getString("code_kapal");
                        String date = jsonObject.getString("tanggal_order_part_center");
                        String status = jsonObject.getString("workflow_state");
                        partRequestList.add(new PartRequestModel(documentId, document, date, status));
                    }
                    presenterPartRequestCallback.connectAdapter(partRequestList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                presenterPartRequestCallback.showMessage("Can't connect to server");
            }
        };
    }

    private void initDeleteCallback() {
        resultCallback = new IResultJSON() {
            @Override
            public void notifySuccess(JSONObject response) {
                presenterPartRequestCallback.showMessage("Delete Part Request Success");
                initPartRequestData();
            }

            @Override
            public void notifyError(VolleyError error) {
                presenterPartRequestCallback.showMessage("Delete Part Request Failed");
            }
        };
    }

    private void initPostCallback() {
        resultCallback = new IResultJSON() {
            @Override
            public void notifySuccess(JSONObject response) {
                presenterPartRequestCallback.showMessage("Post Part Request Success");
                initPartRequestData();
            }

            @Override
            public void notifyError(VolleyError error) {
                presenterPartRequestCallback.showMessage("Post Part Request Failed");
            }
        };
    }
}
