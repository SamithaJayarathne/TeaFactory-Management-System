/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.Research;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.MySQL;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Kavindu Anupama
 */
public class ResearchTeams extends javax.swing.JPanel {

    public ResearchTeams() {
        initComponents();
        loadTeamNames();
        loadProjects();
        loadResearchers();
        loadRoles();
        loadProjectTeams();
    }

    private void loadTeamNames() {
        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `team_name`");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int i = 0; i < jTable1.getColumnCount(); i++) {
                jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("name"));
                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadRoles() {
        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `role`");

            DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
            model.setRowCount(0);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int i = 0; i < jTable4.getColumnCount(); i++) {
                jTable4.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("name"));
                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadResearchers() {
        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `reseachers` INNER JOIN `employees` ON `reseachers`.`employees_nic`=`employees`.`nic`");

            DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
            model.setRowCount(0);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int i = 0; i < jTable5.getColumnCount(); i++) {
                jTable5.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("employees_nic"));
                vector.add(resultSet.getString("employees.fname") + " " + resultSet.getString("employees.lname"));
                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadProjects() {
        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `rs_projects`");

            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int i = 0; i < jTable3.getColumnCount(); i++) {
                jTable3.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("id"));
                vector.add(resultSet.getString("title"));
                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadProjectTeams() {
        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `project_team`"
                    + "INNER JOIN `role` ON `project_team`.`role_id`=`role`.`id`"
                    + "INNER JOIN `rs_projects` ON `project_team`.`rs_projects_id`=`rs_projects`.`id`"
                    + "INNER JOIN `reseachers` ON `project_team`.`reseachers_employees_nic`=`reseachers`.`employees_nic`"
                    + "INNER JOIN `employees` ON `reseachers`.`employees_nic`=`employees`.`nic`"
                    + "INNER JOIN `team_name` ON `project_team`.`team_name_id`=`team_name`.`id`");

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int i = 0; i < jTable2.getColumnCount(); i++) {
                jTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            while (resultSet.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(resultSet.getString("team_name.name"));
                vector.add(resultSet.getString("employees.fname") + " " + resultSet.getString("employees.lname"));
                vector.add(resultSet.getString("employees_nic"));
                vector.add(resultSet.getString("rs_projects.title"));
                vector.add(resultSet.getString("role.name"));
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

        jLabel30 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        jLabel30.setText("Project Teams");

        jLabel27.setText("Select Projects");

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Role ID", "Role Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable4);

        jLabel28.setText("Select Role");

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Researcher NIC", "Researcher Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(jTable5);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel29.setText("Select Researchers");

        jLabel1.setText("Team Names");

        jButton2.setText("Add To Team");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Add Team");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Project ID", "Title"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable3);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Team", "Researcher", "Researcher NIC", "Project Name", "Role"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        jButton3.setText("Delete From Team");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Generate Report");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(34, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton1))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel28)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 279, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel29)
                                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel30)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jLabel28)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton1)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(31, 31, 31))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel27)
                            .addComponent(jLabel29))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2))
                .addContainerGap(37, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

//    String roleName = String.valueOf(jComboBoxRole.getSelectedItem());
        int selectedProjectRow = jTable3.getSelectedRow();
        int selectedResearcherRow = jTable5.getSelectedRow();
        int selectedRoleRow = jTable4.getSelectedRow();
        int selectedTeamRow = jTable4.getSelectedRow();

        try {
            // Validation checks
            if (selectedProjectRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a project", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (selectedResearcherRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a researcher", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (selectedRoleRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a role", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (selectedTeamRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select a Team", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Get IDs from selections
            String projectId = String.valueOf(jTable3.getValueAt(selectedProjectRow, 0));
            String researcherNIC = String.valueOf(jTable5.getValueAt(selectedResearcherRow, 0));
            String roleId = String.valueOf(jTable4.getValueAt(selectedRoleRow, 0));
            String teamId = String.valueOf(jTable1.getValueAt(selectedRoleRow, 0));

            // Check for existing entry
            ResultSet checkRS = MySQL.executeSearch(
                    "SELECT * FROM `project_team` WHERE "
                    + "rs_projects_id = " + projectId + " AND "
                    + "reseachers_employees_nic = '" + researcherNIC + "' AND "
                    + "role_id = '" + roleId + "' AND "
                    + "team_name_id = " + teamId
            );

            if (checkRS.next()) {
                JOptionPane.showMessageDialog(this, "This combination already exists in team", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Insert new team member
            MySQL.executeIUD(
                    "INSERT INTO project_team (rs_projects_id, reseachers_employees_nic, role_id, team_name_id) "
                    + "VALUES (" + projectId + ", '" + researcherNIC + "', " + roleId + ", " + teamId + ")"
            );

            JOptionPane.showMessageDialog(this, "Team member added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            loadProjectTeams();
            reset();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String name = jTextField1.getText();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter a Team Name", "Warning", JOptionPane.ERROR_MESSAGE);
        } else {

            try {
                ResultSet rs = MySQL.executeSearch("SELECT * FROM `team_name` WHERE `name` = '" + name + "'");
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "This Team already Exists", "Warning", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                MySQL.executeIUD("INSERT INTO `team_name` (`name`) VALUES ('" + name + "')");
            } catch (Exception e) {
                e.printStackTrace();
            }

            loadTeamNames();
            reset();
            JOptionPane.showMessageDialog(this, "Added Successfully", "Success", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        int selectedRow = jTable2.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Get required values from the table - verify your column indices first!
            String projectName = jTable2.getValueAt(selectedRow, 3).toString();  // Project Name column
            String researcherNic = jTable2.getValueAt(selectedRow, 2).toString(); // Researcher NIC column
            String roleName = jTable2.getValueAt(selectedRow, 4).toString();      // Role column

            // Get confirmation
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Delete this team assignment?\nResearcher NIC: " + researcherNic
                    + "\nProject: " + projectName
                    + "\nRole: " + roleName,
                    "Confirm Delete", JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            // Get project ID
            ResultSet projectRs = MySQL.executeSearch("SELECT id FROM rs_projects WHERE title = '" + projectName + "'");
            if (!projectRs.next()) {
                JOptionPane.showMessageDialog(this, "Project not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String projectId = projectRs.getString("id");

            // Get role ID
            ResultSet roleRs = MySQL.executeSearch("SELECT id FROM role WHERE name = '" + roleName + "'");
            if (!roleRs.next()) {
                JOptionPane.showMessageDialog(this, "Role not found!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String roleId = roleRs.getString("id");

            // Execute deletion using direct identifiers
            MySQL.executeIUD("DELETE FROM project_team WHERE "
                    + "rs_projects_id = " + projectId + " AND "
                    + "reseachers_employees_nic = '" + researcherNic + "' AND "
                    + "role_id = " + roleId);

            // Refresh table
            loadProjectTeams();  // Make sure this is the correct refresh method
            reset();
            JOptionPane.showMessageDialog(this, "Entry deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error deleting entry: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
         try {
            InputStream path = this.getClass().getResourceAsStream("/reports/R&D/projectTeams.jasper");
            if (path == null) {
                throw new FileNotFoundException("Could not find the report file.");
            }

            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String time = new SimpleDateFormat("HH:mm:ss").format(new Date());

            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", date);
            params.put("Parameter2", time);

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable2.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, params, dataSource);

            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
private void reset() {

        jTable1.clearSelection();
        jTable2.clearSelection();
        jTable3.clearSelection();
        jTable4.clearSelection();
        jTable5.clearSelection();

    }

}
