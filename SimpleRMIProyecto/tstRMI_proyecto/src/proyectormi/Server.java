/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//================================================================
//Código del Servidor:
//================================================================
package proyectormi;	
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
public class Server implements Hello
{
    long cuantos = 0;
    String nomMaq = System.getenv("COMPUTERNAME");
    public Server() {}
    public String sayHello() 
    {
       cuantos++;
        System.out.println("Proporcionando el servicio no. " + cuantos);
       return "Este es el servicio no. " + cuantos + " desde " + nomMaq;
    }	
    public static void main(String args[ ]) 
    {	
        String host = (args.length < 1) ? null : args[0];
        System.out.println("Server, inciando con:");
        if( args.length == 0)
            System.out.println("Sin argumentos");
        else
            for(int i = 0; i < args.length; i++)
                System.out.println("args[" + i + "]:" + args[i]);
        System.out.println("------------------------------------------");

        try 
       { 
          Server obj = new Server();
          Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);
          // Bind the remote object's stub in the registry
          Registry registry = LocateRegistry.getRegistry(host);
          registry.rebind("Hello", stub);
          System.err.println("Listo el servicio...");
      }
      catch (Exception e)
      {
         System.err.println("Server exception: " + e.toString());
         e.printStackTrace();
      }
  }
}

