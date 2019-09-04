package robots.situationTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import robots.main.Game;

/**
 * The Test Class GearsAlice.
 */
class GearsAlice {

	/**
	 * Does nothing within this class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Tests the gears.
	 */
	@Test
	void test() {
		Game g = new Game(true, new File("tests/games/03-gears/board.brd"), new File("tests/games/03-gears/alice-wins.prg"), true, false);
		g.gameFunction(true, true);
		if(!g.getGameWon() || g.getWinnerRobot() != 'A') {
			fail("Not yet working as intended");
		}
	}

}
