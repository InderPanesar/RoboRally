package robots.situationTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import robots.main.Game;

/**
 * The Class ConveryorBeltCorner.
 */
class ConveryorBeltCorner {

	/**
	 * In this class does nothing.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test the corner belts turn.
	 */
	@Test
	void test() {
		Game g = new Game(true, new File("tests/games/05-conveyor-turn/board.brd"), new File("tests/games/05-conveyor-turn/alice-wins.prg"), true, false);
		g.gameFunction(true, true);
		if(!g.getGameWon() || g.getWinnerRobot() != 'A') {
			fail("Not yet working as intended");
		}
	}

}
