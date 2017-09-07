package main;

public class Login {
	public int CheckDetails(int username, String password){
		int tempUserID = username;
		String tempUserPassword = password;
		int pass = 0;
		for (Accounts s: accounts){
			if (s.getUserID() = tempUserID){
				if (s.getUserPassword() = tempUserPassword){
					pass++;
				}
			}
			else {
				return 0;
			}
		}
		return pass;
	}
	
	public int checkPermissions(int userID){
		int tempUserId = userID;
		for (Accounts s: accounts){
			if(s.getUserID() = tempUserID){
				return s.getPermission();
			}
		}
	}
}
