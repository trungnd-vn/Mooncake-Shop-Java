/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.blo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import trungnd.entity.Cake;
import trungnd.entity.Ordering;
import trungnd.entity.OrderingDetail;

/**
 *
 * @author HOME
 */
public class OrderDetailsBLO implements Serializable {

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
    
    public boolean addUpdateOrderDetail(String orderDetailID, double price, int quantity, Cake cakeID, Ordering orderID, Date lastUpdateDate) {
        EntityManager em = emf.createEntityManager();
        
        OrderingDetail orderDetail = em.find(OrderingDetail.class, cakeID.getCakeID());
        orderDetail = em.find(OrderingDetail.class, orderDetailID);
        if (orderDetail == null) {
            orderDetail = new OrderingDetail(orderDetailID, price, quantity, cakeID, orderID, lastUpdateDate);
            em.getTransaction().begin();
            em.persist(orderDetail);
            em.getTransaction().commit();
            return true;
        } else {
            em.getTransaction().begin();
            orderDetail.setDetailID(orderDetailID);
            orderDetail.setPrice(price);
            orderDetail.setQuantity(quantity);
            orderDetail.setCakeID(cakeID);
            orderDetail.setOrderID(orderID);         
            em.merge(orderDetail);
            em.getTransaction().commit();
            return true;
        }
    }
    
    public void removeOrderDetail(Cake cakeID, Ordering orderID) {
        EntityManager em = emf.createEntityManager();
        String selectQuery = "Delete from OrderingDetail o Where o.cakeID = :cakeID And o.orderID = :orderID";
        Query query = em.createQuery(selectQuery);
        query.setParameter("cakeID", cakeID);
        query.setParameter("orderID", orderID);
        em.getTransaction().begin();
        query.executeUpdate();
        em.getTransaction().commit();
//        List<OrderingDetail> listToRemove = query.getResultList();
//        System.out.println(listToRemove);
//        for (int i = 0; i < listToRemove.size(); i++) {
//            em.remove(i);
//        }
    }
    
    
}
