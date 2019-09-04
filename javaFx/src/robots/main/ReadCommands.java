package robots.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * The Class ReadCommands.
 */
public class ReadCommands {

	/** The a queue. */
	private Queue<String> A = new LinkedList<String>();
	
	/** The b queue. */
	private Queue<String> B = new LinkedList<String>();
	
	/** The c queue. */
	private Queue<String> C = new LinkedList<String>();
	
	/** The d queue. */
	private Queue<String> D = new LinkedList<String>();
	
	/** The Name A. */
	//This contains all the robot names.
	private String NameA = "";
	
	/** The Name B. */
	private String NameB = "";
	
	/** The Name C. */
	private String NameC = "";
	
	/** The Name D. */
	private String NameD = "";
	
	/** The accepted user inputs. */
	private static char[] acceptedUserInputs;
	
	/** The check if testing. */
	private Boolean checkIfTesting;
	
	/** The testing error has occurred. */
	private Boolean testingErrorHasOccurred;
	
	/** The check for repeats. */
	private String checkForRepeats = null;

	
	/**
	 * Instantiates a new read commands.
	 *
	 * @param selectedCommandFile the selected command file
	 * @param acceptedInputs the accepted inputs
	 * @param isTesting the is testing
	 */
	public ReadCommands(File selectedCommandFile, char[] acceptedInputs, Boolean isTesting) {
		ReadCommands.acceptedUserInputs = acceptedInputs;
		checkIfTesting = isTesting;
		setTestingErrorHasOccurred(false);
		BufferedReader br = null;
		String st; 
		//Reads all the lines and stores them in the arraylist
		ArrayList<String> lines = new ArrayList<String>();
		try {
			br = new BufferedReader(new FileReader(selectedCommandFile));
		} catch (FileNotFoundException e1) {
			alertHandler("File Error", "Command file doesn't exist or was not able to be found.");
		}
		
		try {
			while ((st = br.readLine()) != null)  {
				lines.add(st);
			}
		} catch (IOException e) {
			alertHandler("FileRead Error", "Command read file is incorrect.");
		} 
		
		
		if(lines.size() > 2) {
			stringValue(lines);
			
			if(A.size() == 0) {
				alertHandler("FileRead Error", "Command file doesn't contain commands");
			}
			else {
				if(A.size()%5 != 0) {
					alertHandler("FileRead Error", "Missing commands in A");
				}
				if(B.size()%5 != 0) {
					alertHandler("FileRead Error", "Missing commands in B");
				}
				if(C.size()%5 != 0) {
					alertHandler("FileRead Error", "Missing commands in C");
				}
				if(D.size()%5 != 0) {
					alertHandler("FileRead Error", "Missing commands in D");
				}
			}
			
			if(A.size() > 1 && B.size() > 1 && C.size() == 0 && D.size() == 0) {
				if(A.size() != B.size()) {
					alertHandler("FileRead Error", "Commands are not equal.");
				}
			}
			else if(A.size() > 1 && B.size() > 1 && C.size() > 1 && D.size() == 0) {
				if(A.size() != B.size() || A.size() != C.size() || C.size() != B.size()) {
					alertHandler("FileRead Error", "Command are not equal");
				}
			}
			else if(A.size() > 1 && B.size() > 1 && C.size() > 1 && D.size() > 1) {
				if(A.size() != B.size() || A.size() != C.size() || C.size() != B.size()) {
					alertHandler("FileRead Error", "Command are not equal.");
				}
				else if(D.size() != A.size() || D.size() != B.size() || D.size() != C.size()) {
					alertHandler("FileRead Error", "Command are not equal.");
				}
			}
		}
		else {
			alertHandler("FileRead Error", "An Empty TextFile.");
		}
		

	
	}
	
	/**
	 * Alert handler.
	 *
	 * @param ErrorType the error type
	 * @param Name the name
	 */
	private void alertHandler(String ErrorType, String Name) {
		if(!checkIfTesting) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle(ErrorType);
			alert.setHeaderText(ErrorType);
			alert.setContentText(Name);
			alert.showAndWait();
			System.exit(0);
		}
		else {
			setTestingErrorHasOccurred(true);
		}
	}
	
	/**
	 * String value.
	 *
	 * @param lines the lines within readFile
	 * @return the int of number of names
	 */
	public int stringValue(ArrayList<String> lines) {
		if(!(lines.get(0).equals("format 1"))) {
			alertHandler("File Error", "Incorrect file format. (Line 1)");
		}
		int names = getNames(lines.get(1));
		for(int i = 2; i < lines.size(); i++) {
			setCommands(lines.get(i));
		}
		return names;
		
	}
	
	/**
	 * This entire class takes all the lines and separates them.
	 * and sets the command values.
	 *
	 * @param c the readFile line
	 */
	public void setCommands(String c) {
		int name = 1;
		String tempChar = null;
		for (int i = 0; i < c.length(); i++){
			if(c.charAt(i) != ' ') {
				if ((new String(acceptedUserInputs).contains(c.charAt(i) + ""))) {
					setQueueHandler(name, c.charAt(i));
					tempChar = null;
				}
				else {
					alertHandler("Incorrect Command Value", c.charAt(i) + " is not a accepted user value.");
				}
				
				if(checkForRepeats == null) {
					checkForRepeats = c.charAt(i) + "" + name;
				}
				else {
					if(checkForRepeats.equals(c.charAt(i) + "" + name)) {
						alertHandler("Command Error", "Duplicated Commands");
					}
					else {
						checkForRepeats = c.charAt(i) + "" + name;
					}
				}
			}
			if(tempChar == null && c.charAt(i) == ' ') {
				tempChar = " ";
				name++;
			}
		}
	}
	
	/**
	 * Sets the queues.
	 *
	 * @param name the number of the deciding number to choose the name
	 * @param c the is the read value
	 */
	public void setQueueHandler(int name, char c) {
		if(name == 1) {
			A.add(""+c);
		}
		if(name == 2) {
			B.add(""+c);
		}
		if(name == 3) {
			C.add(""+c);		
		}
		if(name == 4) {
			D.add(""+c);
		}
	}

	/**
	 * Gets the names.
	 *
	 * @param c the c
	 * @return the names
	 */
	public int getNames(String c) {
		int name = 1;
		String tempChar = null;
		for (int i = 0; i < c.length(); i++){
			if(c.charAt(i) != ' ') {
				setNameHandler(name, c.charAt(i));
				tempChar = null;
			}
			if(tempChar == null && c.charAt(i) == ' ') {
				tempChar = " ";
				name++;
			}
		}
		return name;
	}
	
	/**
	 * Sets the name handler.
	 *
	 * @param name the name
	 * @param c the c
	 */
	public void setNameHandler(int name, char c) {
		if(name == 1) {
			NameA = NameA + c;
		}
		if(name == 2) {
			NameB = NameB + c;
		}
		if(name == 3) {
			NameC = NameC + c;
		}
		if(name == 4) {
			NameD = NameD + c;
		}
	}

	/*
	 * The next 4 get there respective queues.
	 * and then the next 4 get there respective names.
	 */

	
	/**
	 * A queue.
	 *
	 * @return the queue
	 */
	public Queue<String> AQueue() {
		return A;
	}
	
	/**
	 * B queue.
	 *
	 * @return the queue
	 */
	public Queue<String> BQueue() {
		return B;
	}
	
	/**
	 * C queue.
	 *
	 * @return the queue
	 */
	public Queue<String> CQueue() {
		return C;
	}
	
	/**
	 * D queue.
	 *
	 * @return the queue
	 */
	public Queue<String> DQueue() {
		return D;
	}
	
	/**
	 * Name A.
	 *
	 * @return the string
	 */
	public String NameA() {
		return NameA;
	}
	
	/**
	 * Name B.
	 *
	 * @return the string
	 */
	public String NameB() {
		return NameB;
	}
	
	/**
	 * Name C.
	 *
	 * @return the string
	 */
	public String NameC() {
		return NameC;
	}
	
	/**
	 * Name D.
	 *
	 * @return the string
	 */
	public String NameD() {
		return NameD;
	}

	/**
	 * Gets the testing error has occurred.
	 *
	 * @return the testing error has occurred
	 */
	public Boolean getTestingErrorHasOccurred() {
		return testingErrorHasOccurred;
	}

	/**
	 * Sets the testing error has occurred.
	 *
	 * @param testingErrorHasOccurred the new testing error has occurred
	 */
	public void setTestingErrorHasOccurred(Boolean testingErrorHasOccurred) {
		this.testingErrorHasOccurred = testingErrorHasOccurred;
	}

	

}
