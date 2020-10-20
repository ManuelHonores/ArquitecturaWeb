package application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.dto.ClientPurchase;
import application.entity.Client;
import application.repository.ClientRepository;

@RestController
@RequestMapping("clients")
public class ClientControllerJPA {

	@Qualifier("clientRepository")
	@Autowired
	private final ClientRepository repository;

	public ClientControllerJPA(@Qualifier("clientRepository") ClientRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/")
	public List<Client> getClients() {
		List<Client> lista = new ArrayList<Client>();
		lista = repository.findAll();
		System.out.println(lista);
		return lista;
	}
	
	//Alta de cliente
	@PostMapping("/")
	public Client newClient(@RequestBody Client p) {
		return repository.save(p);
	}
	
	//Baja de cliente
	@DeleteMapping("/{id}")
	void deleteClient(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	//Modificar cliente
	@PutMapping("/{id}")
	Client replaceClient(@RequestBody Client newClient, @PathVariable Long id) {

		return repository.findById(id)
				.map(client -> {
					client.setId(id);
					client.setName(newClient.getName());
					client.setLastname(newClient.getLastname());
					return repository.save(client);
				})
				.orElseGet(() -> {
					newClient.setId(id);
					return repository.save(newClient);
				});
	}
	
	
	//Reporte de gastos por cliente
	@GetMapping("/report")
	public List<ClientPurchase> getReport() {
		List<ClientPurchase> report = new ArrayList<ClientPurchase>();
		List<Client> listClients = repository.findAll();
		
		for(int i=0; i<listClients.size(); i++) {
			int expense = repository.purchasesByClient(listClients.get(i).getId());
			ClientPurchase client = new ClientPurchase(listClients.get(i), expense);
			report.add(client);
		}
		
		return report;
	}

//	@GetMapping("/BySurname/{surname}")
//	public Iterable<Product> getProductsBySurname(@PathVariable String surname) {
//		return repository.findAllBySurname(surname);
//	}
//
//	@GetMapping("/ByName/{name}")
//	public Iterable<Product> getPersonsByName(@PathVariable String name) {
//		return repository.findAllByName(name);
//	}
//

//
//	@GetMapping("/{id}")
//	Optional<Product> one(@PathVariable Long id) {
//
//		return repository.findById(id);
//	}
}
