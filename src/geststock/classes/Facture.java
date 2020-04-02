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
@Table(name = "facture")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Facture.findAll", query = "SELECT f FROM Facture f"),
    @NamedQuery(name = "Facture.findById", query = "SELECT f FROM Facture f WHERE f.id = :id"),
    @NamedQuery(name = "Facture.findByDateFact", query = "SELECT f FROM Facture f WHERE f.dateFact = :dateFact"),
    @NamedQuery(name = "Facture.findByVersement", query = "SELECT f FROM Facture f WHERE f.versement = :versement"),
    @NamedQuery(name = "Facture.findByRestePayer", query = "SELECT f FROM Facture f WHERE f.restePayer = :restePayer"),
    @NamedQuery(name = "Facture.findByMontantHt", query = "SELECT f FROM Facture f WHERE f.montantHt = :montantHt"),
    @NamedQuery(name = "Facture.findByMontantTtc", query = "SELECT f FROM Facture f WHERE f.montantTtc = :montantTtc"),
    @NamedQuery(name = "Facture.findByModePayement", query = "SELECT f FROM Facture f WHERE f.modePayement = :modePayement"),
    @NamedQuery(name = "Facture.findByDeleted", query = "SELECT f FROM Facture f WHERE f.deleted = :deleted"),
    @NamedQuery(name = "Facture.findByCreateAt", query = "SELECT f FROM Facture f WHERE f.createAt = :createAt"),
    @NamedQuery(name = "Facture.findByUpdatedAt", query = "SELECT f FROM Facture f WHERE f.updatedAt = :updatedAt"),
    @NamedQuery(name = "Facture.findByUpdatedBy", query = "SELECT f FROM Facture f WHERE f.updatedBy = :updatedBy"),
    @NamedQuery(name = "Facture.findByCreatedBy", query = "SELECT f FROM Facture f WHERE f.createdBy = :createdBy")})
public class Facture implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date_fact")
    @Temporal(TemporalType.DATE)
    private Date dateFact;
    @Column(name = "versement")
    private Integer versement;
    @Column(name = "reste_payer")
    private Integer restePayer;
    @Column(name = "montant_ht")
    private Integer montantHt;
    @Column(name = "montant_ttc")
    private Integer montantTtc;
    @Column(name = "mode_payement")
    private String modePayement;
    @Column(name = "deleted")
    private Boolean deleted;
    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.DATE)
    private Date updatedAt;
    @Column(name = "updated_by")
    private Integer updatedBy;
    @Column(name = "created_by")
    private Integer createdBy;
    @JoinColumn(name = "idcommande", referencedColumnName = "id")
    @ManyToOne
    private Commande idcommande;

    public Facture() {
    }

    public Facture(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateFact() {
        return dateFact;
    }

    public void setDateFact(Date dateFact) {
        this.dateFact = dateFact;
    }

    public Integer getVersement() {
        return versement;
    }

    public void setVersement(Integer versement) {
        this.versement = versement;
    }

    public Integer getRestePayer() {
        return restePayer;
    }

    public void setRestePayer(Integer restePayer) {
        this.restePayer = restePayer;
    }

    public Integer getMontantHt() {
        return montantHt;
    }

    public void setMontantHt(Integer montantHt) {
        this.montantHt = montantHt;
    }

    public Integer getMontantTtc() {
        return montantTtc;
    }

    public void setMontantTtc(Integer montantTtc) {
        this.montantTtc = montantTtc;
    }

    public String getModePayement() {
        return modePayement;
    }

    public void setModePayement(String modePayement) {
        this.modePayement = modePayement;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
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

    public Commande getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(Commande idcommande) {
        this.idcommande = idcommande;
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
        if (!(object instanceof Facture)) {
            return false;
        }
        Facture other = (Facture) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "geststock.classes.Facture[ id=" + id + " ]";
    }
    
}
