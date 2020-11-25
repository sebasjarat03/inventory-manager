package ui;

import com.jfoenix.controls.JFXButton;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Inventory;
import model.Product;
import model.Transaction;
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
	private TableView<Transaction> prodTable;

	@FXML
	private TableColumn<Transaction, String> nameCol;

	@FXML
	private TableColumn<Transaction, Integer> unitCol;

	@FXML
	private TableColumn<Transaction, Double> unitPriceCol;
	
	@FXML
    private TableColumn<Transaction, Double> totalCostCol;
	
	@FXML
    private TableColumn<Transaction, Integer> totalUnitCol;

    @FXML
    private TableColumn<Transaction, Double> unitPriceCol2;

    @FXML
    private TableColumn<Transaction, Double> totalPriceCol2;

	@FXML
	private JFXButton buyBut;

	@FXML
	private JFXButton sellBut;
	
    @FXML
    private ChoiceBox<String> productsChoice;

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
		productsChoice.getItems().setAll(inventory.getStockString());
		
		nameCol.setCellValueFactory(new PropertyValueFactory<>("type"));
		unitCol.setCellValueFactory(new PropertyValueFactory<>("units"));
		unitPriceCol.setCellValueFactory(new PropertyValueFactory<>("pricePerUnit"));
		totalCostCol.setCellValueFactory(new PropertyValueFactory<>("totalCost"));
	}
	
	public void actualizeTV(String product) {
		if(inventory.getProduct(product)!=null) {
			Product p = inventory.getProduct(product);

			ObservableList<Transaction> data = FXCollections.observableArrayList(p.getTransactions());
			prodTable.getItems().setAll(data);
		}
	}
	
	public void actualizeProducts() {
		productsChoice.getItems().setAll(inventory.getStockString());
	}
	
	public void initialize() {
		productsChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				if(arg2!=null) {
					actualizeTV(arg2);
				}
			}
		});
		
		prodTable.setRowFactory(tv -> new TableRow<Transaction>() {
		    @Override
		    public void updateItem(Transaction item, boolean empty) {
		        super.updateItem(item, empty) ;
		        if (item == null) {
		            setStyle("");
		        } else if (item.getType().equals("BUY")) {
		            setStyle("-fx-background-color: #bfffd0;");
		            setText("");
		            
		        } else {
		        	setStyle("-fx-background-color: #ffbfca;");
		        }
		    }
		});
	}

}
