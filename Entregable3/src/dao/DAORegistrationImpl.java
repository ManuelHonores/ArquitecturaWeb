package dao;

import javax.persistence.EntityManager;

import EntityManagerFactory.EMF;
import entity.Registration;

public class DAORegistrationImpl extends EMF{
	EntityManager em = initialiceEntityManager();
	
	public Registration addResgistration(Registration registration) {
		em.getTransaction().begin();
		em.persist(registration);
		em.getTransaction().commit();
		em.close();
		return registration;
	}

}
