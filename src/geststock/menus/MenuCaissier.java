/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geststock.menus;

import javax.swing.*;

/**
 *
 * @author DELL
 */
public class MenuCaissier extends JMenuBar{
    
    private JMenu ventesMenu;
    private JMenuItem vendreItem;
    private JMenuItem lesVentes;
    private JMenuItem caisseItem;
    
    public MenuCaissier(){
        this.ventesMenu=new JMenu("Ventes");
        this.vendreItem=new JMenuItem("Vendre des produits");
        this.caisseItem=new JMenuItem("Etat de la caisse");
        this.lesVentes=new JMenuItem("Historique des ventes");
        this.ventesMenu.add(vendreItem);
        this.ventesMenu.add(lesVentes);
        this.ventesMenu.add(caisseItem);
    }
}
