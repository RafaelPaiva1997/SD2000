package rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public abstract class RMI {
    public static RMIInterface rmi;
    private static String ip = "127.0.0.1";
    private static int port = 9090;

    public static void connect() throws RemoteException, NotBoundException {
        rmi = (RMIInterface) LocateRegistry.getRegistry(ip, port).lookup("rmi-object");
    }
}
