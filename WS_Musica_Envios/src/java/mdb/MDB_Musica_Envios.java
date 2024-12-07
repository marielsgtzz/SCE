/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import entidades.Envios;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;

/**
 *
 * @author Alejandro Uribe
 */
@JMSDestinationDefinition(name = "java:app/jms/SolicitudEnvios", interfaceName = "javax.jms.Queue", resourceAdapter = "jmsra", destinationName = "SolicitudEnvios")

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:app/jms/SolicitudEnvios")
    ,
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MDB_Musica_Envios implements MessageListener {
    
    @Resource
    private MessageDrivenContext mdc;
    
    @PersistenceContext(unitName = "WS_Musica_EnviosPU")
    private EntityManager em;
    
    public MDB_Musica_Envios() {
    }
    
    @Override
    public void onMessage(Message message) 
    {
      ObjectMessage msg = null;
       
      try 
      {
        if (message instanceof ObjectMessage) {
            msg = (ObjectMessage) message;
            Envios envio = (Envios) msg.getObject();
            
            Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                    "Recibiendo solicitud de envio para:" + envio.getName() + ", para el pedido:" + envio.getIdCustomerOrder());

            
            save(envio);
        }
      } 
      catch (JMSException e) 
      {
        e.printStackTrace();
        mdc.setRollbackOnly();
      } 
      catch (Throwable te) 
      {
        te.printStackTrace();
      }      
    }

    public void save(Object object) {
        em.persist(object);
        em.flush();
    }
    
}