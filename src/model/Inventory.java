package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Inventory {

	private Hashtable<String, Product> registeredProducts;
	private Hashtable<String, Product> stock;
	private ArrayList<Transaction> transactions;
	private TreeSet<Transaction> trans;
	
	public Inventory() {
		this.registeredProducts = new Hashtable<String, Product>();
		this.stock = new Hashtable<String, Product>();
	}
	
	public boolean registerProduct(String productName) {
		if(registeredProducts.containsKey(productName))
			return false;
		
		registeredProducts.put(productName, new Product(productName, 0));
		return true;
	}
	
	public void buy(String productName, int amount, double pricePerUnit) {
		if(isInStock(productName)) {
			stock.get(productName).addUnits(amount);
		}
		else {
			Product p = registeredProducts.get(productName);
			p.addUnits(amount);
			stock.put(productName, p);
		}
	}
	public boolean sell(String productName, int amount) {
		if(!isInStock(productName)) 
			return false;
		
		Product p = stock.get(productName);
		
		if(p.getAmount() < amount)
			return false;
		
		p.substractUnits(amount);
		
		if(p.getAmount() == 0)
			stock.remove(productName);
		
		return true;
	}
	private boolean isInStock(String productName) {
		return stock.containsKey(productName);
	}
	
	public List<String> getStringRegisteredProducts(){
		List<String> aux = registeredProducts.keySet().stream().collect(Collectors.toList());
		aux.sort(new Comparator<String>() {
			@Override
			public int compare(String arg0, String arg1) {
				return arg0.compareTo(arg1);
			}
		});
		
		return aux;
	}
	public List<Product> getStringStockProducts(){
		
		List<Product> aux2 = stock.values().stream().collect(Collectors.toList());

		
		return aux2;
	}
	
	public List<String> getStockString(){
		List<String> aux = stock.keySet().stream().collect(Collectors.toList());
		aux.sort(new Comparator<String>() {
			@Override
			public int compare(String arg0, String arg1) {
				return arg0.compareTo(arg1);
			}
		});
		return aux;
	}
}
