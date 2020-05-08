package geststock.classes;

import geststock.classes.Articles;
import geststock.classes.Utilisateurs;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-18T16:59:10")
@StaticMetamodel(Vendres.class)
public class Vendres_ { 

    public static volatile SingularAttribute<Vendres, Date> createdAt;
    public static volatile SingularAttribute<Vendres, Date> dateSortie;
    public static volatile SingularAttribute<Vendres, Articles> articleId;
    public static volatile SingularAttribute<Vendres, Utilisateurs> utilisateurId;
    public static volatile SingularAttribute<Vendres, Integer> id;
    public static volatile SingularAttribute<Vendres, Date> updatedAt;
    public static volatile SingularAttribute<Vendres, Integer> quantite;

}