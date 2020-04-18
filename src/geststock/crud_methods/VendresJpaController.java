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
import geststock.classes.Articles;
import geststock.classes.Utilisateurs;
import geststock.classes.Vendres;
import geststock.crud_methods.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author DELL
 */
public class VendresJpaController implements Serializable {

    public VendresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vendres vendres) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articles articleId = vendres.getArticleId();
            if (articleId != null) {
                articleId = em.getReference(articleId.getClass(), articleId.getId());
                vendres.setArticleId(articleId);
            }
            Utilisateurs utilisateurId = vendres.getUtilisateurId();
            if (utilisateurId != null) {
                utilisateurId = em.getReference(utilisateurId.getClass(), utilisateurId.getId());
                vendres.setUtilisateurId(utilisateurId);
            }
            em.persist(vendres);
            if (articleId != null) {
                articleId.getVendresList().add(vendres);
                articleId = em.merge(articleId);
            }
            if (utilisateurId != null) {
                utilisateurId.getVendresList().add(vendres);
                utilisateurId = em.merge(utilisateurId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vendres vendres) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vendres persistentVendres = em.find(Vendres.class, vendres.getId());
            Articles articleIdOld = persistentVendres.getArticleId();
            Articles articleIdNew = vendres.getArticleId();
            Utilisateurs utilisateurIdOld = persistentVendres.getUtilisateurId();
            Utilisateurs utilisateurIdNew = vendres.getUtilisateurId();
            if (articleIdNew != null) {
                articleIdNew = em.getReference(articleIdNew.getClass(), articleIdNew.getId());
                vendres.setArticleId(articleIdNew);
            }
            if (utilisateurIdNew != null) {
                utilisateurIdNew = em.getReference(utilisateurIdNew.getClass(), utilisateurIdNew.getId());
                vendres.setUtilisateurId(utilisateurIdNew);
            }
            vendres = em.merge(vendres);
            if (articleIdOld != null && !articleIdOld.equals(articleIdNew)) {
                articleIdOld.getVendresList().remove(vendres);
                articleIdOld = em.merge(articleIdOld);
            }
            if (articleIdNew != null && !articleIdNew.equals(articleIdOld)) {
                articleIdNew.getVendresList().add(vendres);
                articleIdNew = em.merge(articleIdNew);
            }
            if (utilisateurIdOld != null && !utilisateurIdOld.equals(utilisateurIdNew)) {
                utilisateurIdOld.getVendresList().remove(vendres);
                utilisateurIdOld = em.merge(utilisateurIdOld);
            }
            if (utilisateurIdNew != null && !utilisateurIdNew.equals(utilisateurIdOld)) {
                utilisateurIdNew.getVendresList().add(vendres);
                utilisateurIdNew = em.merge(utilisateurIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vendres.getId();
                if (findVendres(id) == null) {
                    throw new NonexistentEntityException("The vendres with id " + id + " no longer exists.");
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
            Vendres vendres;
            try {
                vendres = em.getReference(Vendres.class, id);
                vendres.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vendres with id " + id + " no longer exists.", enfe);
            }
            Articles articleId = vendres.getArticleId();
            if (articleId != null) {
                articleId.getVendresList().remove(vendres);
                articleId = em.merge(articleId);
            }
            Utilisateurs utilisateurId = vendres.getUtilisateurId();
            if (utilisateurId != null) {
                utilisateurId.getVendresList().remove(vendres);
                utilisateurId = em.merge(utilisateurId);
            }
            em.remove(vendres);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vendres> findVendresEntities() {
        return findVendresEntities(true, -1, -1);
    }

    public List<Vendres> findVendresEntities(int maxResults, int firstResult) {
        return findVendresEntities(false, maxResults, firstResult);
    }

    private List<Vendres> findVendresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vendres.class));
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

    public Vendres findVendres(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vendres.class, id);
        } finally {
            em.close();
        }
    }

    public int getVendresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vendres> rt = cq.from(Vendres.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
