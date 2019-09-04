package robots.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import robots.Character.Robots;

/**
 * The Class robotTest.
 */
class robotTest {

	/** The Robot. */
	Robots r;
	
	/**
	 * Sets the up the robot.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		r = new Robots(7, 7, 'A');
	}

	/**
	 * Test character.
	 */
	@Test
	void testCharacter() {
		if(r.getValue() != 'A') {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Test intial x and intial Y.
	 */
	@Test
	void testIntialXandIntialY() {
		if(r.getIntialX() != 7 || r.getIntialY() != 7) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Test set x and set Y.
	 */
	@Test
	void testsetXandsetY() {
		r.setX(1);
		r.setY(1);
		if(r.getX() != 1 || r.getY() != 1) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Test intial rotation.
	 */
	@Test
	void testIntialRotation() {
		if(r.getRotation() != 0) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Test set rotation.
	 */
	@Test
	void testSetRotation() {
		r.setRotation(200);
		if(r.getRotation() != 200) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Test intial flag retrieved.
	 */
	@Test
	void testIntialFlagRetrieved() {
		if(r.getFlagsRetrieved() != 0) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Test intial directional values.
	 */
	@Test
	void testIntialDirectionalValues() {
		if(r.getDX() != 0 && r.getDY() != -1) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Test set directional values.
	 */
	@Test
	void testSetDirectionalValues() {
		r.setDXDY(0, 0);
		if(r.getDX() != 0 && r.getDY() != 0) {
			fail("Not yet implemented");
		}
	}

}
