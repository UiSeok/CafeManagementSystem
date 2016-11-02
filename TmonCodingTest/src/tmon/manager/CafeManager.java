package tmon.manager;

import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import tmon.data.BaristaData;
import tmon.data.OrderData;
import tmon.data.constant.BeverageConstant;

public class CafeManager {
	private static CafeManager instance;
	private Queue<OrderData> orderQueue;

	public static CafeManager getInstnace() {
		if (instance == null) {
			instance = new CafeManager();
		}
		return instance;
	}
	public CafeManager() {
		orderQueue = new LinkedList<OrderData>();
	}
	

	public void openCafe() {
		// 바리스타의 경우 DataBase를 따로 제작하여 BaristaManager를 생성할 시
		// DataBase를 연결하여 현재 고용중인 바리스타의 정보를 읽어온 후 사용하도록 변경해야 한다.
		BaristaManager.getInstnace().addBarista(
				new BaristaData(1, "barista1", 20, new Date(), null, 3000));
		BaristaManager.getInstnace().addBarista(
				new BaristaData(2, "barista2", 20, new Date(), null, 3000));
		BaristaManager.getInstnace().addBarista(
				new BaristaData(3, "barista3", 20, new Date(), null, 3000));
		BaristaManager.getInstnace().addBarista(
				new BaristaData(4, "barista4", 20, new Date(), null, 3000));

		BaristaManager.getInstnace().getBarista(1)
				.setGoodOrBadAt(BeverageConstant.FRUITJUICE, 4000);
		BaristaManager.getInstnace().getBarista(2)
				.setGoodOrBadAt(BeverageConstant.AMERICANO, 4000);
		BaristaManager.getInstnace().getBarista(4)
				.setGoodOrBadAt(BeverageConstant.CAFELATTE, 5000);
	}
	
	public void closeCafe(){
		BaristaManager.getInstnace().clear();	
	}
	
	public void showMenu(){
		// 메뉴는 메뉴 모듈을 따로 구성하는 것이 올바르다.
		System.out.println("1. 에스프레소");
		System.out.println("2. 아메리카노");
		System.out.println("3. 과일 주스");
		System.out.println("4. 카페라떼");
	}
	
	public void order(OrderData order){
		orderQueue.add(order);
		BaristaManager.getInstnace().addOrder(BaristaManager.getInstnace().getHaveLeatTimeBarista(), order);
	}
	
	public void removeOrder(){
		// 미 구현
		orderQueue.remove();
	}
}
