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

@WebService(serviceName = "WSMusicaCredito")
public class WSMusicaCredito {

    @EJB
    private CreditoFacade ejbRef;

    @WebMethod(operationName = "create")
    @Oneway
    public void create(@WebParam(name = "entity") Credito entity) {
        ejbRef.create(entity);
    }

    @WebMethod(operationName = "edit")
    @Oneway
    public void edit(@WebParam(name = "entity") Credito entity) {
        ejbRef.edit(entity);
    }

    @WebMethod(operationName = "remove")
    @Oneway
    public void remove(@WebParam(name = "entity") Credito entity) {
        ejbRef.remove(entity);
    }

    @WebMethod(operationName = "find")
    public Credito find(@WebParam(name = "id") Object id) {
        return ejbRef.find(id);
    }

    @WebMethod(operationName = "findAll")
    public List<Credito> findAll() {
        return ejbRef.findAll();
    }

    @WebMethod(operationName = "findRange")
    public List<Credito> findRange(@WebParam(name = "range") int[] range) {
        return ejbRef.findRange(range);
    }

    @WebMethod(operationName = "count")
    public int count() {
        return ejbRef.count();
    }

    /**
     * Web service operation
     * @param id_clte
     * @param monto
     * @return 
     * @throws entidades.ExcepNoCredito
     * @throws entidades.ExcepNoExisteClte
     */
    @WebMethod(operationName = "autoriza")
    public boolean autoriza(@WebParam(name = "id_clte") final int id_clte, @WebParam(name = "monto") final BigDecimal monto) throws ExcepNoCredito, ExcepNoExisteClte {
        
        boolean blnAutorizado = ejbRef.actualizaCredito(id_clte, monto);
        
        return blnAutorizado;
    }
    
    @WebMethod(operationName = "autoriza_double")
    public boolean autoriza_double(@WebParam(name = "id_clte") final int id_clte, @WebParam(name = "dbl_monto") final double dbl_monto) throws ExcepNoCredito, ExcepNoExisteClte {
        
        BigDecimal monto = BigDecimal.valueOf(dbl_monto);

        monto.setScale(2,BigDecimal.ROUND_HALF_UP);

        boolean blnAutorizado = ejbRef.actualizaCredito(id_clte, monto);
        
        return blnAutorizado;
    }
    
}
