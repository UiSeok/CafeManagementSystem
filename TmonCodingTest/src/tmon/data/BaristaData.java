package tmon.data;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import tmon.data.constant.BeverageConstant;

public class BaristaData extends EmployeeData {

	// Different from other Barista
	private Map<Integer, Integer> goodOrBadAt;
	Queue<OrderData> orderList;
	private int totalTime = 0;

	public BaristaData() {
		goodOrBadAt = new HashMap<Integer, Integer>();
		orderList = new LinkedList<OrderData>();
	}

	public BaristaData(int id, String name, int age, Date join_date,
			Date retire_date, int salary) {
		super(id, name, age, join_date, retire_date, salary);
		goodOrBadAt = new HashMap<Integer, Integer>();
		orderList = new LinkedList<OrderData>();
	}

	public void setGoodOrBadAt(int bevId, int time) {
		goodOrBadAt.put(bevId, time);
	}

	public Map<Integer, Integer> getGoodOrBadAt() {
		return goodOrBadAt;
	}

	public void addOrder(OrderData data) {
		orderList.add(data);
		for (Beverage bev : data.beverages) {
			if (goodOrBadAt.containsKey(bev.bevId))
				totalTime += goodOrBadAt.get(bev.bevId) * bev.count;
			else {
				switch (bev.bevId) {
				case BeverageConstant.ESPRESSO:
					totalTime += BeverageConstant.BASIC_TIME_MAKE_ESPRESSO
							* bev.count;
					break;
				case BeverageConstant.AMERICANO:
					totalTime += BeverageConstant.BASIC_TIME_MAKE_AMERICANO
							* bev.count;
					break;
				case BeverageConstant.FRUITJUICE:
					totalTime += BeverageConstant.BASIC_TIME_MAKE_FRUITJUICE
							* bev.count;
					break;
				case BeverageConstant.CAFELATTE:
					totalTime += BeverageConstant.BASIC_TIME_MAKE_CAFELATTE
							* bev.count;
					break;
				}
			}
		}
	}

	public void removeOrder() {
		OrderData removedData = orderList.remove();
		for (Beverage bev : removedData.beverages) {
			if (goodOrBadAt.containsKey(bev.bevId))
				totalTime -= goodOrBadAt.get(bev.bevId) * bev.count;
			else {
				switch (bev.bevId) {
				case BeverageConstant.ESPRESSO:
					totalTime -= BeverageConstant.BASIC_TIME_MAKE_ESPRESSO
							* bev.count;
					break;
				case BeverageConstant.AMERICANO:
					totalTime -= BeverageConstant.BASIC_TIME_MAKE_AMERICANO
							* bev.count;
					break;
				case BeverageConstant.FRUITJUICE:
					totalTime -= BeverageConstant.BASIC_TIME_MAKE_FRUITJUICE
							* bev.count;
					break;
				case BeverageConstant.CAFELATTE:
					totalTime -= BeverageConstant.BASIC_TIME_MAKE_CAFELATTE
							* bev.count;
					break;
				}
			}
		}
	}

	public int getTotalTime() {
		return totalTime;
	}
}
