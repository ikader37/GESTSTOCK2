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
@Table(name = "rangement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rangement.findAll", query = "SELECT r FROM Rangement r"),
    @NamedQuery(name = "Rangement.findById", query = "SELECT r FROM Rangement r WHERE r.id = :id"),
    @NamedQuery(name = "Rangement.findByLibelle", query = "SELECT r FROM Rangement r WHERE r.libelle = :libelle"),
    @NamedQuery(name = "Rangement.findByDesignation", query = "SELECT r FROM Rangement r WHERE r.designation = :designation"),
    @NamedQuery(name = "Rangement.findByCreatedAt", query = "SELECT r FROM Rangement r WHERE r.createdAt = :createdAt"),
    @NamedQuery(name = "Rangement.findByUpdatedAt", query = "SELECT r FROM Rangement r WHERE r.updatedAt = :updatedAt"),
    @NamedQuery(name = "Rangement.findByUpdatedBy", query = "SELECT r FROM Rangement r WHERE r.updatedBy = :updatedBy"),
    @NamedQuery(name = "Rangement.findByCreatedBy", query = "SELECT r FROM Rangement r WHERE r.createdBy = :createdBy"),
    @NamedQuery(name = "Rangement.findByDeleted", query = "SELECT r FROM Rangement r WHERE r.deleted = :deleted")})
public class Rangement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "libelle")
    private String libelle;
    @Column(name = "designation")
    private String designation;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
    @Column(name = "updated_by")
    private Integer updatedBy;
    @Column(name = "created_by")
    private Integer createdBy;
    @Basic(optional = false)
    @Column(name = "deleted")
    private boolean deleted;
    @OneToMany(mappedBy = "idrangemennt")
    private List<Articles> articlesList;

    public Rangement() {
    }

    public Rangement(Integer id) {
        this.id = id;
    }

    public Rangement(Integer id, boolean deleted) {
        this.id = id;
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
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
        if (!(object instanceof Rangement)) {
            return false;
        }
        Rangement other = (Rangement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "geststock.classes.Rangement[ id=" + id + " ]";
    }
    
}
