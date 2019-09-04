package robots.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import robots.main.ReadFile;

/**
 * The Class BoardTest.
 */
class BoardTest {

	/** The reading board class. */
	ReadFile board;
	
	/** The accepted board values. */
	private static char[] acceptedBoardValues = 
		{
			'+', '-', 
			'1', '2', '3', '4', 
			'.', 
			'<', '>', 
			'(', ')', '[', ']', 
			'^', 'v','>','<', 
			'N', 'E', 'W', 'S', 
			'n', 'e', 'w', 's', 
			'x', 
			'A', 'B', 'C', 'D',
		};
	
	/**
	 * Sets up the board file.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		board = null;
	}

	/**
	 * Conveyer loop board check.
	 */
	@Test
	void conveyorLoopBoard() {
		board = new ReadFile(null, new File("tests\\ReadBoard\\conveyor-loops.brd"), acceptedBoardValues, true);
		if(board.getTestingErrorExist()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Zero by zero board check.
	 */
	@Test
	void zeroByZeroBoard() {
		board = new ReadFile(null, new File("tests\\ReadBoard\\empty-0x0.brd"), acceptedBoardValues, true);
		if(!board.getTestingErrorExist()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * One by one board check.
	 */
	@Test
	void oneByoneBoard() {
		board = new ReadFile(null, new File("tests\\ReadBoard\\empty-1x1.brd"), acceptedBoardValues, true);
		if(board.getTestingErrorExist()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * One by two board check.
	 */
	@Test
	void oneBytwoBoard() {
		board = new ReadFile(null, new File("tests\\ReadBoard\\empty-1x2.brd"), acceptedBoardValues, true);
		if(board.getTestingErrorExist()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Two by one board check.
	 */
	@Test
	void twoByOneBoard() {
		board = new ReadFile(null, new File("tests\\ReadBoard\\empty-2x1.brd"), acceptedBoardValues, true);
		if(board.getTestingErrorExist()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Empty board check.
	 */
	@Test
	void emptyBoard() {
		board = new ReadFile(null, new File("tests\\ReadBoard\\empty-file.brd"), acceptedBoardValues, true);
		if(!board.getTestingErrorExist()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Invalid character board check.
	 */
	@Test
	void invalidCharacterBoard() {
		board = new ReadFile(null, new File("tests\\ReadBoard\\invalid-character.brd"), acceptedBoardValues, true);
		if(!board.getTestingErrorExist()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Mismatched rows cols check.
	 */
	@Test
	void mismatchedRowsCols() {
		board = new ReadFile(null, new File("tests\\ReadBoard\\mismatched-rows-cols.brd"), acceptedBoardValues, true);
		if(!board.getTestingErrorExist()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Only flags check.
	 */
	@Test
	void onlyFlags() {
		board = new ReadFile(null, new File("tests\\ReadBoard\\only-flags.brd"), acceptedBoardValues, true);
		if(board.getTestingErrorExist()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Only gears check.
	 */
	@Test
	void onlyGears() {
		board = new ReadFile(null, new File("tests\\ReadBoard\\only-gears.brd"), acceptedBoardValues, true);
		if(board.getTestingErrorExist()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Only pits check.
	 */
	@Test
	void onlyPits() {
		board = new ReadFile(null, new File("tests\\ReadBoard\\only-pits.brd"), acceptedBoardValues, true);
		if(board.getTestingErrorExist()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Only starting robots check.
	 */
	@Test
	void onlyStartingRobots() {
		board = new ReadFile(null, new File("tests\\ReadBoard\\only-starting.brd"), acceptedBoardValues, true);
		if(board.getTestingErrorExist()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Wrong format check.
	 */
	@Test
	void wrongFormat() {
		board = new ReadFile(null, new File("tests\\ReadBoard\\wrong-format.brd"), acceptedBoardValues, true);
		if(!board.getTestingErrorExist()) {
			fail("Not yet implemented");
		}
	}
	

}
