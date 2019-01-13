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

import controller.UserManager;
import entities.User;

@Path("/user")
public class UserService {

	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(@FormParam("email") String email, @FormParam("firstname") String firstname,
			@FormParam("lastname") String lastname, @FormParam("password") String password) {
		if (UserManager.createUser(email, firstname, lastname,password))
			return Response.ok().build();
		return Response.status(Status.FORBIDDEN).build();
	}

	@POST
	@Path("/authenticate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateUser(@FormParam("email") String email, @FormParam("password") String password) {
		User user = UserManager.getUser(email);
		if (user == null)
			return Response.status(Status.NOT_FOUND).build();
		if (UserManager.login(email, password) != null)
			return Response.status(Status.FORBIDDEN).build();
		return Response.ok().build();
	}

	@POST
	@Path("/changeprofile")
	@Produces(MediaType.APPLICATION_JSON)
	public Response changeProfile(@Context SecurityContext security,@FormParam("firstname") String firstname,@FormParam("lastname") String lastname, @FormParam("pwd") String pwd) {
		User user = UserManager.getUser(security.getUserPrincipal().getName());
		User newProfil = new User(user.getMail(), firstname, lastname, pwd);
		if(!UserManager.changeProfile(user, newProfil))
			return Response.status(Status.FORBIDDEN).build();
		else {
			return Response.ok().build();
		}
	}
	
	@GET
	@Path("/userinfo/{mail}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response userInfo(@PathParam("mail") String mail) {
		User user = UserManager.getUser(mail);
		if(user == null)
			return Response.status(Status.NOT_FOUND).build();
		return Response.ok().entity(UserManager.getUserInfo(mail)).build();
		
	}
	
	@GET
	@Path("/listusers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response listUsers() {
		return Response.ok().entity(UserManager.getAllUsers()).build();
	}

}
