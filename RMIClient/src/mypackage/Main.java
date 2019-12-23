package mypackage;

public class Main {
    
    public static void main(String[] args) {
        
        try {
            
            System.setProperty("javax.net.ssl.keyStore", "test-keystore");
            System.setProperty("javax.net.ssl.keyStorePassword", "test-keystore-pwd");
            
            System.setProperty("javax.net.ssl.trustStore", "test-truststore");
            System.setProperty("javax.net.ssl.trustStorePassword", "test-truststore-pwd");
            
            System.setProperty("java.security.policy", "client.policy");
            System.setProperty("java.rmi.server.useCodebaseOnly", "true");
            
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            
            new UI().setVisible(true);
            System.out.println("Client Started ...");
        }
        catch (Exception ex) {
            
            System.out.println(ex);
        }
    }
}
