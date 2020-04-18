/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geststock.crud_methods;

import geststock.classes.Entreprise;
import geststock.crud_methods.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author DELL
 */
public class EntrepriseJpaController implements Serializable {

    public EntrepriseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Entreprise entreprise) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(entreprise);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Entreprise entreprise) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            entreprise = em.merge(entreprise);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = entreprise.getId();
                if (findEntreprise(id) == null) {
                    throw new NonexistentEntityException("The entreprise with id " + id + " no longer exists.");
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
            Entreprise entreprise;
            try {
                entreprise = em.getReference(Entreprise.class, id);
                entreprise.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The entreprise with id " + id + " no longer exists.", enfe);
            }
            em.remove(entreprise);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Entreprise> findEntrepriseEntities() {
        return findEntrepriseEntities(true, -1, -1);
    }

    public List<Entreprise> findEntrepriseEntities(int maxResults, int firstResult) {
        return findEntrepriseEntities(false, maxResults, firstResult);
    }

    private List<Entreprise> findEntrepriseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Entreprise.class));
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

    public Entreprise findEntreprise(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Entreprise.class, id);
        } finally {
            em.close();
        }
    }

    public int getEntrepriseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Entreprise> rt = cq.from(Entreprise.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
