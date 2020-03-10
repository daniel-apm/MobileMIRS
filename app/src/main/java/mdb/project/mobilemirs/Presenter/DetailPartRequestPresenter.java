package mdb.project.mobilemirs.Presenter;

import android.content.Context;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import mdb.project.mobilemirs.API.APIService;
import mdb.project.mobilemirs.API.MethodKey;
import mdb.project.mobilemirs.API.VolleyService;
import mdb.project.mobilemirs.Interface.IDetailPartRequest;
import mdb.project.mobilemirs.Interface.IResultJSON;
import mdb.project.mobilemirs.Model.DetailPartRequestModel;
import mdb.project.mobilemirs.Model.PartCenterModel;

public class DetailPartRequestPresenter {

    private Context context;
    private IDetailPartRequest presenterDetailCallback;
    private IResultJSON requestCallback;
    private APIService params;

    public DetailPartRequestPresenter(Context context, IDetailPartRequest presenterDetailCallback) {
        this.context = context;
        this.presenterDetailCallback = presenterDetailCallback;
        params = new APIService();
    }

    public void initDetailPartRequestData(String documentId) {
        initDetailRequestCallback();
        VolleyService service = new VolleyService(requestCallback, context);
        service.postDataVolley(MethodKey.KEY_GET_DETAIL_PART_CENTER, params.getDetailPartRequest(documentId));
    }

    public ArrayList<PartCenterModel> initPartCenterData(String[] partCenterId, String[] partName, String[] merk, String[] type) {
        ArrayList<PartCenterModel> arrayList = new ArrayList<>();
        for (int i = 0; i < partCenterId.length; i++) {
            arrayList.add(new PartCenterModel(partCenterId[i], partName[i], merk[i], type[i]));
        }
        return arrayList;
    }

    private void initDetailRequestCallback() {
        requestCallback = new IResultJSON() {
            @Override
            public void notifySuccess(JSONObject response) {
                try {
                    if (response.get("code").toString().equals("200")) {
                        ArrayList<DetailPartRequestModel> detailPartRequestList = new ArrayList<>();
                        JSONArray jsonArray = new JSONArray(response.get("data").toString());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            JSONArray innerArray = new JSONArray(object.get("order_part_center_line").toString());
                            for (int j = 0; j < innerArray.length(); j++) {
                                JSONObject innerObject = innerArray.getJSONObject(j);
                                String part = innerObject.getString("nama_part_center");
                                String merk = innerObject.getString("merk");
                                int quantity = innerObject.getInt("qty");
                                String reqDate = innerObject.getString("arrival_request_date");
                                detailPartRequestList.add(new DetailPartRequestModel(part, merk, quantity, reqDate));
                            }
                        }
                        presenterDetailCallback.connectAdapter(detailPartRequestList);
                    } else {
                        presenterDetailCallback.connectFailed("There is no data available");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                presenterDetailCallback.connectFailed("Can't connect to server");
            }
        };
    }

}
