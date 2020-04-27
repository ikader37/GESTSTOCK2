package geststock.classes;

import geststock.classes.Utilisateurs;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-18T16:59:10")
@StaticMetamodel(Employes.class)
public class Employes_ { 

    public static volatile SingularAttribute<Employes, Utilisateurs> utilisateurs;
    public static volatile SingularAttribute<Employes, String> adresse;
    public static volatile SingularAttribute<Employes, Integer> utilisateurId;
    public static volatile SingularAttribute<Employes, String> telephone;
    public static volatile SingularAttribute<Employes, Integer> id;
    public static volatile SingularAttribute<Employes, String> nom;
    public static volatile SingularAttribute<Employes, String> prenom;

}