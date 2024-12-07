/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fachadas;

import entidades.Envios;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alejandro Uribe
 */
@Stateless
public class EnviosFacade extends AbstractFacade<Envios> {

    @PersistenceContext(unitName = "WS_Musica_EnviosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EnviosFacade() {
        super(Envios.class);
    }
    
}
