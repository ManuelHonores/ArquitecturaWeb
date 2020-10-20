package application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import application.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	
	//Consultamos los gastos de un cliente
	@Query("SELECT SUM(p.val) from Client c JOIN c.products cp JOIN cp.product p WHERE c.id =:id")
    public int purchasesByClient(Long id);
}
