/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsmusicapedidos;

import entidades.Customer;
import fachadas.CustomerFacade;
import fachadas.CustomerOrderFacade;
import fachadas.OrderedProductFacade;
import fachadas.ProductFacade;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author sdist
 */
@WebService(serviceName = "WSMusicaPedidos")
public class WSMusicaPedidos {
    
    
    @EJB
    private OrderedProductFacade orderedProductFacade;
    @EJB
    private CustomerOrderFacade customerOrderFacade;
    @EJB
    private ProductFacade productFacade;
    @EJB
    private CustomerFacade customerFacade;

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }

    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "procesoDeCompra")
    public String procesoDeCompra() {
        //TODO write your implementation code here:
        return null;
    }
    
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "altaPedido")
    public int altaPedido(@WebParam(name = "id_clte") int id_clte, 
                          @WebParam(name = "lista_it") List<ClsItem> lista_it) 
    {
        //List<entidades.Product> lista_prods_en_pedido = new ArrayList<>();
        List<entidades.OrderedProduct> lista_orderedProducts = new ArrayList<>();
        entidades.OrderedProduct ordered_product;
        entidades.Product prod;
        int num_conf;
        // para obtener la cantidad posible a surtir
        int cantidad_posible;
        int num_pedido;
        
        BigDecimal bd_monto_pedido = BigDecimal.ZERO;
        BigDecimal bd_monto_item;
        for (ClsItem it : lista_it) 
        {
            Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                    "Product_id:" + it.getId_prod() + ", cantidad:"
                    + it.getCantidad());
            // -----------------------
            
            cantidad_posible = productFacade.actualizaExistencia(it.getId_prod(),it.getCantidad());
            
            if (cantidad_posible > 0) {
                ordered_product = new entidades.OrderedProduct();
                prod = productFacade.find(new Integer(it.getId_prod()));
                ordered_product.setProduct(prod);
                
                if( cantidad_posible > 0 )
                {
                  ordered_product.setQuantity((short) cantidad_posible);
                  lista_orderedProducts.add(ordered_product);
                  //lista_prods_en_pedido.add(prod);
                  Logger.getLogger(this.getClass().getName()).log(Level.INFO,
                        "Product_id:" + prod.getId() + ", "
                        + prod.getName() + ", "
                        + prod.getDescription() + ", "
                        + prod.getCategoryId().getName() + ", "
                        + prod.getPrice());
                  bd_monto_item = prod.getPrice().multiply(BigDecimal.valueOf(cantidad_posible));
                  bd_monto_item = bd_monto_item.setScale(2,BigDecimal.ROUND_HALF_UP);
                  bd_monto_pedido = bd_monto_pedido.add(bd_monto_item);  
                }
                //--------------------------------------------
            } 
            else 
            {
                Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Clave de producto " + it.getId_prod() + " INEXISTENTE o sin existencias");
            }
        }
        if( lista_orderedProducts.size() > 0 ) // hay items en el pedido
        {
          entidades.Customer clte = customerFacade.find(new Integer(id_clte));
          // se obtiene el cliente
          Logger.getLogger(this.getClass().getName()).log(Level.SEVERE,
                "Pedido del cliente:" + clte.getId() + ", "
                + clte.getName() + " por " + bd_monto_pedido);
          //
          // Se genera el customer order
          //
          entidades.CustomerOrder customer_order = new entidades.CustomerOrder();
          customer_order.setCustomerId(clte);
          java.util.Date d = new java.util.Date();

          customer_order.setDateCreated(d);
          customer_order.setAmount(bd_monto_pedido);
        
          // se solicita el número de confirmación del pedido
          num_conf = customerOrderFacade.next_conf_number();
        
          customer_order.setConfirmationNumber(num_conf);
          customerOrderFacade.create(customer_order);
          num_pedido = customer_order.getId();
          //
          // Van los items
          //
          entidades.OrderedProductPK oppk;
          for (entidades.OrderedProduct op : lista_orderedProducts) 
          {
            oppk = new entidades.OrderedProductPK();
            oppk.setCustomerOrderId(customer_order.getId());
            oppk.setProductId(op.getProduct().getId());
            op.setOrderedProductPK(oppk);
            op.setCustomerOrder(customer_order);
            orderedProductFacade.create(op);
          }
        }
        else
        {
            num_pedido = 0;  // no hay items a surtir en el pedido
        }
        
        return num_pedido;
    }
    // =========================================================================
    //             Para el catálogo de clientes y de productos
    // =========================================================================
    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "catalogoCltes")
    public java.util.List<entidades.Customer> catalogoCltes() 
    {
        return customerFacade.findAll();
    }

    /**
     * Web service operation
     * @return 
     */
    @WebMethod(operationName = "catalogoProds")
    public java.util.List<entidades.Product> catalogoProds() 
    {   
        return productFacade.findAll();
    }
    
    
    
    // =============================================================================
    // Nuevos servicios
    // =============================================================================
    /**
    * Web service operation
    */
    @WebMethod(operationName = "montoPedido")
    public BigDecimal montoPedido(@WebParam(name = "num_pedido") int num_pedido)
    {
        java.math.BigDecimal monto_del_pedido;

        monto_del_pedido = customerOrderFacade.find(num_pedido).getAmount();

        return monto_del_pedido;
    }

    /**
    * Web service operation
    */
    @WebMethod(operationName = "clteDelPedido")
    public Customer clteDelPedido(@WebParam(name = "num_pedido") int num_pedido)
    {
        return customerOrderFacade.find(num_pedido).getCustomerId();
    }

    // =============================================================================
    // Fin de Nuevos servicio
    // =============================================================================    

}
