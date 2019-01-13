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
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.Status;

import controller.ColocationManager;
import controller.UserManager;
import entities.User;
import security.SigninNeeded;

@Path("/coloc")
public class ColocationService {

	@POST
	@Path("/addcoloc")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createColoc(@QueryParam("colocName") String colocName, @QueryParam("mail") String mail) {
		if (!ColocationManager.createColocation(colocName, mail)) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().build(); // TODO p-e rediriger
	}

	@POST
	@Path("/addtocoloc")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUserToColoc(@QueryParam("coloc") String idcoloc, @QueryParam("user") String iduser,
			@QueryParam("currentUser") String adminID) {
		if (!ColocationManager.addUserToColoc(idcoloc, iduser, adminID)) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().build();
	}

	@POST
	@Path("/removefromcoloc")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeUserFromColoc(@QueryParam("coloc") String idcoloc, @QueryParam("user") String iduser, @QueryParam("currentUser") String currentUserId) {
		if (!ColocationManager.removeUserFromColoc(idcoloc, iduser, currentUserId)) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().build();
	}

	@POST
	@Path("/changeadmin")
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeColocAdmin(@QueryParam("coloc") String idcoloc, @QueryParam("user") String iduser,
			@QueryParam("admin") String idadmin) {
		ColocationManager.changeColocAdmin(idcoloc, idadmin, iduser);
		if (!ColocationManager.changeColocAdmin(idcoloc, idadmin, iduser)) {
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
	
	@POST
	@SigninNeeded
	@Path("/sendMessage")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendMessage(@Context SecurityContext security, @PathParam("coloc") String colocID, @QueryParam("message") String message) {
		User user = UserManager.getUser(security.getUserPrincipal().getName());
		if (user!=null && !ColocationManager.sendMessage(security.getUserPrincipal().getName(), colocID, message)) 
			return Response.ok().build();
		return Response.status(Status.NETWORK_AUTHENTICATION_REQUIRED).build();	
	}
	
	@GET
	@SigninNeeded
	@Path("/listMessage")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listMessage(@Context SecurityContext security, @PathParam("coloc") String colocID) {
		User user = UserManager.getUser(security.getUserPrincipal().getName());
		if (user!=null)
			Response.ok().entity(ColocationManager.getMessages(colocID, security.getUserPrincipal().getName())).build();
		return Response.status(Status.NETWORK_AUTHENTICATION_REQUIRED).build();	
	}

}
