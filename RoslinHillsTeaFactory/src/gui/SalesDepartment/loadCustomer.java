/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui.SalesDepartment;

//import gui.SalesDepartment.SalesDepartment.Customer_Management;
import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import model.MySQL;

/**
 *
 * @author Lenovo
 */
public class loadCustomer extends javax.swing.JDialog {
    
    private static Customer_Management Cm;  // Removed 'static'
    private static OrderManagement Cm2;  // Removed 'static'

//public void setCustomer(Customer_Management Cm) {
//    this.Cm = Cm;
//}
    public loadCustomer(java.awt.Frame parent, boolean modal, JPanel jpanel) {
        super(parent, modal);
        initComponents();
        design();
        loadCustomer2("fname", "ASC");
        
        if (jpanel instanceof Customer_Management) {
        this.Cm = (Customer_Management) jpanel;
        }
        if (jpanel instanceof OrderManagement) {
            
        this.Cm2 = (OrderManagement) jpanel;
        }
    }
    static String query;
    
    private void loadCustomer2(String column, String orderby) {
        // Start building the base query
        query = "SELECT * FROM `customer` "
                + "INNER JOIN `city` ON `customer`.`city_id` = `city`.`id` "
                + "INNER JOIN `customer_status` ON `customer`.`customer_status_id` = `customer_status`.`id` "
                + "INNER JOIN `gender` ON `customer`.`gender_id` = `gender`.`id`";
        
        boolean whereAdded = false;
        
        try {
            // Check if jTextField1 has a value (avoiding null or empty checks)
            
            if (jTextField1.getText().trim() != null) {
                if (!query.contains("WHERE")) {
                    query += " WHERE";
                } else {
                    query += " AND";
                }
                query += " `customer`.`fname` LIKE '%" + jTextField1.getText().trim() + "%'";
                whereAdded = true;
            }
            if (jTextField4.getText().trim() != null) {
                if (!query.contains("WHERE")) {
                    query += " WHERE";
                } else {
                    query += " AND";
                }
                query += " `customer`.`lname` LIKE '%" + jTextField4.getText().trim() + "%'";
                whereAdded = true;
            }
            if (jTextField2.getText().trim() != null) {
                if (!query.contains("WHERE")) {
                    query += " WHERE";
                } else {
                    query += " AND";
                }
                query += " `customer`.`nic` LIKE '%" + jTextField2.getText().trim() + "%'";
                whereAdded = true;
            }
            if (jTextField3.getText().trim() != null) {
                if (!query.contains("WHERE")) {
                    query += " WHERE";
                } else {
                    query += " AND";
                }
                query += " `customer`.`mobile` LIKE '%" + jTextField3.getText().trim() + "%'";
                whereAdded = true;
            }
            
            if (jComboBox1.getSelectedItem() != null) {
                
                if (whereAdded) {
                    query += " AND `" + column + "` IS NOT NULL";  // You can add a filter if needed
                }
                query += " ORDER BY `" + column + "` " + orderby;
            }
            
            ResultSet rs = MySQL.executeSearch(query);
            
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            
            while (rs.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(rs.getString("customer.fname"));                
                vector.add(rs.getString("customer.lname"));                
                vector.add(rs.getString("customer.nic"));                
                vector.add(rs.getString("customer.email"));                
                vector.add(rs.getString("customer.mobile"));                
                vector.add(rs.getString("customer.registered_date"));                
                vector.add(rs.getString("customer.line1"));                
                vector.add(rs.getString("customer.line2"));                
                vector.add(rs.getString("city.name"));                
                vector.add(rs.getString("gender.name"));                
                vector.add(rs.getString("customer_status.status"));                
                
                model.addRow(vector);
            }
        } catch (Exception ex) {
            Logger.getLogger(loadCustomer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void design() {
        jComboBox1.putClientProperty("JComponent.roundRect", true);
        
        jTextField1.putClientProperty("JComponent.roundRect", true);
        jTextField2.putClientProperty("JComponent.roundRect", true);
        jTextField3.putClientProperty("JComponent.roundRect", true);
        jTextField4.putClientProperty("JComponent.roundRect", true);
        jButton2.putClientProperty("JButton.buttonType", "roundRect");
        
        jTextField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search by First Name");
        jTextField4.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search by Last Name");
        jTextField2.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search by NIC");
        jTextField3.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search by Mobile");
        
        Icon icon = new ImageIcon("src/resources/Search.png");
        jTextField1.putClientProperty("JTextField.trailingIcon", icon);
        Icon icon2 = new ImageIcon("src/resources/Search.png");
        jTextField2.putClientProperty("JTextField.trailingIcon", icon2);
        Icon icon3 = new ImageIcon("src/resources/Search.png");
        jTextField3.putClientProperty("JTextField.trailingIcon", icon3);
        Icon icon4 = new ImageIcon("src/resources/Search.png");
        jTextField4.putClientProperty("JTextField.trailingIcon", icon4);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "First Name", "Last Name", "NIC", "email", "Mobile", "Register Date", "Address Line1", "Address Line2", "City", "Gender", "Customer Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Customer Details");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Sort :");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "First Name ASC", "First Name DESC", "Reg_date ASC", "Reg_date DESC", "Mobile ASC", "Mobile DESC", "City ASC", "City DESC" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 153, 0));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Reset All");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(47, 47, 47)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(26, 26, 26)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(423, 423, 423)
                        .addComponent(jLabel1)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        loadCustomer2("fname", "ASC");
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        if (jComboBox1.getSelectedIndex() == 0) {
            loadCustomer2("fname", "ASC");
        } else if (jComboBox1.getSelectedIndex() == 1) {
            loadCustomer2("fname", "DESC");
        } else if (jComboBox1.getSelectedIndex() == 2) {
            loadCustomer2("registered_date", "ASC");
        } else if (jComboBox1.getSelectedIndex() == 3) {
            loadCustomer2("registered_date", "DESC");
        } else if (jComboBox1.getSelectedIndex() == 4) {
            loadCustomer2("mobile", "ASC");
        } else if (jComboBox1.getSelectedIndex() == 5) {
            loadCustomer2("mobile", "DESC");
        } else if (jComboBox1.getSelectedIndex() == 6) {
            loadCustomer2("`city`.`name`", "ASC");
        } else if (jComboBox1.getSelectedIndex() == 7) {
            loadCustomer2("`city`.`name`", "DESC");
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
            int row = jTable1.getSelectedRow();
            if (Cm != null) {
            
        if (evt.getClickCount() == 2) {
            Cm.getfname().setText(String.valueOf(jTable1.getValueAt(row, 0)));
            Cm.getlname().setText(String.valueOf(jTable1.getValueAt(row, 1)));
            Cm.getnic().setText(String.valueOf(jTable1.getValueAt(row, 2)));
            Cm.getmobile().setText(String.valueOf(jTable1.getValueAt(row, 4)));
            Cm.getgender().setSelectedItem(String.valueOf(jTable1.getValueAt(row, 9)));
            Cm.getstatus().setSelectedItem(String.valueOf(jTable1.getValueAt(row, 10)));
            Cm.getline1().setText(String.valueOf(jTable1.getValueAt(row, 6)));
            Cm.getline2().setText(String.valueOf(jTable1.getValueAt(row, 7)));
            Cm.getcity().setSelectedItem(String.valueOf(jTable1.getValueAt(row, 8)));
            
            Cm.getnic().setEditable(false);
            this.dispose();
            
        }
        }
        
        
        
        if (evt.getClickCount() == 3) {
       long randomNumber = System.currentTimeMillis();
        if(Cm2 != null){
        Cm2.getCustomerNic().setText(String.valueOf(jTable1.getValueAt(row, 2)));
        Cm2.getOrderId().setText("OD/" + randomNumber);
            this.dispose();
        }else{
            System.out.println("CM2 is Null");
        }
        
       String email = String.valueOf(jTable1.getValueAt(row, 3));
       
       
       
        }
     
    }//GEN-LAST:event_jTable1MouseClicked

    
    
    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        loadCustomer2("fname", "ASC");
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        loadCustomer2("fname", "ASC");
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        loadCustomer2("fname", "ASC");
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        reset();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseEntered

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(loadCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(loadCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(loadCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(loadCustomer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                loadCustomer dialog = new loadCustomer(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jTextField1.setText(" ");
        jTextField2.setText(" ");
        jTextField3.setText(" ");
        jTextField4.setText(" ");
        jComboBox1.setSelectedIndex(0);
    }
}
