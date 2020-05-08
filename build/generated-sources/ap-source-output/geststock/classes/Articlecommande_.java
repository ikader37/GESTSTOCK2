package geststock.classes;

import geststock.classes.ArticlecommandePK;
import geststock.classes.Articles;
import geststock.classes.Commande;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-18T16:59:10")
@StaticMetamodel(Articlecommande.class)
public class Articlecommande_ { 

    public static volatile SingularAttribute<Articlecommande, Integer> prixttc;
    public static volatile SingularAttribute<Articlecommande, ArticlecommandePK> articlecommandePK;
    public static volatile SingularAttribute<Articlecommande, Date> createdAt;
    public static volatile SingularAttribute<Articlecommande, Integer> updatedBy;
    public static volatile SingularAttribute<Articlecommande, Integer> createdBy;
    public static volatile SingularAttribute<Articlecommande, Integer> prixht;
    public static volatile SingularAttribute<Articlecommande, Commande> commande;
    public static volatile SingularAttribute<Articlecommande, Articles> articles;
    public static volatile SingularAttribute<Articlecommande, Boolean> tva;
    public static volatile SingularAttribute<Articlecommande, Integer> quantite;
    public static volatile SingularAttribute<Articlecommande, Date> updatedAt;

}