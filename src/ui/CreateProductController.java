package ui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import model.Inventory;

public class CreateProductController {
	
	private Inventory inventory;

	public CreateProductController(Inventory inventory) {
		this.inventory = inventory;
	}

	@FXML
	private JFXTextField nameField;

	@FXML
	private JFXButton addBut;

	@FXML
	void addAct(ActionEvent event) {
		String productName = nameField.getText();
		inventory.registerProduct(productName);
		nameField.setText("");

	}

}
