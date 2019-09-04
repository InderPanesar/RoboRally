package robots.Locations;

import robots.Board.MapTile;

/**
 * The Class BoardLaser.
 */
public class BoardLaser extends MapTile {
	
	/** Checks that the Laser is Reciever. */
	private Boolean isReciever;
	
	/** Checks that the Laser is Emitter. */
	private Boolean isEmitter;
	
	/** Checks that the Laser is Vertical. */
	private Boolean isVertical;
	

	/**
	 * Instantiates a new board laser.
	 *
	 * @param x the x
	 * @param y the y
	 * @param isReciever checks if is Reciever
	 * @param isEmitter checks if is Emitter
	 * @param isVertical checks if is vertical
	 */
	public BoardLaser(int x, int y, char value, Boolean isTesting, Boolean isReciever, Boolean isEmitter, Boolean isVertical) {
		super(x, y, value, isTesting);
		this.isEmitter = isEmitter;
		this.isReciever = isReciever;
		this.isVertical = isVertical;
	}

	/**
	 * Checks if is reciever.
	 *
	 * @return If is reciever
	 */
	public Boolean getIsReciever() {
		return isReciever;
	}

	/**
	 * Checks if is vertical.
	 *
	 * @return If is vertical
	 */
	public Boolean getIsVertical() {
		return isVertical;
	}
	
	/**
	 * Checks if is emitter.
	 *
	 * @return If is emitter
	 */
	public Boolean getIsEmitter() {
		return isEmitter;
	}


}
