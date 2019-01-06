import dao.DAOUser;
import entities.User;

public class Main {

	public static void main(String[] args) {
		DAOUser dUser = new DAOUser();
		User user = new User("toto@tata", "toto", "tata", "oui");
		dUser.set(user);
		User dbUser = dUser.get("toto@tata");
		System.out.println("Test : "+ dbUser.getMail() + dbUser.getFirstname() + dbUser.getLastname() + dbUser.getPassword());
	}
}
