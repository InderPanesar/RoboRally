package robots.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import robots.Board.MapTile;
import robots.Locations.BoardLaser;

/**
 * The Class BoardLaserTest.
 */
class BoardLaserTest {

	/** The class MapTile. */
	MapTile emitterSuper;
	
	/**
	 * Sets the up.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test if is receiver.
	 */
	@Test
	void testifReciever() {
		System.out.println("HEY");
		emitterSuper = new BoardLaser(0, 0, '(',true, true, false, false);
		BoardLaser b = (BoardLaser) emitterSuper;
		if(!b.getIsReciever()) {
			fail("Not yet working as intended.");
		}
	}
	
	/**
	 * Test if is emitter.
	 */
	@Test
	void testifEmitter() {
		System.out.println("HEY");
		emitterSuper = new BoardLaser(0, 0, ')',true, false, true, false);
		BoardLaser b = (BoardLaser) emitterSuper;
		if(!b.getIsEmitter()) {
			fail("Not yet working as intended");
		}
	}
	
	/**
	 * Test if vertical is receiver.
	 */
	@Test
	void testifVerticalReciever() {
		System.out.println("HEY");
		emitterSuper = new BoardLaser(0, 0, ')',true, false, true, true);
		BoardLaser b = (BoardLaser) emitterSuper;
		if(!b.getIsEmitter() || !b.getIsVertical()) {
			fail("Not yet working as intended");
		}
	}
	
	/**
	 * Testif vertical emitter.
	 */
	@Test
	void testifVerticalEmitter() {
		System.out.println("HEY");
		emitterSuper = new BoardLaser(0, 0, ')',true, true, false, true);
		BoardLaser b = (BoardLaser) emitterSuper;
		if(!b.getIsReciever() || !b.getIsVertical()) {
			fail("Not yet working as intended");
		}
	}

}
