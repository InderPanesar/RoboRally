package robots.situationTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import robots.main.Game;

/**
 * The Class PitTest.
 */
class PitTest {

	/**
	 * Does nothing within this class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Tests the pits behaviour.
	 */
	@Test
	void test() {
		Game g = new Game(true, new File("tests/games/02-pits/board.brd"), new File("tests/games/02-pits/bob-wins-using-pit_edge.prg"), true, false);
		g.gameFunction(true, true);
		if(!g.getGameWon() || g.getWinnerRobot() != 'B') {
			fail("Not yet working as intended");
		}
	}

}
