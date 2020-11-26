package model;

public class Item {

	private int units;
	private double pricePerUnit;
	
	public Item(int units, double pricePerUnit) {
		super();
		this.units = units;
		this.pricePerUnit = pricePerUnit;
	}
	
	public void addUnits(int units) {
		this.units += units;
	}
	
	public int sellUnits(int units) {
		this.units -= units;
		return this.units;
	}
	
	public boolean equals(Item i) {
		return this.pricePerUnit == i.pricePerUnit;
	}
	
	public boolean equals(double d) {
		return this.pricePerUnit == d;
	}
	
	public double getPricePerUnit() {
		return this.pricePerUnit;
	}
}
