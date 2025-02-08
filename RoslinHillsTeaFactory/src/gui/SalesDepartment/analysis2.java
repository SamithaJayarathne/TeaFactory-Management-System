/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.SalesDepartment;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import java.sql.ResultSet;
import java.util.HashMap;
import model.MySQL;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Lenovo
 */
public class analysis2 extends javax.swing.JPanel {

    /**
     * Creates new form analysis2
     */
    public analysis2() {
        initComponents();
        loadOrderStatusChart();
        loadMonthlyOrder();
        loadSalesLineChart();
    }

    private void loadMonthlyOrder() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    HashMap<String, Color> monthColors = new HashMap<>(); // Store colors for each month

    String query = "SELECT MONTHNAME(o.order_date) AS order_month, s.`status` AS order_status, COUNT(*) AS order_count  FROM `order` o JOIN `order_status` s ON o.order_status_id = s.id GROUP BY MONTH(o.order_date), MONTHNAME(o.order_date), s.`status` ORDER BY MONTH(o.order_date);";

    try {
        ResultSet rs = MySQL.executeSearch(query);
        int colorIndex = 0;

        while (rs.next()) {
            String month = rs.getString("order_month"); // Get month name
            String status = rs.getString("order_status"); // Get status (e.g., "cancel", "pending")
            int count = rs.getInt("order_count"); // Get count

            dataset.addValue(count, status, month); // Add data to the dataset (status as row, month as column)

            // Assign a unique color to each month (if needed)
            if (!monthColors.containsKey(month)) {
                monthColors.put(month, getDynamicColor(colorIndex));
                colorIndex++;
            }
        }

        // Create a bar chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "Monthly Order Status", // Chart title
                "Month", // X-axis label
                "Number of Orders", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Orientation
                true, // Include legend
                true, // Tooltips
                false // URLs
        );

        // Customize the bar chart
        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.setOutlineVisible(false);

        // Custom BarRenderer to assign colors per month
        BarRenderer renderer = new BarRenderer() {
            @Override
            public Paint getItemPaint(int row, int column) {
                String month = (String) dataset.getColumnKey(column); // Get month name
                return monthColors.get(month); // Return month-specific color
            }
        };

        renderer.setDrawBarOutline(false);
        renderer.setShadowVisible(false);
        renderer.setBarPainter(new org.jfree.chart.renderer.category.StandardBarPainter()); // Flat bar style
        renderer.setMaximumBarWidth(0.1);
        plot.setRenderer(renderer); // Apply custom renderer

        // Customize chart title
        barChart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 18));
        barChart.getTitle().setPaint(Color.DARK_GRAY);

        // Customize X-axis (Month Names)
        plot.getDomainAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
        plot.getDomainAxis().setLabelPaint(Color.DARK_GRAY);
        plot.getDomainAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

        // Customize Y-axis (Number of Orders)
        plot.getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
        plot.getRangeAxis().setLabelPaint(Color.DARK_GRAY);
        plot.getRangeAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

        // Create and add chart panel
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(200, 300));
        chartPanel.setBackground(Color.WHITE);

        // Clear and add the chart to the GUI panel (jPanel2)
        jPanel2.removeAll();
        jPanel2.add(chartPanel);
        jPanel2.validate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

    private Color getDynamicColor(int index) {
        Color[] colorPalette = {
            new Color(76, 175, 80), // Green
            new Color(255, 87, 34), // Orange
            new Color(33, 150, 243), // Blue
            new Color(255, 193, 7), // Yellow
            new Color(156, 39, 176), // Purple
            new Color(0, 188, 212), // Cyan
            new Color(244, 67, 54), // Red
            new Color(63, 81, 181) // Indigo
        };
        return colorPalette[index % colorPalette.length]; // Cycle through colors
    }

    private void loadOrderStatusChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        String query = "SELECT s.`status` AS order_status, COUNT(o.order_status_id) AS order_count FROM `order` o JOIN `order_status` s ON o.order_status_id = s.id GROUP BY s.`status`;";

        try {
            ResultSet rs = MySQL.executeSearch(query);

            while (rs.next()) {
                String status = rs.getString("order_status"); // Get status name as a string
                int count = rs.getInt("order_count"); // Get count as an integer

                dataset.setValue(status, count); // Add status and count to the dataset
            }

            // Create a pie chart
            JFreeChart pieChart = ChartFactory.createPieChart(
                    "Order Status Distribution", // Chart title
                    dataset, // Dataset
                    true, // Include legend
                    true, // Tooltips
                    false // URLs
            );

            // Customize the pie chart
            PiePlot plot = (PiePlot) pieChart.getPlot();
            plot.setBackgroundPaint(Color.WHITE); // Set plot background color
            plot.setOutlineVisible(false); // Remove plot outline

            // Remove the border around the chart itself
            pieChart.setBackgroundPaint(Color.WHITE); // Set chart background color
            pieChart.setBorderVisible(false); // Remove chart border

            // Customize section colors (customize as needed)
            plot.setSectionPaint("Pending", new Color(255, 193, 7)); // Yellow
            plot.setSectionPaint("Completed", new Color(76, 175, 80)); // Green
            plot.setSectionPaint("Cancelled", new Color(244, 67, 54)); // Red

            // Customize label font and color
            plot.setLabelFont(new Font("SansSerif", Font.BOLD, 12));
            plot.setLabelPaint(Color.DARK_GRAY);
            plot.setLabelBackgroundPaint(Color.WHITE);
            plot.setLabelShadowPaint(null);
            plot.setLabelOutlinePaint(null);

            // Create a custom chart panel
            ChartPanel chartPanel = new ChartPanel(pieChart);
            chartPanel.setPreferredSize(new Dimension(200, 200));
            chartPanel.setBackground(Color.WHITE); // Set chart panel background color

            // Clear and add the chart to the GUI panel (jPanel1)
            jPanel1.removeAll();
            jPanel1.add(chartPanel);
            jPanel1.validate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
     private void loadSalesLineChart() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    // SQL query to retrieve daily sales data
    String query = "SELECT DATE(order_date) AS order_date, SUM(total_amount) AS total_order FROM `order` GROUP BY DATE(order_date) ORDER BY order_date;";

    try {
        ResultSet rs = MySQL.executeSearch(query);

        // Populate the dataset with query results
        while (rs.next()) {
            String orderDate = rs.getString("order_date"); // Get order date
            double totalSales = rs.getDouble("total_order"); // Get total sales
            dataset.addValue(totalSales, "Sales", orderDate); // Add to dataset
        }

        // Create a line chart
        JFreeChart lineChart = ChartFactory.createLineChart(
                "Daily Order Line Chart", // Chart title
                "Order Date", // X-axis label
                "Total Order", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Orientation
                true, // Include legend
                true, // Tooltips
                false // URLs
        );

        // Customize the line chart
        CategoryPlot plot = lineChart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE); // Set background color
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY); // X-axis gridlines
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY); // Y-axis gridlines
        plot.setOutlineVisible(false); // Remove plot outline

        // Customize line renderer
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setSeriesPaint(0, new Color(76, 175, 80)); // Set line color (green)
        renderer.setSeriesStroke(0, new BasicStroke(2.0f)); // Set line thickness
        plot.setRenderer(renderer);

        // Customize X-axis (dates)
        plot.getDomainAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 12));
        plot.getDomainAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 10));
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45); // Rotate labels for readability

        // Customize Y-axis (sales)
        plot.getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 12));
        plot.getRangeAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 10));

        // Customize chart title
        lineChart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 16));
        lineChart.getTitle().setPaint(Color.DARK_GRAY);

        // Create chart panel
        ChartPanel chartPanel = new ChartPanel(lineChart);
        chartPanel.setPreferredSize(new Dimension(300, 300));
        chartPanel.setBackground(Color.WHITE);

        // Clear and add the chart to the GUI panel (e.g., jPanel1)
        jPanel3.removeAll();
        jPanel3.add(chartPanel);
        jPanel3.validate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        jPanel1.setLayout(new java.awt.CardLayout());

        jPanel2.setLayout(new java.awt.CardLayout());

        jPanel3.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 425, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 241, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
