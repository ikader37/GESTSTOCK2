package geststock.classes;

import geststock.classes.Employes;
import geststock.classes.Roles;
import geststock.classes.Vendres;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-18T16:59:10")
@StaticMetamodel(Utilisateurs.class)
public class Utilisateurs_ { 

    public static volatile ListAttribute<Utilisateurs, Vendres> vendresList;
    public static volatile SingularAttribute<Utilisateurs, String> updatedBy;
    public static volatile SingularAttribute<Utilisateurs, String> role;
    public static volatile SingularAttribute<Utilisateurs, Roles> roleId;
    public static volatile SingularAttribute<Utilisateurs, String> telephone;
    public static volatile SingularAttribute<Utilisateurs, String> nom;
    public static volatile SingularAttribute<Utilisateurs, Date> createdAt;
    public static volatile SingularAttribute<Utilisateurs, Boolean> deleted;
    public static volatile SingularAttribute<Utilisateurs, String> createdBy;
    public static volatile SingularAttribute<Utilisateurs, String> adresse;
    public static volatile SingularAttribute<Utilisateurs, Integer> id;
    public static volatile SingularAttribute<Utilisateurs, String> motdepasse;
    public static volatile SingularAttribute<Utilisateurs, String> prenom;
    public static volatile SingularAttribute<Utilisateurs, Employes> employes;
    public static volatile SingularAttribute<Utilisateurs, String> username;
    public static volatile SingularAttribute<Utilisateurs, Date> updatedAt;

}