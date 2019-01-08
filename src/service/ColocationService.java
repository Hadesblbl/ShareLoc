package service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import controller.ColocationManager;

@Path("/coloc")
public class ColocationService {
	
	@POST
	@Path("/addcoloc")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createColoc(@FormParam("colocName") String colocName, @FormParam("mail") String mail) {
		ColocationManager.createColocation(colocName, mail);
		return Response.ok().build(); //TODO p-e rediriger
	}
	
	@POST
	@Path("/addtocoloc")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUserToColoc(@PathParam("coloc") String idcoloc,@PathParam("coloc") String iduser) {
		ColocationManager.addUserToColoc(idcoloc, iduser); //TODO retrouver coloc et user depuis leurs id, rajouter cas d'erreur
		return Response.ok().build();
	}
	
	
	@POST
	@Path("/removefromcoloc")
	@Produces(MediaType.APPLICATION_JSON)
	public Response removeUserFromColoc(@PathParam("coloc") String idcoloc,@PathParam("coloc") String iduser) {
		ColocationManager.removeUserFromColoc(idcoloc, iduser); //TODO pareil
		return Response.ok().build();
	}
	
	
	@POST
	@Path("/changeadmin")
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeColocAdmin(@PathParam("coloc") String idcoloc,@PathParam("coloc") String iduser,@PathParam("admin") String idadmin) {
		ColocationManager.changeColocAdmin(idcoloc, iduser, idadmin);
		return Response.ok().build();
	}
}
