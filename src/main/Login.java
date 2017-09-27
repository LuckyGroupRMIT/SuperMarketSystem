package main;

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

            if(checkDetails(userId, password))
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

	private static boolean checkDetails(String userID, String password)
    {
        StaffAccount account = (StaffAccount) Database.getByID(userID, StaffAccount.class);
        if(account != null)
        {
            if (account.getPassword().equals(password))
                return true;
        }
        return false;
	}
	
	private static Permission checkPermissions(String userID)
    {
        StaffAccount account = (StaffAccount) Database.getByID(userID, StaffAccount.class);
        if(account != null)
            return account.getPermissions();
        else
        	return Permission.EMPTY;
	}
}
