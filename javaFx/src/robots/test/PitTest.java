package robots.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import robots.Locations.Pit;

/**
 * The Class PitTest.
 */
class PitTest {

	/**
	 * Does nothing.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test pit X.
	 */
	@Test
	void testPitX() {
		Pit p = new Pit(1, 2, 'x', true);
		if(p.getX() != 1) {
			fail("Incorrect X Value");
		}
	}
	
	/**
	 * Test pit Y.
	 */
	@Test
	void testPitY() {
		Pit p = new Pit(1, 2, 'x', true);
		if(p.getY() != 2) {
			fail("Incorrect Y Value");
		}
	}
	
	/**
	 * Test pit character.
	 */
	@Test
	void testPitCharacter() {
		Pit p = new Pit(1, 2, 'x', true);
		if(p.getValue() != 'x') {
			fail("Incorrect Character Value");
		}
	}

}
