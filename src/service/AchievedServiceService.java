package service;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import controller.AchievedServiceManager;
import controller.ServiceManager;
import controller.UserManager;

@Path("/achievedservice")
public class AchievedServiceService {


	@POST
	@Path("/declare")
	@Produces(MediaType.APPLICATION_JSON)
	public Response declareAchievedService(@FormParam("service") int serviceId,@FormParam("user")  String userId,@FormParam("image")  String image,@FormParam("coloc")  String colocId) {
		if (!AchievedServiceManager.declareAchievedService(serviceId, userId, image, colocId)) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().build();
	}


	@POST
	@Path("/validate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateServiceDeclaration(@FormParam("service") int achievedServiceId,@FormParam("user")  String userId,@FormParam("statement")  boolean statement) {
		if (!AchievedServiceManager.validateServiceDeclaration(achievedServiceId, userId, statement)) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().build();
	}
	
	@GET
	@Path("/achievedserviceinfo/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response serviceInfo(@PathParam("id") int id) {
		String service = AchievedServiceManager.getAchievedServiceInfo(id);
		if(service == null)
			return Response.status(Status.NOT_FOUND).build();
		return Response.ok().entity(service).build();
		
	}
	
	@GET
	@Path("/listachievedservices")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listAchievedServices() {
	return Response.ok().entity(AchievedServiceManager.getAllAchievedServices()).build();
	}
}
