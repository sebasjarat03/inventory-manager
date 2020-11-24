package model;

import java.util.ArrayList;
import java.util.List;

import model.Transaction.TransactionType;

public class Product {
	
	private String name;
	private int units;
	private ArrayList<Transaction> transactions;
	
	public Product(String name, int units) {
		super();
		this.name = name;
		this.units = units;
		this.transactions = new ArrayList<Transaction>();
	}

	public void buy(int units, double pricePerUnit) {
		this.units += units;
		registerTransaction(TransactionType.BUY, units, pricePerUnit);
	}
	public void sell(int units, double pricePerUnit) {
		this.units -= units;
		registerTransaction(TransactionType.SELL, units, pricePerUnit);
	}
	
	private void registerTransaction(TransactionType type, int units, double pricePerUnit) {
		this.transactions.add(new Transaction(type, units, pricePerUnit));
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUnits() {
		return units;
	}

	public void setUnits(int units) {
		this.units = units;
	}
	
	public List<Transaction> getTransactions(){
		return this.transactions;
	}
	
	
}
