package gui.HRDeapartment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import model.MySQL;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class Charts1 extends javax.swing.JPanel {

    public Charts1() {
        initComponents();
        loadRentalChart();
        loadGenderDistributionChart();
        loadAttendanceChart();
        loadSalaryRangeChart();
    }

    private void loadSalaryRangeChart() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    String query = """
        SELECT 
            CASE 
                WHEN basic_salary <= 30000 THEN '0-30,000'
                WHEN basic_salary BETWEEN 30001 AND 50000 THEN '30,001-50,000'
                WHEN basic_salary BETWEEN 50001 AND 100000 THEN '50,001-100,000'
                WHEN basic_salary BETWEEN 100001 AND 200000 THEN '100,001-200,000'
                ELSE '200,001+' 
            END AS SalaryRange,
            COUNT(*) AS EmployeeCount
        FROM 
            employees
        GROUP BY 
            SalaryRange
        ORDER BY 
            FIELD(SalaryRange, '0-30,000', '30,001-50,000', '50,001-100,000', '100,001-200,000', '200,001+');
    """;

    try {
        ResultSet rs = MySQL.executeSearch(query);

        while (rs.next()) {
            String salaryRange = rs.getString("SalaryRange");
            int count = rs.getInt("EmployeeCount");

            dataset.setValue(count, "Employees", salaryRange);
        }

        JFreeChart chart = ChartFactory.createBarChart(
            "Salary Range Distribution",   // Chart title
            "Salary Range",                // X-axis label
            "Employees",                   // Y-axis label
            dataset,
            PlotOrientation.VERTICAL,
            false,                         // No legend needed
            true,
            false
        );

        // === Styling the chart ===
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(0xF9FAFB)); // Light neutral background
        plot.setRangeGridlinePaint(new Color(0xCBD5E1)); // Soft gray gridlines
        plot.setOutlineVisible(false);

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setBarPainter(new StandardBarPainter()); // Flat modern look
        renderer.setDrawBarOutline(false);
        renderer.setSeriesPaint(0, new Color(0x3B82F6)); // Carolina Blue
        renderer.setItemMargin(0.1); // Spacing between bars

        // Fonts
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 12);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 16);

        chart.getTitle().setFont(titleFont);
        plot.getDomainAxis().setLabelFont(labelFont);
        plot.getDomainAxis().setTickLabelFont(labelFont);
        plot.getRangeAxis().setLabelFont(labelFont);
        plot.getRangeAxis().setTickLabelFont(labelFont);

        // Optional: tooltips and hover interactivity can be enabled with mouse listeners

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(820, 500));
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        jPanel10.removeAll();
        jPanel10.setLayout(new BorderLayout());
        jPanel10.add(chartPanel, BorderLayout.CENTER);
        jPanel10.revalidate();
        jPanel10.repaint();

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    private void loadAttendanceChart() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    String query = """
        SELECT 
            DATE(a.date) AS date,
            SUM(CASE WHEN a.attendance_status_id = 1 THEN 1 ELSE 0 END) AS present_count,
            SUM(CASE WHEN a.attendance_status_id = 2 THEN 1 ELSE 0 END) AS absent_count,
            SUM(CASE WHEN a.attendance_status_id = 3 THEN 1 ELSE 0 END) AS late_count
        FROM 
            attendance a
        WHERE 
            a.date >= CURDATE() - INTERVAL 5 DAY
        GROUP BY 
            DATE(a.date)
        ORDER BY 
            DATE(a.date);
    """;

    try {
        ResultSet rs = MySQL.executeSearch(query);

        while (rs.next()) {
            String date = rs.getString("date");
            int present = rs.getInt("present_count");
            int absent = rs.getInt("absent_count");
            int late = rs.getInt("late_count");

            dataset.setValue(present, "Present", date);
            dataset.setValue(absent, "Absent", date);
            dataset.setValue(late, "Late", date);
        }

        JFreeChart chart = ChartFactory.createBarChart(
            "Attendance Overview (Last 5 Days)", // Chart title
            "Date",                               // X-Axis label
            "Count",                              // Y-Axis label
            dataset,
            PlotOrientation.VERTICAL,
            true, true, false
        );

        // Modernize chart
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(0xF9FAFB)); // Light background
        plot.setRangeGridlinePaint(new Color(0xCBD5E1)); // Soft gray lines
        plot.setOutlineVisible(false);

        // Customize the renderer (bars)
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setBarPainter(new StandardBarPainter()); // Flat look
        renderer.setDrawBarOutline(false);
        renderer.setItemMargin(0.05); // Tight bars

        // Set bar colors
        renderer.setSeriesPaint(0, new Color(0x3B82F6)); // Present - Primary Blue
        renderer.setSeriesPaint(1, new Color(0xEF4444)); // Absent - Red
        renderer.setSeriesPaint(2, new Color(0xFACC15)); // Late - Yellow

        // Customize fonts
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 12);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 16);

        chart.getTitle().setFont(titleFont);
        chart.getLegend().setItemFont(labelFont);
        plot.getDomainAxis().setLabelFont(labelFont);
        plot.getDomainAxis().setTickLabelFont(labelFont);
        plot.getRangeAxis().setLabelFont(labelFont);
        plot.getRangeAxis().setTickLabelFont(labelFont);

        // Optional: remove chart border
        chart.setBorderVisible(false);
        chart.setBackgroundPaint(Color.WHITE);

        // Add chart to panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(820, 500));
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        jPanel9.removeAll();
        jPanel9.setLayout(new BorderLayout());
        jPanel9.add(chartPanel, BorderLayout.CENTER);
        jPanel9.revalidate();
        jPanel9.repaint();

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    private void loadGenderDistributionChart() {

    DefaultPieDataset dataset = new DefaultPieDataset();

    String query = """
        SELECT g.name AS Gender, COUNT(e.nic) AS GenderCount
        FROM employees e
        JOIN gender g ON e.gender_id = g.id
        GROUP BY g.name
    """;

    try {
        ResultSet rs = MySQL.executeSearch(query);

        while (rs.next()) {
            String gender = rs.getString("Gender");
            int count = rs.getInt("GenderCount");
            dataset.setValue(gender, count);
        }

        JFreeChart pieChart = ChartFactory.createPieChart(
            "Employee Gender Distribution",
            dataset,
            true,
            true,
            false
        );

        // === Style and appearance ===
        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setBackgroundPaint(new Color(0xF9FAFB)); // Light modern background
        plot.setOutlineVisible(false);
        plot.setShadowPaint(null); // Remove shadow for flat modern look

        // Section colors
        plot.setSectionPaint("Male", new Color(0x4F82C0));    // CG Blue
        plot.setSectionPaint("Female", new Color(0x99B7D7));  // Carolina Blue

        // Font styling
        Font titleFont = new Font("Segoe UI", Font.BOLD, 16);
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 12);
        Font legendFont = new Font("Segoe UI", Font.PLAIN, 12);

        pieChart.getTitle().setFont(titleFont);
        plot.setLabelFont(labelFont);
        pieChart.getLegend().setItemFont(legendFont);

        plot.setLabelBackgroundPaint(new Color(0xE5E7EB)); // Light gray label background
        plot.setLabelOutlinePaint(null);
        plot.setLabelShadowPaint(null);

        // Label format
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})"));

        // Chart panel
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(820, 500));
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Add to panel
        jPanel6.removeAll();
        jPanel6.setLayout(new BorderLayout());
        jPanel6.add(chartPanel, BorderLayout.CENTER);
        jPanel6.revalidate();
        jPanel6.repaint();

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    private void loadRentalChart() {

    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    String query = """
        SELECT d.name AS Department, COUNT(e.nic) AS EmployeeCount
        FROM employees e
        JOIN departments d ON e.departments_id = d.id
        GROUP BY d.name
    """;

    try {
        ResultSet rs = MySQL.executeSearch(query);

        while (rs.next()) {
            int total = rs.getInt("EmployeeCount");
            String dep = rs.getString("Department");
            dataset.setValue(total, "Employees", dep);
        }

        JFreeChart barChart = ChartFactory.createBarChart(
            "Employee Distribution by Department",
            "Department",
            "Number of Employees",
            dataset,
            PlotOrientation.VERTICAL,
            false,  // Legend
            true,   // Tooltips
            false   // URLs
        );

        // === Style the plot ===
        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setRangeGridlinePaint(new Color(0x4F82C0)); // CG Blue grid lines
        plot.setBackgroundPaint(new Color(0xF9FAFB)); // Light background

        // === Style the bars ===
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setSeriesPaint(0, new Color(0x99B7D7)); // Carolina Blue
        renderer.setBarPainter(new StandardBarPainter()); // Flat design
        renderer.setShadowVisible(false);

        // === Font Styling ===
        Font axisFont = new Font("Segoe UI", Font.PLAIN, 12);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 16);

        plot.getDomainAxis().setTickLabelFont(axisFont);
        plot.getRangeAxis().setTickLabelFont(axisFont);
        barChart.getTitle().setFont(titleFont);

        // === Panel setup ===
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(820, 500));
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // === Add to JPanel ===
        jPanel5.removeAll();
        jPanel5.setLayout(new BorderLayout());
        jPanel5.add(chartPanel, BorderLayout.CENTER);
        jPanel5.revalidate();
        jPanel5.repaint();

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        jPanel9.setLayout(new javax.swing.BoxLayout(jPanel9, javax.swing.BoxLayout.LINE_AXIS));

        jPanel10.setLayout(new javax.swing.BoxLayout(jPanel10, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables
}
