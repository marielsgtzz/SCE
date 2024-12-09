
package wsmusicacredito;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getClienteDetalles complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getClienteDetalles">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id_clte" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getClienteDetalles", propOrder = {
    "idClte"
})
public class GetClienteDetalles {

    @XmlElement(name = "id_clte")
    protected int idClte;

    /**
     * Gets the value of the idClte property.
     * 
     */
    public int getIdClte() {
        return idClte;
    }

    /**
     * Sets the value of the idClte property.
     * 
     */
    public void setIdClte(int value) {
        this.idClte = value;
    }

}
