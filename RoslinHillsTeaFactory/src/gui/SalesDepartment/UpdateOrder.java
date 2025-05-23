/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.SalesDepartment;

import com.formdev.flatlaf.FlatClientProperties;
import com.mysql.cj.protocol.Resultset;
import gui.SalesDepartment.Order;
import gui.SalesDepartment.OrderManagement;
import gui.SalesDepartment.Product;
import gui.home;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.MySQL;
import model.OrderItem;

/**
 *
 * @author Lenovo
 */
public class UpdateOrder extends javax.swing.JPanel {

    Product product;

    HashMap<String, Object> productdetails_Map = new HashMap<>();

    OrderItem orderItem;

    public UpdateOrder() {
        initComponents();
        load_Order("orderId", "ASC");
        design();

    }

    public void diductqty() {
        // String availableqty = jFormattedTextField1.getText();
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            try {

                String productId = jTextField5.getText();
                String qty = String.valueOf(jTable2.getValueAt(i, 3));

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

    public static String query;

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
                query += " `order`.`orderId` LIKE '%" + jTextField1.getText().trim() + "%'";
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

    private void design() {
        jTextField2.setEditable(false);
        jTextField3.setEditable(false);
        jTextField1.putClientProperty("JComponent.roundRect", true);
//       jButton1.putClientProperty("JButton.buttonType", "roundRect");

        jTextField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search by Order ID");

        jTextField4.setEditable(false);
        jTextField5.setEditable(false);
        jTextField6.setEditable(false);
        jFormattedTextField3.setEditable(false);
        jFormattedTextField1.setEditable(false);
        jFormattedTextField4.setEditable(false);

        jTextField2.putClientProperty("JComponent.roundRect", true);
        jTextField3.putClientProperty("JComponent.roundRect", true);
        jTextField4.putClientProperty("JComponent.roundRect", true);
        jTextField6.putClientProperty("JComponent.roundRect", true);
        jFormattedTextField2.putClientProperty("JComponent.roundRect", true);
        //jFormattedTextField1.putClientProperty("JComponent.roundRect", true);
        jFormattedTextField3.putClientProperty("JComponent.roundRect", true);
        jFormattedTextField4.putClientProperty("JComponent.roundRect", true);

        jButton1.putClientProperty("JButton.buttonType", "roundRect");
        jButton2.putClientProperty("JButton.buttonType", "roundRect");
        jButton3.putClientProperty("JButton.buttonType", "roundRect");

    }

    public JTextField getProductId2() {
        return jTextField5;
    }

    public JTextField getProductName2() {
        return jTextField4;
    }

    public JTextField getProductCatergory2() {
        return jTextField6;
    }

    public JFormattedTextField getAvailabelqty2() {
        return jFormattedTextField1;
    }

    public JFormattedTextField getUnitPrice2() {
        return jFormattedTextField3;
    }
    HashMap<String, OrderItem> order_Map = new HashMap<>();
    double total;

    private void loadTable() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);

        total = 0;

        for (OrderItem orderItem : order_Map.values()) {
            Vector vector = new Vector();
            //vector.add(orderItem.getproductId());
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
        jFormattedTextField4.setText(String.valueOf(total));

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jFormattedTextField3 = new javax.swing.JFormattedTextField();
        jFormattedTextField4 = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 204)), "Order", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Customer Nic", "Order Id", "Order_date", "Total Amount"
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

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 204)));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel3.setText("ORDER ID");

        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel4.setText("CUSTOMER NIC");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel5.setText("PRODUCT NAME");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel6.setText("CATERGORY");

        jButton1.setBackground(new java.awt.Color(0, 153, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("SELECT PRODUCT");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Catergory", "Unit Price", "Quantity", "Total"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 6, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel7.setText("QUANTITY");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel8.setText("UNIT PRICE");

        jButton2.setBackground(new java.awt.Color(255, 153, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("ADD TO ORDER TABLE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setText("TOTAL");

        jButton3.setBackground(new java.awt.Color(102, 102, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("UPDATE ORDER");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel7)
                                .addGap(56, 56, 56)
                                .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)
                                .addGap(31, 31, 31)
                                .addComponent(jFormattedTextField3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jTextField2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(30, 30, 30)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(340, 340, 340)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jFormattedTextField4))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jFormattedTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("UPDATE ORDER");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cardboard_12829521-ezgif.com-resize.gif"))); // NOI18N
        jLabel2.setToolTipText("");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(588, 588, 588)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(56, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        load_Order("orderId", "ASC");
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();

        home home = new home();
        if (jTextField2.getText().isEmpty()) {

            jTextField2.setText(String.valueOf(jTable1.getValueAt(row, 1)));
            jTextField3.setText(String.valueOf(jTable1.getValueAt(row, 0)));
            order_Map = new HashMap<>();

        } else {
            int response = JOptionPane.showConfirmDialog(this, "Do you want to change the customer?", "confirmation", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                reset();
                order_Map = new HashMap<>();
//        OrderManagement order = new OrderManagement();
                jTextField2.setText(String.valueOf(jTable1.getValueAt(row, 1)));
                jTextField3.setText(String.valueOf(jTable1.getValueAt(row, 0)));
            }

        }
    }//GEN-LAST:event_jTable1MouseClicked
    private void validateqty() {
        double AvailableQty = Double.parseDouble(jFormattedTextField1.getText());
        String pName = jTextField4.getText();
        Double uPrice = Double.parseDouble(jFormattedTextField3.getText());
        for (int i = 0; i < jTable2.getRowCount(); i++) {
            Double qty = Double.parseDouble(String.valueOf(jTable2.getValueAt(i, 3)));
            String tablePName = String.valueOf(jTable2.getValueAt(i, 0));
            double tableUPrice = Double.parseDouble(String.valueOf(jTable2.getValueAt(i, 2)));
            if (pName.equals(tablePName) || uPrice == tableUPrice) {

                if (AvailableQty <= qty) {
                    JOptionPane.showMessageDialog(this, "quantity is not valid", "error", JOptionPane.ERROR_MESSAGE);
                }

            }

        }

    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String orderId = jTextField2.getText().trim();
        String customernic = jTextField3.getText().trim();
        String productName = jTextField4.getText().trim();
        String productId = jTextField5.getText().trim();
        String category = jTextField6.getText().trim();
        String unitPriceStr = jFormattedTextField3.getText().trim();
        String qtyStr = jFormattedTextField2.getText().trim();
        String availableQtyStr = jFormattedTextField1.getText().trim();

// Input validation
        if (orderId.isEmpty() || customernic.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Order ID and Customer NIC cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (productName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a product", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (qtyStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter quantity", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double qty, availableQty, unitPrice;
        try {
            qty = Double.parseDouble(qtyStr);
            availableQty = Double.parseDouble(availableQtyStr);
            unitPrice = Double.parseDouble(unitPriceStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Invalid number format in quantity or price fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (qty <= 0) {
            JOptionPane.showMessageDialog(this, "Quantity must be a positive number", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (qty > availableQty) {
            JOptionPane.showMessageDialog(this, "Enter a valid quantity (less than or equal to available stock)", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        OrderItem existingItem = order_Map.get(productId);
        if (existingItem != null) {
            if (existingItem.getUnitPrice() == unitPrice) {
                double newQty = existingItem.getQty() + qty;

                if (newQty > availableQty) {
                    JOptionPane.showMessageDialog(this, "Total quantity exceeds available stock", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                existingItem.setQty(newQty);
                loadTable();
            } else {
                JOptionPane.showMessageDialog(this, "Order item already exists with a different unit price", "Error", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }

        OrderItem orderItem = new OrderItem();
        orderItem.setOrderId(orderId);
        orderItem.setCustomerNic(customernic);
        orderItem.setQty(qty);
        orderItem.setproductId(productId);
        orderItem.setProductName(productName);
        orderItem.setProductCategory(category);
        orderItem.setUnitPrice(unitPrice);

        order_Map.put(productId, orderItem);
        loadTable();


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        home home = new home();
        Order order = new Order();
        Product product = new Product(home, true, this);
        product.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        diductqty();
        int row = jTable1.getSelectedRow();
        String orderId = String.valueOf(jTable1.getValueAt(row, 1));
        String CustomerNic = String.valueOf(jTable1.getValueAt(row, 0));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        double total1 = Double.parseDouble(String.valueOf(jTable1.getValueAt(row, 3)));
        double currentTotal = Double.parseDouble(jFormattedTextField4.getText());
        double newTotal = total1 + currentTotal;

        Date date = new Date();

        for (OrderItem orderItem : order_Map.values()) {
            try {
                ResultSet rs = MySQL.executeSearch("SELECT * FROM `order` WHERE orderId = '" + orderId + "'");

                if (rs.next()) {
                    double unitPrice = orderItem.getUnitPrice();
                    String productId = orderItem.getproductId();

                    ResultSet rs2 = MySQL.executeSearch("SELECT * FROM order_item WHERE unit_price = '" + unitPrice + "' AND product_stock_id = '" + productId + "' AND order_orderId = '" + orderId + "'");

                    if (rs2.next()) {

                        double qty = rs2.getDouble("qty");
                        double newQty = qty + orderItem.getQty();
                        double newtot = newQty * orderItem.getUnitPrice();
                        MySQL.executeIUD("UPDATE order_item SET qty = '" + newQty + "',`total` = '" + newtot + "' WHERE order_orderId = '" + orderId + "' AND product_stock_id = '" + productId + "'");

                    } else {

                        MySQL.executeIUD("INSERT INTO order_item (qty, unit_price, total, order_orderId, product_stock_id) "
                                + "VALUES ('" + orderItem.getQty() + "', '" + orderItem.getUnitPrice() + "', '" + (orderItem.getQty() * orderItem.getUnitPrice()) + "', '" + orderId + "', '" + productId + "')");

                    }

                    MySQL.executeIUD("UPDATE `order` SET order_date = '" + sdf.format(date) + "', total_amount = '" + newTotal + "' WHERE orderId = '" + orderId + "'");

                }

                ResultSet rs3 = MySQL.executeSearch("SELECT * FROM advance WHERE order_orderId = '" + orderId + "'");

                if (rs3.next()) {
                    double advancePayment = rs3.getDouble("payment");
                    double balance = newTotal - advancePayment;
                    MySQL.executeIUD("UPDATE advance SET balance = '" + balance + "' WHERE order_orderId = '" + orderId + "'");
                    reset2();
                }
                System.out.println("oooooooook");

                reset2();
                resetOrderItem();

                JOptionPane.showMessageDialog(this, "Order Update Scuessful", "scuess", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        load_Order("orderId", "ASC");


    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JFormattedTextField jFormattedTextField3;
    private javax.swing.JFormattedTextField jFormattedTextField4;
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
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);

        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jFormattedTextField2.setText("");
        jFormattedTextField1.setText("");
        jFormattedTextField3.setText("");

        this.orderItem = null;
        order_Map = null;
    }

    private void resetOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.clearData();
    }

    private void reset2() {
jFormattedTextField4.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jFormattedTextField2.setText("");
        jFormattedTextField1.setText("");
        jFormattedTextField3.setText("");

        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
    }
}
