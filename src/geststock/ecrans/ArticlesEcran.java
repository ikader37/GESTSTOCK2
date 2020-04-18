/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geststock.ecrans;

import geststock.classes.Articles;
import geststock.classes.Fournisseurs;
import geststock.classes.Rangement;
import geststock.dialog.ConfirmDialogSup;
import geststock.ecrans.dialog.Reappro;
import geststock.utilities.OutilUtilities;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class ArticlesEcran extends javax.swing.JFrame {

    public void desactiverButton(JButton btn, boolean actif) {
        btn.setEnabled(actif);
    }

    public void desactiverField(JTextField txt, boolean actif) {
        txt.setEnabled(actif);
    }

    private Articles article;
    private String operation;
    private DefaultComboBoxModel modelFournisseur = new DefaultComboBoxModel();
    private DefaultComboBoxModel modelRangement = new DefaultComboBoxModel();
    private DefaultTableModel modelTable = new DefaultTableModel();
    private Rangement rangeSelected = new Rangement();
    private Fournisseurs fournisseurSelected = new Fournisseurs();

    public void remplirTableau(List<Articles> ars) {
        int nombrepresent = modelTable.getRowCount();
        //Verifier quil ny a rien dedans
        int i = nombrepresent;
        if (nombrepresent > 0) {
            i--;
            while (i >= 0) {
                //Retirer les elemets du tableau
                modelTable.removeRow(i);
                i--;
            }
        }

        /**
         * Verifier que la liste passee en parametre n'est pas nulle
         */
        if (ars != null) {
            i = 1;
            for (Articles ar : ars) {
                modelTable.addRow(new String[]{i + "", ar.getId() + "", ar.getReference(),ar.getDesignation()+"",ar.getQuantite()+"",ar.getQtiteMin()+"",ar.getPrixUnitaire()+" FCFA",ar.getPrixVente()+" FCFA",ar.getIdrangemennt().getLibelle(),ar.getFournisseurId().getNom()+" "+ar.getReference(),ar.getDesignation(),ar.getPrixUnitaire()+"",ar.getPrixVente()+"",ar.getIdrangemennt().getLibelle(),ar.getFournisseurId().getPrenom()});
            }
        }
    }

    /**
     * *
     * Cette fonction alimente le combobox des fournisseurs
     *
     * @param fs
     */
    public void remplirComboxFournisseurs(List<Fournisseurs> fs) {
        modelFournisseur.removeAllElements();
        for (Fournisseurs f : fs) {
            modelFournisseur.addElement(f.getId() + "-" + f.getNom() + " " + f.getPrenom());
        }

    }

    /**
     * *
     * Cette fonction alimente le combox des rangees
     *
     * @param rs
     */
    public void remplirComboxRangement(List<Rangement> rs) {
        modelRangement.removeAllElements();
        for (Rangement r : rs) {
            modelRangement.addElement(r.getId() + "-" + r.getLibelle());
        }
    }

    /**
     * Creates new form Rangement
     */
    public ArticlesEcran() {
        initComponents();

        desactiverButton(this.save, false);
        desactiverField(txt_design, false);
//        txt_ref.setEnabled(false);
//        txt_pv.setEnabled(false);
//        txt_qtite.setEnabled(false);
//        txt_qtite_min.setEnabled(false);
//        txt_pa.setEnabled(false);
            
         griserChamps(false);
        desactiverButton(this.annuler, false);
        this.operation = "new";
        remplirComboxFournisseurs(fournisseurSelected.listFournisseursValide());
        remplirComboxRangement(rangeSelected.listRangementValide());

        modelTable.addColumn("#");
        modelTable.addColumn("Id");
        modelTable.addColumn("Référence");
        modelTable.addColumn("Désignation");
        modelTable.addColumn("En stock");
        modelTable.addColumn("Quantité minimale");
        modelTable.addColumn("Prix d'âchat");
        modelTable.addColumn("Prix de vente");
        modelTable.addColumn("Rangée");
        modelTable.addColumn("Fournisseur");

        article = new Articles();
        remplirTableau(article.listArticlesValide());

    }

    /**
     * *
     * Cette methode permet de reinitialiser tous les champs du formulaire
     */
    public void reinitialiserChamp() {
        txt_design.setText("");
        txt_ref.setText("");
        txt_pa.setValue(0);
        txt_pv.setValue(0);
        txt_qtite.setValue(0);
        txt_qtite_min.setValue(0);
        

    }

    /**
     *
     * @param decision prend true si on veut grisser les champs et false si on
     * veut tout activer
     */
    public void griserChamps(boolean decision) {
        txt_design.setEnabled(decision);
        txt_ref.setEnabled(decision);
        txt_pv.setEnabled(decision);
        txt_qtite.setEnabled(decision);
        txt_qtite_min.setEnabled(decision);
        txt_pa.setEnabled(decision);
        cb_fournisseurs.setEnabled(decision);
        cb_range.setEnabled(decision);
    }

    
    
    
    
    public void remplirTousChamps(Articles article){
        
        txt_design.setText(article.getDesignation());
        txt_ref.setText(article.getReference());
        txt_pa.setValue(new Integer(article.getPrixUnitaire()));

        txt_pv.setValue(new Integer(article.getPrixVente()));
        txt_qtite_min.setValue(new Integer(article.getQtiteMin()));

        cb_fournisseurs.setSelectedItem(article.getFournisseurId().getId() + "-" + article.getFournisseurId().getNom() + " " + article.getFournisseurId().getPrenom());

        cb_range.setSelectedItem(article.getIdrangemennt().getId() + "-" + article.getIdrangemennt().getLibelle());

    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        time_to_day = new javax.swing.JLabel();
        panelAdd = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_design = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txt_ref = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cb_range = new javax.swing.JComboBox<>();
        cb_fournisseurs = new javax.swing.JComboBox<>();
        txt_qtite = new javax.swing.JSpinner();
        txt_qtite_min = new javax.swing.JSpinner();
        txt_pa = new javax.swing.JSpinner();
        txt_pv = new javax.swing.JSpinner();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        actualiser = new javax.swing.JButton();
        annuler = new javax.swing.JButton();
        save = new javax.swing.JButton();
        modifier = new javax.swing.JButton();
        supprimer = new javax.swing.JButton();
        nouveau = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("GESTSTOCK");

        time_to_day.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(time_to_day, javax.swing.GroupLayout.DEFAULT_SIZE, 693, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(time_to_day, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 81, Short.MAX_VALUE))
        );

        Date today=new Date();
        time_to_day.setText(today+"");

        jLabel2.setText("Désignation:");

        jLabel3.setText("Quantité:");

        jLabel4.setText("Quantité minimale:");

        jLabel5.setText("Prix d'achât:");

        jLabel6.setText("Référence:");

        jLabel7.setText("Prix de vente:");

        jLabel8.setText("TVA:");

        jRadioButton1.setText("OUI");

        jRadioButton2.setText("NON");

        jLabel9.setText("Fournisseur:");

        jLabel10.setText("Rangée:");

        cb_range.setModel(modelRangement);

        cb_fournisseurs.setModel(modelFournisseur);

        txt_qtite_min.setValue(1);

        javax.swing.GroupLayout panelAddLayout = new javax.swing.GroupLayout(panelAdd);
        panelAdd.setLayout(panelAddLayout);
        panelAddLayout.setHorizontalGroup(
            panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAddLayout.createSequentialGroup()
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_design, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                            .addComponent(txt_ref)
                            .addComponent(txt_qtite)
                            .addComponent(txt_qtite_min))
                        .addGap(57, 57, 57)
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(39, 39, 39)
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelAddLayout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addGap(18, 18, 18)
                                .addComponent(jRadioButton2))
                            .addComponent(cb_fournisseurs, 0, 278, Short.MAX_VALUE)
                            .addComponent(txt_pa)
                            .addComponent(txt_pv)))
                    .addComponent(cb_range, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(393, Short.MAX_VALUE))
        );
        panelAddLayout.setVerticalGroup(
            panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddLayout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelAddLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_ref, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_pa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelAddLayout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_design, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(txt_pv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton1)
                        .addComponent(jRadioButton2))
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(txt_qtite))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_fournisseurs, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_qtite_min, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cb_range, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        table.setModel(modelTable);
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        actualiser.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Syncpx.png"))); // NOI18N
        actualiser.setText("Actualiser");
        actualiser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualiserActionPerformed(evt);
            }
        });

        annuler.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Deletepx.png"))); // NOI18N
        annuler.setText("Annuler");
        annuler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                annulerActionPerformed(evt);
            }
        });

        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Savepx.png"))); // NOI18N
        save.setText("Enregistrer");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        modifier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Edit Propertypx.png"))); // NOI18N
        modifier.setText("Modifier");
        modifier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifierActionPerformed(evt);
            }
        });

        supprimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Trashpx.png"))); // NOI18N
        supprimer.setText("Supprimer");
        supprimer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supprimerActionPerformed(evt);
            }
        });

        nouveau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Pluspx.png"))); // NOI18N
        nouveau.setText("Nouveau");
        nouveau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nouveauActionPerformed(evt);
            }
        });

        jButton1.setText("Réapprovisionner");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(nouveau, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modifier, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(save, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(annuler, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(actualiser, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(supprimer, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nouveau, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(modifier, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(save, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(annuler, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(actualiser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(panelAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103)
                .addComponent(panelAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void supprimerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supprimerActionPerformed
        // TODO add your handling code here:
        ConfirmDialogSup conf = new ConfirmDialogSup(this, true);
        conf.show();
//        txt_ref.setEnabled(false);
//        txt_pv.setEnabled(false);
//        txt_qtite.setEnabled(false);
//        txt_qtite_min.setEnabled(false);
//        txt_pa.setEnabled(false);

        griserChamps(false);

        int i = JOptionPane.showConfirmDialog(null, "Voulez vous vraiment supprimer ");
        if (i == JOptionPane.YES_OPTION) {

            if (article.getId() == null) {

                int id = Integer.parseInt((String) table.getValueAt(0, 1));
                article.setId(id);
                article = article.obtenirArticle();

            }
            article.setUpdatedAt(new Date());
            article.setUpdatedBy(OutilUtilities.userActuel.getId());
            article.setDeleted(true);
            boolean b = article.updateArticle();
            if (b) {
                OutilUtilities.afficherMessage(OutilUtilities.suppsuccess);
                article = new Articles();

                griserChamps(false);
                reinitialiserChamp();
                nouveau.setEnabled(true);
                modifier.setEnabled(true);
                supprimer.setEnabled(true);
                actualiser.setEnabled(true);
                annuler.setEnabled(true);
                save.setEnabled(false);
                

            } else {
                OutilUtilities.afficherMessage(OutilUtilities.supperror);
            }

            remplirTableau(article.listArticlesValide());

        } else {
            OutilUtilities.afficherMessage(OutilUtilities.annulerSupprimer);
        }


    }//GEN-LAST:event_supprimerActionPerformed

    private void nouveauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nouveauActionPerformed
        // TODO add your handling code here:
        desactiverButton(this.modifier, false);
//        txt_ref.setEnabled(true);
//        txt_pv.setEnabled(true);
//        txt_qtite.setEnabled(true);
//        txt_qtite_min.setEnabled(true);
//        txt_pa.setEnabled(true);

        reinitialiserChamp();
        griserChamps(true);
        supprimer.setEnabled(false);
        save.setEnabled(true);
        annuler.setEnabled(true);
        nouveau.setEnabled(false);
        //desactiverField(this.txt_design, true);
        article = new Articles();
        operation="new";

    }//GEN-LAST:event_nouveauActionPerformed

    private void actualiserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualiserActionPerformed
        // TODO add your handling code here:
        desactiverButton(this.save, false);
        desactiverButton(this.annuler, false);
        //desactiverField(txt_design, false);
        griserChamps(false);
        reinitialiserChamp();
        remplirTableau(article.listArticlesValide());
        
//
//        txt_pv.setEnabled(false);
//        txt_qtite.setEnabled(false);
//        txt_qtite_min.setEnabled(false);
//        txt_pa.setEnabled(false);
    }//GEN-LAST:event_actualiserActionPerformed

    private void modifierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifierActionPerformed
        // TODO add your handling code here:
        desactiverButton(this.save, true);
        desactiverButton(this.annuler, true);

        this.operation = "mod";

        if (article.getId() == null) {
            int id = Integer.parseInt((String) table.getValueAt(0, 1));
            article.setId(id);
            article = article.obtenirArticle();
        }

        remplirTousChamps(article);
        griserChamps(true);
        
    }//GEN-LAST:event_modifierActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:

        //Recuperons les valeurs des champs renseignees
        article.setDesignation(txt_design.getText().toString().trim());
        article.setReference(txt_ref.getText().toString().trim());
        article.setPrixUnitaire(Integer.parseInt(txt_pa.getValue().toString()));
        article.setQtiteMin(Integer.parseInt(txt_qtite_min.getValue().toString()));
        article.setPrixVente(Integer.parseInt( txt_pv.getValue().toString()));
        article.setQuantite(Integer.parseInt( txt_qtite.getValue().toString()));

        /**
         * *
         * Verifions que les valeurs entieres sont positives
         *
         */
        if (article.getPrixUnitaire() > 0 && article.getPrixVente() > 0 && article.getQtiteMin() >= 1 && article.getQuantite() > 0) {

            if (this.operation == "new") {
                article.setCreatedAt(new Date());
                article.setUpdatedAt(new Date());
                article.setCreatedBy(OutilUtilities.userActuel.getId());
               article.setUpdatedBy(OutilUtilities.userActuel.getId());
                int idR = Integer.parseInt(OutilUtilities.trouverIdCombobox(modelRangement.getSelectedItem().toString()));

                rangeSelected.setId(idR);

                rangeSelected = rangeSelected.obtenirRangement();

                int idF = Integer.parseInt(OutilUtilities.trouverIdCombobox(modelFournisseur.getSelectedItem().toString()));
                fournisseurSelected.setId(idF);
                fournisseurSelected = fournisseurSelected.obtenirFournisseurs();

                article.setFournisseurId(fournisseurSelected);
                article.setIdrangemennt(rangeSelected);

                boolean b = article.createArticle();
                if (b) {
                    OutilUtilities.afficherMessage(OutilUtilities.createdSucess);
                    article = new Articles();
                    //Grissons tpous l;es champs
                    griserChamps(false);
                    nouveau.setEnabled(true);
                    modifier.setEnabled(true);
                    supprimer.setEnabled(true);
                    annuler.setEnabled(true);
                    actualiser.setEnabled(true);
                    save.setEnabled(false);

                } else {
                    OutilUtilities.afficherMessageErreur(OutilUtilities.createError);
                }

            } else {
                article.setUpdatedAt(new Date());
                //article.setUpdatedBy(OutilUtilities.userActuel.getId());
                int idR = Integer.parseInt(OutilUtilities.trouverIdCombobox(modelRangement.getSelectedItem().toString()));

                rangeSelected.setId(idR);

                rangeSelected = rangeSelected.obtenirRangement();

                int idF = Integer.parseInt(OutilUtilities.trouverIdCombobox(modelFournisseur.getSelectedItem().toString()));
                fournisseurSelected.setId(idF);
                fournisseurSelected = fournisseurSelected.obtenirFournisseurs();

                article.setFournisseurId(fournisseurSelected);
                article.setIdrangemennt(rangeSelected);

                boolean b = article.createArticle();
                if (b) {
                    OutilUtilities.afficherMessage(OutilUtilities.updateSuccess);
                    article = new Articles();
                    griserChamps(false);

                    nouveau.setEnabled(true);
                    modifier.setEnabled(true);
                    supprimer.setEnabled(true);
                    annuler.setEnabled(true);
                    actualiser.setEnabled(true);
                    save.setEnabled(false);

                } else {
                    OutilUtilities.afficherMessageErreur(OutilUtilities.updateError);
                }

            }

        } else {
            OutilUtilities.afficherMessageErreur(OutilUtilities.valNegative);

        }
        
        remplirTableau(article.listArticlesValide());


    }//GEN-LAST:event_saveActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:

        int index = table.getSelectedRow();
        int id = Integer.parseInt((String) modelTable.getValueAt(index, 1));
        article.setId(id);
        System.out.println(id);
        article = article.obtenirArticle();
    }//GEN-LAST:event_tableMouseClicked

    private void annulerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_annulerActionPerformed
        // TODO add your handling code here:
        
        griserChamps(false);
        reinitialiserChamp();
        nouveau.setEnabled(true);
        supprimer.setEnabled(true);
        actualiser.setEnabled(true);
        annuler.setEnabled(false);
        modifier.setEnabled(true);
        save.setEnabled(false);
    }//GEN-LAST:event_annulerActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(article.getId()==null){
         int id=Integer.parseInt(table.getValueAt(0, 1).toString());
         article.setId(id);
         article=article.obtenirArticle();
        }
        Reappro reappro=new  Reappro(this, true, article);
        reappro.show();
        article=new Articles();
        remplirTableau(article.listArticlesValide());
        
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ArticlesEcran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ArticlesEcran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ArticlesEcran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ArticlesEcran.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ArticlesEcran().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton actualiser;
    private javax.swing.JButton annuler;
    private javax.swing.JComboBox<String> cb_fournisseurs;
    private javax.swing.JComboBox<String> cb_range;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton modifier;
    private javax.swing.JButton nouveau;
    private javax.swing.JPanel panelAdd;
    private javax.swing.JButton save;
    private javax.swing.JButton supprimer;
    private javax.swing.JTable table;
    private javax.swing.JLabel time_to_day;
    private javax.swing.JTextField txt_design;
    private javax.swing.JSpinner txt_pa;
    private javax.swing.JSpinner txt_pv;
    private javax.swing.JSpinner txt_qtite;
    private javax.swing.JSpinner txt_qtite_min;
    private javax.swing.JTextField txt_ref;
    // End of variables declaration//GEN-END:variables
}
