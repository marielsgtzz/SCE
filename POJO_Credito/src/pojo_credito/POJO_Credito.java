/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo_credito;

import wsmusicacredito.ExcepNoCredito_Exception;
import wsmusicacredito.ExcepNoExisteClte_Exception;

public class POJO_Credito {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
       int VECES = args.length > 0 ? Integer.parseInt(args[0]) : 10;
       int id_clte;
       double dblMonto = 0.0;
       for(int vez = 1; vez <= VECES; vez++)
       {
           dblMonto = 100.0 * Math.random();
           id_clte  = (int)(10.0 * Math.random());
           //id_clte = 3;
           
           try
           {
               if( autorizaDouble(id_clte,dblMonto))
               {
                   System.out.println("Se ha autorizado el credito de " + dblMonto + " para el id_clte " +id_clte);
               }
               else
               {
                   System.out.println("No se ha autorizado el credito de " + dblMonto + " para el id_clte " +id_clte);
               }
           }
           catch( Exception ex)
           {
               System.out.println(ex.toString());
           }
               
       }
    }

    private static boolean autorizaDouble(int idClte, double dblMonto) throws ExcepNoExisteClte_Exception, ExcepNoCredito_Exception {
        wsmusicacredito.WSMusicaCredito_Service service = new wsmusicacredito.WSMusicaCredito_Service();
        wsmusicacredito.WSMusicaCredito port = service.getWSMusicaCreditoPort();        
        return port.autorizaDouble(idClte, dblMonto);
    }
    
    
    
}
