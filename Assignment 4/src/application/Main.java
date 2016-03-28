package application;
	
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		primaryStage.setMinHeight(500);
		primaryStage.setMinWidth(600);
		BorderPane border = new BorderPane();
		VBox topContainer = new VBox();
		VBox simSettings = new VBox();
		MenuBar mainMenu = new MenuBar();
		topContainer.getChildren().add(mainMenu);
		HBox statusbar = new HBox();
		
		border.setTop(topContainer);
		border.setRight(simSettings);
		border.setBottom(statusbar);
		
		// Menuubar stuff
		Menu file = new Menu("File");
		MenuItem newSim = new MenuItem("New simulation");
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		    	primaryStage.close();
		    }
		});
		file.getItems().addAll(newSim, exit);
		
		Menu settings = new Menu("Settings");
		MenuItem editSettings = new MenuItem("Properties");
		settings.getItems().add(editSettings);		
		
		Menu help = new Menu("Help");
		MenuItem viewCode = new MenuItem("View GitHub repository");
		viewCode.setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		    	String url = "http://github.com/zackharley/Fireworks";
		    	getHostServices().showDocument(url);
		    }
		});
		help.getItems().add(viewCode);
		mainMenu.getMenus().addAll(file, settings, help);
		
		// Simulation settings initialization
		simSettings.setPadding(new Insets(25, 25, 25, 25));
		Label windLabel = new Label("Adjust wind velocity (m/s):");
		Slider windSlider = new Slider(-20, 20, 0);
		HBox windSettings = new HBox();
		TextField windField = new TextField("0");
		Button setWind = new Button("Sent wind velocity");
		Label angleLabel = new Label("Adjust launch angle:");
		Slider launchSlider = new Slider(-15, 15, 0);
		HBox angleSettings = new HBox();
		TextField angleField = new TextField("0");
		Button setAngle = new Button("Set launch angle");
		Button startBtn = new Button("Start simulation");
		
		// Slider Settings
		windSlider.setBlockIncrement(1);
		launchSlider.setBlockIncrement(1);
		windSlider.setShowTickMarks(true);
		launchSlider.setShowTickMarks(true);
		windSlider.setShowTickLabels(true);
		launchSlider.setShowTickLabels(true);
		windSlider.setMajorTickUnit(10);
		launchSlider.setMajorTickUnit(5);
		
		// Text field settings
		windField.setPrefWidth(60);
		angleField.setPrefWidth(60);
		
		windSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov,
					Number old_value, Number new_val) {
				windField.setText(new_val.toString());
			}
		});
		
		launchSlider.valueProperty().addListener(new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> ov,
					Number old_value, Number new_val) {
				angleField.setText(new_val.toString());
			}
		});
		
		windSettings.getChildren().addAll(windField, setWind);
		angleSettings.getChildren().addAll(angleField, setAngle);
		simSettings.getChildren().addAll(windLabel, windSlider, windSettings,
				angleLabel, launchSlider, angleSettings, startBtn);
		
		// Status bar
		Label status = new Label(" Welcome to FWS 2016");
		statusbar.getChildren().add(status);
				
		Scene scene = new Scene(border, 600, 500);		
		primaryStage.setTitle("Firework Simulator 2016");
		primaryStage.getIcons().add(new Image("http://icons.iconarchive.com/icons/blacksugar/new-year-flat/128/Rocket-Fireworks-icon.png"));
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public static Boolean toStringTwoDec(Number num) {
		num.toString().indexOf('.');
		return Pattern.matches("(^\\d{1,}\\.*\\d{0,2}", num.toString());
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
