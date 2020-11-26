package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
		this.items = new ArrayList<Item>();
	}

	public void buy(int units, double pricePerUnit) {
		this.units += units;

		boolean exist = false;

		for(Item i: items) {
			if(i.getPricePerUnit() == pricePerUnit) {
				i.addUnits(units);
				exist = true;
				break;
			}
		}

		if(!exist)
			items.add(new Item(units, pricePerUnit));


		registerTransaction(TransactionType.BUY, units, pricePerUnit);
	}
	public boolean sell(int units) {
		if(this.units < units)
			return false;

		this.units -= units; //Here we're already sure we can sell this amount;
		
		for(Item i: items) {
			int left = i.sellUnits(units);
			
			if(left >= 0) { //Available units were more or equal that the amount sell
				System.out.println("more");
				registerTransaction(TransactionType.SELL, units, i.getPricePerUnit());
				System.out.println("Vendido: " + (units) + " a " + i.getPricePerUnit());
				break;
			}else {
				System.out.println("less");
				//Left is negative here
				registerTransaction(TransactionType.SELL, units + left, i.getPricePerUnit());
				System.out.println("Vendido: " + (units + left) + " a " + i.getPricePerUnit());
				units = Math.abs(left);
			}
		}

		System.out.println("------------------");
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
		System.out.println("Transaction registrada");
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
	
	public ArrayList<Info> getTransactions(){
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
	
	public List<Transaction> getTransactions(LocalDateTime from, LocalDateTime to){
		return new ArrayList<>(transactions.subSet(Transaction.TransactionWithDate(from), Transaction.TransactionWithDate(to)));
	}
}
