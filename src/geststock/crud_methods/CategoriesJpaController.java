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
import geststock.classes.Categories;
import geststock.crud_methods.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author DELL
 */
public class CategoriesJpaController implements Serializable {

    public CategoriesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Categories categories) {
        if (categories.getArticlesList() == null) {
            categories.setArticlesList(new ArrayList<Articles>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Articles> attachedArticlesList = new ArrayList<Articles>();
            for (Articles articlesListArticlesToAttach : categories.getArticlesList()) {
                articlesListArticlesToAttach = em.getReference(articlesListArticlesToAttach.getClass(), articlesListArticlesToAttach.getId());
                attachedArticlesList.add(articlesListArticlesToAttach);
            }
            categories.setArticlesList(attachedArticlesList);
            em.persist(categories);
            for (Articles articlesListArticles : categories.getArticlesList()) {
                Categories oldCategorieIdOfArticlesListArticles = articlesListArticles.getCategorieId();
                articlesListArticles.setCategorieId(categories);
                articlesListArticles = em.merge(articlesListArticles);
                if (oldCategorieIdOfArticlesListArticles != null) {
                    oldCategorieIdOfArticlesListArticles.getArticlesList().remove(articlesListArticles);
                    oldCategorieIdOfArticlesListArticles = em.merge(oldCategorieIdOfArticlesListArticles);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Categories categories) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categories persistentCategories = em.find(Categories.class, categories.getId());
            List<Articles> articlesListOld = persistentCategories.getArticlesList();
            List<Articles> articlesListNew = categories.getArticlesList();
            List<Articles> attachedArticlesListNew = new ArrayList<Articles>();
            for (Articles articlesListNewArticlesToAttach : articlesListNew) {
                articlesListNewArticlesToAttach = em.getReference(articlesListNewArticlesToAttach.getClass(), articlesListNewArticlesToAttach.getId());
                attachedArticlesListNew.add(articlesListNewArticlesToAttach);
            }
            articlesListNew = attachedArticlesListNew;
            categories.setArticlesList(articlesListNew);
            categories = em.merge(categories);
            for (Articles articlesListOldArticles : articlesListOld) {
                if (!articlesListNew.contains(articlesListOldArticles)) {
                    articlesListOldArticles.setCategorieId(null);
                    articlesListOldArticles = em.merge(articlesListOldArticles);
                }
            }
            for (Articles articlesListNewArticles : articlesListNew) {
                if (!articlesListOld.contains(articlesListNewArticles)) {
                    Categories oldCategorieIdOfArticlesListNewArticles = articlesListNewArticles.getCategorieId();
                    articlesListNewArticles.setCategorieId(categories);
                    articlesListNewArticles = em.merge(articlesListNewArticles);
                    if (oldCategorieIdOfArticlesListNewArticles != null && !oldCategorieIdOfArticlesListNewArticles.equals(categories)) {
                        oldCategorieIdOfArticlesListNewArticles.getArticlesList().remove(articlesListNewArticles);
                        oldCategorieIdOfArticlesListNewArticles = em.merge(oldCategorieIdOfArticlesListNewArticles);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = categories.getId();
                if (findCategories(id) == null) {
                    throw new NonexistentEntityException("The categories with id " + id + " no longer exists.");
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
            Categories categories;
            try {
                categories = em.getReference(Categories.class, id);
                categories.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The categories with id " + id + " no longer exists.", enfe);
            }
            List<Articles> articlesList = categories.getArticlesList();
            for (Articles articlesListArticles : articlesList) {
                articlesListArticles.setCategorieId(null);
                articlesListArticles = em.merge(articlesListArticles);
            }
            em.remove(categories);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Categories> findCategoriesEntities() {
        return findCategoriesEntities(true, -1, -1);
    }

    public List<Categories> findCategoriesEntities(int maxResults, int firstResult) {
        return findCategoriesEntities(false, maxResults, firstResult);
    }

    private List<Categories> findCategoriesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Categories.class));
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

    public Categories findCategories(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Categories.class, id);
        } finally {
            em.close();
        }
    }

    public int getCategoriesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Categories> rt = cq.from(Categories.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    /**
     * Cette fonction permet de renvoyer l;es categories valides
     */
    public List<Categories> listValide(){
        
        return this.emf.createEntityManager().createNamedQuery("Categories.findByDeleted").setParameter("deleted", false).getResultList();
    }
    
}
