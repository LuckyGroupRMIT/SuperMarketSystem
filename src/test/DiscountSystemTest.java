package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DiscountSystemTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDiscountsAreApplied() {
		
	}
	public void testPrecedenceOfDiscounts() {
		// A 10% discount each 5 and 30% discount each 8 should not stack
		// Only the minimum should be taken
		fail("Not yet implemented");
	}

}
