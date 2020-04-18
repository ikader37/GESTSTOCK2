/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geststock.crud_methods;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import geststock.classes.Roles;
import geststock.classes.Employes;
import geststock.classes.Utilisateurs;
import geststock.classes.Vendres;
import geststock.crud_methods.exceptions.IllegalOrphanException;
import geststock.crud_methods.exceptions.NonexistentEntityException;
import geststock.menus.MenuAdmin;
import geststock.menus.MenuCaissier;
import geststock.menus.MenuSecretaire;
import geststock.utilities.OutilUtilities;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author DELL
 */
public class UtilisateursJpaController implements Serializable {

    public UtilisateursJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Utilisateurs utilisateurs) {
        if (utilisateurs.getVendresList() == null) {
            utilisateurs.setVendresList(new ArrayList<Vendres>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles roleId = utilisateurs.getRoleId();
            if (roleId != null) {
                roleId = em.getReference(roleId.getClass(), roleId.getId());
                utilisateurs.setRoleId(roleId);
            }
            Employes employes = utilisateurs.getEmployes();
            if (employes != null) {
                employes = em.getReference(employes.getClass(), employes.getUtilisateurId());
                utilisateurs.setEmployes(employes);
            }
            List<Vendres> attachedVendresList = new ArrayList<Vendres>();
            for (Vendres vendresListVendresToAttach : utilisateurs.getVendresList()) {
                vendresListVendresToAttach = em.getReference(vendresListVendresToAttach.getClass(), vendresListVendresToAttach.getId());
                attachedVendresList.add(vendresListVendresToAttach);
            }
            utilisateurs.setVendresList(attachedVendresList);
            em.persist(utilisateurs);
            if (roleId != null) {
                roleId.getUtilisateursList().add(utilisateurs);
                roleId = em.merge(roleId);
            }
            if (employes != null) {
                Utilisateurs oldUtilisateursOfEmployes = employes.getUtilisateurs();
                if (oldUtilisateursOfEmployes != null) {
                    oldUtilisateursOfEmployes.setEmployes(null);
                    oldUtilisateursOfEmployes = em.merge(oldUtilisateursOfEmployes);
                }
                employes.setUtilisateurs(utilisateurs);
                employes = em.merge(employes);
            }
            for (Vendres vendresListVendres : utilisateurs.getVendresList()) {
                Utilisateurs oldUtilisateurIdOfVendresListVendres = vendresListVendres.getUtilisateurId();
                vendresListVendres.setUtilisateurId(utilisateurs);
                vendresListVendres = em.merge(vendresListVendres);
                if (oldUtilisateurIdOfVendresListVendres != null) {
                    oldUtilisateurIdOfVendresListVendres.getVendresList().remove(vendresListVendres);
                    oldUtilisateurIdOfVendresListVendres = em.merge(oldUtilisateurIdOfVendresListVendres);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Utilisateurs utilisateurs) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Utilisateurs persistentUtilisateurs = em.find(Utilisateurs.class, utilisateurs.getId());
            Roles roleIdOld = persistentUtilisateurs.getRoleId();
            Roles roleIdNew = utilisateurs.getRoleId();
            Employes employesOld = persistentUtilisateurs.getEmployes();
            Employes employesNew = utilisateurs.getEmployes();
            List<Vendres> vendresListOld = persistentUtilisateurs.getVendresList();
            List<Vendres> vendresListNew = utilisateurs.getVendresList();
            List<String> illegalOrphanMessages = null;
            if (employesOld != null && !employesOld.equals(employesNew)) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("You must retain Employes " + employesOld + " since its utilisateurs field is not nullable.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (roleIdNew != null) {
                roleIdNew = em.getReference(roleIdNew.getClass(), roleIdNew.getId());
                utilisateurs.setRoleId(roleIdNew);
            }
            if (employesNew != null) {
                employesNew = em.getReference(employesNew.getClass(), employesNew.getUtilisateurId());
                utilisateurs.setEmployes(employesNew);
            }
            List<Vendres> attachedVendresListNew = new ArrayList<Vendres>();
            for (Vendres vendresListNewVendresToAttach : vendresListNew) {
                vendresListNewVendresToAttach = em.getReference(vendresListNewVendresToAttach.getClass(), vendresListNewVendresToAttach.getId());
                attachedVendresListNew.add(vendresListNewVendresToAttach);
            }
            vendresListNew = attachedVendresListNew;
            utilisateurs.setVendresList(vendresListNew);
            utilisateurs = em.merge(utilisateurs);
            if (roleIdOld != null && !roleIdOld.equals(roleIdNew)) {
                roleIdOld.getUtilisateursList().remove(utilisateurs);
                roleIdOld = em.merge(roleIdOld);
            }
            if (roleIdNew != null && !roleIdNew.equals(roleIdOld)) {
                roleIdNew.getUtilisateursList().add(utilisateurs);
                roleIdNew = em.merge(roleIdNew);
            }
            if (employesNew != null && !employesNew.equals(employesOld)) {
                Utilisateurs oldUtilisateursOfEmployes = employesNew.getUtilisateurs();
                if (oldUtilisateursOfEmployes != null) {
                    oldUtilisateursOfEmployes.setEmployes(null);
                    oldUtilisateursOfEmployes = em.merge(oldUtilisateursOfEmployes);
                }
                employesNew.setUtilisateurs(utilisateurs);
                employesNew = em.merge(employesNew);
            }
            for (Vendres vendresListOldVendres : vendresListOld) {
                if (!vendresListNew.contains(vendresListOldVendres)) {
                    vendresListOldVendres.setUtilisateurId(null);
                    vendresListOldVendres = em.merge(vendresListOldVendres);
                }
            }
            for (Vendres vendresListNewVendres : vendresListNew) {
                if (!vendresListOld.contains(vendresListNewVendres)) {
                    Utilisateurs oldUtilisateurIdOfVendresListNewVendres = vendresListNewVendres.getUtilisateurId();
                    vendresListNewVendres.setUtilisateurId(utilisateurs);
                    vendresListNewVendres = em.merge(vendresListNewVendres);
                    if (oldUtilisateurIdOfVendresListNewVendres != null && !oldUtilisateurIdOfVendresListNewVendres.equals(utilisateurs)) {
                        oldUtilisateurIdOfVendresListNewVendres.getVendresList().remove(vendresListNewVendres);
                        oldUtilisateurIdOfVendresListNewVendres = em.merge(oldUtilisateurIdOfVendresListNewVendres);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = utilisateurs.getId();
                if (findUtilisateurs(id) == null) {
                    throw new NonexistentEntityException("The utilisateurs with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Utilisateurs utilisateurs;
            try {
                utilisateurs = em.getReference(Utilisateurs.class, id);
                utilisateurs.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The utilisateurs with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Employes employesOrphanCheck = utilisateurs.getEmployes();
            if (employesOrphanCheck != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Utilisateurs (" + utilisateurs + ") cannot be destroyed since the Employes " + employesOrphanCheck + " in its employes field has a non-nullable utilisateurs field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Roles roleId = utilisateurs.getRoleId();
            if (roleId != null) {
                roleId.getUtilisateursList().remove(utilisateurs);
                roleId = em.merge(roleId);
            }
            List<Vendres> vendresList = utilisateurs.getVendresList();
            for (Vendres vendresListVendres : vendresList) {
                vendresListVendres.setUtilisateurId(null);
                vendresListVendres = em.merge(vendresListVendres);
            }
            em.remove(utilisateurs);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Utilisateurs> findUtilisateursEntities() {
        return findUtilisateursEntities(true, -1, -1);
    }

    public List<Utilisateurs> findUtilisateursEntities(int maxResults, int firstResult) {
        return findUtilisateursEntities(false, maxResults, firstResult);
    }

    private List<Utilisateurs> findUtilisateursEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Utilisateurs.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Utilisateurs findUtilisateurs(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Utilisateurs.class, id);
        } finally {
            em.close();
        }
    }

    public int getUtilisateursCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Utilisateurs> rt = cq.from(Utilisateurs.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    /**
     * Retourne la liste des utilisateurs valides
     * @return 
     */
    public List<Utilisateurs> listUtilisateurValide(){
       List<Utilisateurs> listU=this.emf.createEntityManager().createNamedQuery("Utilisateurs.findByDeleted",Utilisateurs.class).setParameter("deleted", false).getResultList();
        return listU;
    }
    
    /**
     * Cette fonction permet de recherche un utilisateur en fonction de son username.
     * Elle se pour l'authentification
     * @param u
     * @return 
     */
    public Utilisateurs authentification(Utilisateurs u){
        List<Utilisateurs> us=this.emf.createEntityManager().createNamedQuery("Utilisateurs.findByUsername",Utilisateurs.class).setParameter("username", u.getUsername()).getResultList();
        if(us.size()>0){
            if(us.get(0).getMotdepasse().equals(u.getMotdepasse())){
                if(us.get(0).getRole().equals("Administrateur")){
                    OutilUtilities.menu=new MenuAdmin();
                    OutilUtilities.userActuel=us.get(0);
                    System.out.println("Admin");
                }
                if(us.get(0).getRole().equals("Caissier")){
                    OutilUtilities.menu=new MenuCaissier();
                    OutilUtilities.userActuel=us.get(0);
                    System.out.println("Caissier");
                }
                 if(us.get(0).getRole().equals("Sécrétaire")){
                    OutilUtilities.menu=new MenuSecretaire();
                    OutilUtilities.userActuel=us.get(0);
                    System.out.println("Secretaire");
                }
                return us.get(0);
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
}
