package robots.Board;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.scene.control.Label;
import robots.Character.Characters;
import robots.Character.Robots;
import robots.Locations.ConveryorBelt;
import robots.Locations.Flag;
import robots.Locations.Gear;
import robots.Locations.BoardLaser;
import robots.Locations.Pit;
import robots.main.ReadCommands;
import robots.main.ReadFile;

/**
 * the Class Board.
 */
public class Board {
	
	  /** The board. */
	  private char[][] board;
  	
	  /** The tiles. */
	  protected MapTile[][] tiles;
  	
	  /** the Robot Characters. */
	  protected List<Characters> characters;
  	
	  /** the ConveyorBelt file names. */
	  private HashMap<Character, String> ConveryorFileNames = new HashMap<Character, String>();
  	
	  /** the Class for commands. */
	  private ReadCommands commands;
  	
	  /** The read class. */
	  private ReadFile read;
  	
	  /** The laser already hit character. */
	  private Boolean laserAlreadyHitCharacter;
	  
	  /** The is testing. */
  	private Boolean isTesting;
 
  	/**
	   * Instantiates a new board.
	   *
	   * @param fileName the file name
	   * @param selectedBoardFile the selected board file
	   * @param selectedCommandFile the selected command file
	   * @param acceptedBoardValues the accepted board values
	   * @param acceptedUserInputs the accepted user inputs
	   * @param isTest check if a test.
	   */
  	public Board(String fileName, File selectedBoardFile, File selectedCommandFile, char[] acceptedBoardValues, char[] acceptedUserInputs, Boolean isTest) {
  		this.isTesting = isTest;
  		//This if statements are to do separate things depending if we are reading or hot swapping.
  		if(selectedCommandFile != null) {
  			read = new ReadFile(null,selectedBoardFile, acceptedBoardValues, isTest);
  			commands = new ReadCommands(selectedCommandFile, acceptedUserInputs, isTest);
  		}
  		else {
  			read = new ReadFile(fileName,null, acceptedBoardValues, isTest);
  		}
  		//These initialise the values we are going to need
	  	board = read.getBoard();
	  	tiles = new MapTile[read.getWidth()][read.getHeight()];
	  	characters = new ArrayList<Characters>();
	  	//This sets the conveyorBelt FileName Map 
	  	converyorBeltFileNames();
	  	//sets up the IntialTiles
	  	//Has a isTest Parameter as we don't want to print the board when testing
		setTileMap(isTest);
		laserAlreadyHitCharacter = false;
  	}
  	
  	/**
  	   * Converyor Belts have some weird names such as ^ v > <.
  	   * However, this is in place as we cannot have files that are name
  	   * W.png
  	   * w.png
  	   * at the same time.
	   */
	public void converyorBeltFileNames() {
  		ConveryorFileNames.put('N', "N_");
  		ConveryorFileNames.put('S', "S_");
  		ConveryorFileNames.put('W', "W_");
  		ConveryorFileNames.put('E', "E_");
  		ConveryorFileNames.put('n', "n");
  		ConveryorFileNames.put('w', "w");
  		ConveryorFileNames.put('s', "s");
  		ConveryorFileNames.put('e', "e");
  		ConveryorFileNames.put('^', "UP");
  		ConveryorFileNames.put('v', "DOWN");
  		ConveryorFileNames.put('>', "RIGHT");
  		ConveryorFileNames.put('<', "LEFT");
  	}
  
  	/**
	   * This is method is where the MapTile is set up
	   * Each position of the MapTile is initialised and then 
	   * the icons of each MapTile is set up.
	   * It also creates different instances of locations.
	   *
	   * @param isTest the new tile map
	   */
	public void setTileMap(Boolean isTest) {
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				//the below is pretty straightforward, if the board contains character X create new class X
				//since all board values not named '.' should have a class within the program
				if(board[x][y] != '.') {
					tiles[x][y] = new MapTile(x, y, board[x][y], isTest);
					tiles[x][y].setLabel(board[x][y], getHeight(), getWidth());
					if(board[x][y] == 'A' || board[x][y] == 'B' || board[x][y] == 'C' || board[x][y] == 'D') {
						characters.add(new Robots(x, y, board[x][y]));
					}
					else if(ConveryorFileNames.get(board[x][y]) != null) {
						tiles[x][y] = new ConveryorBelt(x, y, board[x][y], isTest);
						tiles[x][y].setLabel(ConveryorFileNames.get(board[x][y]), getHeight(), getWidth());
					}
					else if(board[x][y] == '+' || board[x][y] == '-') {
						tiles[x][y] = new Gear(x, y, board[x][y], isTest);
						tiles[x][y].setLabel(board[x][y], getHeight(), getWidth());
					}
					else if(board[x][y] == 'x') {
						tiles[x][y] = new Pit(x, y, board[x][y], isTest);
						tiles[x][y].setLabel(board[x][y], getHeight(), getWidth());
					}
					else if(Character.isDigit(board[x][y])) {
						tiles[x][y] = new Flag(x, y, board[x][y], isTest);
						tiles[x][y].setLabel(board[x][y], getHeight(), getWidth());
					}
					else if (board[x][y] == '(' || board[x][y] == '[' ) {			
						if(board[x][y] == '(') {
							tiles[x][y] = new BoardLaser(x, y, board[x][y], isTest ,false, true, true);
							tiles[x][y].setLabel(board[x][y], getHeight(), getWidth());
						}
						else {
							tiles[x][y] = new BoardLaser(x, y, board[x][y], isTest, false, true, false);
							tiles[x][y].setLabel(board[x][y], getHeight(), getWidth());
						}

					}
					else if (board[x][y] == ')' || board[x][y] == ']' ) {
						if(board[x][y] == ')') {
							tiles[x][y] = new BoardLaser(x, y, board[x][y], isTest, true, false, true);
							tiles[x][y].setLabel(board[x][y], getHeight(), getWidth());
						}
						else {
							tiles[x][y] = new BoardLaser(x, y, board[x][y], isTest, true, false, false);
							tiles[x][y].setLabel(board[x][y], getHeight(), getWidth());
						}
					}
				}
				//However, if it is an empty space we just initialise a normal MapTile
				else {
					tiles[x][y] = new MapTile(x, y, '.', isTest);
					tiles[x][y].setLabelText(" ");
				}
			}
		}
		System.out.println("Intial Board: ");
		printBoard();
	}
	
	
	// Where ever there is a if(!isTesting) statement this is something we don't want to call
	// when running the test.
  	/**
	   * Update grid icon.
	   *
	   * @param newX The new Tile X value
	   * @param newY The new Tile Y value
	   * @param oldX The old Tile X value
	   * @param oldY The old Tile Y value
	   * @param Value The Robot Value
	   */
	  /*
  	 * This is used when we want to update a icon, since the Tiles on the board
  	 * can be updated from here
  	 */
	public void updateGridIcon(int newX, int newY, int oldX, int oldY, char Value) {
		if(!isTesting) {
			tiles[newX][newY].setLabel(Value, getHeight(), getWidth());
			tiles[oldX][oldY].setLabelText(" ");
			tiles[oldX][oldY].setLabelNull();
		}
	}
	
  	/**
	   * Sets the grid icon.
	   *
	   * @param newX The new Tile X value
	   * @param newY The new Tile Y value
	   * @param Value The Robot Value
	   */
	public void setGridIcon(int newX, int newY, char Value) {
		//Used to setGridIcon on gridPane
		if(!isTesting) {
			tiles[newX][newY].setLabel(Value, getHeight(), getWidth());
		}
	}
	
  	/**
	   * Gets the Robot.
	   *
	   * @param value The Character Value
	   * @return The Robot 
	   */
	public Robots getCharacters(char value) {
		// Gets a specific character/robot that is needed
		for (int i = 0; i < getCharacters().size(); i++) {
			Robots r = (Robots) getCharacters().get(i);
			if (value == r.getValue()) {
				return r;
			}
		}
		return null;
	}
	
	
	/**
	 * Update character reactions.
	 */
	public void updateCharacterReactions() {
		//Updates the icons of the characters.
		if(!isTesting) {
			for (int y = 0; y < characters.size(); y++) {
				Robots R = (Robots) characters.get(y);
				setGridIcon(R.getX(), R.getY(), R.getValue());
				LabelRotationHandler(R, 0);
			}
		}
	}

	
  	/**
	   * Sets/Resets the Robot Icon.
	   */
	public void setCharacterReactions() {
		//sets the reaction of the board, and does the same thing as above.
		if(!isTesting) {
			for (int y = 0; y < characters.size(); y++) {
				Robots R = (Robots) characters.get(y);
				setGridIcon(R.getX(), R.getY(), R.getValue());
				LabelRotationHandler(R, 0);
			}
		}
	}
	
  	/**
	   * Reset tile icons.
	   */
	public void resetTileIcons() {
		//Called after every turn to make sure that the board doesn't contain repeat values.
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) { 
				MapTile r = tiles[x][y];
				if( r instanceof Flag || r instanceof Gear || r instanceof BoardLaser || r instanceof Pit) {
					if(!isTesting) {
						r.getTile().setRotate(0);
					}
					r.setLabel(board[x][y], read.getHeight(), read.getWidth());
					r.setValue(board[x][y]);
				}
				else if (r instanceof ConveryorBelt ) {
					if(!isTesting) {
						r.getTile().setRotate(0);
					}
					r.setLabel(ConveryorFileNames.get(board[x][y]), read.getHeight(), read.getWidth());
					r.setValue(board[x][y]);
				}
				else {
					r.setValue('.');
				}
			}
		}
	}
	
	/**
	 * Checks if is there A tile.
	 *
	 * @param x the x
	 * @param y the y
	 * @return the boolean
	 */
	public Boolean isThereATile(int x, int y) {
		//Checks if there is a location on this tile
		MapTile r = tiles[x][y];
		if( r instanceof Flag || r instanceof Gear || r instanceof BoardLaser || r instanceof Pit || r instanceof ConveryorBelt) {
			return true;
		}
		return false;
		
	}
	
	/**
	 * Check if character is on tile.
	 *
	 * @param Value The Robot Character
	 * @param x The X Value of the Robot
	 * @param y The Y Value of the Robot
	 * @return If Character is on tile.
	 */
	public Boolean checkIfCharacterIsOnTile(char Value, int x, int y) {	
		//Checks if there is a character on this file by going through list of characters
		for(int i = 0; i < characters.size(); i++) {
			if(Value != characters.get(i).getValue()) {
				if(x == characters.get(i).getX()) {
					if(y == characters.get(i).getY()) {
						return true;
					}
				}
			}
		}
		return false;
		
	}
	
	
  	/**
	   * Reset character.
	   *
	   * @param Value the value
	   * @param C the Robot affected
	   */
	public void resetCharacter(String Value, Robots C) {
		//This resets the character
		Boolean CharacterHit = false;
		int dx = 0;
		int dy = 0;
		//Checks that character isn't already on intial tile
		if(!checkIfCharacterIsOnTile(C.getValue(), C.getIntialX(), C.getIntialY())) {
			setGridIcon(C.getIntialX(), C.getIntialY(), C.getValue());
			C.setX(C.getIntialX());
			C.setY(C.getIntialY());
			C.setRotation(0);
			C.setDXDY(0, -1);
			LabelRotationHandler(C, 0);
			resetTileIcons();
		}			
		else {
		//continues to check N, E, S, W respectively.
			if(C.getIntialY() - 1 > 0 && !CharacterHit && !isThereATile(C.getIntialX(), C.getIntialY()-1) ) {
				if(!checkIfCharacterIsOnTile(C.getValue(), C.getIntialX(), C.getIntialY()-1)) {
					dx = 0;
					dy = -1;
					CharacterHit = true;
				}
			}
			if(C.getIntialX() + 1 < read.getWidth() && !isThereATile(C.getIntialX()+1, C.getIntialY()) ) {
				if(!checkIfCharacterIsOnTile(C.getValue(), C.getIntialX()+1, C.getIntialY())) {
					dx = 1;
					dy = 0;
					CharacterHit = true;
				}
			}
			if(C.getIntialY() + 1 < read.getHeight() && !isThereATile(C.getIntialX(), C.getIntialY()+1) ) {
				if(!checkIfCharacterIsOnTile(C.getValue(), C.getIntialX(), C.getIntialY()+1)) {
					dx = 0;
					dy = 1;
					CharacterHit = true;
				}
			}
			if(C.getIntialX() - 1 > 0 && !isThereATile(C.getIntialX()-1, C.getIntialY()) ) {
				if(!checkIfCharacterIsOnTile(C.getValue(), C.getIntialX()-1, C.getIntialY())) {
					dx = -1;
					dy = 0;
					CharacterHit = true;
				}
			}
			
			setGridIcon(C.getIntialX()+dx, C.getIntialY()+dy, C.getValue());
			C.setX(C.getIntialX() + dx);
			C.setY(C.getIntialY() + dy);
			C.setRotation(0);
			C.setDXDY(0, -1);
			LabelRotationHandler(C, 0);
			resetTileIcons();
		}
		
	}
	
	/**
	   * Removes the icon.
	   *
	   * @param x the x
	   * @param y the y
	   */
	  public void removeIcon(int x, int y) {
		setGridIcon(x, y, ' ');
	}
	
	
  	/**
	   * Label rotation handler.
	   *
	   * @param C the Robot affected
	   * @param degrees the degrees
	   */
	public void LabelRotationHandler(Robots C, int degrees) {
		//Used when rotating a label
		if(!isTesting) {
			MapTile t = tiles[C.getX()][C.getY()];
			Label j = t.getTile();
			//rotates the MapTile Label
			j.setRotate(C.getRotation() + degrees);
		}
	}
	

  	/**
	   * This is movement used when U is entered.
  	   * As this will turn the robot 180 degrees opposite.
  	   * It updates the robots DX and DY values.
  	   * We update the DX DY as we use F to move forward
  	   * and B to move backwards
	   *
	   * @param C the Robot affected
	   */
	public void uTurnHandler(Robots C) {
		// Used to set the direction of the robots if they do a u turn
		if(C.getDX() == 0 && C.getDY() == -1) {
			C.setDXDY(0, 1);
		}
		else if (C.getDX() == 1 && C.getDY() == 0) {
			C.setDXDY(-1, 0);
		}
		else if (C.getDX() == 0 && C.getDY() == 1) {
			C.setDXDY(0, -1);
		}
		else {
			C.setDXDY(1, 0);
		}
	}
	
  	/**
	   * This is movement used when L is entered.
  	   * (anticlockwise)
  	   * as this will turn the robot left.
  	   * This will be called on a corner ConveyorBelt.
  	   * As well as gears (-).
  	   * it updates the robots DX and DY values.
  	   * We update the DX DY as we use F to move forward
  	   * and B to move backwards
	   *
	   * @param C the Robot affected
	   */
	public void anticlockwiseTurnHandler(Robots C) {
		// Used to set the directional of the robots if they do an anticlockwise turn
		if(C.getDX() == 0 && C.getDY() == -1) {
			C.setDXDY(-1, 0);
		}
		else if (C.getDX() == -1 && C.getDY() == 0) {
			C.setDXDY(0, 1);
		}
		else if (C.getDX() == 0 && C.getDY() == +1) {
			C.setDXDY(1, 0);
		}
		else {
			C.setDXDY(0, -1);
		}
	}
	
  	/**
  	   * This is movement used when R is entered.
  	   * (clockwise)
  	   * as this will turn the robot right.
  	   * this will be called on a corner ConveyorBelt.
  	   * As well as gears (+).
  	   * it updates the robots DX and DY values.
  	   * We update the DX DY as we use F to move forward
  	   * and B to move backwards
	   *
	   * @param C the Robot affected
	   */
	public void clockwiseTurnHandler(Robots C) {
		// Used to set the directional of the robots if they do an clockwise turn
		if(C.getDX() == 0 && C.getDY() == -1) {
			C.setDXDY(1, 0);
		}
		else if (C.getDX() == 1 && C.getDY() == 0) {
			C.setDXDY(0, 1);
		}
		else if (C.getDX() == 0 && C.getDY() == 1) {
			C.setDXDY(-1, 0);
		}
		else {
			C.setDXDY(0, -1);
		}
	}
	
  
  	/**
	   * Gets the Robot affected characters.
	   *
	   * @return the Robot affected characters
	   */
  	public List<Characters> getCharacters() {
  		return characters;
  	}
  	
  	/**
	   * Sets the characters.
	   *
	   * @param robots the new characters
	   */
	  public void setCharacters(List<Characters> robots) {
  		characters = robots ;
  	}
  	
  	/**
	   * Gets the if character hit by laser.
	   *
	   * @return the if character hit by laser
	   */
	  public Boolean getIfCharacterHitByLaser() {
  		return laserAlreadyHitCharacter;
  	}
  	
  	/**
	   * Sets the if character hit by laser.
	   *
	   * @param value if character hit by laser
	   */
	  public void setIfCharacterHitByLaser(boolean value) {
  		laserAlreadyHitCharacter = value;
  	}
  	
  	/**
	   * Gets the Robot character number.
	   *
	   * @return the Robot character number
	   */
	  public int getCharacterNumber() {
  		return read.getCharacterNumber();
  	}
  	
  	/**
	   * Gets the width.
	   *
	   * @return the width
	   */
	  public int getWidth() {
	  	return read.getWidth();
  	}
  
  	/**
	   * Gets the height.
	   *
	   * @return the height
	   */
	  public int getHeight() {
	  	return read.getHeight();
  	}
  	
	/**
	 * Gets the flag number.
	 *
	 * @return the flag number
	 */
	public int getFlagNumber() {
		return read.getFlagNumber();
	}

	/**
	 * Gets the commands.
	 *
	 * @return the commands
	 */
	public ReadCommands getCommands() {
		return commands;
	}
	
	/**
	 * Hash map vertical.
	 *
	 * @return the hash map
	 */
	public HashMap<String, Boolean> HashMapVertical() {
		return read.HashMapVertical();
	}
	
	
	/**
	 * Hash map horizontal.
	 *
	 * @return the hash map
	 */
	public HashMap<String, Boolean> HashMapHorizontal() {
		return read.HashMapHorizontal();
	}
	
	/**
	 * Prints the board.
	 */
	public void printBoard() {
		System.out.println(" ");
		for (int y = 0; y < getHeight(); y++) {
			for (int x = 0; x < getWidth(); x++) {
				Boolean hitCharacter = false;
				for(int i = 0; i < characters.size(); i++) {
					Robots r = (Robots) characters.get(i);
					if(r.getX() == x && r.getY() == y) {
						System.out.print(" [ " + r.getValue() + " ] ");
						hitCharacter = true;
					}
				}
				if(!hitCharacter) {
					System.out.print(" [ " +tiles[x][y].getValue()+ " ] ");
				}

			}
			System.out.println("");
		}
		System.out.println(" ");
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

  
}