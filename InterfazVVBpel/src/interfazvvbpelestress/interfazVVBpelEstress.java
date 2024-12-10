/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfazvvbpelestress;

/**
 *
 * @author juanp
 */
public interface interfazVVBpelEstress {
    
    boolean prepara (long quienSoy, String host);
    long solicitaServicio(int vez) throws Exception;
    void cierra ();
    
}
