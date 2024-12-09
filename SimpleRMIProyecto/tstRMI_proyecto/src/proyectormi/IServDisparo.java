/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectormi;

/**
 *
 * @author RGGH
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
public interface IServDisparo extends Remote 
{
    public long    quienSoy( )      throws RemoteException;
    public long   deltaTEnMilis()  throws RemoteException;
    public void   acumula( long sdt, long sdt2, long n, long dtMax, long dtMin ) throws RemoteException;
    
    public void   reset( long dentroDeCuantosMilis )throws RemoteException;
    public double deltaTmedia()      throws RemoteException;
    public double deltaTStdDev()     throws RemoteException;
    public long   deltaTMax()        throws RemoteException;
    public long   deltTMin()         throws RemoteException;
    public long   cuantosCltes()     throws RemoteException;
    public long   cuantosServicios() throws RemoteException;
}    

