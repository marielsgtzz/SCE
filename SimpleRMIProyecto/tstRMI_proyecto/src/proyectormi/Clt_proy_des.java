/*
 * Cliente RMI que se conecta a un servidor de disparo para recibir un identificador (quienSoy),
 * y luego invoca un servicio web que realiza operaciones aritméticas básicas (suma, resta, 
 * multiplicación y división). El cliente registra el tiempo que tarda en realizar cada operación 
 * y acumula estadísticas como el tiempo mínimo, máximo y el promedio. Al final, estos datos se 
 * envían de vuelta al servidor para su análisis.
 */

package proyectormi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import interfazvinilesvintachestres.InterfazVinilesVintachEstres;
import pojo_vinilesvintach.Pojo_VinilesVintach;

public class Clt_proy_des {
    
    public static void main(String[] args) {
        // Objeto que representa el servicio remoto que realizará las operaciones
        interfazvinilesvintachestres.InterfazVinilesVintachEstres objServ;
        
        // Variables para el cliente
        long lngQuienSoy;    // Identificador del cliente, proporcionado por el servidor
        long sumDeltaT, sumDeltaT2, dtMin = 0, dtMax = 0;  // Variables para acumular tiempos de respuesta
        long lngCuantosMilisFaltan;  // Tiempo que el cliente debe esperar antes de comenzar
        
        // Verificar que el usuario ha pasado los argumentos necesarios: host y número de operaciones
        if (args.length < 2) {
            System.out.println("Uso: java Clt_des <host> <n>");
            return;
        }
        
        // Obtener el host del servidor y el número de operaciones a realizar
        String host = args[0];
        int n;
        
        // Convertir el segundo argumento (número de operaciones) a entero
        try {
            n = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("El segundo argumento debe ser un número entero.");
            return;
        }
        
        try {
            // Instancia del servicio
            //objServ = (interfazestrestiendita.InterfazEstresTiendita)
            //        Class.forName("pojo_cltetiendita.Pojo_ClteTiendita").newInstance();
            
            objServ = new Pojo_VinilesVintach();
            
            // Conectar con el registro RMI del servidor de disparo
            Registry registry = LocateRegistry.getRegistry(host);
            
            // Buscar el objeto remoto "ServidorDeDisparo" en el registro RMI
            IServDisparo servDisparo = (IServDisparo) registry.lookup("ServidorDeDisparo");
            
            // Obtener el identificador del cliente (quienSoy) del servidor
            lngQuienSoy = servDisparo.quienSoy();
            
            // Inicializar el servicio de operaciones con el identificador obtenido
            objServ.prepara(lngQuienSoy, null);
            
            // Obtener el tiempo que falta para que el cliente comience a invocar las operaciones
            lngCuantosMilisFaltan = servDisparo.deltaTEnMilis();
            System.out.println("Cliente " + lngQuienSoy + " faltan " + lngCuantosMilisFaltan + " milisegundos");
            
            // Inicializar las variables para acumular los tiempos de respuesta
            sumDeltaT  = 0;
            sumDeltaT2 = 0;
            
            // Hacer que el cliente espere el tiempo indicado antes de iniciar las invocaciones
            Thread.currentThread().sleep(lngCuantosMilisFaltan);
            
            // Ciclo que invoca el servicio n veces, realizando una operación aritmética aleatoria en cada iteración
            for (int i = 0; i < n; i++) {
                
                // Medir el tiempo antes de invocar el servicio
                long t0 = System.currentTimeMillis();
                
                // Invocar el servicio de operación remota y medir el tiempo que tarda
                long dt = objServ.solicitaServicio(i);
                
                // Acumular el tiempo de respuesta
                sumDeltaT  += dt;
                sumDeltaT2 += dt * dt;
                
                // Calcular el tiempo mínimo y máximo
                if (i == 0) {
                    dtMin = dt;  // La primera operación establece el mínimo
                    dtMax = dt;  // La primera operación establece el máximo
                } else {
                    if (dt < dtMin) dtMin = dt;  // Actualizar si el tiempo es menor que el mínimo registrado
                    if (dt > dtMax) dtMax = dt;  // Actualizar si el tiempo es mayor que el máximo registrado
                }
            }
            
            // Enviar las estadísticas al servidor de disparo (tiempos acumulados, máximos y mínimos)
            servDisparo.acumula(sumDeltaT, sumDeltaT2, n, dtMax, dtMin);
            
            // Cerrar el servicio después de completar todas las invocaciones
            objServ.cierra();
        } 
        catch (Exception e) {
            // Manejar cualquier excepción que ocurra durante el proceso
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
