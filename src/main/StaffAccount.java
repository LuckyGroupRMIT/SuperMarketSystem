package main;

public class StaffAccount
{
    private String staffID;
    private String password;
    private int permissions;

    public StaffAccount(String staffID, String password, int permissions)
    {
        this.staffID = staffID;
        this.password = password;
        this.permissions = permissions;
    }

    public int getPermissions() {
        return permissions;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getPassword() {
        return password;
    }
}