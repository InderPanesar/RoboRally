package robots.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * The Class ReadFile.
 */
public class ReadFile {
	
	/** The grid width. */
	private int width;
	
	/** The grid height. */
	private int height;
	
	/** The board values. */
	private char[][] boardValues;
	
	/** The Flag number. */
	private int FlagNumber;
	
	/** The Character number. */
	private int CharacterNumber;
    
    /** The Laser check horizontally. To make sure all lasers are used. */
    private HashMap<String, Boolean> LaserCheckHorizontal;
    
    /** The Laser check vertically. To make sure all lasers are used.  */
    private HashMap<String, Boolean> LaserCheckVertical;
    
    /** The Number of emitters. */
    private int NumberOfEmitters;
    
    /** The Number of recievers. */
    private int NumberOfRecievers;
    
    /** The is used to check if testing. */
    private Boolean isTesting;
    
    /** If testing error exist. */
    private Boolean testingErrorExist;

	
	/*
	 * Below we initialise the variable height as 0
	 * we add the return value from readHandler to Lines
	 * we check the width by checking the first line since they should
	 * all be the same width
	 * We then initialise boardValues with the Height and Width values we have.
	 * Then we go through each line in the array and convert them to characters
	 * Using the method ArrayHandler
	 * heightPointer is the height Value since we read a new line each time
	 */

	/**
	 * Instantiates a new read file.
	 *
	 * @param FileName the file name
	 * @param selectedBoardFile the selected board file
	 * @param acceptedBoardValues the accepted board values
	 * @param isTesting the is testing
	 */
	public ReadFile(String FileName, File selectedBoardFile, char[] acceptedBoardValues, Boolean isTesting) {
		this.testingErrorExist = false;
		this.isTesting = isTesting;
		this.height = 0;
		this.NumberOfEmitters = 0;
		this.NumberOfRecievers = 0;
		this.LaserCheckHorizontal = new HashMap<String, Boolean>();
		this.LaserCheckVertical = new HashMap<String, Boolean>();
		ArrayList<String> Lines = null;
		if(selectedBoardFile != null) {
			Lines = readHandlerChosen(selectedBoardFile.getAbsoluteFile());
		}
		else {
			Lines = readHandler(FileName);
		}
		if(Lines.size() == 0) {
			alertHandler("BoardFiles is empty.");
		}
		else {
			this.width = Lines.get(0).length();
			this.boardValues = new char[width][height];
			
			int heightPointer = 0;
			for (int i = 0; i < Lines.size(); i++) {
				if(Lines.get(i).length() != width) {
					alertHandler("There are values missing in the readFile");
				}
				else {
					heightPointer = arrayHandler(Lines.get(i), heightPointer, acceptedBoardValues);
				}
			}
			
			if(NumberOfRecievers != NumberOfEmitters) {
				alertHandler("There is not an Emitter for every Reciever, in .brd file!");
			}
		}


	} 
	

	
	/**
	 * Here the lines are just converted into characters
	 * since each point tile in the array is shown by one character
	 * It takes the string and chooses the string at the position given to it
	 * so for example if we have the string: Harry
	 * and we did --> string.charAt(0);
	 * it would return H
	 * We also add one value to the temporary heightPointer
	 *
	 * @param Error the error described
	 */
	private void alertHandler(String Error) {
		if(!isTesting) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Board File Error");
			alert.setHeaderText("Board File Error");
			alert.setContentText(Error);
			alert.showAndWait();
			System.exit(0);
		}
		else {
			testingErrorExist = true;
		}
	}

	/**
	 * Array handler.
	 *
	 * @param string the line within the readFile
	 * @param height the length of ReadFile 
	 * @param acceptedBoardValues the accepted board values
	 * @return the line its reading on.
	 */
	private int arrayHandler(String string, int height, char[] acceptedBoardValues) {
		int heightPointer = height;
		for (int i = 0; i < width ; i++) {
			boardValues[i][heightPointer] = string.charAt(i);
			if((string.charAt(i)+"").matches("-?\\d+")) {
				FlagNumber++;
			}
			if(string.charAt(i) == 'A' || string.charAt(i) == 'B' || string.charAt(i) == 'C' || string.charAt(i) == 'D') {
				CharacterNumber++;
			}
			if(string.charAt(i) == '(' ) {
				LaserCheckVertical.put(i+""+heightPointer, false);
				NumberOfEmitters++;
			}
			if(string.charAt(i) == '[' ) {
				LaserCheckHorizontal.put(i+""+heightPointer, false);
				NumberOfEmitters++;
			}
			if(string.charAt(i) == ')' ) {
				NumberOfRecievers++;
			}
			if(string.charAt(i) == ']' ) {
				NumberOfRecievers++;
			}
			if (!(new String(acceptedBoardValues).contains(string.charAt(i) + ""))) {
				alertHandler("Incorrect Board Character : " + string.charAt(i));
			}
		}
		heightPointer++;
		return heightPointer;
	}
	
	
	/**
	 * Below the line is checked to see if it contains format
	 * if it does then it confirms it says format 1
	 * otherwise it assumes it is a line and adds it to the line.
	 * By doing this it also adds 1 to the int value height
	 * and adding the line to the list.
	 *
	 * @param FileName the file name
	 * @return the array list
	 */
	private ArrayList<String> readHandler(String FileName) {
		ArrayList<String> Lines = new ArrayList<String>();
		BufferedReader br;
		String line;
		int lineNumber = 0;
		try {
			br = new BufferedReader(new FileReader(new File(FileName)));
			while ((line = br.readLine()) != null) {
				if (lineNumber == 0) {
					if (line.toLowerCase().contains("format")) {
						if(!(line.equals("format 1"))) {
							alertHandler("Format Number is incorrect.");
						}
					}
					else {
						alertHandler("Format Number missing.");
					}
				}
				
				else if (!line.equals(null)) {
					Lines.add(line);
					height++;
				}
				lineNumber++;
			}
		} catch (IOException e) {
			alertHandler("TextFile doesn't exist");
		}
		return Lines;
	}
	
	/**
	 * Below the line is checked to see if it contains format
	 * if it does then it confirms it says format 1
	 * otherwise it assumes it is a line and adds it to the line.
	 * By doing this it also adds 1 to the int value height
	 * and adding the line to the list.
	 * This method is used when we already have the fileName
	 *
	 * @param FileName the file name
	 * @return the array list of file lines,
	 */
	private ArrayList<String> readHandlerChosen(File FileName) {
		System.out.println(FileName);
		ArrayList<String> Lines = new ArrayList<String>();
		BufferedReader br;
		String line;
		int lineNumber = 0;
		try {
			br = new BufferedReader(new FileReader(FileName));
			while ((line = br.readLine()) != null) {
				if (lineNumber == 0) {
					if (line.toLowerCase().contains("format")) {
						if(!(line.equals("format 1"))) {
							alertHandler("Format Number is incorrect.");
						}
					}
					else {
						alertHandler("Format Number missing.");
					}
				}
				
				else if (!line.equals(null)) {
					Lines.add(line);
					height++;
				}
				lineNumber++;
			}
		} catch (IOException e) {
			alertHandler("TextFile doesn't exist");
		}
		return Lines;
	}
	
	/**
	 * Gets the flag number.
	 *
	 * @return the flag number
	 */
	/*
	 * Just some simple return methods
	 */
	public int getFlagNumber() {
		return FlagNumber;
	}
	
	/**
	 * Gets the character number.
	 *
	 * @return the character number
	 */
	public int getCharacterNumber() {
		return CharacterNumber;
	}
	
	/**
	 * Gets the height.
	 *
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * Gets the width.
	 *
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Gets the board.
	 *
	 * @return the board
	 */
	public char[][] getBoard() {
		return boardValues;
	}
	
	/**
	 * Hash map vertical.
	 *
	 * @return the hash map
	 */
	public HashMap<String, Boolean> HashMapVertical() {
		return LaserCheckVertical;
	}
	
	/**
	 * Hash map horizontal.
	 *
	 * @return the hash map
	 */
	public HashMap<String, Boolean> HashMapHorizontal() {
		return LaserCheckHorizontal;
	}



	/**
	 * Gets the testing error exist.
	 *
	 * @return the testing error exist
	 */
	public Boolean getTestingErrorExist() {
		return testingErrorExist;
	}



	/**
	 * Sets the testing error exist.
	 *
	 * @param testingErrorExist the new testing error exist
	 */
	public void setTestingErrorExist(Boolean testingErrorExist) {
		this.testingErrorExist = testingErrorExist;
	}


}