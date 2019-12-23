package mypackage;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UI extends JFrame implements CommunicationProtocolConstants {
    
    private String username, password, session_id;
    private final ClientManagerInterface clientManagerInterface;
    private static final String URL = "//" + CLIENT_MANAGER_ADDRESS + ":" + CLIENT_MANAGER_PORT + "/" + CLIENT_MANAGER_NAME;
    
    public UI() throws Exception {
        
        //Registry registry = LocateRegistry.getRegistry(CLIENT_MANAGER_ADDRESS, CLIENT_MANAGER_PORT, null);
        Registry registry = LocateRegistry.getRegistry(CLIENT_MANAGER_ADDRESS, CLIENT_MANAGER_PORT, new SslRMIClientSocketFactory());
        clientManagerInterface = (ClientManagerInterface) registry.lookup(URL);
        //clientManagerInterface = (ClientManagerInterface)Naming.lookup(URL);
        
        System.out.println("Client Connected to Client Manager ...");
        
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane = new javax.swing.JLayeredPane();
        loginPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        usernameField = new javax.swing.JTextField();
        loginButton = new javax.swing.JButton();
        passwordField = new javax.swing.JPasswordField();
        mainPanel = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        productCodeTextField = new javax.swing.JTextField();
        amountTextField = new javax.swing.JTextField();
        placeOrderButton = new javax.swing.JButton();
        viewProductsButton = new javax.swing.JButton();
        viewCreditButton = new javax.swing.JButton();
        viewOrderButton = new javax.swing.JButton();
        welcomeLabel = new javax.swing.JLabel();
        userLabel = new javax.swing.JLabel();
        logoutButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Online Store");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                WindowClosing(evt);
            }
        });

        jLayeredPane.setLayout(new java.awt.CardLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 204, 204));
        jLabel1.setText("Welcome to Our Online Store");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Username:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Password:");

        usernameField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        loginButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        loginButton.setText("Login");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginButtonActionPerformed(evt);
            }
        });

        passwordField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout loginPanelLayout = new javax.swing.GroupLayout(loginPanel);
        loginPanel.setLayout(loginPanelLayout);
        loginPanelLayout.setHorizontalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap(187, Short.MAX_VALUE)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(loginPanelLayout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(33, 33, 33)
                            .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(loginButton)
                                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(117, 117, 117))
        );
        loginPanelLayout.setVerticalGroup(
            loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(54, 54, 54)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(usernameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(loginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addComponent(loginButton)
                .addContainerGap(225, Short.MAX_VALUE))
        );

        jLayeredPane.add(loginPanel, "card2");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Product Code:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Amount:");

        productCodeTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        amountTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        placeOrderButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        placeOrderButton.setText("Place Order");
        placeOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                placeOrderButtonActionPerformed(evt);
            }
        });

        viewProductsButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        viewProductsButton.setText("View Products");
        viewProductsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewProductsButtonActionPerformed(evt);
            }
        });

        viewCreditButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        viewCreditButton.setText("View Credits");
        viewCreditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewCreditButtonActionPerformed(evt);
            }
        });

        viewOrderButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        viewOrderButton.setText("View Orders");
        viewOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewOrderButtonActionPerformed(evt);
            }
        });

        welcomeLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        welcomeLabel.setForeground(new java.awt.Color(0, 204, 204));
        welcomeLabel.setText("Welcome");

        userLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        userLabel.setForeground(new java.awt.Color(0, 204, 204));
        userLabel.setText("user");

        logoutButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        logoutButton.setText("Logout");
        logoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(welcomeLabel)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addGap(36, 36, 36)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(amountTextField)
                            .addComponent(productCodeTextField)
                            .addComponent(placeOrderButton, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))))
                .addGap(18, 18, 18)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addComponent(userLabel)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                        .addGap(0, 111, Short.MAX_VALUE)
                        .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(logoutButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewOrderButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewProductsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(viewCreditButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(34, 34, 34))))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(welcomeLabel)
                    .addComponent(userLabel))
                .addGap(68, 68, 68)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productCodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(viewProductsButton))
                .addGap(35, 35, 35)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(amountTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(viewCreditButton))
                .addGap(33, 33, 33)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewOrderButton)
                    .addComponent(placeOrderButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 118, Short.MAX_VALUE)
                .addComponent(logoutButton)
                .addGap(51, 51, 51))
        );

        jLayeredPane.add(mainPanel, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jLayeredPane))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginButtonActionPerformed
        
        if(usernameField.getText().isEmpty() || String.valueOf(passwordField.getPassword()).isEmpty()) {
            
            JOptionPane.showMessageDialog(null, "Please enter your username and password", "Blank field",  JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            
            CMResponse response = clientManagerInterface.Login(usernameField.getText(), String.valueOf(passwordField.getPassword()));
            switch(response.getMessage()) {
                
                case SUCCESS:
                    JOptionPane.showMessageDialog(null,"You were successfully logged in to the Online Store","Successful Authentication",JOptionPane.INFORMATION_MESSAGE);
                    session_id = response.getSessionId();
                    switchToMainPanel();
                    break;
                case CONNECTED:
                    JOptionPane.showMessageDialog(null,"You are already connected to the Online Store","Failed authentication",JOptionPane.WARNING_MESSAGE);
                    break;
                case FAIL:
                    JOptionPane.showMessageDialog(null,"The username or password you entered is incorrect","Failed authentication",JOptionPane.WARNING_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"A problem has occurred, please try again later","Failed authentication",JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (RemoteException ex) {
            
            JOptionPane.showMessageDialog(null,"A problem has occurred, please try again later","Failed authentication",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_loginButtonActionPerformed

    private void WindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_WindowClosing
        
        if(session_id == null) {
            
            System.out.println("Client Shutdown");
            return;
        }
        
        try {
            String response = clientManagerInterface.Logout(session_id);
            switch(response) {
                
                case SUCCESS:
                    JOptionPane.showMessageDialog(null,"You were successfully logged out from the Online Store","Successful Logout",JOptionPane.INFORMATION_MESSAGE);
                    break;
                case FAIL:
                    JOptionPane.showMessageDialog(null,"The provided session id does not exist","Logout Failed",JOptionPane.WARNING_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"A problem has occurred","Logout Failed",JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (RemoteException ex) {
            JOptionPane.showMessageDialog(null,"A problem has occurred","Logout Failed",JOptionPane.ERROR_MESSAGE);
        }
        
        System.out.println("Client Shutdown");
    }//GEN-LAST:event_WindowClosing

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutButtonActionPerformed
        
        try {
            String response = clientManagerInterface.Logout(session_id);
            
            switch(response) {
                case SUCCESS:
                    JOptionPane.showMessageDialog(null,"You were successfully logged out from the Online Store","Successful Logout",JOptionPane.INFORMATION_MESSAGE);
                    switchToLoginPanel();
                    break;
                case FAIL:
                    JOptionPane.showMessageDialog(null,"The provided session id does not exist","Logout Failed",JOptionPane.WARNING_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"A problem has occurred","Logout Failed",JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (RemoteException ex) {
            JOptionPane.showMessageDialog(null,"A problem has occurred","Logout Failed",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_logoutButtonActionPerformed

    private void viewCreditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewCreditButtonActionPerformed
        
        try {
            
            BigDecimal credit = clientManagerInterface.GetCredit(session_id);
            if(credit != null) {
                JOptionPane.showMessageDialog(null,"Your credit is " + credit.toPlainString() + " Euros","View Credit",JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (RemoteException ex) {
            
            JOptionPane.showMessageDialog(null,"A problem has occurred","View Credit Failed",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_viewCreditButtonActionPerformed

    private void viewOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewOrderButtonActionPerformed
        
        try {
            CMResponse response = clientManagerInterface.GetOrdersHistory(session_id);
            
            switch(response.getMessage()) {
                case OK:
                    new ViewOrdersJFrame(response.getOrdersList()).setVisible(true);
                    break;
                case SESSION_ID_NOT_FOUND:
                    JOptionPane.showMessageDialog(null,"The provided session id was not found","View Orders Failed",JOptionPane.WARNING_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Unknown response message","View Orders Failed",JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (RemoteException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null,"A problem has occurred","View Orders Failed",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_viewOrderButtonActionPerformed

    private void viewProductsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewProductsButtonActionPerformed
        
        try {
            CMResponse response = clientManagerInterface.RequestViewProducts(session_id, true);
            
            switch(response.getMessage()) {
                case OK:
                    new ViewProductsJFrame(response.getProductsList()).setVisible(true);
                    break;
                case SESSION_ID_NOT_FOUND:
                    JOptionPane.showMessageDialog(null,"The provided session id was not found","View Products Failed",JOptionPane.WARNING_MESSAGE);
                    break;
                case UNKNOWN_RESPONSE_MESSAGE:
                    JOptionPane.showMessageDialog(null,"Unknown response message from Storage Manager","View Products Failed",JOptionPane.WARNING_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Unknown response message from Client Manager","View Products Failed",JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (RemoteException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null,"A problem has occurred","View Products Failed",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_viewProductsButtonActionPerformed

    private void placeOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_placeOrderButtonActionPerformed
        
        if(productCodeTextField.getText().isEmpty() || amountTextField.getText().isEmpty()) {
            
            JOptionPane.showMessageDialog(null,"Please insert a product code and an amount","Empty Field",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(!isInteger(amountTextField.getText()) || Integer.parseInt(amountTextField.getText()) <= 0) {
            
            JOptionPane.showMessageDialog(null,"Please insert a positive integer number for amount","Amount Field Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            
            CMResponse response = clientManagerInterface.RequestBuyProduct(session_id, productCodeTextField.getText(), Integer.parseInt(amountTextField.getText()));
            
            switch(response.getMessage()) {
                case OK:
                    JOptionPane.showMessageDialog(null,"Successful purchase of products","Successful Purchase",JOptionPane.INFORMATION_MESSAGE);
                    productCodeTextField.setText(null);
                    amountTextField.setText(null);
                    break;
                case SESSION_ID_NOT_FOUND:
                    JOptionPane.showMessageDialog(null,"Session id not found","Purchase Failed",JOptionPane.ERROR_MESSAGE);
                    break;
                case PRODUCT_NOT_FOUND:
                    JOptionPane.showMessageDialog(null,"Incorrect product code","Purchase Failed",JOptionPane.WARNING_MESSAGE);
                    break;
                case INSUFFICIENT_BALANCE:
                    JOptionPane.showMessageDialog(null,"Insufficient cash balance","Purchase Failed",JOptionPane.WARNING_MESSAGE);
                    break;
                case INSUFFICIENT_QUANTITY:
                    JOptionPane.showMessageDialog(null,"Insufficient quantity available","Purchase Failed",JOptionPane.WARNING_MESSAGE);
                    break;
                case USER_NOT_FOUND:
                    JOptionPane.showMessageDialog(null,"User not found","Purchase Failed",JOptionPane.ERROR_MESSAGE);
                    break;
                case FAIL:
                    JOptionPane.showMessageDialog(null,"Failed to create order","Purchase Failed",JOptionPane.ERROR_MESSAGE);
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Unknown response message","Purchase Failed",JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (RemoteException ex) {
            
            System.out.println(ex);
            JOptionPane.showMessageDialog(null,"A problem has occurred","Place Order Failed",JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_placeOrderButtonActionPerformed

    private void switchToMainPanel() {
        
        username = usernameField.getText();
        password = String.valueOf(passwordField.getPassword());
        
        userLabel.setText(username);
        jLayeredPane.removeAll();
        jLayeredPane.add(mainPanel);
        jLayeredPane.repaint();
        jLayeredPane.revalidate();
    }
    
    private void switchToLoginPanel() {
        
        username = null;
        password = null;
        session_id = null;
        
        usernameField.setText(null);
        passwordField.setText(null);
        productCodeTextField.setText(null);
        amountTextField.setText(null);
        
        jLayeredPane.removeAll();
        jLayeredPane.add(loginPanel);
        jLayeredPane.repaint();
        jLayeredPane.revalidate();
    }
    
    public static boolean isInteger(String s) {
        
        try {
            Integer.parseInt(s); 
        }
        catch(NumberFormatException ex) { 
            return false; 
        }
        
        return true;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amountTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLayeredPane jLayeredPane;
    private javax.swing.JButton loginButton;
    private javax.swing.JPanel loginPanel;
    private javax.swing.JButton logoutButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JButton placeOrderButton;
    private javax.swing.JTextField productCodeTextField;
    private javax.swing.JLabel userLabel;
    private javax.swing.JTextField usernameField;
    private javax.swing.JButton viewCreditButton;
    private javax.swing.JButton viewOrderButton;
    private javax.swing.JButton viewProductsButton;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
