/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.SalesDepartment;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import java.sql.ResultSet;
import java.util.HashMap;
import model.MySQL;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author Lenovo
 */
public class analysis1 extends javax.swing.JPanel {

    /**
     * Creates new form analysis1
     */
    public analysis1() {
        initComponents();
        loadGenderChart();
        loadCityByCustomer();

    }

    private void loadGenderChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        String query = "SELECT gender.`name` AS gender, COUNT(customer.nic) AS total FROM customer JOIN gender ON customer.gender_id = gender.id GROUP BY gender.`name`;";

        try {
            ResultSet rs = MySQL.executeSearch(query);

            while (rs.next()) {
                int count = rs.getInt("total");
                String name = rs.getString("gender");

                dataset.setValue(name, count);
            }

            // Create a pie chart
            JFreeChart pieChart = ChartFactory.createPieChart(
                    "Customer Count By Gender", // Chart title
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

            // Set section colors (customize as needed)
            plot.setSectionPaint("Male", new Color(76, 175, 80)); // Green
            plot.setSectionPaint("Female", new Color(255, 87, 34)); // Deep Orange

            // Customize label font and color
            plot.setLabelFont(new Font("SansSerif", Font.BOLD, 12));
            plot.setLabelPaint(Color.DARK_GRAY);
            plot.setLabelBackgroundPaint(Color.WHITE);
            plot.setLabelShadowPaint(null);
            plot.setLabelOutlinePaint(null);

            // Create a custom chart panel
            ChartPanel chartPanel = new ChartPanel(pieChart);
            chartPanel.setPreferredSize(new Dimension(500, 500));
            chartPanel.setBackground(Color.WHITE); // Set chart panel background color

            // Clear and add the chart to the GUI panel (jPanel2)
            jPanel2.removeAll();
            jPanel2.add(chartPanel);
            jPanel2.validate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

private void loadCityByCustomer() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    HashMap<String, Color> cityColors = new HashMap<>(); // Store colors for each city

    String query = "SELECT city.`name` AS city, COUNT(customer.nic) AS total FROM customer JOIN city ON customer.city_id = city.id GROUP BY city.`name`;";

    try {
        ResultSet rs = MySQL.executeSearch(query);
        int colorIndex = 0;

        while (rs.next()) {
            String city = rs.getString("city");
            int count = rs.getInt("total");
            dataset.addValue(count, "Customers", city);

            // Assign a unique color to each city
            if (!cityColors.containsKey(city)) {
                cityColors.put(city, getDynamicColor(colorIndex));
                colorIndex++;
            }
        }

        // Create a bar chart
        JFreeChart barChart = ChartFactory.createBarChart(
                "Customer Count By City", // Chart title
                "City", // X-axis label
                "Number of Customers", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Orientation
                false, // Legend (not needed for a clean look)
                true, // Tooltips
                false // URLs
        );

        // Customize the bar chart
        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
        plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
        plot.setOutlineVisible(false);

        // Custom BarRenderer to assign colors per city
        BarRenderer renderer = new BarRenderer() {
            @Override
            public Paint getItemPaint(int row, int column) {
                String city = (String) dataset.getColumnKey(column); // Get city name
                return cityColors.get(city); // Return city-specific color
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

        // Customize X-axis (City Names)
        plot.getDomainAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
        plot.getDomainAxis().setLabelPaint(Color.DARK_GRAY);
        plot.getDomainAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

        // Customize Y-axis (Number of Customers)
        plot.getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
        plot.getRangeAxis().setLabelPaint(Color.DARK_GRAY);
        plot.getRangeAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

        // Create and add chart panel
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        chartPanel.setBackground(Color.WHITE);

        // Clear and add the chart to the GUI panel (jPanel3)
        jPanel3.removeAll();
        jPanel3.add(chartPanel);
        jPanel3.validate();

    } catch (Exception e) {
        e.printStackTrace();
    }
}

// Generate unique colors dynamically
private Color getDynamicColor(int index) {
    Color[] colorPalette = {
        new Color(76, 175, 80),  // Green
        new Color(255, 87, 34),  // Orange
        new Color(33, 150, 243), // Blue
        new Color(255, 193, 7),  // Yellow
        new Color(156, 39, 176), // Purple
        new Color(0, 188, 212),  // Cyan
        new Color(244, 67, 54),  // Red
        new Color(63, 81, 181)   // Indigo
    };
    return colorPalette[index % colorPalette.length]; // Cycle through colors
}
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();

        jPanel2.setLayout(new java.awt.CardLayout(10, 10));

        jPanel3.setLayout(new java.awt.CardLayout(10, 10));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}
