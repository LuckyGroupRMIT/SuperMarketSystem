package main;

import java.io.IOException;
import java.util.Scanner;

public class SuperMarketTerminal {
	// This is to be the main class that will be run to start the program

    public static void main(String[] args) {
        displayInitialMenu();
        try {
            Database.saveAllMaps();
        }catch (IOException io){
            io.printStackTrace();
        }
    }

	private static void displayInitialMenu()
    {
		boolean exitProgram = false;
		Scanner reader= new Scanner(System.in);  // Reading from System.in
        int input;

		while (!exitProgram)
        {
			System.out.println("1. Purchase Mode");
            System.out.println("2. Staff Login");
            System.out.println("3. Quit");
			System.out.print("\n\nEnter a number to select option: ");

			input = reader.nextInt(); // Scans the next token of the input as an int.

			switch (input)
            {
                case 1:
                    displayPurchaseMenu();
                    break;
                case 2:
//                    displayLogin();
                    Manager.displayManagerMenu();
                    break;
                case 3:
                    exitProgram = true;
                    reader.close();
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Error: Invalid option.");
                    break;
			}
		}
	}

	private static void displayPurchaseMenu()
    {
        System.out.println("Purchasing");
    }

    private static void displayLogin()
    {
        StaffAccount accounts = null;
        String userId, password;
        Scanner reader = new Scanner(System.in);
        boolean exitloop = false;

        System.out.println("\nStaff Login Screen\n");

        while (!exitloop)
        {
            System.out.print("Please enter userID, or enter empty line to cancel: ");
            userId = reader.nextLine();
            if(userId.isEmpty())
                break;
            if(Database.getByID(userId, StaffAccount.class) == null)
            {
                System.out.println("Error: account with that id not found");
                continue;
            }

            System.out.print("Please enter password, or enter empty line to cancel: ");
            password = reader.nextLine();
            if(password.isEmpty())
                break;

            if(Login.checkDetails(userId, password))
            {
                switch (Login.checkPermissions(userId))
                {
                    case SALES:
                        displayEmployeeMenu();
                        break;
                    case WAREHOUSE:
                        displayWarehouseMenu();
                        break;
                    case MANAGER:
                        Manager.displayManagerMenu();
                        break;
                    default:
                        System.out.println("Error: Permission Denied.");
                        break;
                }

                exitloop = true;
            }

            else
                System.out.println("Error: invalid username or password, please try again.");
        }
    }

    private static void displayWarehouseMenu()
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
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Error: Invalid option.");
                    break;
            }
        }

        reader.close();
    }

    private static void displayEmployeeMenu()
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
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Error: Invalid option.");
                    break;
            }
        }

        reader.close();
    }

}
