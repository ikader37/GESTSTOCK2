/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geststock.classes;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "fournisseurs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fournisseurs.findAll", query = "SELECT f FROM Fournisseurs f"),
    @NamedQuery(name = "Fournisseurs.findById", query = "SELECT f FROM Fournisseurs f WHERE f.id = :id"),
    @NamedQuery(name = "Fournisseurs.findByNom", query = "SELECT f FROM Fournisseurs f WHERE f.nom = :nom"),
    @NamedQuery(name = "Fournisseurs.findByPrenom", query = "SELECT f FROM Fournisseurs f WHERE f.prenom = :prenom"),
    @NamedQuery(name = "Fournisseurs.findByAdresse", query = "SELECT f FROM Fournisseurs f WHERE f.adresse = :adresse"),
    @NamedQuery(name = "Fournisseurs.findByTelephone", query = "SELECT f FROM Fournisseurs f WHERE f.telephone = :telephone"),
    @NamedQuery(name = "Fournisseurs.findByCreatedAt", query = "SELECT f FROM Fournisseurs f WHERE f.createdAt = :createdAt"),
    @NamedQuery(name = "Fournisseurs.findByUpdatedAt", query = "SELECT f FROM Fournisseurs f WHERE f.updatedAt = :updatedAt"),
    @NamedQuery(name = "Fournisseurs.findByCreatedBy", query = "SELECT f FROM Fournisseurs f WHERE f.createdBy = :createdBy"),
    @NamedQuery(name = "Fournisseurs.findByUpdatedBy", query = "SELECT f FROM Fournisseurs f WHERE f.updatedBy = :updatedBy"),
    @NamedQuery(name = "Fournisseurs.findByDeleted", query = "SELECT f FROM Fournisseurs f WHERE f.deleted = :deleted")})
public class Fournisseurs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "NOM")
    private String nom;
    @Column(name = "PRENOM")
    private String prenom;
    @Column(name = "ADRESSE")
    private String adresse;
    @Column(name = "TELEPHONE")
    private String telephone;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
    @Column(name = "created_by")
    @Temporal(TemporalType.DATE)
    private Date createdBy;
    @Column(name = "updated_by")
    @Temporal(TemporalType.DATE)
    private Date updatedBy;
    @Basic(optional = false)
    @Column(name = "deleted")
    private boolean deleted;
    @OneToMany(mappedBy = "fournisseurId")
    private List<Articles> articlesList;

    public Fournisseurs() {
    }

    public Fournisseurs(Integer id) {
        this.id = id;
    }

    public Fournisseurs(Integer id, boolean deleted) {
        this.id = id;
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Date createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Date updatedBy) {
        this.updatedBy = updatedBy;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @XmlTransient
    public List<Articles> getArticlesList() {
        return articlesList;
    }

    public void setArticlesList(List<Articles> articlesList) {
        this.articlesList = articlesList;
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
        if (!(object instanceof Fournisseurs)) {
            return false;
        }
        Fournisseurs other = (Fournisseurs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "geststock.classes.Fournisseurs[ id=" + id + " ]";
    }
    
}
