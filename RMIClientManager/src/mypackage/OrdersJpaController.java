/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mypackage;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import mypackage.exceptions.NonexistentEntityException;

/**
 *
 * @author Nikolaos
 */
public class OrdersJpaController implements Serializable {

    public OrdersJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Orders orders) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Client client = orders.getClient();
            if (client != null) {
                client = em.getReference(client.getClass(), client.getId());
                orders.setClient(client);
            }
            Product product = orders.getProduct();
            if (product != null) {
                product = em.getReference(product.getClass(), product.getId());
                orders.setProduct(product);
            }
            em.persist(orders);
            if (client != null) {
                client.getOrdersCollection().add(orders);
                client = em.merge(client);
            }
            if (product != null) {
                product.getOrdersCollection().add(orders);
                product = em.merge(product);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Orders orders) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Orders persistentOrders = em.find(Orders.class, orders.getId());
            Client clientOld = persistentOrders.getClient();
            Client clientNew = orders.getClient();
            Product productOld = persistentOrders.getProduct();
            Product productNew = orders.getProduct();
            if (clientNew != null) {
                clientNew = em.getReference(clientNew.getClass(), clientNew.getId());
                orders.setClient(clientNew);
            }
            if (productNew != null) {
                productNew = em.getReference(productNew.getClass(), productNew.getId());
                orders.setProduct(productNew);
            }
            orders = em.merge(orders);
            if (clientOld != null && !clientOld.equals(clientNew)) {
                clientOld.getOrdersCollection().remove(orders);
                clientOld = em.merge(clientOld);
            }
            if (clientNew != null && !clientNew.equals(clientOld)) {
                clientNew.getOrdersCollection().add(orders);
                clientNew = em.merge(clientNew);
            }
            if (productOld != null && !productOld.equals(productNew)) {
                productOld.getOrdersCollection().remove(orders);
                productOld = em.merge(productOld);
            }
            if (productNew != null && !productNew.equals(productOld)) {
                productNew.getOrdersCollection().add(orders);
                productNew = em.merge(productNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = orders.getId();
                if (findOrders(id) == null) {
                    throw new NonexistentEntityException("The orders with id " + id + " no longer exists.");
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
            Orders orders;
            try {
                orders = em.getReference(Orders.class, id);
                orders.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The orders with id " + id + " no longer exists.", enfe);
            }
            Client client = orders.getClient();
            if (client != null) {
                client.getOrdersCollection().remove(orders);
                client = em.merge(client);
            }
            Product product = orders.getProduct();
            if (product != null) {
                product.getOrdersCollection().remove(orders);
                product = em.merge(product);
            }
            em.remove(orders);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Orders> findOrdersEntities() {
        return findOrdersEntities(true, -1, -1);
    }

    public List<Orders> findOrdersEntities(int maxResults, int firstResult) {
        return findOrdersEntities(false, maxResults, firstResult);
    }

    private List<Orders> findOrdersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Orders.class));
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

    public Orders findOrders(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Orders.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Orders> rt = cq.from(Orders.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    /* New Functions */
    public List<Orders> findByClient(Client client) {
        EntityManager em = getEntityManager();
        try {
            Query q1 = em.createQuery("SELECT o FROM Orders o WHERE o.client = :client");
            q1.setParameter("client", client);
            return q1.getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Orders> findByProduct(Product product) {
        EntityManager em = getEntityManager();
        try {
            Query q1 = em.createQuery("SELECT o FROM Orders o WHERE o.product = :product");
            q1.setParameter("product", product);
            return q1.getResultList();
        } finally {
            em.close();
        }
    }
    
}
