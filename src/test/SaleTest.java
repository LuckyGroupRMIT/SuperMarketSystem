package test;

import static org.junit.Assert.*;

import main.ProductType;
import org.junit.Before;
import org.junit.Test;

import main.Sale;

public class SaleTest {

    private Sale sale;
    private ProductType product;

    @Before
    public void setUp() {
        sale = new Sale();
        product = new ProductType();
    }

    @Test
    public void testAddPurchase() {
        sale.addPurchase(product, 5);
        assertEquals(5, sale.getSaleQuantity());
    }

    @Test
    public void testCancelPurchase() {
        sale.addPurchase(product, 5);
        sale.cancelOrder();
        assertEquals(0, sale.getSaleQuantity());
    }
}
