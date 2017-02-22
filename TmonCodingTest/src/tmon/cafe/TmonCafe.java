package tmon.cafe;

import tmon.util.MyScanner;

import java.util.Scanner;

/**
 * Created by uskim3898 on 2017. 2. 22..
 */
public class TmonCafe {

    private final int FIRST_MENU_SHOWMENU = 1;
    private final int FIRST_MENU_AUTOORDER = 2;

    CafeManager manager ;
    Scanner scanner;

    public TmonCafe(){
        scanner = MyScanner.openScanner();
        manager = new CafeManager();
    }


    public void open() {
        this.manager.openCafe();
        int input = 0;
        while(input != -1){
            showFirstMenu();
            input = scanner.nextInt();
            if( input == FIRST_MENU_SHOWMENU){
                Menu.getInstnace().showMenu();
            }
            if( input == FIRST_MENU_AUTOORDER ){
                this.manager.autoOrder();
            }
        }
    }

    public void close(){
        this.manager.closeCafe();
        MyScanner.closeScanner();
    }

    private void showFirstMenu() {
        System.out.println("1. Show Menu");
        System.out.println("2. Auto Order");
        System.out.println("insert -1 to exit program");
    }
}
