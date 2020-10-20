package application.dto;

import application.entity.Client;

public class ClientPurchase {
	
	private Client client;
    private int expenses ;
    
	public ClientPurchase(Client client, int expenses) {
		super();
		this.client = client;
		this.expenses = expenses;
	}

	public ClientPurchase() {
		
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public int getExpenses() {
		return expenses;
	}

	public void setExpenses(int expenses) {
		this.expenses = expenses;
	}
	
	
}
