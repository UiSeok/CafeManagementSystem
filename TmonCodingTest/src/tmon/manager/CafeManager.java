package tmon.manager;

import com.google.gson.Gson;
import com.sun.javafx.tools.packager.Log;
import tmon.data.BaristaData;
import tmon.data.entity.Beverage;
import tmon.data.entity.OrderData;
import tmon.data.entity.OrderResult;
import tmon.util.MyScanner;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CafeManager {

    private Queue<Beverage> orderQueue;
    private List<BaristaData> barista_list;
    private List<Beverage> bev_list;
    private Scanner scan;
    Thread orderCheckThread;
    Gson gson;

    public CafeManager() {
        orderQueue = new LinkedList<Beverage>();
        barista_list = new ArrayList<BaristaData>();
        bev_list = new ArrayList<Beverage>();
        gson = new Gson();
        scan = MyScanner.openScanner();

        init();

        // Make order Queue run another Thread every 1 second
        orderCheckThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    try {
                        orderCheckThread.sleep(1000);
                    } catch (InterruptedException e) {
                        Log.debug(e.getMessage());
                    }
                    if (!orderQueue.isEmpty()) {
                        int index = getMinimumValueBaristaIndex();
                        barista_list.get(index).addOrder(orderQueue.poll());
                    }
                }
            }
        });
    }

    private void init() {
        barista_list.addAll(DBManager.getInstnace().getBaristas());
        bev_list.addAll(DBManager.getInstnace().getMenu());
    }

    public void openCafe() {

        orderCheckThread.start();

    }

    private int getMinimumValueBaristaIndex() {
        // Get Minimum Value Barista Index From Barista total time
        // This is most important LoadBalancing Algorithm.
        if (barista_list.size() == 0)
            return 0;
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
//		DBManager.getInstnace().login(id, pwd);
        return true;

    }

    public void closeCafe() {
        MyScanner.closeScanner();
        DBManager.getInstnace().removeConnection();
        orderCheckThread.interrupt();
        orderQueue.clear();
        orderQueue = null;
        for (BaristaData data : barista_list) {
            data.removeOrder();
        }
        barista_list.clear();
        barista_list = null;
        scan.close();

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

    public void autoOrder() {
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader("/Users/uskim3898/Desktop/Tmon/Java/CafeManagementSystem/TmonCodingTest/res/input.txt"));
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
    }

    public void order(OrderData order) {
        orderQueue.addAll(order.beverages);
        System.out.println("Order Recieved");
    }

}
