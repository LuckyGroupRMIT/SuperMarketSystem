import static org.junit.Assert.*;

import main.Permission;
import org.junit.Before;
import org.junit.Test;

import main.Database;
import main.StaffAccount;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseTest
{
    private StaffAccount staff;
    private ArrayList<StaffAccount> staffList;

    @Before
    public void setUp(){
        staff = new StaffAccount("id", "test", Permission.MANAGER);
        staffList = new ArrayList<>();
        staffList.add(staff);
        Database.clearHashMap(staff.getClass());
    }

    @Test
    public void getByIDTest() {
        boolean tempBool = Database.addObject(staff.getStaffID(), staff);
        assertTrue(tempBool);
        Object tempObj = Database.getByID(staff.getStaffID(), staff.getClass());
        assertNotNull(tempObj);
    }

    @Test
    public void removeObjectTest() {
        boolean tempBool = Database.addObject(staff.getStaffID(), staff);
        assertTrue(tempBool);
        tempBool = Database.removeObject(staff.getStaffID(), staff.getClass());
        assertTrue(tempBool);
    }

    @Test
    public void listAllObjectsTest() {
        boolean tempBool = Database.addObject(staff.getStaffID(), staff);
        assertTrue(tempBool);
        ArrayList<StaffAccount> tempList = Database.listAllAccounts();
        assertTrue(staffList.containsAll(tempList));
    }

    @Test
    public void saveAllMapsTest() {
        try {
            boolean tempBool = Database.addObject(staff.getStaffID(), staff);
            assertTrue(tempBool);
            Database.saveAllMaps();
        }catch (IOException io){
            fail();
        }
    }

    @Test
    public void initAllMapsTest()
    {
        try {
            boolean tempBool = Database.addObject(staff.getStaffID(), staff);
            assertTrue(tempBool);

            HashMap<String, StaffAccount> tempHash = Database.getAccountHash();
            Database.saveAllMaps();
            Database.clearHashMap(staff.getClass());
            if(!Database.initAllMaps()) {
                System.out.println("Failed");
                fail();
            }

            StaffAccount temp = (StaffAccount) Database.getByID(staff.getStaffID(), staff.getClass());
            assertEquals(staff.getPassword(), temp.getPassword());
        }catch (IOException io){
            fail();
        }
    }

    @Test
    public void initAllMapsBlankTest()
    {
        try {
            if(!Database.initAllMaps()) {
                System.out.println("Failed");
                fail();
            }
        }catch (IOException io){
            fail();
        }
    }
}
