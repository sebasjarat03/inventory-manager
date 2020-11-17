package ui.productsoperations;

import java.io.IOException;

import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BuyProductController {
	
	
	VBox buyProductPane;
	public BuyProductController() {
		// TODO Auto-generated constructor stub
	}

	@FXML
	private ChoiceBox<String> menuProducts;
	
	@FXML
    private JFXTextField unitsNumField;

    @FXML
    private JFXTextField unitPriceField;

	
	private Stage buyProductWindow;
	
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
    	
    	menuProducts.getItems().setAll();
	}


}
