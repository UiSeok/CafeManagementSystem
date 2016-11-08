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

		if (bev_list != null && bev_list.size() > 0) {
			System.out.println("============ Menu ============");
			for (Beverage bev : bev_list) {
				System.out.println(" - " + bev.bevName);
			}
		}
	}

	public List<Beverage> getMenu() {
		return bev_list;
	}

}