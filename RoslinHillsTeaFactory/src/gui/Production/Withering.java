/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.Production;

import gui.Production.*;
import gui.OngoingProcessTracker;
import gui.home;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.SwingUtilities;
import model.MySQL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Kavindu Anupama
 */
public class Withering extends javax.swing.JPanel {

    public static String process;
    int sliderValue1, sliderValue2;
    Boolean[] isMoved1 = {false}, isMoved2 = {false};

    private Timer[] timers = new Timer[3]; // Array to hold timers
    private long[] elapsedTimesInMillis = new long[3]; // Array to hold elapsed times in millis
    private boolean[] isPaused = new boolean[3]; // Array to hold pause states
    private long[] startTimes = new long[3];
    private long[] endTimes = new long[3];
    private boolean[] isScheduled = new boolean[3]; // Array to hold scheduled states
    private JLabel[] labels = new JLabel[3];
    private String[] assignedBatches = new String[3]; // Array to hold assigned batch IDs
    private String[] assignedMachines = new String[3]; // Array to hold assigned machine IDs
    private LocalTime[] startTimesInTimeFormat = new LocalTime[3]; // Array to hold start times in LocalTime format
    private boolean[] progressBarCompleted = {false, false, false};

    public Withering() {
        initComponents();

        assignedBatches = new String[]{"Not Assigned", "Not Assigned", "Not Assigned"};
        assignedMachines = new String[]{"Not Assigned", "Not Assigned", "Not Assigned"};

        initializeProgressBars();
        loadData();
        labels[0] = jLabel3;
        labels[1] = jLabel5;
        labels[2] = jLabel7;
        jProgressBar1.setValue(0);
        jProgressBar2.setValue(0);
        jProgressBar3.setValue(0);

        SwingUtilities.invokeLater(() -> {
            var ancestor = SwingUtilities.getWindowAncestor(this);
            if (ancestor != null) {
                ancestor.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        handleWindowClosing();
                    }
                });
            }
        });
    }

    private void initializeProgressBars() {
        jProgressBar1.setStringPainted(true);
        jProgressBar2.setStringPainted(true);
        jProgressBar3.setStringPainted(true);
    }

    public JTextField getTeaBatch() {
        return jTextField1;
    }

    public JTextField getMachines() {
        return jTextField2;
    }

    public JTextField getStartTime() {
        return jTextField3;
    }

    public JTextField getEndTime() {
        return jTextField4;
    }

    public JButton startTimeButton() {
        return jButton5;
    }

    public JButton endTimeButton() {
        return jButton6;
    }

    private long parseTime(String time) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime parsedTime = LocalTime.parse(time, formatter);
        return parsedTime.toSecondOfDay();
    }

    public void startBatchProgress() {
        try {
            String batch = jTextField1.getText();
            String machine = jTextField2.getText();
            long startTime = parseTime(jTextField3.getText());
            long endTime = parseTime(jTextField4.getText());

            LocalTime startTime2 = LocalTime.parse(jTextField3.getText(), DateTimeFormatter.ofPattern("HH:mm"));

            int progressBarNumber = -1; // Find an available progress bar
            if (jProgressBar1.getValue() == 0 && !isScheduled[0]) {
                progressBarNumber = 1;
            } else if (jProgressBar2.getValue() == 0 && !isScheduled[1]) {
                progressBarNumber = 2;
            } else if (jProgressBar3.getValue() == 0 && !isScheduled[2]) {
                progressBarNumber = 3;
            } else {
                JOptionPane.showMessageDialog(this, "Maximum number of batches are being processed", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (progressBarNumber != -1) {
                labels[progressBarNumber - 1].setText(batch); // Set the label
                assignedBatches[progressBarNumber - 1] = batch; // Assign batch ID
                assignedMachines[progressBarNumber - 1] = machine; // Assign batch ID
                startTimes[progressBarNumber - 1] = startTime; // Set startTime
                endTimes[progressBarNumber - 1] = endTime; // Set endTime
                isScheduled[progressBarNumber - 1] = true; // Mark as scheduled
                startProgressBar(progressBarNumber, startTime, endTime, 0);
                startTimesInTimeFormat[progressBarNumber - 1] = startTime2; // Set startTime in LocalTime format
                // Fix: Update the OngoingProcessTracker to reflect that there is an ongoing process
                OngoingProcessTracker.setOngoingProcess(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid time format. Please use HH:mm", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public LocalTime getStartTime(int progressBarNumber) {
        if (progressBarNumber < 1 || progressBarNumber > 3) {
            throw new IllegalArgumentException("Invalid progress bar number.");
        }
        return startTimesInTimeFormat[progressBarNumber - 1];
    }

    private void startProgressBar(int progressBarNumber, long startTime, long endTime, long elapsedTime) {
        if (startTime > endTime) {
            endTime += 24 * 3600;
        }
        long totalTimeInSeconds = endTime - startTime;
        long totalTimeInMillis = totalTimeInSeconds * 1000;

        if (timers[progressBarNumber - 1] == null) { // Create timer only if it doesn't exist
            elapsedTimesInMillis[progressBarNumber - 1] = elapsedTime * 1000; // Initialize elapsed time

            timers[progressBarNumber - 1] = new Timer(100, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!isPaused[progressBarNumber - 1]) {
                        long currentTimeInSeconds = LocalTime.now().toSecondOfDay();
                        if (currentTimeInSeconds >= startTime) {
                            elapsedTimesInMillis[progressBarNumber - 1] += 100;
                            if (elapsedTimesInMillis[progressBarNumber - 1] <= totalTimeInMillis) {
                                int progress = (int) ((elapsedTimesInMillis[progressBarNumber - 1] * 100) / totalTimeInMillis);
                                getProgressBar(progressBarNumber).setValue(progress);
                                getProgressBar(progressBarNumber).setString(progress + "%");
                            } else {
                                ((Timer) e.getSource()).stop();
                                getProgressBar(progressBarNumber).setValue(100);
                                getProgressBar(progressBarNumber).setString("Completed, Click to Finalize - 100%");
                                isScheduled[progressBarNumber - 1] = false; // Mark as not scheduled
                            }
                        } else {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                            LocalTime startTimeLocal = LocalTime.ofSecondOfDay(startTime);
                            getProgressBar(progressBarNumber).setString("Scheduled - " + startTimeLocal.format(formatter));
                        }
                    }
                }
            });
            timers[progressBarNumber - 1].start();
        }
    }

    private JProgressBar getProgressBar(int number) {
        return switch (number) {
            case 1 ->
                jProgressBar1;
            case 2 ->
                jProgressBar2;
            case 3 ->
                jProgressBar3;
            default ->
                throw new IllegalArgumentException("Invalid progress bar number.");
        };
    }

    private void handleWindowClosing() {
        checkAllProgressBarsCompleted(); // Ensure the latest state is checked

        if (!OngoingProcessTracker.hasOngoingProcess()) {
            System.exit(0); // Allow closing only when no process is ongoing
        } else {
            JOptionPane.showMessageDialog(this, "There are ongoing processes", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void loadData() {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `withering` ORDER BY `withering`.`id` DESC");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int i = 0; i < jTable1.getColumnCount(); i++) {
                jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            while (rs.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(rs.getString("tea_batch_id"));
                vector.add(rs.getString("start_time"));
                vector.add(rs.getString("end_time"));
                vector.add(rs.getString("processed_date"));
                vector.add(rs.getString("humidity_level"));
                vector.add(rs.getString("temperature_level"));
                model.addRow(vector);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //method to update only the relevant row
    private void updateRow(String batchId) {
        try {
            ResultSet rs = MySQL.executeSearch("SELECT * FROM `withering` WHERE `tea_batch_id` = '" + batchId + "'");
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

            while (rs.next()) {
                // Loop through the table rows and find the row with the matching batch ID.
                for (int row = 0; row < model.getRowCount(); row++) {
                    if (model.getValueAt(row, 0).equals(batchId)) {
                        model.setValueAt(rs.getString("start_time"), row, 1);
                        model.setValueAt(rs.getString("end_time"), row, 2);
                        model.setValueAt(rs.getString("processed_date"), row, 3);
                        model.setValueAt(rs.getString("temperature_level"), row, 4);
                        model.setValueAt(rs.getString("humidity_level"), row, 5);
                        break;
                    }
                }
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jSlider1 = new javax.swing.JSlider();
        jLabel8 = new javax.swing.JLabel();
        jSlider2 = new javax.swing.JSlider();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar2 = new javax.swing.JProgressBar();
        jProgressBar3 = new javax.swing.JProgressBar();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Processing Batches");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("1.");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Batch ID", "Start Time", "End Time", "Processed Date", "Humidity", "Temperature"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setGridColor(new java.awt.Color(234, 234, 234));
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(5).setResizable(false);
        }

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Not Assigned");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel4.setText("2.");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Not Assigned");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel6.setText("3.");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel7.setText("Not Assigned");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton1.setText("Select Batch");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton2.setText("Select Machine");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jButton3.setText("Add To Withering Machine");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jSlider1.setMajorTickSpacing(10);
        jSlider1.setMinorTickSpacing(2);
        jSlider1.setOrientation(javax.swing.JSlider.VERTICAL);
        jSlider1.setPaintLabels(true);
        jSlider1.setPaintTicks(true);
        jSlider1.setToolTipText("");
        jSlider1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider1StateChanged(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel8.setText("Temperature");

        jSlider2.setMajorTickSpacing(20);
        jSlider2.setMinorTickSpacing(5);
        jSlider2.setOrientation(javax.swing.JSlider.VERTICAL);
        jSlider2.setPaintLabels(true);
        jSlider2.setPaintTicks(true);
        jSlider2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSlider2StateChanged(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel9.setText("Humidity");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel10.setText("00 ");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel11.setText("00 ");

        jProgressBar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jProgressBar1MouseClicked(evt);
            }
        });

        jProgressBar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jProgressBar2MouseClicked(evt);
            }
        });

        jProgressBar3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jProgressBar3MouseClicked(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton5.setText("Select Start Time");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton6.setText("Select End Time");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel12.setText("°c");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel13.setText("g/kg");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setText("Withering");

        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton4.setText("Generate Report");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel15.setText("to");

        jLabel16.setText("From");

        jButton7.setText("Filter");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton14.setText("Veiw all");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5))
                                    .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jProgressBar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7))))
                            .addComponent(jLabel1)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(jLabel16)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel15)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(9, 9, 9)
                                    .addComponent(jButton7)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton14))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 152, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel14)
                        .addGap(175, 175, 175)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addGap(163, 163, 163)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSlider2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(108, 108, 108))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(184, 184, 184)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addGap(122, 122, 122))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSlider2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(7, 7, 7)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jProgressBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jProgressBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton7)
                                .addComponent(jButton14))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(7, 7, 7)
                                .addComponent(jLabel15)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(1, 1, 1)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        home home = new home();
        Machines mac = new Machines(home, true, this, "withering");
        mac.setVisible(true);
        mac.setMachines(mac);


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        home home = new home();
        TeaBatches tbat = new TeaBatches(home, true, this, "withering");
        tbat.setVisible(true);
        tbat.setBatches(tbat);

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jSlider1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider1StateChanged

        sliderValue1 = jSlider1.getValue();
        isMoved1[0] = true;
        jLabel10.setText(sliderValue1 + "");

    }//GEN-LAST:event_jSlider1StateChanged

    private void jSlider2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSlider2StateChanged

        sliderValue2 = jSlider2.getValue();
        isMoved2[0] = true;
        jLabel11.setText(sliderValue2 + "");

    }//GEN-LAST:event_jSlider2StateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        home home = new home();
        Time ti = new Time(home, true, this, "start", "withering");
        ti.setVisible(true);
        ti.setTime(ti);

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed

        home home = new home();
        Time ti2 = new Time(home, true, this, "end", "withering");
        ti2.setVisible(true);
        ti2.setTime(ti2);

    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        String batch = jTextField1.getText();
        String machine = jTextField2.getText();
        int temperature = jSlider1.getValue();
        int humidity = jSlider2.getValue();
        String startTime = jTextField3.getText();
        String endTime = jTextField4.getText();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        long currentTimeInSeconds = LocalTime.now().toSecondOfDay();

        try {

            if (batch.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Select Batch", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (machine.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Select Machine", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!isMoved1[0]) {
                JOptionPane.showMessageDialog(this, "You Haven't Set Temperature", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!isMoved2[0]) {
                JOptionPane.showMessageDialog(this, "You Haven't Set Humidity", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (startTime.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Set Start Time", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (endTime.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Set End Time", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {

                long sTime = parseTime(jTextField3.getText());

                for (String assignedBatch : assignedBatches) {
                    if (batch.equals(assignedBatch)) {
                        JOptionPane.showMessageDialog(this, "Batch already assigned to a progress bar", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }
                for (String assignedMachine : assignedMachines) {
                    if (machine.equals(assignedMachine)) {
                        JOptionPane.showMessageDialog(this, "This Machine has Already been Assigned a Batch", "Warning", JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                if (sTime <= currentTimeInSeconds) {
                    JOptionPane.showMessageDialog(this, "Start time should be in the future", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                MySQL.executeIUD("INSERT INTO `withering` (`tea_batch_id`, `start_time`, `end_time`, `temperature_level`, `humidity_level`,`processed_date`, `production_status_id`)"
                        + "VALUES ('" + batch + "','" + startTime + "','" + endTime + "','" + temperature + "','" + humidity + "','" + date + "','2')");

                MySQL.executeIUD("UPDATE `tea_batch` SET `production_status_id` = '3' WHERE `id` = '" + batch + "'");

                startBatchProgress();
                JOptionPane.showMessageDialog(this, "Added to Withering Machine Successfully", "Warning", JOptionPane.OK_OPTION);
                reset();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        try {
            InputStream path = this.getClass().getResourceAsStream("/reports/Production/withering.jasper");
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


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        try {

            if (jDateChooser1.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Select a Start Date", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (jDateChooser2.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Select a End Date", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String stdate = new SimpleDateFormat("yyyy-MM-dd").format(jDateChooser1.getDate());
            String eddate = new SimpleDateFormat("yyyy-MM-dd").format(jDateChooser2.getDate());

            ResultSet rs = MySQL.executeSearch("SELECT * FROM `withering` WHERE `processed_date` BETWEEN '" + stdate + "' AND '" + eddate + "' ORDER BY `id` DESC");

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int i = 0; i < jTable1.getColumnCount(); i++) {
                jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            while (rs.next()) {

                Vector<String> vector = new Vector<>();
                vector.add(rs.getString("tea_batch_id"));
                vector.add(rs.getString("start_time"));
                vector.add(rs.getString("end_time"));
                vector.add(rs.getString("processed_date"));
                vector.add(rs.getString("temperature_level"));
                vector.add(rs.getString("humidity_level"));
                model.addRow(vector);
            }

            jDateChooser1.setDate(null);
            jDateChooser2.setDate(null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed

    }//GEN-LAST:event_jButton13ActionPerformed

    private void checkAllProgressBarsCompleted() {
        boolean anyOngoing = false;

        // Only consider slots that are actually assigned a batch
        for (int i = 0; i < assignedBatches.length; i++) {
            if (!"Not Assigned".equals(assignedBatches[i]) && !progressBarCompleted[i]) {
                anyOngoing = true;
                break;
            }
        }
        OngoingProcessTracker.setOngoingProcess(anyOngoing);
    }


    private void jProgressBar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jProgressBar1MouseClicked

        if (jProgressBar1.getValue() == 0) {
            JOptionPane.showMessageDialog(this, "A batch is still not Assigned", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jProgressBar1.getValue() == 100) {
            String batchId = assignedBatches[0];
            try {
                MySQL.executeIUD("UPDATE `tea_batch` SET `production_status_id` = '6' WHERE `tea_batch`.`id` = '" + batchId + "'");
            } catch (Exception e) {
                e.printStackTrace();
            }
            updateRow(batchId);
            resetProgressBarState(0);
            progressBarCompleted[0] = true;
            checkAllProgressBarsCompleted();
        } else {
            JOptionPane.showMessageDialog(this, "Batch is still in progress.", "Warning", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jProgressBar1MouseClicked

    private void jProgressBar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jProgressBar2MouseClicked

        if (jProgressBar2.getValue() == 0) {
            JOptionPane.showMessageDialog(this, "A batch is still not Assigned", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jProgressBar2.getValue() == 100) {

            String batchId = assignedBatches[1];
            try {
                MySQL.executeIUD("UPDATE `tea_batch` SET `production_status_id` = '6' WHERE `tea_batch`.`id` = '" + jLabel5.getText() + "'");
            } catch (Exception e) {
                e.printStackTrace();
            }
            updateRow(batchId);
            resetProgressBarState(1);
            progressBarCompleted[1] = true;
            checkAllProgressBarsCompleted();
        } else {
            JOptionPane.showMessageDialog(this, "Batch is still in progress.", "Warning", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jProgressBar2MouseClicked

    private void jProgressBar3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jProgressBar3MouseClicked

        if (jProgressBar3.getValue() == 0) {
            JOptionPane.showMessageDialog(this, "A batch is still not Assigned", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jProgressBar3.getValue() == 100) {

            String batchId = assignedBatches[2];
            try {
                MySQL.executeIUD("UPDATE `tea_batch` SET `production_status_id` = '6' WHERE `tea_batch`.`id` = '" + jLabel7.getText() + "'");
            } catch (Exception e) {
                e.printStackTrace();
            }
            updateRow(batchId);
            resetProgressBarState(2);
            progressBarCompleted[2] = true;
            checkAllProgressBarsCompleted();
        } else {
            JOptionPane.showMessageDialog(this, "Batch is still in progress.", "Warning", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_jProgressBar3MouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        loadData();
    }//GEN-LAST:event_jButton14ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JProgressBar jProgressBar2;
    private javax.swing.JProgressBar jProgressBar3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

    private void reset() {

        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jSlider1.setValue(50);
        jSlider2.setValue(50);
        jLabel10.setText("00");
        jLabel11.setText("00");

    }

    /**
     * Resets the state of a single progress bar (and its related arrays) at the
     * given index (0-based).
     */
    private void resetProgressBarState(int index) {
        // Reset the arrays for this progress bar slot:
        assignedBatches[index] = "Not Assigned";
        assignedMachines[index] = "Not Assigned";
        progressBarCompleted[index] = false;
        isScheduled[index] = false;
        elapsedTimesInMillis[index] = 0;
        startTimes[index] = 0;
        endTimes[index] = 0;
        startTimesInTimeFormat[index] = null;

        // If a timer exists, stop it and remove the reference.
        if (timers[index] != null) {
            timers[index].stop();
            timers[index] = null;
        }

        // Reset the UI elements.
        JProgressBar pb = getProgressBar(index + 1);
        pb.setValue(0);
        pb.setString("0%");

        // Reset the corresponding label.
        switch (index) {
            case 0:
                jLabel3.setText("Not Assigned");
                break;
            case 1:
                jLabel5.setText("Not Assigned");
                break;
            case 2:
                jLabel7.setText("Not Assigned");
                break;
        }
    }

}
