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
import javax.persistence.OneToOne;
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
@Table(name = "utilisateurs")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Utilisateurs.findAll", query = "SELECT u FROM Utilisateurs u"),
    @NamedQuery(name = "Utilisateurs.findById", query = "SELECT u FROM Utilisateurs u WHERE u.id = :id"),
    @NamedQuery(name = "Utilisateurs.findByUsername", query = "SELECT u FROM Utilisateurs u WHERE u.username = :username"),
    @NamedQuery(name = "Utilisateurs.findByMotdepasse", query = "SELECT u FROM Utilisateurs u WHERE u.motdepasse = :motdepasse"),
    @NamedQuery(name = "Utilisateurs.findByCreatedAt", query = "SELECT u FROM Utilisateurs u WHERE u.createdAt = :createdAt"),
    @NamedQuery(name = "Utilisateurs.findByUpdatedAt", query = "SELECT u FROM Utilisateurs u WHERE u.updatedAt = :updatedAt"),
    @NamedQuery(name = "Utilisateurs.findByCreatedBy", query = "SELECT u FROM Utilisateurs u WHERE u.createdBy = :createdBy"),
    @NamedQuery(name = "Utilisateurs.findByUpdatedBy", query = "SELECT u FROM Utilisateurs u WHERE u.updatedBy = :updatedBy"),
    @NamedQuery(name = "Utilisateurs.findByNom", query = "SELECT u FROM Utilisateurs u WHERE u.nom = :nom"),
    @NamedQuery(name = "Utilisateurs.findByPrenom", query = "SELECT u FROM Utilisateurs u WHERE u.prenom = :prenom"),
    @NamedQuery(name = "Utilisateurs.findByAdresse", query = "SELECT u FROM Utilisateurs u WHERE u.adresse = :adresse"),
    @NamedQuery(name = "Utilisateurs.findByTelephone", query = "SELECT u FROM Utilisateurs u WHERE u.telephone = :telephone"),
    @NamedQuery(name = "Utilisateurs.findByRole", query = "SELECT u FROM Utilisateurs u WHERE u.role = :role"),
    @NamedQuery(name = "Utilisateurs.findByDeleted", query = "SELECT u FROM Utilisateurs u WHERE u.deleted = :deleted")})
public class Utilisateurs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "USERNAME")
    private String username;
    @Column(name = "MOTDEPASSE")
    private String motdepasse;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "UPDATED_BY")
    private String updatedBy;
    @Column(name = "NOM")
    private String nom;
    @Column(name = "PRENOM")
    private String prenom;
    @Column(name = "ADRESSE")
    private String adresse;
    @Column(name = "TELEPHONE")
    private String telephone;
    @Column(name = "role")
    private String role;
    @Basic(optional = false)
    @Column(name = "deleted")
    private boolean deleted;
    @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
    @ManyToOne
    private Roles roleId;
    @OneToMany(mappedBy = "utilisateurId")
    private List<Vendres> vendresList;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "utilisateurs")
    private Employes employes;

    public Utilisateurs() {
    }

    public Utilisateurs(Integer id) {
        this.id = id;
    }

    public Utilisateurs(Integer id, boolean deleted) {
        this.id = id;
        this.deleted = deleted;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMotdepasse() {
        return motdepasse;
    }

    public void setMotdepasse(String motdepasse) {
        this.motdepasse = motdepasse;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Roles getRoleId() {
        return roleId;
    }

    public void setRoleId(Roles roleId) {
        this.roleId = roleId;
    }

    @XmlTransient
    public List<Vendres> getVendresList() {
        return vendresList;
    }

    public void setVendresList(List<Vendres> vendresList) {
        this.vendresList = vendresList;
    }

    public Employes getEmployes() {
        return employes;
    }

    public void setEmployes(Employes employes) {
        this.employes = employes;
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
        if (!(object instanceof Utilisateurs)) {
            return false;
        }
        Utilisateurs other = (Utilisateurs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "geststock.classes.Utilisateurs[ id=" + id + " ]";
    }
    
    /**
     * Cette methode permettra de creer un utilisateur
     */
    public boolean createUtilisateur(){
        boolean b=false;
        try {
            OutilUtilities.userJpa.create(this);
            b=true;
        } catch (Exception e) {
            b=false;
        }
        return b;
    }
    
    
    /**
     * Obtenir la liste des utilisateurs valides
     * @return 
     */
    public List<Utilisateurs> listUtilisateurValide(){
        
        return OutilUtilities.userJpa.listUtilisateurValide();
    }
    
    public Utilisateurs obtenirUtilisateur(){
        return OutilUtilities.userJpa.findUtilisateurs(this.id);
    }
    
    public Utilisateurs authentification(){
        return OutilUtilities.userJpa.authentification(this);
    }  
}
