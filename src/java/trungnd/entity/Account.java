/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HOME
 */
@Entity
@Table(name = "Account", catalog = "YellowMoonShop", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
    , @NamedQuery(name = "Account.findByEmail", query = "SELECT a FROM Account a WHERE a.email = :email")
    , @NamedQuery(name = "Account.findByPassword", query = "SELECT a FROM Account a WHERE a.password = :password")
    , @NamedQuery(name = "Account.findByName", query = "SELECT a FROM Account a WHERE a.name = :name")
    , @NamedQuery(name = "Account.findByPicture", query = "SELECT a FROM Account a WHERE a.picture = :picture")
    , @NamedQuery(name = "Account.findByUserAddress", query = "SELECT a FROM Account a WHERE a.userAddress = :userAddress")
    , @NamedQuery(name = "Account.findByUserRole", query = "SELECT a FROM Account a WHERE a.userRole = :userRole")
    , @NamedQuery(name = "Account.findByGoogleID", query = "SELECT a FROM Account a WHERE a.googleID = :googleID")
    , @NamedQuery(name = "Account.findByUserStatus", query = "SELECT a FROM Account a WHERE a.userStatus = :userStatus")})
public class Account implements Serializable {
    

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 255)
    private String email;
    @Column(name = "password", length = 100)
    private String password;
    @Basic(optional = false)
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Column(name = "picture", length = 20)
    private String picture;
    @Column(name = "userAddress", length = 500)
    private String userAddress;
    @Basic(optional = false)
    @Column(name = "userRole", nullable = false, length = 10)
    private String userRole;
    @Column(name = "googleID", length = 255)
    private String googleID;
    @Basic(optional = false)
    @Column(name = "userStatus", nullable = false, length = 10)
    private String userStatus;
    @OneToMany(mappedBy = "email")
    private Collection<Ordering> orderingCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "accAdminUpdate")
    private Collection<Cake> cakeCollection;

    public Account() {
    }

    public Account(String email) {
        this.email = email;
    }

    public Account(String email, String name, String userRole, String userStatus) {
        this.email = email;
        this.name = name;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }

    public Account(String email, String password, String name, String picture, String userAddress, String userRole, String userStatus) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.picture = picture;
        this.userAddress = userAddress;
        this.userRole = userRole;
        this.userStatus = userStatus;
    }
    
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getGoogleID() {
        return googleID;
    }

    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @XmlTransient
    public Collection<Ordering> getOrderingCollection() {
        return orderingCollection;
    }

    public void setOrderingCollection(Collection<Ordering> orderingCollection) {
        this.orderingCollection = orderingCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "trungnd.entity.Account[ email=" + email + " ]";
    }

    @XmlTransient
    public Collection<Cake> getCakeCollection() {
        return cakeCollection;
    }

    public void setCakeCollection(Collection<Cake> cakeCollection) {
        this.cakeCollection = cakeCollection;
    }
    
}
