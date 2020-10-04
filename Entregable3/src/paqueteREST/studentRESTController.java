package paqueteREST;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DAOStudentImpl;
import entity.Student;

@Path("/estudiantes")
public class studentRESTController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getAllStudents() {
		DAOStudentImpl daoEst = new DAOStudentImpl();
		return daoEst.getAll();
	}
	
	//Traer un estudiante por libreta universitaria
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Student getStudent(@PathParam("id") int i) {
		DAOStudentImpl daoEst = new DAOStudentImpl();
		return daoEst.getById(i);
	}
	
	@GET
	@Path("/genero/{gender}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentByGender(@PathParam("gender") char i) {
		DAOStudentImpl daoEst = new DAOStudentImpl();
		return daoEst.getAllByGender(i);
	}
	
	@GET
	@Path("/{city}/{career}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Student> getStudentByCityCareer(@PathParam("city") String city, @PathParam("career") int car) {
		DAOStudentImpl daoEst = new DAOStudentImpl();
		return daoEst.getByCityCareer(city, car);
	}
	
	//Persistir un student, se realiza un Json, donde no ponemos la "lu" porque es la PK
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createPerro(Student student) {
		DAOStudentImpl daoEst = new DAOStudentImpl();
		Student result= daoEst.addStudent(student);
		if(result == null) {
			return "El usuario no se pudo guardar";
		}else {
			return "El usuario se guardo con exito";
		}
	}
}
