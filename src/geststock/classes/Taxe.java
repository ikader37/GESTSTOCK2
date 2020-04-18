/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geststock.classes;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author DELL
 */
@Entity
@Table(name = "taxe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Taxe.findAll", query = "SELECT t FROM Taxe t"),
    @NamedQuery(name = "Taxe.findById", query = "SELECT t FROM Taxe t WHERE t.id = :id"),
    @NamedQuery(name = "Taxe.findByLibelle", query = "SELECT t FROM Taxe t WHERE t.libelle = :libelle"),
    @NamedQuery(name = "Taxe.findByValeur", query = "SELECT t FROM Taxe t WHERE t.valeur = :valeur")})
public class Taxe implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "libelle")
    private String libelle;
    @Column(name = "valeur")
    private Integer valeur;

    public Taxe() {
    }

    public Taxe(Integer id) {
        this.id = id;
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

    public Integer getValeur() {
        return valeur;
    }

    public void setValeur(Integer valeur) {
        this.valeur = valeur;
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
        if (!(object instanceof Taxe)) {
            return false;
        }
        Taxe other = (Taxe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "geststock.classes.Taxe[ id=" + id + " ]";
    }
    
}
