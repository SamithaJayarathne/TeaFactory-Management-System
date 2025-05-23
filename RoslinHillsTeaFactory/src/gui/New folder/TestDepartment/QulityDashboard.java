/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.TestDepartment;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Paint;
import java.sql.ResultSet;
import javax.swing.BorderFactory;
import model.MySQL;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.CategoryToolTipGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author hansa
 */
public class QulityDashboard extends javax.swing.JPanel {

    /**
     * Creates new form qulityDashboard
     */
    public QulityDashboard() {
        initComponents();
        showPieChartV();
        showPieChartV2();
        loadQualityEvaluationsOverTime();
        loadTeaBatchQualityGradeCount();
    }
/////////////////////////////////////////////////////////////////////////////////////////////////

    private void loadQualityEvaluationsOverTime() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String query = "SELECT DATE(qc.date) AS inspection_date, COUNT(*) AS total "
                + "FROM quality_control qc "
                + "GROUP BY DATE(qc.date) "
                + "ORDER BY inspection_date;";

        try {
            ResultSet rs = MySQL.executeSearch(query);

            while (rs.next()) {
                String date = rs.getString("inspection_date");
                int total = rs.getInt("total");
                dataset.addValue(total, "Inspections", date);
            }

            JFreeChart lineChart = ChartFactory.createLineChart(
                    "Quality Evaluations Over Time", // Chart title
                    "Date", // X-axis label
                    "Number of Inspections", // Y-axis label
                    dataset, // Dataset
                    PlotOrientation.VERTICAL, // Orientation
                    false, // Legend
                    true, // Tooltips
                    false // URLs
            );

            CategoryPlot plot = lineChart.getCategoryPlot();
            plot.setBackgroundPaint(Color.WHITE);
            plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
            plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
            plot.setOutlineVisible(false);

            LineAndShapeRenderer renderer = new LineAndShapeRenderer();
            renderer.setSeriesPaint(0, new Color(59, 130, 246)); // Tailwind-style Blue
            renderer.setSeriesStroke(0, new BasicStroke(2.5f));
            renderer.setBaseToolTipGenerator(new CategoryToolTipGenerator() {
                @Override
                public String generateToolTip(CategoryDataset dataset, int row, int column) {
                    String date = (String) dataset.getColumnKey(column);
                    Number count = dataset.getValue(row, column);
                    return date + ": " + count + " inspections";
                }
            });

            plot.setRenderer(renderer);

            // Customize fonts and title
            lineChart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 18));
            lineChart.getTitle().setPaint(Color.DARK_GRAY);
            plot.getDomainAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
            plot.getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
            plot.getRangeAxis().setLabelPaint(Color.DARK_GRAY);
            plot.getDomainAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
            plot.getRangeAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

            ChartPanel chartPanel = new ChartPanel(lineChart);
            chartPanel.setPreferredSize(new Dimension(800, 600));
            chartPanel.setBackground(Color.WHITE);

            // Clear and add chart to jPanel3
            jPanel14.removeAll();
            jPanel14.add(chartPanel);
            jPanel14.validate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadTeaBatchQualityGradeCount() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String query = "SELECT tea_batch_id, quality_grade, COUNT(*) AS count "
                + "FROM quality_control "
                + "GROUP BY tea_batch_id, quality_grade "
                + "ORDER BY tea_batch_id;";

        try {
            ResultSet rs = MySQL.executeSearch(query);

            while (rs.next()) {
                String batchId = "Batch " + rs.getString("tea_batch_id");
                String grade = rs.getString("quality_grade");
                int count = rs.getInt("count");
                dataset.addValue(count, grade, batchId);
            }

            JFreeChart stackedBarChart = ChartFactory.createStackedBarChart(
                    "Tea Batch Quality Grade Count", // Chart title
                    "Tea Batch", // X-axis label
                    "Count", // Y-axis label
                    dataset, // Dataset
                    PlotOrientation.VERTICAL, // Orientation
                    true, // Legend (shows grades)
                    true, // Tooltips
                    false // URLs
            );

            CategoryPlot plot = stackedBarChart.getCategoryPlot();
            plot.setBackgroundPaint(Color.WHITE);
            plot.setDomainGridlinePaint(Color.LIGHT_GRAY);
            plot.setRangeGridlinePaint(Color.LIGHT_GRAY);
            plot.setOutlineVisible(false);

            StackedBarRenderer renderer = new StackedBarRenderer();
            renderer.setDrawBarOutline(false);
            renderer.setBarPainter(new StandardBarPainter());
            renderer.setShadowVisible(false);

            // Tooltip for each stack segment
            renderer.setBaseToolTipGenerator(new CategoryToolTipGenerator() {
                @Override
                public String generateToolTip(CategoryDataset dataset, int row, int column) {
                    String grade = (String) dataset.getRowKey(row);
                    String batch = (String) dataset.getColumnKey(column);
                    Number value = dataset.getValue(row, column);
                    return grade + " in " + batch + ": " + value;
                }
            });

            // Optional: Custom colors per grade
            Paint[] gradeColors = new Paint[]{
                new Color(34, 197, 94), // A grade - green
                new Color(59, 130, 246), // B grade - blue
                new Color(234, 179, 8), // C grade - yellow
                new Color(239, 68, 68) // D grade - red
            };

            for (int i = 0; i < dataset.getRowCount(); i++) {
                if (i < gradeColors.length) {
                    renderer.setSeriesPaint(i, gradeColors[i]);
                }
            }

            plot.setRenderer(renderer);

            // Customize chart title and fonts
            stackedBarChart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 18));
            stackedBarChart.getTitle().setPaint(Color.DARK_GRAY);
            plot.getDomainAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
            plot.getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
            plot.getRangeAxis().setLabelPaint(Color.DARK_GRAY);
            plot.getDomainAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
            plot.getRangeAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));

            ChartPanel chartPanel = new ChartPanel(stackedBarChart);
            chartPanel.setPreferredSize(new Dimension(800, 600));
            chartPanel.setBackground(Color.WHITE);

            // Add to your panel (like jPanel3)
            jPanel16.removeAll();
            jPanel16.add(chartPanel);
            jPanel16.validate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   public void showPieChartV() {

    DefaultPieDataset dataset = new DefaultPieDataset();

    String query = """
        SELECT qs.name AS quality_status, COUNT(*) AS quality_status_count
        FROM quality_control q
        JOIN quality_status qs ON q.quality_status_id = qs.id
        GROUP BY qs.name
    """;

    try {
        ResultSet rs = MySQL.executeSearch(query);

        while (rs.next()) {
            dataset.setValue(rs.getString("quality_status"), rs.getDouble("quality_status_count"));
        }

        JFreeChart pieChart = ChartFactory.createPieChart(
            "Quality Status Distribution",
            dataset,
            true,
            true,
            false
        );

        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setBackgroundPaint(new Color(0xF9FAFB));
        plot.setOutlineVisible(false);
        plot.setShadowPaint(null);

        // Section colors
        plot.setSectionPaint("Approved", new Color(0x4F82C0)); // CG Blue
        plot.setSectionPaint("Reject", new Color(0xEF4444));   // Red

        // Font and label styling
        Font titleFont = new Font("Segoe UI", Font.BOLD, 16);
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 12);
        Font legendFont = new Font("Segoe UI", Font.PLAIN, 12);

        pieChart.getTitle().setFont(titleFont);
        plot.setLabelFont(labelFont);
        pieChart.getLegend().setItemFont(legendFont);

        plot.setLabelBackgroundPaint(new Color(0xE5E7EB));
        plot.setLabelOutlinePaint(null);
        plot.setLabelShadowPaint(null);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})"));

        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(820, 500));
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        jPanel13.removeAll();
        jPanel13.setLayout(new BorderLayout());
        jPanel13.add(chartPanel, BorderLayout.CENTER);
        jPanel13.revalidate();
        jPanel13.repaint();

    } catch (Exception e) {
        e.printStackTrace();
    }
}


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void showPieChartV2() {

    DefaultPieDataset dataset = new DefaultPieDataset();

    String query = """
        SELECT at.name AS action_taken, COUNT(*) AS action_taken_count
        FROM quality_control q
        JOIN action_taken at ON q.action_taken_id = at.id
        GROUP BY at.name
    """;

    try {
        ResultSet rs = MySQL.executeSearch(query);

        while (rs.next()) {
            dataset.setValue(rs.getString("action_taken"), rs.getDouble("action_taken_count"));
        }

        JFreeChart pieChart = ChartFactory.createPieChart(
            "Quality Action Taken Distribution",
            dataset,
            true,
            true,
            false
        );

        PiePlot plot = (PiePlot) pieChart.getPlot();
        plot.setBackgroundPaint(new Color(0xF9FAFB));
        plot.setOutlineVisible(false);
        plot.setShadowPaint(null);

        // Section colors
        plot.setSectionPaint("None", new Color(0x10B981));               // Success Green
        plot.setSectionPaint("Re-dry Suggested", new Color(0xF59E0B));   // Orange
        plot.setSectionPaint("Reprocess Required", new Color(0xEF4444)); // Error Red

        // Font and label styling
        Font titleFont = new Font("Segoe UI", Font.BOLD, 16);
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 12);
        Font legendFont = new Font("Segoe UI", Font.PLAIN, 12);

        pieChart.getTitle().setFont(titleFont);
        plot.setLabelFont(labelFont);
        pieChart.getLegend().setItemFont(legendFont);

        plot.setLabelBackgroundPaint(new Color(0xE5E7EB));
        plot.setLabelOutlinePaint(null);
        plot.setLabelShadowPaint(null);
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {1} ({2})"));

        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new Dimension(820, 500));
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        jPanel12.removeAll();
        jPanel12.setLayout(new BorderLayout());
        jPanel12.add(chartPanel, BorderLayout.CENTER);
        jPanel12.revalidate();
        jPanel12.repaint();

    } catch (Exception e) {
        e.printStackTrace();
    }
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel13 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();

        jPanel13.setLayout(new java.awt.BorderLayout());

        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel14.setLayout(new java.awt.BorderLayout());

        jPanel15.setLayout(new java.awt.BorderLayout());
        jPanel14.add(jPanel15, java.awt.BorderLayout.CENTER);

        jPanel16.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 280, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    // End of variables declaration//GEN-END:variables
}
