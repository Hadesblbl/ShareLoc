package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-01-12T18:47:55.864+0100")
@StaticMetamodel(User.class)
public class User_ {
	public static volatile SingularAttribute<User, String> mail;
	public static volatile SingularAttribute<User, String> firstname;
	public static volatile SingularAttribute<User, String> lastname;
	public static volatile SingularAttribute<User, String> password;
	public static volatile ListAttribute<User, AchievedService> services;
	public static volatile ListAttribute<User, Colocation> colocs;
}
