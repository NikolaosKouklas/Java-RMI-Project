package mypackage;

import java.math.BigDecimal;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientManagerInterface extends Remote {
    
    public CMResponse Login(String username, String password) throws RemoteException;
    public String Logout(String session_id) throws RemoteException;
    public BigDecimal GetCredit(String session_id) throws RemoteException;
    public CMResponse GetOrdersHistory(String session_id) throws RemoteException; 
    public CMResponse RequestViewProducts(String session_id, Boolean available_products) throws RemoteException;
    public CMResponse RequestBuyProduct(String session_id, String product_code, Integer order_quantity) throws RemoteException;
}
