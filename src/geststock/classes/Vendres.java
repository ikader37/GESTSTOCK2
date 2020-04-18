/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geststock.classes;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "vendres")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendres.findAll", query = "SELECT v FROM Vendres v"),
    @NamedQuery(name = "Vendres.findById", query = "SELECT v FROM Vendres v WHERE v.id = :id"),
    @NamedQuery(name = "Vendres.findByCreatedAt", query = "SELECT v FROM Vendres v WHERE v.createdAt = :createdAt"),
    @NamedQuery(name = "Vendres.findByUpdatedAt", query = "SELECT v FROM Vendres v WHERE v.updatedAt = :updatedAt"),
    @NamedQuery(name = "Vendres.findByQuantite", query = "SELECT v FROM Vendres v WHERE v.quantite = :quantite"),
    @NamedQuery(name = "Vendres.findByDateSortie", query = "SELECT v FROM Vendres v WHERE v.dateSortie = :dateSortie")})
public class Vendres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "QUANTITE")
    private Integer quantite;
    @Column(name = "DATE_SORTIE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateSortie;
    @JoinColumn(name = "ARTICLE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Articles articleId;
    @JoinColumn(name = "UTILISATEUR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Utilisateurs utilisateurId;

    public Vendres() {
    }

    public Vendres(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public Articles getArticleId() {
        return articleId;
    }

    public void setArticleId(Articles articleId) {
        this.articleId = articleId;
    }

    public Utilisateurs getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(Utilisateurs utilisateurId) {
        this.utilisateurId = utilisateurId;
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
        if (!(object instanceof Vendres)) {
            return false;
        }
        Vendres other = (Vendres) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "geststock.classes.Vendres[ id=" + id + " ]";
    }
    
}
