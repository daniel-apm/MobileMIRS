package mdb.project.mobilemirs.Interface;

import java.util.ArrayList;

import mdb.project.mobilemirs.Model.MenuModel;

public interface IMenu {
    void connectAdapter(ArrayList<MenuModel> menuList);
//    void connectFailed(String message);
    void selectMenu(String menu, int position);
}
