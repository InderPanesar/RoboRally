package robots.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import robots.Locations.Gear;

/**
 * The Class GearsTest.
 */
class GearsTest {

	/**
	 * Does Nothing.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test gear X.
	 */
	@Test
	void testGearX() {
		Gear g = new Gear(1, 0, '+', true);
		if(g.getX() != 1) {
			fail("Incorrect X Value");
		}
	}
	
	/**
	 * Test gear Y.
	 */
	@Test
	void testGearY() {
		Gear g = new Gear(1, 0, '+', true);
		if(g.getY() != 0) {
			fail("Incorrect Y Value");
		}
	}
	
	/**
	 * Test gear value.
	 */
	@Test
	void testGearValue() {
		Gear g = new Gear(1, 0, '+', true);
		if(g.getValue() != '+') {
			fail("Incorrect Value Value");
		}
	}

}
