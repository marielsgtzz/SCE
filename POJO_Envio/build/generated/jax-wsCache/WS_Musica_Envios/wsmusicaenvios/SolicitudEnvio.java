
package wsmusicaenvios;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para solicitudEnvio complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="solicitudEnvio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id_Tda" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id_pedido" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="city_region" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "solicitudEnvio", propOrder = {
    "idTda",
    "idPedido",
    "name",
    "email",
    "phone",
    "address",
    "cityRegion"
})
public class SolicitudEnvio {

    @XmlElement(name = "id_Tda")
    protected int idTda;
    @XmlElement(name = "id_pedido")
    protected int idPedido;
    protected String name;
    protected String email;
    protected String phone;
    protected String address;
    @XmlElement(name = "city_region")
    protected String cityRegion;

    /**
     * Obtiene el valor de la propiedad idTda.
     * 
     */
    public int getIdTda() {
        return idTda;
    }

    /**
     * Define el valor de la propiedad idTda.
     * 
     */
    public void setIdTda(int value) {
        this.idTda = value;
    }

    /**
     * Obtiene el valor de la propiedad idPedido.
     * 
     */
    public int getIdPedido() {
        return idPedido;
    }

    /**
     * Define el valor de la propiedad idPedido.
     * 
     */
    public void setIdPedido(int value) {
        this.idPedido = value;
    }

    /**
     * Obtiene el valor de la propiedad name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Define el valor de la propiedad name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtiene el valor de la propiedad email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define el valor de la propiedad email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Obtiene el valor de la propiedad phone.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Define el valor de la propiedad phone.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * Obtiene el valor de la propiedad address.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * Define el valor de la propiedad address.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * Obtiene el valor de la propiedad cityRegion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityRegion() {
        return cityRegion;
    }

    /**
     * Define el valor de la propiedad cityRegion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityRegion(String value) {
        this.cityRegion = value;
    }

}