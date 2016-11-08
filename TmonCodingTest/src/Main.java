import tmon.manager.CafeManager;

public class Main {
	public static void main(String[] args) {
		
		if(CafeManager.getInstnace().openCafe() == 1){
			// Manager open Cafe
			int input = 0;
			do{
				input = CafeManager.getInstnace().getMenu();
				switch (input) {
				case 1:
					// Show Menu
					CafeManager.getInstnace().showMenu();
					break;
				case 2:
					// Manage Barista
					
					break;
				case 3:
					// Manage LoadBalancing
					
					break;
				case 4:
					// Manage Menu
					break;
				case 5:
					CafeManager.getInstnace().order(null);
					break;
				case 6:
					// Close Cafe
					CafeManager.getInstnace().closeCafe();
					return;
				}
			}
			while( input != 6);
		}
		
	}

}
