package robots.Character;

/**
 * The Class Robots.
 */
public class Robots implements Characters {
	
	/** The intial X. */
	private int intialX;
	
	/** The intial Y. */
	private int intialY;
	
	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/** The Robot character value. */
	private char Value;
	
	/** The directional x. */
	protected int dx;
	
	/** The directional y. */
	protected int dy;
	
	/** The Rotation degrees. */
	private int RotationDegrees;
	
	/** The Flag achieved. */
	private int FlagAchieved;
	
	/** The health. */
	private int health;
	
	/**
	 * Instantiates a new robots.
	 *
	 * @param x the x
	 * @param y the y
	 * @param value the value
	 */
	/*
	 * This initialises all of the robots values.
	 */
	public Robots(int x, int y, char value) {
		this.intialX = x;
		this.intialY = y;
		this.x = x;
		this.y = y;
		this.Value = value;
		this.dx = 0;
		this.dy = -1;
		this.RotationDegrees = 0;
		this.FlagAchieved = 0;
		this.setHealth(5);
	}
	
	
	/**
	 * Gets the flags retrieved.
	 *
	 * @return the flags retrieved
	 */
	public int getFlagsRetrieved() {	
		return FlagAchieved;
	}
	
	/**
	 * Sets the flags retrieved.
	 *
	 * @param flags the new flags retrieved
	 */
	public void setFlagsRetrieved(int flags) {
		FlagAchieved = flags;
	}
	
	/**
	 * Gets the rotation.
	 *
	 * @return the rotation
	 */
	public int getRotation() {	
		return RotationDegrees;
	}
	
	/**
	 * Sets the rotation.
	 * Contains %360 which is modulus 360
	 * this will divide the value and check the remainder.
	 *
	 * @param RotationDegrees the new rotation
	 */
	public void setRotation(int RotationDegrees) {	
		this.RotationDegrees = RotationDegrees%360;
	}
	
	/**
	 * Gets the dy.
	 *
	 * @return the directional y
	 */
	public int getDY() {	
		return dy;
	}
	
	/**
	 * Gets the dx.
	 *
	 * @return the directional x
	 */
	public int getDX() {	
		return dx;
	}

	/**
	 * Sets the DXDY.
	 *
	 * @param dx the directional X
	 * @param dy the directional Y
	 */
	public void setDXDY(int dx, int dy) {	
		this.dx = dx;
		this.dy = dy;
	}
	
	/**
	 * Gets the intialX.
	 *
	 * @return the intial X
	 */
	public int getIntialX() {
		return intialX;
	}
	
	/**
	 * Gets the intialY.
	 *
	 * @return the intial Y
	 */
	public int getIntialY() {
		return intialY;
	}

	/* (non-Javadoc)
	 * @see robots.Character.Characters#getValue()
	 */
	@Override
	public char getValue() {
		return Value;
	}
	
	/* (non-Javadoc)
	 * @see robots.Character.Characters#getX()
	 */
	@Override
	public int getX() {
		return x;
	}

	/* (non-Javadoc)
	 * @see robots.Character.Characters#getY()
	 */
	@Override
	public int getY() {
		return y;
	}

	/* (non-Javadoc)
	 * @see robots.Character.Characters#setX(int)
	 */
	@Override
	public void setX(int x) {
		this.x = x;
		
	}

	/* (non-Javadoc)
	 * @see robots.Character.Characters#setY(int)
	 */
	@Override
	public void setY(int y) {
		this.y = y;
		
	}

	/**
	 * Gets the health value.
	 *
	 * @return the health value
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Sets health value.
	 *
	 * @param health the new health value
	 */
	public void setHealth(int health) {
		this.health = health;
	}

}
