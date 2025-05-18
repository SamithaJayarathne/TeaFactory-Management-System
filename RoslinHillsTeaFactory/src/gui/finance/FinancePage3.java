package gui.finance;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.MySQL;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class FinancePage3 extends javax.swing.JPanel {

    public FinancePage3() {
        initComponents();
        loadDailyIncomeVsExpensesChart();
        loadMonthlyIncomeVsExpensesChart();
       

    }
    
   private void loadMonthlyIncomeVsExpensesChart() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    String query = "SELECT "
            + "YEAR(date) AS year_number, "
            + "MONTH(date) AS month_number, "
            + "SUM(CASE WHEN source = 'income' THEN total_amount ELSE 0 END) AS income_total, "
            + "SUM(CASE WHEN source = 'expense' THEN total_amount ELSE 0 END) AS expense_total "
            + "FROM ( "
            + "   SELECT date, total_amount, 'income' AS source FROM incomes "
            + "   UNION ALL "
            + "   SELECT date, total_amount AS total, 'expense' AS source FROM expences "
            + ") AS combined_data "
            + "GROUP BY year_number, month_number "
            + "ORDER BY year_number, month_number";

    try {
        ResultSet rs = MySQL.executeSearch(query);

        while (rs.next()) {
            String month = rs.getString("year_number") + "-" + String.format("%02d", rs.getInt("month_number"));
            dataset.addValue(rs.getDouble("income_total"), "Income", month);
            dataset.addValue(rs.getDouble("expense_total"), "Expenses", month);
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Monthly Income vs Expenses",
                "Month",
                "Total Amount (Rs.)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        customizeChart(chart);
        addChartToPanel(chart, jPanel5);

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading monthly income vs expenses chart: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void loadDailyIncomeVsExpensesChart() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    String query = "SELECT "
            + "DATE(date) AS date_only, "
            + "SUM(CASE WHEN source = 'income' THEN total_amount ELSE 0 END) AS income_total, "
            + "SUM(CASE WHEN source = 'expense' THEN total_amount ELSE 0 END) AS expense_total "
            + "FROM ( "
            + "   SELECT date, total_amount, 'income' AS source FROM incomes WHERE date >= DATE_SUB(NOW(), INTERVAL 30 DAY) "
            + "   UNION ALL "
            + "   SELECT date, total_amount AS total, 'expense' AS source FROM expences WHERE date >= DATE_SUB(NOW(), INTERVAL 30 DAY) "
            + ") AS combined_data "
            + "GROUP BY date_only "
            + "ORDER BY date_only";

    try {
        ResultSet rs = MySQL.executeSearch(query);

        while (rs.next()) {
            String date = rs.getString("date_only");
            dataset.addValue(rs.getDouble("income_total"), "Income", date);
            dataset.addValue(rs.getDouble("expense_total"), "Expenses", date);
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Daily Income vs Expenses (Last 30 Days)",
                "Date",
                "Total Amount (Rs.)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false
        );

        customizeChart(chart);
        addChartToPanel(chart, jPanel6);

    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading daily income vs expenses chart: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void customizeChart(JFreeChart chart) {
    CategoryPlot plot = chart.getCategoryPlot();
    plot.setBackgroundPaint(new Color(0xF9FAFB));
    plot.setRangeGridlinePaint(new Color(0xCBD5E1));
    plot.setOutlineVisible(false);

    LineAndShapeRenderer renderer = new LineAndShapeRenderer();
    renderer.setSeriesPaint(0, new Color(0x10B981)); // Income - Green
    renderer.setSeriesPaint(1, new Color(0xEF4444)); // Expenses - Red
    renderer.setSeriesStroke(0, new BasicStroke(2.0f));
    renderer.setSeriesStroke(1, new BasicStroke(2.0f));
    plot.setRenderer(renderer);

    Font labelFont = new Font("Segoe UI", Font.PLAIN, 12);
    Font titleFont = new Font("Segoe UI", Font.BOLD, 16);

    chart.getTitle().setFont(titleFont);
    plot.getDomainAxis().setLabelFont(labelFont);
    plot.getDomainAxis().setTickLabelFont(labelFont);
    plot.getRangeAxis().setLabelFont(labelFont);
    plot.getRangeAxis().setTickLabelFont(labelFont);

    chart.setBackgroundPaint(Color.WHITE);
    chart.setBorderVisible(false);
}

private void addChartToPanel(JFreeChart chart, JPanel panel) {
    panel.removeAll();
    panel.setLayout(new BorderLayout());

    ChartPanel chartPanel = new ChartPanel(chart);
    chartPanel.setPreferredSize(new Dimension(panel.getWidth(), panel.getHeight()));
    chartPanel.setBackground(Color.WHITE);
    chartPanel.setOpaque(false);
    chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    panel.add(chartPanel, BorderLayout.CENTER);
    panel.revalidate();
    panel.repaint();
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
