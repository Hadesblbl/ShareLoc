package entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.MapAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2019-01-12T18:47:55.766+0100")
@StaticMetamodel(AchievedService.class)
public class AchievedService_ {
	public static volatile SingularAttribute<AchievedService, Integer> ID;
	public static volatile SingularAttribute<AchievedService, User> from_;
	public static volatile ListAttribute<AchievedService, User> to;
	public static volatile SingularAttribute<AchievedService, String> date;
	public static volatile SingularAttribute<AchievedService, String> image;
	public static volatile SingularAttribute<AchievedService, Boolean> valid;
	public static volatile SingularAttribute<AchievedService, Service> service;
	public static volatile MapAttribute<AchievedService, User, Boolean> votes;
}
