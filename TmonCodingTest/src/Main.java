import tmon.data.Menu;
import tmon.data.constant.MenuConstant;
import tmon.manager.CafeManager;
import tmon.util.MyScanner;

import java.util.Scanner;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = MyScanner.openScanner();

        CafeManager manager = new CafeManager();

        manager.openCafe();

        cafeManage(manager, scanner);

        manager.closeCafe();
        MyScanner.closeScanner();
        exit(0);
    }

    private static void cafeManage(CafeManager manager, Scanner scanner) {
        int input = 0;
        while(input != -1){
            showFirstMenu();
            input = scanner.nextInt();
            if( input == MenuConstant.FIRST_MENU_SHOWMENU){
                Menu.getInstnace().showMenu();
            }
            if( input == MenuConstant.FIRST_MENU_AUTOORDER ){
                manager.autoOrder();
            }
        }
    }

    private static void showFirstMenu() {
        System.out.println("1. Show Menu");
        System.out.println("2. Auto Order");
        System.out.println("insert -1 to exit program");
    }

}
