/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.blo;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import trungnd.entity.Account;

/**
 *
 * @author HOME
 */
public class AccountBLO implements Serializable{

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
    
    public boolean checkLogin(String email, String password) {
        EntityManager em = emf.createEntityManager();
        
        String jpql = "Select a from Account a Where a.email = :email And a.password = :password";
        
        Query query = em.createQuery(jpql);
        query.setParameter("email", email);
        query.setParameter("password", password);
        
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }
    
    public Account loginPage(String email) {
        Account acc = null;
        EntityManager em = emf.createEntityManager();
        
        Query queryLogin = em.createNamedQuery("Account.findByEmail");
        queryLogin.setParameter("email", email);
        
        try {
            acc = (Account) queryLogin.getSingleResult();
            return acc;
        } catch (NoResultException e) {
            return null;
        }
    }
    
    public boolean createAccount 
        (String accID, String password, String name, String picture, String address, String roleName, String status) {
        EntityManager em = emf.createEntityManager();
        
        Account acc = em.find(Account.class, accID);
        if (acc == null) {
            acc = new Account(accID, password, name, picture, address, roleName, status);
            
            em.getTransaction().begin();
            em.persist(acc);
            em.getTransaction().commit();
            
            return true;
        }       
        return false;
    }

    public Account findAccountAdmin(String accAdmin) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Account.findByEmail";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("email", accAdmin);
        try {
            Account accAdminUpdate = (Account) query.getSingleResult();
//            System.out.println(accAdminUpdate);
            return accAdminUpdate;
        } catch (NoResultException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
