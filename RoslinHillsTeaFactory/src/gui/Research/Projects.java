/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.Research;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.BorderFactory;
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
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Kavindu Anupama
 */
public class Projects extends javax.swing.JPanel {

    private HashMap<String, Integer> statusMap = new HashMap<>();

    /**
     * Creates new form ProjectManagement
     */
    public Projects() {
        initComponents();
        loadStatusMap();
        loadProjectsTable();
        loadProjectCharts();
    }

    private void loadProjectCharts() {
        // Dataset for project durations
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        try {
            String durationQuery = "SELECT title, s_date, end_date FROM rs_projects WHERE s_date IS NOT NULL AND end_date IS NOT NULL";
            ResultSet rs = MySQL.executeSearch(durationQuery);

            while (rs.next()) {
                String project = rs.getString("title");
                Date start = rs.getDate("s_date");
                Date end = rs.getDate("end_date");

                // Calculate duration in days
                long durationDays = (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24);

                dataset.addValue(durationDays, "Duration (Days)", project);
            }

            // Create the bar chart
            JFreeChart chart = ChartFactory.createBarChart(
                    "Project Timelines",
                    "Project",
                    "Duration (Days)",
                    dataset,
                    PlotOrientation.VERTICAL,
                    false, true, false
            );

            // Chart appearance
            chart.setBackgroundPaint(Color.WHITE);
            chart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 18));

            CategoryPlot plot = chart.getCategoryPlot();
            plot.setBackgroundPaint(new Color(245, 245, 245));
            plot.setDomainGridlinePaint(Color.GRAY);
            plot.setRangeGridlinePaint(Color.GRAY);
            plot.setOutlineVisible(false);

            // Renderer
            BarRenderer renderer = new BarRenderer();
            renderer.setSeriesPaint(0, new Color(79, 129, 189));
            renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            renderer.setBaseItemLabelsVisible(true);
            renderer.setBaseItemLabelFont(new Font("SansSerif", Font.PLAIN, 12));
            plot.setRenderer(renderer);

            // Axes
            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
            rangeAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
            rangeAxis.setLabelFont(new Font("SansSerif", Font.BOLD, 14));

            CategoryAxis domainAxis = plot.getDomainAxis();
            domainAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
            domainAxis.setLabelFont(new Font("SansSerif", Font.BOLD, 14));

            // Panel setup
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(900, 500));
            chartPanel.setMouseWheelEnabled(true);
            chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            jPanel1.removeAll();
            jPanel1.setLayout(new BorderLayout());
            jPanel1.add(chartPanel, BorderLayout.CENTER);
            jPanel1.validate();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error loading project timeline chart: " + e.getMessage(),
                    "Chart Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void loadStatusMap() {
        try {

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            ResultSet rs = MySQL.executeSearch("SELECT * FROM `rs_status`");
            while (rs.next()) {
                vector.add(rs.getString("name"));
                statusMap.put(rs.getString("name"), rs.getInt("id"));

            }
            jComboBox1.setModel(new DefaultComboBoxModel<>(vector));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadProjectsTable() {

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < jTable1.getColumnCount(); i++) {
            jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `rs_projects` INNER JOIN `rs_status` ON `rs_projects`.`rs_status_id`=`rs_status`.`id`");
            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getDate("s_date"),
                    rs.getDate("end_date"),
                    rs.getString("rs_status.name")
                });
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

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        dateStart = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        dateEnd = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();

        jButton1.setText("Add Project");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Edit Project");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Title", "Description", "Start Date", "End Date", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel1.setText("Project Titile");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jLabel2.setText("Project Description");

        jLabel4.setText("Status ");

        jLabel5.setText("Start Date");

        jLabel6.setText("End Date");

        jButton3.setText("reset");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(15, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 866, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel4))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(dateEnd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(dateStart, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jButton1)
                                    .addGap(45, 45, 45)
                                    .addComponent(jButton2)
                                    .addGap(46, 46, 46)
                                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jButton4)
                                .addGap(99, 99, 99)))))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateStart, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateEnd, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(12, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String title = jTextField1.getText();
        String description = jTextArea1.getText();
        String status = jComboBox1.getSelectedItem().toString();

// Validate Title
        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

// Validate Description
        if (description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Description cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

// Validate Status Selection
        if (status.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Select Status", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

// Validate Dates
        if (dateStart.getDate() == null || dateEnd.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Please select both dates", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

// Validate Date Order
        if (dateEnd.getDate().before(dateStart.getDate())) {
            JOptionPane.showMessageDialog(this, "End date must be after start date", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String startDate = sdf.format(dateStart.getDate());
            String endDate = sdf.format(dateEnd.getDate());
            int statusId = statusMap.get(jComboBox1.getSelectedItem().toString());

            // Insert project into database
            MySQL.executeIUD("INSERT INTO rs_projects (title, description, s_date, end_date, rs_status_id) "
                    + "VALUES ('" + title + "','" + description + "','" + startDate + "','" + endDate + "'," + statusId + ")");

            loadProjectsTable();
            reset();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving project: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String title = jTextField1.getText().trim();
        String description = jTextArea1.getText();
        String status = jComboBox1.getSelectedItem().toString();

        if (title.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Title cannot be empty", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String startDate = sdf.format(dateStart.getDate());
            String endDate = sdf.format(dateEnd.getDate());
            int projectId = (int) jTable1.getValueAt(jTable1.getSelectedRow(), 0);

            ResultSet rs = MySQL.executeSearch("SELECT * FROM `rs_projects` WHERE `description` = '" + description + "' AND `rs_status_id` = '" + statusMap.get(status) + "'");
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Change Description or Status to update", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                MySQL.executeIUD("UPDATE rs_projects SET "
                        + "title = '" + title + "', "
                        + "description = '" + description + "', "
                        + "s_date = '" + startDate + "', "
                        + "end_date = '" + endDate + "', "
                        + "rs_status_id = " + statusMap.get(status) + " "
                        + "WHERE id = " + projectId);
                JOptionPane.showMessageDialog(this, "Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            }

            reset();
            loadProjectsTable();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving project: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        jTextField1.setEditable(false);
        dateEnd.setEnabled(false);
        dateStart.setEnabled(false);
        int row = jTable1.getSelectedRow();

        String title = String.valueOf(jTable1.getValueAt(row, 1));
        jTextField1.setText(title);

        String des = String.valueOf(jTable1.getValueAt(row, 2));
        jTextArea1.setText(des);

        String date = String.valueOf(jTable1.getValueAt(row, 3));
        SimpleDateFormat date2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date3 = null;
        try {
            date3 = date2.parse(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        dateStart.setDate(date3);

        String date4 = String.valueOf(jTable1.getValueAt(row, 4));
        SimpleDateFormat date5 = new SimpleDateFormat("yyyy-MM-dd");
        Date date6 = null;
        try {
            date6 = date5.parse(date4);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        dateEnd.setDate(date6);

        jComboBox1.setSelectedItem(String.valueOf(jTable1.getValueAt(row, 5)));

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        reset();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        try {
            InputStream path = this.getClass().getResourceAsStream("/reports/R&D/projects.jasper");
            if (path == null) {
                throw new FileNotFoundException("Could not find the report file.");
            }

            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String time = new SimpleDateFormat("HH:mm:ss").format(new Date());

            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", date);
            params.put("Parameter2", time);

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable1.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, params, dataSource);

            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser dateEnd;
    private com.toedter.calendar.JDateChooser dateStart;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables

    private void reset() {
        jTextField1.setText("");
        jTextArea1.setText("");
        dateEnd.setEnabled(true);
        dateEnd.setDate(null);
        dateStart.setEnabled(true);
        dateStart.setDate(null);
        jComboBox1.setSelectedIndex(0);
    }

}
