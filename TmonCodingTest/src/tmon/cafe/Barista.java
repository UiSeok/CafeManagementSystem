package tmon.cafe;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import tmon.data.constant.BeverageConstant;
import tmon.data.entity.Beverage;

public class Barista {

	private int id;
	private String name;
	private int age;
	private Date join_date;
	private Date retire_date;
	private int salary;

	private Map<Integer, Integer> beverage_management;
	Queue<Beverage> orderList;
	private int totalTime = 0;
	Thread orderCheckThread;

	public Barista(int id, String name, int age, Date join_date, Date retire_date, int salary) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.join_date = join_date;
		this.retire_date = retire_date;
		this.salary = salary;

		init();
	}

	private void init() {
		beverage_management = new HashMap<Integer, Integer>();
		for (Beverage bev : Menu.getInstnace().getBevList()) {
			if (bev.bevId == BeverageConstant.ESPRESSO) { // Espresso
				beverage_management.put(bev.bevId, 2000);
			} else if (bev.bevId == BeverageConstant.AMERICANO) {
				beverage_management.put(bev.bevId, 3000);
			} else if (bev.bevId == BeverageConstant.FRUITJUICE) { // Fruit Juice
				beverage_management.put(bev.bevId, 5000);
			} else if (bev.bevId == BeverageConstant.CAFELATTE) { // Cafe Latte
				beverage_management.put(bev.bevId, 4000);
			}
		}
		orderList = new LinkedList<Beverage>();
		orderCheckThread = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("Barista work start");
				while (true) {
					try {
						orderCheckThread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (!orderList.isEmpty()) {
						Beverage order = orderList.poll();
						try {
							orderCheckThread.sleep(beverage_management
									.get(order.bevId) * order.count);
							System.out.println("Beverage " + order.bevName
									+ " " + order.count + "cups are ready.");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

				}
			}
		});
		orderCheckThread.start();
	}

	public void addOrder(Beverage bev) {
		orderList.add(bev);

		if (beverage_management.containsKey(bev.bevId))
			totalTime += beverage_management.get(bev.bevId) * bev.count;
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

	public Beverage removeOrder() {
		Beverage removedData = null;
		if (!orderList.isEmpty()) {
			removedData = orderList.remove();

			if (beverage_management.containsKey(removedData.bevId))
				totalTime -= beverage_management.get(removedData.bevId)
						* removedData.count;
			else {
				switch (removedData.bevId) {
				case BeverageConstant.ESPRESSO:
					totalTime -= BeverageConstant.BASIC_TIME_MAKE_ESPRESSO
							* removedData.count;
					break;
				case BeverageConstant.AMERICANO:
					totalTime -= BeverageConstant.BASIC_TIME_MAKE_AMERICANO
							* removedData.count;
					break;
				case BeverageConstant.FRUITJUICE:
					totalTime -= BeverageConstant.BASIC_TIME_MAKE_FRUITJUICE
							* removedData.count;
					break;
				case BeverageConstant.CAFELATTE:
					totalTime -= BeverageConstant.BASIC_TIME_MAKE_CAFELATTE
							* removedData.count;
					break;
				}
			}
		}
		return removedData;
	}

	public int getTotalTime() {
		return totalTime;
	}

	@Override
	public String toString() {

		StringBuffer sb = new StringBuffer();
		sb.append("================================ BARISTA INFO ================================");
		sb.append("\nID : " + this.id);
		sb.append("  NAME : " + this.name);
		sb.append("  AGE : " + this.age);
		sb.append("  SALARY : " + this.salary);
		sb.append("\nJOIN_DATE : " + this.join_date);
		sb.append("  RETIRE_DATE : " + this.retire_date);

		return sb.toString();
	}

	public void stopWorking() {
		orderCheckThread.interrupt();
		orderCheckThread = null;
	}


	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public Date getJoin_date() {
		return join_date;
	}

	public Date getRetire_date() {
		return retire_date;
	}

	public int getSalary() {
		return salary;
	}
}
