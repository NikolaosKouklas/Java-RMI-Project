package mypackage;



public interface CommunicationProtocolConstants {
    
    public static final String SUCCESS = "SUCCESS";
    public static final String CONNECTED = "CONNECTED";
    public static final String FAIL = "FAIL";
    
    public static final String SESSION_ID_NOT_FOUND = "SESSION_ID_NOT_FOUND";
    public static final String UNKNOWN_RESPONSE_MESSAGE = "UNKNOWN_RESPONSE_MESSAGE";
    
    public static final String OK="OK";
    public static final String PRODUCT_NOT_FOUND="PRODUCT_NOT_FOUND";
    public static final String USER_NOT_FOUND="USER_NOT_FOUND";
    public static final String INSUFFICIENT_BALANCE="INSUFFICIENT_BALANCE";
    public static final String INSUFFICIENT_QUANTITY="INSUFFICIENT_QUANTITY";
    
    public static final String CLIENT_MANAGER_ADDRESS = "127.0.0.1";
    public static final String STORAGE_MANAGER_ADDRESS = "127.0.0.1";
    public static final int CLIENT_MANAGER_PORT = 2000;
    public static final int STORAGE_MANAGER_PORT = 2001;
    public static final String CLIENT_MANAGER_NAME = "ClientManager";
    public static final String STORAGE_MANAGER_NAME = "StorageManager";
}
