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

import application.dto.ClientPurchase;
import application.dto.PurchasesByDate;
import application.entity.Client;
import application.entity.Product;
import application.entity.Purchase;
import application.repository.ProductRepository;
import application.repository.PurchaseRepository;

@RestController
@RequestMapping("purchases")
public class PurchaseControllerJPA {

	@Qualifier("purchaseRepository")
	@Autowired
	private final PurchaseRepository repository;
	@Qualifier("productRepository")
	@Autowired
	private final ProductRepository repositoryProduct;

	public PurchaseControllerJPA(@Qualifier("purchaseRepository") PurchaseRepository repository, @Qualifier("productRepository") ProductRepository repositoryProduct) {
		this.repository = repository;
		this.repositoryProduct = repositoryProduct;
	}

//	@GetMapping("/")
//	public List<Purchase> getPurchases() {
//		List<Purchase> lista = new ArrayList<Purchase>();
//		lista = repository.findAll();
//		System.out.println(lista);
//		return lista;
//	}
//	
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

	@GetMapping("/")
	public List<Purchase> findAllPurchase() {
		List<Purchase> lista = new ArrayList<Purchase>();
		lista = repository.findAll();
		System.out.println(lista);
		return lista;
	}
	
	//Reporte de ventas por dia
	@GetMapping("/report")
	public List<PurchasesByDate> getReport() {
		List<PurchasesByDate> report = new ArrayList<PurchasesByDate>();
		List<Purchase> purchases = repository.purchasesByDate();
		PurchasesByDate purchase = new PurchasesByDate();
		purchase = null;
		for(int i=0; i<purchases.size(); i++) {
			if(purchase == null) {
				purchase = new PurchasesByDate(purchases.get(i).getDay(),purchases.get(i).getMonth(),purchases.get(i).getYear(), 1);
			} else if((purchases.get(i).getDay() == purchase.getDay()) && (purchases.get(i).getMonth() == purchase.getMonth()) && (purchases.get(i).getYear() == purchase.getYear())) {
				purchase.setQuantity(purchase.getQuantity() + 1);
			} else {
				report.add(new PurchasesByDate(purchase.getDay(), purchase.getMonth(), purchase.getYear(), purchase.getQuantity()));
				purchase.setDay(purchases.get(i).getDay());
				purchase.setMonth(purchases.get(i).getMonth());
				purchase.setYear(purchases.get(i).getYear());
				purchase.setQuantity(1);
			}
			
			if(i == purchases.size() - 1) {
				report.add(new PurchasesByDate(purchase.getDay(), purchase.getMonth(), purchase.getYear(), purchase.getQuantity()));
			}
		}
		
		return report;
	}
	
	//Producto mas vendido
	@GetMapping("/report/purchase")
	public Product productByPurchase() {
		
		List<Long> listPurchaseId = repository.purchases();
		
		Long id = listPurchaseId.get(0);
		
		List<Product> prod = repositoryProduct.findId(id);
		
		Product product = prod.get(0);
		
		return product;
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
