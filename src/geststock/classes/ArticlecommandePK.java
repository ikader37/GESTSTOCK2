/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geststock.classes;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author ilboudo
 */
@Embeddable
public class ArticlecommandePK implements Serializable {

    @Basic(optional = false)
    @Column(name = "idarticle")
    private int idarticle;
    @Basic(optional = false)
    @Column(name = "idcommande")
    private int idcommande;

    public ArticlecommandePK() {
    }

    public ArticlecommandePK(int idarticle, int idcommande) {
        this.idarticle = idarticle;
        this.idcommande = idcommande;
    }

    public int getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(int idarticle) {
        this.idarticle = idarticle;
    }

    public int getIdcommande() {
        return idcommande;
    }

    public void setIdcommande(int idcommande) {
        this.idcommande = idcommande;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idarticle;
        hash += (int) idcommande;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ArticlecommandePK)) {
            return false;
        }
        ArticlecommandePK other = (ArticlecommandePK) object;
        if (this.idarticle != other.idarticle) {
            return false;
        }
        if (this.idcommande != other.idcommande) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "javaapplication1.vvv.ArticlecommandePK[ idarticle=" + idarticle + ", idcommande=" + idcommande + " ]";
    }
    
}
