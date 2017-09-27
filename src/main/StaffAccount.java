package main;

import java.io.Serializable;

public class StaffAccount implements Serializable
{
    private String staffID;
    private String password;
    private Permission permissions;

    public StaffAccount(String staffID, String password, Permission permissions)
    {
        this.staffID = staffID;
        this.password = password;
        this.permissions = permissions;
    }

    public Permission getPermissions() {
        return permissions;
    }

    public String getStaffID() {
        return staffID;
    }

    public String getPassword() {
        return password;
    }
}