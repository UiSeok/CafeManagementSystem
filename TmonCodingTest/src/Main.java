import java.util.Date;
import java.util.Scanner;

import tmon.data.Beverage;
import tmon.data.OrderData;
import tmon.data.constant.BeverageConstant;
import tmon.manager.CafeManager;

public class Main {
	public static void main(String[] args) {

		CafeManager.getInstnace().openCafe();

		Scanner scan = new Scanner(System.in);
		int input = 1;
		while (input != -1) {
			System.out.println("실행하고자 하는 메뉴 번호를 입력하세요 : ");
			System.out.println("1. 주문");
			System.out.println("2. 바리스타 추가");
			System.out.println("3. 메뉴 추가");
			System.out.println("4. 시스템 종료");
			input = scan.nextInt();
			switch (input) {
			case 1:
				// 주문
				System.out.println("메뉴를 선택하세요");
				CafeManager.getInstnace().showMenu();
				int order = scan.nextInt();
				System.out.println("잔 수를 선택하세요");
				int count = scan.nextInt();
				long orderTime = new Date().getTime();
				switch (order) {

				case 1: // 에스프레소
					CafeManager.getInstnace().order(
							new OrderData(orderTime, orderTime, new Beverage(
									BeverageConstant.ESPRESSO, count)));
					break;
				case 2: // 아메리카노
					CafeManager.getInstnace().order(
							new OrderData(orderTime, orderTime, new Beverage(
									BeverageConstant.AMERICANO, count)));
					break;
				case 3: // 과일 주스
					CafeManager.getInstnace().order(
							new OrderData(orderTime, orderTime, new Beverage(
									BeverageConstant.FRUITJUICE, count)));
					break;
				case 4: // 카페 라떼
					CafeManager.getInstnace().order(
							new OrderData(orderTime, orderTime, new Beverage(
									BeverageConstant.CAFELATTE, count)));
					break;
				}
				break;
			case 2:
				// 바리스타 추가
				break;
			case 3:
				// 메뉴 추가
				break;
			case 4:
				// 시스템 종료
				input = -1;
				break;
			}
		}

		scan.close();

	}
}
