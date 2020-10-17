package application.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name= "products")
public class Product {
	
	 @Id
	 private int id;
	 
	 @Column
	 private String name;
	 
	 @Column
	 private int val;
	 
	 @Column
	 private int stock;
	 
	 @OneToMany(mappedBy="product")
	 private List<Purchase> clients;
	 
	 public Product() {
		 
	 }
	 
	public Product(String name, int stock, int id, int val) {
		super();
		this.name = name;
		this.stock = stock;
		this.clients = new ArrayList<Purchase>();
		this.id = id;
		this.val = val;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public List<Purchase> getClients() {
		return clients;
	}

	public void setClients(List<Purchase> clients) {
		this.clients = clients;
	}

}
