package mdb.project.mobilemirs.Model;

public class MenuModel {

    private String menuName;
    private int menuImage;

    public MenuModel(String menuName, int menuImage) {
        this.menuName = menuName;
        this.menuImage = menuImage;
    }

    public String getMenuName() {
        return menuName;
    }

    public int getMenuImage() {
        return menuImage;
    }
}
