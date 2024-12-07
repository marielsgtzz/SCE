
package wsmusicacredito;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para autoriza_double complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="autoriza_double">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id_clte" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="dbl_monto" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "autoriza_double", propOrder = {
    "idClte",
    "dblMonto"
})
public class AutorizaDouble {

    @XmlElement(name = "id_clte")
    protected int idClte;
    @XmlElement(name = "dbl_monto")
    protected double dblMonto;

    /**
     * Obtiene el valor de la propiedad idClte.
     * 
     */
    public int getIdClte() {
        return idClte;
    }

    /**
     * Define el valor de la propiedad idClte.
     * 
     */
    public void setIdClte(int value) {
        this.idClte = value;
    }

    /**
     * Obtiene el valor de la propiedad dblMonto.
     * 
     */
    public double getDblMonto() {
        return dblMonto;
    }

    /**
     * Define el valor de la propiedad dblMonto.
     * 
     */
    public void setDblMonto(double value) {
        this.dblMonto = value;
    }

}
