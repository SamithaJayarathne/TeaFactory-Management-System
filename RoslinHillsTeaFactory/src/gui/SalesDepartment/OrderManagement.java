/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.SalesDepartment;

import com.formdev.flatlaf.FlatClientProperties;
import com.sun.mail.util.MailConnectException;
import gui.home;
import java.awt.TextField;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.MySQL;
import model.OrderItem;
import java.sql.ResultSet;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author Lenovo
 */
public class OrderManagement extends javax.swing.JPanel {

    Product product;

    HashMap<String, Object> productdetails_Map = new HashMap<>();

    public static OrderManagement om = new OrderManagement();

    private void Product(Product product) {
        this.product = product;
    }
    HashMap<String, OrderItem> order_Map;

    OrderItem orderItem;

    public OrderManagement() {
        initComponents();
        design();
//        this.OR = (Order) jpanel;
        if (product != null) {

        }
        All_Order order = new All_Order();
        order.tableCenter(jTable1, jLabel1);

    }

    String id;

    public String setID(String id) {
        this.id = id;
        return id;
    }

    String id2 = id;
    DefaultTableModel model;

    private void reset() {
        model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        this.orderItem = null;
        order_Map = null;

    }

    private void design() {

        jTextField1.putClientProperty("JComponent.roundRect", true);
        jFormattedTextField4.putClientProperty("JComponent.roundRect", true);
        jTextField2.putClientProperty("JComponent.roundRect", true);
        jFormattedTextField1.putClientProperty("JComponent.roundRect", true);
        jFormattedTextField2.putClientProperty("JComponent.roundRect", true);
        jTextField3.putClientProperty("JComponent.roundRect", true);
        jTextField5.putClientProperty("JComponent.roundRect", true);
        jTextField4.putClientProperty("JComponent.roundRect", true);
        jFormattedTextField3.putClientProperty("JComponent.roundRect", true);

        jPanel1.putClientProperty(FlatClientProperties.STYLE, "arc: 25");
        jButton3.putClientProperty("JButton.buttonType", "roundRect");
        jButton4.putClientProperty("JButton.buttonType", "roundRect");
        jButton5.putClientProperty("JButton.buttonType", "roundRect");
        jButton7.putClientProperty("JButton.buttonType", "roundRect");
        jButton6.putClientProperty("JButton.buttonType", "roundRect");

        jFormattedTextField1.setEnabled(false);
        jTextField5.setEnabled(false);
        jTextField1.setEnabled(false);
        jTextField2.setEnabled(false);
        jTextField3.setEnabled(false);
        jTextField4.setEnabled(false);
        jFormattedTextField4.setEnabled(false);
        jFormattedTextField3.setEditable(false);
    }
    double total;

    private void loadTable() {
        model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        total = 0;

        for (OrderItem orderItem : order_Map.values()) {
            Vector vector = new Vector();
            vector.add(orderItem.getproductId());
            vector.add(orderItem.getProductName());
            vector.add(orderItem.getProductCategory());
            vector.add(orderItem.getUnitPrice());
            vector.add(orderItem.getQty());

            Double itemTotal = orderItem.getQty() * orderItem.getUnitPrice();
            total += itemTotal;
            vector.add(String.valueOf(itemTotal));
            model.addRow(vector);

            System.out.println(orderItem.getProductName());
        }
        jFormattedTextField3.setText(String.valueOf(total));

    }

    
    
    public JTextField getProductName() {
        return jTextField2;

    }

    public JFormattedTextField getTotal() {
        return jFormattedTextField3;

    }

    public Double getTot() {
        String total2 = jFormattedTextField3.getText();
        Double tot = Double.parseDouble(total2);
        return tot;

    }

    public JTextField getProductId() {
        return jTextField4;
    }

    public JTable getTable() {
        return jTable1;
    }

    public JTextField getCategory() {
        return jTextField3;
    }

    public JTextField getID() {
        return jTextField4;
    }

    public JFormattedTextField getUnitPrice() {
        return jFormattedTextField4;
    }

    public JFormattedTextField getWantqty() {
        return jFormattedTextField2;
    }

    public JFormattedTextField getqty() {
        return jFormattedTextField1;
    }

    public JTextField getOrderId() {
        return jTextField5;
    }

    public JTextField getCustomerNic() {
        return jTextField1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1250, 585));

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 255)));

        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel3.setText("CUSTOMER NIC");

        jButton4.setBackground(new java.awt.Color(0, 204, 153));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("SELECT CUSTOMER");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel1.setText("PRODUCT NAME");

        jTextField2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel6.setText("PRODUCT CATEGORY");

        jTextField3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jPanel1.setBackground(new java.awt.Color(204, 204, 255));
        jPanel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 255, 204), 1, true));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel9.setText("TOTAL");

        jFormattedTextField3.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jFormattedTextField3ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Product Category", "Unit Price", "Quantity", "Total"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel7.setText("UNIT PRICE");

        jFormattedTextField2.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel8.setText("QUANTITY");

        jButton7.setBackground(new java.awt.Color(0, 153, 153));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("ADD ORDER");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jFormattedTextField4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setText("ID");

        jTextField4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jButton5.setBackground(new java.awt.Color(153, 0, 204));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("SELECT PRODUCT");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel2.setText("ORDER ID");

        jTextField5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jFormattedTextField4))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jFormattedTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextField3)
                            .addComponent(jTextField1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(59, 59, 59)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jButton6.setBackground(new java.awt.Color(255, 153, 102));
        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("RESET ALL");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 153, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("ADVANCE PAYMENT");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Place Order");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-create-order.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(553, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addGap(539, 539, 539)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(85, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(245, 245, 245)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(60, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        home home = new home();
        Product product = new Product(home, true, this);
        product.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        home home = new home();
        if (jTextField1.getText().isEmpty()) {

            order_Map = new HashMap<>();
            OrderCustomer customer2 = new OrderCustomer(home, true, this);
            customer2.setVisible(true);
        } else {
            int response = JOptionPane.showConfirmDialog(this, "Do you want to change the customer?", "confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                reset();
                order_Map = new HashMap<>();
                loadCustomer customer2 = new loadCustomer(home, true, this);
                customer2.setVisible(true);
            }

        }


    }//GEN-LAST:event_jButton4ActionPerformed


    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        String orderId = jTextField5.getText().trim();
        String customerNic = jTextField1.getText().trim();
        String productId = jTextField4.getText().trim();
        String productName = jTextField2.getText().trim();
        String productCategory = jTextField3.getText().trim();
        String qtyText = jFormattedTextField2.getText().trim();
        String availableQtyText = jFormattedTextField1.getText().trim();
        String unitPriceText = jFormattedTextField4.getText().trim();

        if (orderId.isEmpty() || customerNic.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Order ID or Customer NIC is empty.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (productName.isEmpty() || productId.isEmpty() || productCategory.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a product.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (qtyText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter quantity.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (availableQtyText.isEmpty() || unitPriceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Available quantity or unit price is missing.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double qty, availableQty, unitPrice;

        try {
            qty = Double.parseDouble(qtyText);
            availableQty = Double.parseDouble(availableQtyText);
            unitPrice = Double.parseDouble(unitPriceText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number format in quantity or price.", "Parse Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (qty <= 0) {
            JOptionPane.showMessageDialog(this, "Quantity must be greater than zero.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (qty > availableQty) {
            JOptionPane.showMessageDialog(this, "Quantity exceeds available stock.", "Stock Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        if (order_Map == null) {
            order_Map = new HashMap<>(); 
        }

        OrderItem existingItem = order_Map.get(productId);

        if (existingItem != null) {
            if (existingItem.getUnitPrice() == unitPrice) {
                double newQty = existingItem.getQty() + qty;
                if (newQty > availableQty) {
                    JOptionPane.showMessageDialog(this, "Total quantity exceeds available stock.", "Stock Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                existingItem.setQty(newQty);
            } else {
                JOptionPane.showMessageDialog(this, "Product already added with a different unit price.", "Price Mismatch", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setCustomerNic(customerNic);
            orderItem.setQty(qty);
            orderItem.setproductId(productId); 
            orderItem.setProductName(productName);
            orderItem.setProductCategory(productCategory);
            orderItem.setUnitPrice(unitPrice);
            order_Map.put(productId, orderItem);
        }


        loadTable(); 

    }//GEN-LAST:event_jButton7ActionPerformed
    public void diductqty() {
        String availableqty = jFormattedTextField1.getText();
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            try {

                String productId = String.valueOf(jTable1.getValueAt(i, 0));
                String qty = String.valueOf(jTable1.getValueAt(i, 4));

                ResultSet rs = MySQL.executeSearch("SELECT * FROM `product_stock` WHERE `id` = '" + productId + "'");
                if (rs.next()) {
                    System.out.println(rs.getDouble("qty"));
                    double newqty = rs.getDouble("qty") - Double.parseDouble(qty);
                    MySQL.executeIUD("UPDATE `product_stock` SET `qty` = '" + newqty + "' WHERE `id` = '" + productId + "'");
                }

//               
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void laodOrderItem() {
        System.out.println("ok");
        String productId = jTextField4.getText();
        String OrderId = jTextField5.getText();

        for (OrderItem orderItem : order_Map.values()) {
            try {
                System.out.println("1");
                Double total = orderItem.getQty() * orderItem.getUnitPrice();
//               ResultSet rs =MySQL.executeSearch("SELECT * FROM `order` WHERE `orderId` = '"+OrderId+"'");
//                if (rs.next()) {
//                System.out.println("2");

                MySQL.executeIUD("INSERT INTO `order_item`(`qty`,`unit_price`,`total`,`order_orderId`,`product_stock_id`)"
                        + "VALUES('" + orderItem.getQty() + "','" + orderItem.getUnitPrice() + "','" + total + "','" + OrderId + "','" + orderItem.getproductId() + "')");
                System.out.println("3");

//                }
            } catch (Exception ex) {
                Logger.getLogger(OrderManagement.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        String orderId = jTextField5.getText();

        String total = jFormattedTextField3.getText();
        if (total.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Add to Order", "Error", JOptionPane.ERROR_MESSAGE);
        } else {

//         System.out.println(percentageAdvance);
        }
        String customerNic = jTextField1.getText();
        Double finalTotal = Double.parseDouble(total);
        Double percentageAdvance = (finalTotal / 100) * 30;
        String percentageAdvance2 = "RS" + " " + percentageAdvance;
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `customer` WHERE `nic` = '" + customerNic + "'");

            if (rs.next()) {

                String emailcheck = rs.getString("email");
                if (emailcheck != null) {
                    System.out.println(" have email");
                    if (!emailcheck.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {

                        JOptionPane.showMessageDialog(this, "Invalid Email Address", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    String to2 = "kavindarashmika508@gmail.com";
                    String host = "smtp.gmail.com";
                    final String username = to2;
                    final String password = "qplg semu alwa ouae"; 

                    Properties properties = new Properties();
                    properties.put("mail.smtp.auth", "true");
                    properties.put("mail.smtp.starttls.enable", "true");
                    properties.put("mail.smtp.host", host);
                    properties.put("mail.smtp.port", "587");

                    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(username));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailcheck));

                    String htmlContent = "</head>\n"
                            + "<body style=\"font-family: Arial, sans-serif; margin: 0; padding: 0;\">\n"
                            + "    <div style=\"width: 80%; margin: 40px auto; padding: 20px; border: 1px solid #ccc; border-radius: 10px; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);\">\n"
                            + "        <div style=\"background-color: #4a90e2; color: #ffffff; padding: 20px; border-radius: 10px 10px 0 0;\">\n"
                            + "            <h1 style=\"margin: 0; font-size: 2em;\">ROSLIN HILLS TEA FACTORY</h1>\n"
                            + "            <p style=\"margin: 5px 0;\">Contact No: 011 3457654</p>\n"
                            + "            <p style=\"margin: 5px 0;\">Address: No 4, Main Street, Kandy Road, Pilimathalawa</p>\n"
                            + "        </div>\n"
                            + "        <div style=\"margin-top: 20px;\">\n"
                            + "            <h2 style=\"color: #4a90e2;\">Advance Payment</h2>\n"
                            + "            <form>\n"
                            + "                <div style=\"margin-bottom: 20px;\">\n"
                            + "                    <label for=\"orderId\" style=\"display: block; margin-bottom: 10px; font-weight: bold;\">Order ID:</label>\n"
                            + "                    <p id=\"orderId\" style=\"margin-bottom: 20px; padding: 10px; border: 1px solid #ccc; border-radius: 5px;\">" + orderId + "</p>\n"
                            + "                </div>\n"
                            + "                <div style=\"margin-bottom: 20px;\">\n"
                            + "                    <label for=\"bankDetails\" style=\"display: block; margin-bottom: 10px; font-weight: bold;\">Bank Details:</label>\n"
                            + "                    <p id=\"bankDetails\" style=\"margin-bottom: 20px; padding: 10px; border: 1px solid #ccc; border-radius: 5px;\">Bank Name: Sampath Bank,<br> <br> Account No: 78657997658, <br> <br> Account Name: Roslin Hills teaFactory(PVT) Limited,Sampath Bank</p>\n"
                            + "                </div>\n"
                            + "                <div style=\"margin-bottom: 20px;\">\n"
                            + "                    <label for=\"advancePayment\" style=\"display: block; margin-bottom: 10px; font-weight: bold;\">Advance Payment:</label>\n"
                            + "                    <p id=\"advancePayment\" style=\"margin-bottom: 20px; padding: 10px; border: 1px solid #ccc; border-radius: 5px;\">" + percentageAdvance2 + "</p>\n"
                            + "                </div>\n"
                            + "            </form>\n"
                            + "        </div>\n"
                            + "    </div>\n"
                            + "</body>";

                    message.setContent(htmlContent, "text/html");
                    Transport.send(message);

                    System.out.println("Email sent successfully!");

                }
            } else {
                System.out.println("NOT HAVE EMAIL");

            }
        } catch (MailConnectException mc) {
            JOptionPane.showMessageDialog(this, "Email Not Sent", "error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            Logger.getLogger(OrderManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

        home home = new home();

        OrderManagement order = new OrderManagement();
        AdvacePayment payment = new AdvacePayment(home, true, this);
        payment.setVisible(true);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jFormattedTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jFormattedTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jFormattedTextField3ActionPerformed

    public void backFunction() {
    }
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        reset();
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
