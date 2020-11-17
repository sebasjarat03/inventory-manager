package model;

public class Product {

	private String name;
	private int amount;
	
	public Product(String name, int amount) {
		super();
		this.name = name;
		this.amount = amount;
	}

	public void addUnits(int amount) {
		this.amount += amount;
	}
	public void substractUnits(int amount) {
		this.amount -= amount;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
