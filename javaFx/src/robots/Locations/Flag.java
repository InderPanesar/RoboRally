package robots.Locations;

import robots.Board.MapTile;

/**
 * The Class Flags.
 */
public class Flag extends MapTile {
	
	/** The Flag number. */
	int FlagNumber;

	/**
	 * Instantiates a new flag.
	 *
	 * @param x the X value of the TileMap
	 * @param y the Y value of the TileMap
	 * @param flagNumber the flag number
	 */
	public Flag(int x, int y, char flagNumber, Boolean isTesting) {
		super(x, y, flagNumber, isTesting);
		FlagNumber = flagNumber - '0';
	}
	
	/**
	 * Gets the flag number.
	 *
	 * @return the flag number
	 */
	public int getFlagNumber() {
		return FlagNumber;
	}

}
