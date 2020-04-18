/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geststock.classes;

import geststock.utilities.OutilUtilities;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "articles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articles.findAll", query = "SELECT a FROM Articles a"),
    @NamedQuery(name = "Articles.findById", query = "SELECT a FROM Articles a WHERE a.id = :id"),
    @NamedQuery(name = "Articles.findByDesignation", query = "SELECT a FROM Articles a WHERE a.designation = :designation"),
    @NamedQuery(name = "Articles.findByQuantite", query = "SELECT a FROM Articles a WHERE a.quantite = :quantite"),
    @NamedQuery(name = "Articles.findByUpdatedAt", query = "SELECT a FROM Articles a WHERE a.updatedAt = :updatedAt"),
    @NamedQuery(name = "Articles.findByCreatedAt", query = "SELECT a FROM Articles a WHERE a.createdAt = :createdAt"),
    @NamedQuery(name = "Articles.findByCreatedBy", query = "SELECT a FROM Articles a WHERE a.createdBy = :createdBy"),
    @NamedQuery(name = "Articles.findByUpdatedBy", query = "SELECT a FROM Articles a WHERE a.updatedBy = :updatedBy"),
    @NamedQuery(name = "Articles.findByPrixUnitaire", query = "SELECT a FROM Articles a WHERE a.prixUnitaire = :prixUnitaire"),
    @NamedQuery(name = "Articles.findByDateSortie", query = "SELECT a FROM Articles a WHERE a.dateSortie = :dateSortie"),
    @NamedQuery(name = "Articles.findByPrixVente", query = "SELECT a FROM Articles a WHERE a.prixVente = :prixVente"),
    @NamedQuery(name = "Articles.findByTva", query = "SELECT a FROM Articles a WHERE a.tva = :tva"),
    @NamedQuery(name = "Articles.findByQtiteMin", query = "SELECT a FROM Articles a WHERE a.qtiteMin = :qtiteMin"),
    @NamedQuery(name = "Articles.findByReference", query = "SELECT a FROM Articles a WHERE a.reference = :reference"),
    @NamedQuery(name = "Articles.findByDeleted", query = "SELECT a FROM Articles a WHERE a.deleted = :deleted")})
public class Articles implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "DESIGNATION")
    private String designation;
    @Column(name = "QUANTITE")
    private Integer quantite;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "CREATED_BY")
    private int createdBy;
    @Column(name = "UPDATED_BY")
    private int updatedBy;
    @Column(name = "PRIX_UNITAIRE")
    private Integer prixUnitaire;
    @Column(name = "DATE_SORTIE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSortie;
    @Column(name = "prix_vente")
    private Integer prixVente;
    @Column(name = "tva")
    private Boolean tva;
    @Column(name = "qtite_min")
    private Integer qtiteMin;
    @Column(name = "reference")
    private String reference;
    @Basic(optional = false)
    @Column(name = "deleted")
    private boolean deleted;
    @OneToMany(mappedBy = "articleId")
    private List<Vendres> vendresList;
    @JoinColumn(name = "CATEGORIE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Categories categorieId;
    @JoinColumn(name = "FOURNISSEUR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Fournisseurs fournisseurId;
    @JoinColumn(name = "idrangemennt", referencedColumnName = "id")
    @ManyToOne
    private Rangement idrangemennt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "articles")
    private List<Articlecommande> articlecommandeList;

    public Articles() {
    }

    public Articles(Integer id) {
        this.id = id;
    }

    public Articles(Integer id, boolean deleted) {
        this.id = id;
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Integer prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public Integer getPrixVente() {
        return prixVente;
    }

    public void setPrixVente(Integer prixVente) {
        this.prixVente = prixVente;
    }

    public Boolean getTva() {
        return tva;
    }

    public void setTva(Boolean tva) {
        this.tva = tva;
    }

    public Integer getQtiteMin() {
        return qtiteMin;
    }

    public void setQtiteMin(Integer qtiteMin) {
        this.qtiteMin = qtiteMin;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @XmlTransient
    public List<Vendres> getVendresList() {
        return vendresList;
    }

    public void setVendresList(List<Vendres> vendresList) {
        this.vendresList = vendresList;
    }

    public Categories getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(Categories categorieId) {
        this.categorieId = categorieId;
    }

    public Fournisseurs getFournisseurId() {
        return fournisseurId;
    }

    public void setFournisseurId(Fournisseurs fournisseurId) {
        this.fournisseurId = fournisseurId;
    }

    public Rangement getIdrangemennt() {
        return idrangemennt;
    }

    public void setIdrangemennt(Rangement idrangemennt) {
        this.idrangemennt = idrangemennt;
    }
    
    @XmlTransient
    public List<Articlecommande> getArticlecommandeList() {
        return articlecommandeList;
    }

    public void setArticlecommandeList(List<Articlecommande> articlecommandeList) {
        this.articlecommandeList = articlecommandeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articles)) {
            return false;
        }
        Articles other = (Articles) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "geststock.classes.Articles[ id=" + id + " ]";
    }
    
    
    public boolean createArticle(){
        
        boolean b=false;
        
        try{
            OutilUtilities.articleJpa.create(this);
            b=true;
        }catch(Exception ex){
            b=false;
        }
        return b;
    }
    
    public List<Articles> listArticlesValide(){
        
        return OutilUtilities.articleJpa.listArticleValides();
    }
    
    public boolean updateArticle(){
        boolean b=false;
        
        try{
            
            OutilUtilities.articleJpa.edit(this);
            b=true;
        }catch(Exception ex){
            b=false;
        }
        
        return b;
        
    }
    
    
    public Articles obtenirArticle(){
        return OutilUtilities.articleJpa.findArticles(id);
    }
            
}
