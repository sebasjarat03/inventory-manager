package ui;

import com.jfoenix.controls.JFXButton;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Inventory;
import model.Product;
import ui.productsoperations.BuyProductController;
import ui.productsoperations.SellProductController;

public class InventoryController {

	private Inventory inventory;

	public InventoryController(Inventory inventory) {
		this.inventory = inventory;
	}

	private BuyProductController bpc;
	
	private SellProductController spc;

	@FXML
	private TableView<Product> prodTable;

	@FXML
	private TableColumn<Product, String> nameCol;

	@FXML
	private TableColumn<Product, Integer> unitCol;

	@FXML
	private TableColumn<?, ?> unitPriceCol;

	@FXML
	private JFXButton buyBut;

	@FXML
	private JFXButton sellBut;

	@FXML
	void buyAct(ActionEvent event) {
		bpc = new BuyProductController(inventory, this);
		bpc.buyProduct();

	}

	@FXML
	void sellAct(ActionEvent event) {
		spc = new SellProductController(inventory, this);
		spc.sellProduct();
	}
	
	public void initializeTV() {
		ObservableList<Product> data = FXCollections.observableArrayList(inventory.getStringStockProducts());
		nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
		unitCol.setCellValueFactory(new PropertyValueFactory<>("amount"));
		prodTable.getItems().setAll(data);
	}
	
	public void actualizeTV() {
		ObservableList<Product> data = FXCollections.observableArrayList(inventory.getStringStockProducts());
		prodTable.getItems().setAll(data);
	}

}
