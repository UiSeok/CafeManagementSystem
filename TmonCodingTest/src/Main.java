

import tmon.cafe.TmonCafe;

import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) {
        TmonCafe tmonCafe = new TmonCafe();
        tmonCafe.open();
        tmonCafe.close();
        exit(0);
    }
}
