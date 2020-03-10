package mdb.project.mobilemirs.API;

import org.json.JSONException;
import org.json.JSONObject;

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

    public JSONObject getDetailPartRequest(String documentId) {
        params = new JSONObject();
        try {
            params.put(ParamKey.KEY_DOCUMENT_ID, documentId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return params;
    }
}
