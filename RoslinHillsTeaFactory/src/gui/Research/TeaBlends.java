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
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author VIDARSHANA
 */
public class TeaBlends extends javax.swing.JPanel {

//    private HashMap<String, Integer> statusMap = new HashMap<>();
    /**
     * Creates new form teaDataMagement
     */
    public TeaBlends() {
        initComponents();
        loadTeaBatches();
        loadTeaBlendsTable();
        loadProjects();
        loadTeaProductionByGradeChart();
    }
  
    private void loadTeaProductionByGradeChart() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    String query = "SELECT g.name AS grade_name, SUM(tb.qty_end) AS total_qty " +
                   "FROM tea_batch tb " +
                   "JOIN grades g ON tb.grades_id = g.id " +
                   "GROUP BY g.name " +
                   "ORDER BY total_qty DESC";

    try {
        ResultSet rs = MySQL.executeSearch(query);
        while (rs.next()) {
            String grade = rs.getString("grade_name");
            double totalQty = rs.getDouble("total_qty");
            dataset.addValue(totalQty, "Quantity", grade);
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Tea Production by Grade",
                "Grade",
                "Quantity (kg)",
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false);

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(245, 245, 245));
        plot.setDomainGridlinePaint(Color.GRAY);
        plot.setRangeGridlinePaint(Color.GRAY);

        // Customize renderer
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(76, 175, 80));  // Green
        renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBaseItemLabelFont(new Font("SansSerif", Font.PLAIN, 12));

        // Axis font styling
        CategoryAxis domainAxis = plot.getDomainAxis();
        domainAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        domainAxis.setLabelFont(new Font("SansSerif", Font.BOLD, 14));

        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        rangeAxis.setLabelFont(new Font("SansSerif", Font.BOLD, 14));

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
        JOptionPane.showMessageDialog(this, "Error loading production chart: " + e.getMessage(),
                "Chart Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
    private void loadTeaBlendsTable() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM tea_blends INNER JOIN `rs_projects` ON `tea_blends`.`rs_projects_id`=`rs_projects`.`id`");
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
            
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int i = 0; i < jTable2.getColumnCount(); i++) {
                jTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getString("id"),
                    rs.getString("blend_name"),
                    rs.getDate("deve_date"),
                    rs.getString("characteristics"),
                    rs.getString("rs_projects.title"),
                    rs.getString("tea_batch_id")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTeaBatches() {
        try {

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `tea_batch` "
                    + "INNER JOIN `grades` ON `tea_batch`.`grades_id` = `grades`.`id` ");

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
                vector.add(resultSet.getString("grades.name"));
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();

        jScrollPane2.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane2.setViewportView(jTextArea1);

        jButton5.setText("Add");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Update");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Reset");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel16.setText("Blend Name:");

        jLabel18.setText("Developed Date:");

        jTextField2.setSelectionColor(new java.awt.Color(0, 204, 204));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Batch ID", "Grade"
            }
        ));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel19.setText("characteristics");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Blend Name", "Developed Date", "Characteristics", "Project Title", "Tea Batch ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
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
            jTable2.getColumnModel().getColumn(4).setResizable(false);
            jTable2.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel26.setText("Select Tea Batch");

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
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setResizable(false);
            jTable3.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel27.setText("Select Projects");

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jButton8.setText("Generate Report");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(64, 64, 64))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel18))
                                .addGap(48, 48, 48)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField2)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 737, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 17, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        String blendName = jTextField2.getText();
        String characteristics = jTextArea1.getText();

        if (blendName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Blend Name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (characteristics.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter Characteristics", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jTable3.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please Select a Row from Projects Table", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please Select a Row from Batch ID Table", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jDateChooser1.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Select Development Date", "Warning", JOptionPane.WARNING_MESSAGE);
        }

        try {
            // Check for existing blend name

            String teaBatchID = String.valueOf(jTable1.getValueAt(jTable1.getSelectedRow(), 0));
            String projectID = String.valueOf(jTable3.getValueAt(jTable3.getSelectedRow(), 0));

            ResultSet rs = MySQL.executeSearch("SELECT * FROM tea_blends WHERE blend_name = '" + blendName + "'");
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Blend Name already exists", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String date = new SimpleDateFormat("yyyy-MM-dd").format(jDateChooser1.getDate());

            MySQL.executeIUD("INSERT INTO tea_blends (blend_name, characteristics, deve_date, rs_projects_id, tea_batch_id) "
                    + "VALUES ('" + blendName + "', '" + characteristics + "', '" + date + "', " + projectID + ", '" + teaBatchID + "')");

            loadTeaBlendsTable();
            reset();
            JOptionPane.showMessageDialog(this, "Blend Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        String characteristics = jTextArea1.getText();

        if (jTable2.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Select a row to Update", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (characteristics.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Characteristics cannoth be empty", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jDateChooser1.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Production Date cannot be Empty", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String date = sdf.format(jDateChooser1.getDate());

                int id = Integer.parseInt(jTable2.getValueAt(jTable2.getSelectedRow(), 0).toString());

                ResultSet rs = MySQL.executeSearch("SELECT * FROM `tea_blends` WHERE `characteristics` = '" + characteristics + "' AND `deve_date` = '" + date + "'");
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "Change Developed Date or Characteristics to Update", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Perform update
                    MySQL.executeIUD(
                            "UPDATE `tea_blends` SET "
                            + "`characteristics` = '" + characteristics + "', "
                            + "`deve_date` = '" + date + "' "
                            + "WHERE `id` = '" + id + "'"
                    );
                    JOptionPane.showMessageDialog(this, "Updated successfully!");
                    loadTeaBlendsTable();
                    reset();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked

        jTextField2.setEditable(false);
        int row = jTable2.getSelectedRow();

        String blname = String.valueOf(jTable2.getValueAt(row, 1));
        jTextField2.setText(blname);

        String charac = String.valueOf(jTable2.getValueAt(row, 3));
        jTextArea1.setText(charac);

        String date = String.valueOf(jTable2.getValueAt(row, 2));
        SimpleDateFormat date2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date3 = null;
        try {
            date3 = date2.parse(date);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        jDateChooser1.setDate(date3);

    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        reset();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        
        try {
            InputStream path = this.getClass().getResourceAsStream("/reports/R&D/teaBlends.jasper");
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
        
    }//GEN-LAST:event_jButton8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    private void reset() {

        jTextField2.setText("");
        jTextField2.setEditable(true);
        jTextArea1.setText("");
        jDateChooser1.setDate(null);

    }
}
