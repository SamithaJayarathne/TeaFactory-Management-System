/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.inventory;

import com.formdev.flatlaf.FlatClientProperties;
import gui.home;
import gui.inventory.warehouse;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


/**
 *
 * @author Sahan
 */
public class wareManager extends javax.swing.JPanel {

    private HashMap<String, String> loadcity = new HashMap<>();
    private HashMap<String, String> loadgender = new HashMap<>();
    private HashMap<String, String> loadmanager = new HashMap<>();

    /**
     * Creates new form wareManager
     */
    public wareManager() {
        initComponents();
        loadcity();
        loadgender();
        loadManager();
        jTextField1.putClientProperty("JComponent.roundRect", true);
        jTextField1.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        jTextField2.putClientProperty("JComponent.roundRect", true);
        jTextField2.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        jTextField3.putClientProperty("JComponent.roundRect", true);
        jTextField3.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        jTextField4.putClientProperty("JComponent.roundRect", true);
        jTextField4.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        jTextField5.putClientProperty("JComponent.roundRect", true);
        jTextField5.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        jTextField6.putClientProperty("JComponent.roundRect", true);
        jTextField6.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        jTextField7.putClientProperty("JComponent.roundRect", true);
        jTextField7.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        jTextField8.putClientProperty("JComponent.roundRect", true);
        jTextField8.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        

        
        jButton1.putClientProperty("JButton.buttonType", "roundRect");
        jButton2.putClientProperty("JButton.buttonType", "roundRect");
        jButton3.putClientProperty("JButton.buttonType", "roundRect");
        jButton4.putClientProperty("JButton.buttonType", "roundRect");
        jButton5.putClientProperty("JButton.buttonType", "roundRect");
        jButton6.putClientProperty("JButton.buttonType", "roundRect");
        jButton7.putClientProperty("JButton.buttonType", "roundRect");
        jButton8.putClientProperty("JButton.buttonType", "roundRect");
        jButton9.putClientProperty("JButton.buttonType", "roundRect");
        jButton10.putClientProperty("JButton.buttonType", "roundRect");

    }

    public JTextField getfname() {
        return jTextField1;
    }

    public JTextField getlname() {
        return jTextField2;
    }

    public JTextField getnic() {
        return jTextField3;
    }

    public JTextField getmobile() {
        return jTextField4;
    }

    public JTextField getemail() {
        return jTextField5;
    }

    public JComboBox<String> getcity() {
        return jComboBox3;
    }

    public JComboBox<String> getgender() {
        return jComboBox2;
    }

    public JTextField getWname() {
        return jTextField6;
    }

    public JTextField getWAddress() {
        return jTextField7;
    }

    public JTextField getcapacity() {
        return jTextField8;
    }

    public JComboBox<String> getMcity() {
        return jComboBox6;
    }

    public JComboBox getMan() {
        return jComboBox5;
    }

    private void loadManager() {
        try {
            ResultSet resultSet = model.MySQL.executeSearch("SELECT * FROM `warehouse_managers`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                String fname =  resultSet.getString("nic");
                vector.add(fname);

                loadmanager.put(resultSet.getString("nic"), resultSet.getString("nic"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox5.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadcity() {

        try {

            ResultSet resultSet = model.MySQL.executeSearch("SELECT * FROM `city`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                loadcity.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox3.setModel(model);
            jComboBox6.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadgender() {

        try {

            ResultSet resultSet = model.MySQL.executeSearch("SELECT * FROM `gender`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                loadgender.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jComboBox6 = new javax.swing.JComboBox<>();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Personal Details ");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel7.setText("EMAIL   :");

        jTextField5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel2.setText("FIRST NAME  : ");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel3.setText("NIC :");

        jTextField3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Contact Details");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel5.setText("MOBILE :");

        jTextField4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel6.setText("GENDER");

        jComboBox2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel8.setText("LAST NAME  : ");

        jTextField2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel9.setText("CITY   :");

        jComboBox3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton1.setText("REGISTER");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton2.setText("UPDATE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton3.setText("DELETE");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton4.setText("CLEAR");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton5.setText("VIEW THE MANAGER");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("WareHouse Manager Registaion");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("WareHouse Registaion");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel13.setText("NAME       : ");

        jTextField6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel14.setText("ADDRESS :");

        jTextField7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel15.setText("CAPACITY : ");

        jTextField8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel16.setText("MANAGER");

        jComboBox5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel17.setText("CITY");

        jButton6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton6.setText("REGISTER");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton7.setText("UPDATE");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton8.setText("VIEW MANAGER");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton9.setText("DELETE");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jButton10.setText("CLEAR");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jComboBox6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(52, 52, 52)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                    .addComponent(jTextField7)
                    .addComponent(jTextField8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(104, 104, 104)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox6, 0, 308, Short.MAX_VALUE)
                            .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(141, 141, 141))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(126, 126, 126))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(553, 553, 553))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(81, 81, 81))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(55, 55, 55)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel8)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jLabel2)
                                .addGap(119, 119, 119)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(173, 173, 173)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel7))
                                            .addGap(32, 32, 32)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                                                    .addComponent(jTextField5))
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)))))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(142, 142, 142)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String fname = jTextField1.getText();
        String lname = jTextField2.getText();
        String nic = jTextField3.getText();
        String mobile = jTextField4.getText();
        String email = jTextField5.getText();
        String city = String.valueOf(jComboBox3.getSelectedItem());
        String gender = String.valueOf(jComboBox2.getSelectedItem());

        System.out.println(loadgender.get(gender));

        if (fname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your first name", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (lname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your first name", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (nic.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your Nic", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (city.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a city", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (gender.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a gender", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (mobile.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your mobile", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (!mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
            JOptionPane.showMessageDialog(this, "Please enter valid mobile", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your email", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")) {
            JOptionPane.showMessageDialog(this, "Please enter valid email", "Warning", JOptionPane.WARNING_MESSAGE);

        } else {
            try {
                ResultSet resultSet = model.MySQL.executeSearch("SELECT * FROM warehouse_managers WHERE `nic` = '" + nic + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Warehouse Manager already registerd", "Warning", JOptionPane.WARNING_MESSAGE);

                } else {

                    model.MySQL.executeIUD("INSERT INTO warehouse_managers(`nic`,`fname`,`lname`,`mobile`,`email`,`gender_id`,`city_id`) VALUES('"
                            + nic + "','"
                            + fname + "','"
                            + lname + "','"
                            + mobile + "','"
                            + email + "','"
                            + loadgender.get(gender) + "','"
                            + loadcity.get(city) + "')");
                    JOptionPane.showMessageDialog(this, "Success Fully Added", "Warning", JOptionPane.WARNING_MESSAGE);
                    reset();

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date currentDate = new Date();
                    String title = fname + " " + lname + ":" + "warehouse Manager Added successfully";
                    String departmentId = ("1");
                    String statusId = ("2");

                    model.MySQL.executeIUD("INSERT INTO notifications(`title`,`date`,`departments_id`,`notification_status_id`) VALUES('"
                            + title + "','"
                            + format.format(currentDate) + "',"
                            + departmentId + ","
                            + statusId + ")");

                    JOptionPane.showMessageDialog(this, "Notification", "Info", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        reset();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String fname = jTextField1.getText();
        String lname = jTextField2.getText();
        String nic = jTextField3.getText();
        jTextField3.setEditable(false);
        String mobile = jTextField4.getText();
        String email = jTextField5.getText();
        String city = String.valueOf(jComboBox3.getSelectedItem());
        String gender = String.valueOf(jComboBox2.getSelectedItem());

        System.out.println(loadgender.get(gender));
        if (fname.isEmpty()) {
            manager warM = new manager(new home(), true, this);
            warM.setVisible(true);
        } else if (fname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your first name", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (lname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your first name", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (nic.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your Nic", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (city.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a city", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (gender.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a gender", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (mobile.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your mobile", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (!mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
            JOptionPane.showMessageDialog(this, "Please enter valid mobile", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your email", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")) {
            JOptionPane.showMessageDialog(this, "Please enter valid email", "Warning", JOptionPane.WARNING_MESSAGE);

        } else {
            try {

                ResultSet resultSet = model.MySQL.executeSearch("SELECT * FROM warehouse_managers WHERE `nic` = '" + nic + "'");

                if (!resultSet.next()) {

                    JOptionPane.showMessageDialog(this, "Manage not registered yet", "Warning", JOptionPane.WARNING_MESSAGE);

                } else {

                    model.MySQL.executeIUD("UPDATE warehouse_managers SET `fname` = '" + fname + "',`lname` = '" + lname + "', `mobile` = '" + mobile + "', `email` = '" + email + "', `gender_id` = '" + loadgender.get(gender) + "', `city_id` = '" + loadcity.get(city) + "' WHERE `nic` = '" + nic + "'");
                    JOptionPane.showMessageDialog(this, "Manager updated successfully", "Info", JOptionPane.INFORMATION_MESSAGE);
                    reset();

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date currentDate = new Date();
                    String title = fname + " " + lname + ":" + "warehouse Manager Update successfully";
                    String departmentId = ("1");
                    String statusId = ("2");

                    model.MySQL.executeIUD("INSERT INTO notifications(`title`,`date`,`departments_id`,`notification_status_id`) VALUES('"
                            + title + "','"
                            + format.format(currentDate) + "',"
                            + departmentId + ","
                            + statusId + ")");

                    JOptionPane.showMessageDialog(this, "Notification", "Info", JOptionPane.INFORMATION_MESSAGE);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        manager warM = new manager(new home(), true, this);
        warM.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        String fname = jTextField1.getText();
        String lname = jTextField2.getText();
        String nic = jTextField3.getText();
        jTextField3.setEditable(false);
        String mobile = jTextField4.getText();
        String email = jTextField5.getText();
        String city = String.valueOf(jComboBox3.getSelectedItem());
        String gender = String.valueOf(jComboBox2.getSelectedItem());

        System.out.println(loadgender.get(gender));

        if (fname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your first name", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (lname.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your first name", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (nic.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your Nic", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (city.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a city", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (gender.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a gender", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (mobile.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your mobile", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (!mobile.matches("^07[01245678]{1}[0-9]{7}$")) {
            JOptionPane.showMessageDialog(this, "Please enter valid mobile", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your email", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (!email.matches("^(?=.{1,64}@)[A-Za-z0-9\\+_-]+(\\.[A-Za-z0-9\\+_-]+)*@[^-][A-Za-z0-9\\+-]+(\\.[A-Za-z0-9\\+-]+)*(\\.[A-Za-z]{2,})$")) {
            JOptionPane.showMessageDialog(this, "Please enter valid email", "Warning", JOptionPane.WARNING_MESSAGE);

        } else {
            try {

                ResultSet resultSet = model.MySQL.executeSearch("SELECT * FROM warehouse_managers WHERE `nic` = '" + nic + "'");

                if (fname.isEmpty()) {
                    manager warM = new manager(new home(), true, this);
                    warM.setVisible(true);
                } else if (resultSet.next()) {

                    JOptionPane.showMessageDialog(this, "Manage not registered yet", "Warning", JOptionPane.WARNING_MESSAGE);

                } else {

                    model.MySQL.executeIUD("DELETE FROM warehouse_managers WHERE fname = '" + fname + "' AND lname = '" + lname + "'");

                    JOptionPane.showMessageDialog(this, "Supplier deleted successfully", "Info", JOptionPane.INFORMATION_MESSAGE);
                    reset();

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date currentDate = new Date();
                    String title = fname + " " + lname + ":" + "warehouse Manager Delete";
                    String departmentId = ("1");
                    String statusId = ("2");

                    model.MySQL.executeIUD("INSERT INTO notifications(`title`,`date`,`departments_id`,`notification_status_id`) VALUES('"
                            + title + "','"
                            + format.format(currentDate) + "',"
                            + departmentId + ","
                            + statusId + ")");

                    JOptionPane.showMessageDialog(this, "Notification", "Info", JOptionPane.INFORMATION_MESSAGE);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String name = jTextField6.getText();
        String address = jTextField7.getText();
        String capacity = jTextField8.getText();

        String manager = String.valueOf(jComboBox5.getSelectedItem());
        String city = String.valueOf(jComboBox6.getSelectedItem());

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your first name", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Address", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (capacity.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter capacity", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (manager.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a manager", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (city.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a gender", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                ResultSet resultSet = model.MySQL.executeSearch("SELECT * FROM warehouses WHERE `city_id` = '" + city + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Warehouse Manager already registerd", "Warning", JOptionPane.WARNING_MESSAGE);

                } else {

                    model.MySQL.executeIUD("INSERT INTO warehouses(`name`,`Address`,`capacity`,`warehouse_managers_nic`,`city_id`) VALUES('"
                            + name + "','"
                            + address + "','"
                            + capacity + "','"
                            + loadmanager.get(manager) + "','"
                            + loadcity.get(city) + "')");
                    JOptionPane.showMessageDialog(this, "Success Fully Added", "Warning", JOptionPane.WARNING_MESSAGE);
                    resetW();

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date currentDate = new Date();
                    String title = name + " " + ":" + "warehouse  Added successfully";
                    String departmentId = ("1");
                    String statusId = ("2");

                    model.MySQL.executeIUD("INSERT INTO notifications(`title`,`date`,`departments_id`,`notification_status_id`) VALUES('"
                            + title + "','"
                            + format.format(currentDate) + "',"
                            + departmentId + ","
                            + statusId + ")");

                    JOptionPane.showMessageDialog(this, "Notification", "Info", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        String name = jTextField6.getText();
        String address = jTextField7.getText();
        String capacity = jTextField8.getText();

        String manager = String.valueOf(jComboBox5.getSelectedItem());
        String city = String.valueOf(jComboBox6.getSelectedItem());
        if (name.isEmpty()) {
            warehouse warM = new warehouse(new home(), true, this);
            warM.setVisible(true);
        } else if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your first name", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Address", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (capacity.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter capacity", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (manager.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a manager", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (city.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a gender", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                ResultSet resultSet = model.MySQL.executeSearch("SELECT * FROM warehouses WHERE `city_id` = '" + city + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Warehouse Manager already registerd", "Warning", JOptionPane.WARNING_MESSAGE);

                } else {

                    model.MySQL.executeIUD("UPDATE warehouses SET `name` = '" + name + "',`Address` = '" + address + "', `capacity` = '" + capacity + "', `warehouse_managers_nic` = '" + loadmanager.get(manager) + "',  `city_id` = '" + loadcity.get(city) + "' WHERE `name` = '" + name + "'");
                    JOptionPane.showMessageDialog(this, "Success Updare", "Warning", JOptionPane.WARNING_MESSAGE);
                    resetW();

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date currentDate = new Date();
                    String title = name + " " + ":" + "warehouse  Update successfully";
                    String departmentId = ("1");
                    String statusId = ("2");

                    model.MySQL.executeIUD("INSERT INTO notifications(`title`,`date`,`departments_id`,`notification_status_id`) VALUES('"
                            + title + "','"
                            + format.format(currentDate) + "',"
                            + departmentId + ","
                            + statusId + ")");

                    JOptionPane.showMessageDialog(this, "Notification", "Info", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        warehouse warehouse = new warehouse(new home(), true, this);
        warehouse.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        String name = jTextField6.getText();
        String address = jTextField7.getText();
        String capacity = jTextField8.getText();

        if (name.isEmpty()) {
            warehouse warM = new warehouse(new home(), true, this);
            warM.setVisible(true);
        } else if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your first name", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Address", "Warning", JOptionPane.WARNING_MESSAGE);

        } else if (capacity.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter capacity", "Warning", JOptionPane.WARNING_MESSAGE);

        } else {
            try {
                ResultSet resultSet = model.MySQL.executeSearch("SELECT * FROM warehouses WHERE `name` = '" + name + "'");

                if (resultSet.next()) {

                    model.MySQL.executeIUD("DELETE FROM warehouses WHERE name = '" + name + "'  ");
                    JOptionPane.showMessageDialog(this, "Success Fully Delete", "Warning", JOptionPane.WARNING_MESSAGE);
                    resetW();

                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    Date currentDate = new Date();
                    String title = name + " " + ":" + "warehouse  Delete";
                    String departmentId = ("1");
                    String statusId = ("2");

                    model.MySQL.executeIUD("INSERT INTO notifications(`title`,`date`,`departments_id`,`notification_status_id`) VALUES('"
                            + title + "','"
                            + format.format(currentDate) + "',"
                            + departmentId + ","
                            + statusId + ")");

                    JOptionPane.showMessageDialog(this, "Notification", "Info", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed
    private void resetW() {

        jTextField6.setText("");
        jTextField7.setText("");
        jTextField8.setText("");
        jComboBox6.setSelectedIndex(0);
        jComboBox5.setSelectedIndex(0);
        jTextField3.setEditable(true);

    }

    private void reset() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jTextField3.setEditable(true);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
