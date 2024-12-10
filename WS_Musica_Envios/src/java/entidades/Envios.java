/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alejandro Uribe
 */
@Entity
@Table(name = "ENVIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Envios.findAll", query = "SELECT e FROM Envios e")
    , @NamedQuery(name = "Envios.findById", query = "SELECT e FROM Envios e WHERE e.id = :id")
    , @NamedQuery(name = "Envios.findByIdTda", query = "SELECT e FROM Envios e WHERE e.idTda = :idTda")
    , @NamedQuery(name = "Envios.findByIdCustomerOrder", query = "SELECT e FROM Envios e WHERE e.idCustomerOrder = :idCustomerOrder")
    , @NamedQuery(name = "Envios.findByName", query = "SELECT e FROM Envios e WHERE e.name = :name")
    , @NamedQuery(name = "Envios.findByPhone", query = "SELECT e FROM Envios e WHERE e.phone = :phone")
    , @NamedQuery(name = "Envios.findByAddress", query = "SELECT e FROM Envios e WHERE e.address = :address")
    , @NamedQuery(name = "Envios.findByCityRegion", query = "SELECT e FROM Envios e WHERE e.cityRegion = :cityRegion")})
public class Envios implements Serializable {

    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "EMAIL")
    private String email;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TDA")
    private int idTda;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_CUSTOMER_ORDER")
    private int idCustomerOrder;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NAME")
    private String name;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "PHONE")
    private String phone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ADDRESS")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "CITY_REGION")
    private String cityRegion;

    public Envios() {
    }

    public Envios(Integer id) {
        this.id = id;
    }

    public Envios(Integer id, int idTda, int idCustomerOrder, String name, String phone, String address, String cityRegion) {
        this.id = id;
        this.idTda = idTda;
        this.idCustomerOrder = idCustomerOrder;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.cityRegion = cityRegion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdTda() {
        return idTda;
    }

    public void setIdTda(int idTda) {
        this.idTda = idTda;
    }

    public int getIdCustomerOrder() {
        return idCustomerOrder;
    }

    public void setIdCustomerOrder(int idCustomerOrder) {
        this.idCustomerOrder = idCustomerOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCityRegion() {
        return cityRegion;
    }

    public void setCityRegion(String cityRegion) {
        this.cityRegion = cityRegion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Envios)) {
            return false;
        }
        Envios other = (Envios) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Envios[ id=" + id + " ]";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
