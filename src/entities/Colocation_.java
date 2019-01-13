package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-01-12T18:47:55.860+0100")
@StaticMetamodel(Colocation.class)
public class Colocation_ {
	public static volatile SingularAttribute<Colocation, String> name;
	public static volatile SingularAttribute<Colocation, User> gestionnaire;
	public static volatile ListAttribute<Colocation, User> colocataires;
}
