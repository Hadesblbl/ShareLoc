package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-01-12T18:47:55.862+0100")
@StaticMetamodel(Service.class)
public class Service_ {
	public static volatile SingularAttribute<Service, Integer> ID;
	public static volatile SingularAttribute<Service, Colocation> coloc;
	public static volatile SingularAttribute<Service, String> title;
	public static volatile SingularAttribute<Service, String> description;
	public static volatile SingularAttribute<Service, Integer> cost;
	public static volatile SingularAttribute<Service, Boolean> isValidated;
	public static volatile MapAttribute<Service, User, Boolean> votes;
}
