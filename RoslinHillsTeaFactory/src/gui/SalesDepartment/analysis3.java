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
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Lenovo
 */
public class analysis3 extends javax.swing.JPanel {

    /**
     * Creates new form analysis3
     */
    public analysis3() {
        initComponents();
        loadPaymentMethodSales();
        loadSalesLineChart();
        loadTopItem();
        loadDailyTotalSales();
    }

    private void loadPaymentMethodSales() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        HashMap<String, Color> methodColors = new HashMap<>(); // Store colors for each payment method

        String query = "SELECT pm.method AS payment_method_name, SUM(s.total_amount) AS total_sales FROM sales s JOIN payment_method pm ON s.payment_method_id = pm.id GROUP BY pm.method ORDER BY total_sales DESC;";

        try {
            ResultSet rs = MySQL.executeSearch(query);
            int colorIndex = 0;

            while (rs.next()) {
                String paymentMethodName = rs.getString("payment_method_name"); // Get payment method name
                double totalSales = rs.getDouble("total_sales"); // Get total sales amount

                dataset.addValue(totalSales, "Total Sales", paymentMethodName); // Use payment method name as the key

                // Assign a unique color per payment method
                if (!methodColors.containsKey(paymentMethodName)) {
                    methodColors.put(paymentMethodName, getDynamicColor(colorIndex));
                    colorIndex++;
                }
            }

            // Create a bar chart
            JFreeChart barChart = ChartFactory.createBarChart(
                    "Sales by Payment Method", // Chart title
                    "Payment Method", // X-axis label
                    "Total Sales (Amount)", // Y-axis label
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

            // Custom BarRenderer to assign colors per payment method
            BarRenderer renderer = new BarRenderer() {
                @Override
                public Paint getItemPaint(int row, int column) {
                    String paymentMethodName = (String) dataset.getColumnKey(column);
                    return methodColors.get(paymentMethodName);
                }
            };

            renderer.setDrawBarOutline(false);
            renderer.setShadowVisible(false);
            renderer.setBarPainter(new org.jfree.chart.renderer.category.StandardBarPainter()); // Flat bar style
            renderer.setMaximumBarWidth(0.1);
            plot.setRenderer(renderer);

            // Customize chart title
            barChart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 18));
            barChart.getTitle().setPaint(Color.DARK_GRAY);

            // Customize X-axis (Payment Methods)
            plot.getDomainAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
            plot.getDomainAxis().setLabelPaint(Color.DARK_GRAY);
            plot.getDomainAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

            // Customize Y-axis (Total Sales)
            plot.getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
            plot.getRangeAxis().setLabelPaint(Color.DARK_GRAY);
            plot.getRangeAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

            // Create and add chart panel
            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new Dimension(400, 600));
            chartPanel.setBackground(Color.WHITE);

            // Clear and add the chart to the GUI panel (jPanel1)
            jPanel1.removeAll();
            jPanel1.add(chartPanel);
            jPanel1.validate();

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

    private void loadSalesLineChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // SQL query to retrieve daily sales data
        String query = "SELECT DATE(date) AS sales_date, SUM(total_amount) AS total_sales FROM sales GROUP BY DATE(DATE) ORDER BY sales_date;";

        try {
            ResultSet rs = MySQL.executeSearch(query);

            // Populate the dataset with query results
            while (rs.next()) {
                String orderDate = rs.getString("sales_date"); // Get order date
                double totalSales = rs.getDouble("total_sales"); // Get total sales
                dataset.addValue(totalSales, "Sales", orderDate); // Add to dataset
            }

            // Create a line chart
            JFreeChart lineChart = ChartFactory.createLineChart(
                    " Sales Line Chart", // Chart title
                    "Sales Date", // X-axis label
                    "Total Sales", // Y-axis label
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
            chartPanel.setPreferredSize(new Dimension(400, 600));
            chartPanel.setBackground(Color.WHITE);

            // Clear and add the chart to the GUI panel (e.g., jPanel1)
            jPanel2.removeAll();
            jPanel2.add(chartPanel);
            jPanel2.validate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    
      private void loadTopItem() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        HashMap<String, Color> methodColors = new HashMap<>(); // Store colors for each payment method

        String query = "SELECT products.title AS product_name, SUM(sales_item.qty) AS total_quantity FROM sales_item INNER JOIN products ON products.id = sales_item.product_stock_id GROUP BY products.title ORDER BY total_quantity DESC LIMIT 10;";

        try {
            ResultSet rs = MySQL.executeSearch(query);
            int colorIndex = 0;

            while (rs.next()) {
                String productName = rs.getString("product_name"); // Get payment method name
                double totalqty = rs.getDouble("total_quantity"); // Get total sales amount

                dataset.addValue(totalqty, "Total Sales", productName); // Use payment method name as the key

                // Assign a unique color per payment method
                if (!methodColors.containsKey(productName)) {
                    methodColors.put(productName, getDynamicColor(colorIndex));
                    colorIndex++;
                }
            }

            // Create a bar chart
            JFreeChart barChart = ChartFactory.createBarChart(
                    "Top-Selling Items by Quantity", // Chart title
                    "Product Name", // X-axis label
                    "Total Sales (Amount)", // Y-axis label
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

            // Custom BarRenderer to assign colors per payment method
            BarRenderer renderer = new BarRenderer() {
                @Override
                public Paint getItemPaint(int row, int column) {
                    String paymentMethodName = (String) dataset.getColumnKey(column);
                    return methodColors.get(paymentMethodName);
                }
            };

            renderer.setDrawBarOutline(false);
            renderer.setShadowVisible(false);
            renderer.setBarPainter(new org.jfree.chart.renderer.category.StandardBarPainter()); // Flat bar style
            renderer.setMaximumBarWidth(0.1);
            plot.setRenderer(renderer);

            // Customize chart title
            barChart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 18));
            barChart.getTitle().setPaint(Color.DARK_GRAY);

            // Customize X-axis (Payment Methods)
            plot.getDomainAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
            plot.getDomainAxis().setLabelPaint(Color.DARK_GRAY);
            plot.getDomainAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

            // Customize Y-axis (Total Sales)
            plot.getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
            plot.getRangeAxis().setLabelPaint(Color.DARK_GRAY);
            plot.getRangeAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

            // Create and add chart panel
            ChartPanel chartPanel = new ChartPanel(barChart);
            chartPanel.setPreferredSize(new Dimension(200, 500));
            chartPanel.setBackground(Color.WHITE);

            // Clear and add the chart to the GUI panel (jPanel1)
            jPanel5.removeAll();
            jPanel5.add(chartPanel);
            jPanel5.validate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
      
      
       private void loadDailyTotalSales() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // SQL query to retrieve daily sales data
        String query = "SELECT DATE(s.date) AS sales_date, si.id AS item_id, SUM(si.qty * si.unit_price) AS total_sales_value FROM sales s INNER JOIN sales_item si ON s.sales_id = si.sales_sales_id GROUP BY DATE(s.date), si.id ORDER BY sales_date, item_id;";

        try {
            ResultSet rs = MySQL.executeSearch(query);

            // Populate the dataset with query results
            while (rs.next()) {
                String orderDate = rs.getString("sales_date"); // Get order date
                double totalSales = rs.getDouble("total_sales_value"); // Get total sales
                dataset.addValue(totalSales, "Sales", orderDate); // Add to dataset
            }

            // Create a line chart
            JFreeChart lineChart = ChartFactory.createLineChart(
                    "Daily Total Sales Value by Item", // Chart title
                    "Sales Date", // X-axis label
                    "total_sales_value", // Y-axis label
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
            chartPanel.setPreferredSize(new Dimension(400, 600));
            chartPanel.setBackground(Color.WHITE);

            // Clear and add the chart to the GUI panel (e.g., jPanel1)
            jPanel6.removeAll();
            jPanel6.add(chartPanel);
            jPanel6.validate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();

        jPanel1.setLayout(new java.awt.CardLayout());

        jPanel2.setLayout(new java.awt.CardLayout());

        jPanel5.setLayout(new java.awt.CardLayout());

        jPanel6.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    // End of variables declaration//GEN-END:variables
}
