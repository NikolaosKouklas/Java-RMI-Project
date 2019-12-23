package mypackage;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StorageManagerInterface extends Remote {
    
    public SMResponse ViewProducts(Boolean available_product) throws RemoteException;
    public SMResponse BuyProduct(String username, String product_code, Integer order_quantity) throws RemoteException;
}
