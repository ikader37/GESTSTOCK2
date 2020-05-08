package geststock.classes;

import geststock.classes.Articlecommande;
import geststock.classes.Facture;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-18T16:59:10")
@StaticMetamodel(Commande.class)
public class Commande_ { 

    public static volatile SingularAttribute<Commande, Integer> updatedBy;
    public static volatile SingularAttribute<Commande, Integer> montantHt;
    public static volatile ListAttribute<Commande, Articlecommande> articlecommandeList;
    public static volatile SingularAttribute<Commande, Integer> montantTtc;
    public static volatile SingularAttribute<Commande, Date> createdAt;
    public static volatile SingularAttribute<Commande, Boolean> deleted;
    public static volatile SingularAttribute<Commande, Integer> createdBy;
    public static volatile SingularAttribute<Commande, Integer> id;
    public static volatile SingularAttribute<Commande, Integer> idclient;
    public static volatile SingularAttribute<Commande, Date> dateComan;
    public static volatile ListAttribute<Commande, Facture> factureList;
    public static volatile SingularAttribute<Commande, Boolean> tva;
    public static volatile SingularAttribute<Commande, Date> updatedAt;

}