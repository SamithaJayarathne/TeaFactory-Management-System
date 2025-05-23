/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.Research;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.FileNotFoundException;
import java.io.InputStream;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.BorderFactory;
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
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;

/**
 *
 * @author VIDARSHANA
 */
public class BudgetManagement extends javax.swing.JPanel {

    /**
     * Creates new form budgetManagementInterface
     */
    public BudgetManagement() {
        initComponents();
        loadBudgetTable();
        loadExpenseTable();
        loadProjects();
        loadBudgetChart();
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

    private void loadBudgetTable() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `rs_budget` "
                    + "INNER JOIN `rs_projects` ON `rs_budget`.`rs_projects_id`=`rs_projects`.`id`");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int i = 0; i < jTable3.getColumnCount(); i++) {
                jTable3.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("rs_projects.id"),
                    rs.getInt("allocated"),
                    rs.getInt("spent"),
                    rs.getInt("remaining"),
                    rs.getString("fiscal_year")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadExpenseTable() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `rs_expences`"
                    + "INNER JOIN `rs_budget` ON `rs_expences`.`rs_budget_id`=`rs_budget`.`id`");

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int i = 0; i < jTable3.getColumnCount(); i++) {
                jTable3.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            while (rs.next()) {
                model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getInt("rs_budget.id"),
                    rs.getInt("amount"),
                    rs.getString("reason"),
                    rs.getDate("expence_date")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadBudgetChart() {
        // Create dataset for stacked bar chart
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String selectedYear = (String) jComboBox1.getSelectedItem();

        String query = "SELECT rs_projects.title AS project, rs_budget.allocated, rs_budget.spent "
                + "FROM rs_budget "
                + "JOIN rs_projects ON rs_budget.rs_projects_id = rs_projects.id "
                + "WHERE rs_budget.fiscal_year = '" + selectedYear + "' "
                + "ORDER BY rs_budget.allocated DESC";

        try {
            ResultSet rs = MySQL.executeSearch(query);
            dataset.clear();

            double totalAllocated = 0;
            int projectCount = 0;

            while (rs.next()) {
                String project = rs.getString("project");
                double allocated = rs.getDouble("allocated");
                double spent = rs.getDouble("spent");

                dataset.addValue(allocated, "Allocated", project);
                dataset.addValue(spent, "Spent", project);

                totalAllocated += allocated;
                projectCount++;
            }

            JFreeChart barChart = ChartFactory.createBarChart(
                    "Budget Overview - " + selectedYear,
                    "Projects",
                    "Amount (LKR)",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );

            CategoryPlot plot = barChart.getCategoryPlot();
            plot.setBackgroundPaint(Color.WHITE);
            plot.setRangeGridlinePaint(Color.LIGHT_GRAY);

            // ✅ Use plain number format without currency symbol
            NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
            NumberFormat currencyFormat = NumberFormat.getNumberInstance();
            currencyFormat.setMaximumFractionDigits(0);
            rangeAxis.setNumberFormatOverride(currencyFormat);
            rangeAxis.setLabelFont(new Font("SansSerif", Font.BOLD, 14));
            rangeAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setBarPainter(new StandardBarPainter());
            renderer.setDrawBarOutline(false);
            renderer.setItemMargin(0.1);

            renderer.setSeriesPaint(0, new Color(79, 129, 189));
            renderer.setSeriesPaint(1, new Color(192, 80, 77));

            renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            renderer.setBaseItemLabelsVisible(true);
            renderer.setBaseItemLabelFont(new Font("SansSerif", Font.PLAIN, 11));
            renderer.setBasePositiveItemLabelPosition(
                    new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));

            if (projectCount > 0) {
                double avg90 = (totalAllocated / projectCount) * 0.9;
                ValueMarker threshold = new ValueMarker(avg90);
                threshold.setPaint(Color.ORANGE);
                threshold.setStroke(new BasicStroke(2f));
                threshold.setLabel("90% Avg Allocation");
                threshold.setLabelFont(new Font("SansSerif", Font.BOLD, 12));
                threshold.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
                threshold.setLabelTextAnchor(TextAnchor.BOTTOM_RIGHT);
                plot.addRangeMarker(threshold);
            }

            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new Dimension(900, 500));
            chartPanel.setMouseWheelEnabled(true);
            chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            jPanel1.removeAll();
            jPanel1.setLayout(new BorderLayout());
            jPanel1.add(chartPanel, BorderLayout.CENTER);
            jPanel1.validate();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading budget chart: " + e.getMessage(),
                    "Chart Load Error", JOptionPane.ERROR_MESSAGE);
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

        jLabel11 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel13 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jButton4 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel29 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jFormattedTextField2 = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        jLabel11.setText("Fiscal Year");

        jLabel13.setText("Allocate Amount");

        jLabel27.setText("Select Projects");

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

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Budget ID", "Project ID", "Allocated Budget", "Spent Budget", "Remaining", "Fiscal Year"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select", "2024", "2025", "2026", "2027" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jLabel28.setText("budget table");

        jButton4.setText("Add Budget");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel14.setText("Expence Amount");

        jLabel15.setText("Reason");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane3.setViewportView(jTextArea1);

        jLabel16.setText("Expence Date");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Expence ID", "Budget ID", "Expence Amount", "Reason", "Expence Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setResizable(false);
            jTable2.getColumnModel().getColumn(1).setResizable(false);
            jTable2.getColumnModel().getColumn(2).setResizable(false);
            jTable2.getColumnModel().getColumn(3).setResizable(false);
            jTable2.getColumnModel().getColumn(4).setResizable(false);
        }

        jLabel29.setText("Expence Table");

        jButton5.setText("Add Expence");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        jButton6.setText("Budget Report");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Expence Report");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel27)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel13)
                                .addComponent(jLabel11))
                            .addGap(95, 95, 95)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 419, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel28)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15)))
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 498, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(82, 82, 82))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jFormattedTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addGap(63, 63, 63))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(jButton6)
                            .addComponent(jButton7))
                        .addGap(29, 29, 29)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(jFormattedTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addGap(7, 7, 7)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        String allocated = jFormattedTextField2.getText();
        String fiscalYear = (String) jComboBox1.getSelectedItem();

        try {         
            if (jTable3.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Select a project", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (allocated.isEmpty() || !allocated.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Invalid allocated amount", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (fiscalYear.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Select Fiscal Year", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String projectId = (String) jTable3.getValueAt(jTable3.getSelectedRow(), 0);

            ResultSet rs = MySQL.executeSearch("SELECT * FROM rs_budget WHERE rs_projects_id = " + projectId);
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "Budget already exists for this project", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String query = "INSERT INTO rs_budget (`rs_projects_id`, `allocated`, `spent`,`remaining`,`fiscal_year`) "
                    + "VALUES ('" + projectId + "', '" + allocated + "', '0','" + allocated + "', '" + fiscalYear + "')";
            MySQL.executeIUD(query);

            loadBudgetTable();
            JOptionPane.showMessageDialog(this, "Budget added successfully");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        String amount = jFormattedTextField1.getText();
        String description = jTextArea1.getText();

        try {
         
            if (jTable1.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Select a budget", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (amount.isEmpty() || !amount.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Invalid amount", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (description.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Write the Reason for the Expence", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if (jDateChooser1.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Select Expence Date", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int totalSpent = 0;
            int remaining = 0;
            int remaining2 = 0;

            String budgetId = jTable1.getValueAt(jTable1.getSelectedRow(), 0).toString();
            String date = new SimpleDateFormat("yyyy-MM-dd").format(jDateChooser1.getDate());

           
            ResultSet budget = MySQL.executeSearch("SELECT allocated, spent, remaining FROM rs_budget WHERE id = " + budgetId);
            if (budget.next()) {
                remaining = budget.getInt("allocated") - budget.getInt("spent");
                remaining2 = budget.getInt("remaining") - Integer.parseInt(amount);
                if (Integer.parseInt(amount) > remaining) {
                    JOptionPane.showMessageDialog(this, "Amount exceeds remaining budget", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }
            }

          
            MySQL.executeIUD("INSERT INTO rs_expences (`rs_budget_id`, `amount`, `reason`, `expence_date`) "
                    + "VALUES (" + budgetId + ", " + amount + ", '" + description + "', '" + date + "')");

          
            ResultSet sumResult = MySQL.executeSearch("SELECT SUM(amount) AS total FROM rs_expences WHERE rs_budget_id = " + budgetId);
            if (sumResult.next()) {
                totalSpent = sumResult.getInt("total");
                MySQL.executeIUD("UPDATE rs_budget SET spent = " + totalSpent + ", remaining = '" + remaining2 + "' WHERE id = " + budgetId);
            }

            loadExpenseTable();
            loadBudgetTable(); 

            // 90% Alert
            if ((double) totalSpent / budget.getInt("allocated") >= 0.9) {
                JOptionPane.showMessageDialog(this, "Budget exceeds 90% of allocation!", "Alert", JOptionPane.WARNING_MESSAGE);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

        if (!jComboBox1.getSelectedItem().toString().equals("Select")) {
            loadBudgetChart();
        }

    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
        try {
            InputStream path = this.getClass().getResourceAsStream("/reports/R&D/budget.jasper");
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
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
        try {
            InputStream path = this.getClass().getResourceAsStream("/reports/R&D/expence.jasper");
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
        
    }//GEN-LAST:event_jButton7ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JFormattedTextField jFormattedTextField2;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
