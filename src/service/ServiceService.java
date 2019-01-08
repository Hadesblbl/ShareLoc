package service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import controller.ServiceManager;

@Path("/service")
public class ServiceService {

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createService(@FormParam("user") String userID,@FormParam("coloc")  String colocID,@FormParam("title")  String title,@FormParam("description")  String description,@FormParam("cost")  int cost) {
		if (!ServiceManager.createService(userID, colocID, title, description, cost)) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().build();
	}

	@POST
	@Path("/vote")
	@Produces(MediaType.APPLICATION_JSON)
	public Response voteService(@FormParam("user") String userID, @FormParam("service") int serviceID, @FormParam("response")  boolean response) {
		if (!ServiceManager.voteService(userID, serviceID, response)) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().build();
	}
	

	@POST
	@Path("/validate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateService(@FormParam("user") String userID,@FormParam("service")  int serviceID) {
		if (!ServiceManager.validateService(userID, serviceID)) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().build();
	}
}