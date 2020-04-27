package geststock.classes;

import geststock.classes.Articles;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-04-18T16:59:10")
@StaticMetamodel(Categories.class)
public class Categories_ { 

    public static volatile SingularAttribute<Categories, Date> createdAt;
    public static volatile SingularAttribute<Categories, Boolean> deleted;
    public static volatile SingularAttribute<Categories, Integer> createdBy;
    public static volatile SingularAttribute<Categories, String> libelle;
    public static volatile SingularAttribute<Categories, Integer> cupdatedBy;
    public static volatile SingularAttribute<Categories, Integer> id;
    public static volatile ListAttribute<Categories, Articles> articlesList;
    public static volatile SingularAttribute<Categories, Date> updatedAt;

}