package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Inventory;

public class CreateProductController {
	
	private Inventory inventory;

	@FXML
	private JFXTextField nameField;

	@FXML
	private JFXButton addBut;

	public CreateProductController(Inventory inventory) {
		this.inventory = inventory;
	}
	
	@FXML
	void addAct(ActionEvent event) {
		registerProduct();
	}

	public void initialize() {
		nameField.setOnKeyReleased(new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        if(event.getCode().equals(KeyCode.ENTER)) {
		        	registerProduct();
		        }
		    }
		});
	}
	
	private void registerProduct() {
		String productName = nameField.getText();
		inventory.registerProduct(productName);
		nameField.setText("");
	}
}
