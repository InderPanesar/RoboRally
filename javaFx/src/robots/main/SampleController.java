package robots.main;

import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import robots.Board.MapTile;

/**
 * The Class SampleController.
 */
public class SampleController {

	/** The gridPane element. */
	@FXML GridPane grid;
	
	/** The text input element. */
	@FXML TextField textInput;
	
	/** The submit button element. */
	@FXML Button submitButton;
	
	/** The Current player field element. */
	@FXML TextField CurrentPlayer;
	
	/** The Move history field element.  */
	@FXML TextArea MoveHistory;
	
	/** The Robot 1 health field element. */
	@FXML TextField Robot1Health;
	
	/** The Robot 2 health field element.  */
	@FXML TextField Robot2Health;
	
	/** The Robot 3 health field element. */
	@FXML TextField Robot3Health;
	
	/** The Robot 4 health field element. */
	@FXML TextField Robot4Health;
	
	/** The Grid width. */
	private int width;
	
	/** The Grid height. */
	private int height;
	
	/** The Grid tiles. */
	private MapTile[][] tiles;
	
	/** The input command. */
	private String command = "";
	
	/**
	 * Instantiates a new sample controller.
	 *
	 * @param width the width
	 * @param height the height
	 * @param tiles the tiles
	 */
	public SampleController(int width, int height, MapTile[][] tiles) {
		this.width = width;
		this.height = height;
		this.tiles = tiles;
	}
	
	/**
	 * Initialise the User Interface.
	 */
	@FXML public void initialize() {
		grid.setMaxWidth(555);
		grid.setMaxHeight(555/width * height);
		
		for (int y = 0; y < height; y++) {
			grid.getRowConstraints().addAll( new RowConstraints(555/height));
			for (int x = 0; x < width; x++) { 
				grid.add(tiles[x][y].getTile(), x, y);
				if(y == 0) {
					//Grid Constraints make sure the width in the grid is always the same.
					grid.getColumnConstraints().addAll( new ColumnConstraints(555/width));
				}
			}
		}
		
		for (Node child : grid.getChildren()) {
			//Sets the grid Label alignment, both for vertical and horizontally.
			GridPane.setHalignment(child, HPos.CENTER);
			GridPane.setValignment(child, VPos.CENTER);
		}
		
	}
	
	/**
	 * Sets text A.
	 *
	 * @param value the text A
	 */
	public void setTextA(String value) {
		Robot1Health.setText(value);
	}
	
	/**
	 * Sets text B.
	 *
	 * @param value the text B
	 */
	public void setTextB(String value) {
		Robot2Health.setText(value);
	}
	
	/**
	 * Sets text C.
	 *
	 * @param value the text C
	 */
	public void setTextC(String value) {
		Robot3Health.setText(value);
	}
	
	/**
	 * Sets text D.
	 *
	 * @param value the text D
	 */
	public void setTextD(String value) {
		Robot4Health.setText(value);
	}
	
	/**
	 * Gets the input variable.
	 *
	 * @return the input
	 */
	public String getInput() {
		return command;
	}
	
	/**
	 * Reset input, variable.
	 */
	public void resetInput() {
		command = "";
	}
	
	
	/**
	 * Disable input.
	 */
	public void DisableInput() {
		submitButton.setDisable(true);
		textInput.setDisable(true);
	}
	
	/**
	 * Enabled input.
	 */
	public void EnabledInput() {
		submitButton.setDisable(false);
		textInput.setDisable(false);
	}
	
	/**
	 * Gets the grid Tiles.
	 * All the JLabels within the Grid.
	 * The next sets the Tiles
	 *
	 * @return the tiles
	 */
	public MapTile[][] getTiles() {
		return tiles;
	}
	
	/**
	 * Sets the tiles.
	 *
	 * @param x the new tiles
	 */
	public void setTiles(MapTile[][] x) {
		tiles = x;
	}
	
	/**
	 * This is used to add text to the TextArea.
	 *
	 * @param text the new text
	 */
	public void setText(String text) {
		String newLine = System.getProperty("line.separator");
		MoveHistory.appendText(text + newLine);
	}
	
	/**
	 * Character shower.
	 *
	 * @param c the c
	 */
	public void CharacterShower(String c) {
		CurrentPlayer.setText(c);
	}
	
	/**
	 * This is called when the button on the UI 
	 * is clicked.
	 * And stores the input within command.
	 */
	@FXML public void submitClicked() {
		command = textInput.getText();
	}	
}
