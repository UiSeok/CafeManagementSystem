package tmon.manager;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import tmon.data.BaristaData;
import tmon.data.Beverage;
import tmon.data.MyScanner;
import tmon.data.OrderData;
import tmon.data.OrderResult;

import com.google.gson.Gson;

public class CafeManager {
	private static CafeManager instance;
	private Queue<Beverage> orderQueue;
	private List<BaristaData> barista_list;
	private List<Beverage> menu_list;
	private Scanner scan = MyScanner.openScanner();
	Thread orderCheckThread;
	Gson gson;

	public static CafeManager getInstnace() {
		if (instance == null) {
			instance = new CafeManager();
		}
		return instance;
	}

	public CafeManager() {
		orderQueue = new LinkedList<Beverage>();
		barista_list = new ArrayList<BaristaData>();
		menu_list = new ArrayList<Beverage>();
		gson = new Gson();
		
		// Make order Queue run another Thread every 1 second
		orderCheckThread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						orderCheckThread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if (!orderQueue.isEmpty()) {
						int index = getMinimumValueBaristaIndex();
						barista_list.get(index).addOrder(orderQueue.poll());
					}
				}
			}
		});
	}

	public int openCafe() {
		int result = 0;

		String input = "";
		String id = "", pwd = "";

		// Login Protocol
		while (!input.equals("-1")) {
			System.out.println("1. Manager Login\n2. Exit");
			input = scan.nextLine();
			if (input.equals("1")) {
				System.out.print("ID : ");
				id = scan.nextLine();
				System.out.print("Password : ");
				pwd = scan.nextLine();
				if (login(id, pwd)) {
					result = 1;
					break;
				} else {
					result = 0;
					continue;
				}

			} else if (input.equals("2")) {
				break;
			} else {
				continue;
			}
		}

		orderCheckThread.start();

		return result;
	}

	

	private int getMinimumValueBaristaIndex() {
		// Get Minimum Value Barista Index From Barista total time
		// This is most important LoadBalancing Algorithm.
		int minValue = barista_list.get(0).getTotalTime();
		int index = 0;
		if (barista_list.size() > 1) {
			for (int i = 0; i < barista_list.size(); i++) {
				int temp = barista_list.get(i).getTotalTime();
				if (minValue > temp) {
					index = i;
					minValue = temp;
				}
			}
		}
		return index;
	}

	private boolean login(String id, String pwd) {
		if (id.equals("admin") && pwd.equals("1234")) { // Check the ID and Pwd 
			barista_list.addAll(DBManager.getInstnace().getBaristas()); // Get Barista List From DB
			menu_list.addAll(DBManager.getInstnace().getMenu()); // Get Menu List From DB
			System.out
					.println("=====================================================================");
			System.out.print("   ================== ");
			System.out.print(new SimpleDateFormat("yyyy-MM-dd").format(Calendar
					.getInstance().getTime()));
			System.out.println(" Tmon Cafe Open ==================");
			System.out.print("   ==================       ");
			System.out.println(" WelCome!!          ==================");
			System.out
					.println("=====================================================================\n");
			return true;
		} else {
			System.out.println("Id or Password Wrong! Try Again!");
			return false;
		}
	}

	public void closeCafe() {
		MyScanner.closeScanner();
		DBManager.getInstnace().removeConnection();
		orderCheckThread.stop();
		orderQueue.clear();
		orderQueue = null;
		for(BaristaData data : barista_list){
			data.removeOrder();
		}
		barista_list.clear();
		barista_list = null;

		System.out
				.println("=====================================================================");
		System.out.print("   ================== ");
		System.out.print(new SimpleDateFormat("yyyy-MM-dd").format(Calendar
				.getInstance().getTime()));
		System.out.println(" Tmon Cafe Close  ==================");
		System.out.print("   ==================        ");
		System.out.println(" Thank You           ==================");
		System.out
				.println("=====================================================================\n");
	}

	public int getMenu() {
		int result = 0;
		System.out.println("================== Manager Mode"
				+ " ==================");
		System.out.println("1. Show Menu");
		System.out.println("2. Manage Barista");
		System.out.println("3. Manage LoadBalancing");
		System.out.println("4. Manage Menu");
		System.out.println("5. Order");
		System.out.println("6. Close Cafe");
		result = scan.nextInt();
		return result;
	}

	public void showMenu() {
		System.out
				.println("\n\n================== Tmon Cafe Menu ==================");
		for (Beverage data : menu_list) {
			System.out.println(" ==  " + data.bevName + " == ");
		}
		System.out
				.println("====================================================\n\n");
	}

	public void order(OrderData order) {
		int input;
		do {
			System.out.println("\n1. Auto Order");
			System.out.println("2. Self Order");
			System.out.println("3. Order Finish");
			input = scan.nextInt();
			switch (input) {
			case 1: // Auto Order Here
				BufferedReader br;
				try {
					br = new BufferedReader(new FileReader("res/input.txt"));
					StringBuilder sb = new StringBuilder();
					String line = br.readLine();

					while (line != null) {
						sb.append(line);
						sb.append(System.lineSeparator());
						line = br.readLine();
					}
					String everything = sb.toString();
					OrderResult orders = gson.fromJson(everything,
							OrderResult.class);
					orderQueue.addAll(orders.orderData.beverages);
					br.close();
					System.out.println("Auto Order OK!!");
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				// Self Order Here but not created yet
				break;
			case 3:
				return;
			}
		} while (input != 3);

	}

}
