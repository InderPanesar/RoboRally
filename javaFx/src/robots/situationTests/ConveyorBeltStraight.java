package robots.situationTests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import robots.main.Game;

/**
 * The Class ConveyorBeltStraight.
 */
class ConveyorBeltStraight {

	/**
	 * In this class does nothing.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * Test the straight belts.
	 */
	@Test
	void test() {
		Game g = new Game(true, new File("tests/games/04-conveyor-straight/board.brd"), new File("tests/games/04-conveyor-straight/bob-wins.prg"), true, false);
		g.gameFunction(true, true);
		if(!g.getGameWon() || g.getWinnerRobot() != 'B') {
			fail("Not yet working as intended");
		}
	}

}
