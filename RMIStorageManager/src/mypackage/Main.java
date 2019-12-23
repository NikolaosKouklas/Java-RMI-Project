package mypackage;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;

public class Main implements CommunicationProtocolConstants {
    
    private static final String STORAGE_MANAGER_URL = "//" + STORAGE_MANAGER_ADDRESS + ":" + STORAGE_MANAGER_PORT + "/" + STORAGE_MANAGER_NAME;
    
    public static void main(String[] args) {
        
        try {
            
            System.setProperty("javax.net.ssl.keyStore", "test-keystore");
            System.setProperty("javax.net.ssl.keyStorePassword", "test-keystore-pwd");
            
            System.setProperty("javax.net.ssl.trustStore", "test-truststore");
            System.setProperty("javax.net.ssl.trustStorePassword", "test-truststore-pwd");
            
            System.setProperty("java.security.policy", "storagemanager.policy");
            System.setProperty("java.rmi.server.useCodebaseOnly", "true");
            
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            
            /*LocateRegistry.createRegistry(STORAGE_MANAGER_PORT);
            Naming.rebind(STORAGE_MANAGER_URL, new StorageManager());*/
            
            Registry registry = LocateRegistry.createRegistry(STORAGE_MANAGER_PORT, new SslRMIClientSocketFactory(), new SslRMIServerSocketFactory());
            //Registry registry = LocateRegistry.createRegistry(STORAGE_MANAGER_PORT, null, null);
            registry.rebind(STORAGE_MANAGER_URL, new StorageManager());
            
            System.out.println("Storage Manager is ready ...");
            
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                
                try {
                    registry.unbind(STORAGE_MANAGER_URL);
                }
                catch (RemoteException | NotBoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.out.println("Storage Manager's Shutdown.");
            }));
            
            new UI().setVisible(true);
        }
        catch (RemoteException ex) {
            
            System.out.println(ex);
        }
    }
    
}
