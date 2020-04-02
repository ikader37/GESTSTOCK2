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
@Table(name = "usagep")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usagep.findAll", query = "SELECT u FROM Usagep u"),
    @NamedQuery(name = "Usagep.findById", query = "SELECT u FROM Usagep u WHERE u.id = :id"),
    @NamedQuery(name = "Usagep.findByLibelle", query = "SELECT u FROM Usagep u WHERE u.libelle = :libelle"),
    @NamedQuery(name = "Usagep.findByDesignation", query = "SELECT u FROM Usagep u WHERE u.designation = :designation")})
public class Usagep implements Serializable {

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

    public Usagep() {
    }

    public Usagep(Integer id) {
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
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
        if (!(object instanceof Usagep)) {
            return false;
        }
        Usagep other = (Usagep) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "geststock.classes.Usagep[ id=" + id + " ]";
    }
    
}
