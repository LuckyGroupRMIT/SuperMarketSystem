import static org.junit.Assert.*;

import org.junit.Test;

import main.StaffAccount;
import main.Database;

public class DatabaseTest
{
    @Test
    public void getByIDTest()
    {
        Object temp = Database.getByID("user", StaffAccount.class);
        assertNotNull(temp);
    }
}
