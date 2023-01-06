/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database.databaseA;

import database.databaseA.exceptions.NonexistentEntityException;
import database.databaseA.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ACER
 */
public class A2020JpaController implements Serializable {

    public A2020JpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("database_databaseA_jar_0.0.1-SNAPSHOTPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public A2020JpaController(){}

    public void create(A2020 a2020) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(a2020);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findA2020(a2020.getId()) != null) {
                throw new PreexistingEntityException("A2020 " + a2020 + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(A2020 a2020) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            a2020 = em.merge(a2020);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = a2020.getId();
                if (findA2020(id) == null) {
                    throw new NonexistentEntityException("The a2020 with id " + id + " no longer exists.");
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
            A2020 a2020;
            try {
                a2020 = em.getReference(A2020.class, id);
                a2020.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The a2020 with id " + id + " no longer exists.", enfe);
            }
            em.remove(a2020);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<A2020> findA2020Entities() {
        return findA2020Entities(true, -1, -1);
    }

    public List<A2020> findA2020Entities(int maxResults, int firstResult) {
        return findA2020Entities(false, maxResults, firstResult);
    }

    private List<A2020> findA2020Entities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(A2020.class));
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

    public A2020 findA2020(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(A2020.class, id);
        } finally {
            em.close();
        }
    }

    public int getA2020Count() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<A2020> rt = cq.from(A2020.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
