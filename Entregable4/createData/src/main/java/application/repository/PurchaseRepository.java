package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {}
