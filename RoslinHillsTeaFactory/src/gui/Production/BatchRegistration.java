/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.Production;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.MySQL;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Kavindu Anupama
 */
public class BatchRegistration extends javax.swing.JPanel {

    private static HashMap<String, String> processMap = new HashMap();
    private static HashMap<String, String> machineMap = new HashMap();
    private static HashMap<String, String> gradeMap = new HashMap();

    /**
     * Creates new form BatchRegistration
     */
    public BatchRegistration() {
        initComponents();
        loadRawMaterialStock();
        batchID();
        loadProductionProcesses();
        loadMachines();
        loadTeaBatch();
        loadCategoryChart();
    }

    private void loadRawMaterialStock() {

        try {

            ResultSet rs = MySQL.executeSearch("SELECT * FROM raw_materials_stock "
                    + "INNER JOIN `grades` ON `raw_materials_stock`.`grades_id`=`grades`.`id` "
                    + "INNER JOIN `unit` ON `raw_materials_stock`.`unit_id` = `unit`.`id` "
                    + "ORDER BY `raw_materials_stock`.`id` ASC");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int i = 0; i < jTable1.getColumnCount(); i++) {
                jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            while (rs.next()) {
                String unit = rs.getString("unit.name");
                String qty = rs.getString("qty");
                Vector<String> vector = new Vector();
                vector.add(String.valueOf(rs.getString("id")));
                vector.add(qty + " " + unit);
                vector.add(rs.getString("added_date"));
                vector.add(rs.getString("grades.name"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Method to generate a unique batch ID using timestamp and random number
    public static long generateUniqueBatchId() {
        Random random = new Random();

        // Get current time in milliseconds
        long timestamp = System.currentTimeMillis();

        // Generate a random number (3-digit) between 100 and 999
        int randomNumber = 100 + random.nextInt(900);

        // Combine timestamp and random number to generate a unique batch ID
        long batchId = Long.parseLong(String.valueOf(timestamp).substring(6) + randomNumber);

        return batchId;
    }

    // GUI Method to display Batch ID
    public void batchID() {
        long batchid = generateUniqueBatchId();
        jTextField1.setText(String.valueOf(batchid));
        jTextField1.setEditable(false);
    }

    private void loadProductionProcesses() {

        try {

            Vector<String> vector = new Vector<>();

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `production_status`");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                processMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            jComboBox1.setModel(new DefaultComboBoxModel<>(vector));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadMachines() {

        try {

            Vector<String> vector = new Vector<>();

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `machine`");

            while (resultSet.next()) {
                vector.add(resultSet.getString("name"));
                machineMap.put(resultSet.getString("name"), resultSet.getString("id"));
            }

            jComboBox2.setModel(new DefaultComboBoxModel<>(vector));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTeaBatch() {

        try {

            ResultSet rs = MySQL.executeSearch("SELECT * FROM `tea_batch`"
                    + "INNER JOIN `grades` ON `tea_batch`.`grades_id`=`grades`.`id` "
                    + "INNER JOIN `machine` ON `tea_batch`.`machine_id`=`machine`.`id` "
                    + "INNER JOIN `production_status` ON `tea_batch`.`production_status_id`=`production_status`.`id` ");

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int i = 0; i < jTable2.getColumnCount(); i++) {
                jTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            while (rs.next()) {
                Vector<String> vector = new Vector();
                vector.add(rs.getString("id"));
                vector.add(rs.getString("production_date"));
                vector.add(rs.getString("qty_start"));
                vector.add(rs.getString("grades.name"));
                vector.add(rs.getString("machine.name"));
                vector.add(rs.getString("production_status.name"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadCategoryChart() {
        // Create dataset for the bar chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String query = "SELECT * FROM `tea_batch`"
                + "INNER JOIN `raw_materials_stock` ON `raw_materials_stock`.`id` = `tea_batch`.`raw_materials_stock_id`"
                + "INNER JOIN `grades` ON `grades`.`id` = `tea_batch`.`grades_id`";

        try {
            ResultSet rs = MySQL.executeSearch(query);

            while (rs.next()) {
                int avai_qty = rs.getInt("qty");  // Retrieve book count from database
                String name = rs.getString("grades.name");  // Retrieve category name from database

                // Add values to the dataset for the bar chart
                dataset.setValue(avai_qty, "Available Quantity", name);  // Rows (y-axis value), Columns (x-axis category)
            }

            // Create a bar chart
            JFreeChart barChart = ChartFactory.createBarChart(
                    "Remaining Stock Based On Grade", // Chart title
                    "Tea Leaf Grade", // Category axis label (x-axis)
                    "Quantity", // Value axis label (y-axis)
                    dataset, // Dataset
                    org.jfree.chart.plot.PlotOrientation.VERTICAL, // Orientation
                    true, // Include legend
                    true, // Tooltips
                    false // URLs
            );

            // Customize the bar chart (optional)
            CategoryPlot plot = barChart.getCategoryPlot();
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setSeriesPaint(0, new Color(79, 129, 189));  // Customize color for bars
            plot.setRangeGridlinePaint(Color.BLACK);  // Set gridline color

            // Add chart to panel
            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new Dimension(400, 600));

            // Clear and add the chart to the GUI panel (jPanel1)
            jPanel1.removeAll();
            jPanel1.add(chartPanel);
            jPanel1.validate();

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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton14 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Select Raw Material Stock");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock ID", "Quantity", "Date Added", "Grade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel2.setText("Batch ID");

        jLabel3.setText("Quantity");

        jLabel4.setText("Assign Process");

        jLabel5.setText("Assign Machine");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton1.setText("Create Batch");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Batch ID", "Produced Date", "Quantity", "Grade", "Assigned Machine", "Assigned Process"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
            jTable2.getColumnModel().getColumn(5).setResizable(false);
        }

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));
        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton2.setText("Genarate Report");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel15.setText("to");

        jLabel16.setText("From");

        jButton7.setText("Filter");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton14.setText("Veiw all");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(34, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel2)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(125, 125, 125)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3)
                                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 625, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel4)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(375, 375, 375)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel5)
                                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(194, 194, 194))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel15)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(9, 9, 9)
                                        .addComponent(jButton7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton14)))))))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton7)
                                .addComponent(jButton14))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel15)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(11, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        int row = jTable1.getSelectedRow();
        String batch_id = jTextField1.getText();
        String quty = jTextField2.getText();
        String process = String.valueOf(jComboBox1.getSelectedItem());
        String machine = String.valueOf(jComboBox2.getSelectedItem());
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Select tea leaves Stock first", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (quty.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Add Quantity", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (Integer.parseInt(quty) > 250) {
            JOptionPane.showMessageDialog(this, "Quantity should be less than 250", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (Integer.parseInt(quty) % 50 != 0) {
            JOptionPane.showMessageDialog(this, "Invalid, Quantity should be a Multiple of 50", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {
                String raw_id = String.valueOf(jTable1.getValueAt(row, 0));
                String grade = String.valueOf(jTable1.getValueAt(row, 3));
                ResultSet res = MySQL.executeSearch("SELECT `qty` FROM `raw_materials_stock` WHERE `id` = '" + raw_id + "'");

                ResultSet rs = MySQL.executeSearch("SELECT * FROM `grades`");

                while (rs.next()) {
                    gradeMap.put(rs.getString("name"), rs.getString("id"));
                }

                MySQL.executeIUD("INSERT INTO `tea_batch`(`id`,`production_date`,`qty_start`,`raw_materials_stock_id`,`grades_id`,`machine_id`,`production_status_id`)"
                        + "VALUES('" + batch_id + "','" + date + "','" + quty + "','" + raw_id + "','" + gradeMap.get(grade) + "','" + machineMap.get(machine) + "','" + processMap.get(process) + "')");

                if (res.next()) {
                    Double existing_stock = Double.valueOf(String.valueOf(res.getString("qty")));
                    Double new_stock = existing_stock - Double.parseDouble(quty);
                    MySQL.executeIUD("UPDATE `raw_materials_stock` SET `qty` = '" + new_stock + "' WHERE `id` = '" + raw_id + "'");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        batchID();
        loadTeaBatch();
        loadRawMaterialStock();
        loadCategoryChart();
        jTextField2.setText("");

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        try {
            String path = "src//reports//batchReport.jasper";
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
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        try {

            if (jDateChooser1.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Select a Start Date", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (jDateChooser2.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Select a End Date", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String stdate = new SimpleDateFormat("yyyy-MM-dd").format(jDateChooser1.getDate());
            String eddate = new SimpleDateFormat("yyyy-MM-dd").format(jDateChooser2.getDate());

            ResultSet rs = MySQL.executeSearch("SELECT * FROM `tea_batch`"
                    + "INNER JOIN `grades` ON `tea_batch`.`grades_id`=`grades`.`id` "
                    + "INNER JOIN `machine` ON `tea_batch`.`machine_id`=`machine`.`id` "
                    + "INNER JOIN `production_status` ON `tea_batch`.`production_status_id`=`production_status`.`id` "
                    + "WHERE `production_date` BETWEEN '" + stdate + "' AND '" + eddate + "' ORDER BY `tea_batch`.`id` DESC");

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int i = 0; i < jTable2.getColumnCount(); i++) {
                jTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            while (rs.next()) {
                Vector<String> vector = new Vector();
                vector.add(rs.getString("id"));
                vector.add(rs.getString("production_date"));
                vector.add(rs.getString("qty_start"));
                vector.add(rs.getString("grades.name"));
                vector.add(rs.getString("machine.name"));
                vector.add(rs.getString("production_status.name"));

                model.addRow(vector);
            }

            jDateChooser1.setDate(null);
            jDateChooser2.setDate(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        loadTeaBatch();
    }//GEN-LAST:event_jButton14ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
