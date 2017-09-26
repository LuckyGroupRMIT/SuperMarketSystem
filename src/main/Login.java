package main;

public class Login
{
	public static boolean checkDetails(String userID, String password)
    {
        StaffAccount account = (StaffAccount) Database.getByID(userID, StaffAccount.class);
        if(account != null)
        {
            if (account.getPassword().equals(password))
                return true;
        }
        return false;
	}
	
	public static int checkPermissions(String userID)
    {
        StaffAccount account = (StaffAccount) Database.getByID(userID, StaffAccount.class);
        if(account != null)
            return account.getPermissions();
        else
        	return 0;
	}
}
