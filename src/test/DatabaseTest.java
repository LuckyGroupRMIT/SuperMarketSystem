import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Product;
import main.Database;
import main.PricingMethod;

import java.util.ArrayList;

public class DatabaseTest
{
    private Product item;
    private ArrayList<Product> itemList;

    @Before
    public void setUp() throws Exception {
        item = new Product("item", 560, PricingMethod.QUANTITY, "item co", 500);
        itemList = new ArrayList<>();
        itemList.add(item);
    }

    @Test
    public void getByIDTest() {
        boolean tempBool = Database.addObject(item.getProductID(), item);
        assertTrue(tempBool);
        Object tempObj = Database.getByID("item", item.getClass());
        assertNotNull(tempObj);
    }

    @Test
    public void removeObjectTest() {
        boolean tempBool = Database.addObject(item.getProductID(), item);
        assertTrue(tempBool);
        tempBool = Database.removeObject(item.getProductID(), item.getClass());
        assertTrue(tempBool);
    }

    @Test
    public void listAllObjectsTest() {
        boolean tempBool = Database.addObject(item.getProductID(), item);
        assertTrue(tempBool);
        ArrayList<Product> tempList = Database.listAllProducts();
        assertTrue(itemList.containsAll(tempList));
    }
}
