package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Colocations")
public class Colocation  {

	@Id
	@Column(name = "name")
	private String name;

	@JoinColumn(name="Gestionnaire", referencedColumnName="mail")
	private User gestionnaire;
	
	@ManyToMany(mappedBy = "colocs")
	@JoinColumn(name="Colocataires", referencedColumnName="mail")
	private List<User> colocataires = new ArrayList<User>();
	
	@OneToMany(mappedBy="coloc")
	private List<Message> messages = new ArrayList<Message>();
	
	public Colocation() {
		
	}
	
	public Colocation(String name, User gestionnaire) {
		setName(name);
		setGestionnaire(gestionnaire);
		colocataires.add(gestionnaire);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<User> getColocataires() {
		return colocataires;
	}
	
	public boolean addColocataire(User user) {
		return colocataires.add(user);
	}
	
	public User getGestionnaire() {
		return gestionnaire;
	}

	public void setGestionnaire(User gestionnaire) {
		this.gestionnaire = gestionnaire;
	}

	public boolean removeColocataire(User user) {
		return colocataires.remove(user);
	}

	public void setColocataires(ArrayList<User> colocataires) {
		this.colocataires = colocataires;
	}
	
	public List<Message> getMessages(){
		return messages;
	}
	
	public void setMessage(ArrayList<Message> messages) {
		this.messages = messages;
	}
   
}
