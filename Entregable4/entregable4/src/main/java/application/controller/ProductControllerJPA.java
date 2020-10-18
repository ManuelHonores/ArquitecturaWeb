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

import application.entity.Product;
import application.repository.ProductRepository;

@RestController
@RequestMapping("products")
public class ProductControllerJPA {

	@Qualifier("productRepository")
	@Autowired
	private final ProductRepository repository;

	public ProductControllerJPA(@Qualifier("productRepository") ProductRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/")
	public List<Product> getProducts() {
		List<Product> lista = new ArrayList<Product>();
		lista = repository.findAll();
		System.out.println(lista);
		return lista;
	}
	
	//Alta de producto
	@PostMapping("/")
	public Product newProduct(@RequestBody Product p) {
		return repository.save(p);
	}
	
	//Baja de producto
	@DeleteMapping("/{id}")
	void deleteProduct(@PathVariable long id) {
		repository.deleteById(id);
	}
	
	//Modificar producto
	@PutMapping("/{id}")
	Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id) {

		return repository.findById(id)
				.map(product -> {
					product.setId(id);
					product.setName(newProduct.getName());
					product.setStock(newProduct.getStock());
					product.setVal(newProduct.getVal());
					return repository.save(product);
				})
				.orElseGet(() -> {
					newProduct.setId(id);
					return repository.save(newProduct);
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
