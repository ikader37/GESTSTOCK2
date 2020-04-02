/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geststock.menus;

import geststock.ecrans.users.UsersEcran;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author DELL
 */
public class MenuAdmin extends JMenuBar{
    
    private JMenu parametre;
    private JMenuItem entrepriseMenu;
    private JMenuItem taxeMenu;
    private JMenuItem rangementMenu;
    private JMenuItem fournisseurMenu;
    private JMenuItem categorieMenu;
    private JMenuItem addProdMenu;
    
    /**
     * Menus du caissier
     */
    private JMenu ventesMenu;
    private JMenuItem vendreItem;
    private JMenuItem lesVentes;
    private JMenuItem caisseItem;
    
    /**
     * Utilisateur
     */
    private JMenu utilisqteurMenu;
    private JMenuItem addUtilisateur;
    private JFrame fenetre;

    public JFrame getFenetre() {
        return fenetre;
    }

    public void setFenetre(JFrame fenetre) {
        this.fenetre = fenetre;
    }
    
    
    
    
    public MenuAdmin(){
        
        parametre=new JMenu("Paramètres");
        entrepriseMenu=new JMenuItem("Entreprise");
        taxeMenu=new JMenuItem("TVA");
        rangementMenu=new JMenuItem("Les rangées");
        fournisseurMenu=new JMenuItem("Fournisseurs");
        categorieMenu=new JMenuItem("Catégories");
        addProdMenu=new JMenuItem("Ajouter Produit");
        
        parametre.add(entrepriseMenu);
        parametre.add(taxeMenu);
        parametre.add(rangementMenu);
        parametre.add(fournisseurMenu);
        parametre.add(categorieMenu);
        parametre.add(addProdMenu);
        
        /**
         * Menu du caissier
         */
        this.ventesMenu=new JMenu("Ventes");
        this.vendreItem=new JMenuItem("Vendre des produits");
        this.caisseItem=new JMenuItem("Etat de la caisse");
        this.lesVentes=new JMenuItem("Historique des ventes");
        this.ventesMenu.add(vendreItem);
        this.ventesMenu.add(lesVentes);
        this.ventesMenu.add(caisseItem);
        
        /**
         * Utilisateur
         */
        utilisqteurMenu=new JMenu("Utilisateurs");
        addUtilisateur=new JMenuItem("Ajouter utilisateur");
        utilisqteurMenu.add(addUtilisateur);
        
        addUtilisateur.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fenetre.setVisible(false);
                fenetre.dispose();
                UsersEcran ecU=new UsersEcran();
                ecU.setVisible(true);
                //ecU.setMenuBars();
                
                ecU.show();
                
            }
        });
        
        this.add(parametre);
        this.add(ventesMenu);
        this.add(utilisqteurMenu);
    }
    
    
    
}
