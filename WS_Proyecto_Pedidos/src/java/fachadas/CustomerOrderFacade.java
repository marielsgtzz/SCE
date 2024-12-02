/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.CustomerOrder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sdist
 */
@Stateless
public class CustomerOrderFacade extends AbstractFacade<CustomerOrder> {

    @PersistenceContext(unitName = "WS_Proyecto_PedidosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerOrderFacade() {
        super(CustomerOrder.class);
    }
       // ===========================================================================================

    public int next_conf_number() {
        int intRes = (int) em.createNativeQuery("VALUES (NEXT VALUE FOR NUM_CONF)").getSingleResult();
        Logger.getAnonymousLogger().log(Level.SEVERE, "El valor del número de confirmación del pedido es:" + intRes);
        return intRes;
    }

    // ===========================================================================================


}
