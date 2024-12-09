/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazvinilesvintachestres;

/**
 *
 * @author Alejandro Uribe
 */
public interface InterfazVinilesVintachEstres {

    boolean prepara (long quienSoy, String host);
    long solicitaServicio(int vez) throws Exception;
    void cierra ();
}
