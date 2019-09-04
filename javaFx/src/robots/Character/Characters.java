package robots.Character;

/**
 * The Interface Characters.
 */
public interface Characters {
  	/*
  	 * This interface is here so if we wanted to add more
  	 * Movable characters in the future it would be easier
  	 * to do so. This allows us to add many characters in the future, such
  	 * as roaming robots which destroy the player robots for example.
  	 */
	
	/**
     * Sets the x.
     *
     * @param x the new x
     */
	public void setX(int x);
	
	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y);
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX();
	
	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY();
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public char getValue();

}
