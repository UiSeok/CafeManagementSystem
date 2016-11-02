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
			System.out.println("�����ϰ��� �ϴ� �޴� ��ȣ�� �Է��ϼ��� : ");
			System.out.println("1. �ֹ�");
			System.out.println("2. �ٸ���Ÿ �߰�");
			System.out.println("3. �޴� �߰�");
			System.out.println("4. �ý��� ����");
			input = scan.nextInt();
			switch (input) {
			case 1:
				// �ֹ�
				System.out.println("�޴��� �����ϼ���");
				CafeManager.getInstnace().showMenu();
				int order = scan.nextInt();
				System.out.println("�� ���� �����ϼ���");
				int count = scan.nextInt();
				long orderTime = new Date().getTime();
				switch (order) {

				case 1: // ����������
					CafeManager.getInstnace().order(
							new OrderData(orderTime, orderTime, new Beverage(
									BeverageConstant.ESPRESSO, count)));
					break;
				case 2: // �Ƹ޸�ī��
					CafeManager.getInstnace().order(
							new OrderData(orderTime, orderTime, new Beverage(
									BeverageConstant.AMERICANO, count)));
					break;
				case 3: // ���� �ֽ�
					CafeManager.getInstnace().order(
							new OrderData(orderTime, orderTime, new Beverage(
									BeverageConstant.FRUITJUICE, count)));
					break;
				case 4: // ī�� ��
					CafeManager.getInstnace().order(
							new OrderData(orderTime, orderTime, new Beverage(
									BeverageConstant.CAFELATTE, count)));
					break;
				}
				break;
			case 2:
				// �ٸ���Ÿ �߰�
				break;
			case 3:
				// �޴� �߰�
				break;
			case 4:
				// �ý��� ����
				input = -1;
				break;
			}
		}

		scan.close();

	}
}
