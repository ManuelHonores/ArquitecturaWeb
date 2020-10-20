package application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import application.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
	
	//Esto creo que no va
	@Query("SELECT client FROM Purchase p")
    public List<Purchase> findAllPurchase();
	
	//Retorna todas las ventas ordenadas por dia para luego generar el reporte
	@Query("SELECT p FROM Purchase p ORDER BY p.year, p.month, p.day")
    public List<Purchase>  purchasesByDate();
	
	//
	@Query("SELECT pp.id FROM Purchase p JOIN p.product pp GROUP BY pp.id ORDER BY COUNT(pp.id) DESC")
    public List<Long> purchases();
}
