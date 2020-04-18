/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geststock.classes;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
 * @author ilboudo
 */
@Entity
@Table(name = "articlecommande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Articlecommande.findAll", query = "SELECT a FROM Articlecommande a"),
    @NamedQuery(name = "Articlecommande.findByIdarticle", query = "SELECT a FROM Articlecommande a WHERE a.articlecommandePK.idarticle = :idarticle"),
    @NamedQuery(name = "Articlecommande.findByIdcommande", query = "SELECT a FROM Articlecommande a WHERE a.articlecommandePK.idcommande = :idcommande"),
    @NamedQuery(name = "Articlecommande.findByQuantite", query = "SELECT a FROM Articlecommande a WHERE a.quantite = :quantite"),
    @NamedQuery(name = "Articlecommande.findByTva", query = "SELECT a FROM Articlecommande a WHERE a.tva = :tva"),
    @NamedQuery(name = "Articlecommande.findByPrixht", query = "SELECT a FROM Articlecommande a WHERE a.prixht = :prixht"),
    @NamedQuery(name = "Articlecommande.findByPrixttc", query = "SELECT a FROM Articlecommande a WHERE a.prixttc = :prixttc"),
    @NamedQuery(name = "Articlecommande.findByCreatedAt", query = "SELECT a FROM Articlecommande a WHERE a.createdAt = :createdAt"),
    @NamedQuery(name = "Articlecommande.findByCreatedBy", query = "SELECT a FROM Articlecommande a WHERE a.createdBy = :createdBy"),
    @NamedQuery(name = "Articlecommande.findByUpdatedAt", query = "SELECT a FROM Articlecommande a WHERE a.updatedAt = :updatedAt"),
    @NamedQuery(name = "Articlecommande.findByUpdatedBy", query = "SELECT a FROM Articlecommande a WHERE a.updatedBy = :updatedBy")})
public class Articlecommande implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ArticlecommandePK articlecommandePK;
    @Column(name = "quantite")
    private Integer quantite;
    @Column(name = "tva")
    private Boolean tva;
    @Column(name = "prixht")
    private Integer prixht;
    @Column(name = "prixttc")
    private Integer prixttc;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
    @Column(name = "updated_by")
    private Integer updatedBy;
    @JoinColumn(name = "idcommande", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Commande commande;
    @JoinColumn(name = "idarticle", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Articles articles;

    public Articlecommande() {
    }

    public Articlecommande(ArticlecommandePK articlecommandePK) {
        this.articlecommandePK = articlecommandePK;
    }

    public Articlecommande(int idarticle, int idcommande) {
        this.articlecommandePK = new ArticlecommandePK(idarticle, idcommande);
    }

    public ArticlecommandePK getArticlecommandePK() {
        return articlecommandePK;
    }

    public void setArticlecommandePK(ArticlecommandePK articlecommandePK) {
        this.articlecommandePK = articlecommandePK;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Boolean getTva() {
        return tva;
    }

    public void setTva(Boolean tva) {
        this.tva = tva;
    }

    public Integer getPrixht() {
        return prixht;
    }

    public void setPrixht(Integer prixht) {
        this.prixht = prixht;
    }

    public Integer getPrixttc() {
        return prixttc;
    }

    public void setPrixttc(Integer prixttc) {
        this.prixttc = prixttc;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
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

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Articles getArticles() {
        return articles;
    }

    public void setArticles(Articles articles) {
        this.articles = articles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articlecommandePK != null ? articlecommandePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Articlecommande)) {
            return false;
        }
        Articlecommande other = (Articlecommande) object;
        if ((this.articlecommandePK == null && other.articlecommandePK != null) || (this.articlecommandePK != null && !this.articlecommandePK.equals(other.articlecommandePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.vvv.Articlecommande[ articlecommandePK=" + articlecommandePK + " ]";
    }
    
}
