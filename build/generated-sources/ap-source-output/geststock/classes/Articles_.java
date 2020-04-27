package geststock.classes;

import geststock.classes.Articlecommande;
import geststock.classes.Categories;
import geststock.classes.Fournisseurs;
import geststock.classes.Rangement;
import geststock.classes.Vendres;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-18T16:59:10")
@StaticMetamodel(Articles.class)
public class Articles_ { 

    public static volatile ListAttribute<Articles, Vendres> vendresList;
    public static volatile SingularAttribute<Articles, Integer> prixUnitaire;
    public static volatile SingularAttribute<Articles, Integer> updatedBy;
    public static volatile ListAttribute<Articles, Articlecommande> articlecommandeList;
    public static volatile SingularAttribute<Articles, Date> dateSortie;
    public static volatile SingularAttribute<Articles, Integer> prixVente;
    public static volatile SingularAttribute<Articles, Integer> qtiteMin;
    public static volatile SingularAttribute<Articles, String> reference;
    public static volatile SingularAttribute<Articles, Date> createdAt;
    public static volatile SingularAttribute<Articles, Boolean> deleted;
    public static volatile SingularAttribute<Articles, Categories> categorieId;
    public static volatile SingularAttribute<Articles, Fournisseurs> fournisseurId;
    public static volatile SingularAttribute<Articles, Integer> createdBy;
    public static volatile SingularAttribute<Articles, Integer> id;
    public static volatile SingularAttribute<Articles, String> designation;
    public static volatile SingularAttribute<Articles, Rangement> idrangemennt;
    public static volatile SingularAttribute<Articles, Boolean> tva;
    public static volatile SingularAttribute<Articles, Integer> quantite;
    public static volatile SingularAttribute<Articles, Date> updatedAt;

}