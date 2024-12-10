/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sdist
 */
@Entity
@Table(name = "ORDERED_PRODUCT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderedProduct.findAll", query = "SELECT o FROM OrderedProduct o")
    , @NamedQuery(name = "OrderedProduct.findByCustomerOrderId", query = "SELECT o FROM OrderedProduct o WHERE o.orderedProductPK.customerOrderId = :customerOrderId")
    , @NamedQuery(name = "OrderedProduct.findByProductId", query = "SELECT o FROM OrderedProduct o WHERE o.orderedProductPK.productId = :productId")
    , @NamedQuery(name = "OrderedProduct.findByQuantity", query = "SELECT o FROM OrderedProduct o WHERE o.quantity = :quantity")
    , @NamedQuery(name = "OrderedProduct.findByStatusId", query = "SELECT o FROM OrderedProduct o WHERE o.statusId = :statusId")})
public class OrderedProduct implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected OrderedProductPK orderedProductPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "QUANTITY")
    private short quantity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "STATUS_ID")
    private String statusId;
    @JoinColumn(name = "CUSTOMER_ORDER_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CustomerOrder customerOrder;
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "ID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Product product;

    public OrderedProduct() {
    }

    public OrderedProduct(OrderedProductPK orderedProductPK) {
        this.orderedProductPK = orderedProductPK;
    }

    public OrderedProduct(OrderedProductPK orderedProductPK, short quantity, String statusId) {
        this.orderedProductPK = orderedProductPK;
        this.quantity = quantity;
        this.statusId = statusId;
    }

    public OrderedProduct(int customerOrderId, int productId) {
        this.orderedProductPK = new OrderedProductPK(customerOrderId, productId);
    }

    public OrderedProductPK getOrderedProductPK() {
        return orderedProductPK;
    }

    public void setOrderedProductPK(OrderedProductPK orderedProductPK) {
        this.orderedProductPK = orderedProductPK;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderedProductPK != null ? orderedProductPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderedProduct)) {
            return false;
        }
        OrderedProduct other = (OrderedProduct) object;
        if ((this.orderedProductPK == null && other.orderedProductPK != null) || (this.orderedProductPK != null && !this.orderedProductPK.equals(other.orderedProductPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.OrderedProduct[ orderedProductPK=" + orderedProductPK + " ]";
    }
    
}
