package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

public class Inventory {

	private ArrayList<String> registeredProducts;
	private Hashtable<String, Product> stock;
	
	public Inventory() {
		this.registeredProducts = new ArrayList<String>();
		this.stock = new Hashtable<String, Product>();
	}
	
	public boolean registerProduct(String productName) {
		if(registeredProducts.contains(productName))
			return false;
		
		registeredProducts.add(productName);
		return true;
	}
	
	public void buy(String productName, int units, double pricePerUnit, boolean paMode) {
		if(isInStock(productName)) {
			stock.get(productName).buy(units, pricePerUnit, paMode);
		}
		else {
			Product p = new Product(productName, 0);
			p.buy(units, pricePerUnit, paMode);
			stock.put(productName, p);
		}
	}
	public boolean sell(String productName, int units, boolean ppMode) {
		if(!isInStock(productName)) 
			return false;
		
		Product p = stock.get(productName);
		
		return p.sell(units, ppMode);
	}
	private boolean isInStock(String productName) {
		return stock.containsKey(productName);
	}
	
	public List<String> getStringRegisteredProducts(){
		return registeredProducts;
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
	
	
	public Product getProduct(String name) {
		return stock.get(name);
	}
}
