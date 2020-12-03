/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.blo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import trungnd.entity.Account;
import trungnd.entity.Cake;
import trungnd.entity.Category;

/**
 *
 * @author HOME
 */
public class CakeBLO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("J3.L.P0011YellowMoonShopJPAPU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public List searchByLikeInput(String searchValue, int currentPage, int pageMaxSize) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Select a From Cake a Where a.name like :name and a.cakeStatus = 'Active'"
                + " and a.quantity > 0 Order By a.lastUpdatedDate DESC";
        Query query = em.createQuery(jpql);
        query.setParameter("name", "%" + searchValue + "%");
        query.setFirstResult((currentPage - 1) * pageMaxSize);
        query.setMaxResults(pageMaxSize);
        List resultList = query.getResultList();
        return resultList;
    }

    public int getAmountFindByLikeInput(String searchValue, int pageMaxSize) {
        EntityManager em = emf.createEntityManager();
        String sql = "select COUNT(cakeID) from Cake where cakeStatus='Active' and name like ? and quantity > 0 Order By lastUpdatedDate DESC";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter("1", "%" + searchValue + "%");
        int count = (int) query.getSingleResult();
        System.out.println(count);
        em.getTransaction().commit();
        if (count % pageMaxSize == 0) {
            return count / pageMaxSize;
        }
        return count / pageMaxSize + 1;
    }

    public List searchByLikeInput_Category(Category searchValue, int currentPage, int pageMaxSize) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Select a From Cake a Where a.categoryID = :categoryID and a.cakeStatus = 'Active'"
                + " and a.quantity > 0 Order By a.lastUpdatedDate DESC";
        Query query = em.createQuery(jpql);
        query.setParameter("categoryID", searchValue);
        query.setFirstResult((currentPage - 1) * pageMaxSize);
        query.setMaxResults(pageMaxSize);
        List resultList = query.getResultList();
        return resultList;
    }

    public long getAmountFindByLikeInput_Category(Category searchValue, int pageMaxSize) {
        EntityManager em = emf.createEntityManager();
        String jpql = "SELECT COUNT(c.cakeID) FROM Cake c WHERE c.categoryID = :categoryID AND c.cakeStatus = 'Active' AND c.quantity > 0";
        Query query = em.createQuery(jpql);
//        System.out.println(searchValue + " jsadfbsdgfsdiufgu");
        query.setParameter("categoryID", searchValue);
        int count = (int) (long) query.getSingleResult();
        System.out.println(count);
        if (count % pageMaxSize == 0) {
            return count / pageMaxSize;
        }
        return count / pageMaxSize + 1;
    }

    public List searchByLikeInput_Price(double minPrice, double maxPrice, int currentPage, int pageMaxSize) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Select a From Cake a Where a.price BETWEEN :minPrice AND :maxPrice and a.cakeStatus = 'Active'"
                + " and a.quantity > 0 Order By a.lastUpdatedDate DESC";
        Query query = em.createQuery(jpql);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        query.setFirstResult((currentPage - 1) * pageMaxSize);
        query.setMaxResults(pageMaxSize);
        List resultList = query.getResultList();
        return resultList;
    }

    public long getAmountFindByLikeInput_Price(double minPrice, double maxPrice, int pageMaxSize) {
        EntityManager em = emf.createEntityManager();
        String sql = "Select COUNT(a.cakeID) From Cake a Where a.price BETWEEN :minPrice AND :maxPrice and a.cakeStatus = 'Active'"
                + " and a.quantity > 0";
        Query query = em.createQuery(sql);
        query.setParameter("minPrice", minPrice);
        query.setParameter("maxPrice", maxPrice);
        int count = (int) (long) query.getSingleResult();
        System.out.println(count);
        if (count % pageMaxSize == 0) {
            return count / pageMaxSize;
        }
        return count / pageMaxSize + 1;
    }

    public boolean addCake(String cakeID, String cakeName, String cakeImage, String cakeDescription, double cakePrice,
            int quantity, Date createdDate, Date expDate, Date updateDate, String status, Category categoryID, Account accAdminUpdate) {
        EntityManager em = emf.createEntityManager();

        Cake cake = em.find(Cake.class, cakeID);
        if (cake == null) {
            cake = new Cake(cakeID, cakeName, cakeDescription, cakeImage, cakePrice, quantity, createdDate, expDate, updateDate, status, categoryID, accAdminUpdate);
            em.getTransaction().begin();
            em.persist(cake);
            em.getTransaction().commit();

            return true;
        }
        return false;
    }

    public boolean updateCake(String cakeID, String cakeName, String cakeImage, String cakeDescription, double cakePrice,
            int quantity, Date createdDate, Date expDate, Date updateDate, String status, Category categoryID, Account accAdminUpdate) {
        EntityManager em = emf.createEntityManager();
        Cake cake = em.find(Cake.class, cakeID);
        if (cake != null) {
//            cake.setCakeID(cakeID);
            cake.setName(cakeName);
            cake.setCakeDescription(cakeDescription);
            cake.setCakeImage(cakeImage);
            cake.setPrice(cakePrice);
            cake.setQuantity(quantity);
            cake.setCreatedDate(createdDate);
            cake.setExpDate(expDate);
            cake.setLastUpdatedDate(updateDate);
            cake.setCakeStatus(status);
            cake.setCategoryID(categoryID);
            cake.setAccAdminUpdate(accAdminUpdate);

            em.getTransaction().begin();
            em.merge(cake);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }

    public Cake findCake(String searchID) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Cake.findByCakeID";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("cakeID", searchID);
        try {
            Cake cake = (Cake) query.getSingleResult();
//            System.out.println(cake);
            return cake;
        } catch (NoResultException e) {
//            e.printStackTrace();
        }
        return null;
    }
 
}
