/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wsvinilesvintach;

/**
 *
 * @author marij
 */
public class ExcepSinExistencias extends Exception{
    int id_prod;
    
    public ExcepSinExistencias() {
        //this.id_prod = id_prod;
    }

    @Override
    public String toString() {
        //return "Producto sin existencias. No se puede generar el pedido.";
        return "No hay cliente";
    }
    
}
