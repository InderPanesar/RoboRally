package robots.Board;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import robots.Character.Characters;
import robots.Character.Robots;
import robots.Locations.BoardLaser;
import robots.Locations.ConveryorBelt;
import robots.Locations.Flag;
import robots.Locations.Gear;
import robots.Locations.Pit;
import robots.main.ReadCommands;

/**
 * The Class BoardLocationHandler.
 */
public class BoardLocationHandler {
	
	/** The board. */
	private Board board;
	
	/** The Hash map vertical. */
	private HashMap<String, Boolean> HashMapVertical;
	
	/** The Hash map horizontal. */
	private HashMap<String, Boolean> HashMapHorizontal;
	
	/** checks if testing. */
	private Boolean isTesting;
	
	/**  checks if robot is using laser. */
	private Boolean robotUsesLaser;
	
	/**
	 * Instantiates a new board location handler.
	 *
	 * @param fileName the file name
	 * @param selectedBoardFile the selected board file
	 * @param selectedCommandFile the selected command file
	 * @param acceptedBoardValues the accepted board values
	 * @param acceptedUserInputs the accepted user inputs
	 * @param isTest check if a test
	 * @param robotUseLasers if robot use lasers
	 */
	public BoardLocationHandler(String fileName, File selectedBoardFile, File selectedCommandFile, char[] acceptedBoardValues, char[] acceptedUserInputs, Boolean isTest, Boolean robotUseLasers) {
		//Checks if robots use there lasers within the game
		this.setRobotUsesLaser(robotUseLasers);
		//Sets if it is testing
		this.setIsTesting(isTest);
		//intialises a board
		board = new Board(fileName, selectedBoardFile, selectedCommandFile, acceptedBoardValues, acceptedUserInputs, isTest);
		//Sets up the hash map for lasers
		this.HashMapHorizontal = board.HashMapHorizontal();
		this.HashMapVertical = board.HashMapVertical();
		/*
		 * The way this works is that each board laser has its own unique boolean value
		 * this is done by it having a key which is its X+Y coordinate. These two values
		 * are unique to each emitter and hence can be checked.
		 */
	}
	
	/**
	 * Prints the board.
	 */
	public void printBoard() {
		//Used in the game class once game is successful
		board.printBoard();
	}
	
	/**
	 * Gets the commands.
	 *
	 * @return the commands
	 */
	public ReadCommands getCommands() {
		//Gets ReadCommand class.
		return board.getCommands();
	}
	
	/**
	 * Sets the character reactions.
	 */
	public void setCharacterReactions() {
		//calls the method below
		board.setCharacterReactions();
	}
	
	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return board.getWidth();
	}
	
	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return board.getHeight();
	}
	
	/**
	 * Gets the flag number.
	 *
	 * @return the flag number
	 */
	public int getFlagNumber() {
		return board.getFlagNumber();
	}
	
	/**
	 * Gets the character number.
	 *
	 * @return the character number
	 */
	public int getCharacterNumber() {
		return board.getCharacterNumber();
	}
	
	/**
	 * Gets the tiles.
	 *
	 * @return the tiles
	 */
	public MapTile[][] getTiles() {
		return board.tiles;
	}
	
	/**
	 * Removes the and reset icon.
	 *
	 * @param value the robot character
	 */
	public void removeAndResetIcon(char value) {
		board.removeIcon(board.getCharacters(value).getX(), board.getCharacters(value).getY()); 
		board.resetCharacter(value + "", board.getCharacters(value));
	}
	
	/**
	 * Gets the characters.
	 *
	 * @param value the robot character
	 * @return the characters
	 */
	public Robots getCharacters(char value) {
		return board.getCharacters(value);
	}
	
	/**
	 * Gets the characters.
	 *
	 * @return the characters
	 */
	public List<Characters> getCharacters() {
		return board.getCharacters();
	}
	

	/**
	 * Pit collisions.
	 *
	 * @param C the Robot
	 * @param Value the value
	 * @param x the x
	 * @param y the y
	 * @param dx the directional X
	 * @param dy the directional Y
	 * @param isClockwise check if clockwise
	 */
	public void PitCollisions(Robots C, String Value, int x, int y, int dx, int dy, boolean isClockwise) {
		//isClockwise is used to check if the robot is going straight or backward
		if(!isClockwise) { 
			if (board.tiles[x+dx][y+dy] instanceof Pit) {
				if(C.getX() == C.getIntialX() && C.getY() == C.getIntialY()) {
					board.setGridIcon(x, y, ' ');
					board.resetCharacter(Value, C);
				}
				else {
					board.setGridIcon(x, y, ' ');
					board.resetCharacter(Value, C);
				}
			}
		}
		else {
			//Checks if tile contains pits
			if (board.tiles[x-dx][y-dy] instanceof Pit) {
				if(C.getX() == C.getIntialX() && C.getY() == C.getIntialY()) {
					board.setGridIcon(x, y, ' ');
					board.resetCharacter(Value, C);
				}
				else {
					board.setGridIcon(x, y, ' ');
					board.resetCharacter(Value, C);
				}
			}
		}
	}
	
	/**
	 * Flag handler.
	 *
	 * @param C the Robot
	 */
	public void FlagHandler(Robots C) {
		int x = C.getX();
		int y = C.getY();
		//This just checks if the robot is currently on a flag
		if (board.tiles[x][y] instanceof Flag) {
			Flag flag = (Flag) board.tiles[x][y];
			//if there is it adds it to FlagRetrieved within robots
			int CurrentLooking = C.getFlagsRetrieved()+1;
			if(CurrentLooking == flag.getFlagNumber()) {
				C.setFlagsRetrieved(C.getFlagsRetrieved()+1);
			}
		}
	}
	
	/**
	 * Check if lasers are hit.
	 *
	 * @param isVertical checks if vertical
	 * @param Key the key generated from X and Y
	 * @return the boolean from corresponding HashMap
	 */
	public Boolean checkifLasersAreHit(Boolean isVertical, String Key) {
		//gets the boolean value for each emitter.
		if(isVertical) {
			return HashMapVertical.get(Key);
		}
		else {
			return HashMapHorizontal.get(Key);
		}
	}
	
	/**
	 * Sets the lasers hashmap.
	 *
	 * @param isVertical check if vertical
	 * @param Key the key generated from X and Y
	 */
	public void setLasersHashmap(Boolean isVertical, String Key) {
		//This sets up the Laser Hashmap
		if(isVertical) {
			HashMapVertical.put(Key, true);
		}
		else {
			HashMapHorizontal.put(Key, true);
		}
	}
	
	/**
	 * Reset hash map vertical.
	 */
	public void resetHashMapVertical() {
		//resets it once the board has been activated
	      for (Entry<String, Boolean> entry : HashMapVertical.entrySet()) {
	    	  HashMapVertical.put(entry.getKey(), false);
	      }
	}
	
	/**
	 * Reset hash map horizontal.
	 */
	public void resetHashMapHorizontal() {
		//resets it once the board has been activated
	      for (Entry<String, Boolean> entry : HashMapHorizontal.entrySet()) {
	    	  HashMapHorizontal.put(entry.getKey(), false);
	      }
	}
	
	/**
	 * Laser beam handler.
	 */
	public void LaserBeamHandler() {
	  	for (int GridY = 0; GridY < board.getHeight(); GridY++) {
	  		for (int GridX = 0; GridX < board.getWidth(); GridX++) {
	  			//checks if instance of boardLaser
	  			if (board.tiles[GridX][GridY] instanceof BoardLaser) {
	  				BoardLaser Emitter = (BoardLaser) board.tiles[GridX][GridY];
	  				//if it is vertical and an emitter
	  				if(Emitter.getIsVertical() && Emitter.getIsEmitter()) {
	  					Boolean CharacterHit = false;
	  					//Looks down below until it hits a character
	  					int TempY = GridY+1;
	  					for(TempY = GridY+1; TempY < board.getHeight() - 1; TempY++) {
	  						if (board.tiles[GridX][TempY] instanceof BoardLaser || CharacterHit) {
	  							break;
	  						}
	  						else {
	  							for(int i = 0; i < board.getCharacters().size(); i++) {
	  								if(GridX == board.getCharacters().get(i).getX() && TempY == board.getCharacters().get(i).getY() && !CharacterHit && !checkifLasersAreHit(true, GridX + "" + GridY)) {
	  									Robots robots = (Robots) board.getCharacters().get(i);
	  									CharacterHit = true;
	  			  						robots.setHealth(robots.getHealth() - 1);
	  			  						//once it hits a character it sets it hashmap hit to true
	  			  						setLasersHashmap(true, GridX + "" + GridY);
	  			  						break;
	  								}
	  							}
	  						}
	  					}
	  				}
	  				// else check it is an emitter
	  				else if(Emitter.getIsEmitter()) {
	  						Boolean CharacterHit = false;
	  						//Same as above however instead of below it check everything to its right.
	  						int TempX = GridX+1;
	  						for(TempX = GridX+1; TempX < board.getWidth() -1; TempX++) {
	  							if (board.tiles[TempX][GridY] instanceof BoardLaser || CharacterHit) {
	  								break;
	  							}
	  							else {
	  								for(int i = 0; i < board.getCharacters().size(); i++) {
	  									if(TempX == board.getCharacters().get(i).getX() && GridY == board.getCharacters().get(i).getY() && !CharacterHit && !checkifLasersAreHit(false, GridX + "" + GridY)) {
	  										Robots robots = (Robots) board.getCharacters().get(i);
	  										CharacterHit = true;
	  										robots.setHealth(robots.getHealth() - 1);
		  			  						setLasersHashmap(false, GridX + "" + GridY);
	  										break;
	  									}
	  								}
	  							}
	  						}
	  					}
	  				}	
	  			}
	  		}
		}
	
  	/**
	   * This is the ConveryorBelt
	   * and will handle how the robots move on
	   * the conveyorbelt.
	   *
	   * @param C the robot
	   * @param isForward the is forward
	   */
	public void ConveryorBeltCorner(Robots C, Boolean isForward) {
		int x = C.getX();
		int y = C.getY();
		
		if(board.tiles[x][y] instanceof ConveryorBelt) {
			ConveryorBelt belt = (ConveryorBelt) board.tiles[x][y];
			char value = belt.getValue();
			//Checks the value of the belt
			if(value == 'n' || value == 'N') {
				//Checks if it isn't already looking in that direction
				if(C.getRotation() != 0) {
					if(value == 'n') {
						//anticlockwise since lowercase
						board.LabelRotationHandler(C, -90);
						C.setRotation(C.getRotation()-90);
						board.anticlockwiseTurnHandler(C);
						C.setY(y - 1);
					}
					if(value == 'N') {
						//clockwise since uppercase
						board.LabelRotationHandler(C, 90);
						C.setRotation(C.getRotation()+90);
						board.clockwiseTurnHandler(C);
						//Sets the movement of the belt
						C.setY(y - 1);
					}
				}
			}
			//The same happens as above but different values for get rotation
			//and Character set values
			if(value == 's' || value == 'S') {
				if(C.getRotation() != 180) {
					if(value == 's') {
						board.LabelRotationHandler(C, -90);
						C.setRotation(C.getRotation()-90);
						board.anticlockwiseTurnHandler(C);
						C.setY(y + 1);
					}
					if(value == 'S') {
						board.LabelRotationHandler(C, 90);
						C.setRotation(C.getRotation()+90);
						board.clockwiseTurnHandler(C);
						C.setY(y + 1);
					}
				}
			}
			if(value == 'e' || value == 'E') {
				if(C.getRotation() != 90) {
					if(value == 'e') {
						board.LabelRotationHandler(C, -90);
						C.setRotation(C.getRotation()-90);
						board.anticlockwiseTurnHandler(C);
						C.setX(x + 1);
					}
					if(value == 'E') {
						board.LabelRotationHandler(C, 90);
						C.setRotation(C.getRotation()+90);
						board.clockwiseTurnHandler(C);
						C.setX(x + 1);
					}
				}
			}
			if(value == 'w' || value == 'W') {
				if(C.getRotation() != 270) {
					if(value == 'w') {
						board.LabelRotationHandler(C, -90);
						C.setRotation(C.getRotation()-90);
						board.anticlockwiseTurnHandler(C);
						C.setX(x - 1);
					}
					if(value == 'W') {
						board.LabelRotationHandler(C, 90);
						C.setRotation(C.getRotation()+90);
						board.clockwiseTurnHandler(C);
						C.setX(x - 1);
					}
				}
			}
		}
	}
	
	/**
	 * Converyor belt straight.
	 *
	 * @param C the c
	 * @param isForward the is forward
	 */
	public void ConveryorBeltStraight(Robots C, Boolean isForward) {
		int x = C.getX();
		int y = C.getY();
		if(board.tiles[x][y] instanceof ConveryorBelt) {
			ConveryorBelt belt = (ConveryorBelt) board.tiles[x][y];
			char value = belt.getValue();
			//Sets the label of each depending on the direction.
			if(value == '^') {
				C.setX(x);
				C.setY(y-1);
			}
			if(value == 'v') {
				C.setX(x);
				C.setY(y+1);
			}
			if(value == '>') {
				C.setX(x+1);
				C.setY(y);
			}
			if(value == '<') {
				C.setX(x-1);
				C.setY(y);
			}
		}
	}
	
  	/**
	   * Gear handler.
	   *
	   * @param C the Robot
	   */
	  /*
  	 * This is another location however, this handles the movement of the gears.
  	 */
	public void GearsHandler(Robots C) {
		int x = C.getX();
		int y = C.getY();
		if (board.tiles[x][y] instanceof Gear) {
			Gear r = (Gear) board.tiles[x][y];
			if(r.getValue() == '+') {
				//Sets the rotation depending on the gear, in this case clockwise
				board.LabelRotationHandler(C, 90);
				C.setRotation(C.getRotation()+90);
				board.clockwiseTurnHandler(C);
			}
			else {
				//Sets the rotation depending on the gear, in this case anticlockwise
				board.LabelRotationHandler(C, -90);
				C.setRotation(C.getRotation()-90);
				board.anticlockwiseTurnHandler(C);
			}

		}
	}
	
  	/**
	   * Board logic character movement.
	   *
	   * @param Value the robot character
	   * @param C the robot
	   * @param isFirstGo the is first go
	   * @return true, if successful
	   */
	  /*
  	 * This is where all the boardLogic is handled.
  	 * This is where all the board values.
  	 * This handles each value input, and what it will do the Grid/Board
  	 * It will check the pit values here.
  	 */
	public boolean boardLogicCharacterMovement(String Value, Robots C, Boolean isFirstGo) {
		int x = C.getX();
		int y = C.getY();
		GearsHandler(C);
		
		board.resetTileIcons();
		board.updateCharacterReactions();
		
		if(Value.equals("F")) {
			if(y+C.getDY() >= 0 && y+C.getDY() < board.getHeight() && x+C.getDX() < board.getWidth() && x+C.getDX() >= 0) {
				
				if (board.tiles[x+C.getDX()][y+C.getDY()] instanceof Pit) {
					PitCollisions(C, Value, x, y, C.getDX(), C.getDY(), false);
					board.printBoard();
					return false;
				}
				
				board.updateGridIcon(x+C.getDX(), y+C.getDY(), x, y, C.getValue());
				C.setX(x+C.getDX());
				C.setY(y+C.getDY());
				ForwardsandBackwardsHandler(C, Value, false);
				ConveryorBeltStraight(C, false);
				ConveryorBeltCorner(C,false);
				FlagHandler(C);
				board.printBoard();
				LaserBeams();
				return false;
			}
			else {
				board.setGridIcon(x, y, ' ');
				board.resetCharacter(Value, C);
				board.setCharacterReactions();
				board.printBoard();
				LaserBeams();
				return true;
			}
		}
		
		if(Value.equals("B")) {
			if(y-C.getDY() >= 0 && y-C.getDY() < board.getHeight() && x-C.getDX() < board.getWidth() && x-C.getDX() >= 0) {
				if (board.tiles[x-C.getDX()][y-C.getDY()] instanceof Pit) {
					PitCollisions(C, Value, x, y, C.getDX(), C.getDY(), true);
					return false;
				}
				board.updateGridIcon(x-C.getDX(), y-C.getDY(), x, y, C.getValue());
				C.setX(x-C.getDX());
				C.setY(y-C.getDY());
				ForwardsandBackwardsHandler(C, Value, true);
				ConveryorBeltStraight(C, false);
				ConveryorBeltCorner(C,false);
				FlagHandler(C);
				board.printBoard();
				LaserBeams();
				return false;
			}
			else {
				board.printBoard();
				return true;
			}
		}
		
		if(Value.equals("W")) {
			ConveryorBeltStraight(C, false);
			ConveryorBeltCorner(C,false);
			return false;
		}
		
		if(Value.equals("L")) {
			board.LabelRotationHandler(C, -90);
			C.setRotation(C.getRotation()-90);
			board.anticlockwiseTurnHandler(C);
			ConveryorBeltStraight(C, false);
			ConveryorBeltCorner(C,false);
			board.printBoard();
			LaserBeams();
			return false;

		}
		
		if(Value.equals("R")) {
			board.LabelRotationHandler(C, 90);
			C.setRotation(C.getRotation()+90);
			board.clockwiseTurnHandler(C);
			ConveryorBeltStraight(C, false);
			ConveryorBeltCorner(C,false);
			board.printBoard();
			LaserBeams();
			return false;
		}
		
		if(Value.equals("U")) {
			board.LabelRotationHandler(C, 180);
			C.setRotation(C.getRotation()+180);
			board.uTurnHandler(C);
			ConveryorBeltStraight(C, false);
			ConveryorBeltCorner(C,false);
			board.printBoard();
			LaserBeams();
			return false;
		}
		
		if(Value.equals("S")) {
			RobotLaserBeamHandler(C);
		}
		return false;
	}
	
	/**
	 * Robot laser beam handler.
	 *
	 * @param C the robot
	 */
	public void RobotLaserBeamHandler(Robots C) {
		//This is the robot laser beam handler
		//Essentially works of the same methods that board does.
		//However in this case the emitter is seen to be Robot
		int dx = C.getDX();
		int dy = C.getDY();
		Boolean hitRobot = false;
		if(dx == 0 && dy == -1) {
			for(int TempY = C.getY() - 1; TempY > -1; TempY--) {
				for(int i = 0; i < board.getCharacters().size(); i++) {
					if(C.getX() == board.getCharacters().get(i).getX() && TempY == board.getCharacters().get(i).getY() && !hitRobot && C.getValue() != board.getCharacters().get(i).getValue()) {
						Robots robots = (Robots) board.getCharacters().get(i);
						hitRobot = true;
		  				robots.setHealth(robots.getHealth() - 1);
	  					board.setIfCharacterHitByLaser(true);		  				
	  					break;
					}
				}
				if(hitRobot) {
					break;
				}
			}
			board.setIfCharacterHitByLaser(false);
		}
		else if(dx == -1 && dy == 0) {
			for(int TempX = C.getX() - 1; TempX > -1; TempX--) {
				for(int i = 0; i < board.getCharacters().size(); i++) {
					if(TempX == board.getCharacters().get(i).getX() && C.getY() == board.getCharacters().get(i).getY() && !hitRobot && C.getValue() != board.getCharacters().get(i).getValue()) {
						Robots robots = (Robots) board.getCharacters().get(i);
						hitRobot = true;
		  				robots.setHealth(robots.getHealth() - 1);
	  					board.setIfCharacterHitByLaser(true);		  				
	  					break;
					}
				}
				if(hitRobot) {
					break;
				}
			}
			board.setIfCharacterHitByLaser(false);

		}
		else if(dx == 1 && dy == 0) {
			for(int TempX = C.getX() + 1; TempX < board.getWidth() -1; TempX++) {
				for(int i = 0; i < board.getCharacters().size(); i++) {
					if(TempX == board.getCharacters().get(i).getX() && C.getY() == board.getCharacters().get(i).getY() && !hitRobot && C.getValue() != board.getCharacters().get(i).getValue()) {
						Robots robots = (Robots) board.getCharacters().get(i);
						hitRobot = true;
		  				robots.setHealth(robots.getHealth() - 1);
						board.setIfCharacterHitByLaser(true);
		  				break;
					}
				}
				if(hitRobot) {
					break;
				}
			}
			board.setIfCharacterHitByLaser(false);
		}
		else if(dx == 0 && dy == 1) {
			for(int TempY = C.getY() + 1; TempY < board.getHeight() - 1; TempY++) {
				for(int i = 0; i < board.getCharacters().size(); i++) {
					if(C.getX() == board.getCharacters().get(i).getX() && TempY == board.getCharacters().get(i).getY() && !hitRobot && C.getValue() != board.getCharacters().get(i).getValue()) {
						Robots robots = (Robots) board.getCharacters().get(i);
						hitRobot = true;
		  				robots.setHealth(robots.getHealth() - 1);
						board.setIfCharacterHitByLaser(true);
		  				break;
					}
				}
				if(hitRobot) {
					break;
				}
			}
			board.setIfCharacterHitByLaser(false);
		}
	}
	
  	/**
	   * Check characters.
	   *
	   * @param C the robot
	   * @param Value the character value
	   * @param isBackwards checks if backwards
	   * @param dx the directional x
	   * @param dy the directional y
	   */
	  /*
  	 * This is used to check collision with Characters.
  	 * This methods calls itself it again to see if the character it had already collided 
  	 * into haven't collided into another character.
  	 * 
  	 * The way it works is that it will continue to call itself. If the collided robot has not
  	 * fallen of the board it may have collided with another robot it may have hit another character so this
  	 * is checked.
  	 */
	public void checkCharacters(Robots C, String Value, Boolean isBackwards, int dx, int dy) {
		for (int i = 0; i < getCharacters().size(); i++) {
			Robots r = (Robots) getCharacters().get(i);
			if (C != r) {
				if(C.getX() == r.getX()) {
					if(C.getY() == r.getY()) {
						int x = r.getX();
						int y = r.getY();
						
						if(dx == 0 && dy == 0) {
							if(!isBackwards) {
								if(x+C.getDX() < 0 || x+C.getDX() > board.getWidth()) {
									board.resetCharacter(r.getValue() + "", r);
								}
								else if(y+C.getDY() < 0 || y+C.getDY() > board.getHeight()) {
									board.resetCharacter(r.getValue() + "", r);
								}
								else {
									board.setGridIcon(x+C.getDX(), y+C.getDY(), r.getValue());
									r.setX(x+C.getDX());																	
									r.setY(y+C.getDY());
									PitCollisions(r, Character.toString(r.getValue()), r.getX(), r.getY(), 0, 0, isBackwards);
									checkCharacters(r, Character.toString(r.getValue()), isBackwards, C.getDX(), C.getDY());
								}
							}
							else {
								if(x-C.getDX() < 1 || x-C.getDX() > board.getWidth()) {
									board.resetCharacter(r.getValue() + "", r);	
								}
								else if(y-C.getDY() < 1 || y-C.getDY() > board.getHeight()) {
									board.resetCharacter(r.getValue() + "", r);
								}
								else {
									board.setGridIcon(x-C.getDX(), y-C.getDY(), r.getValue());
									r.setX(x-C.getDX());
									r.setY(y-C.getDY());
									PitCollisions(r, Character.toString(r.getValue()), r.getX(), r.getY(), 0, 0, isBackwards);
									checkCharacters(r, Character.toString(r.getValue()), isBackwards, C.getDX(), C.getDY());
								}
							}
						}
						
						else {
							if(!isBackwards) {	
								if(x+dx < 0 || x+dx > board.getWidth()) {
									board.resetCharacter(r.getValue() + "", r);
								}
								else if(y+dy < 0 || y+dy > board.getHeight()) {
									board.resetCharacter(r.getValue() + "", r);
								}
								else {
									board.setGridIcon(x+dx, y+dy, r.getValue());
									r.setX(x+dx);
									r.setY(y+dy);
									PitCollisions(r, Character.toString(r.getValue()), r.getX(), r.getY(), 0, 0, isBackwards);
									checkCharacters(r, Character.toString(r.getValue()), isBackwards, dx, dy);
								}

							}
							else {
								if(x-dx < 0 || x-dx > board.getWidth()) {
									board.resetCharacter(r.getValue() + "", r);
								}
								else if(y-dy < 0 || y-dy > board.getHeight()) {
									board.resetCharacter(r.getValue() + "", r);
								}
								else {
									board.setGridIcon(x-dx, y-dy, r.getValue());
									r.setX(x-dx);
									r.setY(y-dy);
									PitCollisions(r, Character.toString(r.getValue()), r.getX(), r.getY(), 0, 0, isBackwards);
									checkCharacters(r, Character.toString(r.getValue()), isBackwards, dx, dy);
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * Forwardsand backwards handler.
	 *
	 * @param C the Robot
	 * @param Value the character Value
	 * @param isBackwards check for if backwards
	 */
	public void ForwardsandBackwardsHandler(Robots C, String Value, Boolean isBackwards) {
		//A set a methods that are called in both backwards and forwards handlers
		board.LabelRotationHandler(C, 0);
		checkCharacters(C, Value, isBackwards, 0, 0);
		board.resetTileIcons();
		board.updateCharacterReactions();
	}
	
	
  	/**
	   * Resets Characters Icons.
	   *
	   * @param C the Robot
	   */
	  /*
  	 * Used to handle locations
  	 */
	public void NonGameWinningLocationHandler(Robots C) {
		board.LabelRotationHandler(C, 0);
		board.resetTileIcons();
		board.updateCharacterReactions();
	}
	
	/**
	 * Laser beams.
	 */
	public void LaserBeams() {
		if(robotUsesLaser) {
			for(int i = 0; i < getCharacters().size(); i++) {
				Robots r = (Robots) getCharacters().get(i);
				RobotLaserBeamHandler(r);
			}
		}
		LaserBeamHandler();
	}

	/**
	 * Gets the checks if is testing.
	 *
	 * @return the checks if is testing
	 */
	public Boolean getIsTesting() {
		return isTesting;
	}

	/**
	 * Sets the checks if is testing.
	 *
	 * @param isTesting the new checks if is testing
	 */
	public void setIsTesting(Boolean isTesting) {
		this.isTesting = isTesting;
	}

	/**
	 * Gets the robot uses laser.
	 *
	 * @return the robot uses laser
	 */
	public Boolean getRobotUsesLaser() {
		return robotUsesLaser;
	}

	/**
	 * Sets the robot uses laser.
	 *
	 * @param robotUsesLaser the new robot uses laser
	 */
	public void setRobotUsesLaser(Boolean robotUsesLaser) {
		this.robotUsesLaser = robotUsesLaser;
	}
}
