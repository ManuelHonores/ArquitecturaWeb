package application.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name= "purchase")
public class Purchase{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	private Product product;

	@ManyToOne
	private Client client;
	
	@Column
	private int day;
	
	@Column
	private int month;
	
	@Column
	private int year;
	
	public Purchase() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Purchase(Product product, Client client, int day, int month, int year) {
		super();
		this.product = product;
		this.client = client;
		this.day = day;
		this.month = month;
		this.year = year;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	
	
}
