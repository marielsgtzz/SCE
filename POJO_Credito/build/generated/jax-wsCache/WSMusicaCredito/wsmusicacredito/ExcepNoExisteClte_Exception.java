
package wsmusicacredito;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "ExcepNoExisteClte", targetNamespace = "http://wsmusicacredito/")
public class ExcepNoExisteClte_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ExcepNoExisteClte faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ExcepNoExisteClte_Exception(String message, ExcepNoExisteClte faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public ExcepNoExisteClte_Exception(String message, ExcepNoExisteClte faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: wsmusicacredito.ExcepNoExisteClte
     */
    public ExcepNoExisteClte getFaultInfo() {
        return faultInfo;
    }

}