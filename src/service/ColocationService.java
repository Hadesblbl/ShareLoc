package service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import controller.ColocationManager;

@Path("/coloc")
public class ColocationService {
	
	@POST
	@Path("/addcoloc")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createColoc(@FormParam("colocName") String colocName, @FormParam("mail") String mail) {
		if (!ColocationManager.createColocation(colocName, mail)) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().build(); //TODO p-e rediriger
	}
	
	@POST
	@Path("/addtocoloc")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUserToColoc(@PathParam("coloc") String idcoloc,@PathParam("coloc") String iduser) {
		if(!ColocationManager.addUserToColoc(idcoloc, iduser)) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().build();
	}
	
	
	@POST
	@Path("/removefromcoloc")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeUserFromColoc(@PathParam("coloc") String idcoloc,@PathParam("coloc") String iduser) {
		if (!ColocationManager.removeUserFromColoc(idcoloc, iduser)) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().build();
	}
	
	
	@POST
	@Path("/changeadmin")
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeColocAdmin(@PathParam("coloc") String idcoloc,@PathParam("coloc") String iduser,@PathParam("admin") String idadmin) {
		if (!ColocationManager.changeColocAdmin(idcoloc, iduser, idadmin)) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().build();
	}
}
