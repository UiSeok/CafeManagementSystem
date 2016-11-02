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
		// �ٸ���Ÿ�� ��� DataBase�� ���� �����Ͽ� BaristaManager�� ������ ��
		// DataBase�� �����Ͽ� ���� ������� �ٸ���Ÿ�� ������ �о�� �� ����ϵ��� �����ؾ� �Ѵ�.
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
		// �޴��� �޴� ����� ���� �����ϴ� ���� �ùٸ���.
		System.out.println("1. ����������");
		System.out.println("2. �Ƹ޸�ī��");
		System.out.println("3. ���� �ֽ�");
		System.out.println("4. ī���");
	}
	
	public void order(OrderData order){
		orderQueue.add(order);
		BaristaManager.getInstnace().addOrder(BaristaManager.getInstnace().getHaveLeatTimeBarista(), order);
	}
	
	public void removeOrder(){
		// �� ����
		orderQueue.remove();
	}
}
