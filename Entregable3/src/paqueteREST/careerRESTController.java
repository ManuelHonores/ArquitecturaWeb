package paqueteREST;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DAOCareerImpl;
import entity.Career;

@Path("/carreras")
public class careerRESTController {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Career> getAllStudents() {
		DAOCareerImpl daoC = new DAOCareerImpl();
		return daoC.getAllEnrolled();
	}
	
	@GET
	@Path("/reporte") 
	@Produces(MediaType.APPLICATION_JSON)
	public List<Object> getReporte() {
		DAOCareerImpl daoC = new DAOCareerImpl();
		return daoC.getReport();
	}
}
