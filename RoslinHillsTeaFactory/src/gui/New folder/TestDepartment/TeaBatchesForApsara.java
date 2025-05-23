package gui.TestDepartment;

import gui.Production.*;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.MySQL;

/**
 *
 * @author Kavindu Anupama
 */
public class TeaBatchesForApsara extends javax.swing.JDialog {

    private String process, query;
    private TeaBatchesForApsara batch;
    private static BatchQuality TeaBatch;
    private static Rolling TeaBatch2;
    private static Oxidation TeaBatch3;
    private static Drying TeaBatch4;

    public void setBatches(TeaBatchesForApsara batch) {
        this.batch = batch;
    }

    public TeaBatchesForApsara(java.awt.Frame parent, boolean modal, JPanel jpanel) {
        super(parent, modal);
        initComponents();
        this.TeaBatch = (BatchQuality) jpanel;

        loadBatches();
    }

    private void loadBatches() {

        try {

            query = "SELECT * FROM `tea_batch` "
                    + "INNER JOIN `grades` ON `tea_batch`.`grades_id` = `grades`.`id` "
                    + "INNER JOIN `production_status` ON `tea_batch`.`production_status_id` = `production_status`.`id` ";

            ResultSet rs = MySQL.executeSearch(query);

            DefaultTableModel model = (DefaultTableModel) jTable5.getModel();
            model.setRowCount(0);

            // Center-align the cell content
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            // Apply the center renderer to all columns
            for (int i = 0; i < jTable5.getColumnCount(); i++) {
                jTable5.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            while (rs.next()) {
                Vector<String> vector = new Vector();
                vector.add(rs.getString("id"));
                vector.add(rs.getString("production_date"));
                vector.add(rs.getString("qty_start"));
                vector.add(rs.getString("grades.name"));
                vector.add(rs.getString("production_status.name"));

                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Batch ID", "Produced Date", "Quantity", "Grade", "Undergoing Process"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable5);
        if (jTable5.getColumnModel().getColumnCount() > 0) {
            jTable5.getColumnModel().getColumn(0).setResizable(false);
            jTable5.getColumnModel().getColumn(1).setResizable(false);
            jTable5.getColumnModel().getColumn(2).setResizable(false);
            jTable5.getColumnModel().getColumn(3).setResizable(false);
            jTable5.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 765, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 366, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked

        int row = jTable5.getSelectedRow();

        if (evt.getClickCount() == 2) {
            if (TeaBatch != null) {
                TeaBatch.getjTextField2().setText(String.valueOf(jTable5.getValueAt(row, 0)));
                this.dispose();
            }
        }

    }//GEN-LAST:event_jTable5MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TeaBatchesForApsara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TeaBatchesForApsara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TeaBatchesForApsara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TeaBatchesForApsara.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the dialog */
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable5;
    // End of variables declaration//GEN-END:variables
}
