
package wsmusicaenvios;

import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WS_Musica_Envios", targetNamespace = "http://wsmusicaenvios/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WSMusicaEnvios {


    /**
     * 
     * @param entity
     */
    @WebMethod
    @Oneway
    @RequestWrapper(localName = "remove", targetNamespace = "http://wsmusicaenvios/", className = "wsmusicaenvios.Remove")
    @Action(input = "http://wsmusicaenvios/WS_Musica_Envios/remove")
    public void remove(
        @WebParam(name = "entity", targetNamespace = "")
        Envios entity);

    /**
     * 
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "count", targetNamespace = "http://wsmusicaenvios/", className = "wsmusicaenvios.Count")
    @ResponseWrapper(localName = "countResponse", targetNamespace = "http://wsmusicaenvios/", className = "wsmusicaenvios.CountResponse")
    @Action(input = "http://wsmusicaenvios/WS_Musica_Envios/countRequest", output = "http://wsmusicaenvios/WS_Musica_Envios/countResponse")
    public int count();

    /**
     * 
     * @param id
     * @return
     *     returns wsmusicaenvios.Envios
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "find", targetNamespace = "http://wsmusicaenvios/", className = "wsmusicaenvios.Find")
    @ResponseWrapper(localName = "findResponse", targetNamespace = "http://wsmusicaenvios/", className = "wsmusicaenvios.FindResponse")
    @Action(input = "http://wsmusicaenvios/WS_Musica_Envios/findRequest", output = "http://wsmusicaenvios/WS_Musica_Envios/findResponse")
    public Envios find(
        @WebParam(name = "id", targetNamespace = "")
        Object id);

    /**
     * 
     * @param entity
     */
    @WebMethod
    @Oneway
    @RequestWrapper(localName = "create", targetNamespace = "http://wsmusicaenvios/", className = "wsmusicaenvios.Create")
    @Action(input = "http://wsmusicaenvios/WS_Musica_Envios/create")
    public void create(
        @WebParam(name = "entity", targetNamespace = "")
        Envios entity);

    /**
     * 
     * @return
     *     returns java.util.List<wsmusicaenvios.Envios>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findAll", targetNamespace = "http://wsmusicaenvios/", className = "wsmusicaenvios.FindAll")
    @ResponseWrapper(localName = "findAllResponse", targetNamespace = "http://wsmusicaenvios/", className = "wsmusicaenvios.FindAllResponse")
    @Action(input = "http://wsmusicaenvios/WS_Musica_Envios/findAllRequest", output = "http://wsmusicaenvios/WS_Musica_Envios/findAllResponse")
    public List<Envios> findAll();

    /**
     * 
     * @param entity
     */
    @WebMethod
    @Oneway
    @RequestWrapper(localName = "edit", targetNamespace = "http://wsmusicaenvios/", className = "wsmusicaenvios.Edit")
    @Action(input = "http://wsmusicaenvios/WS_Musica_Envios/edit")
    public void edit(
        @WebParam(name = "entity", targetNamespace = "")
        Envios entity);

    /**
     * 
     * @param range
     * @return
     *     returns java.util.List<wsmusicaenvios.Envios>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findRange", targetNamespace = "http://wsmusicaenvios/", className = "wsmusicaenvios.FindRange")
    @ResponseWrapper(localName = "findRangeResponse", targetNamespace = "http://wsmusicaenvios/", className = "wsmusicaenvios.FindRangeResponse")
    @Action(input = "http://wsmusicaenvios/WS_Musica_Envios/findRangeRequest", output = "http://wsmusicaenvios/WS_Musica_Envios/findRangeResponse")
    public List<Envios> findRange(
        @WebParam(name = "range", targetNamespace = "")
        List<Integer> range);

    /**
     * 
     * @param idTda
     * @param cityRegion
     * @param address
     * @param phone
     * @param name
     * @param idPedido
     * @param email
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "solicitudEnvio", targetNamespace = "http://wsmusicaenvios/", className = "wsmusicaenvios.SolicitudEnvio")
    @ResponseWrapper(localName = "solicitudEnvioResponse", targetNamespace = "http://wsmusicaenvios/", className = "wsmusicaenvios.SolicitudEnvioResponse")
    @Action(input = "http://wsmusicaenvios/WS_Musica_Envios/solicitudEnvioRequest", output = "http://wsmusicaenvios/WS_Musica_Envios/solicitudEnvioResponse")
    public boolean solicitudEnvio(
        @WebParam(name = "id_Tda", targetNamespace = "")
        int idTda,
        @WebParam(name = "id_pedido", targetNamespace = "")
        int idPedido,
        @WebParam(name = "name", targetNamespace = "")
        String name,
        @WebParam(name = "email", targetNamespace = "")
        String email,
        @WebParam(name = "phone", targetNamespace = "")
        String phone,
        @WebParam(name = "address", targetNamespace = "")
        String address,
        @WebParam(name = "city_region", targetNamespace = "")
        String cityRegion);

}
