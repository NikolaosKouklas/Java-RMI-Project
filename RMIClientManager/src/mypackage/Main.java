package mypackage;

import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;

public class Main implements CommunicationProtocolConstants {
    
    private static final String CLIENT_MANAGER_URL = "//" + CLIENT_MANAGER_ADDRESS + ":" + CLIENT_MANAGER_PORT + "/" + CLIENT_MANAGER_NAME;
    
    public static void main(String[] args) {
        
        try {
            
            System.setProperty("javax.net.ssl.keyStore", "test-keystore");
            System.setProperty("javax.net.ssl.keyStorePassword", "test-keystore-pwd");
            
            System.setProperty("javax.net.ssl.trustStore", "test-truststore");
            System.setProperty("javax.net.ssl.trustStorePassword", "test-truststore-pwd");
            
            System.setProperty("java.security.policy", "clientmanager.policy");
            System.setProperty("java.rmi.server.useCodebaseOnly", "true");
            
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            
            /*LocateRegistry.createRegistry(CLIENT_MANAGER_PORT);
            Naming.rebind(CLIENT_MANAGER_URL, new ClientManager());*/
            
            //Registry registry = LocateRegistry.createRegistry(CLIENT_MANAGER_PORT, null, null);
            Registry registry = LocateRegistry.createRegistry(CLIENT_MANAGER_PORT, new SslRMIClientSocketFactory(), new SslRMIServerSocketFactory());
            registry.rebind(CLIENT_MANAGER_URL, new ClientManager());
            
            System.out.println("Client Manager is ready ...");
            
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                
                try {
                    registry.unbind(CLIENT_MANAGER_URL);
                }
                catch (RemoteException | NotBoundException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                System.out.println("Client Manager's Shutdown.");
            }));
            
            new UI().setVisible(true);
        }
        catch (RemoteException | MalformedURLException | NotBoundException ex) {
            
            System.out.println(ex);
        }
    }
    
}
