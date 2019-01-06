package service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import controller.ColocationManager;
import controller.UserManager;
import entities.Colocation;
import entities.User;

@Path("/coloc")
public class ColocationService {


/*	@POST
	@Path("/modify")
	@Produces(MediaType.APPLICATION_JSON)
	public Response modifyColoc(@FormParam("colocName") String colocName, @FormParam("mail") String mail) {
		System.out.println(colocName + " " + mail);
		return Response.ok().build();
	}

	@POST
	@Path("/inviteuser")
	@Produces(MediaType.APPLICATION_JSON)
	public Response inviteUser(@FormParam("id") String id, @FormParam("pwd") String pwd) {
		System.out.println(id + " " + pwd);
		
		return Response.ok().build();
	}

	@POST
	@Path("/declareservice")
	@Produces(MediaType.APPLICATION_JSON)
	public Response declareService(@FormParam("id") String id, @FormParam("pwd") String pwd) {
		System.out.println(id + " " + pwd);
		return Response.ok().build();
	}

	@POST
	@Path("/newservice")
	@Produces(MediaType.APPLICATION_JSON)
	public Response newService(@FormParam("id") String id, @FormParam("pwd") String pwd) {
		System.out.println(id + " " + pwd);
		return Response.ok().build();
	}

	@POST
	@Path("/voteService")
	@Produces(MediaType.APPLICATION_JSON)
	public Response voteService(@FormParam("id") String id, @FormParam("pwd") String pwd) {
		System.out.println(id + " " + pwd);
		return Response.ok().build();
	}

	@POST
	@Path("/validateService")
	@Produces(MediaType.APPLICATION_JSON)
	public Response validateService(@FormParam("id") String id, @FormParam("pwd") String pwd) {
		System.out.println(id + " " + pwd);
		return Response.ok().build();
	}

	@POST
	@Path("/chat")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getChat(@FormParam("id") String id, @FormParam("pwd") String pwd) {
		System.out.println(id + " " + pwd);
		return Response.ok().build();
	}
	*/
	
	@POST
	@Path("/addcoloc")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createColoc(@FormParam("colocName") String colocName, @FormParam("mail") String mail) {
		User user = UserManager.getUser(mail);
		Colocation coloc = new Colocation(colocName, user);
		ColocationManager.createColocation(coloc, user);
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
