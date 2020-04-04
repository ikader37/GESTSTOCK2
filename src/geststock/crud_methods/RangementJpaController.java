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
import geststock.classes.Rangement;
import geststock.crud_methods.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author DELL
 */
public class RangementJpaController implements Serializable {

    public RangementJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Rangement rangement) {
        if (rangement.getArticlesList() == null) {
            rangement.setArticlesList(new ArrayList<Articles>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Articles> attachedArticlesList = new ArrayList<Articles>();
            for (Articles articlesListArticlesToAttach : rangement.getArticlesList()) {
                articlesListArticlesToAttach = em.getReference(articlesListArticlesToAttach.getClass(), articlesListArticlesToAttach.getId());
                attachedArticlesList.add(articlesListArticlesToAttach);
            }
            rangement.setArticlesList(attachedArticlesList);
            em.persist(rangement);
            for (Articles articlesListArticles : rangement.getArticlesList()) {
                Rangement oldIdrangemenntOfArticlesListArticles = articlesListArticles.getIdrangemennt();
                articlesListArticles.setIdrangemennt(rangement);
                articlesListArticles = em.merge(articlesListArticles);
                if (oldIdrangemenntOfArticlesListArticles != null) {
                    oldIdrangemenntOfArticlesListArticles.getArticlesList().remove(articlesListArticles);
                    oldIdrangemenntOfArticlesListArticles = em.merge(oldIdrangemenntOfArticlesListArticles);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Rangement rangement) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Rangement persistentRangement = em.find(Rangement.class, rangement.getId());
            List<Articles> articlesListOld = persistentRangement.getArticlesList();
            List<Articles> articlesListNew = rangement.getArticlesList();
            List<Articles> attachedArticlesListNew = new ArrayList<Articles>();
            for (Articles articlesListNewArticlesToAttach : articlesListNew) {
                articlesListNewArticlesToAttach = em.getReference(articlesListNewArticlesToAttach.getClass(), articlesListNewArticlesToAttach.getId());
                attachedArticlesListNew.add(articlesListNewArticlesToAttach);
            }
            articlesListNew = attachedArticlesListNew;
            rangement.setArticlesList(articlesListNew);
            rangement = em.merge(rangement);
            for (Articles articlesListOldArticles : articlesListOld) {
                if (!articlesListNew.contains(articlesListOldArticles)) {
                    articlesListOldArticles.setIdrangemennt(null);
                    articlesListOldArticles = em.merge(articlesListOldArticles);
                }
            }
            for (Articles articlesListNewArticles : articlesListNew) {
                if (!articlesListOld.contains(articlesListNewArticles)) {
                    Rangement oldIdrangemenntOfArticlesListNewArticles = articlesListNewArticles.getIdrangemennt();
                    articlesListNewArticles.setIdrangemennt(rangement);
                    articlesListNewArticles = em.merge(articlesListNewArticles);
                    if (oldIdrangemenntOfArticlesListNewArticles != null && !oldIdrangemenntOfArticlesListNewArticles.equals(rangement)) {
                        oldIdrangemenntOfArticlesListNewArticles.getArticlesList().remove(articlesListNewArticles);
                        oldIdrangemenntOfArticlesListNewArticles = em.merge(oldIdrangemenntOfArticlesListNewArticles);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = rangement.getId();
                if (findRangement(id) == null) {
                    throw new NonexistentEntityException("The rangement with id " + id + " no longer exists.");
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
            Rangement rangement;
            try {
                rangement = em.getReference(Rangement.class, id);
                rangement.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The rangement with id " + id + " no longer exists.", enfe);
            }
            List<Articles> articlesList = rangement.getArticlesList();
            for (Articles articlesListArticles : articlesList) {
                articlesListArticles.setIdrangemennt(null);
                articlesListArticles = em.merge(articlesListArticles);
            }
            em.remove(rangement);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Rangement> findRangementEntities() {
        return findRangementEntities(true, -1, -1);
    }

    public List<Rangement> findRangementEntities(int maxResults, int firstResult) {
        return findRangementEntities(false, maxResults, firstResult);
    }

    private List<Rangement> findRangementEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Rangement.class));
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

    public Rangement findRangement(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Rangement.class, id);
        } finally {
            em.close();
        }
    }

    public int getRangementCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Rangement> rt = cq.from(Rangement.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Rangement> listRangementValide(){
        
        return this.emf.createEntityManager().createNamedQuery("Rangement.findByDeleted", Rangement.class).setParameter("deleted", false).getResultList();
    }
    
}
