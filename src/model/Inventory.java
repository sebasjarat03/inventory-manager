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
	
	public void buy(String productName, int units, double pricePerUnit) {
		if(isInStock(productName)) {
			stock.get(productName).buy(units, pricePerUnit);
		}
		else {
			Product p = new Product(productName, 0);
			p.buy(units, pricePerUnit);
			stock.put(productName, p);
		}
	}
	public boolean sell(String productName, int units, double pricePerUnit) {
		if(!isInStock(productName)) 
			return false;
		
		Product p = stock.get(productName);
		
		if(p.getUnits() < units)
			return false;
		
		p.sell(units, pricePerUnit);
		
		return true;
	}
	private boolean isInStock(String productName) {
		return stock.containsKey(productName);
	}
	
	public List<String> getStringRegisteredProducts(){
		return registeredProducts;
	}
	
	public List<Product> getStockProducts(){
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
	
	public List<Product> getSellableProducts(){
		List<Product> aux2 = stock.values().stream()
				.filter(p -> p.getUnits() > 0)
				.collect(Collectors.toList());
		return aux2;
	}
	public List<String> getSellableString(){
		List<String> aux = getSellableProducts().stream().map(Product::getName).collect(Collectors.toList());
		return aux;
	}
}
