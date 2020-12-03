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
@Table(name = "Cake", catalog = "YellowMoonShop", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cake.findAll", query = "SELECT c FROM Cake c")
    , @NamedQuery(name = "Cake.findByCakeID", query = "SELECT c FROM Cake c WHERE c.cakeID = :cakeID")
    , @NamedQuery(name = "Cake.findByName", query = "SELECT c FROM Cake c WHERE c.name = :name")
    , @NamedQuery(name = "Cake.findByCakeDescription", query = "SELECT c FROM Cake c WHERE c.cakeDescription = :cakeDescription")
    , @NamedQuery(name = "Cake.findByPrice", query = "SELECT c FROM Cake c WHERE c.price = :price")
    , @NamedQuery(name = "Cake.findByQuantity", query = "SELECT c FROM Cake c WHERE c.quantity = :quantity")
    , @NamedQuery(name = "Cake.findByCreatedDate", query = "SELECT c FROM Cake c WHERE c.createdDate = :createdDate")
    , @NamedQuery(name = "Cake.findByExpDate", query = "SELECT c FROM Cake c WHERE c.expDate = :expDate")
    , @NamedQuery(name = "Cake.findByLastUpdatedDate", query = "SELECT c FROM Cake c WHERE c.lastUpdatedDate = :lastUpdatedDate")
    , @NamedQuery(name = "Cake.findByCakeStatus", query = "SELECT c FROM Cake c WHERE c.cakeStatus = :cakeStatus")
    , @NamedQuery(name = "Cake.findByCake_Category", query = "SELECT COUNT(c.cakeID) FROM Cake c WHERE c.categoryID = :categoryID AND c.cakeStatus = 'Active' AND c.quantity > 0 ORDER BY c.lastUpdatedDate DESC")})
public class Cake implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cakeID", nullable = false, length = 10)
    private String cakeID;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "cakeDescription", length = 1000)
    private String cakeDescription;
    @Column(name = "cakeImage", length = 1000)
    private String cakeImage;
    @Basic(optional = false)
    @Column(name = "price", nullable = false)
    private double price;
    @Basic(optional = false)
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Basic(optional = false)
    @Column(name = "createdDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Basic(optional = false)
    @Column(name = "expDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expDate;
    @Basic(optional = false)
    @Column(name = "lastUpdatedDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Basic(optional = false)
    @Column(name = "cakeStatus", nullable = false, length = 10)
    private String cakeStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cakeID")
    private Collection<OrderingDetail> orderingDetailCollection;

    @JoinColumn(name = "categoryID", referencedColumnName = "categoryID", nullable = false)
    @ManyToOne(optional = false)
    private Category categoryID;
    @JoinColumn(name = "accAdminUpdate", referencedColumnName = "email", nullable = false)
    @ManyToOne(optional = false)
    private Account accAdminUpdate;


    public Cake() {
    }

    public Cake(String cakeID) {
        this.cakeID = cakeID;
    }

    public Cake(String cakeID, String name, double price, int quantity, Date createdDate, Date expDate, Date lastUpdatedDate, String cakeStatus) {
        this.cakeID = cakeID;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.createdDate = createdDate;
        this.expDate = expDate;
        this.lastUpdatedDate = lastUpdatedDate;
        this.cakeStatus = cakeStatus;
    }

    public Cake(String cakeID, String name, String cakeDescription, String cakeImage, double price, int quantity, Date createdDate, Date expDate, Date lastUpdatedDate, String cakeStatus, Category categoryID, Account accAdminUpdate) {
        this.cakeID = cakeID;
        this.name = name;
        this.cakeDescription = cakeDescription;
        this.cakeImage = cakeImage;
        this.price = price;
        this.quantity = quantity;
        this.createdDate = createdDate;
        this.expDate = expDate;
        this.lastUpdatedDate = lastUpdatedDate;
        this.cakeStatus = cakeStatus;
        this.categoryID = categoryID;
        this.accAdminUpdate = accAdminUpdate;
    }

    

    

    public String getCakeID() {
        return cakeID;
    }

    public void setCakeID(String cakeID) {
        this.cakeID = cakeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCakeDescription() {
        return cakeDescription;
    }

    public void setCakeDescription(String cakeDescription) {
        this.cakeDescription = cakeDescription;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCakeStatus() {
        return cakeStatus;
    }

    public void setCakeStatus(String cakeStatus) {
        this.cakeStatus = cakeStatus;
    }

    @XmlTransient
    public Collection<OrderingDetail> getOrderingDetailCollection() {
        return orderingDetailCollection;
    }

    public void setOrderingDetailCollection(Collection<OrderingDetail> orderingDetailCollection) {
        this.orderingDetailCollection = orderingDetailCollection;
    }

    public Category getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Category categoryID) {
        this.categoryID = categoryID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cakeID != null ? cakeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cake)) {
            return false;
        }
        Cake other = (Cake) object;
        if ((this.cakeID == null && other.cakeID != null) || (this.cakeID != null && !this.cakeID.equals(other.cakeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trungnd.entity.Cake[ cakeID=" + cakeID + " ]";
    }

    public String getCakeImage() {
        return cakeImage;
    }

    public void setCakeImage(String cakeImage) {
        this.cakeImage = cakeImage;
    }

    public Account getAccAdminUpdate() {
        return accAdminUpdate;
    }

    public void setAccAdminUpdate(Account accAdminUpdate) {
        this.accAdminUpdate = accAdminUpdate;
    }
    
}
