/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

public class ExcepNoCredito extends Exception
{
    int id_clte;
    
    public ExcepNoCredito(int id_clte) {
        this.id_clte = id_clte;
    }

    @Override
    public String toString() {
        return "El Cliente " + this.id_clte + " no tiene crèdito";
    }
        
}
