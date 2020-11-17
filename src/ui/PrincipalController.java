package ui;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class PrincipalController {
	
	private CreateProductController cp;
	
	private InventoryController ic;
	
	private VBox createProduct;
	private VBox inventoryWindow;
	
	public PrincipalController() {
		
	}
	@FXML
	private JFXButton addBut;
	
	@FXML
    private BorderPane mainPane;

	@FXML
	private JFXButton inventBut;

	@FXML
	void addAct(ActionEvent event) {
		mainPane.setCenter(createProduct);

	}

	@FXML
	void inventAct(ActionEvent event) {
		mainPane.setCenter(inventoryWindow);

	}
	
	 
	
	public void whenInitialize() {
		cp = new CreateProductController();
		ic = new InventoryController();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addProductWindow.fxml"));
		
		


		fxmlLoader.setController(cp);

		
		try {
			createProduct = fxmlLoader.load();
		} catch (IOException e) {
		}
		mainPane.setCenter(createProduct);
		
		fxmlLoader = new FXMLLoader(getClass().getResource("InventoryWindow.fxml"));
		
		fxmlLoader.setController(ic);

		try {
			inventoryWindow = fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
