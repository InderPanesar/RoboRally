package robots.situationTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import robots.main.Game;

/**
 * The Class DegTurnAlice.
 */
class DegTurnAlice {

	/**
	 * Does nothing within this class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Tests the turning outcome for robots.
	 */
	@Test
	void test() {
		Game g = new Game(true, new File("tests/games/01-90degturn/board.brd"), new File("tests/games/01-90degturn/alice-wins.prg"), true, false);
		g.gameFunction(true, true);
		if(!g.getGameWon() || g.getWinnerRobot() != 'A') {
			fail("Not yet working as intended");
		}
	}

}
