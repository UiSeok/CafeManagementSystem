package tmon.data;

import java.util.Scanner;

public class MyScanner {
	private static Scanner scanner = null;
	
	public static Scanner openScanner(){
		scanner = new Scanner(System.in);
		return scanner;
	}
	
	public static void closeScanner(){
		scanner.close();
	}
	
	
}
