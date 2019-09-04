package robots.test;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import robots.main.ReadCommands;

/**
 * The Class commandTest.
 */
class commandTest {
	
	/** The accepted user inputs. */
	private static char[] acceptedUserInputs = {'F', 'B', 'L', 'R', 'W', 'U', 'S'};
	
	/** The input handler. */
	private ReadCommands inputHandler;

    
	/**
	 * Sets up the inputHandler Class.
	 *
	 * @throws Exception the exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		inputHandler = null;
	}
	

	/**
	 * Test 0 players.
	 */
	@Test
	void test0Players() { 
		inputHandler = new ReadCommands(new File("tests\\ReadCommands\\0players.prg"), acceptedUserInputs, true);
		if(!inputHandler.getTestingErrorHasOccurred()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Test 1 players not enough.
	 */
	@Test
	void test1PlayersNotEnough() { 
		inputHandler = new ReadCommands(new File("tests\\ReadCommands\\1player-not_enough.prg"), acceptedUserInputs, true);
		if(!inputHandler.getTestingErrorHasOccurred()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Test 1 players OK.
	 */
	@Test
	void test1PlayersOK() { 
		inputHandler = new ReadCommands(new File("tests\\ReadCommands\\1player-ok.prg"), acceptedUserInputs, true);
		if(inputHandler.getTestingErrorHasOccurred()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Test 1 players repeated.
	 */
	@Test
	void test1PlayersRepeated() { 
		inputHandler = new ReadCommands(new File("tests\\ReadCommands\\1player-repeated.prg"), acceptedUserInputs, true);
		if(!inputHandler.getTestingErrorHasOccurred()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Test 1 players too many.
	 */
	@Test
	void test1PlayersTooMany() { 
		inputHandler = new ReadCommands(new File("tests\\ReadCommands\\1player-too_many.prg"), acceptedUserInputs, true);
		System.out.println(inputHandler.NameA());
		if(!inputHandler.getTestingErrorHasOccurred()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Test 2 players OK.
	 */
	@Test
	void test2PlayersOK() { 
		inputHandler = new ReadCommands(new File("tests\\ReadCommands\\2players.prg"), acceptedUserInputs, true);
		if(inputHandler.getTestingErrorHasOccurred()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Test 2 players 2 rounds OK.
	 */
	@Test
	void test2Players2RoundsOK() { 
		inputHandler = new ReadCommands(new File("tests\\ReadCommands\\2players-2rounds.prg"), acceptedUserInputs, true);
		if(inputHandler.getTestingErrorHasOccurred()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Test 2 players missing program.
	 */
	@Test
	void test2PlayersMissingProgram() { 
		inputHandler = new ReadCommands(new File("tests\\ReadCommands\\2players-missing-program.prg"), acceptedUserInputs, true);
		if(!inputHandler.getTestingErrorHasOccurred()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Test 2 players too many program.
	 */
	@Test
	void test2PlayersTooManyProgram() { 
		inputHandler = new ReadCommands(new File("tests\\ReadCommands\\2players-too-many-programs.prg"), acceptedUserInputs, true);
		if(!inputHandler.getTestingErrorHasOccurred()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Test 4 players.
	 */
	@Test
	void test4Players() { 
		inputHandler = new ReadCommands(new File("tests\\ReadCommands\\4players.prg"), acceptedUserInputs, true);
		if(inputHandler.getTestingErrorHasOccurred()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Test Wrong format.
	 */
	@Test
	void wrongFormat() { 
		inputHandler = new ReadCommands(new File("tests\\ReadCommands\\wrong-format.prg"), acceptedUserInputs, true);
		if(!inputHandler.getTestingErrorHasOccurred()) {
			fail("Not yet implemented");
		}
	}
	
	/**
	 * Test Empty file.
	 */
	@Test
	void emptyFile() { 
		inputHandler = new ReadCommands(new File("tests\\ReadCommands\\empty-file.prg"), acceptedUserInputs, true);
		if(!inputHandler.getTestingErrorHasOccurred()) {
			fail("Not yet implemented");
		}
	}

}
