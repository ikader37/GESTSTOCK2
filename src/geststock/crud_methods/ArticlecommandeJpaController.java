/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geststock.crud_methods;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import geststock.classes.*;
import geststock.crud_methods.exceptions.NonexistentEntityException;
import geststock.crud_methods.exceptions.PreexistingEntityException;

/**
 *
 * @author ilboudo
 */
public class ArticlecommandeJpaController implements Serializable {

    public ArticlecommandeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Articlecommande articlecommande) throws PreexistingEntityException, Exception {
        if (articlecommande.getArticlecommandePK() == null) {
            articlecommande.setArticlecommandePK(new ArticlecommandePK());
        }
        articlecommande.getArticlecommandePK().setIdcommande(articlecommande.getCommande().getId());
        articlecommande.getArticlecommandePK().setIdarticle(articlecommande.getArticles().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Commande commande = articlecommande.getCommande();
            if (commande != null) {
                commande = em.getReference(commande.getClass(), commande.getId());
                articlecommande.setCommande(commande);
            }
            Articles articles = articlecommande.getArticles();
            if (articles != null) {
                articles = em.getReference(articles.getClass(), articles.getId());
                articlecommande.setArticles(articles);
            }
            em.persist(articlecommande);
            if (commande != null) {
                commande.getArticlecommandeList().add(articlecommande);
                commande = em.merge(commande);
            }
            if (articles != null) {
                articles.getArticlecommandeList().add(articlecommande);
                articles = em.merge(articles);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findArticlecommande(articlecommande.getArticlecommandePK()) != null) {
                throw new PreexistingEntityException("Articlecommande " + articlecommande + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Articlecommande articlecommande) throws NonexistentEntityException, Exception {
        articlecommande.getArticlecommandePK().setIdcommande(articlecommande.getCommande().getId());
        articlecommande.getArticlecommandePK().setIdarticle(articlecommande.getArticles().getId());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articlecommande persistentArticlecommande = em.find(Articlecommande.class, articlecommande.getArticlecommandePK());
            Commande commandeOld = persistentArticlecommande.getCommande();
            Commande commandeNew = articlecommande.getCommande();
            Articles articlesOld = persistentArticlecommande.getArticles();
            Articles articlesNew = articlecommande.getArticles();
            if (commandeNew != null) {
                commandeNew = em.getReference(commandeNew.getClass(), commandeNew.getId());
                articlecommande.setCommande(commandeNew);
            }
            if (articlesNew != null) {
                articlesNew = em.getReference(articlesNew.getClass(), articlesNew.getId());
                articlecommande.setArticles(articlesNew);
            }
            articlecommande = em.merge(articlecommande);
            if (commandeOld != null && !commandeOld.equals(commandeNew)) {
                commandeOld.getArticlecommandeList().remove(articlecommande);
                commandeOld = em.merge(commandeOld);
            }
            if (commandeNew != null && !commandeNew.equals(commandeOld)) {
                commandeNew.getArticlecommandeList().add(articlecommande);
                commandeNew = em.merge(commandeNew);
            }
            if (articlesOld != null && !articlesOld.equals(articlesNew)) {
                articlesOld.getArticlecommandeList().remove(articlecommande);
                articlesOld = em.merge(articlesOld);
            }
            if (articlesNew != null && !articlesNew.equals(articlesOld)) {
                articlesNew.getArticlecommandeList().add(articlecommande);
                articlesNew = em.merge(articlesNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ArticlecommandePK id = articlecommande.getArticlecommandePK();
                if (findArticlecommande(id) == null) {
                    throw new NonexistentEntityException("The articlecommande with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ArticlecommandePK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articlecommande articlecommande;
            try {
                articlecommande = em.getReference(Articlecommande.class, id);
                articlecommande.getArticlecommandePK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The articlecommande with id " + id + " no longer exists.", enfe);
            }
            Commande commande = articlecommande.getCommande();
            if (commande != null) {
                commande.getArticlecommandeList().remove(articlecommande);
                commande = em.merge(commande);
            }
            Articles articles = articlecommande.getArticles();
            if (articles != null) {
                articles.getArticlecommandeList().remove(articlecommande);
                articles = em.merge(articles);
            }
            em.remove(articlecommande);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Articlecommande> findArticlecommandeEntities() {
        return findArticlecommandeEntities(true, -1, -1);
    }

    public List<Articlecommande> findArticlecommandeEntities(int maxResults, int firstResult) {
        return findArticlecommandeEntities(false, maxResults, firstResult);
    }

    private List<Articlecommande> findArticlecommandeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Articlecommande.class));
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

    public Articlecommande findArticlecommande(ArticlecommandePK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Articlecommande.class, id);
        } finally {
            em.close();
        }
    }

    public int getArticlecommandeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Articlecommande> rt = cq.from(Articlecommande.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
