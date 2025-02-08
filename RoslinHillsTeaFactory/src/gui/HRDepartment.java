/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class HRDepartment extends javax.swing.JPanel {

    public HRDepartment() {
        initComponents();
        toggleButtonBachground();

    }

    private void toggleButtonBachground() {
        jToggleButton1.setContentAreaFilled(false);
        jToggleButton1.setOpaque(false);
        jToggleButton1.setBorderPainted(false);
        jToggleButton2.setContentAreaFilled(false);
        jToggleButton2.setOpaque(false);
        jToggleButton2.setBorderPainted(false);
        jToggleButton3.setContentAreaFilled(false);
        jToggleButton3.setOpaque(false);
        jToggleButton3.setBorderPainted(false);
        jToggleButton4.setContentAreaFilled(false);
        jToggleButton4.setOpaque(false);
        jToggleButton4.setBorderPainted(false);
        jToggleButton5.setContentAreaFilled(false);
        jToggleButton5.setOpaque(false);
        jToggleButton5.setBorderPainted(false);
        jToggleButton6.setContentAreaFilled(false);
        jToggleButton6.setOpaque(false);
        jToggleButton6.setBorderPainted(false);
        jToggleButton7.setContentAreaFilled(false);
        jToggleButton7.setOpaque(false);
        jToggleButton7.setBorderPainted(false);
    }

    public JLabel getJlabel2() {

        return jLabel9;

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel12 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jToggleButton2 = new javax.swing.JToggleButton();
        jToggleButton3 = new javax.swing.JToggleButton();
        jToggleButton4 = new javax.swing.JToggleButton();
        jToggleButton5 = new javax.swing.JToggleButton();
        jToggleButton6 = new javax.swing.JToggleButton();
        jToggleButton7 = new javax.swing.JToggleButton();
        jToggleButton1 = new javax.swing.JToggleButton();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(204, 255, 204));
        jPanel3.setPreferredSize(new java.awt.Dimension(1250, 30));
        jPanel3.setLayout(new java.awt.GridLayout(1, 7));

        jToggleButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jToggleButton2.setText("Dashboard");
        jToggleButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jToggleButton2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jToggleButton2MouseExited(evt);
            }
        });
        jToggleButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jToggleButton2);

        jToggleButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jToggleButton3.setText("Employee Management");
        jToggleButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jToggleButton3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jToggleButton3MouseExited(evt);
            }
        });
        jToggleButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jToggleButton3);

        jToggleButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jToggleButton4.setText("Attendance ");
        jToggleButton4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jToggleButton4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jToggleButton4MouseExited(evt);
            }
        });
        jToggleButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton4ActionPerformed(evt);
            }
        });
        jPanel3.add(jToggleButton4);

        jToggleButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jToggleButton5.setText("Leave Management");
        jToggleButton5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jToggleButton5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jToggleButton5MouseExited(evt);
            }
        });
        jToggleButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton5ActionPerformed(evt);
            }
        });
        jPanel3.add(jToggleButton5);

        jToggleButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jToggleButton6.setText("Advance Payments");
        jToggleButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jToggleButton6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jToggleButton6MouseExited(evt);
            }
        });
        jToggleButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton6ActionPerformed(evt);
            }
        });
        jPanel3.add(jToggleButton6);

        jToggleButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jToggleButton7.setText("Overtime Management");
        jToggleButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jToggleButton7MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jToggleButton7MouseExited(evt);
            }
        });
        jToggleButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton7ActionPerformed(evt);
            }
        });
        jPanel3.add(jToggleButton7);

        jToggleButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jToggleButton1.setText("Payroll Management");
        jToggleButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jToggleButton1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jToggleButton1MouseExited(evt);
            }
        });
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jToggleButton1);

        jPanel2.add(jPanel3, java.awt.BorderLayout.PAGE_START);

        jPanel8.setLayout(new java.awt.BorderLayout());

        jPanel9.setPreferredSize(new java.awt.Dimension(1250, 5));
        jPanel9.setLayout(new java.awt.GridLayout(1, 7));

        jLabel8.setOpaque(true);
        jLabel8.setPreferredSize(new java.awt.Dimension(0, 5));
        jPanel9.add(jLabel8);

        jLabel9.setBackground(new java.awt.Color(0, 204, 51));
        jPanel9.add(jLabel9);
        jPanel9.add(jLabel10);
        jPanel9.add(jLabel11);
        jPanel9.add(jLabel12);
        jPanel9.add(jLabel13);
        jPanel9.add(jLabel14);

        jPanel8.add(jPanel9, java.awt.BorderLayout.PAGE_START);

        jPanel10.setPreferredSize(new java.awt.Dimension(1250, 585));
        jPanel10.setLayout(new java.awt.CardLayout());
        jPanel8.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel8, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 985, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jToggleButton2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton2MouseEntered
        jLabel8.setOpaque(true);
        jLabel8.setBackground(new Color(0, 204, 51));
        Border border = BorderFactory.createLineBorder(new Color(225, 252, 204), 1);
        jLabel8.setBorder(border);
    }//GEN-LAST:event_jToggleButton2MouseEntered

    private void jToggleButton2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton2MouseExited
        if (jToggleButton2.isSelected()) {

        } else {
            jLabel8.setOpaque(false);
            jLabel8.setBorder(null);
        }
    }//GEN-LAST:event_jToggleButton2MouseExited

    private void jToggleButton3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton3MouseEntered
        jLabel9.setOpaque(true);
        jLabel9.setBackground(new Color(0, 204, 51));
        Border border = BorderFactory.createLineBorder(new Color(225, 252, 204), 1);
        jLabel9.setBorder(border);
    }//GEN-LAST:event_jToggleButton3MouseEntered

    private void jToggleButton3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton3MouseExited
        if (jToggleButton3.isSelected()) {

        } else {
            jLabel9.setOpaque(false);
            jLabel9.setBorder(null);
        }
    }//GEN-LAST:event_jToggleButton3MouseExited

    private void jToggleButton4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton4MouseEntered
        jLabel10.setOpaque(true);
        jLabel10.setBackground(new Color(0, 204, 51));
        Border border = BorderFactory.createLineBorder(new Color(225, 252, 204), 1);
        jLabel10.setBorder(border);
    }//GEN-LAST:event_jToggleButton4MouseEntered

    private void jToggleButton4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton4MouseExited
        if (jToggleButton4.isSelected()) {

        } else {
            jLabel10.setOpaque(false);
            jLabel10.setBorder(null);
        }
    }//GEN-LAST:event_jToggleButton4MouseExited

    private void jToggleButton5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton5MouseEntered
        jLabel11.setOpaque(true);
        jLabel11.setBackground(new Color(0, 204, 51));
        Border border = BorderFactory.createLineBorder(new Color(225, 252, 204), 1);
        jLabel11.setBorder(border);
    }//GEN-LAST:event_jToggleButton5MouseEntered

    private void jToggleButton5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton5MouseExited
        if (jToggleButton5.isSelected()) {

        } else {
            jLabel11.setOpaque(false);
            jLabel11.setBorder(null);
        }
    }//GEN-LAST:event_jToggleButton5MouseExited

    private void jToggleButton6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton6MouseEntered
        jLabel12.setOpaque(true);
        jLabel12.setBackground(new Color(0, 204, 51));
        Border border = BorderFactory.createLineBorder(new Color(225, 252, 204), 1);
        jLabel12.setBorder(border);
    }//GEN-LAST:event_jToggleButton6MouseEntered

    private void jToggleButton6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton6MouseExited
        if (jToggleButton6.isSelected()) {

        } else {
            jLabel12.setOpaque(false);
            jLabel12.setBorder(null);
        }
    }//GEN-LAST:event_jToggleButton6MouseExited

    private void jToggleButton7MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton7MouseEntered
        jLabel13.setOpaque(true);
        jLabel13.setBackground(new Color(0, 204, 51));
        Border border = BorderFactory.createLineBorder(new Color(225, 252, 204), 1);
        jLabel13.setBorder(border);
    }//GEN-LAST:event_jToggleButton7MouseEntered

    private void jToggleButton7MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton7MouseExited
        if (jToggleButton7.isSelected()) {

        } else {
            jLabel13.setOpaque(false);
            jLabel13.setBorder(null);
        }
    }//GEN-LAST:event_jToggleButton7MouseExited

    private void jToggleButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton2ActionPerformed
        jLabel9.setOpaque(false);
        jLabel9.setBorder(null);
        jLabel10.setOpaque(false);
        jLabel10.setBorder(null);
        jLabel11.setOpaque(false);
        jLabel11.setBorder(null);
        jLabel12.setOpaque(false);
        jLabel12.setBorder(null);
        jLabel13.setOpaque(false);
        jLabel13.setBorder(null);
        jLabel14.setOpaque(false);
        jLabel14.setBorder(null);
        
        jToggleButton1.setSelected(false);
        jToggleButton3.setSelected(false);
        jToggleButton4.setSelected(false);
        jToggleButton5.setSelected(false);
        jToggleButton6.setSelected(false);
        jToggleButton7.setSelected(false);

        
    }//GEN-LAST:event_jToggleButton2ActionPerformed

    private void jToggleButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton3ActionPerformed
       jLabel8.setOpaque(false);
        jLabel8.setBorder(null);
        jLabel10.setOpaque(false);
        jLabel10.setBorder(null);
        jLabel11.setOpaque(false);
        jLabel11.setBorder(null);
        jLabel12.setOpaque(false);
        jLabel12.setBorder(null);
        jLabel13.setOpaque(false);
        jLabel13.setBorder(null);
        jLabel14.setOpaque(false);
        jLabel14.setBorder(null);
        
        jToggleButton1.setSelected(false);
        jToggleButton2.setSelected(false);
        jToggleButton4.setSelected(false);
        jToggleButton5.setSelected(false);
        jToggleButton6.setSelected(false);
        jToggleButton7.setSelected(false);
        
        Employees employees = new Employees();
        jPanel10.removeAll();
        jPanel10.add(employees);
        SwingUtilities.updateComponentTreeUI(jPanel10);
    }//GEN-LAST:event_jToggleButton3ActionPerformed

    private void jToggleButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton4ActionPerformed
        jLabel9.setOpaque(false);
        jLabel9.setBorder(null);
        jLabel8.setOpaque(false);
        jLabel8.setBorder(null);
        jLabel11.setOpaque(false);
        jLabel11.setBorder(null);
        jLabel12.setOpaque(false);
        jLabel12.setBorder(null);
        jLabel13.setOpaque(false);
        jLabel13.setBorder(null);
        jLabel14.setOpaque(false);
        jLabel14.setBorder(null);
        
        jToggleButton1.setSelected(false);
        jToggleButton2.setSelected(false);
        jToggleButton3.setSelected(false);
        jToggleButton5.setSelected(false);
        jToggleButton6.setSelected(false);
        jToggleButton7.setSelected(false);
    }//GEN-LAST:event_jToggleButton4ActionPerformed

    private void jToggleButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton5ActionPerformed
        jLabel9.setOpaque(false);
        jLabel9.setBorder(null);
        jLabel10.setOpaque(false);
        jLabel10.setBorder(null);
        jLabel8.setOpaque(false);
        jLabel8.setBorder(null);
        jLabel12.setOpaque(false);
        jLabel12.setBorder(null);
        jLabel13.setOpaque(false);
        jLabel13.setBorder(null);
        jLabel14.setOpaque(false);
        jLabel14.setBorder(null);
        
        jToggleButton1.setSelected(false);
        jToggleButton3.setSelected(false);
        jToggleButton2.setSelected(false);
        jToggleButton4.setSelected(false);
        jToggleButton6.setSelected(false);
        jToggleButton7.setSelected(false);
    }//GEN-LAST:event_jToggleButton5ActionPerformed

    private void jToggleButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton6ActionPerformed
       jLabel9.setOpaque(false);
        jLabel9.setBorder(null);
        jLabel10.setOpaque(false);
        jLabel10.setBorder(null);
        jLabel11.setOpaque(false);
        jLabel11.setBorder(null);
        jLabel8.setOpaque(false);
        jLabel8.setBorder(null);
        jLabel13.setOpaque(false);
        jLabel13.setBorder(null);
        jLabel14.setOpaque(false);
        jLabel14.setBorder(null);
        
        jToggleButton1.setSelected(false);
        jToggleButton3.setSelected(false);
        jToggleButton4.setSelected(false);
        jToggleButton5.setSelected(false);
        jToggleButton2.setSelected(false);
        jToggleButton7.setSelected(false);
    }//GEN-LAST:event_jToggleButton6ActionPerformed

    private void jToggleButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton7ActionPerformed
        jLabel9.setOpaque(false);
        jLabel9.setBorder(null);
        jLabel10.setOpaque(false);
        jLabel10.setBorder(null);
        jLabel11.setOpaque(false);
        jLabel11.setBorder(null);
        jLabel12.setOpaque(false);
        jLabel12.setBorder(null);
        jLabel8.setOpaque(false);
        jLabel8.setBorder(null);
        jLabel14.setOpaque(false);
        jLabel14.setBorder(null);
        
        jToggleButton1.setSelected(false);
        jToggleButton3.setSelected(false);
        jToggleButton4.setSelected(false);
        jToggleButton5.setSelected(false);
        jToggleButton6.setSelected(false);
        jToggleButton2.setSelected(false);
    }//GEN-LAST:event_jToggleButton7ActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
        jLabel9.setOpaque(false);
        jLabel9.setBorder(null);
        jLabel10.setOpaque(false);
        jLabel10.setBorder(null);
        jLabel11.setOpaque(false);
        jLabel11.setBorder(null);
        jLabel12.setOpaque(false);
        jLabel12.setBorder(null);
        jLabel8.setOpaque(false);
        jLabel8.setBorder(null);
        jLabel13.setOpaque(false);
        jLabel13.setBorder(null);
        
        jToggleButton7.setSelected(false);
        jToggleButton3.setSelected(false);
        jToggleButton4.setSelected(false);
        jToggleButton5.setSelected(false);
        jToggleButton6.setSelected(false);
        jToggleButton2.setSelected(false);
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void jToggleButton1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton1MouseEntered
       jLabel14.setOpaque(true);
        jLabel14.setBackground(new Color(0, 204, 51));
        Border border = BorderFactory.createLineBorder(new Color(225, 252, 204), 1);
        jLabel14.setBorder(border);
    }//GEN-LAST:event_jToggleButton1MouseEntered

    private void jToggleButton1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jToggleButton1MouseExited
       if (jToggleButton1.isSelected()) {

        } else {
            jLabel14.setOpaque(false);
            jLabel14.setBorder(null);
        }
    }//GEN-LAST:event_jToggleButton1MouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JToggleButton jToggleButton3;
    private javax.swing.JToggleButton jToggleButton4;
    private javax.swing.JToggleButton jToggleButton5;
    private javax.swing.JToggleButton jToggleButton6;
    private javax.swing.JToggleButton jToggleButton7;
    // End of variables declaration//GEN-END:variables
}
