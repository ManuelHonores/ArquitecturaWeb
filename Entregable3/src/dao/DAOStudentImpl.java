package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import EntityManagerFactory.EMF;
import entity.Student;


public class DAOStudentImpl extends EMF{
	EntityManager em = initialiceEntityManager();
	
	public List<Student> getAll() {
		Query query2c = em.createQuery("SELECT s FROM Student s ORDER BY age", Student.class);
		@SuppressWarnings("unchecked")
		List<Student> students2c = query2c.getResultList();
		return students2c;
	}
	
	public Student getById(int id) {
		Student student2d = new Student();
		Query query2d = em.createQuery("SELECT s FROM Student s WHERE lu = ?1", Student.class);
		query2d.setParameter(1, id);
		student2d = (Student) query2d.getSingleResult();
		return student2d;
	}
	
	public List<Student> getAllByGender(char gen) {
		Query query2e = em.createQuery("SELECT s FROM Student s WHERE gender = ?1", Student.class);
		query2e.setParameter(1, gen);
		@SuppressWarnings("unchecked")
		List<Student> students2e = query2e.getResultList();
		return students2e;
	}
	
	public List<Student> getByCityCareer(String city, int career) {
		Query query2g = em.createQuery(
				"SELECT DISTINCT(s) FROM Student s, Registration r WHERE s.city = ?1 AND r.career.id = ?2");
		query2g.setParameter(1, city);
		query2g.setParameter(2, career);
		@SuppressWarnings("unchecked")
		List<Student> students2g = query2g.getResultList();
		return students2g;
	}
	
	//Metodo para poder insertar un estudiante
	
	public Student addStudent(Student student) {
		em.getTransaction().begin();
		em.persist(student);
		em.getTransaction().commit();
		em.close();
		return student;
	}
}
