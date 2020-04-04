/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geststock.crud_methods;

import geststock.classes.Employes;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import geststock.classes.Utilisateurs;
import geststock.crud_methods.exceptions.IllegalOrphanException;
import geststock.crud_methods.exceptions.NonexistentEntityException;
import geststock.crud_methods.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author DELL
 */
public class EmployesJpaController implements Serializable {

    public EmployesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Employes employes) throws IllegalOrphanException, PreexistingEntityException, Exception {
        List<String> illegalOrphanMessages = null;
        Utilisateurs utilisateursOrphanCheck = employes.getUtilisateurs();
        if (utilisateursOrphanCheck != null) {
            Employes oldEmployesOfUtilisateurs = utilisateursOrphanCheck.getEmployes();
            if (oldEmployesOfUtilisateurs != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The Utilisateurs " + utilisateursOrphanCheck + " already has an item of type Employes whose utilisateurs column cannot be null. Please make another selection for the utilisateurs field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Utilisateurs utilisateurs = employes.getUtilisateurs();
            if (utilisateurs != null) {
                utilisateurs = em.getReference(utilisateurs.getClass(), utilisateurs.getId());
                employes.setUtilisateurs(utilisateurs);
            }
            em.persist(employes);
            if (utilisateurs != null) {
                utilisateurs.setEmployes(employes);
                utilisateurs = em.merge(utilisateurs);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEmployes(employes.getUtilisateurId()) != null) {
                throw new PreexistingEntityException("Employes " + employes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Employes employes) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Employes persistentEmployes = em.find(Employes.class, employes.getUtilisateurId());
            Utilisateurs utilisateursOld = persistentEmployes.getUtilisateurs();
            Utilisateurs utilisateursNew = employes.getUtilisateurs();
            List<String> illegalOrphanMessages = null;
            if (utilisateursNew != null && !utilisateursNew.equals(utilisateursOld)) {
                Employes oldEmployesOfUtilisateurs = utilisateursNew.getEmployes();
                if (oldEmployesOfUtilisateurs != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The Utilisateurs " + utilisateursNew + " already has an item of type Employes whose utilisateurs column cannot be null. Please make another selection for the utilisateurs field.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (utilisateursNew != null) {
                utilisateursNew = em.getReference(utilisateursNew.getClass(), utilisateursNew.getId());
                employes.setUtilisateurs(utilisateursNew);
            }
            employes = em.merge(employes);
            if (utilisateursOld != null && !utilisateursOld.equals(utilisateursNew)) {
                utilisateursOld.setEmployes(null);
                utilisateursOld = em.merge(utilisateursOld);
            }
            if (utilisateursNew != null && !utilisateursNew.equals(utilisateursOld)) {
                utilisateursNew.setEmployes(employes);
                utilisateursNew = em.merge(utilisateursNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = employes.getUtilisateurId();
                if (findEmployes(id) == null) {
                    throw new NonexistentEntityException("The employes with id " + id + " no longer exists.");
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
            Employes employes;
            try {
                employes = em.getReference(Employes.class, id);
                employes.getUtilisateurId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The employes with id " + id + " no longer exists.", enfe);
            }
            Utilisateurs utilisateurs = employes.getUtilisateurs();
            if (utilisateurs != null) {
                utilisateurs.setEmployes(null);
                utilisateurs = em.merge(utilisateurs);
            }
            em.remove(employes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Employes> findEmployesEntities() {
        return findEmployesEntities(true, -1, -1);
    }

    public List<Employes> findEmployesEntities(int maxResults, int firstResult) {
        return findEmployesEntities(false, maxResults, firstResult);
    }

    private List<Employes> findEmployesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Employes.class));
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

    public Employes findEmployes(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Employes.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmployesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Employes> rt = cq.from(Employes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
