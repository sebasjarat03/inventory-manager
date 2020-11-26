package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.TreeSet;

import model.Transaction.TransactionType;

public class Product {
	
	private String name;
	private int units;
	private TreeSet<Transaction> transactions;
	private ArrayList<Item> items;
	
	
	public Product(String name, int units) {
		super();
		this.name = name;
		this.units = units;
		this.transactions = new TreeSet<Transaction>();
	}

	public void buy(int units, double pricePerUnit) {
		this.units += units;
		
		if(items.contains(pricePerUnit)) {
			for(Item i: items) {
				if(i.equals(pricePerUnit)) {
					i.addUnits(units);
					break;
				}
			}
		}else {
			items.add(new Item(units, pricePerUnit));
		}
		
		registerTransaction(TransactionType.BUY, units, pricePerUnit);
	}
	public boolean sell(int units) {
		if(this.units < units)
			return false;

		this.units -= units; //Here we're already sure we can sell this amount;
		
		for(Item i: items) {
			int left = i.sellUnits(units);
			
			if(left >= 0) { //Available units were more or equal that the amount sell
				registerTransaction(TransactionType.SELL, units, i.getPricePerUnit());
				break;
			}else {
				//Left is negative here
				registerTransaction(TransactionType.SELL, units + left, i.getPricePerUnit());
				units = -left;
			}
		}

		return true;
	}
	
	private void registerTransaction(TransactionType type, int units, double pricePerUnit) {
		this.transactions.add(new Transaction(type, units, pricePerUnit, LocalDateTime.now()));
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
		return new ArrayList<>(transactions);
	}
	
	public List<Transaction> getTransactions(LocalDateTime from, LocalDateTime to){
		return new ArrayList<>(transactions.subSet(Transaction.TransactionWithDate(from), Transaction.TransactionWithDate(to)));
	}
}
