/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsmusicaenvios;


import entidades.Envios;
import fachadas.EnviosFacade;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Alejandro Uribe
 */
@WebService(serviceName = "WS_Musica_Envios")
public class WS_Musica_Envios {
 @Resource(mappedName="jms/SolicitudEnviosFactory")
    private  ConnectionFactory connectionFactory;

    @Resource(mappedName="java:app/jms/SolicitudEnvios")
    private  Queue queue; 
    
    @EJB
    private EnviosFacade ejbRef;// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Web Service Operation")

    @WebMethod(operationName = "create")
    @Oneway
    public void create(@WebParam(name = "entity") Envios entity) {
        ejbRef.create(entity);
    }

    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@WebParam(name = "entity") Envios entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "entity") Envios entity) {
        ejbRef.remove(entity);
    }

    @WebMethod(operationName = "find")
    public Envios find(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<Envios> findAll() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRange")
    public List<Envios> findRange(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "count")
    public int count() {
        return ejbRef.count();
    }

    /**
     * Web service operation
     * @param id_Tda
     * @param id_pedido
     * @param name
     * @param email
     * @param phone
     * @param address
     * @param city_region
     * @return 
     */
    @WebMethod(operationName = "solicitudEnvio")
    public boolean solicitudEnvio(@WebParam(name = "id_Tda")      int    id_Tda, 
                                  @WebParam(name = "id_pedido")   int    id_pedido,
                                  @WebParam(name = "name")        String name, 
                                  @WebParam(name = "email")       String email, 
                                  @WebParam(name = "phone")       String phone, 
                                  @WebParam(name = "address")     String address, 
                                  @WebParam(name = "city_region") String city_region) 
    {
        boolean blnRes = false;
        
        entidades.Envios envio = new entidades.Envios();
        
        envio.setIdTda(id_Tda);
        envio.setIdCustomerOrder(id_pedido);
        envio.setName(name);
        envio.setEmail(email);
        envio.setPhone(phone);
        envio.setAddress(address);
        envio.setCityRegion(city_region);
        
        try 
        {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer messageProducer = session.createProducer(queue);

            ObjectMessage message = session.createObjectMessage();
            
            message.setObject(envio);
            messageProducer.send(message);
            
            Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                    "Encolando solicitud de envio para:" + envio.getName() + ", para el pedido:" + envio.getIdCustomerOrder() + " co email:" + envio.getEmail());

            messageProducer.close();
            connection.close();

            blnRes = true;
        }
        catch (Exception ex) 
        {
            blnRes = false;
        }

        return blnRes;
    }
    
    
    
    
}
