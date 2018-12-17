package entities;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class User {

	@Id
	@Column(name = "mail")
	private String mail;
	
	@Column(name = "firstname")
	private String firstname;
	
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "password")
	private String password;
	
	@OneToMany(mappedBy="from")
	@JoinColumn(name="AchievedServices", referencedColumnName="ID")
	private ArrayList<AchievedService> services = new ArrayList<AchievedService>();
	
	@ManyToMany
	private ArrayList<Colocation> colocs = new ArrayList<Colocation>();
	
	public User() {
		
	}
	
	public User(String mail, String firstname, String lastname, String password) {
		setMail(mail);
		setFirstname(firstname);
		setLastname(lastname);
		setPassword(password);
	}
	
	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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
