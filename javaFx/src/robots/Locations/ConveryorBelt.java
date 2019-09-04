package robots.Locations;

import robots.Board.MapTile;

/**
 * The Class ConveryorBelt.
 */
public class ConveryorBelt extends MapTile {
	
	/** The letter. */
	private char letter;

	/**
	 * Instantiates a new converyor belt.
	 *
	 * @param x the X value of the TileMap
	 * @param y the Y value of the TileMap
	 * @param value the value
	 */
	public ConveryorBelt(int x, int y, char value, Boolean isTesting) {
		super(x, y, value, isTesting);
		this.letter = value;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public char getValue() {
		return letter;
	}


}
