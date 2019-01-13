package service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import controller.ColocationManager;
import controller.UserManager;
import entities.User;

@Path("/coloc")
public class ColocationService {

	@POST
	@Path("/addcoloc")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createColoc(@Context SecurityContext security, @QueryParam("colocName") String colocName) {
		User user = UserManager.getUser(security.getUserPrincipal().getName());
		if (!ColocationManager.createColocation(colocName, user.getMail())) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().build();
	}

	@POST
	@Path("/addtocoloc")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUserToColoc(@Context SecurityContext security, @QueryParam("coloc") String idcoloc, @QueryParam("user") String iduser) {
		User admin = UserManager.getUser(security.getUserPrincipal().getName());
		if (!ColocationManager.addUserToColoc(idcoloc, iduser, admin.getMail())) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().build();
	}

	@POST
	@Path("/removefromcoloc")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeUserFromColoc(@Context SecurityContext security, @QueryParam("coloc") String idcoloc, @QueryParam("user") String iduser) {
		User currentUser = UserManager.getUser(security.getUserPrincipal().getName());
		if (!ColocationManager.removeUserFromColoc(idcoloc, iduser, currentUser.getMail())) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().build();
	}

	@POST
	@Path("/changeadmin")
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeColocAdmin(@Context SecurityContext security,@QueryParam("coloc") String idcoloc, @QueryParam("user") String iduser) {
		User admin = UserManager.getUser(security.getUserPrincipal().getName());
		ColocationManager.changeColocAdmin(idcoloc, admin.getMail(), iduser);
		if (!ColocationManager.changeColocAdmin(idcoloc, admin.getMail(), iduser)) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().build();
	}
	
	@GET
	@Path("/colocinfo/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response colocInfo(@PathParam("id") String id) {
		String coloc = ColocationManager.getColocInfo(id);
		if(coloc == null)
			return Response.status(Status.NOT_FOUND).build();
		return Response.ok().entity(coloc).build();
		
	}
	
	@GET
	@Path("/listcolocs")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listColocs() {
		return Response.ok().entity(ColocationManager.getAllColocs()).build();
	}

}
