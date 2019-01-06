package service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import controller.ColocationManager;

@Path("/coloc")
public class ColocationService {

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response createColoc(@FormParam("colocName") String colocName, @FormParam("mail") String mail) {
		ColocationManager.createColocation(colocName, mail);
		return Response.ok().build();
	}

	@POST
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

}
