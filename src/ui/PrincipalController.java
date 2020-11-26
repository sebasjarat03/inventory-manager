package ui;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Inventory;

public class PrincipalController {
	
	private boolean PAMODE;
	
	private CreateProductController cp;
	

    @FXML
    private JFXButton pepsBut;

    @FXML
    private JFXButton ppBut;

    @FXML
    void pepsAct(ActionEvent event) {
    	PAMODE = false;
    	addBut.setDisable(false);
    	inventBut.setDisable(false);
    	ic.setPaMode(PAMODE);
    	mainPane.setCenter(createProduct);
    }

    @FXML
    void ppAct(ActionEvent event) {
    	PAMODE =true;
    	addBut.setDisable(false);
    	inventBut.setDisable(false);
    	ic.setPaMode(PAMODE);
    	mainPane.setCenter(createProduct);
    }
	
	private InventoryController ic;
	
	private Inventory inventory;
	
	private VBox createProduct;
	private VBox inventoryWindow;
	
	private Pane options;
	
	public PrincipalController(Inventory inventory) {
		this.inventory = inventory;
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
		ic.initializeTV();

	}
	
	 
	
	public void whenInitialize() {
		cp = new CreateProductController(inventory);
		ic = new InventoryController(inventory);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addProductWindow.fxml"));
		
		


		fxmlLoader.setController(cp);

		
		try {
			createProduct = fxmlLoader.load();
		} catch (IOException e) {
		}
		
		
		fxmlLoader = new FXMLLoader(getClass().getResource("InventoryWindow.fxml"));
		
		fxmlLoader.setController(ic);

		try {
			inventoryWindow = fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		fxmlLoader = new FXMLLoader(getClass().getResource("optionPane.fxml"));

		fxmlLoader.setController(this);
		try {
			options = fxmlLoader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addBut.setDisable(true);
		inventBut.setDisable(true);
		mainPane.setCenter(options);
	}

}
