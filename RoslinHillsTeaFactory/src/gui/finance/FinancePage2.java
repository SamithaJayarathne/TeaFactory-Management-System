package gui.finance;

import gui.HRDeapartment.*;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import model.MySQL;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

public class FinancePage2 extends javax.swing.JPanel {

    public FinancePage2() {
        initComponents();
        loadMonthlyExpensesChart();
        loadDailyExpensesChart();

    }

    private void loadMonthlyExpensesChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String query = "SELECT "
                + "YEAR(date) AS year_number, "
                + "MONTH(date) AS month_number, "
                + "SUM(total_amount) AS total "
                + "FROM expences "
                + "GROUP BY year_number, month_number";
        try {
            ResultSet rs = MySQL.executeSearch(query);

            while (rs.next()) {
                String month = rs.getString("month_number");
                double total = rs.getDouble("total");
                dataset.addValue(total, "Expenses", month);
            }

            // Create Line Chart
            JFreeChart chart = ChartFactory.createLineChart(
                    "Monthly Expenses", // Chart title
                    "Month", // X-Axis label
                    "Total Amount (Rs.)", // Y-Axis label
                    dataset,
                    PlotOrientation.VERTICAL,
                    false, true, false
            );

            // Customize plot
            CategoryPlot plot = chart.getCategoryPlot();
            plot.setBackgroundPaint(new Color(0xF9FAFB));       // Light background
            plot.setRangeGridlinePaint(new Color(0xCBD5E1));    // Soft gray grid lines
            plot.setOutlineVisible(false);

            // Customize renderer
            LineAndShapeRenderer renderer = new LineAndShapeRenderer();
            renderer.setSeriesPaint(0, new Color(0xEF4444));    // Primary red
            renderer.setSeriesStroke(0, new BasicStroke(2.0f)); // Thicker line
//        renderer.setDefaultShapesVisible(true);
//        renderer.setDefaultShapesFilled(true);
            plot.setRenderer(renderer);

            // Customize fonts
            Font labelFont = new Font("Segoe UI", Font.PLAIN, 12);
            Font titleFont = new Font("Segoe UI", Font.BOLD, 16);

            chart.getTitle().setFont(titleFont);
            plot.getDomainAxis().setLabelFont(labelFont);
            plot.getDomainAxis().setTickLabelFont(labelFont);
            plot.getRangeAxis().setLabelFont(labelFont);
            plot.getRangeAxis().setTickLabelFont(labelFont);

            chart.setBorderVisible(false);
            chart.setBackgroundPaint(Color.WHITE);

            // Add to panel
            ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(jPanel5.getWidth(), jPanel5.getHeight()));
            chartPanel.setBackground(Color.WHITE);
            chartPanel.setOpaque(false);
            chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

            jPanel5.removeAll();
            jPanel5.setLayout(new BorderLayout());
            jPanel5.add(chartPanel, BorderLayout.CENTER);
            jPanel5.revalidate();
            jPanel5.repaint();

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading chart: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
    
    private void loadDailyExpensesChart() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    String query = "SELECT "
            + "DATE(date) AS day, "
            + "SUM(total_amount) AS total "
            + "FROM expences "
            + "WHERE date >= CURDATE() - INTERVAL 30 DAY "
            + "GROUP BY day "
            + "ORDER BY day ASC";
    try {
        ResultSet rs = MySQL.executeSearch(query);

        while (rs.next()) {
            String day = rs.getString("day"); // Format: YYYY-MM-DD
            double total = rs.getDouble("total");
            dataset.addValue(total, "Expenses", day);
        }

        // Create Line Chart
        JFreeChart chart = ChartFactory.createLineChart(
                "Daily Expenses (Last 30 Days)", // Chart title
                "Date",                          // X-Axis label
                "Total Amount (Rs.)",            // Y-Axis label
                dataset,
                PlotOrientation.VERTICAL,
                false, true, false
        );

        // Customize plot
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(0xF9FAFB));
        plot.setRangeGridlinePaint(new Color(0xCBD5E1));
        plot.setOutlineVisible(false);

        // Customize renderer
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesPaint(0, new Color(0xEF4444));
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        plot.setRenderer(renderer);

        // Customize fonts
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 12);
        Font titleFont = new Font("Segoe UI", Font.BOLD, 16);

        chart.getTitle().setFont(titleFont);
        plot.getDomainAxis().setLabelFont(labelFont);
        plot.getDomainAxis().setTickLabelFont(labelFont);
        plot.getRangeAxis().setLabelFont(labelFont);
        plot.getRangeAxis().setTickLabelFont(labelFont);

        chart.setBorderVisible(false);
        chart.setBackgroundPaint(Color.WHITE);

        // Add to panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(jPanel6.getWidth(), jPanel6.getHeight()));
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setOpaque(false);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        jPanel6.removeAll();
        jPanel6.setLayout(new BorderLayout());
        jPanel6.add(chartPanel, BorderLayout.CENTER);
        jPanel6.revalidate();
        jPanel6.repaint();

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading chart: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();

        jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.LINE_AXIS));

        jPanel6.setLayout(new javax.swing.BoxLayout(jPanel6, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 993, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}
