/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.SalesDepartment;

import com.formdev.flatlaf.FlatClientProperties;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.management.Query.value;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.MySQL;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import gui.SalesDepartment.All_Order;

/**
 *
 * @author Lenovo
 */
public class Invoice extends javax.swing.JPanel {

    public Invoice() {
        initComponents();
        design();
        load_Order("customer_nic", "ASC");
        loadPaymentMethod();
        All_Order order = new All_Order();
        order.tableCenter(jTable1, jLabel1);

    }

    private void design() {

        jTextField1.putClientProperty("JComponent.roundRect", true);
        jFormattedTextField1.putClientProperty("JComponent.roundRect", true);
        jFormattedTextField2.putClientProperty("JComponent.roundRect", true);
        jFormattedTextField3.putClientProperty("JComponent.roundRect", true);
        jFormattedTextField6.putClientProperty("JComponent.roundRect", true);
        jFormattedTextField5.putClientProperty("JComponent.roundRect", true);
        jTextField2.putClientProperty("JComponent.roundRect", true);

        jComboBox1.putClientProperty("JComponent.roundRect", true);

        jPanel1.putClientProperty(FlatClientProperties.STYLE, "arc: 25");
        jPanel2.putClientProperty(FlatClientProperties.STYLE, "arc: 25");
        jPanel3.putClientProperty(FlatClientProperties.STYLE, "arc: 25");

        jButton1.putClientProperty("JButton.buttonType", "roundRect");
        jTextField2.setEditable(false);
        jFormattedTextField1.setEditable(false);
        jFormattedTextField2.setEditable(false);
        jFormattedTextField3.setEditable(false);
    }
    static String query;

    private void load_Order(String column, String orderby) {
        try {
            query = "SELECT * FROM `order` INNER JOIN `order_status` "
                    + "ON `order`.`order_status_id` = `order_status`.`id` INNER JOIN "
                    + " `payment_status` ON `order`.`payment_status_id` = `payment_status`.`id` INNER JOIN"
                    + "`payment_method` ON `order`.`payment_method_id` = `payment_method`.`id` WHERE `order`.`order_status_id` = '1'";

            boolean whereadded = true;

            if (jTextField1.getText().trim() != null) {
                if (!query.contains("WHERE")) {
                    query += " WHERE";
                } else {
                    query += " AND";
                }
                query += " `order`.`customer_nic` LIKE '%" + jTextField1.getText().trim() + "%' ";
                whereadded = true;
            }
            if (jTextField1.getText().trim() != null) {
                if (!query.contains("WHERE")) {
                    query += " WHERE";
                } else {
                    query += " AND";
                }
                query += " `order`.`orderId` LIKE '%" + jTextField1.getText().trim() + "%' ";
                whereadded = true;
            }

            ResultSet rs = MySQL.executeSearch(query);
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                Vector vector = new Vector();
                vector.add(rs.getString("order.customer_nic"));
                vector.add(rs.getString("order.orderId"));
                vector.add(rs.getDate("order.order_date"));
                vector.add(rs.getDouble("order.total_amount"));
                vector.add(rs.getString("order_status.status"));
                vector.add(rs.getString("payment_method.method"));
                vector.add(rs.getString("payment_status.status"));

                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    HashMap<String, Integer> paymentMap = new HashMap<>();

    private void loadPaymentMethod() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `payment_method`");

            Vector vector = new Vector();
            vector.add("Select");

            while (rs.next()) {
                vector.add(rs.getString("method"));
                paymentMap.put(rs.getString("method"), rs.getInt("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jLabel3 = new javax.swing.JLabel();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jFormattedTextField5 = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        jFormattedTextField6 = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)), "Select the Customer", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 3, 14), new java.awt.Color(0, 0, 0))); // NOI18N

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer NIC", "Order ID", "Order Date", "Total Amount"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 468, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 255)));

        jTable2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Product ID", "Product", "Quantity", "Unit Price", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jPanel3.setBackground(new java.awt.Color(204, 204, 255));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel2.setText("TOTAL");

        jFormattedTextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        jFormattedTextField1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel3.setText("ADVANCE PAYMENT");

        jFormattedTextField2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel4.setText("PENDING PAYMENT");

        jFormattedTextField3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel5.setText("PAYMENT METHOD");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel6.setText("PAYMENT");

        jFormattedTextField5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jFormattedTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jFormattedTextField5KeyReleased(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel7.setText("BALANCE");

        jFormattedTextField6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jButton1.setBackground(new java.awt.Color(255, 153, 0));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("PRINT INVOICE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jFormattedTextField6)
                            .addComponent(jFormattedTextField5)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField1)
                            .addComponent(jFormattedTextField2)
                            .addComponent(jFormattedTextField3))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jFormattedTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jFormattedTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel8.setText("INVOICE NO");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(107, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(100, 50));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("INVOICE");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons8-order-completed.gif"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(582, 582, 582)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(24, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 1248, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
HashMap<String, model.OrderItem> order_Map = new HashMap<>();
    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        if (evt.getClickCount() == 2) {
            String orderId = String.valueOf(jTable1.getValueAt(row, 1));

            try {

                long randomNumber = System.currentTimeMillis();
                jTextField2.setText("INO/ " + randomNumber);

                ResultSet rs = MySQL.executeSearch("SELECT * FROM `order_item` INNER JOIN `product_stock`"
                        + "ON `product_stock`.`id` = `order_item`.`product_stock_id` INNER JOIN `products`"
                        + "ON `products`.`id` = `product_stock`.`products_id` WHERE `order_item`.`order_orderId` =  '" + orderId + "'");

                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                model.setRowCount(0);

                while (rs.next()) {
                    Vector vector = new Vector();

                    vector.add(rs.getString("order_item.order_orderId"));
                    vector.add(rs.getString("product_stock.id"));
                    vector.add(rs.getString("products.title"));
                    vector.add(rs.getDouble("order_item.qty"));
                    vector.add(rs.getDouble("order_item.unit_price"));
                    vector.add(rs.getDouble("order_item.total"));

                    model.OrderItem orderItem = new model.OrderItem();
                    orderItem.setOrderId(rs.getString("order_item.order_orderId"));
                    orderItem.setProductName(rs.getString("products.title"));
                    orderItem.setQty(rs.getDouble("order_item.qty"));
                    orderItem.setUnitPrice(rs.getDouble("order_item.unit_price"));
                    orderItem.setTotal(rs.getDouble("order_item.total"));

                    order_Map.put(rs.getString("products.id"), orderItem);

                    model.addRow(vector);
                }

                double total = 0;
                for (int i = 0; i < jTable2.getRowCount(); i++) {

                    Object value = jTable2.getValueAt(i, 4);
                    if (value instanceof Number) {

                        total += ((Number) value).doubleValue();
                    } else {
                        total += Double.parseDouble(String.valueOf(value));
                    }

                }

                ResultSet rs2 = MySQL.executeSearch("SELECT * FROM `advance` WHERE `order_orderId` = '" + orderId + "'");

                if (rs2.next()) {
                    jFormattedTextField2.setText(String.valueOf(rs2.getDouble("payment")));
                    jFormattedTextField3.setText(String.valueOf(rs2.getDouble("balance")));

                }
                double tot = Double.parseDouble(String.valueOf(jTable1.getValueAt(row, 3)));
                jFormattedTextField1.setText(String.valueOf(tot));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jFormattedTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jFormattedTextField5KeyReleased

        String pendingPayment = jFormattedTextField3.getText();
        String payment2 = jFormattedTextField5.getText();

        double balance = Double.parseDouble(pendingPayment) - Double.parseDouble(payment2);
        double newBalance = Math.abs(balance);
        if (payment2.trim().isEmpty()) {
            jFormattedTextField6.setText("");
        }
        jFormattedTextField6.setText(String.valueOf(newBalance));
    }//GEN-LAST:event_jFormattedTextField5KeyReleased
    private void InsertSalesItem() {

    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jFormattedTextField1.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Total is Empty and Select the row", "Warning", JOptionPane.INFORMATION_MESSAGE);
        } else if (jFormattedTextField2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Advance Payment is Empty", "Warning", JOptionPane.INFORMATION_MESSAGE);
        } else if (jFormattedTextField3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pending Payment is Empty", "Warning", JOptionPane.INFORMATION_MESSAGE);
        } else if (jComboBox1.getSelectedItem().equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a Payment Method", "Warning", JOptionPane.INFORMATION_MESSAGE);
        } else if (jFormattedTextField5.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter your Payment", "Warning", JOptionPane.INFORMATION_MESSAGE);
        } else if (jFormattedTextField6.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Balance is Empty", "Warning", JOptionPane.INFORMATION_MESSAGE);
        } else {
            Double pendingPayment = Double.parseDouble(String.valueOf(jFormattedTextField3.getText()));
            Double payment3 = Double.parseDouble(String.valueOf(jFormattedTextField5.getText()));
            if (pendingPayment >= payment3) {
                JOptionPane.showMessageDialog(this, "Please Enter valid Payment", "Warning", JOptionPane.INFORMATION_MESSAGE);
            } else {
                int Row1 = jTable1.getSelectedRow();

                if (Row1 == -1) {
                    JOptionPane.showMessageDialog(this, "Please select a row from the table", "Warning", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                int Row = jTable1.getSelectedRow();
                String InvoiceNo = jTextField2.getText();
                String total = jFormattedTextField1.getText();
                String Advance_Payment = jFormattedTextField2.getText();
                String Pending_Payment = jFormattedTextField3.getText();
                String payment = jFormattedTextField5.getText();
                String balance = jFormattedTextField6.getText();
                String payment_method = String.valueOf(jComboBox1.getSelectedItem());
                String customerNic = String.valueOf(jTable1.getValueAt(Row, 0));

                try {
                    ResultSet rs = MySQL.executeSearch("SELECT * FROM `customer` WHERE `nic` = '" + customerNic + "'");

                    if (rs.next()) {
                        System.out.println("ok");
                        InputStream path = this.getClass().getResourceAsStream("/reports/Sales/invoice_report7.jasper");

                        String fname = rs.getString("fname");
                        String lname = rs.getString("fname");
                        String fullName = fname + lname;
                        String line1 = rs.getString("line1");
                        String line2 = rs.getString("line2");

                        HashMap<String, Object> param = new HashMap<>();
                        param.put("Parameter1", InvoiceNo);
                        param.put("Parameter2", payment_method);
                        param.put("Parameter3", fullName);
                        param.put("Parameter4", line1);
                        param.put("Parameter5", total);
                        param.put("Parameter6", Advance_Payment);
                        param.put("Parameter7", payment);
                        param.put("Parameter8", balance);
                        param.put("Parameter9", line2);
                        param.put("Parameter10", Pending_Payment);

                        JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable2.getModel());

                        JasperPrint jasperPrint = JasperFillManager.fillReport(path, param, dataSource);

                        JasperViewer.viewReport(jasperPrint, false);
                    }

                    Date date = new Date();

                    int row = jTable1.getSelectedRow();
                    double total_amount = Double.parseDouble(String.valueOf(jTable1.getValueAt(row, 3)));
                    String nic = String.valueOf(jTable1.getValueAt(row, 0));
                    ResultSet rs2 = MySQL.executeSearch("SELECT * FROM `order` INNER JOIN `order_item` "
                            + "ON `order_item`.`order_orderId` = `order`.`orderId` WHERE `orderId` = '" + jTable1.getValueAt(row, 1) + "'");
                    if (rs2.next()) {
                        System.out.println("iNSERT SALE TABLE");
                        String OrderID = String.valueOf(jTable1.getValueAt(row, 1));
                        long randomNumber = System.currentTimeMillis();
                        String SalesId = ("SID/ " + randomNumber);
                        int PaymentMethod = rs2.getInt("order.payment_method_id");
                        double qty = rs2.getDouble("order_item.qty");
                        double unitprice = rs2.getDouble("order_item.unit_price");
                        double OrderItemTotal = rs2.getDouble("order_item.total");
                        String productId = rs2.getString("order_item.product_stock_id");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

//                    for (model.OrderItem orderItem : order_Map.values()) {
//                          Double qty1 = orderItem.getQty();
//                          Double unitPrice1 = orderItem.getUnitPrice();
//                          Double total1 = orderItem.getTotal();
//                          String productId1 = orderItem.getproductId();
                        MySQL.executeIUD("INSERT INTO `sales`(`sales_id`,`date`,`total_amount`,`customer_nic`,`payment_method_id`)"
                                + "VALUES('" + SalesId + "','" + sdf.format(date) + "','" + total_amount + "','" + nic + "','" + PaymentMethod + "')");

//                     MySQL.executeIUD("UPDATE `order` SET `payment_status_id` = '2' AND `order_status_id` = '2'");
                        int row2 = jTable2.getRowCount();
                        for (int i = 0; i < row2; i++) {
                            String qty1 = String.valueOf(jTable2.getValueAt(i, 3));
                            String unitPrice1 = String.valueOf(jTable2.getValueAt(i, 4));
                            String total1 = String.valueOf(jTable2.getValueAt(i, 5));
                            String productId1 = String.valueOf(jTable2.getValueAt(i, 1));
                            System.out.println(productId1);
                            MySQL.executeIUD("INSERT INTO `sales_item`(`qty`,`unit_price`,`total`,`sales_sales_id`,`product_stock_id`)"
                                    + "VALUES('" + Double.parseDouble(qty1) + "','" + Double.parseDouble(unitPrice1) + "','" + Double.parseDouble(total1) + "','" + SalesId + "','" + productId1 + "')");
                        }

//                      }
//                    MySQL.executeIUD("DELETE FROM `advace` WHERE `order_orderId` = '"+OrderID+"'");
//                    MySQL.executeIUD("DELETE FROM `order_item` WHERE `order_orderId` = '"+OrderID+"'");
//                    MySQL.executeIUD("DELETE FROM `order` WHERE `orderId` = '"+OrderID+"'");
                        MySQL.executeIUD("UPDATE `order` SET `order_status_id` = '2' WHERE `orderId` = '" + OrderID + "'");
                        System.out.println("Updatesd Order");
                        load_Order("customer_nic", "ASC");
                        reset();
                        sendEmailToPdf();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void sendEmailToPdf() {
        try {
            String nic;
            for (int i = 0; i < jTable1.getRowCount(); i++) {
                nic = String.valueOf(jTable1.getValueAt(i, 0));
                ResultSet rs = MySQL.executeSearch("SELECT `email` FROM `customer` WHERE `nic` = '" + nic + "'");

                if (rs.next()) {
                    String email = rs.getString("customer.email");

                    if (email.isEmpty()) {
                        System.out.println("have email");
                    } else {
                        System.out.println("Not have email");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void reset() {
        jTextField2.setText("");
        jFormattedTextField1.setText("");
        jFormattedTextField2.setText("");
        jFormattedTextField3.setText("");
        jFormattedTextField5.setText("");
        jFormattedTextField6.setText("");
        jComboBox1.setSelectedIndex(0);
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
    }

    private void InsertOrderItem() {

    }
    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField5;
    private javax.swing.JFormattedTextField jFormattedTextField6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
