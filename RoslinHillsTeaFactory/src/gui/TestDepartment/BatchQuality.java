package gui.TestDepartment;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.MySQL;

/**
 *
 * @author j
 */
public class BatchQuality extends javax.swing.JPanel {

    private static HashMap<String, String> qualityStatusMap = new HashMap<>();
    private static HashMap<String, String> actionTakenMap = new HashMap<>();

    public BatchQuality() {
        initComponents();
        loadQualityStatus();
        loadActionTaken();
        loadBatchQuality();
        design();
    }

    private void loadQualityStatus() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `quality_status`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                qualityStatusMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox3.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadActionTaken() {

        try {
            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `action_taken`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                actionTakenMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            DefaultComboBoxModel model = new DefaultComboBoxModel(vector);
            jComboBox5.setModel(model);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadBatchQuality() {

        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `quality_control`"
                    + "INNER JOIN `employees` ON `quality_control`.`employees_nic` = `employees`.`nic`"
                    + "INNER JOIN `tea_batch` ON `quality_control`.`tea_batch_id` = `tea_batch`.`id`"
                    + "INNER JOIN `quality_status` ON `quality_control`.`quality_status_id` = `quality_status`.`id`"
                    + "INNER JOIN `action_taken` ON `quality_control`.`action_taken_id` = `action_taken`.`id`");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();

                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("tea_batch_id"));
                vector.add(resultSet.getString("employees_nic"));
                vector.add(resultSet.getString("date"));
                vector.add(resultSet.getString("quality_grade"));
                vector.add(resultSet.getString("taste_evaluation"));
                vector.add(resultSet.getString("quality_status.name"));
                vector.add(resultSet.getString("action_taken.name"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
     private void design(){
         
         jTextField1.putClientProperty("JComponent.roundRect", true);
         jTextField2.putClientProperty("JComponent.roundRect", true);
         jTextField3.putClientProperty("JComponent.roundRect", true);
         jTextField4.putClientProperty("JComponent.roundRect", true);
         jTextField5.putClientProperty("JComponent.roundRect", true);
         jComboBox3.putClientProperty("JComponent.roundRect", true);
         jComboBox5.putClientProperty("JComponent.roundRect", true);
         jButton2.putClientProperty("JButton.buttonType", "roundRect");
         jButton3.putClientProperty("JButton.buttonType", "roundRect");
         jButton5.putClientProperty("JButton.buttonType", "roundRect");
         jButton6.putClientProperty("JButton.buttonType", "roundRect");
         jButton7.putClientProperty("JButton.buttonType", "roundRect");
         clearall.putClientProperty("JButton.buttonType", "roundRect");
     }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        clearall = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1150, 577));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Batch Quality");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel2.setText("Date");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel3.setText("ID");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel4.setText("Batch");

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setText("Select");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel5.setText("Employee");

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setText("Select");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel6.setText("Quality Grade");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel7.setText("Taste Evaluation");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel8.setText("Status");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel9.setText("Action Taken");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Batch ID", "Employee", "Date", "Quality Grade", "Taste Evaluation", "Status", "Action Taken"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setMaximumSize(new java.awt.Dimension(32767, 32767));
        jTable1.setMinimumSize(new java.awt.Dimension(0, 0));
        jTable1.setPreferredSize(new java.awt.Dimension(1250, 577));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel2.setBackground(new java.awt.Color(102, 153, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setText("Add To Checking");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setText("Update Checking");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        clearall.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        clearall.setText("Clear All");
        clearall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearallActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 109, 109));
        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setText("Delete");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(clearall, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clearall, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/green-tea (1).png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(jLabel1)
                        .addGap(23, 23, 23)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
                                    .addComponent(jLabel6))
                                .addComponent(jLabel4)
                                .addComponent(jLabel3)
                                .addComponent(jLabel5)
                                .addComponent(jLabel7)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9))
                            .addGap(1011, 1011, 1011))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jComboBox5, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField5, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField2)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                                        .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addContainerGap(856, Short.MAX_VALUE)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(68, 68, 68)
                    .addComponent(jLabel3)
                    .addGap(47, 47, 47)
                    .addComponent(jLabel4)
                    .addGap(2, 2, 2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel6)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(2, 2, 2)
                    .addComponent(jLabel8)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jLabel9)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        String id = jTextField1.getText();
        String batch = jTextField2.getText();
        String employees = jTextField3.getText();
        Date date = jDateChooser1.getDate();
        String grade = jTextField4.getText();
        String taste = jTextField5.getText();
        String status = String.valueOf(jComboBox3.getSelectedItem());
        String action = String.valueOf(jComboBox5.getSelectedItem());

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your ID", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (batch.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your Batch", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (employees.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Employees", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (grade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Quality Grade", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (taste.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Taste Evaluation", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (status.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Quality Status", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (action.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Action Taken", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `quality_control` WHERE `id` = '" + id + "'");

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(this, "Batch Quality already registerd", "Warning", JOptionPane.WARNING_MESSAGE);
                } else {
                    
//                    Date date1 = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    MySQL.executeIUD("INSERT INTO `quality_control`(`id`,`date`,`quality_grade`,`taste_evaluation`,`employees_nic`,`tea_batch_id`,`quality_status_id`,`action_taken_id`)"
                            + "VALUES('" + id + "','" + sdf.format(date) + "','" + grade + "','" + taste + "','" + employees + "','" + batch + "','" + qualityStatusMap.get(status) + "','" + actionTakenMap.get(action) + "')");

                    reset();
                    loadBatchQuality();
                    
                    JOptionPane.showMessageDialog(this, "Batch Quality Successfully Added", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        String id = jTextField1.getText();
        String batch = jTextField2.getText();
        String employees = jTextField3.getText();
        Date date = jDateChooser1.getDate();
        String grade = jTextField4.getText();
        String taste = jTextField5.getText();
        String status = String.valueOf(jComboBox3.getSelectedItem());
        String action = String.valueOf(jComboBox5.getSelectedItem());

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your ID", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (batch.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your Batch", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (employees.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Employees", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (grade.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Quality Grade", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (taste.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Taste Evaluation", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (status.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Quality Status", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (action.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Action Taken", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {

                ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `quality_control` WHERE `tea_batch_id` = '" + batch + "'");
                
                boolean canUpdate = false;

                if (resultSet.next()) {
                    if (resultSet.getString("id").equals(id)) {
                        canUpdate = true;
                    } else {
                        JOptionPane.showMessageDialog(this, "Batch Quality already registerd", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                    
                } else {
                    canUpdate = true;
                }
                
                if (canUpdate) {
                    
                    Date date1 = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

                    MySQL.executeIUD("UPDATE `quality_control` SET `tea_batch_id` = '"+batch+"',`employees_nic` = '"+employees+"',"
                            + "`date` = '"+sdf.format(date)+"',`quality_grade` = '"+grade+"',`taste_evaluation` = '"+taste+"',"
                            + "`quality_status_id` = '"+ qualityStatusMap.get(status) +"',`action_taken_id` = '"+actionTakenMap.get(action)+"'"
                            + "WHERE `id` = '"+id+"'");

                    loadBatchQuality();
                    reset();
                    
                    JOptionPane.showMessageDialog(this, "Batch Quality Successfully Update", "Information", JOptionPane.INFORMATION_MESSAGE);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
        jButton3.setEnabled(false);
        
        int row = jTable1.getSelectedRow();
        
        String id = String.valueOf(jTable1.getValueAt(row, 0));
        jTextField1.setText(id);
        jTextField1.setEditable(false);
        
        String batch = String.valueOf(jTable1.getValueAt(row, 1));
        jTextField2.setText(batch);
        
        String employees = String.valueOf(jTable1.getValueAt(row, 2));
        jTextField3.setText(employees);
        
        String dateString = String.valueOf(jTable1.getValueAt(row, 3));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dateString);
            jDateChooser1.setDate(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String grade = String.valueOf(jTable1.getValueAt(row, 4));
        jTextField4.setText(grade);
        
        String taste = String.valueOf(jTable1.getValueAt(row, 5));
        jTextField5.setText(taste);
        
        String status = String.valueOf(jTable1.getValueAt(row, 6));
        jComboBox3.setSelectedItem(status);
        
        String action = String.valueOf(jTable1.getValueAt(row, 7));
        jComboBox5.setSelectedItem(action);
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
        int row = jTable1.getSelectedRow();
        
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please Select a Batch to Delete", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
        
            try {
                
                String id = String.valueOf(jTable1.getValueAt(row, 0));
                
                MySQL.executeIUD("DELETE FROM `quality_control` WHERE `id` = '"+id+"'");
                
                reset();
                loadBatchQuality();
                
                JOptionPane.showMessageDialog(this, "Batch Quality Deleted Successfully", "Information", JOptionPane.INFORMATION_MESSAGE);
                jButton3.setEnabled(true);
                jTextField1.setEditable(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void clearallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearallActionPerformed
        
        jTextField1.setEditable(true);
        jButton3.setEnabled(true);
        
        reset();
    }//GEN-LAST:event_clearallActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearall;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables

    private void reset(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jDateChooser1.setDate(null);
        jTextField4.setText("");
        jTextField5.setText("");
        jComboBox3.setSelectedIndex(0);
        jComboBox5.setSelectedIndex(0);
        
        jTable1.clearSelection();
        
        jTextField1.setEnabled(true);
        jButton3.setEnabled(true);
    }
}
