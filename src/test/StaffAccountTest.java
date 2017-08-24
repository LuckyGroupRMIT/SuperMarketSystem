package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import main.StaffAccountSystem;

public class StaffAccountSystemTest
{
    private StaffAccount acccount;

    @Before
    public void setup()
    {
        account = new StaffAccount("Firstname LastName");
    }


    @Test
    public void testSettingPermissions()
    {
        Permission accountPermission = Permission.STAFF;

        account.setPermission(Permission.STAFF);
        assertEquals(account.getPermission(), accountPermission);
    }

    @Test
    public void testSettingName()
    {

    }
}