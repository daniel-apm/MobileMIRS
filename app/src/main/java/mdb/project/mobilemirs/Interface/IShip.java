package mdb.project.mobilemirs.Interface;

import java.util.ArrayList;

import mdb.project.mobilemirs.Model.ShipModel;

public interface IShip {
    void connectAdapter(ArrayList<ShipModel> shipList);
    void connectFailed(String message);
    void selectShip(String shipCode, String shipName);
}
