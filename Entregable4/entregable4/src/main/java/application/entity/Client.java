package application.entity;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "clients")
public class Client {

	@Id
	private long id;

	@Column
	private String name;
	
	@Column
	private String lastname;
	
	@OneToMany(mappedBy="client")
	@JsonIgnore
	private List<Purchase> products;
	

	public Client() {

	}
	public Client(String name, String lastname, long id) {
		super();
		this.name = name;
		this.lastname = lastname;
		this.products = new ArrayList<Purchase>();
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public List<Purchase> getProducts() {
		return products;
	}
	public void setProducts(List<Purchase> products) {
		this.products = products;
	}
	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", lastname=" + lastname + ", products=" + products + "]";
	}
	
	public Purchase adquireProduct(int day, int month, int year, Product product) {
		Purchase p = new Purchase(product, this, day, month, year);
		this.products.add(p);
		return p;
	}

}
