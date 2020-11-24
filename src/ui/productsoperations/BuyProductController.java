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

public class BuyProductController {
	private Inventory inventory;
	private InventoryController ic;
	
	VBox buyProductPane;
	
	public BuyProductController(Inventory inventory, InventoryController ic) {
		this.inventory = inventory;
		this.ic = ic;
	}

	@FXML
	private ChoiceBox<String> menuProducts;
	
	@FXML
    private JFXTextField unitsNumField;

    @FXML
    private JFXTextField unitPriceField;

	
    private Stage buyProductWindow;

    @FXML
    private JFXButton buyBut;

    @FXML
    void buyAct(ActionEvent event) {
    	String productName = menuProducts.getSelectionModel().getSelectedItem();
    	int amount = Integer.parseInt(unitsNumField.getText());
    	double pricePerUnit = Double.parseDouble(unitPriceField.getText());
    	inventory.buy(productName, amount, pricePerUnit);
    	ic.actualizeProducts();
    	ic.actualizeTV(productName);
    	menuProducts.getSelectionModel().clearSelection();
    	unitsNumField.setText("");
    	unitPriceField.setText("");
    }
	
	public void buyProduct() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("BuyProductWindow.fxml"));
    	fxmlLoader.setController(this);
    	
		try {
			buyProductPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	Scene scene = new Scene(buyProductPane);
    	
    	buyProductWindow = new Stage();
    	buyProductWindow.setScene(scene);
    	buyProductWindow.setResizable(false);
    	buyProductWindow.initModality(Modality.APPLICATION_MODAL);
    	
    	buyProductWindow.show();
    	
    	menuProducts.getItems().setAll(inventory.getStringRegisteredProducts());
	}


}
