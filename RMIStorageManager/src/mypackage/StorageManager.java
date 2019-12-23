package mypackage;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;

public class StorageManager extends UnicastRemoteObject implements StorageManagerInterface, CommunicationProtocolConstants {
    
    public StorageManager() throws RemoteException {
        
        //super();
        super(STORAGE_MANAGER_PORT, new SslRMIClientSocketFactory(), new SslRMIServerSocketFactory());
    }

    @Override
    public SMResponse ViewProducts(Boolean available_products) throws RemoteException {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RMIStorageManagerPU");
        ProductJpaController product_controller = new ProductJpaController(emf);
        
        List<Product> products_list = available_products ? product_controller.getAvailableProducts() : product_controller.findProductEntities();
        emf.close();
        return new SMResponse(OK, products_list);
    }
    
    
    @Override
    public synchronized SMResponse BuyProduct(String username, String product_code, Integer order_quantity) throws RemoteException {
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("RMIStorageManagerPU");
       
        ProductJpaController product_controller = new ProductJpaController(emf);
        Product product = product_controller.findProductByCode(product_code);
        
        if(product != null) {
            
            ClientJpaController client_controller = new ClientJpaController(emf);
            Client client = client_controller.findClientByUsername(username);
            
            if(client != null ) {
                
                Integer product_quantity = product.getQuantity();
                BigDecimal client_credit = client.getCredit();
                BigDecimal order_price = product.getPrice().multiply(new BigDecimal(order_quantity));
                
                if(order_quantity > product_quantity) {
                    
                    emf.close();
                    return new SMResponse(INSUFFICIENT_QUANTITY);
                }
                else if(client_credit.compareTo(order_price) <= 0) {
                    
                    emf.close();
                    return new SMResponse(INSUFFICIENT_BALANCE);
                }
                else {
                    
                    product.setQuantity(product_quantity - order_quantity);
                    client.setCredit(client_credit.subtract(order_price));
                    
                    try {
                        product_controller.edit(product);
                        client_controller.edit(client);
                        Orders order = new Orders();
                        order.setClient(client);
                        order.setProduct(product);
                        order.setQuantity(order_quantity);
                        OrdersJpaController orders_controller = new OrdersJpaController(emf);
                        orders_controller.create(order);
                    }
                    catch (Exception ex) {
                        
                        emf.close();
                        return new SMResponse(FAIL);
                    }
                    
                    emf.close();
                    return new SMResponse(OK);
                }
            }
            else {
                
                emf.close();
                return new SMResponse(USER_NOT_FOUND);
            }
        }
        
        emf.close();
        return new SMResponse(PRODUCT_NOT_FOUND);
    }
    
    private void insertDelay() {
        long TimeOne = java.lang.System.currentTimeMillis();
        do {} while ((TimeOne+(5*1000))>java.lang.System.currentTimeMillis());
    }
}
