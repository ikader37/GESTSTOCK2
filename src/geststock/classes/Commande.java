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
@Table(name = "commande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commande.findAll", query = "SELECT c FROM Commande c"),
    @NamedQuery(name = "Commande.findById", query = "SELECT c FROM Commande c WHERE c.id = :id"),
    @NamedQuery(name = "Commande.findByDateComan", query = "SELECT c FROM Commande c WHERE c.dateComan = :dateComan"),
    @NamedQuery(name = "Commande.findByMontantTHt", query = "SELECT c FROM Commande c WHERE c.montantTHt = :montantTHt"),
    @NamedQuery(name = "Commande.findByMontantTTtc", query = "SELECT c FROM Commande c WHERE c.montantTTtc = :montantTTtc"),
    @NamedQuery(name = "Commande.findByIdclient", query = "SELECT c FROM Commande c WHERE c.idclient = :idclient"),
    @NamedQuery(name = "Commande.findByTva", query = "SELECT c FROM Commande c WHERE c.tva = :tva"),
    @NamedQuery(name = "Commande.findByCreatedAt", query = "SELECT c FROM Commande c WHERE c.createdAt = :createdAt"),
    @NamedQuery(name = "Commande.findByUpdatedAt", query = "SELECT c FROM Commande c WHERE c.updatedAt = :updatedAt"),
    @NamedQuery(name = "Commande.findByCreatedBy", query = "SELECT c FROM Commande c WHERE c.createdBy = :createdBy"),
    @NamedQuery(name = "Commande.findByUpdatedBy", query = "SELECT c FROM Commande c WHERE c.updatedBy = :updatedBy"),
    @NamedQuery(name = "Commande.findByDeleted", query = "SELECT c FROM Commande c WHERE c.deleted = :deleted")})
public class Commande implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date_coman")
    @Temporal(TemporalType.DATE)
    private Date dateComan;
    @Column(name = "montant_t_ht")
    private Integer montantTHt;
    @Column(name = "montant_t_ttc")
    private Integer montantTTtc;
    @Column(name = "idclient")
    private Integer idclient;
    @Column(name = "tva")
    private Boolean tva;
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "updated_by")
    private Integer updatedBy;
    @Basic(optional = false)
    @Column(name = "deleted")
    private boolean deleted;
    @OneToMany(mappedBy = "idcommande")
    private List<Facture> factureList;

    public Commande() {
    }

    public Commande(Integer id) {
        this.id = id;
    }

    public Commande(Integer id, boolean deleted) {
        this.id = id;
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateComan() {
        return dateComan;
    }

    public void setDateComan(Date dateComan) {
        this.dateComan = dateComan;
    }

    public Integer getMontantTHt() {
        return montantTHt;
    }

    public void setMontantTHt(Integer montantTHt) {
        this.montantTHt = montantTHt;
    }

    public Integer getMontantTTtc() {
        return montantTTtc;
    }

    public void setMontantTTtc(Integer montantTTtc) {
        this.montantTTtc = montantTTtc;
    }

    public Integer getIdclient() {
        return idclient;
    }

    public void setIdclient(Integer idclient) {
        this.idclient = idclient;
    }

    public Boolean getTva() {
        return tva;
    }

    public void setTva(Boolean tva) {
        this.tva = tva;
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

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @XmlTransient
    public List<Facture> getFactureList() {
        return factureList;
    }

    public void setFactureList(List<Facture> factureList) {
        this.factureList = factureList;
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
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "geststock.classes.Commande[ id=" + id + " ]";
    }
    
}
