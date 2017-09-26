package main;

public class StaffAccount extends UserAccount
{
    private String permissions;

    public StaffAccount(String username, String password, String permissions)
    {
        super(username, password);
        this.permissions = permissions;
    }

    public String getPermissions() {
        return permissions;
    }
}