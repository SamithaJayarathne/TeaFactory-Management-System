/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.Production;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import model.MySQL;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Kavindu Anupama
 */
public class DashBoard extends javax.swing.JPanel {

    /**
     * Creates new form DashBoard
     */
    public DashBoard() {
        initComponents();
        loadMachineMaintenanceNotification();
        loadProductionChart();
        loadWorkerSchedule();
        jScrollPane2.setBorder(new LineBorder(Color.BLACK, 4));
        jScrollPane5.setBorder(new LineBorder(Color.BLACK, 4));
        jPanel4.setBorder(new LineBorder(Color.BLACK, 4));
        jPanel2.setBorder(new LineBorder(Color.BLACK, 4));
        jPanel1.setBorder(new LineBorder(Color.BLACK, 4));
    }

    private void loadMachineMaintenanceNotification() {
        JPanel notificationPanel = new JPanel();
        notificationPanel.setLayout(new BoxLayout(notificationPanel, BoxLayout.Y_AXIS));
        notificationPanel.setBackground(Color.WHITE); // Overall background

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `machine_maintenance` "
                    + "INNER JOIN `machine` ON `machine`.`id` = `machine_maintenance`.`machine_id` "
                    + "WHERE `machine`.`machine_status_id` = '4' AND `machine_maintenance`.`details` IS NULL "
                    + "ORDER BY `machine_maintenance`.`date` ASC");
            LocalDate today = LocalDate.now();

            while (rs.next()) {
                String details = rs.getString("name");
                LocalDate scheduledDate = rs.getDate("machine_maintenance.date").toLocalDate();
                int machineId = rs.getInt("machine_id");

                // Create the notification panel with a compound border for style
                JPanel singleNotification = new JPanel(new BorderLayout());
                singleNotification.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(new Color(200, 200, 200), 1, true),
                        BorderFactory.createEmptyBorder(8, 12, 8, 12)
                ));
                singleNotification.setBackground(scheduledDate.equals(today)
                        ? new Color(230, 255, 230) // Light green tone if scheduled for today
                        : new Color(245, 245, 245)); // Light gray for other days
                singleNotification.setMaximumSize(new Dimension(600, 80)); // Fixed max height

                // Create a text panel for the notification's labels
                JPanel textPanel = new JPanel();
                textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
                textPanel.setOpaque(false);

                // Machine ID title
                JLabel titleLabel = new JLabel("Machine ID: " + machineId);
                titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

                // Maintenance details message
                JLabel messageLabel = new JLabel(details);
                messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));

                // Scheduled date information
                JLabel dateLabel = new JLabel("Scheduled: "
                        + scheduledDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                dateLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                dateLabel.setForeground(Color.DARK_GRAY);

                // Add labels with additional spacing between them
                textPanel.add(titleLabel);
                textPanel.add(Box.createVerticalStrut(5));
                textPanel.add(messageLabel);
                textPanel.add(Box.createVerticalStrut(5));
                textPanel.add(dateLabel);

                singleNotification.add(textPanel, BorderLayout.CENTER);
                notificationPanel.add(singleNotification);
                notificationPanel.add(Box.createVerticalStrut(8)); // Space between notifications
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set the notification panel into the scroll pane
        jScrollPane5.setViewportView(notificationPanel);

        // Enable scrolling by setting the vertical scrollbar policy to AS_NEEDED.
        jScrollPane5.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane5.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Hide the vertical scroll bar visually by setting its preferred size to 0.
        jScrollPane5.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

        // Increase scroll speed by setting a higher unit increment (in pixels)
        jScrollPane5.getVerticalScrollBar().setUnitIncrement(20);
    }

    private void loadWorkerSchedule() {
        JPanel notificationPanel = new JPanel();
        notificationPanel.setLayout(new BoxLayout(notificationPanel, BoxLayout.Y_AXIS));
        notificationPanel.setBackground(Color.WHITE); // Overall background

        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `shift` "
                    + "INNER JOIN `employees` ON `employees`.`nic` = `shift`.`employees_nic` "
                    + "INNER JOIN `shift_status` ON `shift_status`.`id` = `shift`.`shift_status_id` "
                    + "ORDER BY `shift`.`date` ASC");
            LocalDate today = LocalDate.now();

            while (rs.next()) {
                String fname = rs.getString("employees.fname");
                String lname = rs.getString("employees.lname");
                LocalDate scheduledDate = rs.getDate("date").toLocalDate();
                String shiftTime = rs.getString("shift_status.name");

                // Create the notification panel with a compound border for style
                JPanel singleNotification = new JPanel(new BorderLayout());
                singleNotification.setBorder(BorderFactory.createCompoundBorder(
                        new LineBorder(new Color(200, 200, 200), 1, true),
                        BorderFactory.createEmptyBorder(8, 12, 8, 12)
                ));
                singleNotification.setBackground(scheduledDate.equals(today)
                        ? new Color(230, 255, 230) // Light green tone if scheduled for today
                        : new Color(245, 245, 245)); // Light gray for other days
                singleNotification.setMaximumSize(new Dimension(600, 80)); // Fixed max height

                // Create a text panel for the notification's labels
                JPanel textPanel = new JPanel();
                textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
                textPanel.setOpaque(false);

                // Machine ID title
                JLabel titleLabel = new JLabel("Worker Name: " + fname + " " + lname);
                titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

                // Maintenance details message
                JLabel messageLabel = new JLabel("Shift Time: " + shiftTime);
                messageLabel.setFont(new Font("Segoe UI", Font.PLAIN, 13));

                // Scheduled date information
                JLabel dateLabel = new JLabel("Scheduled: "
                        + scheduledDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                dateLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
                dateLabel.setForeground(Color.DARK_GRAY);

                // Add labels with additional spacing between them
                textPanel.add(titleLabel);
                textPanel.add(Box.createVerticalStrut(5));
                textPanel.add(messageLabel);
                textPanel.add(Box.createVerticalStrut(5));
                textPanel.add(dateLabel);

                singleNotification.add(textPanel, BorderLayout.CENTER);
                notificationPanel.add(singleNotification);
                notificationPanel.add(Box.createVerticalStrut(8)); // Space between notifications
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set the notification panel into the scroll pane
        jScrollPane2.setViewportView(notificationPanel);

        // Enable scrolling by setting the vertical scrollbar policy to AS_NEEDED.
        jScrollPane2.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jScrollPane2.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        // Hide the vertical scroll bar visually by setting its preferred size to 0.
        jScrollPane2.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));

        // Increase scroll speed by setting a higher unit increment (in pixels)
        jScrollPane2.getVerticalScrollBar().setUnitIncrement(20);
    }

    private void loadProductionChart() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        String query
                = "SELECT 'Withering' AS stage, HOUR(w.start_time) AS hour, COUNT(w.tea_batch_id) AS batch_count "
                + "FROM withering w GROUP BY HOUR(w.start_time) "
                + "UNION ALL "
                + "SELECT 'Rolling', HOUR(r.start_time), COUNT(r.tea_batch_id) "
                + "FROM rolling r GROUP BY HOUR(r.start_time) "
                + "UNION ALL "
                + "SELECT 'Oxidation', HOUR(o.start_time), COUNT(o.tea_batch_id) "
                + "FROM oxidation o GROUP BY HOUR(o.start_time) "
                + "UNION ALL "
                + "SELECT 'Drying', HOUR(d.start_time), COUNT(d.tea_batch_id) "
                + "FROM drying d GROUP BY HOUR(d.start_time)";

        try {
            ResultSet rs = MySQL.executeSearch(query);

            while (rs.next()) {
                String stage = rs.getString("stage");
                int hour = rs.getInt("hour");
                int batchCount = rs.getInt("batch_count");

                dataset.addValue(batchCount, stage, String.format("%02d:00", hour));
            }

            JFreeChart lineChart = ChartFactory.createLineChart(
                    "Hourly Production Stage Throughput",
                    "Time (Hour)",
                    "Batches Processed",
                    dataset,
                    PlotOrientation.VERTICAL,
                    true,
                    true,
                    false
            );

            // High-quality rendering settings
            lineChart.setAntiAlias(true);
            lineChart.setTextAntiAlias(true);
            lineChart.setBackgroundPaint(Color.WHITE);
            lineChart.getTitle().setPaint(new Color(44, 62, 80));
            lineChart.getTitle().setFont(new Font("SansSerif", Font.BOLD, 16));

            // Apply additional rendering hints
            lineChart.getRenderingHints().put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            lineChart.getRenderingHints().put(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
            lineChart.getRenderingHints().put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

            CategoryPlot plot = lineChart.getCategoryPlot();
            plot.setBackgroundPaint(new Color(245, 245, 245));
            plot.setRangeGridlinePaint(new Color(200, 200, 200));
            plot.setOutlineVisible(false);

            // Axis styling
            plot.getDomainAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
            plot.getRangeAxis().setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
            plot.getDomainAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));
            plot.getRangeAxis().setLabelFont(new Font("SansSerif", Font.BOLD, 14));

            // Renderer styling
            LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
            renderer.setSeriesPaint(0, new Color(52, 152, 219));   // Withering
            renderer.setSeriesPaint(1, new Color(46, 204, 113));   // Rolling
            renderer.setSeriesPaint(2, new Color(231, 76, 60));    // Oxidation
            renderer.setSeriesPaint(3, new Color(241, 196, 15));   // Drying

            // Rounded data points and smooth lines
            Shape circle = new java.awt.geom.Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0);
            for (int i = 0; i < dataset.getRowCount(); i++) {
                renderer.setSeriesStroke(i, new BasicStroke(2.5f));
                renderer.setSeriesShape(i, circle);
                renderer.setSeriesShapesVisible(i, true);
            }

            // Create and configure ChartPanel
            ChartPanel chartPanel = new ChartPanel(lineChart);
            chartPanel.setPreferredSize(new Dimension(1200, 600));
            chartPanel.setMouseWheelEnabled(true);
            chartPanel.setBackground(Color.WHITE);
            chartPanel.setPopupMenu(null); // Hide old-style popup

            // Add to UI
            jPanel1.removeAll();
            jPanel1.add(chartPanel);
            jPanel1.validate();

            // Optional: export to high-res PNG
            try {
                File outputFile = new File("production_throughput_chart.png");
                ChartUtilities.saveChartAsPNG(outputFile, lineChart, 1920, 1080);
            } catch (IOException ex) {
                ex.printStackTrace();
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
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();

        jPanel4.setBackground(new java.awt.Color(51, 255, 51));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Workers Schedule");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(146, 146, 146)
                .addComponent(jLabel3)
                .addContainerGap(156, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(51, 255, 51));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Maintenance Schedule");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(105, 105, 105))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jScrollPane5.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 943, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane5)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    // End of variables declaration//GEN-END:variables
}
