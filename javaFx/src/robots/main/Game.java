package robots.main;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import robots.Board.BoardLocationHandler;
import robots.Board.Program;
import robots.Character.Robots;

/**
 * The Class Game.
 */
public class Game {
	
	/** The board. */
	private BoardLocationHandler board;
	
	/** The controller. */
	private SampleController controller;
	
	/** The program. */
	private Program program;
	
	/** The executor. */
	private ScheduledExecutorService exec;
	
	/** The Current player. */
	private char CurrentPlayer;
	
	/** The Round number. */
	private int RoundNumber;
	
	/** The round number printed already. */
	private Boolean roundHitAlready;
	
	/** The number of flags. */
	private int numberOfFlags;
	
	/** The character number. */
	private int characterNumber;
	
	/** The a command queue. */
	private Queue<String> A = new LinkedList<String>();
	
	/** The b command queue. */
	private Queue<String> B = new LinkedList<String>();
	
	/** The c command queue. */
	private Queue<String> C = new LinkedList<String>();
	
	/** The d command queue. */
	private Queue<String> D = new LinkedList<String>();
	
	/** The accepted user inputs. */
	private static char[] acceptedUserInputs = {'F', 'B', 'L', 'R', 'W', 'U'};
	
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
	
	/** The letters taken. */
	public int lettersTaken = 0;
	
	/** The is test. */
	private Boolean isTest;
	
	/** The game won. */
	private Boolean gameWon;
	
	/** The winner robot. */
	private char winnerRobot;
	
	
  	/**
	   * Instantiates a new game.
	   *
	   * @param usingFiles checks if usingFiles
	   * @param selectedBoardFile the selected board file
	   * @param selectedCommandFile the selected command file
	   * @param isTest check if a test
	   * @param robotUseLasers if robot use lasers
	   */
	public Game(Boolean usingFiles, File selectedBoardFile, File selectedCommandFile, Boolean isTest, Boolean robotUseLasers) {
		//Used if it is a test
		this.setIsTest(isTest);
		//Check that the game is won.
		this.setGameWon(false);
		this.RoundNumber = 1;
		this.CurrentPlayer = 'A';
		this.roundHitAlready = false;
		//again if it using files it will use specific files
		if(!usingFiles) {
	    	board = new BoardLocationHandler("only-starting.brd", null, null, acceptedBoardValues, null, isTest, robotUseLasers);
		} 
		else {
			board = new BoardLocationHandler("", selectedBoardFile, selectedCommandFile, acceptedBoardValues, acceptedUserInputs, isTest, robotUseLasers);
		}
		//will initiating the controller for the user interface.
    	controller = new SampleController(board.getWidth(), board.getHeight(), board.getTiles());
    	program = new Program(acceptedUserInputs);
    	numberOfFlags = board.getFlagNumber();
    	characterNumber = board.getCharacterNumber();
    	
    	//A couple of fail state checks.
    	if(characterNumber < 1) {
    		alertHandler("Game Unplayable", "There are no characters on the board.", true);
    	}
    	else if(characterNumber < 2) {
    		alertHandler("Single Player", "Only one character on the board so it is single player.", false);

    	}
    	
    	if(numberOfFlags < 1) {
    		alertHandler("Testing Mode", "There is no win state with this board as there are no flags.", false);
    	}
	}
	
	/**
	 * Gets the UI controller.
	 *
	 * @return the UI controller
	 */
	public SampleController getUIController() {
		return controller;
	}
	
	
	/**
	   * If character dead handler.
	   */
	  public void ifCharacterDeadHandler() {
		//Used to check if the characters are dead.
		if(characterNumber == 1 ) {
			if(board.getCharacters('A').getHealth() <= 0) {
				board.getCharacters('A').setHealth(5);
				board.removeAndResetIcon('A');
				if(!isTest) {
					AHealthBar();
				}
			}
		}
		else if(characterNumber == 2) {
			if(board.getCharacters('A').getHealth() <= 0) {
				board.getCharacters('A').setHealth(5);
				board.removeAndResetIcon('A');
				if(!isTest) {
					AHealthBar();
				}
			}
			if(board.getCharacters('B').getHealth() <= 0) {
				board.getCharacters('B').setHealth(5);
				board.removeAndResetIcon('B');
				if(!isTest) {
					BHealthBar();
				}
			}
		}
		else if(characterNumber == 3) {
			if(board.getCharacters('A').getHealth() <= 0) {
				board.getCharacters('A').setHealth(5);
				board.removeAndResetIcon('A');
				if(!isTest) {
					AHealthBar();
				}
			}
			if(board.getCharacters('B').getHealth() <= 0) {
				board.getCharacters('B').setHealth(5);
				board.removeAndResetIcon('B');
				if(!isTest) {
					BHealthBar();
				}
			}
			if(board.getCharacters('C').getHealth() <= 0) {
				board.getCharacters('C').setHealth(5);
				board.removeAndResetIcon('C');
				if(!isTest) {
					CHealthBar();
				}
			}
		}
		else {
			if(board.getCharacters('A').getHealth() <= 0) {
				board.getCharacters('A').setHealth(5);
				board.removeAndResetIcon('A');
				if(!isTest) {
					AHealthBar();
				}
			}
			if(board.getCharacters('B').getHealth() <= 0) {
				board.getCharacters('B').setHealth(5);
				board.removeAndResetIcon('B');
				if(!isTest) {
					BHealthBar();
				}
			}
			if(board.getCharacters('C').getHealth() <= 0) {
				board.getCharacters('C').setHealth(5);
				board.removeAndResetIcon('C');
				if(!isTest) {
					CHealthBar();
				}
			}
			if(board.getCharacters('D').getHealth() <= 0) {
				board.getCharacters('D').setHealth(5);
				board.removeAndResetIcon('D');
				if(!isTest) {
					DHealthBar();
				}
			}
		}
		updateHealthBar();
	}
	
	/**
	 * Update health bar.
	 */
	public void updateHealthBar() {
		//updates ui health column
		AHealthBar();
		if(characterNumber > 1) {
			BHealthBar();
		}
		if(characterNumber > 2) {
			CHealthBar();
		}
		if(characterNumber > 3) {
			DHealthBar();
		}

	}
	
	/**
	 * Alert handler.
	 *
	 * @param Header the header
	 * @param Information the information
	 * @param shouldClose if window should close
	 */
	private void alertHandler(String Header, String Information, Boolean shouldClose) {
		if(!isTest) {
			Platform.runLater(new Runnable(){
				@Override
				public void run() {
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle(Header);
					alert.setHeaderText(Header);
					alert.setContentText(Information);
					alert.showAndWait();
					if(shouldClose) {
						System.exit(0);
					}				
				}
				
			});
		}
		else {
			if(Header.equals("Game Won")) {
				setGameWon(true);
			}
		}

	}
	
	/**
	 * Game function.
	 *
	 * @param usingFiles the using files
	 * @param isTesting the is testing
	 */
	public void gameFunction(boolean usingFiles, boolean isTesting) {
		if(!usingFiles) {
			if(controller.getInput() != "") {
				if(program.checkValue(controller.getInput())) {
					if(controller.getInput().length() == board.getCharacters(CurrentPlayer).getHealth()) {
						//Takes all values and adds to queue
						addToQueues();
						if(characterNumber != 1) {
							changePlayer();
						}
						if (checkIfQueueFull()) {
							//once all active characters queues are full the game begins
							controller.DisableInput();
							Boolean isWon = false;
							Robots winnerRobot = null;
							int charactersPlayed = 0;
							//Continues to go through each robot queue until all are empty
							while (!A.isEmpty() || !B.isEmpty() || !C.isEmpty() || !D.isEmpty()) {
								charactersPlayed++;
								if(charactersPlayed == characterNumber) {
									System.out.println(CurrentPlayer);
									changePlayer();
									System.out.println(CurrentPlayer);
								}
								moveCharacters();
								for(int i = 0; i < board.getCharacters().size(); i++) {
									Robots r = (Robots) board.getCharacters().get(i);
									if((r.getFlagsRetrieved() == numberOfFlags) && numberOfFlags > 0){
										isWon = true;
										winnerRobot = r;
										break;
									}
								}
								if(isWon) {
									System.out.println("Winning Board: ");
									board.printBoard();
									System.out.println(winnerRobot.getValue() + " has won!");
									this.setWinnerRobot(winnerRobot.getValue());
									alertHandler("Game Won", winnerRobot.getValue() + " has won the game!", true);
									break;
								}
							}
							if(characterNumber > 2) {
								CurrentPlayer = 'D';
								changePlayer();
							}
							controller.setText("--------------------");
							roundHitAlready = false;
							RoundNumber++;
							controller.EnabledInput();
						}
						controller.resetInput();
						board.setCharacterReactions();
						startLoop(false);
					}
					else {
						controller.resetInput();
						alertHandler("Incorrect Input Length", "The length of your command should be equal to robots health!", false);
					}

				}
				else {
					controller.resetInput();
					alertHandler("Incorrect Input Values", "There are incorrect values within your input!", false);
				}

			}

		}
		else {
			//Used to turn the executor off.
			if(!isTesting) {
				exec.shutdown();
			}
			updateQueueInputValues(true);
			Boolean isWon = false;
			Robots winnerRobot = null;
			CurrentPlayer = 'A';
			int charactersPlayed = 0;
			while (!A.isEmpty() || !B.isEmpty() || !C.isEmpty() || !D.isEmpty()) {
				moveCharacters();
				charactersPlayed++;
				if(charactersPlayed == characterNumber) {
					System.out.println(CurrentPlayer);
					changePlayer();
					System.out.println(CurrentPlayer);
				}
				for(int i = 0; i < board.getCharacters().size(); i++) {
					Robots r = (Robots) board.getCharacters().get(i);
					if((r.getFlagsRetrieved() == numberOfFlags) && numberOfFlags > 0){
						isWon = true;
						winnerRobot = r;
						break;
					}
				}
				if(isWon) {
					System.out.println("Winning Board: ");
					board.printBoard();
					System.out.println(winnerRobot.getValue() + " has won!");
					this.setWinnerRobot(winnerRobot.getValue());
					alertHandler("Game Won", winnerRobot.getValue() + " has won the game!", true);
					break;
				}
				if(!board.getCommands().AQueue().isEmpty() || !board.getCommands().BQueue().isEmpty() || !board.getCommands().CQueue().isEmpty() || !board.getCommands().DQueue().isEmpty()) {
					updateQueueInputValues(false);
				}
				delayAnimation();
				delayAnimation();
			}
			if(!isWon) {
				alertHandler("Stalemate", "No robot has won the game!", true);
			}
		}
	}
	
	/**
	 * Sets the textArea text.
	 *
	 * @param Value the new text
	 */
	public void setText(String Value) {
		if(!isTest) {
			Platform.runLater(new Runnable(){
	
				@Override
				public void run() {
					controller.setText(Value);				
				}
	
			});
		}
	}
	
  	/**
	   * Update queue input values.
	   *
	   * @param firstRun if this method is being run the first time
	   */
	public void updateQueueInputValues(boolean firstRun) {
		if(characterNumber == 1) {
			while (A.isEmpty()) {
				roundHitAlready = false;
				setRoundText(false);
				
				queueHandler(A, board.getCommands().AQueue());
				setText("Robot " + CurrentPlayer + " : " + A);
				setText("--------------------");
				RoundNumber++;				
			}
		}
		else if(characterNumber == 2) {
			while (A.isEmpty() && B.isEmpty()) {
				roundHitAlready = false;
				setRoundText(false);
			
				if(!firstRun) {
					CurrentPlayer = 'B';
					changePlayer();
				}
				
				queueHandler(A, board.getCommands().AQueue());
				setText("Robot " + CurrentPlayer + " : " + A);
				changePlayer();
				
				queueHandler(B, board.getCommands().BQueue());
				setText("Robot " + CurrentPlayer + " : " + B);
				setText("--------------------");
				RoundNumber++;				
			}
		}
		else if(characterNumber == 3) {
			while (A.isEmpty() && B.isEmpty() && C.isEmpty()) {
				roundHitAlready = false;
				setRoundText(false);
				
				if(!firstRun) {
					CurrentPlayer = 'C';
					changePlayer();
				}
				
				queueHandler(A, board.getCommands().AQueue());
				setText("Robot " + CurrentPlayer + " : " + A);
				changePlayer();
				
				queueHandler(B, board.getCommands().BQueue());
				setText("Robot " + CurrentPlayer + " : " + B);
				changePlayer();
				
				queueHandler(C, board.getCommands().CQueue());
				setText("Robot " + CurrentPlayer + " : " + C);
				setText("--------------------");
				RoundNumber++;				
			}
		}
		else {
			while (A.isEmpty() && B.isEmpty() && C.isEmpty() && D.isEmpty()) {
				roundHitAlready = false;
				setRoundText(false);
				
				if(!firstRun) {
					CurrentPlayer = 'D';
					changePlayer();
				}
				
				queueHandler(A, board.getCommands().AQueue());
				setText("Robot " + CurrentPlayer + " : " + A);
				changePlayer();
				
				queueHandler(B, board.getCommands().BQueue());
				setText("Robot " + CurrentPlayer + " : " + B);
				changePlayer();
				
				queueHandler(C, board.getCommands().CQueue());
				setText("Robot " + CurrentPlayer + " : " + C);
				changePlayer();
				
				queueHandler(D, board.getCommands().DQueue());
				setText("Robot " + CurrentPlayer + " : " + D);
				controller.setText("--------------------");
				RoundNumber++;				
			}
		}

	}
	
  	/**
	   * Queue handler.
	   *
	   * @param values the input values
	   * @param queues the queues
	   */
	  /*		
  	 * This is used to add values to the queue, from the ReadCommands value.
  	 */
	public void queueHandler(Queue<String> values, Queue<String> queues) {
		for(int i = 0; i < 5; i++) {
			if(queues.size() == 0) {
				values = queues;
				i = 6;
			}
			else {
				values.add(queues.element());
				queues.remove();
			}
		}
	}
	
	/**
	 * Round text UI.
	 */
	public void roundTextUI() {
		controller.setText("Round " + RoundNumber);
		controller.setText("--------------------");							
	}
	
	/**
	 * Round text UI text input.
	 */
	public void roundTextUITextInput() {
		Platform.runLater(new Runnable(){

			@Override
			public void run() {
				controller.setText("Round " + RoundNumber);
				controller.setText("--------------------");					
			}
		
		});
	}
	
  	/**
	   * Sets the round text.
	   *
	   * @param usingInputs the new round text
	   */
	public void setRoundText(Boolean usingInputs) {
		if(usingInputs) {
			if(!roundHitAlready) {
				roundTextUI();
				roundHitAlready = true;
			}
		}
		else {
			if(!roundHitAlready) {
				if(!isTest) {
					roundTextUITextInput();
				}
				roundHitAlready = true;
			}
		}
	}
	
  	/**
	   * Adds values to the queues.
	   */
	public void addToQueues() {
    	setRoundText(true);
		String values = controller.getInput();
		exec.shutdown();
		String valuesUpper = values.toUpperCase();
		String[] commandInput = valuesUpper.split("");
		for(int i = 0; i < commandInput.length; i++) {
			if(CurrentPlayer == 'A') {
				A.add(commandInput[i]);
			}
			else if(CurrentPlayer == 'B' && characterNumber > 1) {
				B.add(commandInput[i]);
			}
			else if(CurrentPlayer == 'C' && characterNumber > 2) {
				C.add(commandInput[i]);
			}
			else if(CurrentPlayer == 'D' && characterNumber > 3) {
				D.add(commandInput[i]);
			}
		}
		controller.setText("Robot " + CurrentPlayer + " : " + values);
	}
	
  	/**
	   * Delay animation on Grid.
	   */
	public void delayAnimation() {
		try {
			Thread.sleep(400);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Activate board.
	 */
	public void ActivateBoard() {
		if(characterNumber == 1) {
			board.NonGameWinningLocationHandler(board.getCharacters('A'));
			board.resetHashMapVertical();
			board.resetHashMapHorizontal();
		}
		else if(characterNumber == 2) {
			board.NonGameWinningLocationHandler(board.getCharacters('A'));
			board.NonGameWinningLocationHandler(board.getCharacters('B'));
			board.resetHashMapVertical();
			board.resetHashMapHorizontal();
		}
		else if(characterNumber == 3) {
			board.NonGameWinningLocationHandler(board.getCharacters('A'));
			board.NonGameWinningLocationHandler(board.getCharacters('B'));
			board.NonGameWinningLocationHandler(board.getCharacters('C'));
			board.resetHashMapVertical();
			board.resetHashMapHorizontal();
		}
		else if(characterNumber == 4) {
			board.NonGameWinningLocationHandler(board.getCharacters('A'));
			board.NonGameWinningLocationHandler(board.getCharacters('B'));
			board.NonGameWinningLocationHandler(board.getCharacters('C'));
			board.NonGameWinningLocationHandler(board.getCharacters('D'));
			board.resetHashMapVertical();
			board.resetHashMapHorizontal();
		}
	}
	
  	/**
	   * Move characters.
	   */
	public void moveCharacters() {
		ActivateBoard();
		updateHealthBar();
		/*
		 * These are what are used to control each of the characters movements on the board.
		 * it takes the head of each Queue sends it to boardLogicCharacterMovement class
		 * handles it and then moves on.
		 * it then checks if the player is currently on a flag
		 */
		if(CurrentPlayer == 'A') {
			if(A.peek() != null) {
				String Input = A.element();
				delayAnimation();
				Boolean hitWall = false;
				if(lettersTaken == 0) {
					hitWall = board.boardLogicCharacterMovement(Input, board.getCharacters(CurrentPlayer), true);

				}
				else {
					hitWall = board.boardLogicCharacterMovement(Input, board.getCharacters(CurrentPlayer), false);

				}
				ifCharacterDeadHandler();
				if (hitWall) {
					board.getCharacters('A').setHealth(5);
					A.remove();
				}
				else {
					A.remove();
				}
				board.FlagHandler(board.getCharacters('A'));


			}
			if(characterNumber != 1) {
				changePlayer();
			}
		}
		else if(CurrentPlayer == 'B' && characterNumber > 1) {
			if(B.peek() != null) {
				String Input = B.element();
				delayAnimation();
				Boolean hitWall = board.boardLogicCharacterMovement(Input, board.getCharacters(CurrentPlayer), false);
				ifCharacterDeadHandler();
				if (hitWall) {
					board.getCharacters('B').setHealth(5);
					B.remove();
				}
				else {
					B.remove();
				}
				board.FlagHandler(board.getCharacters('B'));

			}
			changePlayer();
		}
		else if(CurrentPlayer == 'C' && characterNumber > 2) {
			if(C.peek() != null) {
				String Input = C.element();
				delayAnimation();
				Boolean hitWall = board.boardLogicCharacterMovement(Input, board.getCharacters(CurrentPlayer), false);
				ifCharacterDeadHandler();
				if (hitWall) {
					board.getCharacters('C').setHealth(5);
					C.remove();
				}
				else {
					C.remove();
				}
				board.FlagHandler(board.getCharacters('C'));
			}
			changePlayer();
		}
		else if(CurrentPlayer == 'D' && characterNumber > 3) {
			if(D.peek() != null) {
				String Input = D.element();
				delayAnimation();
				Boolean hitWall = board.boardLogicCharacterMovement(Input, board.getCharacters(CurrentPlayer), false);
				ifCharacterDeadHandler();
				if (hitWall) {
					board.getCharacters('D').setHealth(5);
					D.remove();
				}
				else {
					D.remove();
				}
				board.FlagHandler(board.getCharacters('D'));

			}
			changePlayer();
		}
	}
	
  	/**
	   * A health bar changing.
	   */
	public void AHealthBar() {
		if(!isTest) {
			Platform.runLater(new Runnable(){
				@Override
				public void run() {
					controller.setTextA(""+ board.getCharacters('A').getHealth());
				}
				
			});
		}
	}
	
	/**
	 * B health bar changing.
	 */
	public void BHealthBar() {
		if(!isTest) {
			Platform.runLater(new Runnable(){
				@Override
				public void run() {
					controller.setTextB(""+ board.getCharacters('B').getHealth());
				}
				
			});
		}
	}
	
	/**
	 * C health bar changing.
	 */
	public void CHealthBar() {
		if(!isTest) {
			Platform.runLater(new Runnable(){
				@Override
				public void run() {
					controller.setTextC(""+ board.getCharacters('C').getHealth());
				}
				
			});
		}
	}
	
	/**
	 * D health bar changing.
	 */
	public void DHealthBar() {
		if(!isTest) {
			Platform.runLater(new Runnable(){
				@Override
				public void run() {
					controller.setTextD(""+ board.getCharacters('D').getHealth());
				}
				
			});
		}
	}
	
  	/**
	   * Check if queue full.
	   *
	   * @return the boolean
	   */
	public Boolean checkIfQueueFull() {
		
		if(characterNumber == 1) {
			if (!A.isEmpty()) {
				return true;
			}
		}
		else if(characterNumber == 2) {
			if (!A.isEmpty() && !B.isEmpty()) {
				return true;
			}
		}
		else if(characterNumber == 3) {
			if (!A.isEmpty() && !B.isEmpty() && !C.isEmpty()) {
				return true;
			}
		}
		else {
			if (!A.isEmpty() && !B.isEmpty() && !C.isEmpty() && !D.isEmpty()) {
				return true;
			}
		}

		return false;
	}
	
	/**
	 * Current process.
	 *
	 * @param letter the current letter value
	 */
	public void currentProcess(String letter) {
		if(!isTest) {
			Platform.runLater(new Runnable(){
	
				@Override
				public void run() {
					controller.CharacterShower(letter);
					
				}
				
			});
		}
	}
	
  	/**
	   * Change player.
	   */
	  /*
  	 * This changes the player value.
  	 */
	public void changePlayer() {
		if(characterNumber == 2) {
			if(CurrentPlayer == 'A') {
				CurrentPlayer = 'B';
				currentProcess("B");
				lettersTaken++;
			}
			else if(CurrentPlayer == 'B') {
				CurrentPlayer = 'A';
				currentProcess("A");
				lettersTaken++;
			}
		}
		else if(characterNumber == 3) {
			if(CurrentPlayer == 'A') {
				CurrentPlayer = 'B';
				currentProcess("B");
				lettersTaken++;
			}
			else if(CurrentPlayer == 'B') {
				CurrentPlayer = 'C';
				currentProcess("C");
				lettersTaken++;
			}
			else if(CurrentPlayer == 'C') {
				CurrentPlayer = 'A';
				currentProcess("A");
				lettersTaken++;
			}
		}
		else {
			if(CurrentPlayer == 'A') {
				CurrentPlayer = 'B';
				currentProcess("B");
				lettersTaken++;
			}
			else if(CurrentPlayer == 'B') {
				CurrentPlayer = 'C';
				currentProcess("C");
				lettersTaken++;
			}
			else if(CurrentPlayer == 'C') {
				CurrentPlayer = 'D';
				currentProcess("D");
				lettersTaken++;
			}
			else if(CurrentPlayer == 'D') {
				CurrentPlayer = 'A';
				currentProcess("A");
				lettersTaken++;
			}
		}
	}
		
  	/**
	   * Start loop.
	   *
	   * @param usingFiles the using files
	   */
	  /*
  	 * This is used to start the loop
  	 * starts a new thread to start the game loop which is separate
  	 * from the initial game thread. 
  	 * Executors are used with TimeUnit to check the value to see if it MilliSeconds.
  	 * We call this run method every millisecond to make sure we get the user input
  	 * without delay.
  	 */
	public void startLoop(Boolean usingFiles) {
		exec = Executors.newSingleThreadScheduledExecutor();
		exec.scheduleAtFixedRate(new Runnable() {
		    @Override
		    public void run() {
		    	if(!usingFiles) {
			    	gameFunction(false, false);

		    	}
		    	else {
		    		controller.DisableInput();
		    		RoundNumber--;
		    		gameFunction(true, false);
		    	}
		    }
		}, 0, 1, TimeUnit.MILLISECONDS);;
		
	}

	/**
	 * Gets the checks if is test.
	 *
	 * @return the checks if is test
	 */
	public Boolean getIsTest() {
		return isTest;
	}

	/**
	 * Sets the checks if is test.
	 *
	 * @param isTest the new checks if is test
	 */
	public void setIsTest(Boolean isTest) {
		this.isTest = isTest;
	}

	/**
	 * Gets the game won.
	 *
	 * @return the game won
	 */
	public Boolean getGameWon() {
		return gameWon;
	}

	/**
	 * Sets the game won.
	 *
	 * @param gameWon the new game won
	 */
	public void setGameWon(Boolean gameWon) {
		this.gameWon = gameWon;
	}

	/**
	 * Gets the winner robot.
	 *
	 * @return the winner robot
	 */
	public char getWinnerRobot() {
		return winnerRobot;
	}

	/**
	 * Sets the winner robot.
	 *
	 * @param winnerRobot the new winner robot
	 */
	public void setWinnerRobot(char winnerRobot) {
		this.winnerRobot = winnerRobot;
	}
}
