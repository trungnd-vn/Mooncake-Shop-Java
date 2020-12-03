/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HOME
 */
@Entity
@Table(name = "Ordering", catalog = "YellowMoonShop", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordering.findAll", query = "SELECT o FROM Ordering o")
    , @NamedQuery(name = "Ordering.findByOrderID", query = "SELECT o FROM Ordering o WHERE o.orderID = :orderID")
    , @NamedQuery(name = "Ordering.findByTotal", query = "SELECT o FROM Ordering o WHERE o.total = :total")
    , @NamedQuery(name = "Ordering.findByCreatedDate", query = "SELECT o FROM Ordering o WHERE o.createdDate = :createdDate")
    , @NamedQuery(name = "Ordering.findByRecipientName", query = "SELECT o FROM Ordering o WHERE o.recipientName = :recipientName")
    , @NamedQuery(name = "Ordering.findByPhoneNumber", query = "SELECT o FROM Ordering o WHERE o.phoneNumber = :phoneNumber")
    , @NamedQuery(name = "Ordering.findByShippingAddress", query = "SELECT o FROM Ordering o WHERE o.shippingAddress = :shippingAddress")
    , @NamedQuery(name = "Ordering.findByPaymentMethod", query = "SELECT o FROM Ordering o WHERE o.paymentMethod = :paymentMethod")
    , @NamedQuery(name = "Ordering.findByPaymentStatus", query = "SELECT o FROM Ordering o WHERE o.paymentStatus = :paymentStatus")
    , @NamedQuery(name = "Ordering.findByOrderStatus", query = "SELECT o FROM Ordering o WHERE o.orderStatus = :orderStatus")})
public class Ordering implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "orderID", nullable = false, length = 10)
    private String orderID;
    @Basic(optional = false)
    @Column(name = "total", nullable = false)
    private double total;
    @Basic(optional = false)
    @Column(name = "createdDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @Column(name = "recipientName", nullable = false, length = 100)
    private String recipientName;
    @Basic(optional = false)
    @Column(name = "phoneNumber", nullable = false, length = 20)
    private String phoneNumber;
    @Basic(optional = false)
    @Column(name = "shippingAddress", nullable = false, length = 500)
    private String shippingAddress;
    @Basic(optional = false)
    @Column(name = "paymentMethod", nullable = false, length = 100)
    private String paymentMethod;
    @Basic(optional = false)
    @Column(name = "paymentStatus", nullable = false, length = 10)
    private String paymentStatus;
    @Basic(optional = false)
    @Column(name = "orderStatus", nullable = false, length = 10)
    private String orderStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderID")
    private Collection<OrderingDetail> orderingDetailCollection;
    @JoinColumn(name = "email", referencedColumnName = "email")
    @ManyToOne
    private Account email;

    public Ordering() {
    }

    public Ordering(String orderID) {
        this.orderID = orderID;
    }

    public Ordering(String orderID, double total, Date createdDate, String recipientName, String phoneNumber, String shippingAddress, String paymentMethod, String paymentStatus, String orderStatus) {
        this.orderID = orderID;
        this.total = total;
        this.createdDate = createdDate;
        this.recipientName = recipientName;
        this.phoneNumber = phoneNumber;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.orderStatus = orderStatus;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @XmlTransient
    public Collection<OrderingDetail> getOrderingDetailCollection() {
        return orderingDetailCollection;
    }

    public void setOrderingDetailCollection(Collection<OrderingDetail> orderingDetailCollection) {
        this.orderingDetailCollection = orderingDetailCollection;
    }

    public Account getEmail() {
        return email;
    }

    public void setEmail(Account email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderID != null ? orderID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordering)) {
            return false;
        }
        Ordering other = (Ordering) object;
        if ((this.orderID == null && other.orderID != null) || (this.orderID != null && !this.orderID.equals(other.orderID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trungnd.entity.Ordering[ orderID=" + orderID + " ]";
    }
    
}
