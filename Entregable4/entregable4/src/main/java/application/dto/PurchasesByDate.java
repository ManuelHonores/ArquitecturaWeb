package application.dto;

public class PurchasesByDate {
	
	private int day;
	private int month;
	private int year;
	
	private int quantity;

	public PurchasesByDate(int day, int month, int year, int quantity) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
		this.quantity = quantity;
	}
	
	public PurchasesByDate() {
		
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
}
