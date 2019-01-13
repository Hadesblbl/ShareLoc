package service;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import controller.AchievedServiceManager;
import controller.UserManager;
import entities.User;

@Path("/achievedservice")
public class AchievedServiceService {


	@POST
	@Path("/declare")
	@Produces(MediaType.APPLICATION_JSON)
	public Response declareAchievedService(@Context SecurityContext security,@FormParam("service") int serviceId,@FormParam("image")  String image,@FormParam("coloc")  String colocId) {
		User user = UserManager.getUser(security.getUserPrincipal().getName());
		if (!AchievedServiceManager.declareAchievedService(serviceId, user.getMail(), image, colocId)) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().build();
	}
	
	@POST
	@Path("/vote")
	@Produces(MediaType.APPLICATION_JSON)
	public Response voteAchievedService(@Context SecurityContext security, @FormParam("service") int achievedServiceId, @FormParam("statement")  boolean statement) {
		User user = UserManager.getUser(security.getUserPrincipal().getName());
		if (!AchievedServiceManager.voteAchievedService(achievedServiceId, user.getMail(), statement)) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().build();
	}


	@POST
	@Path("/validate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateServiceDeclaration(@Context SecurityContext security, @FormParam("service") int achievedServiceId) {
		User user = UserManager.getUser(security.getUserPrincipal().getName());
		if (!AchievedServiceManager.validateServiceDeclaration(achievedServiceId, user.getMail()))
			return Response.status(Status.FORBIDDEN).build();
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
