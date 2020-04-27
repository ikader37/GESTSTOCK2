package geststock.classes;

import geststock.classes.Articles;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-18T16:59:10")
@StaticMetamodel(Fournisseurs.class)
public class Fournisseurs_ { 

    public static volatile SingularAttribute<Fournisseurs, Date> createdAt;
    public static volatile SingularAttribute<Fournisseurs, Date> updatedBy;
    public static volatile SingularAttribute<Fournisseurs, Boolean> deleted;
    public static volatile SingularAttribute<Fournisseurs, Date> createdBy;
    public static volatile SingularAttribute<Fournisseurs, String> adresse;
    public static volatile SingularAttribute<Fournisseurs, String> telephone;
    public static volatile SingularAttribute<Fournisseurs, Integer> id;
    public static volatile ListAttribute<Fournisseurs, Articles> articlesList;
    public static volatile SingularAttribute<Fournisseurs, String> nom;
    public static volatile SingularAttribute<Fournisseurs, String> prenom;
    public static volatile SingularAttribute<Fournisseurs, Date> updatedAt;

}