package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import main.Sale;

public class SaleTest {

    private Sale sale;

    @Before
    public void setUp() {
        sale = new Sale();
    }

    @Test
    public void testCancelPurchase() {
        sale.cancelOrder();
        assertEquals(0, sale.getSaleQuantity());
    }
}
