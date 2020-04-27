/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geststock.crud_methods;

import geststock.classes.Articlecommande;
import geststock.classes.Commande;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import geststock.classes.Facture;
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
public class CommandeJpaController implements Serializable {

    public CommandeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Commande commande) {
        if (commande.getArticlecommandeList() == null) {
            commande.setArticlecommandeList(new ArrayList<Articlecommande>());
        }
        if (commande.getFactureList() == null) {
            commande.setFactureList(new ArrayList<Facture>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Articlecommande> attachedArticlecommandeList = new ArrayList<Articlecommande>();
            for (Articlecommande articlecommandeListArticlecommandeToAttach : commande.getArticlecommandeList()) {
                articlecommandeListArticlecommandeToAttach = em.getReference(articlecommandeListArticlecommandeToAttach.getClass(), articlecommandeListArticlecommandeToAttach.getArticlecommandePK());
                attachedArticlecommandeList.add(articlecommandeListArticlecommandeToAttach);
            }
            commande.setArticlecommandeList(attachedArticlecommandeList);
            List<Facture> attachedFactureList = new ArrayList<Facture>();
            for (Facture factureListFactureToAttach : commande.getFactureList()) {
                factureListFactureToAttach = em.getReference(factureListFactureToAttach.getClass(), factureListFactureToAttach.getId());
                attachedFactureList.add(factureListFactureToAttach);
            }
            commande.setFactureList(attachedFactureList);
            em.persist(commande);
            for (Articlecommande articlecommandeListArticlecommande : commande.getArticlecommandeList()) {
                Commande oldCommandeOfArticlecommandeListArticlecommande = articlecommandeListArticlecommande.getCommande();
                articlecommandeListArticlecommande.setCommande(commande);
                articlecommandeListArticlecommande = em.merge(articlecommandeListArticlecommande);
                if (oldCommandeOfArticlecommandeListArticlecommande != null) {
                    oldCommandeOfArticlecommandeListArticlecommande.getArticlecommandeList().remove(articlecommandeListArticlecommande);
                    oldCommandeOfArticlecommandeListArticlecommande = em.merge(oldCommandeOfArticlecommandeListArticlecommande);
                }
            }
            for (Facture factureListFacture : commande.getFactureList()) {
                Commande oldIdcommandeOfFactureListFacture = factureListFacture.getIdcommande();
                factureListFacture.setIdcommande(commande);
                factureListFacture = em.merge(factureListFacture);
                if (oldIdcommandeOfFactureListFacture != null) {
                    oldIdcommandeOfFactureListFacture.getFactureList().remove(factureListFacture);
                    oldIdcommandeOfFactureListFacture = em.merge(oldIdcommandeOfFactureListFacture);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Commande commande) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Commande persistentCommande = em.find(Commande.class, commande.getId());
            List<Articlecommande> articlecommandeListOld = persistentCommande.getArticlecommandeList();
            List<Articlecommande> articlecommandeListNew = commande.getArticlecommandeList();
            List<Facture> factureListOld = persistentCommande.getFactureList();
            List<Facture> factureListNew = commande.getFactureList();
            List<String> illegalOrphanMessages = null;
            for (Articlecommande articlecommandeListOldArticlecommande : articlecommandeListOld) {
                if (!articlecommandeListNew.contains(articlecommandeListOldArticlecommande)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Articlecommande " + articlecommandeListOldArticlecommande + " since its commande field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Articlecommande> attachedArticlecommandeListNew = new ArrayList<Articlecommande>();
            for (Articlecommande articlecommandeListNewArticlecommandeToAttach : articlecommandeListNew) {
                articlecommandeListNewArticlecommandeToAttach = em.getReference(articlecommandeListNewArticlecommandeToAttach.getClass(), articlecommandeListNewArticlecommandeToAttach.getArticlecommandePK());
                attachedArticlecommandeListNew.add(articlecommandeListNewArticlecommandeToAttach);
            }
            articlecommandeListNew = attachedArticlecommandeListNew;
            commande.setArticlecommandeList(articlecommandeListNew);
            List<Facture> attachedFactureListNew = new ArrayList<Facture>();
            for (Facture factureListNewFactureToAttach : factureListNew) {
                factureListNewFactureToAttach = em.getReference(factureListNewFactureToAttach.getClass(), factureListNewFactureToAttach.getId());
                attachedFactureListNew.add(factureListNewFactureToAttach);
            }
            factureListNew = attachedFactureListNew;
            commande.setFactureList(factureListNew);
            commande = em.merge(commande);
            for (Articlecommande articlecommandeListNewArticlecommande : articlecommandeListNew) {
                if (!articlecommandeListOld.contains(articlecommandeListNewArticlecommande)) {
                    Commande oldCommandeOfArticlecommandeListNewArticlecommande = articlecommandeListNewArticlecommande.getCommande();
                    articlecommandeListNewArticlecommande.setCommande(commande);
                    articlecommandeListNewArticlecommande = em.merge(articlecommandeListNewArticlecommande);
                    if (oldCommandeOfArticlecommandeListNewArticlecommande != null && !oldCommandeOfArticlecommandeListNewArticlecommande.equals(commande)) {
                        oldCommandeOfArticlecommandeListNewArticlecommande.getArticlecommandeList().remove(articlecommandeListNewArticlecommande);
                        oldCommandeOfArticlecommandeListNewArticlecommande = em.merge(oldCommandeOfArticlecommandeListNewArticlecommande);
                    }
                }
            }
            for (Facture factureListOldFacture : factureListOld) {
                if (!factureListNew.contains(factureListOldFacture)) {
                    factureListOldFacture.setIdcommande(null);
                    factureListOldFacture = em.merge(factureListOldFacture);
                }
            }
            for (Facture factureListNewFacture : factureListNew) {
                if (!factureListOld.contains(factureListNewFacture)) {
                    Commande oldIdcommandeOfFactureListNewFacture = factureListNewFacture.getIdcommande();
                    factureListNewFacture.setIdcommande(commande);
                    factureListNewFacture = em.merge(factureListNewFacture);
                    if (oldIdcommandeOfFactureListNewFacture != null && !oldIdcommandeOfFactureListNewFacture.equals(commande)) {
                        oldIdcommandeOfFactureListNewFacture.getFactureList().remove(factureListNewFacture);
                        oldIdcommandeOfFactureListNewFacture = em.merge(oldIdcommandeOfFactureListNewFacture);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = commande.getId();
                if (findCommande(id) == null) {
                    throw new NonexistentEntityException("The commande with id " + id + " no longer exists.");
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
            Commande commande;
            try {
                commande = em.getReference(Commande.class, id);
                commande.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The commande with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Articlecommande> articlecommandeListOrphanCheck = commande.getArticlecommandeList();
            for (Articlecommande articlecommandeListOrphanCheckArticlecommande : articlecommandeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Commande (" + commande + ") cannot be destroyed since the Articlecommande " + articlecommandeListOrphanCheckArticlecommande + " in its articlecommandeList field has a non-nullable commande field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Facture> factureList = commande.getFactureList();
            for (Facture factureListFacture : factureList) {
                factureListFacture.setIdcommande(null);
                factureListFacture = em.merge(factureListFacture);
            }
            em.remove(commande);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Commande> findCommandeEntities() {
        return findCommandeEntities(true, -1, -1);
    }

    public List<Commande> findCommandeEntities(int maxResults, int firstResult) {
        return findCommandeEntities(false, maxResults, firstResult);
    }

    private List<Commande> findCommandeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Commande.class));
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

    public Commande findCommande(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Commande.class, id);
        } finally {
            em.close();
        }
    }

    public int getCommandeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Commande> rt = cq.from(Commande.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    
    
    public List<Commande> commandeValides(){
        
        return this.emf.createEntityManager().createNamedQuery("Commande.findByDeleted",Commande.class).setParameter("deleted", false).getResultList();
       
    }
    
    public List<Commande> commandeDeleted(){
        return this.emf.createEntityManager().createNamedQuery("Commande.findByDeleted",Commande.class).setParameter("deleted", true).getResultList();
       
    }
    
}
