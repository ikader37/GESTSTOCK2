package geststock.classes;

import geststock.classes.Commande;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-18T16:59:10")
@StaticMetamodel(Facture.class)
public class Facture_ { 

    public static volatile SingularAttribute<Facture, Integer> updatedBy;
    public static volatile SingularAttribute<Facture, Integer> montantHt;
    public static volatile SingularAttribute<Facture, Commande> idcommande;
    public static volatile SingularAttribute<Facture, String> modePayement;
    public static volatile SingularAttribute<Facture, Integer> montantTtc;
    public static volatile SingularAttribute<Facture, Date> dateFact;
    public static volatile SingularAttribute<Facture, Date> createAt;
    public static volatile SingularAttribute<Facture, Boolean> deleted;
    public static volatile SingularAttribute<Facture, Integer> versement;
    public static volatile SingularAttribute<Facture, Integer> createdBy;
    public static volatile SingularAttribute<Facture, Integer> restePayer;
    public static volatile SingularAttribute<Facture, Integer> id;
    public static volatile SingularAttribute<Facture, Date> updatedAt;

}