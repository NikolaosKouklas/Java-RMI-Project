package mypackage;





import java.io.Serializable;
import java.util.List;


public class CMResponse  implements Serializable{
    
    private String message;
    private String session_id;
    private List<Product> products_list;
    private List<Orders> orders_list;
    
    public CMResponse() {
        
    }
    
    public CMResponse(String message) {
        
        this.message = message;
    }
    
    public CMResponse(String message, String session_id) {
        
        this.message = message;
        this.session_id = session_id;
    }
    
    public CMResponse(String message, List<Orders> orders_list) {
        
        this.message = message;
        this.orders_list = orders_list;
    }
    
    public void setMessage(String message) {
        
        this.message = message;
    }
    
    public String getMessage() {
        
        return this.message;
    }
    
    public void setSessionId(String session_id) {
        
        this.session_id = session_id;
    }
    
    public String getSessionId() {
        
        return this.session_id;
    }
    
    public void setProductsList(List<Product> products_list) {
        
        this.products_list = products_list;
    }
    
    public List<Product> getProductsList() {
        
        return this.products_list;
    }
    
    public List<Orders> getOrdersList() {
        
        return this.orders_list;
    }
}
