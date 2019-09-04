package robots.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import robots.Locations.Flag;

/**
 * The Class FlagTest.
 */
class FlagTest {

	/**
	 * Does nothing.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}
	
	/**
	 * Test Flag X value.
	 */
	@Test
	void testFlagX() {
		Flag f = new Flag(1, 3, '1', true);
		if(f.getX() != 1) {
			fail("Incorrect Y Value");
		}
	}

	/**
	 * Test Flag Y value.
	 */
	@Test
	void testFlagY() {
		Flag f = new Flag(1, 3, '1', true);
		if(f.getY() != 3) {
			fail("Incorrect Y Value");
		}
	}
	
	/**
	 * Test Flag character value.
	 */
	@Test
	void testFlagValue() {
		Flag f = new Flag(1, 3, '1', true);
		if(f.getValue() != '1') {
			fail("Incorrect Y Value");
		}
	}

}
