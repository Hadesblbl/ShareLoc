package service;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import database.DBAccess;


@Path("/user")
public class UserService {

		@POST
		@Path("/add")
		@Produces(MediaType.APPLICATION_JSON)
		public Response addUser(@FormParam("id") String id, @FormParam("pwd") String pwd) {
			System.out.println(id+" "+pwd);
			User user = new User(id,pwd);
			DBAccess.addUser(user);
			return Response.ok().build();
		}

		@POST
		@Path("/authenticate")
		@Produces(MediaType.APPLICATION_JSON)
		public Response authenticateUser(@FormParam("id") String id, @FormParam("pwd") String pwd) {
			System.out.println(id+" "+pwd);
			User user = new User(id,pwd);
			if (DBAccess.getUsers().contains(user)) {
				return Response.ok().build();
			}
			return Response.ok().build(); //TODO add error
		}
		
		@POST
		@Path("/createprofile")
		@Produces(MediaType.APPLICATION_JSON)
		public Response createProfile(@FormParam("id") String id, @FormParam("pwd") String pwd, @FormParam("fn") String firstname, @FormParam("ln") String lastname) {
			System.out.println(id+" "+pwd);//TODO define profile
			return Response.ok().build();
		}
		
		@POST
		@Path("/changeprofile")
		@Produces(MediaType.APPLICATION_JSON)
		public Response changeProfile(@FormParam("id") String id, @FormParam("pwd") String pwd) {
			System.out.println(id+" "+pwd);
			return Response.ok().build();
		}

}
