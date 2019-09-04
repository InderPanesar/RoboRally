package robots.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import robots.Locations.ConveryorBelt;

/**
 * The Class ConveryorBeltTest.
 */
class ConveryorBeltTest {

	/**
	 * Does nothing.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test Belt X value.
	 */
	@Test
	void testBeltX() {
		ConveryorBelt cb = new ConveryorBelt(1, 0, '^', true);
		if(cb.getX() != 1) {
			fail("Incorrect X Value");
		}
	}
	
	/**
	 * Test Belt Y value.
	 */
	@Test
	void testBeltY() {
		ConveryorBelt cb = new ConveryorBelt(1, 0, '^', true);
		if(cb.getY() != 0) {
			fail("Incorrect Y Value");
		}
	}
	
	/**
	 * Test Belt character value.
	 */
	@Test
	void testBeltValue() {
		ConveryorBelt cb = new ConveryorBelt(1, 0, '^', true);
		if(cb.getValue() != '^') {
			fail("Incorrect Character Value");
		}
	}

}
