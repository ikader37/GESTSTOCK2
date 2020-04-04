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
import geststock.classes.Commande;
import geststock.classes.Facture;
import geststock.crud_methods.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author DELL
 */
public class FactureJpaController implements Serializable {

    public FactureJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Facture facture) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Commande idcommande = facture.getIdcommande();
            if (idcommande != null) {
                idcommande = em.getReference(idcommande.getClass(), idcommande.getId());
                facture.setIdcommande(idcommande);
            }
            em.persist(facture);
            if (idcommande != null) {
                idcommande.getFactureList().add(facture);
                idcommande = em.merge(idcommande);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Facture facture) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Facture persistentFacture = em.find(Facture.class, facture.getId());
            Commande idcommandeOld = persistentFacture.getIdcommande();
            Commande idcommandeNew = facture.getIdcommande();
            if (idcommandeNew != null) {
                idcommandeNew = em.getReference(idcommandeNew.getClass(), idcommandeNew.getId());
                facture.setIdcommande(idcommandeNew);
            }
            facture = em.merge(facture);
            if (idcommandeOld != null && !idcommandeOld.equals(idcommandeNew)) {
                idcommandeOld.getFactureList().remove(facture);
                idcommandeOld = em.merge(idcommandeOld);
            }
            if (idcommandeNew != null && !idcommandeNew.equals(idcommandeOld)) {
                idcommandeNew.getFactureList().add(facture);
                idcommandeNew = em.merge(idcommandeNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = facture.getId();
                if (findFacture(id) == null) {
                    throw new NonexistentEntityException("The facture with id " + id + " no longer exists.");
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
            Facture facture;
            try {
                facture = em.getReference(Facture.class, id);
                facture.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The facture with id " + id + " no longer exists.", enfe);
            }
            Commande idcommande = facture.getIdcommande();
            if (idcommande != null) {
                idcommande.getFactureList().remove(facture);
                idcommande = em.merge(idcommande);
            }
            em.remove(facture);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Facture> findFactureEntities() {
        return findFactureEntities(true, -1, -1);
    }

    public List<Facture> findFactureEntities(int maxResults, int firstResult) {
        return findFactureEntities(false, maxResults, firstResult);
    }

    private List<Facture> findFactureEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Facture.class));
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

    public Facture findFacture(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Facture.class, id);
        } finally {
            em.close();
        }
    }

    public int getFactureCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Facture> rt = cq.from(Facture.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
