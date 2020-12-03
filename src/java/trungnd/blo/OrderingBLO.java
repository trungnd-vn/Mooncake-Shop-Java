/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.blo;

import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import trungnd.entity.Ordering;

/**
 *
 * @author HOME
 */
public class OrderingBLO implements Serializable{

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

    
    public boolean addUpdateOrder(String orderID, double priceTotal, Date createDate, String name, String phoneNumber,
            String shipAddress, String paymentStatus, String orderStatus, String paymentMethod) {
        EntityManager em = emf.createEntityManager();
        System.out.println(orderID + "oooorrrrrdddeeerrrIIIIDDDDD");
        Ordering order = em.find(Ordering.class, orderID);
        if (order == null) {
            order = new Ordering(orderID, priceTotal, createDate, name, phoneNumber, shipAddress, paymentMethod, paymentStatus, orderStatus);
            em.getTransaction().begin();
            em.persist(order);
            em.getTransaction().commit();
            return true;
        } else {
            em.getTransaction().begin();
            order.setTotal(priceTotal);
            order.setCreatedDate(createDate);
            order.setRecipientName(name);
            order.setPhoneNumber(phoneNumber);
            order.setShippingAddress(shipAddress);
            order.setOrderStatus(orderStatus);
            order.setPaymentStatus(paymentStatus);
            order.setPaymentMethod(paymentMethod);
            em.merge(order);
            em.getTransaction().commit();
            return true;
        }
    }
}
