package entities;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Colocations")
public class Colocation  {

	@Id
	@Column(name = "name")
	private String name;
	
	@JoinColumn(name = "gestionnaire")
	private User gestionnaire;
	
	@JoinColumn
	@ManyToMany
	private ArrayList<User> colocataires = new ArrayList<User>();
	
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

	public ArrayList<User> getColocataires() {
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
   
}
