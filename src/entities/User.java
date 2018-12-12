package entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int ID;
	
	@Column(name = "mail")
	private String mail;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "password")
	private String password;
	
	public User() {
		
	}
	
	public User(String mail, String firstname, String lastname, String password) {
		setMail(mail);
		setFirstname(firstname);
		setLastname(lastname);
		setPassword(password);
	}
	
	public int getID() {
		return ID;
	}
	
	public String getMail() {
		return mail;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

}
