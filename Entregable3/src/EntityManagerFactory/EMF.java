package EntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EMF {
	private EntityManagerFactory emf;
	private EntityManager em;
	
	public EntityManager initialiceEntityManager() {
		emf = Persistence.createEntityManagerFactory("exampleDB");
		em = emf.createEntityManager();
		return em;
	}
	
	public void closeEntityManager() {
		this.em.close();
		this.emf.close();
	}

//	public void contextInitialized() {
//		emf = Persistence.createEntityManagerFactory("exampleDB");
//	}
//
//	public void contextDestroyed() {
//		emf.close();
//	}
//
//	public static EntityManager createEntityManager() {
//		if (emf == null) {
//			throw new IllegalStateException("Context is not initialized yet.");
//		}
//		return emf.createEntityManager();
//	}
}
