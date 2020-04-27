package geststock.classes;

import geststock.classes.Utilisateurs;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-18T16:59:10")
@StaticMetamodel(Roles.class)
public class Roles_ { 

    public static volatile SingularAttribute<Roles, String> libelle;
    public static volatile SingularAttribute<Roles, Integer> id;
    public static volatile ListAttribute<Roles, Utilisateurs> utilisateursList;

}