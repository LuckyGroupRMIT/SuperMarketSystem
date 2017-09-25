package main;

import java.util.Scanner;

public class SuperMarketTerminal {
	// This is to be the main class that will be run to start the program

    public static void main(String[] args) {
        displayInitialMenu();
    }

	private static void displayInitialMenu()
    {
		boolean exitProgram = false;
		Scanner reader = new Scanner(System.in);  // Reading from System.in

		while (!exitProgram)
        {
			System.out.println("1. Purchase Mode");
            System.out.println("2. Staff Login");
            System.out.println("3. Quit");
			System.out.print("\n\nEnter a number to select option: ");

			int input = reader.nextInt(); // Scans the next token of the input as an int.

			switch (input)
            {
                case 1:
                    displayPurchaseMenu();
                    break;
                case 2:
                    displayLogin();
                    break;
                case 3:
                    exitProgram = true;
                    System.out.println("Exited Program");
                    break;
                default:
                    System.out.println("Error: Invalid option.");
                    break;
			}
		}

		reader.close();
	}

	private static void displayPurchaseMenu()
    {
        System.out.println("Purchasing");
    }

    private static StaffAccount displayLogin()
    {
        StaffAccount accounts = null;
        String userId, password;
        Scanner reader = new Scanner(System.in);
        boolean exitloop = false;

        System.out.println("\tStaff Login Screen\n");

        while (!exitloop)
        {
            System.out.print("\nPlease enter userID: ");
            userId = reader.next();
            System.out.print("\nPlease enter password: ");
            password = reader.next();

            if(Login.checkDetails(userId, password))
                exitloop = true;
        }

        return accounts;
    }

    private static void displayManagerMenu()
    {

    }

    private static void displayWarehouseMenu()
    {

    }

    private static void displayEmployeeMenu()
    {

    }

}
