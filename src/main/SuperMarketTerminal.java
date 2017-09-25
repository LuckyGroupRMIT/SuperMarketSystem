package main;

import java.util.Scanner;

public class SuperMarketTerminal {
	// This is to be the main class that will be run to start the program

	private void displayInitialMenu() {
		boolean exitProgram = false;
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		while (!exitProgram) {
			System.out.println("1. Purchase Mode");
			System.out.println("2. Warehouse Mode");
			System.out.println("3. Manager Mode");
			System.out.println("4. Quit");
			System.out.println("\nEnter a number to select option: ");
			int n = reader.nextInt(); // Scans the next token of the input as an int.
			switch (n) {
			case 1: 
				PurchaseMode p = new PurchaseMode(reader,null);
			case 4: 
				exitProgram = true;
				System.out.println("Exited Program");
			break;
			default:
			break;
			}
			System.out.println(n);
		}
		reader.close();
	}

	public static void main(String[] args) {
		SuperMarketTerminal x = new SuperMarketTerminal();
		x.displayInitialMenu();

	}

}
