package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application{

	PrincipalController principalController;
	double yOffset, xOffset;

	public Main() {
		principalController = new PrincipalController();
	}

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader mainWindow = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
		

		mainWindow.setController(principalController);
		
		BorderPane mainPane = mainWindow.load();
		
		

		Scene scene = new Scene(mainPane);
		primaryStage.setResizable(false);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Manager de inventarios");
		primaryStage.getIcons().add(new Image("file:assets/inventory.png"));
	

		primaryStage.show();
		principalController.whenInitialize();

	}

}
