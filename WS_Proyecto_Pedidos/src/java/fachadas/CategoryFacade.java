/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.Category;
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
public class CategoryFacade extends AbstractFacade<Category> {

    @PersistenceContext(unitName = "WS_Proyecto_PedidosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoryFacade() {
        super(Category.class);
    }
    
        ///
    public int next_id_pedidos()
    {
        EntityManager em = getEntityManager();
       
        int intRes = (int) em.createNativeQuery("VALUES (NEXT VALUE FOR pedidos_id)").getSingleResult();       
        
        Logger.getAnonymousLogger().log(Level.SEVERE,"El valor del folio de pedidos es:" + intRes);
        
        return intRes;
    }
    
    //////////
    
}
