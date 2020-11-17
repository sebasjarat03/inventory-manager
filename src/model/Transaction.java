package model;

import java.time.LocalDateTime;

public class Transaction {
	private int units;
	private LocalDateTime date;
	private double pricePerUnit;
	private Product product;
	
	public Transaction(int units, double pricePerUnits, LocalDateTime date, Product product) {
		this.units = units;
		this.pricePerUnit = pricePerUnits;
		this.date = date;
		this.product = product;
	}
	
	

}
