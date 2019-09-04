package robots.main;
import java.io.File;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.fxml.FXMLLoader;

/**
 * The Class Main.
 */
public class Main extends Application {
	
	/** The selected board file. */
	private File selectedBoardFile = null;
	
	/** The selected command file. */
	private File selectedCommandFile = null;
	
	/** Check if using files not input. */
	private Boolean usingFiles = false;
	
	/** The is used to check for testing. */
	private Boolean isTesting = false;
	
	/** If robot use lasers. */
	private Boolean robotUseLasers = false;
	
	
	/**
	 * Gets the file extension.
	 *
	 * @param file the file to check from ChooseValue
	 * @return the file extension
	 */
	private String getFileExtension(File file) {
		String extension = "";
	    String name = file.getName();
		int i = name.lastIndexOf('.');
		if (i >= 0) {
		    extension = name.substring(i+1);
		}
		return extension;
	}
	
	/**
	 * Alert handler.
	 *
	 * @param ErrorType the error type
	 * @param Name the description of the error
	 */
	private void alertHandler(String ErrorType, String Name) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(ErrorType);
		alert.setHeaderText(ErrorType);
		alert.setContentText(Name);
		alert.showAndWait();
		System.exit(0);
	}
	
	/**
	 * File extension check.
	 */
	private void fileExtensionCheck() {
		if(selectedBoardFile != null) {
			if(!(getFileExtension(selectedBoardFile).equals("brd"))) {
				alertHandler("Incorrect Board File", "The file chosen is not correct for a board file. Extension must be .brd");
			}
		}
		if(selectedCommandFile != null) {
			if(!(getFileExtension(selectedCommandFile).equals("prg"))) {
				alertHandler("Incorrect Command File", "The file chosen is not correct for a command file. Extension must be .prg");
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
		  	/*
		  	 * Lets you choose if you want the default file or the text files.
		  	 */
			ChooseValue();
			ChooseIfRobotsShootLasers();
			fileExtensionCheck();
			Game game = null;
			
			if(!usingFiles) {
				game = new Game(false, null, null,isTesting,robotUseLasers);
			}
			
			else if(selectedBoardFile == null && selectedCommandFile == null && usingFiles) {
				Alert BoardFileAlert = new Alert(AlertType.INFORMATION);
				BoardFileAlert.setTitle("Information");
				BoardFileAlert.setHeaderText(null);
				BoardFileAlert.setContentText(".brd or .prg file missing. Will use default textfiles.");
				BoardFileAlert.showAndWait();
				usingFiles = false;
				game = new Game(false, null, null,isTesting, robotUseLasers);
			}
			
			else {
				game = new Game(true, selectedBoardFile, selectedCommandFile,isTesting, robotUseLasers);
			}
			//The process of loading the entire UI
	    	final FXMLLoader loader = new FXMLLoader();
	    	loader.setLocation(getClass().getResource("Fxm.fxml"));
	    	SampleController controller = game.getUIController();
	    	loader.setController(controller);
	    	Parent root = (Parent) loader.load();
			Scene scene = new Scene(root,840,650);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.sizeToScene();
			primaryStage.setMinHeight(680);
			primaryStage.setMinWidth(825);
			primaryStage.setResizable(false);
			primaryStage.setTitle("Robots V1.0");
			primaryStage.show();
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			    @Override
			    public void handle(WindowEvent t) {
			        Platform.exit();
			        System.exit(0);
			    }
			});
			
			if(!usingFiles ) {
				game.startLoop(false);
			}
			else {
				game.startLoop(true);
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Choose if robots shoot lasers.
	 */
	public void ChooseIfRobotsShootLasers() {
		//An alert confirmation if you want robots to shoot lasers
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Query about gameplay");
		alert.setHeaderText("Choose an Option");
		alert.setContentText("Do you want robots to shoot lasers");
		ButtonType buttonTypeOne = new ButtonType("Yes");
		ButtonType buttonTypeTwo = new ButtonType("No");
		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
		    setRobotUseLasers(true);
		}
		else {
			setRobotUseLasers(false);
		}

	}
	
  	/**
	   * UI input values.
	   */
	public void ChooseValue() {
		//An alert confirmation if you want to use TextInput or TextFiles
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Welcome to Robots");
		alert.setHeaderText("Choose an Option");
		alert.setContentText("Would you like to use TextInputs or TextFiles");
		ButtonType buttonTypeOne = new ButtonType("Using Inputs");
		ButtonType buttonTypeTwo = new ButtonType("Using TextFiles");
		ButtonType buttonTypeCancel = new ButtonType("Exit", ButtonData.CANCEL_CLOSE);
		
		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
		    usingFiles = false;

		}
		else if (result.get() == buttonTypeTwo) {
			Alert BoardFileAlert = new Alert(AlertType.INFORMATION);
			BoardFileAlert.setTitle("Information");
			BoardFileAlert.setHeaderText(null);
			BoardFileAlert.setContentText("Please choose your .brd file");
			BoardFileAlert.showAndWait();
			
			FileChooser boardFile = new FileChooser();
		    selectedBoardFile = boardFile.showOpenDialog(null);
		    
			Alert CommandsFileAlert = new Alert(AlertType.INFORMATION);
			CommandsFileAlert.setTitle("Information");
			CommandsFileAlert.setHeaderText(null);
			CommandsFileAlert.setContentText("Please choose your .prg file");
			CommandsFileAlert.showAndWait();
			
			FileChooser commandsFile = new FileChooser();
		    selectedCommandFile = commandsFile.showOpenDialog(null);
		    usingFiles = true;
			
		} 
		else {
			System.exit(0);
		}
	}

	/**
	 * Gets the checks if is testing.
	 *
	 * @return the checks if is testing
	 */
	public Boolean getIsTesting() {
		return isTesting;
	}

	/**
	 * Sets the checks if is testing.
	 *
	 * @param isTesting the new checks if is testing
	 */
	public void setIsTesting(Boolean isTesting) {
		this.isTesting = isTesting;
	}
	
	/**
	 * File selected command file.
	 *
	 * @param selectedCommandFile the selected command file
	 */
	public void fileSelectedCommandFile(File selectedCommandFile) {
		this.selectedCommandFile = selectedCommandFile; 
	}
	
	/**
	 * File board command file.
	 *
	 * @param selectedBoardFile the selected board file
	 */
	public void fileBoardCommandFile(File selectedBoardFile) {
		this.selectedBoardFile = selectedCommandFile; 
	}

	/**
	 * Gets if robot use lasers.
	 *
	 * @return if robot use lasers
	 */
	public Boolean getRobotUseLasers() {
		return robotUseLasers;
	}

	/**
	 * Sets if robot use lasers.
	 *
	 * @param robotUseLasers set if robots use lasers
	 */
	public void setRobotUseLasers(Boolean robotUseLasers) {
		this.robotUseLasers = robotUseLasers;
	}
}


