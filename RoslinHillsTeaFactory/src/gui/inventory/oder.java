/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.inventory;

import com.mysql.cj.Session;
import com.mysql.cj.protocol.Message;
import com.sun.jdi.connect.Transport;
import com.sun.jdi.connect.spi.Connection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Sahan
 */
public class oder extends javax.swing.JPanel {

    /**
     * Creates new form oder
     */
    public oder() {
        initComponents();
        generateRequestId(); // Generate and set request ID on init
        loadMaterial();
        loadSupplier();
        loadPay();
        addComboBoxListeners();
        setDefaultDate();
        jTextField2.setEditable(false);
         jButton1.putClientProperty("JButton.buttonType", "roundRect");
    }

    private HashMap<String, String> loadmaterial = new HashMap<>();
    private HashMap<String, String> loadsupplier = new HashMap<>();
    private HashMap<String, String> loadpay = new HashMap<>();

    private String generateRequestId() {
        Random random = new Random();
        String requestId = "REQ" + System.currentTimeMillis() + random.nextInt(1000);
        jTextField2.setText(requestId);
        return requestId;
    }

    private void setDefaultDate() {
        jDateChooser1.setDate(new Date()); // Set current date as default
    }

  

    private void loadMaterial() {
        try {
            ResultSet resultSet = model.MySQL.executeSearch("SELECT * FROM `raw_materials`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                loadmaterial.put(resultSet.getString("name"), resultSet.getString("material_type_id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadSupplier() {
        try {
            ResultSet resultSet = model.MySQL.executeSearch("SELECT * FROM `suppliers`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                loadsupplier.put(resultSet.getString("name"), resultSet.getString("nic"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox3.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPay() {
        try {
            ResultSet resultSet = model.MySQL.executeSearch("SELECT * FROM `payment_method`");
            Vector<String> vector = new Vector<>();
            vector.add("Select");
            while (resultSet.next()) {
                vector.add(resultSet.getString("method"));
                loadpay.put(resultSet.getString("method"), resultSet.getString("id"));
            }
            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addComboBoxListeners() {
        ActionListener comboListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    updatePriceAndTotal();
                } catch (Exception ex) {
                    Logger.getLogger(oder.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        jComboBox2.addActionListener(comboListener);
        jComboBox3.addActionListener(comboListener);
        jSpinner1.addChangeListener(e -> {
            try {
                updatePriceAndTotal();
            } catch (Exception ex) {
                Logger.getLogger(oder.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void updatePriceAndTotal() throws SQLException, Exception {
        String selectedMaterial = (String) jComboBox2.getSelectedItem();
        String selectedSupplier = (String) jComboBox3.getSelectedItem();
        int quantity = (int) jSpinner1.getValue();

        if (selectedMaterial == null || selectedSupplier == null || selectedMaterial.equals("Select") || selectedSupplier.equals("Select")) {
            jLabel7.setText("00.00");
            jLabel9.setText("00.00");
            return;
        }

        try {
            // Get material_type_id from raw_materials
            String rawQuery = "SELECT material_type_id, price FROM raw_materials WHERE name = '" + selectedMaterial + "'";
            ResultSet rawRs = model.MySQL.executeSearch(rawQuery);
            double price = 0.0;
            int materialTypeId = -1;
            if (rawRs.next()) {
                materialTypeId = rawRs.getInt("material_type_id");
                price = rawRs.getDouble("price");
            }

            // Get supplier's material to match
            String suppQuery = "SELECT material FROM suppliers WHERE name = '" + selectedSupplier + "'";
            ResultSet suppRs = model.MySQL.executeSearch(suppQuery);
            int supplierMaterial = -1;
            if (suppRs.next()) {
                supplierMaterial = suppRs.getInt("material");
            }

            // If material matches, set the price and calculate total
            if (materialTypeId == supplierMaterial) {
                jLabel7.setText(String.format("%.2f", price));
                double total = price * quantity;
                jLabel9.setText(String.format("%.2f", total));
            } else {
                jLabel7.setText("00.00 (No match)");
                jLabel9.setText("00.00");
            }
        } catch (IOException e) {
            e.printStackTrace();
            jLabel7.setText("Error");
            jLabel9.setText("Error");
        }
    }

    private String generateBill() {
        String orderId = jTextField2.getText();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(jDateChooser1.getDate());
        String material = (String) jComboBox2.getSelectedItem();
        String supplier = (String) jComboBox3.getSelectedItem();
        String paymentMethod = (String) jComboBox1.getSelectedItem();
        int quantity = (int) jSpinner1.getValue();
        double price = Double.parseDouble(jLabel7.getText().replaceAll("[^0-9.]", ""));
        double total = Double.parseDouble(jLabel9.getText().replaceAll("[^0-9.]", ""));

        StringBuilder bill = new StringBuilder();
        bill.append("===== Order Bill =====\n");
        bill.append("Order ID: ").append(orderId).append("\n");
        bill.append("Date: ").append(date).append("\n");
        bill.append("Material: ").append(material).append("\n");
        bill.append("Supplier: ").append(supplier).append("\n");
        bill.append("Payment Method: ").append(paymentMethod).append("\n");
        bill.append("Quantity: ").append(quantity).append("\n");
        bill.append("Price per Unit: $").append(String.format("%.2f", price)).append("\n");
        bill.append("Total Amount: $").append(String.format("%.2f", total)).append("\n");
        bill.append("==================\n");
        return bill.toString();
    }

    private void saveBillToFile(String billContent) {
        try (FileWriter writer = new FileWriter("bill_" + jTextField2.getText() + ".txt")) {
            writer.write(billContent);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to save bill to file!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sendEmailToSupplier(String billContent, String supplier_Order_Bill__Rostenhill_Tea_Facto, String dear_SupplierPlease_find_attached_the_bil, String outputPath) {
        String selectedSupplier = (String) jComboBox3.getSelectedItem();
        if (selectedSupplier == null || selectedSupplier.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a supplier!", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Get supplier email
            String emailQuery = "SELECT email FROM suppliers WHERE name = '" + selectedSupplier + "'";
            ResultSet emailRs = model.MySQL.executeSearch(emailQuery);
            String supplierEmail = null;
            if (emailRs.next()) {
                supplierEmail = emailRs.getString("email");
            }

            if (supplierEmail == null || supplierEmail.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Supplier email not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Email configuration
            String host = "smtp.gmail.com";
            String username = "your-email@gmail.com"; // Replace with your email
            String password = "your-password"; // Replace with your app-specific password

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");

            // Send the email
            JOptionPane.showMessageDialog(this, "Bill sent to supplier!", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to send email!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QTY    :  ");

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel2.setText("PAYMENT METHOD");

        jTextField2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel3.setText("ORDER ID :");

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("MATERIAL   :");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("SUPPLIER :");

        jComboBox3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jSpinner1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("PRICE    :");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("00.00");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setText("Total Amount");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("00.00");

        jButton1.setBackground(new java.awt.Color(102, 102, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton1.setText("ODER");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jDateChooser1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel10.setText("DATE                     :");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel11.setText("ORDER");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(560, 560, 560)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1010, 1010, 1010)
                        .addComponent(jLabel8))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1030, 1030, 1030)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(898, 898, 898)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(83, 83, 83)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(68, 68, 68)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(73, 73, 73)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(4, 4, 4)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(7, 7, 7)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(178, 178, 178)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(139, 139, 139)
                .addComponent(jLabel8)
                .addGap(5, 5, 5)
                .addComponent(jLabel9)
                .addGap(22, 22, 22)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       // Validate inputs
    String material = (String) jComboBox2.getSelectedItem();
    String supplier = (String) jComboBox3.getSelectedItem();
    String paymentMethod = (String) jComboBox1.getSelectedItem();
    if (material == null || material.equals("Select") || supplier == null || supplier.equals("Select") || paymentMethod == null || paymentMethod.equals("Select")) {
        JOptionPane.showMessageDialog(this, "Please fill all fields!", "Warning", JOptionPane.WARNING_MESSAGE);
        return;
    }

    String orderId = jTextField2.getText();
    String date = new SimpleDateFormat("yyyy-MM-dd").format(jDateChooser1.getDate());
    String supplierNic = loadsupplier.get(supplier); // Assuming loadsupplier is a Map
    int paymentStatusId = 1; // Assuming 1 is for pending payment status
    double totalAmount = Double.parseDouble(jLabel9.getText().replaceAll("[^0-9.]", ""));
    int quantity = (int) jSpinner1.getValue();
    String rawMaterialId = loadmaterial.get(material); // Assuming loadmaterial is a Map

    try {
        // Step 1: Fetch supplier email using MySQL.executeSearch
        String emailQuery = "SELECT email FROM suppliers WHERE nic = '" + supplierNic + "'";
        ResultSet rs = model.MySQL.executeSearch(emailQuery);
       
       

        // Step 2: Insert into supplier_orders table using MySQL.executeIUD
        String ordersQuery = "INSERT INTO supplier_orders (id, date, worker, suppliers_nic, payment_status_id, total_amount) VALUES ('" +
                orderId + "', '" +
                date + "', '" +
                "sahan" + "', '" +
                supplierNic + "', " +
                paymentStatusId + ", " +
                totalAmount + ")";
        model.MySQL.executeIUD(ordersQuery);

        // Step 3: Insert into supplier_order_items table using MySQL.executeIUD
        String orderItemsQuery = "INSERT INTO supplier_order_items (qty, total_amount, supplier_orders_id, raw_materials_material_id) VALUES (" +
                quantity + ", " +
                totalAmount + ", '" +
                orderId + "', '" +
                rawMaterialId + "')";
        model.MySQL.executeIUD(orderItemsQuery);
        
        managerReports mn = new managerReports();
//        jPanel10.removeAll();
//        jPanel10.add(mn);
//        SwingUtilities.updateComponentTreeUI(jPanel10);
       
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Failed to process order: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
