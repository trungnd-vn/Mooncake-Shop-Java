/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.blo;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import trungnd.entity.Category;

/**
 *
 * @author HOME
 */
public class CategoryBLO implements Serializable {

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
    
    public List<Category> showCategoryLeftSide() {
        EntityManager em = emf.createEntityManager();
        String jpql = "Category.findAll";
        Query query = em.createNamedQuery(jpql);
        List<Category> resultList = query.getResultList();
        return resultList;
    }
    
    public Category findCategory(String searchID) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Category.findByCategoryID";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("categoryID", searchID);
        try {
            Category category = (Category) query.getSingleResult();
            System.out.println(category);
            return category;
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public boolean addCake(String categoryID, String categoryName) {
        EntityManager em = emf.createEntityManager();
        
        Category cate = em.find(Category.class, categoryName);
        if (cate == null) {
            cate = new Category(categoryID, categoryName);
            em.getTransaction().begin();
            em.persist(cate);
            em.getTransaction().commit();          
            return true;
        }
        return false;
    }
}
