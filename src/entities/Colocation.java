package entities;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Colocations")
public class Colocation  {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private int ID;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "colocataires")
	private ArrayList<User> colocataires = new ArrayList<User>();
	
	public Colocation() {
		
	}
	
	public int getID() {
		return ID;
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
	
	public boolean removeColocataire(User user) {
		return colocataires.remove(user);
	}

	public void setColocataires(ArrayList<User> colocataires) {
		this.colocataires = colocataires;
	}
   
}
