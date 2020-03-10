package mdb.project.mobilemirs.Interface;

import java.util.ArrayList;

import mdb.project.mobilemirs.Model.EngineModel;

public interface IEngine {
    void connectAdapter(ArrayList<EngineModel> engineList);
    void connectFailed(String message);
    void selectEngine(String engineName);
}
