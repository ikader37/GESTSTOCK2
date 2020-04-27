package geststock.classes;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-18T16:59:10")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, Date> createdAt;
    public static volatile SingularAttribute<Client, Integer> updatedBy;
    public static volatile SingularAttribute<Client, Integer> createdBy;
    public static volatile SingularAttribute<Client, String> tel;
    public static volatile SingularAttribute<Client, Integer> idclient;
    public static volatile SingularAttribute<Client, String> nom;
    public static volatile SingularAttribute<Client, String> prenom;
    public static volatile SingularAttribute<Client, Date> updatedAt;

}