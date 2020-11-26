package model;


public class Item implements Info{

	private int units;
	private double pricePerUnit;
	
	public Item(int units, double pricePerUnit) {
		this.units = units;
		this.pricePerUnit = pricePerUnit;
	}
	
	public void setUnits(int units) {
		this.units = units;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public Item(Item i) {
		this.units = i.units;
		this.pricePerUnit = i.pricePerUnit;
	}
	
	public void addUnits(int units) {
		this.units += units;
	}
	
	public int sellUnits(int units) {
		this.units -= units;
		return this.units;
	}
	
	public double getPricePerUnit() {
		return this.pricePerUnit;
	}

	@Override
	public String getType() {
		return "";
	}

	@Override
	public int getUnits() {
		return units;
	}
}
