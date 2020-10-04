package main;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


import entity.Career;
import entity.Student;
import entity.Registration;

public class Creator {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("exampleDB");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Student one = new Student(1, "Marcos", "Mujica", 20, 'M', "Tandil");
		Student two = new Student(2, "Julian", "Gutierres", 21, 'M', "Tandil");
		Student three = new Student(3, "Manuel", "Gonzalez", 22, 'M', "Tandil");
		Student four = new Student(4, "Leonardo", "Lopez", 21, 'M', "Necochea");
		Student five = new Student(5, "Agustin", "Gosende", 20, 'M', "Azul");
		Student six = new Student(6, "Fernando", "Honores", 20, 'M', "Juarez");
		Student seven = new Student(7, "Maria", "Becerra", 21, 'F', "Azul");
		Student eight = new Student(8, "Julieta", "Brandi", 22, 'F', "Olavarria");
		Student nine = new Student(9, "Sol", "Vazquez", 23, 'F', "Olavarria");
		Student ten = new Student(10, "Lara", "Navarro", 27, 'F', "Barker");
		Student eleven = new Student(11, "Ignacio", "Lopez", 40, 'M', "Balcarce");
		
		Career tudai = new Career("Tudai");
		Career tupar = new Career("Tupar");
		Career fisic = new Career("Fisic");
		
		Registration t = tudai.registerStudent(one, false, 2017, null);
		Registration t1 = tudai.registerStudent(two, false, 2013, 2016);
		Registration t2 = tudai.registerStudent(three, false, 2017, null);
		Registration t3 = tudai.registerStudent(four, true, 2014, 2018);
		Registration t4 = tudai.registerStudent(five, true, 2018, 2020);
		Registration t5 = tupar.registerStudent(six, false, 2019, null);
		Registration t6 = tupar.registerStudent(seven, false, 2019, null);
		Registration t7 = tupar.registerStudent(eight, true, 2015, 2019);
		Registration t8 = fisic.registerStudent(nine, false, 2014, null);
		Registration t9 = fisic.registerStudent(ten, true, 2011, 2015);
		Registration t10 = fisic.registerStudent(eleven, true, 2007, 2016);
		
		em.persist(one);
		em.persist(two);
		em.persist(three);
		em.persist(four);
		em.persist(five);
		em.persist(six);
		em.persist(seven);
		em.persist(eight);
		em.persist(nine);
		em.persist(ten);
		em.persist(eleven);
		
		em.persist(tudai);
		em.persist(tupar);
		em.persist(fisic);
		
		em.persist(t);
		em.persist(t1);
		em.persist(t2);
		em.persist(t3);
		em.persist(t4);
		em.persist(t5);
		em.persist(t6);
		em.persist(t7);
		em.persist(t8);
		em.persist(t9);
		em.persist(t10);
		
		em.getTransaction().commit();
		em.close();
		emf.close();
		
	}

}
