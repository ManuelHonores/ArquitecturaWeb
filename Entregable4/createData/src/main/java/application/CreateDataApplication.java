package application;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import application.entity.Client;
import application.entity.Product;
import application.entity.Purchase;
import application.repository.ClientRepository;
import application.repository.ProductRepository;
import application.repository.PurchaseRepository;

@SpringBootApplication
public class CreateDataApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreateDataApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner run(ProductRepository productRepository, ClientRepository clientRepository, PurchaseRepository purchaseRepository) throws Exception {
        return (String[] args) -> {
            Product p1 = new Product("Yerba", 20, 1, 100);
            Product p2 = new Product("Alfajor", 10, 2, 50);
            Product p3 = new Product("Manteca", 30, 3, 120);
            Product p4 = new Product("Harina", 15, 4, 80);
            Product p5 = new Product("Maicena", 5, 5, 60);
            Product p6 = new Product("Detergente", 35, 6, 40);
            Product p7 = new Product("Lavandina", 50, 7, 30);
            Product p8 = new Product("Alcohol", 8, 8, 100);
            Product p9 = new Product("Rollo de cocina", 22, 9, 80);
            
            Client c1 = new Client("Manuel", "Gosende", 1);
            Client c2 = new Client("Matias", "Molleker", 2);
            Client c3 = new Client("Manuel", "Honores", 3);
            Client c4 = new Client("Maria", "Bezerra", 4);
            Client c5 = new Client("Peque", "Schuarzman", 5);
            Client c6 = new Client("Pipa", "Higuain", 6);
            
            Purchase pur1 = c1.adquireProduct(7,6,2001, p8);
            Purchase pur2 = c2.adquireProduct(30,1,2011, p1);
            Purchase pur3 = c3.adquireProduct(10,6,1996, p2);
            Purchase pur4 = c4.adquireProduct(14,6,1998, p3);
            Purchase pur5 = c5.adquireProduct(23,7,2009, p7);
            Purchase pur6 = c6.adquireProduct(11,4,2015, p9);
            Purchase pur7 = c6.adquireProduct(22,17,2008, p6);
            
            
            productRepository.save(p1);
            productRepository.save(p2);
            productRepository.save(p3);
            productRepository.save(p4);
            productRepository.save(p5);
            productRepository.save(p6);
            productRepository.save(p7);
            productRepository.save(p8);
            productRepository.save(p9);
            
            clientRepository.save(c1);
            clientRepository.save(c2);
            clientRepository.save(c3);
            clientRepository.save(c4);
            clientRepository.save(c5);
            clientRepository.save(c6);
            
            purchaseRepository.save(pur1);
            purchaseRepository.save(pur2);
            purchaseRepository.save(pur3);
            purchaseRepository.save(pur4);
            purchaseRepository.save(pur5);
            purchaseRepository.save(pur6);
            purchaseRepository.save(pur7);
        };
	}
}
