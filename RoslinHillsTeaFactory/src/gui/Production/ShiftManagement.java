/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.Production;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.MySQL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Kavindu Anupama
 */
public class ShiftManagement extends javax.swing.JPanel {

    /**
     * Creates new form ShiftManagement
     */
    public ShiftManagement() {
        initComponents();
        loadEmployees();
        loadShiftTimes();
        loadScheduleTable();
        loadCurrentWeekSchedule();
    }

    HashMap<String, String> shiftMap = new HashMap<>();

    private void loadShiftTimes() {

        try {

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `shift_status`");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                shiftMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            jComboBox1.setModel(new DefaultComboBoxModel<>(vector));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEmployees() {

        try {

            ResultSet rs = MySQL.executeSearch("SELECT * FROM `employees`");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int i = 0; i < jTable1.getColumnCount(); i++) {
                jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            while (rs.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(rs.getString("nic"));
                vector.add(rs.getString("fname"));
                vector.add(rs.getString("lname"));
                vector.add(rs.getString("mobile"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadScheduleTable() {
        try {
            String selectedDay = jComboBox2.getSelectedItem().toString();

            // Get the current date
            LocalDate today = LocalDate.now();

            // Get the start (Monday) and end (Sunday) of the current week
            LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
            LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);

            // SQL Query to filter shifts by the selected day and the current week
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `shift` "
                    + "INNER JOIN `employees` ON `employees`.`nic`=`shift`.`employees_nic` "
                    + "INNER JOIN `shift_status` ON `shift_status`.`id`=`shift`.`shift_status_id` "
                    + "WHERE DAYNAME(`shift`.`date`) = '" + selectedDay + "' "
                    + "AND `shift`.`date` BETWEEN '" + startOfWeek.toString() + "' AND '" + endOfWeek.toString() + "' "
                    + "ORDER BY `shift`.`date` DESC");

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int i = 0; i < jTable2.getColumnCount(); i++) {
                jTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            while (rs.next()) {
                String fname = rs.getString("employees.fname");
                String lname = rs.getString("employees.lname");
                Vector<String> vector = new Vector<>();
                vector.add(rs.getString("employees_nic"));
                vector.add(fname + " " + lname);
                vector.add(rs.getString("shift.date"));
                vector.add(rs.getString("shift_status.name"));
                model.addRow(vector);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadCurrentWeekSchedule() {
        try {
            // Get the current date
            LocalDate today = LocalDate.now();

            // Get the start (Monday) and end (Sunday) of the current week
            LocalDate startOfWeek = today.with(DayOfWeek.MONDAY);
            LocalDate endOfWeek = today.with(DayOfWeek.SUNDAY);

            // SQL Query to get the schedule filtered by the date range
            String query = "SELECT * FROM `shift` "
                    + "INNER JOIN `employees` ON `employees`.`nic`=`shift`.`employees_nic` "
                    + "INNER JOIN `shift_status` ON `shift_status`.`id`=`shift`.`shift_status_id` "
                    + "WHERE `shift`.`date` BETWEEN '" + Date.valueOf(startOfWeek) + "' AND '" + Date.valueOf(endOfWeek) + "' "
                    + "ORDER BY `shift`.`date` DESC";

            ResultSet rs = MySQL.executeSearch(query);

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

            for (int i = 0; i < jTable2.getColumnCount(); i++) {
                jTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            while (rs.next()) {
                Vector<String> vector = new Vector<>();
                String fname = rs.getString("employees.fname");
                String lname = rs.getString("employees.lname");
                vector.add(rs.getString("employees_nic"));
                vector.add(fname + " " + lname);
                vector.add(rs.getString("shift.date"));
                vector.add(rs.getString("shift_status.name"));
                model.addRow(vector);
            }

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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NIC", "First Name", "Last Name", "mobile"
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
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Available workers");

        jLabel4.setText("Select Date");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", " " }));

        jLabel5.setText("Select Shift Time");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Add to Schedule");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "NIC", "Name", "Schedule date", "Shift Time"
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
        jScrollPane3.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Worker Schedule");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton2.setText("Update Schedule");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton3.setText("Delete Schedule");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jButton4.setText("Veiw All");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton5.setText("Generate Report");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(56, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(90, 90, 90)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 131, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(193, 193, 193)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 538, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(49, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jComboBox1)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)))
                .addContainerGap(69, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        try {
            int row = jTable1.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a Row to Select Worker", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String shift = String.valueOf(jComboBox1.getSelectedItem());

            if (shift.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Select a Shift to Schedule", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (jDateChooser1.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Select a Date to Schedule", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Get the selected date as a string
            java.util.Date utilSelectedDate = jDateChooser1.getDate();
            LocalDate selectedDate = utilSelectedDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            String date = selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // Directly in correct format

            // Get the current date
            LocalDate currentDate = LocalDate.now();

            // Check if the selected date is in the past
            if (selectedDate.isBefore(currentDate)) {
                JOptionPane.showMessageDialog(this, "Selected date is in the past. Please select a future date.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String nic = String.valueOf(jTable1.getValueAt(row, 0));

            // **Check if the shift already exists for this worker on the same date**
            ResultSet rs = MySQL.executeSearch("SELECT COUNT(*) AS count FROM `shift` "
                    + "WHERE `date` = '" + date + "' AND `shift_status_id` = '" + shiftMap.get(shift) + "' "
                    + "AND `employees_nic` = '" + nic + "'");

            if (rs.next() && rs.getInt("count") > 0) {
                JOptionPane.showMessageDialog(this, "This worker is already scheduled for this shift on the selected date.", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Insert the new shift if no duplicate exists
            MySQL.executeIUD("INSERT INTO `shift` (`date`,`shift_status_id`,`employees_nic`) "
                    + "VALUES ('" + date + "','" + shiftMap.get(shift) + "','" + nic + "')");

            JOptionPane.showMessageDialog(this, "Shift Scheduled Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadScheduleTable();
            loadCurrentWeekSchedule();
            reset();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        try {
            int row = jTable2.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a Row From Worker Schedule Table to Update", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String shift = String.valueOf(jComboBox1.getSelectedItem());

            if (shift.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Select a Shift to Schedule", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String shift_time = String.valueOf(jTable2.getValueAt(row, 3));
            String nic = String.valueOf(jTable1.getValueAt(row, 0));

            java.util.Date utilSelectedDate = jDateChooser1.getDate();
            LocalDate selectedDate = utilSelectedDate.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
            String date = selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")); // Directly in correct format

            if (shift_time.equals(shift)) {
                JOptionPane.showMessageDialog(this, "Nothing to Update", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                ResultSet rs = MySQL.executeSearch("SELECT COUNT(*) AS count FROM `shift` "
                        + "WHERE `date` = '" + date + "' AND `shift_status_id` = '" + shiftMap.get(shift) + "' "
                        + "AND `employees_nic` = '" + nic + "'");

                if (rs.next() && rs.getInt("count") > 0) {
                    JOptionPane.showMessageDialog(this, "This worker is already scheduled for this shift on the selected date.", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                MySQL.executeIUD("UPDATE `shift` SET `shift_status_id` = '" + shiftMap.get(shift) + "' WHERE `employees_nic` = '" + nic + "'");

                JOptionPane.showMessageDialog(this, "Shift Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                loadScheduleTable();
                loadCurrentWeekSchedule();
                reset();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

        int row = jTable2.getSelectedRow();
        jDateChooser1.setEnabled(false);

        jComboBox1.setSelectedItem(String.valueOf(jTable2.getValueAt(row, 3)));

        String date = String.valueOf(jTable2.getValueAt(row, 2));
        SimpleDateFormat date2 = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date3 = null;
        try {
            date3 = date2.parse(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        jDateChooser1.setDate(date3);

    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        try {
            int row = jTable2.getSelectedRow();

            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a Row From Worker Schedule Table to Delete", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String nic = String.valueOf(jTable1.getValueAt(row, 0));
            String shift = String.valueOf(jTable2.getValueAt(row, 3));

            MySQL.executeIUD("DELETE FROM `shift` WHERE `employees_nic`='" + nic + "' AND `shift_status_id`='" + shiftMap.get(shift) + "'");
            JOptionPane.showMessageDialog(this, "Shift Deleted Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadScheduleTable();
            loadCurrentWeekSchedule();
            reset();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        jDateChooser1.setEnabled(true);
        reset();
    }//GEN-LAST:event_jTable1MouseClicked

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        loadScheduleTable();
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        loadCurrentWeekSchedule();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        try {
            String path = "src//reports//workerShifts.jasper";
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date());
            String time = new SimpleDateFormat("HH:mm:ss").format(new java.util.Date());

            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", date);
            params.put("Parameter2", time);

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable2.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, params, dataSource);

            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables

    private void reset() {

        jComboBox1.setSelectedIndex(0);
        jDateChooser1.setDate(null);

    }

}
