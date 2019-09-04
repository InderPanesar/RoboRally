package robots.Board;

/**
 * The Class Program.
 */
public class Program {
	
	
	/** The accepted inputs. */
	  private char[] acceptedInputs;
	
  	/**
	   * Instantiates a new program.
	   *
	   * @param acceptedInputs the accepted inputs
	   */
	public Program (char[] acceptedInputs) {
		this.acceptedInputs = acceptedInputs;
	}

  	/**
  	   * This checks the values for errors.
  	   * It first checks that the values are part of the acceptedInputs
  	   * and then it also checks if Values are repeated.
	   *
	   * @param Command the command from user of fileInput.
	   * @return the boolean
	   */
	public Boolean checkValue(String Command) {
		String valuesUpper = Command.toUpperCase();
		String[] values = valuesUpper.split("");
		for (int i = 0; i < values.length; i++) {
			if (!(new String(acceptedInputs).contains(values[i]))) {
			    return false;
			}
		}
		
		for (int i = 0; i < (values.length -1); i++) {
			if (values[i].contentEquals(values[i+1])) {
				return false;
			}
		}
		return true;

	}
	
}
