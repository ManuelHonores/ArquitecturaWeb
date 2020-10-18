package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {}

