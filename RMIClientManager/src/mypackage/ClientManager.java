package mypackage;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;

public class ClientManager extends UnicastRemoteObject implements ClientManagerInterface, CommunicationProtocolConstants {
    
    private final StorageManagerInterface storageManagerInterface;
    private final Hashtable<String,String> connectedClients;
    
    private static final String STORAGE_MANAGER_URL = "//" + STORAGE_MANAGER_ADDRESS + ":" + STORAGE_MANAGER_PORT + "/" + STORAGE_MANAGER_NAME;
    
    public ClientManager() throws RemoteException, NotBoundException, MalformedURLException {
        
        //super();
        super(CLIENT_MANAGER_PORT, new SslRMIClientSocketFactory(), new SslRMIServerSocketFactory());
        
        Registry registry = LocateRegistry.getRegistry(STORAGE_MANAGER_ADDRESS, STORAGE_MANAGER_PORT, new SslRMIClientSocketFactory());
        //Registry registry = LocateRegistry.getRegistry(STORAGE_MANAGER_ADDRESS, STORAGE_MANAGER_PORT, null);
        storageManagerInterface = (StorageManagerInterface) registry.lookup(STORAGE_MANAGER_URL);
        //storageManagerInterface = (StorageManagerInterface) Naming.lookup(STORAGE_MANAGER_URL);
        
        System.out.println("Client Manager connected to Storage Manager ...");
        connectedClients = new Hashtable<String,String>();
    }
    
    @Override
    public CMResponse Login(String username, String password) throws RemoteException {
        
        if(connectedClients.containsValue(username)) {
             
            return new CMResponse(CONNECTED);
        }
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RMIClientManagerPU");
        ClientJpaController client_controller = new ClientJpaController(emf);
        Client client = client_controller.findByUsername(username);
        emf.close();
        
        if(client != null && client.getUsername().equals(username) && client.getPassword().equals(password)) {
            
            String session_id = UUID.randomUUID().toString() + "" + UUID.randomUUID().toString();
            connectedClients.put(session_id, username);
            return new CMResponse(SUCCESS, session_id);
        }
        
        return new CMResponse(FAIL);
    }

    @Override
    public String Logout(String session_id) throws RemoteException {
        
        if(connectedClients.containsKey(session_id)) {
            
            connectedClients.remove(session_id);
            return SUCCESS;
        }
        
        return FAIL;
    }

    @Override
    public BigDecimal GetCredit(String session_id) throws RemoteException {
        
        if(connectedClients.containsKey(session_id)) {
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("RMIClientManagerPU");
            ClientJpaController client_controller = new ClientJpaController(emf);
            Client client = client_controller.findByUsername(connectedClients.get(session_id));
            emf.close();
            return client.getCredit();
        }
        
        return null;
    }

    @Override
    public CMResponse GetOrdersHistory(String session_id) throws RemoteException {
        
        if(connectedClients.containsKey(session_id)) {
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("RMIClientManagerPU");
            ClientJpaController client_controller = new ClientJpaController(emf);
            Client client = client_controller.findByUsername(connectedClients.get(session_id));
            OrdersJpaController orders_controller = new OrdersJpaController(emf);
            List<Orders> order_history = orders_controller.findByClient(client);
            emf.close();
            return new CMResponse(OK, order_history);
        }
        
        return new CMResponse(SESSION_ID_NOT_FOUND);
    }

    @Override
    public CMResponse RequestViewProducts(String session_id, Boolean available_products) throws RemoteException {
        
        if(connectedClients.containsKey(session_id)) {
            
            SMResponse sm_response = storageManagerInterface.ViewProducts(true);
            
            CMResponse cm_response = new CMResponse();
            
            switch(sm_response.getMessage()) {
                
                case OK:
                    cm_response.setMessage(OK);
                    cm_response.setProductsList(sm_response.getProductsList());
                    break;
                    
                default:
                    cm_response.setMessage(UNKNOWN_RESPONSE_MESSAGE);
            }
            
            return cm_response;
        }
        
        return new CMResponse(SESSION_ID_NOT_FOUND);
    }

    @Override
    public CMResponse RequestBuyProduct(String session_id, String product_code, Integer order_quantity) throws RemoteException {
        
        if(connectedClients.containsKey(session_id)) {
            
            SMResponse sm_response = storageManagerInterface.BuyProduct(connectedClients.get(session_id), product_code, order_quantity);
            
            return new CMResponse(sm_response.getMessage());
        }
        
        return new CMResponse(SESSION_ID_NOT_FOUND);
    }
}
