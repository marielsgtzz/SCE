/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.Product;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sdist
 */
@Stateless
public class ProductFacade extends AbstractFacade<Product> {

    @PersistenceContext(unitName = "WS_Proyecto_PedidosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductFacade() {
        super(Product.class);
    }
        // ====================================================================================================

    public int actualizaExistencia(int id_prod,int cant_solicitada)
    {
       int cant_posible = 0;
       int cant_existente;

       Product prod   = em.find(Product.class, new Integer(id_prod), LockModeType.PESSIMISTIC_WRITE);
       if(prod == null)
       {
         cant_posible = 0;   
       }
       else
       {
         cant_existente = prod.getExistencia();
         cant_posible   = cant_solicitada < cant_existente ? cant_solicitada : cant_existente;
         cant_existente -= cant_posible;
         prod.setExistencia(cant_existente);
         this.edit(prod);
       }

       prod = null;
       return cant_posible;
    }
   
    // ====================================================================================================
    
}
