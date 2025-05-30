/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.SalesDepartment;

import java.io.InputStream;
import java.util.HashMap;
import net.sf.jasperreports.engine.JasperFillManager;

import java.sql.ResultSet;

import java.sql.Connection;
import java.sql.DriverManager;
import model.MySQL;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Lenovo
 */
public class DatabaseReport extends javax.swing.JPanel {

    /**
     * Creates new form DatabaseReport
     */
    public DatabaseReport() {
        initComponents();
        design(); 
    }

    private void design() {
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
        jButton11.putClientProperty("JButton.buttonType", "roundRect");
        jButton12.putClientProperty("JButton.buttonType", "roundRect");
        jButton13.putClientProperty("JButton.buttonType", "roundRect");
        jButton14.putClientProperty("JButton.buttonType", "roundRect");
        jButton15.putClientProperty("JButton.buttonType", "roundRect");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 204)), "DATABASE REPORT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(0, 51, 204))); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102)), "Customer Management"));

        jButton1.setText("Active Customer Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Inactive Customer Report");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102)), "Order Management"));

        jButton3.setText("Pending Order Report");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Complete Order Report");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Cancel Order Report");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Return Order Report");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102)), "Sales and Invoice"));

        jButton7.setText("Sales Invoice Report");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Top Selling Products");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setText("Customer Purchase Report");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Mounthly Sales Performance");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Sales Growth Report");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText(" Profit Margin Report");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setText("Current Month Sales Report");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Unsold Products Report");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Sales Item Breakdown by Sale");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                    .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(78, 78, 78)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Connection connection = null;
        try {
            InputStream path = this.getClass().getResourceAsStream("/reports/Sales/Customer_report.jasper");
            if (path == null) {
                throw new IllegalArgumentException("Report file not found at the specified path.");
            }

            HashMap<String, Object> parameters = new HashMap<>();

            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teafactorydb", "root", "mr3175SAM@");

            JasperPrint report = JasperFillManager.fillReport(path, parameters, connection);

            JasperViewer.viewReport(report, false);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        Connection connection = null;
        try {
            // Get the report file path
            InputStream path = this.getClass().getResourceAsStream("/reports/Sales/Inactive_Customer_report.jasper");
            if (path == null) {
                throw new IllegalArgumentException("Report file not found at the specified path.");
            }

            // Create an empty parameter map
            HashMap<String, Object> parameters = new HashMap<>();

            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teafactorydb", "root", "mr3175SAM@");
            // Fill the report
            JasperPrint report = JasperFillManager.fillReport(path, parameters, connection);

            // View the report
            JasperViewer.viewReport(report, false);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure the connection is closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Connection connection = null;
        try {
            // Get the report file path
            InputStream path = this.getClass().getResourceAsStream("/reports/Sales/pending_order_report.jasper");
            if (path == null) {
                throw new IllegalArgumentException("Report file not found at the specified path.");
            }

            // Create an empty parameter map
            HashMap<String, Object> parameters = new HashMap<>();

            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teafactorydb", "root", "mr3175SAM@");

            // Fill the report
            JasperPrint report = JasperFillManager.fillReport(path, parameters, connection);

            // View the report
            JasperViewer.viewReport(report, false);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure the connection is closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        Connection connection = null;
        try {
            // Get the report file path
            InputStream path = this.getClass().getResourceAsStream("/reports/Sales/complete_order_report.jasper");
            if (path == null) {
                throw new IllegalArgumentException("Report file not found at the specified path.");
            }

            // Create an empty parameter map
            HashMap<String, Object> parameters = new HashMap<>();

            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teafactorydb", "root", "mr3175SAM@");

            // Fill the report
            JasperPrint report = JasperFillManager.fillReport(path, parameters, connection);

            // View the report
            JasperViewer.viewReport(report, false);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure the connection is closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Connection connection = null;
        try {
            // Get the report file path
            InputStream path = this.getClass().getResourceAsStream("/reports/Sales/cancel_order_report.jasper");
            if (path == null) {
                throw new IllegalArgumentException("Report file not found at the specified path.");
            }

            // Create an empty parameter map
            HashMap<String, Object> parameters = new HashMap<>();

            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teafactorydb", "root", "mr3175SAM@");

            // Fill the report
            JasperPrint report = JasperFillManager.fillReport(path, parameters, connection);

            // View the report
            JasperViewer.viewReport(report, false);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure the connection is closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        Connection connection = null;
        try {
            // Get the report file path
            InputStream path = this.getClass().getResourceAsStream("/reports/Sales/return_order_report.jasper");
            if (path == null) {
                throw new IllegalArgumentException("Report file not found at the specified path.");
            }

            // Create an empty parameter map
            HashMap<String, Object> parameters = new HashMap<>();

            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teafactorydb", "root", "mr3175SAM@");

            // Fill the report
            JasperPrint report = JasperFillManager.fillReport(path, parameters, connection);

            // View the report
            JasperViewer.viewReport(report, false);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure the connection is closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        Connection connection = null;
        try {
            // Get the report file path
            InputStream path = this.getClass().getResourceAsStream("/reports/Sales/sales_invoice_report.jasper");
            if (path == null) {
                throw new IllegalArgumentException("Report file not found at the specified path.");
            }

            // Create an empty parameter map
            HashMap<String, Object> parameters = new HashMap<>();

            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teafactorydb", "root", "mr3175SAM@");

            // Fill the report
            JasperPrint report = JasperFillManager.fillReport(path, parameters, connection);

            // View the report
            JasperViewer.viewReport(report, false);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure the connection is closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        Connection connection = null;
        try {
            // Get the report file path
            InputStream path = this.getClass().getResourceAsStream("/reports/Sales/top_product_report.jasper");
            if (path == null) {
                throw new IllegalArgumentException("Report file not found at the specified path.");
            }

            // Create an empty parameter map
            HashMap<String, Object> parameters = new HashMap<>();

            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teafactorydb", "root", "mr3175SAM@");

            // Fill the report
            JasperPrint report = JasperFillManager.fillReport(path, parameters, connection);

            // View the report
            JasperViewer.viewReport(report, false);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure the connection is closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        Connection connection = null;
        try {
            // Get the report file path
            InputStream path = this.getClass().getResourceAsStream("/reports/Sales/customer_purches_report.jasper");
            if (path == null) {
                throw new IllegalArgumentException("Report file not found at the specified path.");
            }

            // Create an empty parameter map
            HashMap<String, Object> parameters = new HashMap<>();

            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teafactorydb", "root", "mr3175SAM@");

            // Fill the report
            JasperPrint report = JasperFillManager.fillReport(path, parameters, connection);

            // View the report
            JasperViewer.viewReport(report, false);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure the connection is closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        Connection connection = null;
        try {
            // Get the report file path
            InputStream path = this.getClass().getResourceAsStream("/reports/Sales/monthSales_performance_report.jasper");
            if (path == null) {
                throw new IllegalArgumentException("Report file not found at the specified path.");
            }

            // Create an empty parameter map
            HashMap<String, Object> parameters = new HashMap<>();

            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teafactorydb", "root", "mr3175SAM@");

            // Fill the report
            JasperPrint report = JasperFillManager.fillReport(path, parameters, connection);

            // View the report
            JasperViewer.viewReport(report, false);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure the connection is closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        Connection connection = null;
        try {
            // Get the report file path
            InputStream path = this.getClass().getResourceAsStream("/reports/Sales/sales_growth_report.jasper");
            if (path == null) {
                throw new IllegalArgumentException("Report file not found at the specified path.");
            }

            // Create an empty parameter map
            HashMap<String, Object> parameters = new HashMap<>();

            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teafactorydb", "root", "mr3175SAM@");

            // Fill the report
            JasperPrint report = JasperFillManager.fillReport(path, parameters, connection);

            // View the report
            JasperViewer.viewReport(report, false);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure the connection is closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        Connection connection = null;
        try {
            // Get the report file path
            InputStream path = this.getClass().getResourceAsStream("/reports/Sales/profit_report.jasper");
            if (path == null) {
                throw new IllegalArgumentException("Report file not found at the specified path.");
            }

            // Create an empty parameter map
            HashMap<String, Object> parameters = new HashMap<>();

            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teafactorydb", "root", "mr3175SAM@");

            // Fill the report
            JasperPrint report = JasperFillManager.fillReport(path, parameters, connection);

            // View the report
            JasperViewer.viewReport(report, false);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure the connection is closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        Connection connection = null;
        try {
            // Get the report file path
            InputStream path = this.getClass().getResourceAsStream("/reports/Sales/current_mounth_sales_report.jasper");
            if (path == null) {
                throw new IllegalArgumentException("Report file not found at the specified path.");
            }

            // Create an empty parameter map
            HashMap<String, Object> parameters = new HashMap<>();

            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teafactorydb", "root", "mr3175SAM@");

            // Fill the report
            JasperPrint report = JasperFillManager.fillReport(path, parameters, connection);

            // View the report
            JasperViewer.viewReport(report, false);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure the connection is closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        Connection connection = null;
        try {
            // Get the report file path
            InputStream path = this.getClass().getResourceAsStream("/reports/Sales/unsold_product_report.jasper");
            if (path == null) {
                throw new IllegalArgumentException("Report file not found at the specified path.");
            }

            // Create an empty parameter map
            HashMap<String, Object> parameters = new HashMap<>();

            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teafactorydb", "root", "mr3175SAM@");

            // Fill the report
            JasperPrint report = JasperFillManager.fillReport(path, parameters, connection);

            // View the report
            JasperViewer.viewReport(report, false);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure the connection is closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        Connection connection = null;
        try {
            // Get the report file path
            InputStream path = this.getClass().getResourceAsStream("/reports/Sales/sales_item_report.jasper");
            if (path == null) {
                throw new IllegalArgumentException("Report file not found at the specified path.");
            }

            // Create an empty parameter map
            HashMap<String, Object> parameters = new HashMap<>();

            // Establish database connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teafactorydb", "root", "mr3175SAM@");

            // Fill the report
            JasperPrint report = JasperFillManager.fillReport(path, parameters, connection);

            // View the report
            JasperViewer.viewReport(report, false);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Ensure the connection is closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }    }//GEN-LAST:event_jButton15ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
}
