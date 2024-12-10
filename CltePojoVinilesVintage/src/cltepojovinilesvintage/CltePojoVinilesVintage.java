/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cltepojovinilesvintage;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.Dispatch;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Service;
import java.io.StringReader;
import web.GestionaPedidoOut;
import web.VinilesVintageWSDLOperationFault;


/**
 *
 * @author juanp
 */
public class CltePojoVinilesVintage implements interfazvvbpelestress.interfazVVBpelEstress{
    
    long quienSoy;
    web.VinilesVintageWSDLPortType port;
    int num_cltes;
    int num_prods;

    @Override
    public boolean prepara(long quienSoy, String host) {
        this.quienSoy = quienSoy;
        web.CAVinilesVintageService1 service = new web.CAVinilesVintageService1();
        port = service.getCasaPort1();
        host = null;
        num_cltes     = 4; //listaCltes.size();
        num_prods     = 8; //listaProds.size();
        return true;
    }
    
    @Override
    public long solicitaServicio(int vez) throws Exception {
        long t0,t1=0,dt=0;
        web.GestionaPedido datos_pedido = new web.GestionaPedido();
        web.GestionaPedidoOut res;
        
        java.util.List<web.Clte>  listaIt = datos_pedido.getListaIt();
        
        String respuesta;
        int id_clte;
        int num_it;
        int id_prod,cantidad;

        int queClte,queProd = 0;
       
        web.Clte item;
        
        queClte = (int) ( 1 + num_cltes * Math.random());
        id_clte = queClte; //listaCltes.get(queClte).getId();

        num_it = (int) (1.0 + 10.0 * Math.random());
        num_it = num_it <= this.num_prods ? num_it : this.num_prods;
        
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
              queProd = (int) (1 +  num_prods * Math.random());
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
            
            id_prod  = queProd; //listaProds.get(queProd).getId();
            cantidad = (int)( 5.0 + 100 * Math.random() );
            item = new web.Clte();
            item.setIdProd(id_prod);
            item.setCantidad(cantidad);
            listaIt.add(item);
        }
        System.out.println("-----------------------------------------------");
        System.out.println("Estresador:" + this.quienSoy + ", vez:" + vez + ", Clte:" + id_clte);
        System.out.println("-----------------------------------------------");
        for(web.Clte it : listaIt)
            System.out.println("Prod_id:" + it.getIdProd() + ", cantidad:" + it.getCantidad() );
        System.out.println("-----------------------------------------------");
        //
        //   Se solicita registrar el pedido en el WS
        //
        
        datos_pedido.setIdClte(queClte);
        t0=System.currentTimeMillis();
        try {
            //sol_res = solicitudOperation(sol_req);
            res = port.vinilesVintageWSDLOperation(datos_pedido);
            respuesta = res.getReturn();//altaPedido(id_clte,listaIt);
            System.out.println(respuesta);
        } catch (VinilesVintageWSDLOperationFault ex) {
            System.out.println(" ex:" + ex.getFaultInfo().getFault());
        }
        t1=System.currentTimeMillis();
        dt=t1-t0;
        return dt;
    }

    @Override
    public void cierra() {
        System.out.println("El thread de stress " + this.quienSoy + " ha terminado su trabajo");
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        CltePojoVinilesVintage objServ = new CltePojoVinilesVintage();
        long dt=0;
        objServ.prepara(25, null);
        int n_veces = args.length > 0 ? Integer.parseInt(args[0]):5;
        for( int vez = 1; vez <= n_veces; vez++)
            dt += objServ.solicitaServicio(vez);
        System.out.println("El tiempo que tardo el pojo con:"+ n_veces+" veces, fue de: "+ dt +" milisegundos");
        objServ.cierra();
    }
    
    
    
    private static GestionaPedidoOut vinilesVintageWSDLOperation(web.GestionaPedido part1) throws VinilesVintageWSDLOperationFault {
        web.CAVinilesVintageService1 service = new web.CAVinilesVintageService1();
        web.VinilesVintageWSDLPortType port = service.getCasaPort1();
        return port.vinilesVintageWSDLOperation(part1);
    }   
    
}
