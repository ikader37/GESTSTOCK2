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
import geststock.classes.Fournisseurs;
import geststock.crud_methods.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author DELL
 */
public class FournisseursJpaController implements Serializable {

    public FournisseursJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fournisseurs fournisseurs) {
        if (fournisseurs.getArticlesList() == null) {
            fournisseurs.setArticlesList(new ArrayList<Articles>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Articles> attachedArticlesList = new ArrayList<Articles>();
            for (Articles articlesListArticlesToAttach : fournisseurs.getArticlesList()) {
                articlesListArticlesToAttach = em.getReference(articlesListArticlesToAttach.getClass(), articlesListArticlesToAttach.getId());
                attachedArticlesList.add(articlesListArticlesToAttach);
            }
            fournisseurs.setArticlesList(attachedArticlesList);
            em.persist(fournisseurs);
            for (Articles articlesListArticles : fournisseurs.getArticlesList()) {
                Fournisseurs oldFournisseurIdOfArticlesListArticles = articlesListArticles.getFournisseurId();
                articlesListArticles.setFournisseurId(fournisseurs);
                articlesListArticles = em.merge(articlesListArticles);
                if (oldFournisseurIdOfArticlesListArticles != null) {
                    oldFournisseurIdOfArticlesListArticles.getArticlesList().remove(articlesListArticles);
                    oldFournisseurIdOfArticlesListArticles = em.merge(oldFournisseurIdOfArticlesListArticles);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fournisseurs fournisseurs) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fournisseurs persistentFournisseurs = em.find(Fournisseurs.class, fournisseurs.getId());
            List<Articles> articlesListOld = persistentFournisseurs.getArticlesList();
            List<Articles> articlesListNew = fournisseurs.getArticlesList();
            List<Articles> attachedArticlesListNew = new ArrayList<Articles>();
            for (Articles articlesListNewArticlesToAttach : articlesListNew) {
                articlesListNewArticlesToAttach = em.getReference(articlesListNewArticlesToAttach.getClass(), articlesListNewArticlesToAttach.getId());
                attachedArticlesListNew.add(articlesListNewArticlesToAttach);
            }
            articlesListNew = attachedArticlesListNew;
            fournisseurs.setArticlesList(articlesListNew);
            fournisseurs = em.merge(fournisseurs);
            for (Articles articlesListOldArticles : articlesListOld) {
                if (!articlesListNew.contains(articlesListOldArticles)) {
                    articlesListOldArticles.setFournisseurId(null);
                    articlesListOldArticles = em.merge(articlesListOldArticles);
                }
            }
            for (Articles articlesListNewArticles : articlesListNew) {
                if (!articlesListOld.contains(articlesListNewArticles)) {
                    Fournisseurs oldFournisseurIdOfArticlesListNewArticles = articlesListNewArticles.getFournisseurId();
                    articlesListNewArticles.setFournisseurId(fournisseurs);
                    articlesListNewArticles = em.merge(articlesListNewArticles);
                    if (oldFournisseurIdOfArticlesListNewArticles != null && !oldFournisseurIdOfArticlesListNewArticles.equals(fournisseurs)) {
                        oldFournisseurIdOfArticlesListNewArticles.getArticlesList().remove(articlesListNewArticles);
                        oldFournisseurIdOfArticlesListNewArticles = em.merge(oldFournisseurIdOfArticlesListNewArticles);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fournisseurs.getId();
                if (findFournisseurs(id) == null) {
                    throw new NonexistentEntityException("The fournisseurs with id " + id + " no longer exists.");
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
            Fournisseurs fournisseurs;
            try {
                fournisseurs = em.getReference(Fournisseurs.class, id);
                fournisseurs.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fournisseurs with id " + id + " no longer exists.", enfe);
            }
            List<Articles> articlesList = fournisseurs.getArticlesList();
            for (Articles articlesListArticles : articlesList) {
                articlesListArticles.setFournisseurId(null);
                articlesListArticles = em.merge(articlesListArticles);
            }
            em.remove(fournisseurs);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fournisseurs> findFournisseursEntities() {
        return findFournisseursEntities(true, -1, -1);
    }

    public List<Fournisseurs> findFournisseursEntities(int maxResults, int firstResult) {
        return findFournisseursEntities(false, maxResults, firstResult);
    }

    private List<Fournisseurs> findFournisseursEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fournisseurs.class));
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

    public Fournisseurs findFournisseurs(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fournisseurs.class, id);
        } finally {
            em.close();
        }
    }

    public int getFournisseursCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fournisseurs> rt = cq.from(Fournisseurs.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
