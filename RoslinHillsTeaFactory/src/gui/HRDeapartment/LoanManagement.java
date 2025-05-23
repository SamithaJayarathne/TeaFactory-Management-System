package gui.HRDeapartment;

import com.formdev.flatlaf.FlatClientProperties;
import gui.home;
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MySQL;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class LoanManagement extends javax.swing.JPanel {

    private static Double monthlyInstallment;
    private static Double fullRepayment;
    private static Double interestRate;
    private static Double totalInterest;

    public LoanManagement() {
        initComponents();
        loadRoundComp();
        loadPendingLoans();
        tableCenter();
        jTextField5.setEnabled(false);

    }

    private void tableCenter() {

        // Custom renderer to center-align cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        // Apply the renderer to all columns in jTable2
        for (int i = 0; i < jTable2.getColumnCount(); i++) {
            jTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

    }

    private void loadPendingLoans() {

        try {
            ResultSet Rs = MySQL.executeSearch("SELECT * FROM `loan` INNER JOIN `approvel_status` ON "
                    + "`loan`.`approvel_status_id`=`approvel_status`.`id` WHERE `approvel_status`.`id`='1'");

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

            while (Rs.next()) {

                Vector vector = new Vector();
                vector.add(Rs.getString("loan.employees_nic"));
                vector.add(Rs.getString("loan.requested_date"));
                vector.add(Rs.getString("loan.loan_amount"));
                vector.add(Rs.getString("loan.monthly"));

                model.addRow(vector);

            }
        } catch (Exception ex) {
            Logger.getLogger(AdvancePayments.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void calculate() {

        Double amount = Double.parseDouble(jTextField4.getText());
        Double months = Double.parseDouble((String) jComboBox1.getSelectedItem());

        Double rate = 0.00;

        if (!jTextField5.getText().isEmpty()) {

            rate = Double.parseDouble(jTextField5.getText());

            if (!jCheckBox1.isSelected()) {

                rate = 0.00;

            }

        }

        Double interest = (rate * amount) / 100;
        Double repayment = interest + amount;

        Double installment = repayment / months;

        DecimalFormat df = new DecimalFormat("#.00");

        monthlyInstallment = Double.parseDouble(df.format(installment));
        fullRepayment = Double.parseDouble(df.format(repayment));
        totalInterest = Double.parseDouble(df.format(interest));
        interestRate = rate;

        jLabel1.setText("Monthly Installment (LKR) : " + df.format(installment));
        jLabel17.setText("Repayment (LKR) : " + df.format(repayment));
    }

    private void loadRoundComp() {

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
        jTextArea1.putClientProperty("JComponent.roundRect", true);
        jTextArea1.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        jComboBox1.putClientProperty("JComponent.roundRect", true);

        jButton1.putClientProperty("JButton.buttonType", "roundRect");
        jButton2.putClientProperty("JButton.buttonType", "roundRect");
        jButton3.putClientProperty("JButton.buttonType", "roundRect");
        jButton4.putClientProperty("JButton.buttonType", "roundRect");

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel12 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jCheckBox1 = new javax.swing.JCheckBox();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("APPLY FOR A LOAN");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel3.setText("EMPLOYEE NIC : ");

        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel4.setText("EMPLOYEE NAME : ");

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel5.setText("BASIC SALARY :");

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel6.setText("AMOUNT :");

        jTextField4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(0, 0, 204));
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

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("APPLY");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(251, 97, 7));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("CLEAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 153));
        jLabel7.setText("LOAN MANAGEMENT");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel8.setText("REASON :");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel12.setText("REPAYMENT PERIOD :");

        jComboBox1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "12", "24", "36", "48", "60" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel13.setText("INTEREST :");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel14.setText("RATE (%) :");

        jTextField5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Monthly Installment : 0");
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 0, 204), 3, true));

        jButton3.setBackground(new java.awt.Color(251, 97, 7));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("CLEAR");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel9.setText("Ongoing loans ?  :        Y / N");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee NIC", "Date", "Amount", "Monthly Installment"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("PENDING LOAN APPLICATIONS");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("( Months )");

        jCheckBox1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jCheckBox1.setText("YES");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 0, 204));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Repayment (LKR) :  0");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 0, 0));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("\"Note: Monthly installments include both principal and interest, based on the loan terms.\"");

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("SEE LOAN DETAILS");
        jButton4.setMaximumSize(new java.awt.Dimension(157, 35));
        jButton4.setMinimumSize(new java.awt.Dimension(157, 35));
        jButton4.setPreferredSize(new java.awt.Dimension(157, 35));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(81, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField1)
                                        .addComponent(jTextField2)
                                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel10)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 100, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(379, 379, 379))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField4)
                                    .addComponent(jScrollPane3)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jComboBox1, 0, 158, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(52, 52, 52))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(61, 61, 61))
                            .addComponent(jScrollPane3))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCheckBox1))
                        .addGap(26, 26, 26))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel18))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jButton4.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 7, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 14, Short.MAX_VALUE))
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

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased

        String nic = jTextField1.getText();

        try {
            ResultSet employeeRs = MySQL.executeSearch("SELECT * FROM employees WHERE `nic`='" + nic + "'");

            if (employeeRs.next()) {
                // Set employee full name in jTextField2
                jTextField2.setText(employeeRs.getString("fname") + " " + employeeRs.getString("lname"));
                jTextField3.setText(employeeRs.getString("basic_salary"));

                ResultSet loanRs = MySQL.executeSearch("SELECT * FROM `loan` WHERE `employees_nic`='" + nic + "' AND `balance` > '0'");

                if (loanRs.next()) {

                    jLabel9.setText("Ongoing Loan /  Balance = " + loanRs.getDouble("balance"));
                    jLabel9.setForeground(Color.red);
                    jButton1.setEnabled(false);

                } else {

                    jLabel9.setText("Ongoing Loans /  No ");
                    jLabel9.setForeground(Color.green);

                }

            }

        } catch (Exception ex) {
            Logger.getLogger(AdvancePayments.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String nic = jTextField1.getText();
        String name = jTextField2.getText();
        String basic = jTextField3.getText();
        String amount = jTextField4.getText();
        String months = (String) jComboBox1.getSelectedItem();

        if (nic.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Please enter the employee's NIC", "", JOptionPane.WARNING_MESSAGE);

        } else if (amount.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Please enter a amount", "", JOptionPane.WARNING_MESSAGE);

        } else {

            String searchBasic = "";

            String reason = jTextArea1.getText();

            Double basicSalary = Double.parseDouble(basic);
            Double minSalary = (basicSalary * 3) / 4;

            Date today = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

            try {
                ResultSet searchRs = MySQL.executeSearch("SELECT * FROM `employees` WHERE `nic`='" + nic + "'");

                if (searchRs.next()) {

                    searchBasic = searchRs.getString("basic_salary");

                }

            } catch (Exception ex) {
                Logger.getLogger(LoanManagement.class.getName()).log(Level.SEVERE, null, ex);
            }

            if (nic.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Please enter the employee's NIC", "", JOptionPane.WARNING_MESSAGE);

            } else if (name.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Please enter a valid NIC", "", JOptionPane.WARNING_MESSAGE);

            } else if (!basic.equals(searchBasic)) {

                JOptionPane.showMessageDialog(this, "Entered NIC doesn't match to the other details. Please check again!", "", JOptionPane.WARNING_MESSAGE);

            } else if (amount.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Please enter a amount", "", JOptionPane.WARNING_MESSAGE);

            } else if (!amount.matches("\\d+")) {

                JOptionPane.showMessageDialog(this, "Please enter a valid amount", "", JOptionPane.WARNING_MESSAGE);

            } else if (reason.isEmpty()) {

                JOptionPane.showMessageDialog(this, "Please enter a reason for the requested loan", "", JOptionPane.WARNING_MESSAGE);

            } else if (minSalary <= monthlyInstallment) {

                JOptionPane.showMessageDialog(this, "75% of your salary must be greater than or equal to the monthly installment.", "", JOptionPane.WARNING_MESSAGE);

            } else {

                try {
                    // enter data for the loan table

                    MySQL.executeIUD("INSERT INTO `loan` (`employees_nic`, `approvel_status_id`, `loan_amount`, `monthly`, `reason`, "
                            + "`repayment_period`, `interest_rate`, `balance`, `requested_date`) "
                            + "VALUES ('" + nic + "', '" + 1 + "', '" + Double.parseDouble(amount) + "', '" + monthlyInstallment + "', '" + reason + "', "
                            + "'" + Integer.parseInt(months) + "', '" + interestRate + "', '" + fullRepayment + "', '" + format.format(today) + "')");

                    JOptionPane.showMessageDialog(this, "Successfully applied", "", JOptionPane.WARNING_MESSAGE);

                    reset();
                    loadPendingLoans();

                } catch (Exception ex) {
                    Logger.getLogger(LoanManagement.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        jButton1.setEnabled(true);
        jLabel9.setForeground(Color.black);
        jLabel9.setText("Ongoing loans ?  :        Y / N");
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        reset();


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

        if (evt.getClickCount() == 2) {

            int row = jTable2.getSelectedRow();

            String nic = String.valueOf(jTable2.getValueAt(row, 0));
            String date = String.valueOf(jTable2.getValueAt(row, 1));
            String amount = String.valueOf(jTable2.getValueAt(row, 2));
            String name = "";
            String installment = "";
            String salary = "";
            String repayment = "";
            String interestrate = "";
            String months = "";

            ResultSet employeeRs;
            try {
                employeeRs = MySQL.executeSearch("SELECT * FROM `employees` INNER JOIN `loan` ON "
                        + " `loan`.`employees_nic`=`employees`.`nic` INNER JOIN `approvel_status` ON "
                        + " `approvel_status`.`id`=`loan`.`approvel_status_id`"
                        + "WHERE `loan`.`employees_nic`='" + nic + "' AND `loan`.`approvel_status_id`='1'");
                if (employeeRs.next()) {

                    name = employeeRs.getString("employees.fname") + " " + employeeRs.getString("employees.lname");
                    installment = employeeRs.getString("loan.monthly");
                    salary = employeeRs.getString("employees.basic_salary");
                    repayment = employeeRs.getString("loan.balance");
                    interestrate = employeeRs.getString("loan.interest_rate");
                    months = employeeRs.getString("loan.repayment_period");

                }
            } catch (Exception ex) {
                Logger.getLogger(LeaveManagement.class.getName()).log(Level.SEVERE, null, ex);
            }

            HashMap<String, String> map = new HashMap<>();
            map.put("name", name);
            map.put("nic", nic);
            map.put("date", date);
            map.put("amount", amount);
            map.put("installment", installment);
            map.put("salary", salary);
            map.put("repayment", repayment);
            map.put("interestRate", interestrate);
            map.put("months", months);

            ApproveLoans dlg = new ApproveLoans(new home(), true, map);
            dlg.setVisible(true);

        }

    }//GEN-LAST:event_jTable2MouseClicked

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged

        if (jTextField4.getText().isEmpty()) {

        } else {
            calculate();

        }


    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased

        String typedAmount = jTextField4.getText().trim();

        if (typedAmount.matches("\\d+(\\.\\d{1,2})?")) {
            calculate();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid amount.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            jTextField4.setText("");
        }

    }//GEN-LAST:event_jTextField4KeyReleased

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed

        calculate();

        if (jCheckBox1.isSelected()) {

            jTextField5.setEnabled(true);

        } else {

            jTextField5.setEnabled(false);

        }


    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        String rate = jTextField5.getText().trim();

        if (rate.matches("\\d+(\\.\\d{1,2})?")) {
            calculate();
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a valid rate.", "Invalid Input", JOptionPane.WARNING_MESSAGE);
            jTextField5.setText("");
        }
    }//GEN-LAST:event_jTextField5KeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        AllLoansView view = new AllLoansView(new home(), true, this);
        view.setVisible(true);
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jButton1.setEnabled(true);
        jLabel9.setForeground(Color.black);
        jLabel9.setText("Ongoing loans ?  :        Y / N");
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");

        jTextField4.setText("");
        jTextField5.setText("");
        jTextArea1.setText("");
        jComboBox1.setSelectedIndex(0);
        jCheckBox1.setSelected(false);
        jLabel1.setText("Monthly Installment : 0");
        jLabel17.setText("Repayment (LKR) :  0");
    }
}
