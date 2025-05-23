/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui.Production;

import java.io.InputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import model.MySQL;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Kavindu Anupama
 */
public class ManageProductStock extends javax.swing.JPanel {

    private static HashMap<String, String> productcatMap = new HashMap();

    /**
     * Creates new form ManageProductStock
     */
    public ManageProductStock() {
        initComponents();
        loadQualityBatches();
        loadPackagingStock();
        loadProductCategory();
        productStock();
    }

    private String generateProductID() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String timestamp = dateFormat.format(new Date());
        int random = new Random().nextInt(9000) + 1000; // Random 4-digit number (1000-9999)
        return "PROD-" + timestamp + "-" + random;
    }

    private void loadProductCategory() {

        try {

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            ResultSet resultSet = MySQL.executeSearch("SELECT * FROM `products`");

            while (resultSet.next()) {
                vector.add(resultSet.getString("title"));
                productcatMap.put(resultSet.getString("title"), resultSet.getString("id"));
            }

            jComboBox1.setModel(new DefaultComboBoxModel<>(vector));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadQualityBatches() {

        try {

            ResultSet rs = MySQL.executeSearch("SELECT * FROM `quality_control` "
                    + "INNER JOIN `tea_batch` ON `quality_control`.`tea_batch_id` = `tea_batch`.`id` "
                    + "INNER JOIN `grades` ON `tea_batch`.`grades_id` = `grades`.`id`");

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
                vector.add(rs.getString("tea_batch.qty_end"));
                vector.add(rs.getString("grades.name"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void productStock() {

        try {

            ResultSet rs = MySQL.executeSearch("SELECT * FROM `product_stock` "
                    + "INNER JOIN `products` ON `products`.`id` = `product_stock`.`products_id` "
                    + "INNER JOIN `product_category` ON `product_category`.`id` = `products`.`product_category_id`");

            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int i = 0; i < jTable3.getColumnCount(); i++) {
                jTable3.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            while (rs.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(rs.getString("id"));
                vector.add(rs.getString("products.title"));
                vector.add(rs.getString("product_category.name"));
                vector.add(rs.getString("price"));
                vector.add(rs.getString("qty"));
                vector.add(rs.getString("mfd"));
                vector.add(rs.getString("exp"));
                model.addRow(vector);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void loadPackagingStock() {

        try {

            ResultSet rs = MySQL.executeSearch("SELECT * FROM `raw_materials_stock` "
                    + "INNER JOIN `raw_materials` ON `raw_materials_stock`.`raw_materials_material_id`=`raw_materials`.`material_id` "
                    + "INNER JOIN `material_type` ON `raw_materials`.`material_type_id`=`material_type`.`id` "
                    + "WHERE `grades_id` IS NULL ORDER BY `raw_materials`.`material_id` ASC");

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);

            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

            for (int i = 0; i < jTable2.getColumnCount(); i++) {
                jTable2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            while (rs.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(rs.getString("raw_materials_stock.id"));
                vector.add(rs.getString("material_type.name"));
                vector.add(rs.getString("raw_materials_stock.qty"));
                model.addRow(vector);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Batch ID", "Quantity", "Grade"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setPreferredSize(new java.awt.Dimension(400, 80));
        jScrollPane1.setViewportView(jTable1);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel2.setText("Select Processed Batch");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Material ID", "Material Name", "Quantity"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setPreferredSize(new java.awt.Dimension(400, 80));
        jScrollPane2.setViewportView(jTable2);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel3.setText("Select Packaging Material");

        jLabel1.setText("Select Product Type");

        jLabel4.setText("Manufacture Date");

        jLabel5.setText("Expiration Date");

        jLabel6.setText("Added Date");

        jLabel7.setText("Enter Price Per Unit");

        jLabel8.setText("Enter Quantity");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Product ID", "Product Name", "Product Category", "Price", "Quantity", "Manufacture Date", "Expiration Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Add Product");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setText("Get Report");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton3.setText("Reset All");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(44, 44, 44)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1)
                                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField1)
                                    .addComponent(jLabel7)
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel6)
                                    .addComponent(jTextField2)
                                    .addComponent(jLabel8)
                                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(111, 111, 111))))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1242, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addComponent(jButton2))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(29, 29, 29)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(33, 33, 33)
                                        .addComponent(jButton1)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String price_per_unit = jTextField1.getText();
        String stk_qty = jTextField2.getText();
        String products = String.valueOf(jComboBox1.getSelectedItem());

        if (jTable1.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please Select a Row From Processed Batch Table", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (jTable2.getSelectedRow() == -1) {
            JOptionPane.showMessageDialog(this, "Please Select a Row From Packaging Material Table", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (products.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select Product Type", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (price_per_unit.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Price Per Unit", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (stk_qty.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter Stock Quantity", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            if (jDateChooser1.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Please select Manufacture Date", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (jDateChooser2.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Please select Expiration Date", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (jDateChooser3.getDate() == null) {
                JOptionPane.showMessageDialog(this, "Please Select Adding Date", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int productQty;
            productQty = Integer.parseInt(stk_qty);
            String materialName = (String) jTable2.getValueAt(jTable2.getSelectedRow(), 1);
            Matcher matcher = Pattern.compile("\\d+").matcher(materialName);
            if (!matcher.find()) {
                JOptionPane.showMessageDialog(this, "Material name must include grams (e.g., 'Bottle 250g').", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int gramsPerUnit = Integer.parseInt(matcher.group());

            // Get tea batch quantity (kg) - GUARANTEED non-null per your note
            String batchId = (String) jTable1.getValueAt(jTable1.getSelectedRow(), 0);
            double batchQtyKg = Double.parseDouble((String) jTable1.getValueAt(jTable1.getSelectedRow(), 1));

            // Calculate required tea in kg
            double requiredTeaKg = (productQty * gramsPerUnit) / 1000.0;
            if (batchQtyKg < requiredTeaKg) {
                JOptionPane.showMessageDialog(this,
                        String.format("Not enough tea! Available: %.2f kg, Needed: %.2f kg", batchQtyKg, requiredTeaKg),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            // Get packaging material stock (handle commas in qty)
            String materialId = (String) jTable2.getValueAt(jTable2.getSelectedRow(), 0);
            String materialQtyStr = (String) jTable2.getValueAt(jTable2.getSelectedRow(), 2);
            double materialQty;
            materialQty = Double.parseDouble(materialQtyStr);

            if (materialQty < productQty) {
                JOptionPane.showMessageDialog(this,
                        String.format("Not enough packaging! Available: %d units, Needed: %d units", materialQty, productQty),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            try {

                // Deduct tea batch
                double newBatchQty = batchQtyKg - requiredTeaKg;
                MySQL.executeIUD("UPDATE tea_batch SET qty_end = " + newBatchQty + " WHERE id = '" + batchId + "'");

                // Deduct packaging material
                double newMaterialQty = materialQty - productQty;
                MySQL.executeIUD("UPDATE raw_materials_stock SET qty = " + newMaterialQty + " WHERE id = '" + materialId + "'");

                String added_date = new SimpleDateFormat("yyyy-MM-dd").format(jDateChooser3.getDate());
                String mfd = new SimpleDateFormat("yyyy-MM-dd").format(jDateChooser1.getDate());
                String exp = new SimpleDateFormat("yyyy-MM-dd").format(jDateChooser2.getDate());
                String productId = generateProductID();

                MySQL.executeIUD("INSERT INTO `product_stock`(`id`,`price`,`qty`,`mfd`,`exp`,`added_date`,`products_id`) "
                        + "VALUES('" + productId + "','" + price_per_unit + "','" + stk_qty + "','" + mfd + "','" + exp + "','" + added_date + "','" + productcatMap.get(products) + "')");

                // Refresh GUI tables
                loadQualityBatches();
                loadPackagingStock();
                productStock();
                reset();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        try {
            InputStream path = this.getClass().getResourceAsStream("/reports/Production/ProductStockReport.jasper");
            String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String time = new SimpleDateFormat("HH:mm:ss").format(new Date());

            HashMap<String, Object> params = new HashMap<>();
            params.put("Parameter1", date);
            params.put("Parameter2", time);

            JRTableModelDataSource dataSource = new JRTableModelDataSource(jTable3.getModel());

            JasperPrint jasperPrint = JasperFillManager.fillReport(path, params, dataSource);

            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        reset();
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

private void reset() {

        jTextField1.setText("");
        jTextField2.setText("");
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jDateChooser3.setDate(null);
        jTable1.clearSelection();
        jTable2.clearSelection();
        jTable3.clearSelection();
        jComboBox1.setSelectedIndex(0);

    }
}
