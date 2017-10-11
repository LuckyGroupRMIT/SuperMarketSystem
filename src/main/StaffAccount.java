package main;

import java.io.Serializable;

public class StaffAccount extends UserAccount implements Serializable
{
    private static final long serialVersionUID = 1113799434508676095L;

    private String password;
    private Permission permissions;

    public StaffAccount(String staffID, String password, Permission permissions)
    {
        super(staffID);
        this.password = password;
        this.permissions = permissions;
    }

    public Permission getPermissions() {
        return permissions;
    }

    public String getPassword() {
        return password;
    }
}