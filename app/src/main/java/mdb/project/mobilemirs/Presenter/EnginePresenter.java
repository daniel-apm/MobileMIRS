package mdb.project.mobilemirs.Presenter;

import android.content.Context;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mdb.project.mobilemirs.API.MethodKey;
import mdb.project.mobilemirs.API.VolleyService;
import mdb.project.mobilemirs.Interface.IEngine;
import mdb.project.mobilemirs.Interface.IResultJSON;
import mdb.project.mobilemirs.Model.EngineModel;

public class EnginePresenter implements IResultJSON {

    private Context context;
    private IEngine presenterEngineCallback;

    public EnginePresenter(Context context, IEngine presenterEngineCallback) {
        this.context = context;
        this.presenterEngineCallback = presenterEngineCallback;
    }

    public void initEngineData() {
        VolleyService volleyService = new VolleyService(this, context);
        volleyService.getDataVolley(MethodKey.KEY_MASTER_ENGINE);
    }

    @Override
    public void notifySuccess(JSONObject response) {
        try {
            if (response.get("code").toString().equals("200")) {
                ArrayList<EngineModel> engineList = new ArrayList<>();
                JSONArray jsonArray = new JSONArray(response.get("data").toString());
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String engineText = jsonObject.get("name").toString();
                    engineList.add(new EngineModel(engineText));
                }
                presenterEngineCallback.connectAdapter(engineList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void notifyError(VolleyError error) {
        presenterEngineCallback.connectFailed("Can't connect to server");
    }
}
