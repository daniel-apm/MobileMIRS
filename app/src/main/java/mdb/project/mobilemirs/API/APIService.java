package mdb.project.mobilemirs.API;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mdb.project.mobilemirs.Model.DetailPartRequestModel;

public class APIService {

    private JSONObject params;

    public JSONObject login(String username, String password) {
        params = new JSONObject();
        try {
            params.put(ParamKey.KEY_USERNAME, username);
            params.put(ParamKey.KEY_PASSWORD, password);
            return params;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return params;
    }

    public JSONObject postPartCenter(String document, String date, String codeKapal, String namaKapal, String opini) {
        params = new JSONObject();
        try {
            params.put(ParamKey.KEY_DOCUMENT_PART_CENTER, document);
            params.put(ParamKey.KEY_DATE_PART_CENTER, date);
            params.put(ParamKey.KEY_CODE_KAPAL, codeKapal);
            params.put(ParamKey.KEY_NAME_KAPAL, namaKapal);
            params.put(ParamKey.KEY_OPINI_PART_CENTER, opini);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return params;
    }

    public JSONObject postPartRequestDetail(DetailPartRequestModel model) {
        params = new JSONObject();
        JSONArray data = new JSONArray();
        JSONObject obj = new JSONObject();
        try {
            obj.put(ParamKey.KEY_PART_CENTER_ID, model.getPartId());
            obj.put(ParamKey.KEY_PART_CENTER_NAME, model.getPartName());
            obj.put(ParamKey.KEY_PART_CENTER_MERK, model.getMerk());
            obj.put(ParamKey.KEY_PART_CENTER_QTY, model.getQuantity());
            obj.put(ParamKey.KEY_PART_CENTER_REQ_DATE, model.getReqDate());
            data.put(obj);
            params.put(ParamKey.KEY_DETAIL_PART_CENTER, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }

    public JSONObject appendPartRequestDetail(DetailPartRequestModel model, String documentId) {
        params = new JSONObject();
        try {
            params.put(ParamKey.KEY_PART_CENTER_PARENT, documentId);
            params.put(ParamKey.KEY_PART_CENTER_QTY, model.getQuantity());
            params.put(ParamKey.KEY_PART_CENTER_ID, model.getPartId());
            params.put(ParamKey.KEY_PART_CENTER_REQ_DATE, model.getReqDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }

    public JSONObject deletePartRequestDetail(String parent, int idx) {
        params = new JSONObject();
        try {
            params.put(ParamKey.KEY_PART_CENTER_PARENT, parent);
            params.put(ParamKey.KEY_PART_CENTER_LINE_ID, idx);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params;
    }

    public JSONObject putDocumentId(String documentId) {
        params = new JSONObject();
        try {
            params.put(ParamKey.KEY_DOCUMENT_ID, documentId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return params;
    }
}
