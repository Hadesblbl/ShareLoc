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

import controller.ServiceManager;
import controller.UserManager;
import entities.User;	

@Path("/service")
public class ServiceService {

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createService(@Context SecurityContext security, @FormParam("coloc")  String colocID,@FormParam("title")  String title,@FormParam("description")  String description,@FormParam("cost")  int cost) {
		User user = UserManager.getUser(security.getUserPrincipal().getName());
		if (!ServiceManager.createService(user.getMail(), colocID, title, description, cost)) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().build();
	}

	@POST
	@Path("/vote")
	@Produces(MediaType.APPLICATION_JSON)
	public Response voteService(@Context SecurityContext security, @FormParam("service") int serviceID, @FormParam("response")  boolean response) {
		User user = UserManager.getUser(security.getUserPrincipal().getName());
		if (!ServiceManager.voteService(user.getMail(), serviceID, response)) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().build();
	}
	

	@POST
	@Path("/validate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateService(@Context SecurityContext security,@FormParam("service")  int serviceID) {
		User user = UserManager.getUser(security.getUserPrincipal().getName());
		if (!ServiceManager.validateService(user.getMail(), serviceID)) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().build();
	}
	
	@GET
	@Path("/serviceinfo/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response serviceInfo(@PathParam("id") int id) {
		String service = ServiceManager.getServiceInfo(id);
		if(service == null)
			return Response.status(Status.NOT_FOUND).build();
		return Response.ok().entity(service).build();
		
	}
	
	@GET
	@Path("/listservices")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listServices() {
		return Response.ok().entity(ServiceManager.getAllServices()).build();
	}

}