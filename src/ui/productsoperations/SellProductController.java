package ui.productsoperations;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Inventory;
import ui.InventoryController;

public class SellProductController {
	
	private Inventory inventory;
	private InventoryController ic;
	private boolean paMode;
	
	public SellProductController(Inventory inventory, InventoryController ic, boolean paMode) {
		this.inventory = inventory;
		this.ic = ic;
		this.paMode = paMode;
	}
	
	@FXML
	private VBox SellPane;
	
	@FXML
	private Stage SellWindow;

	@FXML
	private ChoiceBox<String> menuProducts;

	@FXML
	private JFXTextField unitsNumField;

	@FXML
	private JFXButton sellBut;

	@FXML
	void sellAct(ActionEvent event) {
		String productName = menuProducts.getSelectionModel().getSelectedItem();
    	int amount = Integer.parseInt(unitsNumField.getText());
    	inventory.sell(productName, amount, paMode);
    	ic.actualizeProducts();
    	ic.actualizeTV(productName);
    	menuProducts.getSelectionModel().clearSelection();
    	unitsNumField.setText("");

	}
	
	public void sellProduct() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SellProductWindow.fxml"));
    	fxmlLoader.setController(this);
    	
		try {
			SellPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	Scene scene = new Scene(SellPane);
    	
    	SellWindow = new Stage();
    	SellWindow.setScene(scene);
    	SellWindow.setResizable(false);
    	SellWindow.initModality(Modality.APPLICATION_MODAL);
    	
    	SellWindow.show();
    	
    	menuProducts.getItems().setAll(inventory.getStockString());
	}

}
