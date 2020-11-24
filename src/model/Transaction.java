package model;

import java.time.LocalDateTime;

public class Transaction implements Comparable<Transaction>{
	
	public enum TransactionType{
		BUY,
		SELL;
	}
	
	private TransactionType type;
	private int units;
	private LocalDateTime date;
	private double pricePerUnit;
	
	public Transaction(TransactionType type, int units, double pricePerUnit, LocalDateTime date) {
		this.type = type;
		this.units = units;
		this.pricePerUnit = pricePerUnit;
		this.date = date;
	}
	
	//This is to get subsets from a TreeSet, a bit hacky but it works ;).
	public static Transaction TransactionWithDate(LocalDateTime date) {
		return new Transaction(date);
	}
	private Transaction(LocalDateTime date) {
		this.date = date;
	}

	public String getType() {
		return type.toString();
	}

	public int getUnits() {
		return units;
	}

	public LocalDateTime getDate() {
		return date;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}
	
	public double getTotalPrice() {
		return pricePerUnit*units;
	}

	@Override
	public int compareTo(Transaction o) {
		return this.date.compareTo(o.date);
	}
	
}
