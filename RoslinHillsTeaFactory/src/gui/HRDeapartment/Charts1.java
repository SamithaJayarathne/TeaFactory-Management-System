package gui.HRDeapartment;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import model.MySQL;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
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
                int employeeCount = rs.getInt("EmployeeCount");

                // Add data to dataset
                dataset.setValue(employeeCount, "Employees", salaryRange);
            }

            JFreeChart barChart = ChartFactory.createBarChart(
                    "Employee Salary Range Distribution",
                    "Salary Range",
                    "Number of Employees",
                    dataset,
                    PlotOrientation.VERTICAL,
                    false, // Legend not needed
                    true,
                    false
            );

            // Customize chart appearance
            CategoryPlot plot = barChart.getCategoryPlot();
            plot.setRangeGridlinePaint(new Color(0x4F82C0)); // CG Blue for gridlines
            plot.setBackgroundPaint(Color.WHITE); // White background

            // Customize bar colors
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setSeriesPaint(0, new Color(0x99B7D7)); // Carolina Blue for bars

            // Create a chart panel and add to the Swing component
            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new Dimension(800, 600));

            // Add the chart to the JPanel (replace jPanel6 with your panel)
            jPanel10.removeAll();
            jPanel10.add(chartPanel);
            jPanel10.validate();

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
                int presentCount = rs.getInt("present_count");
                int absentCount = rs.getInt("absent_count");
                int lateCount = rs.getInt("late_count");

                // Add data to dataset
                dataset.setValue(presentCount, "Present", date);
                dataset.setValue(absentCount, "Absent", date);
                dataset.setValue(lateCount, "Late", date);
            }

            JFreeChart barChart = ChartFactory.createBarChart(
                    "Attendance Summary - Last 5 Days",
                    "Date",
                    "Count",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true, // Show legend
                    true,
                    false
            );

            // Customize chart appearance
            CategoryPlot plot = barChart.getCategoryPlot();
            plot.setRangeGridlinePaint(new Color(0x4F82C0)); // CG Blue gridlines
            plot.setBackgroundPaint(Color.WHITE); // White background

            // Customize bar colors
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setSeriesPaint(0, new Color(0x99B7D7)); // Carolina Blue for "Present"
            renderer.setSeriesPaint(1, new Color(0xFF6F61)); // Light Red for "Absent"
            renderer.setSeriesPaint(2, new Color(0xFFD700)); // Golden Yellow for "Late"

            // Create chart panel and set to the Swing component
            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new Dimension(800, 600));

            // Add the chart to your JPanel
            jPanel9.removeAll();
            jPanel9.add(chartPanel);
            jPanel9.validate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadGenderDistributionChart() {

        DefaultPieDataset dataset = new DefaultPieDataset();

        // Query to fetch gender distribution from employees and gender tables
        String query = "SELECT g.name AS Gender, COUNT(e.nic) AS GenderCount FROM employees e JOIN gender g ON e.gender_id = g.id GROUP BY g.name";

        try {
            ResultSet rs = MySQL.executeSearch(query);

            while (rs.next()) {
                String gender = rs.getString("Gender");
                int count = rs.getInt("GenderCount");
                dataset.setValue(gender, count);
            }

            JFreeChart pieChart = ChartFactory.createPieChart(
                    "Employee Gender Distribution", // Chart title
                    dataset, // Dataset
                    true, // Include legend
                    true, // Tooltips
                    false // URLs
            );

            // Customize the pie chart's appearance
            PiePlot plot = (PiePlot) pieChart.getPlot();
            plot.setSectionPaint("Male", new Color(0x4F82C0)); // CG Blue color
            plot.setSectionPaint("Female", new Color(0x99B7D7)); // Carolina Blue color
            plot.setBackgroundPaint(Color.WHITE); // White background
            plot.setOutlineVisible(false); // Hide the outline of the pie chart

            // Create a panel for displaying the chart
            ChartPanel chartPanel = new ChartPanel(pieChart);
            chartPanel.setPreferredSize(new Dimension(800, 600));

            // Add the chart to the panel (similar to your previous code)
            jPanel6.removeAll();
            jPanel6.add(chartPanel);
            jPanel6.validate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadRentalChart() {

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String query = "SELECT d.name AS Department, COUNT(e.nic) AS EmployeeCount FROM employees e JOIN departments d ON e.departments_id = d.id GROUP BY d.name";

        try {
            ResultSet rs = MySQL.executeSearch(query);

            while (rs.next()) {
                int total = rs.getInt("EmployeeCount");
                String dep = rs.getString("Department");

                dataset.setValue(total, "", dep);
            }

            JFreeChart barChart = ChartFactory.createBarChart(
                    "Employee Distribution By Department",
                    "Department",
                    "Employees",
                    dataset,
                    PlotOrientation.VERTICAL,
                    false,
                    true,
                    false
            );

            // Customize the chart appearance
            CategoryPlot plot = barChart.getCategoryPlot();
            plot.setRangeGridlinePaint(new Color(0x4F82C0)); // CG Blue for gridlines
            plot.setBackgroundPaint(Color.WHITE); // White background

            // Customize bars color
            BarRenderer renderer = (BarRenderer) plot.getRenderer();
            renderer.setSeriesPaint(0, new Color(0x99B7D7)); // Carolina Blue for bars

            // Create a panel for displaying the chart
            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new Dimension(800, 600));

            // Add the chart to the panel (similar to your previous code)
            jPanel5.removeAll();
            jPanel5.add(chartPanel);
            jPanel5.validate();

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
