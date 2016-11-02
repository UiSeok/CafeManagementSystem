package tmon.manager;

import java.util.ArrayList;
import java.util.List;

import tmon.data.BaristaData;
import tmon.data.OrderData;

public class BaristaManager {
	private static BaristaManager instance;
	private List<BaristaData> baristaList;
	private int[] totalTimeArray;

	public static BaristaManager getInstnace() {
		if (instance == null) {
			instance = new BaristaManager();
		}
		return instance;
	}

	private BaristaManager() {
		baristaList = new ArrayList<BaristaData>();
		totalTimeArray = new int[baristaList.size()];
	}

	public void addBarista(BaristaData data) {
		baristaList.add(data);
		totalTimeArray = new int[baristaList.size()];
		for (int i = 0; i < baristaList.size(); i++) {
			totalTimeArray[i] = baristaList.get(i).getTotalTime();
		}
	}

	public BaristaData getBarista(int id) {
		for (int i = 0; i < baristaList.size(); i++) {
			if (baristaList.get(i).getId() == id)
				return baristaList.get(i);
		}
		return null;
	}

	public int getHaveLeatTimeBarista() {
		int id;
		int time = totalTimeArray[0];
		int index = 0;
		for (int i = 1; i < baristaList.size(); i++) {
			index = time > totalTimeArray[i] ? index : i;
			time = time > totalTimeArray[i] ? time : totalTimeArray[i];
		}
		id = baristaList.get(index).getId();
		return id;
	}
	
	public void addOrder(int id, OrderData order){
		for( BaristaData barista : baristaList){
			if( barista.getId() == id )
				barista.addOrder(order);
		}
	}
	
	public void removeOrder(){};

	public void clear() {
		instance = null;
		baristaList = null;
	}
}
