/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geststock.crud_methods;

import geststock.classes.Articlecommande;
import geststock.classes.Articles;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import geststock.classes.Categories;
import geststock.classes.Fournisseurs;
import geststock.classes.Rangement;
import geststock.classes.Vendres;
import geststock.crud_methods.exceptions.IllegalOrphanException;
import geststock.crud_methods.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author DELL
 */
public class ArticlesJpaController implements Serializable {

    public ArticlesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Articles articles) {
        if (articles.getVendresList() == null) {
            articles.setVendresList(new ArrayList<Vendres>());
        }
        if (articles.getArticlecommandeList() == null) {
            articles.setArticlecommandeList(new ArrayList<Articlecommande>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categories categorieId = articles.getCategorieId();
            if (categorieId != null) {
                categorieId = em.getReference(categorieId.getClass(), categorieId.getId());
                articles.setCategorieId(categorieId);
            }
            Fournisseurs fournisseurId = articles.getFournisseurId();
            if (fournisseurId != null) {
                fournisseurId = em.getReference(fournisseurId.getClass(), fournisseurId.getId());
                articles.setFournisseurId(fournisseurId);
            }
            Rangement idrangemennt = articles.getIdrangemennt();
            if (idrangemennt != null) {
                idrangemennt = em.getReference(idrangemennt.getClass(), idrangemennt.getId());
                articles.setIdrangemennt(idrangemennt);
            }
            List<Vendres> attachedVendresList = new ArrayList<Vendres>();
            for (Vendres vendresListVendresToAttach : articles.getVendresList()) {
                vendresListVendresToAttach = em.getReference(vendresListVendresToAttach.getClass(), vendresListVendresToAttach.getId());
                attachedVendresList.add(vendresListVendresToAttach);
            }
            articles.setVendresList(attachedVendresList);
            List<Articlecommande> attachedArticlecommandeList = new ArrayList<Articlecommande>();
            for (Articlecommande articlecommandeListArticlecommandeToAttach : articles.getArticlecommandeList()) {
                articlecommandeListArticlecommandeToAttach = em.getReference(articlecommandeListArticlecommandeToAttach.getClass(), articlecommandeListArticlecommandeToAttach.getArticlecommandePK());
                attachedArticlecommandeList.add(articlecommandeListArticlecommandeToAttach);
            }
            articles.setArticlecommandeList(attachedArticlecommandeList);
            em.persist(articles);
            if (categorieId != null) {
                categorieId.getArticlesList().add(articles);
                categorieId = em.merge(categorieId);
            }
            if (fournisseurId != null) {
                fournisseurId.getArticlesList().add(articles);
                fournisseurId = em.merge(fournisseurId);
            }
            if (idrangemennt != null) {
                idrangemennt.getArticlesList().add(articles);
                idrangemennt = em.merge(idrangemennt);
            }
            for (Vendres vendresListVendres : articles.getVendresList()) {
                Articles oldArticleIdOfVendresListVendres = vendresListVendres.getArticleId();
                vendresListVendres.setArticleId(articles);
                vendresListVendres = em.merge(vendresListVendres);
                if (oldArticleIdOfVendresListVendres != null) {
                    oldArticleIdOfVendresListVendres.getVendresList().remove(vendresListVendres);
                    oldArticleIdOfVendresListVendres = em.merge(oldArticleIdOfVendresListVendres);
                }
            }
            for (Articlecommande articlecommandeListArticlecommande : articles.getArticlecommandeList()) {
                Articles oldArticlesOfArticlecommandeListArticlecommande = articlecommandeListArticlecommande.getArticles();
                articlecommandeListArticlecommande.setArticles(articles);
                articlecommandeListArticlecommande = em.merge(articlecommandeListArticlecommande);
                if (oldArticlesOfArticlecommandeListArticlecommande != null) {
                    oldArticlesOfArticlecommandeListArticlecommande.getArticlecommandeList().remove(articlecommandeListArticlecommande);
                    oldArticlesOfArticlecommandeListArticlecommande = em.merge(oldArticlesOfArticlecommandeListArticlecommande);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Articles articles) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Articles persistentArticles = em.find(Articles.class, articles.getId());
            Categories categorieIdOld = persistentArticles.getCategorieId();
            Categories categorieIdNew = articles.getCategorieId();
            Fournisseurs fournisseurIdOld = persistentArticles.getFournisseurId();
            Fournisseurs fournisseurIdNew = articles.getFournisseurId();
            Rangement idrangemenntOld = persistentArticles.getIdrangemennt();
            Rangement idrangemenntNew = articles.getIdrangemennt();
            List<Vendres> vendresListOld = persistentArticles.getVendresList();
            List<Vendres> vendresListNew = articles.getVendresList();
            List<Articlecommande> articlecommandeListOld = persistentArticles.getArticlecommandeList();
            List<Articlecommande> articlecommandeListNew = articles.getArticlecommandeList();
            List<String> illegalOrphanMessages = null;
            for (Articlecommande articlecommandeListOldArticlecommande : articlecommandeListOld) {
                if (!articlecommandeListNew.contains(articlecommandeListOldArticlecommande)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Articlecommande " + articlecommandeListOldArticlecommande + " since its articles field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (categorieIdNew != null) {
                categorieIdNew = em.getReference(categorieIdNew.getClass(), categorieIdNew.getId());
                articles.setCategorieId(categorieIdNew);
            }
            if (fournisseurIdNew != null) {
                fournisseurIdNew = em.getReference(fournisseurIdNew.getClass(), fournisseurIdNew.getId());
                articles.setFournisseurId(fournisseurIdNew);
            }
            if (idrangemenntNew != null) {
                idrangemenntNew = em.getReference(idrangemenntNew.getClass(), idrangemenntNew.getId());
                articles.setIdrangemennt(idrangemenntNew);
            }
            List<Vendres> attachedVendresListNew = new ArrayList<Vendres>();
            for (Vendres vendresListNewVendresToAttach : vendresListNew) {
                vendresListNewVendresToAttach = em.getReference(vendresListNewVendresToAttach.getClass(), vendresListNewVendresToAttach.getId());
                attachedVendresListNew.add(vendresListNewVendresToAttach);
            }
            vendresListNew = attachedVendresListNew;
            articles.setVendresList(vendresListNew);
            List<Articlecommande> attachedArticlecommandeListNew = new ArrayList<Articlecommande>();
            for (Articlecommande articlecommandeListNewArticlecommandeToAttach : articlecommandeListNew) {
                articlecommandeListNewArticlecommandeToAttach = em.getReference(articlecommandeListNewArticlecommandeToAttach.getClass(), articlecommandeListNewArticlecommandeToAttach.getArticlecommandePK());
                attachedArticlecommandeListNew.add(articlecommandeListNewArticlecommandeToAttach);
            }
            articlecommandeListNew = attachedArticlecommandeListNew;
            articles.setArticlecommandeList(articlecommandeListNew);
            articles = em.merge(articles);
            if (categorieIdOld != null && !categorieIdOld.equals(categorieIdNew)) {
                categorieIdOld.getArticlesList().remove(articles);
                categorieIdOld = em.merge(categorieIdOld);
            }
            if (categorieIdNew != null && !categorieIdNew.equals(categorieIdOld)) {
                categorieIdNew.getArticlesList().add(articles);
                categorieIdNew = em.merge(categorieIdNew);
            }
            if (fournisseurIdOld != null && !fournisseurIdOld.equals(fournisseurIdNew)) {
                fournisseurIdOld.getArticlesList().remove(articles);
                fournisseurIdOld = em.merge(fournisseurIdOld);
            }
            if (fournisseurIdNew != null && !fournisseurIdNew.equals(fournisseurIdOld)) {
                fournisseurIdNew.getArticlesList().add(articles);
                fournisseurIdNew = em.merge(fournisseurIdNew);
            }
            if (idrangemenntOld != null && !idrangemenntOld.equals(idrangemenntNew)) {
                idrangemenntOld.getArticlesList().remove(articles);
                idrangemenntOld = em.merge(idrangemenntOld);
            }
            if (idrangemenntNew != null && !idrangemenntNew.equals(idrangemenntOld)) {
                idrangemenntNew.getArticlesList().add(articles);
                idrangemenntNew = em.merge(idrangemenntNew);
            }
            for (Vendres vendresListOldVendres : vendresListOld) {
                if (!vendresListNew.contains(vendresListOldVendres)) {
                    vendresListOldVendres.setArticleId(null);
                    vendresListOldVendres = em.merge(vendresListOldVendres);
                }
            }
            for (Vendres vendresListNewVendres : vendresListNew) {
                if (!vendresListOld.contains(vendresListNewVendres)) {
                    Articles oldArticleIdOfVendresListNewVendres = vendresListNewVendres.getArticleId();
                    vendresListNewVendres.setArticleId(articles);
                    vendresListNewVendres = em.merge(vendresListNewVendres);
                    if (oldArticleIdOfVendresListNewVendres != null && !oldArticleIdOfVendresListNewVendres.equals(articles)) {
                        oldArticleIdOfVendresListNewVendres.getVendresList().remove(vendresListNewVendres);
                        oldArticleIdOfVendresListNewVendres = em.merge(oldArticleIdOfVendresListNewVendres);
                    }
                }
            }
            for (Articlecommande articlecommandeListNewArticlecommande : articlecommandeListNew) {
                if (!articlecommandeListOld.contains(articlecommandeListNewArticlecommande)) {
                    Articles oldArticlesOfArticlecommandeListNewArticlecommande = articlecommandeListNewArticlecommande.getArticles();
                    articlecommandeListNewArticlecommande.setArticles(articles);
                    articlecommandeListNewArticlecommande = em.merge(articlecommandeListNewArticlecommande);
                    if (oldArticlesOfArticlecommandeListNewArticlecommande != null && !oldArticlesOfArticlecommandeListNewArticlecommande.equals(articles)) {
                        oldArticlesOfArticlecommandeListNewArticlecommande.getArticlecommandeList().remove(articlecommandeListNewArticlecommande);
                        oldArticlesOfArticlecommandeListNewArticlecommande = em.merge(oldArticlesOfArticlecommandeListNewArticlecommande);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = articles.getId();
                if (findArticles(id) == null) {
                    throw new NonexistentEntityException("The articles with id " + id + " no longer exists.");
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
            Articles articles;
            try {
                articles = em.getReference(Articles.class, id);
                articles.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The articles with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Articlecommande> articlecommandeListOrphanCheck = articles.getArticlecommandeList();
            for (Articlecommande articlecommandeListOrphanCheckArticlecommande : articlecommandeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Articles (" + articles + ") cannot be destroyed since the Articlecommande " + articlecommandeListOrphanCheckArticlecommande + " in its articlecommandeList field has a non-nullable articles field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Categories categorieId = articles.getCategorieId();
            if (categorieId != null) {
                categorieId.getArticlesList().remove(articles);
                categorieId = em.merge(categorieId);
            }
            Fournisseurs fournisseurId = articles.getFournisseurId();
            if (fournisseurId != null) {
                fournisseurId.getArticlesList().remove(articles);
                fournisseurId = em.merge(fournisseurId);
            }
            Rangement idrangemennt = articles.getIdrangemennt();
            if (idrangemennt != null) {
                idrangemennt.getArticlesList().remove(articles);
                idrangemennt = em.merge(idrangemennt);
            }
            List<Vendres> vendresList = articles.getVendresList();
            for (Vendres vendresListVendres : vendresList) {
                vendresListVendres.setArticleId(null);
                vendresListVendres = em.merge(vendresListVendres);
            }
            em.remove(articles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Articles> findArticlesEntities() {
        return findArticlesEntities(true, -1, -1);
    }

    public List<Articles> findArticlesEntities(int maxResults, int firstResult) {
        return findArticlesEntities(false, maxResults, firstResult);
    }

    private List<Articles> findArticlesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Articles.class));
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

    public Articles findArticles(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Articles.class, id);
        } finally {
            em.close();
        }
    }

    public int getArticlesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Articles> rt = cq.from(Articles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Articles> listArticleValides(){
        
        return this.emf.createEntityManager().createNamedQuery("Articles.findByDeleted").setParameter("deleted", false).getResultList();
    }
    
}
