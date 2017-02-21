package tmon.util;

import java.util.Scanner;

public class MyScanner {
	private static Scanner scanner = null;
	
	public static Scanner openScanner(){
		if( scanner == null )
			scanner = new Scanner(System.in);
		return scanner;
	}
	
	public static void closeScanner(){
		if( scanner != null)
			scanner.close();
	}
	
	
}
