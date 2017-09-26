package main;

public class StaffAccount extends UserAccount
{
    private int permissions;

    public StaffAccount(String username, String password, int permissions)
    {
        super(username, password);
        this.permissions = permissions;
    }

    public int getPermissions() {
        return permissions;
    }
}