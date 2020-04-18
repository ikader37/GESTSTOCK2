/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geststock.crud_methods;

import geststock.classes.Roles;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import geststock.classes.Utilisateurs;
import geststock.crud_methods.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author DELL
 */
public class RolesJpaController implements Serializable {

    public RolesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Roles roles) {
        if (roles.getUtilisateursList() == null) {
            roles.setUtilisateursList(new ArrayList<Utilisateurs>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Utilisateurs> attachedUtilisateursList = new ArrayList<Utilisateurs>();
            for (Utilisateurs utilisateursListUtilisateursToAttach : roles.getUtilisateursList()) {
                utilisateursListUtilisateursToAttach = em.getReference(utilisateursListUtilisateursToAttach.getClass(), utilisateursListUtilisateursToAttach.getId());
                attachedUtilisateursList.add(utilisateursListUtilisateursToAttach);
            }
            roles.setUtilisateursList(attachedUtilisateursList);
            em.persist(roles);
            for (Utilisateurs utilisateursListUtilisateurs : roles.getUtilisateursList()) {
                Roles oldRoleIdOfUtilisateursListUtilisateurs = utilisateursListUtilisateurs.getRoleId();
                utilisateursListUtilisateurs.setRoleId(roles);
                utilisateursListUtilisateurs = em.merge(utilisateursListUtilisateurs);
                if (oldRoleIdOfUtilisateursListUtilisateurs != null) {
                    oldRoleIdOfUtilisateursListUtilisateurs.getUtilisateursList().remove(utilisateursListUtilisateurs);
                    oldRoleIdOfUtilisateursListUtilisateurs = em.merge(oldRoleIdOfUtilisateursListUtilisateurs);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Roles roles) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles persistentRoles = em.find(Roles.class, roles.getId());
            List<Utilisateurs> utilisateursListOld = persistentRoles.getUtilisateursList();
            List<Utilisateurs> utilisateursListNew = roles.getUtilisateursList();
            List<Utilisateurs> attachedUtilisateursListNew = new ArrayList<Utilisateurs>();
            for (Utilisateurs utilisateursListNewUtilisateursToAttach : utilisateursListNew) {
                utilisateursListNewUtilisateursToAttach = em.getReference(utilisateursListNewUtilisateursToAttach.getClass(), utilisateursListNewUtilisateursToAttach.getId());
                attachedUtilisateursListNew.add(utilisateursListNewUtilisateursToAttach);
            }
            utilisateursListNew = attachedUtilisateursListNew;
            roles.setUtilisateursList(utilisateursListNew);
            roles = em.merge(roles);
            for (Utilisateurs utilisateursListOldUtilisateurs : utilisateursListOld) {
                if (!utilisateursListNew.contains(utilisateursListOldUtilisateurs)) {
                    utilisateursListOldUtilisateurs.setRoleId(null);
                    utilisateursListOldUtilisateurs = em.merge(utilisateursListOldUtilisateurs);
                }
            }
            for (Utilisateurs utilisateursListNewUtilisateurs : utilisateursListNew) {
                if (!utilisateursListOld.contains(utilisateursListNewUtilisateurs)) {
                    Roles oldRoleIdOfUtilisateursListNewUtilisateurs = utilisateursListNewUtilisateurs.getRoleId();
                    utilisateursListNewUtilisateurs.setRoleId(roles);
                    utilisateursListNewUtilisateurs = em.merge(utilisateursListNewUtilisateurs);
                    if (oldRoleIdOfUtilisateursListNewUtilisateurs != null && !oldRoleIdOfUtilisateursListNewUtilisateurs.equals(roles)) {
                        oldRoleIdOfUtilisateursListNewUtilisateurs.getUtilisateursList().remove(utilisateursListNewUtilisateurs);
                        oldRoleIdOfUtilisateursListNewUtilisateurs = em.merge(oldRoleIdOfUtilisateursListNewUtilisateurs);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = roles.getId();
                if (findRoles(id) == null) {
                    throw new NonexistentEntityException("The roles with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Roles roles;
            try {
                roles = em.getReference(Roles.class, id);
                roles.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The roles with id " + id + " no longer exists.", enfe);
            }
            List<Utilisateurs> utilisateursList = roles.getUtilisateursList();
            for (Utilisateurs utilisateursListUtilisateurs : utilisateursList) {
                utilisateursListUtilisateurs.setRoleId(null);
                utilisateursListUtilisateurs = em.merge(utilisateursListUtilisateurs);
            }
            em.remove(roles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Roles> findRolesEntities() {
        return findRolesEntities(true, -1, -1);
    }

    public List<Roles> findRolesEntities(int maxResults, int firstResult) {
        return findRolesEntities(false, maxResults, firstResult);
    }

    private List<Roles> findRolesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Roles.class));
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

    public Roles findRoles(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Roles.class, id);
        } finally {
            em.close();
        }
    }

    public int getRolesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Roles> rt = cq.from(Roles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
