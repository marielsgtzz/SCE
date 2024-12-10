/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

public class ExcepNoExisteClte extends Exception
{
    private String msg;
    
    public ExcepNoExisteClte(String mensaje) {
        this.msg = mensaje;
    }

    public String getMsg() {
        return msg;
    }
    

    @Override
    public String toString() {
        return "Excepcion:" + this.msg;
    }
}
