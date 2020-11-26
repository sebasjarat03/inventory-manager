package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeSet;

import model.Transaction.TransactionType;

public class Product {
	
	private String name;
	private int units;
	private TreeSet<Transaction> transactions;
	private ArrayList<Item> items;
	
	//Pondered Average
	private Item item;
	
	public Product(String name, int units) {
		super();
		this.name = name;
		this.units = units;
		this.transactions = new TreeSet<Transaction>();
		this.items = new ArrayList<Item>();
		
		item = new Item(0, 0);
	}

	public void buy(int units, double pricePerUnit, boolean paMode) {
		this.units += units;

		boolean exist = false;

		if(paMode) {
			double newPrice = ((units*pricePerUnit) + item.getUnits()*item.getPricePerUnit()) / ((double) units + item.getUnits());
			item.setPricePerUnit(newPrice);
			item.addUnits(units);
		}else {
			for(Item i: items) {
				if(i.getPricePerUnit() == pricePerUnit) {
					i.addUnits(units);
					exist = true;
					break;
				}
			}
			if(!exist)
				items.add(new Item(units, pricePerUnit));
		}
		
		registerTransaction(TransactionType.BUY, units, pricePerUnit);
	}
	public boolean sell(int units, boolean paMode) {
		if(this.units < units)
			return false;

		this.units -= units; //Here we're already sure we can sell this amount;
		
		if(paMode) {
			item.sellUnits(units);
			registerTransaction(TransactionType.SELL, units, item.getPricePerUnit());
			
		}else {
			for(Item i: items) {
				int left = i.sellUnits(units);
				
				if(left >= 0) { //Available units were more or equal that the amount sell
					registerTransaction(TransactionType.SELL, units, i.getPricePerUnit());
					break;
				}else {
					//Left is negative here
					registerTransaction(TransactionType.SELL, units + left, i.getPricePerUnit());
					units = Math.abs(left);
				}
			}
		}
		
		cleanEmptyItems();
		return true;
	}
	
	private void cleanEmptyItems() {
		boolean changed = false;
		for(int i = 0; i < copyOfItems().size(); i++) {
			if(copyOfItems().get(i).getUnits() <= 0) {
				items.remove(i);
				changed = true;
				break;
			}
		}
		
		if(changed)
			cleanEmptyItems();
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
	
	public double calcTotalValueInventory() {
		double total = 0;
		for(Item i: items) {
			total += i.getPricePerUnit()*i.getUnits();
		}
		
		return total;
	}
	public ArrayList<Info> getTransactionsFIFO(){
		ArrayList<Info> aux = new ArrayList<>(transactions);
		aux.addAll(copyOfItems());
		
		transactions.clear();
		return aux;
	}
	private ArrayList<Info> copyOfItems(){
		ArrayList<Info> aux = new ArrayList<>();
		
		for(Item i: items) {
			aux.add(new Item(i));
		}
		
		return aux;
	}
	
	public ArrayList<Info> getTransactionsPA(){
		ArrayList<Info> aux = new ArrayList<>(transactions);
		aux.addAll(copyOfItems());
		
		transactions.clear();
		return aux;
	}
}
