/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HOME
 */
@Entity
@Table(name = "OrderingDetail", catalog = "YellowMoonShop", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderingDetail.findAll", query = "SELECT o FROM OrderingDetail o")
    , @NamedQuery(name = "OrderingDetail.findByDetailID", query = "SELECT o FROM OrderingDetail o WHERE o.detailID = :detailID")
    , @NamedQuery(name = "OrderingDetail.findByPrice", query = "SELECT o FROM OrderingDetail o WHERE o.price = :price")
    , @NamedQuery(name = "OrderingDetail.findByQuantity", query = "SELECT o FROM OrderingDetail o WHERE o.quantity = :quantity")})
public class OrderingDetail implements Serializable {

    @Column(name = "lastUpdateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "detailID", nullable = false, length = 10)
    private String detailID;
    @Basic(optional = false)
    @Column(name = "price", nullable = false)
    private double price;
    @Basic(optional = false)
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @JoinColumn(name = "cakeID", referencedColumnName = "cakeID", nullable = false)
    @ManyToOne(optional = false)
    private Cake cakeID;
    @JoinColumn(name = "orderID", referencedColumnName = "orderID", nullable = false)
    @ManyToOne(optional = false)
    private Ordering orderID;

    public OrderingDetail() {
    }

    public OrderingDetail(String detailID) {
        this.detailID = detailID;
    }

    public OrderingDetail(String detailID, double price, int quantity) {
        this.detailID = detailID;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderingDetail(String detailID, double price, int quantity, Cake cakeID, Ordering orderID) {
        this.detailID = detailID;
        this.price = price;
        this.quantity = quantity;
        this.cakeID = cakeID;
        this.orderID = orderID;
    }

    public OrderingDetail(String detailID, double price, int quantity, Cake cakeID, Ordering orderID, Date lastUpdateDate) {      
        this.detailID = detailID;
        this.price = price;
        this.quantity = quantity;
        this.cakeID = cakeID;
        this.orderID = orderID;
        this.lastUpdateDate = lastUpdateDate;
    }
    
    
 
    public String getDetailID() {
        return detailID;
    }

    public void setDetailID(String detailID) {
        this.detailID = detailID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Cake getCakeID() {
        return cakeID;
    }

    public void setCakeID(Cake cakeID) {
        this.cakeID = cakeID;
    }

    public Ordering getOrderID() {
        return orderID;
    }

    public void setOrderID(Ordering orderID) {
        this.orderID = orderID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detailID != null ? detailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderingDetail)) {
            return false;
        }
        OrderingDetail other = (OrderingDetail) object;
        if ((this.detailID == null && other.detailID != null) || (this.detailID != null && !this.detailID.equals(other.detailID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trungnd.entity.OrderingDetail[ detailID=" + detailID + " ]";
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
    
}
