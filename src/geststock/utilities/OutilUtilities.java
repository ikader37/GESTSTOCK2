/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geststock.utilities;

import geststock.classes.Utilisateurs;
import geststock.crud_methods.ArticlesJpaController;
import geststock.crud_methods.CategoriesJpaController;
import geststock.crud_methods.FournisseursJpaController;
import geststock.crud_methods.RangementJpaController;
import geststock.crud_methods.UtilisateursJpaController;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;

/**
 *
 * @author DELL
 */
public class OutilUtilities {
    
    public static JMenuBar menu=new JMenuBar();
    public static Utilisateurs userActuel=new Utilisateurs(0);
    public static JFrame fenetreCourante=new JFrame();
    
    static EntityManagerFactory emf=Persistence.createEntityManagerFactory("GestStockPU");
    public static UtilisateursJpaController userJpa=new UtilisateursJpaController(emf);
    public static CategoriesJpaController categorieJpa=new CategoriesJpaController(emf);
    public static RangementJpaController rangJpa=new RangementJpaController(emf);
    public static FournisseursJpaController fourniJpa=new FournisseursJpaController(emf);
    
    
    public static ArticlesJpaController articleJpa=new ArticlesJpaController(emf);
    
    
    /**
     * 
     * Les variables suivantes seront des messages a affiches 
     */
    public static String createdSucess="Enregistrement réussi!!. Merci!";
    
    public static String createError="Enregistrement échoué!!\n Veuillez réessayer svp. Merci!";
    
    public static String updateSuccess="Mise à jour réussi!!. Merci!";
    
    public static String updateError="Mise à jour échouée!!\n Veuillez réessayer svp. Merci!";
    
    public static String valNegative="Veuiller saissir des valeurs positives svp";
    public static String annulerSupprimer="Opération de suppression annulée!!!";
    public static String suppsuccess="Suppression réussi";
    public static String supperror="Suppression échouée.\n Veuillez réessayer svp!!";
    
    public static String reapproSucess="Réapprovisionnement réussi!!";
    public static String reapproError="Réapprovisionnement échoué .Veuillez réessayer svp.Merci!!";
    
    
    
    public static void afficherMessage(String message){
        JOptionPane.showMessageDialog(null, message,"Succes",JOptionPane.INFORMATION_MESSAGE);
    }
    public static void afficherMessageErreur(String message){
        JOptionPane.showMessageDialog(null,  message,"Erreur", JOptionPane.ERROR_MESSAGE);
    }
    
    
    public static String md5Java(String message){
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
           
            //converting byte array to Hexadecimal String
           StringBuilder sb = new StringBuilder(2*hash.length);
           for(byte b : hash){
               sb.append(String.format("%02x", b&0xff));
           }
          
           digest = sb.toString();
          
        } catch (UnsupportedEncodingException ex) {
            //Logger.getLogger(StringReplace.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchAlgorithmException ex) {
           // Logger.getLogger(StringReplace.class.getName()).log(Level.SEVERE, null, ex);
        }
        return digest;
    }
    
    
    /***
     * Cette fonction permettra d'extraire le id des combobox
     * @param str
     * @return 
     */
    public static String trouverIdCombobox(String str){
        
        String [] ids=str.split("-");
        return ids[0];
        
    }
    
}
