package mypackage;

import java.io.Serializable;
import java.util.List;


public class SMResponse implements Serializable{
    
    private String message;
    private List<Product> products_list;
    
    public SMResponse(String message) {
        
        this.message = message;
    }
    
    public SMResponse(String message, List<Product> products_list) {
        
        this.message = message;
        this.products_list = products_list;
    }
    
    public void setMessage(String message) {
        
        this.message = message;
    }
    
    public String getMessage() {
        
        return this.message;
    }
    
    public void setProductsList(List<Product> products_list) {
        
        this.products_list = products_list;
    }
    
    public List<Product> getProductsList() {
        
        return this.products_list;
    }
}
