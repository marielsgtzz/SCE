/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.OrderedProduct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author sdist
 */
@Stateless
public class OrderedProductFacade extends AbstractFacade<OrderedProduct> {

    @PersistenceContext(unitName = "WS_Musica_PedidosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderedProductFacade() {
        super(OrderedProduct.class);
    }
    
    public void updateOrderedProductStatus(int numPedido, int productId, String status) {
        Query query = em.createQuery(
            "UPDATE OrderedProduct op " +
            "SET op.statusId = :status " +
            "WHERE op.customerOrder.id = :numPedido AND op.product.id = :productId"
        );
        query.setParameter("status", status);
        query.setParameter("numPedido", numPedido);
        query.setParameter("productId", productId);
        query.executeUpdate();
    }

    public java.util.List<OrderedProduct> getOrderedProductsByOrder(int numPedido) {
        Query query = em.createQuery(
            "SELECT op FROM OrderedProduct op WHERE op.customerOrder.id = :numPedido"
        );
        query.setParameter("numPedido", numPedido);
        return query.getResultList();
    }


    
}
