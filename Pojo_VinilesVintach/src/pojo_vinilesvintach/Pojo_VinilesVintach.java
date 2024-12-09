/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo_vinilesvintach;

import interfazvinilesvintachestres.InterfazVinilesVintachEstres;
import wsvinilesvintach.ExcepSinExistencias_Exception;


/**
 *
 * @author Alejandro Uribe
 */
public class Pojo_VinilesVintach implements InterfazVinilesVintachEstres{


    long quienSoy;
    String host = null;
    
    @Override
    public boolean prepara(long quienSoy, String host) 
    {
        this.quienSoy = quienSoy;
        this.host     = host;        // NOTA: no se utiliza
        return true;
    }

    @Override
    public long solicitaServicio(int vez)
    {
        long t0, t1 = 0, dt;
        java.util.List<wsmusicapedidos.ClsItem>  listaIt    = new java.util.ArrayList<>();
        java.util.List<wsmusicapedidos.Customer> catCltes = catalogoCltes();
        java.util.List<wsmusicapedidos.Product> catProds = catalogoProds();
        
        int num_cltes = catCltes.size();
        int num_prods = catProds.size();
        String num_pedido;
        int id_clte;
        int num_it;
        int id_prod,cantidad;

        int queClte,queProd = 0;
       
        wsmusicapedidos.ClsItem item;
        
        queClte = (int) ( num_cltes * Math.random());
        id_clte = catCltes.get(queClte).getId();
        //id_clte = 1;

        num_it = (int) (1.0 + 4.0 * Math.random());
        num_it = num_it <= num_prods ? num_it : num_prods;
        
        //
        // para controlar que no se repitan los id_prod de los items
        //
        int [] arr_id_prod = new int[num_it];
        boolean ya_esta;
        //
        // Se generan los items para el pedido
        //
        for( int k = 0; k <  num_it; k++)
        {
            ya_esta = true;
            while(ya_esta)
            {
              queProd = (int) ( num_prods * Math.random());
              if( k == 0)
                  ya_esta = false;
              else
              {   
                  ya_esta = false;
                  for( int j = 0; j < k; j++)
                      ya_esta = ya_esta | queProd == arr_id_prod[j]; 
              }
            }
            arr_id_prod[k] = queProd;
            
            id_prod  = catProds.get(queProd).getId();
            cantidad = (int)( 1.0 + 49.0 * Math.random() );
            item = new wsmusicapedidos.ClsItem();
            item.setIdProd(id_prod);
            item.setCantidad(cantidad);
            listaIt.add(item);
        }
        System.out.println("-----------------------------------------------");
        System.out.println("Estresador:" + this.quienSoy + ", vez:" + vez + ", Clte:" + id_clte);
        System.out.println("-----------------------------------------------");
        for(wsmusicapedidos.ClsItem it : listaIt)
            System.out.println("Prod_id:" + it.getIdProd() + ", cantidad:" + it.getCantidad() );
        System.out.println("-----------------------------------------------");
        //
        //   Se solicita registrar el pedido en el WS
        //
        t0 =System.currentTimeMillis();
        try {
            num_pedido = procesoCompra(id_clte,listaIt);
            System.out.println("El número de pedido es:" + num_pedido);
            System.out.println("===============================================");
        } catch (Exception e){
            System.out.println("Error: " + e.toString());
        }
        t1 = System.currentTimeMillis();
        dt = t1-t0;
        return dt;
    }

    @Override
    public void cierra() 
    {
         System.out.println("El thread de stress " + this.quienSoy + " ha terminado su trabajo"); 
    }

    // =========================================================================
    //                    main para probar el pojo  
    // =========================================================================
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Pojo_VinilesVintach objServ = new Pojo_VinilesVintach();
        long dt= 0;
        objServ.prepara(25, null);
        int n_veces = args.length > 0 ? Integer.parseInt(args[0]):5;
        for( int vez = 1; vez <= n_veces; vez++)
            dt += objServ.solicitaServicio(vez);
        
        System.out.println("El tiempo que tardo el pojo con:"+ n_veces+" veces, fue de: "+ dt +" milisegundos");
        objServ.cierra();
        
    }
    // =========================================================================
    //                    Utilerías del WS 
    // =========================================================================
    private static String procesoCompra(int idClte, java.util.List<wsmusicapedidos.ClsItem> listaIt)
        throws ExcepSinExistencias_Exception{
        wsvinilesvintach.WSVinilesVintach_Service service = new wsvinilesvintach.WSVinilesVintach_Service();
        wsvinilesvintach.WSVinilesVintach port = service.getWSVinilesVintachPort();
        return port.procesoCompra(idClte, listaIt);
    }

    private static java.util.List<wsmusicapedidos.Customer> catalogoCltes() {
        wsvinilesvintach.WSVinilesVintach_Service service = new wsvinilesvintach.WSVinilesVintach_Service();
        wsvinilesvintach.WSVinilesVintach port = service.getWSVinilesVintachPort();
        return port.catalogoClientes();
    }

    private static java.util.List<wsmusicapedidos.Product> catalogoProds() {
        wsvinilesvintach.WSVinilesVintach_Service service = new wsvinilesvintach.WSVinilesVintach_Service();
        wsvinilesvintach.WSVinilesVintach port = service.getWSVinilesVintachPort();
        return port.catalogoProductos();
    }
    
}
