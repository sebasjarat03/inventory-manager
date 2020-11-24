package model;

import java.time.LocalDateTime;

public class Transaction {
	
	public enum TransactionType{
		BUY,
		SELL;
	}
	
	private TransactionType type;
	private int units;
	private LocalDateTime date;
	private double pricePerUnit;
	
	//I dont think we need this constructor
	public Transaction(TransactionType type, int units, double pricePerUnit, LocalDateTime date) {
		this.units = units;
		this.pricePerUnit = pricePerUnit;
		this.date = date;
	}
	
	public Transaction(TransactionType type, int units, double pricePerUnit) {
		this.type = type;
		this.units = units;
		this.pricePerUnit = pricePerUnit;
	}

	public String getType() {
		return type.toString();
	}

	public int getUnits() {
		return units;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}
	
	public double getTotalPrice() {
		return pricePerUnit*units;
	}
	
	
	
	

}
