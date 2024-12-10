/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

public class ExcepNoCredito extends Exception
{
    private String msg;
    
    public ExcepNoCredito(String mensaje) {
        this.msg = mensaje;
        
    }

    public String getMsg() {
        return msg;
    }
    
    

    @Override
    public String toString() {
        return "Excepcion de servicio:" + this.msg;
    }
        
}
