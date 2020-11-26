package ui.productsoperations;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TotalsPepsController {
	int totalBuyUnits;
	double totalBuyPrice;
	int totalSellUnits;
	double totalSellPrice; 
	int invUnits;
	double invPrice;
	
	public TotalsPepsController(int totalBuyUnits,double totalBuyPrice, int totalSellUnits, double totalSellPrice, int invUnits, double invPrice) {
		this.totalBuyUnits = totalBuyUnits;
		this.totalBuyPrice = totalBuyPrice;
		this.totalSellUnits = totalSellUnits;
		this.totalSellPrice = totalSellPrice;
		this.invUnits = invUnits;
		this.invPrice = invPrice;
	}
	
	private Pane totalPane;
	
	private Stage totalsWindow;

	@FXML
	private Label buysCant;

	@FXML
	private Label totalEmtradas;

	@FXML
	private Label outsQuantitys;

	@FXML
	private Label totOurs;

	@FXML
	private Label unitsInv;

	@FXML
	private Label totCostInv;
	
	public void totalWindow() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("totalsWindow.fxml"));
    	fxmlLoader.setController(this);
    	
		try {
			totalPane = fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	Scene scene = new Scene(totalPane);
    	
    	totalsWindow = new Stage();
    	totalsWindow.setScene(scene);
    	totalsWindow.setResizable(false);
    	totalsWindow.initModality(Modality.APPLICATION_MODAL);
    	
    	totalsWindow.show();
    	
    	buysCant.setText(totalBuyUnits + "");
    	totalEmtradas.setText(totalBuyPrice + "");
    	outsQuantitys.setText(totalSellUnits + "");
    	totOurs.setText(totalSellPrice + "");
    	unitsInv.setText(invUnits + "");
    	totCostInv.setText(invPrice + "");
	}


}
