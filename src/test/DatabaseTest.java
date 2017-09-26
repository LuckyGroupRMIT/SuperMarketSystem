import static org.junit.Assert.*;

import main.ProductType;
import org.junit.Before;
import org.junit.Test;

import main.Database;

import java.util.ArrayList;

public class DatabaseTest
{
    private ProductType item;
    private ArrayList<ProductType> itemList;

    @Before
    public void setUp() throws Exception {
        item = new ProductType("P0001", "item", "item co");
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
        ArrayList<ProductType> tempList = Database.listAllProducts();
        assertTrue(itemList.containsAll(tempList));
    }
}
