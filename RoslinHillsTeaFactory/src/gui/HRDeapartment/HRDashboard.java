
package gui.HRDeapartment;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import javax.swing.SwingUtilities;

import java.sql.*;
import javax.swing.JOptionPane;

public class HRDashboard extends javax.swing.JPanel {

    private static java.sql.Connection connection;

    public HRDashboard() {
        initComponents();
        
        jPanel4.removeAll();
        Charts1 c1 = new Charts1();
        jPanel4.add(c1);
        SwingUtilities.updateComponentTreeUI(jPanel4); 
        
        jButton1.putClientProperty("JButton.buttonType", "roundRect");
        jButton2.putClientProperty("JButton.buttonType", "roundRect");
        jButton3.putClientProperty("JButton.buttonType", "roundRect");
        jButton4.putClientProperty("JButton.buttonType", "roundRect");
       

    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();

        jPanel2.setPreferredSize(new java.awt.Dimension(1250, 657));

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(250, 670));

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Analytics – Part 1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Analytics – Part 2");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 70, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Update Payroll for New Month");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton4.setText("Reports");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 314, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41))
        );

        jPanel3.add(jPanel1, java.awt.BorderLayout.LINE_START);

        jPanel4.setLayout(new java.awt.CardLayout());
        jPanel3.add(jPanel4, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 1267, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 682, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jPanel4.removeAll();
        Charts1 c1 = new Charts1();
        jPanel4.add(c1);
        SwingUtilities.updateComponentTreeUI(jPanel4);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
         jPanel4.removeAll();
        Charts2 c1 = new Charts2();
        jPanel4.add(c1);
        SwingUtilities.updateComponentTreeUI(jPanel4);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
          try {
            // Database connection
            Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/teafactorydb", "root", "mr3175SAM@");

            // Get the current month and year
            LocalDate today = LocalDate.now();
            LocalDate startDate = today.with(TemporalAdjusters.firstDayOfMonth());
            LocalDate endDate = today.with(TemporalAdjusters.lastDayOfMonth());

            int currentMonth = today.getMonthValue();
            int currentYear = today.getYear();

            // Find employees without a payroll record for the current month
            String fetchEmployeesQuery = "SELECT * FROM employees WHERE nic NOT IN ("
                    + "SELECT employees_nic FROM payroll WHERE MONTH(start_date) = ? AND YEAR(start_date) = ?)";
            PreparedStatement fetchEmployeesStmt = conn.prepareStatement(fetchEmployeesQuery);
            fetchEmployeesStmt.setInt(1, currentMonth);
            fetchEmployeesStmt.setInt(2, currentYear);
            ResultSet employeeRs = fetchEmployeesStmt.executeQuery();

            // Prepare insert statement for new payroll records
            String insertPayrollQuery = "INSERT INTO payroll (start_date, end_date, overtime_hours, "
                    + "overtime_payment, deductions, bonuses, basic_salary, net_salary, pay_date, employees_nic) "
                    + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement insertPayrollStmt = conn.prepareStatement(insertPayrollQuery);

            // Loop through employees without payroll for the current month
            while (employeeRs.next()) {
                String employeeNic = employeeRs.getString("nic");
                Double employeeBasicSalary = employeeRs.getDouble("basic_salary");

                // Set payroll values
                insertPayrollStmt.setDate(1, Date.valueOf(startDate)); // start_date
                insertPayrollStmt.setDate(2, Date.valueOf(endDate));   // end_date
                insertPayrollStmt.setInt(3, 0);                        // overtime_hours
                insertPayrollStmt.setDouble(4, 0.0);                  // overtime_payment
                insertPayrollStmt.setDouble(5, 0.0);                  // deductions
                insertPayrollStmt.setDouble(6, 0.0);                  // bonuses
                insertPayrollStmt.setDouble(7, employeeBasicSalary);  // basic_salary
                insertPayrollStmt.setDouble(8, 0.0);                  // net_salary
                insertPayrollStmt.setNull(9, Types.DATE);             // pay_date
                insertPayrollStmt.setString(10, employeeNic);         // employees_nic

                // Execute insert
                insertPayrollStmt.executeUpdate();
                System.out.println("Payroll created for NIC: " + employeeNic);
            }

            JOptionPane.showMessageDialog(this, "Payroll initialization complete for the month: " + today.getMonth(), "", JOptionPane.INFORMATION_MESSAGE);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables

}
