package mdb.project.mobilemirs.Presenter;

import android.content.Context;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;

import mdb.project.mobilemirs.API.APIService;
import mdb.project.mobilemirs.API.MethodKey;
import mdb.project.mobilemirs.API.VolleyService;
import mdb.project.mobilemirs.Interface.IDetailPartRequest;
import mdb.project.mobilemirs.Interface.IResultJSON;
import mdb.project.mobilemirs.Model.DetailPartRequestModel;

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
        service.postDataVolley(MethodKey.KEY_GET_DETAIL_PART_CENTER, params.putDocumentId(documentId));
    }

    public ArrayList<DetailPartRequestModel> initPartCenterData(String[] partCenterId, String[] partName, String[] merk, String[] type) {
        ArrayList<DetailPartRequestModel> arrayList = new ArrayList<>();
        for (int i = 0; i < partCenterId.length; i++) {
            DetailPartRequestModel model = new DetailPartRequestModel();
            model.setPartId(partCenterId[i]);
            model.setPartName(partName[i]);
            model.setMerk(merk[i]);
            model.setType(type[i]);
            arrayList.add(model);
        }
        return arrayList;
    }

    public void postDetailPartRequest(DetailPartRequestModel model, String documentId) {
        initPostDetailRequestCallback(documentId);
        VolleyService service = new VolleyService(requestCallback, context);
        service.putDataVolley(MethodKey.KEY_POST_PART_CENTER + "/" + documentId, params.postPartRequestDetail(model));
    }

    public void appendDetailPartRequest(DetailPartRequestModel model, String documentId) {
        initPostDetailRequestCallback(documentId);
        VolleyService service = new VolleyService(requestCallback, context);
        service.postDataVolley(MethodKey.KEY_POST_DETAIL_PART_CENTER, params.appendPartRequestDetail(model, documentId));
    }

    public void deleteDetailPartRequest(String documentId, int idx) {
        initDeleteDetailRequestCallback(documentId);
        VolleyService service = new VolleyService(requestCallback, context);
        service.postDataVolley(MethodKey.KEY_DELETE_DETAIL_PART_CENTER, params.deletePartRequestDetail(documentId, idx));
    }

    private void initPostDetailRequestCallback(final String documentId) {
        requestCallback = new IResultJSON() {
            @Override
            public void notifySuccess(JSONObject response) {
                presenterDetailCallback.showMessage("Create Part Detail Success");
                initDetailPartRequestData(documentId);
            }

            @Override
            public void notifyError(VolleyError error) {
                presenterDetailCallback.showMessage("Create Part Detail Failed");
            }
        };
    }

    private void initDeleteDetailRequestCallback(final String documentId) {
        requestCallback = new IResultJSON() {
            @Override
            public void notifySuccess(JSONObject response) {
                presenterDetailCallback.showMessage("Delete Part Detail Success");
                initDetailPartRequestData(documentId);
            }

            @Override
            public void notifyError(VolleyError error) {
                presenterDetailCallback.showMessage("Delete Part Detail Failed");
            }
        };
    }

    private void initDetailRequestCallback() {
        requestCallback = new IResultJSON() {
            @Override
            public void notifySuccess(JSONObject response) {
                try {
                    ArrayList<DetailPartRequestModel> detailPartRequestList = new ArrayList<>();
                    if (response.get("code").toString().equals("200")) {
                        JSONArray jsonArray = new JSONArray(response.get("data").toString());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            JSONArray innerArray = new JSONArray(object.get("order_part_center_line").toString());
                            for (int j = 0; j < innerArray.length(); j++) {
                                JSONObject innerObject = innerArray.getJSONObject(j);
                                DetailPartRequestModel model = new DetailPartRequestModel();
                                model.setParentId(innerObject.getString("parent"));
                                model.setLineId(innerObject.getInt("idx"));
                                model.setPartName(innerObject.getString("nama_part_center"));
                                model.setMerk(innerObject.getString("merk"));
                                model.setQuantity(innerObject.getInt("qty"));
                                model.setReqDate(innerObject.getString("arrival_request_date"));
                                detailPartRequestList.add(model);
                            }
                        }
                    }
                    presenterDetailCallback.connectAdapter(detailPartRequestList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                presenterDetailCallback.showMessage("Can't connect to server");
            }
        };
    }

}
