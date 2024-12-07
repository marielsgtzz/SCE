/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsmusicacredito;

import entidades.ExcepNoExisteClte;
import entidades.ExcepNoCredito;
import entidades.Credito;
import fachadas.CreditoFacade;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Servicio Web para la gestión de créditos de clientes en Vinilos Vintach.
 */
@WebService(serviceName = "WSMusicaCredito")
public class WSMusicaCredito {

    /**
     * Referencia al EJB para operaciones sobre la entidad Credito.
     */
    @EJB
    private CreditoFacade ejbRef;

    /**
     * Crea un nuevo registro de crédito en la base de datos.
     * @param entity Entidad de tipo Credito.
     */
    @WebMethod(operationName = "create")
    @Oneway
    public void create(@WebParam(name = "entity") Credito entity) {
        ejbRef.create(entity);
    }

    /**
     * Edita un registro de crédito existente en la base de datos.
     * @param entity Entidad de tipo Credito con datos actualizados.
     */
    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@WebParam(name = "entity") Credito entity) {
        ejbRef.edit(entity);
    }

    /**
     * Elimina un registro de crédito de la base de datos.
     * @param entity Entidad de tipo Credito a eliminar.
     */
    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "entity") Credito entity) {
        ejbRef.remove(entity);
    }

    /**
     * Busca un registro de crédito por ID.
     * @param id Identificador único del crédito.
     * @return Entidad Credito encontrada o null si no existe.
     */
    @WebMethod(operationName = "find")
    public Credito find(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    /**
     * Recupera todos los registros de crédito de la base de datos.
     * @return Lista de entidades Credito.
     */
    @WebMethod(operationName = "findAll")
    public List<Credito> findAll() {
        return ejbRef.findAll();
    }

    /**
     * Recupera un subconjunto de registros de crédito basado en un rango.
     * @param range Rango de índices para consultar.
     * @return Lista de entidades Credito dentro del rango especificado.
     */
    @WebMethod(operationName = "findRange")
    public List<Credito> findRange(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    /**
     * Retorna la cantidad total de créditos registrados.
     * @return Cantidad de registros en la base de datos.
     */
    @WebMethod(operationName = "count")
    public int count() {
        return ejbRef.count();
    }

    /**
     * Autoriza un crédito para un cliente.
     * @param id_clte ID del cliente.
     * @param monto Monto solicitado como BigDecimal.
     * @return true si el crédito es autorizado, false en caso contrario.
     * @throws ExcepNoCredito Si el cliente no tiene crédito suficiente.
     * @throws ExcepNoExisteClte Si el cliente no está registrado.
     */
    @WebMethod(operationName = "autoriza")
    public boolean autoriza(@WebParam(name = "id_clte") final int id_clte, 
                            @WebParam(name = "monto") final BigDecimal monto) throws ExcepNoCredito, ExcepNoExisteClte {
        boolean blnAutorizado = ejbRef.actualizaCredito(id_clte, monto);
        return blnAutorizado;
    }

    /**
     * Autoriza un crédito para un cliente con un monto en formato double.
     * @param id_clte ID del cliente.
     * @param dbl_monto Monto solicitado como double.
     * @return true si el crédito es autorizado, false en caso contrario.
     * @throws ExcepNoCredito Si el cliente no tiene crédito suficiente.
     * @throws ExcepNoExisteClte Si el cliente no está registrado.
     */
    @WebMethod(operationName = "autoriza_double")
    public boolean autoriza_double(@WebParam(name = "id_clte") final int id_clte, 
                                   @WebParam(name = "dbl_monto") final double dbl_monto) throws ExcepNoCredito, ExcepNoExisteClte {
        BigDecimal monto = BigDecimal.valueOf(dbl_monto);
        monto.setScale(2,BigDecimal.ROUND_HALF_UP);
        boolean blnAutorizado = ejbRef.actualizaCredito(id_clte, monto);
        return blnAutorizado;
    }
    
     @WebMethod(operationName = "getClienteDetalles")
    public String[] getClienteDetalles(@WebParam(name = "id_clte") final int id_clte) throws ExcepNoExisteClte {
        // Buscar cliente en la base de datos usando el id_clte
        Credito cliente = ejbRef.find(id_clte);

        // Si el cliente no existe, lanzamos una excepción
        if (cliente == null) {
            throw new ExcepNoExisteClte(id_clte);
        }

        // Crear un array de Strings con los detalles del cliente
        String[] detalles = new String[8];
        detalles[0] = "ID: " + cliente.getId();
        detalles[1] = "Nombre: " + cliente.getName();
        detalles[2] = "Email: " + cliente.getEmail();
        detalles[3] = "Teléfono: " + cliente.getPhone();
        detalles[4] = "Dirección: " + cliente.getAddress();
        detalles[5] = "Región de Ciudad: " + cliente.getCityRegion();
        detalles[6] = "Número de Tarjeta de Crédito: " + cliente.getCcNumber();
        detalles[7] = "Crédito Disponible: " + cliente.getCredito().toPlainString();

        // Devolver el array con los detalles
        return detalles;
    }
}
