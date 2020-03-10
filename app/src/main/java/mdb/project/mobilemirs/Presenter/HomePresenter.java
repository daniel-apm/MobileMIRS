package mdb.project.mobilemirs.Presenter;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import mdb.project.mobilemirs.API.MethodKey;
import mdb.project.mobilemirs.API.VolleyService;
import mdb.project.mobilemirs.Interface.IMenu;
import mdb.project.mobilemirs.Interface.IShip;
import mdb.project.mobilemirs.Interface.IResultJSON;
import mdb.project.mobilemirs.Manager.SessionManager;
import mdb.project.mobilemirs.Model.MenuModel;
import mdb.project.mobilemirs.Model.ShipModel;
import mdb.project.mobilemirs.R;

public class HomePresenter {

    private Context context;
    private IShip presenterShipCallback;
    private IMenu presenterMenuCallback;
    private IResultJSON requestCallback;
    private VolleyService service;
    private SessionManager session;

    public HomePresenter(Context context) {
        this.context = context;
    }

    public HomePresenter(Context context, IShip presenterShipCallback) {
        this.context = context;
        this.presenterShipCallback = presenterShipCallback;
    }

    public HomePresenter(Context context, IMenu presenterMenuCallback) {
        this.context = context;
        this.presenterMenuCallback = presenterMenuCallback;
    }

    public void initDate() {
        initDateCallback();
        service = new VolleyService(requestCallback, context);
        service.getDataVolley(MethodKey.KEY_URL_DATE);
    }

    public void initMenuData(String[] nameList, int[] imageList) {
        ArrayList<MenuModel> menuList = new ArrayList<>();
        for (int i = 0; i < nameList.length; i++) {
            menuList.add(new MenuModel(nameList[i], imageList[i]));
        }
        presenterMenuCallback.connectAdapter(menuList);
    }

    public void initShipData() {
        initRequestCallback();
        service = new VolleyService(requestCallback, context);
        service.getDataVolley(MethodKey.KEY_MASTER_KAPAL);
    }

    private void initDateCallback() {
        requestCallback = new IResultJSON() {
            @Override
            public void notifySuccess(JSONObject response) {
                try {
                    if(response.get("code").toString().equals("200")) {
                        String date = response.get("data").toString();
                        setDateSession(date);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                Toast.makeText(context, "Date error", Toast.LENGTH_SHORT).show();
            }
        };
    }

    private void initRequestCallback() {
        requestCallback = new IResultJSON() {
            @Override
            public void notifySuccess(JSONObject response) {
                try {
                    if (response.get("code").toString().equals("200")) {
                        ArrayList<ShipModel> shipList = new ArrayList<>();
                        JSONArray array = new JSONArray(response.get("data").toString());
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject object = array.getJSONObject(i);
                            String shipCode = object.getString("code_kapal");
                            String shipName = object.getString("nama_kapal");
                            shipList.add(new ShipModel(shipCode, shipName, R.drawable.ship));
                        }
                        presenterShipCallback.connectAdapter(shipList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void notifyError(VolleyError error) {
                presenterShipCallback.connectFailed("Can't connect to server");
            }
        };
    }

    private void setDateSession(String date) {
        session = new SessionManager(context);
        session.putSessionString(SessionManager.KEY_DATE, date);
    }

    public void setShipSession(String shipCode, String shipName) {
        session = new SessionManager(context);
        session.putSessionString(SessionManager.KEY_SHIP_CODE, shipCode);
        session.putSessionString(SessionManager.KEY_SHIP_NAME, shipName);
    }


}
