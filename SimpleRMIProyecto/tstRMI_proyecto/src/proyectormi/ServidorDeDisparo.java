/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectormi;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author RGGH
 */
public class ServidorDeDisparo implements IServDisparo
{
    long cuantosCltes;
    long aQueHora = 0;
    long cuantosCltesHanReportado = 0;
    long lngDeltaTMax, lngDeltaTMin;
    long lngNTot;
    long lngSDeltaT, lngSDeltaT2;
    boolean banCalc = false;
    
    double dblDeltaTMedia, dblDeltaTDesvStd;
    
    
    @Override
    public synchronized long quienSoy( )
    {
        return ++cuantosCltes;
    }
    
    @Override
    public synchronized long deltaTEnMilis()  throws RemoteException
    {
        return aQueHora - System.currentTimeMillis();
    }
    
    @Override
    public synchronized void acumula( long sdt, long sdt2, long n, long dtMax, long dtMin ) throws RemoteException
    {
        cuantosCltesHanReportado++;
        lngSDeltaT  += sdt;
        lngSDeltaT2 += sdt2;
        if( lngNTot > 0 ) // ya trae algo acumulado
        {
          if( dtMax > lngDeltaTMax ) lngDeltaTMax = dtMax;
          if( dtMin < lngDeltaTMin ) lngDeltaTMin = dtMin;
        }
        else  // primera vez de esta tanda de acumulación
        {
            lngDeltaTMax = dtMax;
            lngDeltaTMin = dtMin;
        }
        lngNTot += n;
    }
    
    @Override
    public synchronized void reset( long dentroDeCuantosMilis )throws RemoteException
    {
        aQueHora = System.currentTimeMillis() + dentroDeCuantosMilis;
        cuantosCltes = 0;
        cuantosCltesHanReportado = 0;
        lngDeltaTMax = 0;
        lngDeltaTMin = 0;
        lngNTot      = 0;
        lngSDeltaT   = 0;
        lngSDeltaT2  = 0;
        banCalc      = false;
        System.out.println("ServidorDeDisparo: la fiesta empieza en " + dentroDeCuantosMilis + " milisegs");
    }
    
    @Override
    public synchronized double deltaTmedia()  throws RemoteException
    {
        if( !banCalc )
        {
           obtenEstadisticas();
           banCalc = true;
        }
        return  dblDeltaTMedia;
    }
    
    @Override
    public synchronized double deltaTStdDev() throws RemoteException
    {
        if( !banCalc )
        {
           obtenEstadisticas();
           banCalc = true;
        }
        return dblDeltaTDesvStd;
    }
    
    @Override
    public synchronized long   deltaTMax()    throws RemoteException
    {
        return lngDeltaTMax;
    }
    
    @Override
    public synchronized long   deltTMin()     throws RemoteException
    {
        return lngDeltaTMin;
    }
    
    private synchronized void obtenEstadisticas()
    {
        dblDeltaTMedia   = lngSDeltaT / lngNTot;
        dblDeltaTDesvStd = Math.sqrt((1.0/(lngNTot-1)) * ( lngSDeltaT2 - lngNTot * dblDeltaTMedia * dblDeltaTMedia ));
    }
    
    @Override
    public long cuantosCltes() throws RemoteException 
    {
        return cuantosCltesHanReportado;
    }

    @Override
    public long cuantosServicios() throws RemoteException 
    {
        return lngNTot;
    }

    
    // ======================= Método principal =======================
    
    public static void main(String args[ ]) 
    {	
        String host = (args.length < 1) ? null : args[0];
        System.out.println("ServidorDeDisparo, inciando con:");
        if( args.length == 0)
            System.out.println("Sin argumentos");
        else
            for(int i = 0; i < args.length; i++)
                System.out.println("args[" + i + "]:" + args[i]);
        System.out.println("------------------------------------------");
        try 
       { 
          ServidorDeDisparo obj = new ServidorDeDisparo();
          IServDisparo stub = (IServDisparo) UnicastRemoteObject.exportObject(obj, 0);
          // Bind the remote object's stub in the registry
          Registry registry = LocateRegistry.getRegistry(host);
          registry.rebind("ServidorDeDisparo", stub);
          System.err.println("Servidor de Disparo listo...");
      }
      catch (Exception e)
      {
         System.err.println("Server exception: " + e.toString());
         e.printStackTrace();
      }
  }

    
}
