package paqueteREST;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.DAORegistrationImpl;
import entity.Registration;

@Path("/registro")
public class registrationRESTController {
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String createPerro(Registration registration) {
		DAORegistrationImpl daoRes = new DAORegistrationImpl();
		Registration result= daoRes.addResgistration(registration);
		if(result == null) {
			return "El usuario no se pudo guardar";
		}else {
			return "El usuario se guardo con exito";
		}
	}

}
