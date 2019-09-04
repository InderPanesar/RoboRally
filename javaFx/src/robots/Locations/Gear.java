package robots.Locations;

import robots.Board.MapTile;

/**
 * The Class Gears.
 */
public class Gear extends MapTile {

	/** The Gear value. */
	private char value;
	
	/**
	 * Instantiates a new gears.
	 *
	 * @param x the X value of the TileMap
	 * @param y the Y value of the TileMap
	 * @param c the character of the Gear
	 */
	public Gear(int x, int y, char c, Boolean isTesting) {
		super(x, y, c, isTesting);
		this.value = c;
	}
	
	/**
	 * Gets the Gear value.
	 *
	 * @return the Gear Value
	 */
	public char getValue() {
		return value;
	}

}
