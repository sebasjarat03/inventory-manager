package ui;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ui.productsoperations.BuyProductController;

public class InventoryController {
	public InventoryController() {
		// TODO Auto-generated constructor stub
	}
	
	private BuyProductController bpc;
	
	  @FXML
	    private TableView<?> prodTable;

	    @FXML
	    private TableColumn<?, ?> nameCol;

	    @FXML
	    private TableColumn<?, ?> unitCol;

	    @FXML
	    private TableColumn<?, ?> unitPriceCol;

	    @FXML
	    private JFXButton buyBut;

	    @FXML
	    private JFXButton sellBut;

	    @FXML
	    void buyAct(ActionEvent event) {
	    	bpc = new BuyProductController();
	    	bpc.buyProduct();

	    }

	    @FXML
	    void sellAct(ActionEvent event) {

	    }

}
