package service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
		System.out.println(email + " " + password);
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
	public Response changeProfile(@FormParam("mail") String mail,@FormParam("firstname") String firstname,@FormParam("lastname") String lastname, @FormParam("pwd") String pwd) {
		System.out.println(mail + " " + pwd);
		User user = UserManager.getUser(mail);
		User newProfil = new User(mail, firstname, lastname, pwd);
		if(!UserManager.changeProfile(user, newProfil))
			return Response.status(Status.FORBIDDEN).build();
		else {
			return Response.ok().build();
		}
	}

}
