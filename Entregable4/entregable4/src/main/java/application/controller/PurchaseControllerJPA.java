package application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import application.entity.Purchase;
import application.repository.PurchaseRepository;

@RestController
@RequestMapping("purchases")
public class PurchaseControllerJPA {

	@Qualifier("purchaseRepository")
	@Autowired
	private final PurchaseRepository repository;

	public PurchaseControllerJPA(@Qualifier("purchaseRepository") PurchaseRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/")
	public List<Purchase> getPurchases() {
		List<Purchase> lista = new ArrayList<Purchase>();
		lista = repository.findAll();
		System.out.println(lista);
		return lista;
	}
	
	//Alta de compra
	@PostMapping("/")
	public Purchase newPurchase(@RequestBody Purchase p) {
		return repository.save(p);
	}
	
	//Baja de compra
	@DeleteMapping("/{id}")
	void deletePurchase(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	//Modificar compra
	@PutMapping("/{id}")
	Purchase replacePurchase(@RequestBody Purchase newPurchase, @PathVariable Long id) {

		return repository.findById(id)
				.map(purchase -> {
					purchase.setId(id);
					purchase.setDay(newPurchase.getDay());
					purchase.setMonth(newPurchase.getMonth());
					purchase.setYear(newPurchase.getYear());
					purchase.setClient(newPurchase.getClient());
					purchase.setProduct(newPurchase.getProduct());
					
					return repository.save(purchase);
				})
				.orElseGet(() -> {
					newPurchase.setId(id);
					return repository.save(newPurchase);
				});
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
