/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsvinilesvintach;

import java.math.BigDecimal;
import java.util.Random;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceRef;
import wsmusicacredito.ExcepNoCredito_Exception;
import wsmusicacredito.ExcepNoExisteClte_Exception;
import wsmusicacredito.WSMusicaCredito_Service;
import wsmusicaenvios.WSMusicaEnvios_Service;
import wsmusicapedidos.WSMusicaPedidos_Service;
import wsmusicapedidos.Customer;

/**
 *
 * @author Alejandro Uribe
 */
@WebService(serviceName = "WS_VinilesVintach")
public class WS_VinilesVintach {

    wsmusicapedidos.WSMusicaPedidos_Service service = new wsmusicapedidos.WSMusicaPedidos_Service();
    //wsmusicapedidos.WSmusicaPedidos port = service.getWSmusicaPedidosPort();

    wsmusicacredito.WSMusicaCredito_Service service_1 = new wsmusicacredito.WSMusicaCredito_Service();
    //wsmusicacredito.WSmusicaCredito port = service_1.getWSmusicaCreditoPort();

    wsmusicaenvios.WSMusicaEnvios_Service service_2 = new wsmusicaenvios.WSMusicaEnvios_Service();
    //wsmusicaenvios.WSmusicaEnvios port = service_2.getWSmusicaEnviosPort();


    /**
     * Web service operation
     *
     * @param idClte
     * @param listaIt
     * @return
     * @throws wsmusicacredito.ExcepNoExisteClte_Exception
     * @throws wsmusicacredito.ExcepNoCredito_Exception
     * @throws wsvinilesvintach.ExcepSinExistencias
     */
    @WebMethod(operationName = "procesoCompra")
    public String procesoCompra(@WebParam(name = "idCliente") int idClte, 
                                @WebParam(name = "listaItems") java.util.List<wsmusicapedidos.ClsItem> listaIt)
            throws ExcepSinExistencias {
        if (listaIt == null || listaIt.isEmpty()) {
            return "Error: La lista de productos está vacía.";
        }

        int numPedido = altaPedido(idClte, listaIt);

        if (numPedido == 0) {
            throw new ExcepSinExistencias();
        }

        try {
            int idTda = 1;
            BigDecimal monto = montoPedido(numPedido);
            Customer clte = clteDelPedido(numPedido);

            boolean hayFondos = autoriza(idClte, monto);
            boolean haySolicitud = false;

            if (hayFondos) {
                haySolicitud = solicitudEnvio(idTda, numPedido, clte.getName(), clte.getEmail(), clte.getPhone(), clte.getAddress(), clte.getCityRegion());
            } else {
                throw new ExcepNoCredito_Exception("Fondos insuficientes.", null);
            }

            if (haySolicitud) {
                aceptar(numPedido, listaIt);
                return "Pedido Confirmado. Num Pedido: " + numPedido;
            } else {
                return "Error en la generación del pedido: Fallo en la solicitud de envío.";
            }
        } catch (ExcepNoExisteClte_Exception | ExcepNoCredito_Exception ex) {
            // Realizar restock si ocurre una de estas excepciones
            restock(numPedido, listaIt);
            return "Pedido Cancelado: " + ex.toString() +" restock completado";
        }
    }

    
    private void restock(int numPedido, java.util.List<wsmusicapedidos.ClsItem> listaItems) {
        wsmusicapedidos.WSMusicaPedidos port = service.getWSMusicaPedidosPort();

        for (wsmusicapedidos.ClsItem item : listaItems) {
            int productId = item.getIdProd();
            int cantidad = item.getCantidad();

            // Regresar la cantidad al inventario
            port.restockProduct(productId, cantidad);

            // Actualizar el estado del producto en el pedido a "C" (Cancelado)
            port.updateOrderedProductStatus(numPedido, productId, "C");
        }
    }
    
    private void aceptar(int numPedido, java.util.List<wsmusicapedidos.ClsItem> listaItems) {
        wsmusicapedidos.WSMusicaPedidos port = service.getWSMusicaPedidosPort();

        for (wsmusicapedidos.ClsItem item : listaItems) {
            int productId = item.getIdProd();
            int cantidad = item.getCantidad();

            // Actualizar el estado del producto en el pedido a "A" (Aplicado)
            port.updateOrderedProductStatus(numPedido, productId, "A");
        }
    }



    // =========================================================================
    // Servicios para apoyar la creación del Pojo
    // =========================================================================
    /**
     * Web service operation
     *
     * @return
     */
    @WebMethod(operationName = "catalogoClientes")
    public java.util.List<wsmusicapedidos.Customer> catalogoClientes() {

        return catalogoCltes();
    }

    /**
     * Web service operation
     *
     * @return
     */
    @WebMethod(operationName = "catalogoProductos")
    public java.util.List<wsmusicapedidos.Product> catalogoProductos() {

        return catalogoProds();
    }

    // =========================================================================
    // Servicios de Apoyo
    // =========================================================================
    private int altaPedido(int idClte, java.util.List<wsmusicapedidos.ClsItem> listaIt) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        wsmusicapedidos.WSMusicaPedidos port = service.getWSMusicaPedidosPort();
        return port.altaPedido(idClte, listaIt);
    }

    private boolean autoriza(int idClte, java.math.BigDecimal monto) throws ExcepNoExisteClte_Exception, ExcepNoCredito_Exception {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        wsmusicacredito.WSMusicaCredito port = service_1.getWSMusicaCreditoPort();
        return port.autoriza(idClte, monto);
    }

    private boolean solicitudEnvio(int idTda, int idPedido, java.lang.String name, java.lang.String email, java.lang.String phone, java.lang.String address, java.lang.String cityRegion) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        wsmusicaenvios.WSMusicaEnvios port = service_2.getWSMusicaEnviosPort();
        return port.solicitudEnvio(idTda, idPedido, name, email, phone, address, cityRegion);
    }

    private BigDecimal montoPedido(int numPedido) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        wsmusicapedidos.WSMusicaPedidos port = service.getWSMusicaPedidosPort();
        return port.montoPedido(numPedido);
    }

    private Customer clteDelPedido(int numPedido) {
        // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
        // If the calling of port operations may lead to race condition some synchronization is required.
        wsmusicapedidos.WSMusicaPedidos port = service.getWSMusicaPedidosPort();
        return port.clteDelPedido(numPedido);
    }

    // =========================================================================
    // Estos Servicios de Apoyo son para facilitar la creación del Pojo de prueba
    // =========================================================================
    private java.util.List<wsmusicapedidos.Customer> catalogoCltes() {
    // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
    // If the calling of port operations may lead to race condition some synchronization is required.
        wsmusicapedidos.WSMusicaPedidos port = service.getWSMusicaPedidosPort();
        return port.catalogoCltes();
    }

    private java.util.List<wsmusicapedidos.Product> catalogoProds() {
    // Note that the injected javax.xml.ws.Service reference as well as port objects are not thread safe.
    // If the calling of port operations may lead to race condition some synchronization is required.
        wsmusicapedidos.WSMusicaPedidos port = service.getWSMusicaPedidosPort();
        return port.catalogoProds();
    }
}

// =========================================================================
// Fin de Servicios de Apoyo
// =========================================================================

