package tmon.data;

import java.util.ArrayList;
import java.util.List;

import tmon.manager.DBManager;

public class Menu {
    List<Beverage> bev_list;

    private static Menu instance;

    public static Menu getInstnace() {
        if (instance == null) {
            instance = new Menu();
        }
        return instance;
    }

    private Menu() {
        bev_list = new ArrayList<Beverage>();
        bev_list.addAll(DBManager.getInstnace().getMenu());
    }

    public void showMenu() {
        System.out
                .println("\n\n================== Tmon Cafe Menu ==================");
        for (Beverage data : bev_list) {
            System.out.println(" ==  " + data.bevName + " == ");
        }
        System.out
                .println("====================================================\n\n");
    }

    public List<Beverage> getBevList() {
        return bev_list;
    }

}