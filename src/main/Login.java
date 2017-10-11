package main;

import java.util.ArrayList;
import java.util.Scanner;

public class Login
{
    public static Permission displayLogin()
    {
        String userId, password;
        Scanner reader = new Scanner(System.in);
        boolean exitLoop = false;

        System.out.println("\nStaff Login Screen\n");

        while (!exitLoop)
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

            if(checkStaffDetails(userId, password))
            {
                if(checkPermissions(userId) == Permission.EMPTY)
                    System.out.println("Error: permission denied");
                else
                    return checkPermissions(userId);
                exitLoop = true;
            }
            else
                System.out.println("Error: invalid username or password, please try again.");
        }

        return Permission.EMPTY;
    }

	public static boolean checkStaffDetails(String userID, String password)
    {
        ArrayList<StaffAccount> staffAccounts = Database.listAllStaff();

        for (StaffAccount staffAccount: staffAccounts)
            if (staffAccount.getID().equals(userID) && staffAccount.getPassword().equals(password))
                return true;

        return false;
	}

	public static boolean checkCustomerDetails(String userID)
    {
        ArrayList<CustomerAccount> customerAccounts = Database.listAllCustomers();

        for (CustomerAccount customerAccount: customerAccounts)
            if (customerAccount.getID().equals(userID))
                return true;

        return false;
    }
	
	public static Permission checkPermissions(String userID)
    {
        StaffAccount account = (StaffAccount) Database.getByID(userID, UserAccount.class);
        if(account != null)
            return account.getPermissions();
        else
        	return Permission.EMPTY;
	}
}
