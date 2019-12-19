package view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Hello world!
 *
 */
public class App extends Application {
	private Stage guiStage;

	@Override
	public void start(Stage stage) throws IOException {
		guiStage = stage;
		stage.setResizable(false);
		stage.setTitle("My Food");
		Scene scene = new Scene(loadFXML("view/LoginScenario"));
		stage.setScene(scene);
		stage.show();
		LogManager logManager = LogManager.getLogManager();
		Logger logger = logManager.getLogger("");
		logger.setLevel(Level.OFF);
	}

	public static Parent loadFXML(String fxml) throws IOException {
		System.out.println(App.class.getClassLoader().getResource(fxml + ".fxml"));
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getClassLoader().getResource(fxml + ".fxml"));
		return fxmlLoader.load();
	}

}
