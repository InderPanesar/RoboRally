package robots.Board;

import java.io.File;
import java.net.MalformedURLException;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The Class MapTile.
 */
public class MapTile {
	
	/** The Grid Label. */
	private Label Tile;
	
	/** The x. */
	private int x;
	
	/** The y. */
	private int y;
	
	/** The robot value. */
	private char value;
	
  	/**
	   * Instantiates a new map tile.
	   *
	   * @param x the x
	   * @param y the y
	   * @param value the value
	   * @param isTest the is test
	   */
	public MapTile(int x, int y, char value, Boolean isTest) {
		if(!isTest) {
			this.Tile = new Label();
		}
		//Sets the X, Y and char value of the robot
		this.setX(x);
		this.setY(y);
		this.setValue(value);
	}
	
  	/**
	   * Gets the tile.
	   *
	   * @return the tile
	   */
	public Label getTile() {
		return Tile;
	}
	
  	/**
	   * Sets the label null.
	   */
	public void setLabelNull() {
		if(Tile != null) {
			Platform.runLater(new Runnable(){

				@Override
				public void run() {
			        Tile.setGraphic(null);				
				}
			});
		}
	}
	
  	/**
	   * Sets the label.
	   *
	   * @param board the file name (Character)
	   * @param height the grid height
	   * @param width the grid width
	   */
	public void setLabel(char board, int height, int width) {
		if(Tile != null) {
			int boxWidth;
			if(width > height) {
				boxWidth = 555/height;
			}
			else {
				boxWidth = 555/width;
			}

			Platform.runLater(new Runnable(){
				@Override
				public void run() {
					Image image = null;
			        try {
			        	//Check to see which side is bigger
			        	if(width > height) {
			        		//Resizes the image to fit the board
			    			image = new Image(new File("icons\\"+board+".PNG").toURI().toURL().toExternalForm(), (boxWidth-(width*4)), (boxWidth-(width*4)), false, false);
			        	}
			        	else {
			    			image = new Image(new File("icons\\"+board+".PNG").toURI().toURL().toExternalForm(), (boxWidth - (height*4)), (boxWidth - (height*4)), false, false);
			        	}
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
			        Tile.setText("");
			        Tile.setGraphic(new ImageView(image));
					
				}
			});
		}
	}
	
  	/**
	   * Sets the label.
	   *
	   * @param board the file name (String)
	   * @param height the grid height
	   * @param width the grid width
	   */
	  /*
  	 * This is used to set the label
  	 */
	public void setLabel(String board, int height, int width) {
		//Same as above however this will be able to take a string as well as char value.
		if(Tile != null) {
			int boxWidth;
			if(width > height) {
				boxWidth = 555/height;
			}
			else {
				boxWidth = 555/width;
			}
	
			Platform.runLater(new Runnable(){
				@Override
				public void run() {
					Image image = null;
			        try {
			        	if(width > height) {
			    			image = new Image(new File("icons\\"+board+".PNG").toURI().toURL().toExternalForm(), (boxWidth-(width*4)), (boxWidth-(width*4)), false, false);
			        	}
			        	else {
			    			image = new Image(new File("icons\\"+board+".PNG").toURI().toURL().toExternalForm(), (boxWidth - (height*4)), (boxWidth - (height*4)), false, false);
			        	}
					} catch (MalformedURLException e) {
						alertHandler("Incorrect Icon File", "Incorrect icon file!");
					}
			        		        
			        Tile.setText("");
			        Tile.setGraphic(new ImageView(image));
					
				}
			});
		}

	}
	
  	/**
	   * Used to handle different alert when something 
	   * goes wrong within the TileMap.
	   *
	   * @param ErrorType the error type
	   * @param Error the description of the error
	   */
	private void alertHandler(String ErrorType, String Error) {
		Platform.runLater(new Runnable(){
			@Override
			public void run() {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle(ErrorType);
				alert.setHeaderText(ErrorType);
				alert.setContentText(Error);
				alert.showAndWait();
				System.exit(0);
			}
			
		});

	}	
  	
	  /**
	   * Sets the label text.
	   *
	   * @param value the new label text
	   */
	public void setLabelText(String value) {
		if(Tile != null) {
			Platform.runLater(new Runnable(){

				@Override
				public void run() {
					Tile.setText(value);
					
				}
			});
		}
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the robot value.
	 *
	 * @return the robot value
	 */
	public char getValue() {
		return value;
	}

	/**
	 * Sets the robot value.
	 *
	 * @param value the new robot value
	 */
	public void setValue(char value) {
		this.value = value;
	}
	

}
