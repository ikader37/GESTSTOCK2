package geststock.classes;

import geststock.classes.Articles;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-18T16:59:10")
@StaticMetamodel(Rangement.class)
public class Rangement_ { 

    public static volatile SingularAttribute<Rangement, Date> createdAt;
    public static volatile SingularAttribute<Rangement, Integer> updatedBy;
    public static volatile SingularAttribute<Rangement, Boolean> deleted;
    public static volatile SingularAttribute<Rangement, Integer> createdBy;
    public static volatile SingularAttribute<Rangement, String> libelle;
    public static volatile SingularAttribute<Rangement, Integer> id;
    public static volatile SingularAttribute<Rangement, String> designation;
    public static volatile ListAttribute<Rangement, Articles> articlesList;
    public static volatile SingularAttribute<Rangement, Date> updatedAt;

}