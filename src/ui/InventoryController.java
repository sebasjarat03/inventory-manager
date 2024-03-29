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
import model.Info;
import model.Inventory;
import ui.productsoperations.BuyProductController;
import ui.productsoperations.SellProductController;
import ui.productsoperations.TotalsPepsController;

public class InventoryController {

	private Inventory inventory;
	private boolean paMode;

	public InventoryController(Inventory inventory) {
		this.inventory = inventory;
		
	}

	private BuyProductController bpc;
	
	private SellProductController spc;
	
	private TotalsPepsController tpc;

	@FXML
	private TableView<Info> prodTable;

	@FXML
	private TableColumn<Info, String> nameCol;

	@FXML
	private JFXButton updateTbBut;


	@FXML
	private TableColumn<Info, Integer> unitCol;

	@FXML
	private TableColumn<Info, Double> unitPriceCol;
	
	@FXML
    private TableColumn<Info, Double> totalCostCol;

	@FXML
	private JFXButton buyBut;

	@FXML
	private JFXButton sellBut;
	
    @FXML
    private ChoiceBox<String> productsChoice;
    
    @FXML
    private JFXButton showTotalsBut;

	@FXML
	void buyAct(ActionEvent event) {
		bpc = new BuyProductController(inventory, this, paMode);
		bpc.buyProduct();

	}

	@FXML
	void sellAct(ActionEvent event) {
		spc = new SellProductController(inventory, this, paMode);
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
		if(inventory.getProduct(product) == null) 
			return;

		
		if(productsChoice.getSelectionModel().getSelectedItem()==null || !productsChoice.getSelectionModel().getSelectedItem().equals(product)) {
			productsChoice.getSelectionModel().select(product);
			ObservableList<Info> data = FXCollections.observableArrayList(inventory.getProduct(product).getTransactions());
			prodTable.getItems().setAll(data);
			System.out.println("assssssssssssssssssssss");
			return;
		}
		
		ObservableList<Info> data = FXCollections.observableArrayList(inventory.getProduct(product).getTransactions());
		prodTable.getItems().addAll(data);
	}

	public void actualizeProducts(String proud) {
		productsChoice.getItems().setAll(inventory.getStockString());
		productsChoice.getSelectionModel().select(proud);
	}
	
	@FXML
    void updateTbAct(ActionEvent event) {
		prodTable.getItems().clear();
		actualizeTV(productsChoice.getSelectionModel().getSelectedItem());

    }
	
	public void initialize() {
		
//		productsChoice.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
//
//			@Override
//			public void changed(ObservableValue<? extends String> arg0, String oldVal, String newVal) {
//				if(newVal != null && !newVal.equalsIgnoreCase(oldVal)) {
//					ObservableList<Info> data = FXCollections.observableArrayList(inventory.getProduct(newVal).getTransactions());
//					prodTable.getItems().setAll(data);
//					System.out.println("Here: " + newVal + " " + oldVal);
//				}
//			}
//		});
		
		prodTable.setRowFactory(tv -> new TableRow<Info>() {
		    @Override
		    public void updateItem(Info item, boolean empty) {
		        super.updateItem(item, empty) ;
		        if (item == null) {
		            setStyle("");
		        } else if (item.getType().equals("BUY")) {
		            setStyle("-fx-background-color: #bfffd0;");
		        } else if (item.getType().equals("SELL")){
		        	setStyle("-fx-background-color: #ffbfca;");
		        } else {
		        	setStyle("-fx-background-color: #b4dffa;");
//		        	setPadding(new Insets(0, 0, 0, 20));
		        }
		    }
		});
	}
	
	public void setPaMode(boolean paMode) {
		this.paMode = paMode;
	}
	
	private void calculateTotalsFIFO() {
		int totalUnitsBuy = 0;
		double totalBuyPrice = 0;
		
		int totalUnitsSell = 0;
		double totalSellPrice = 0;
		
		for(Info row: prodTable.getItems()) {
			if(row.getType().equals("BUY")) {
				totalUnitsBuy += row.getUnits();
				totalBuyPrice += row.getPricePerUnit()*row.getUnits();
			}else if(row.getType().equals("SELL")) {
				totalUnitsSell += row.getUnits();
				totalSellPrice += row.getPricePerUnit()*row.getUnits();
			}
		}
		
		tpc = new TotalsPepsController(totalUnitsBuy, totalBuyPrice, totalUnitsSell, totalSellPrice, 
				inventory.getProduct(productsChoice.getSelectionModel().getSelectedItem()).getUnits(), 
				inventory.getProduct(productsChoice.getSelectionModel().getSelectedItem()).calcTotalValueInventory(paMode));
		tpc.totalWindow();
	}
	
	@FXML
    public void showTotalsAct(ActionEvent event) {
		calculateTotalsFIFO();

    }

}
