package gui;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.IntelliJTheme;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.MySQL;

public class SignInWindow extends javax.swing.JFrame {

    public SignInWindow() {
        initComponents();
        loadRoundComp();
        setData();
    }

    private void loadRoundComp() {

        jTextField1.putClientProperty("JComponent.roundRect", true);
        jPasswordField1.putClientProperty("JComponent.roundRect", true);

        jTextField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Username");
        jTextField1.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);

        jPasswordField1.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
        jPasswordField1.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Password");
        jButton1.putClientProperty("JButton.buttonType", "roundRect");
        jButton2.putClientProperty("JButton.buttonType", "roundRect");

    }

    private void setData() {

        int id = SelectedDepartmentForSignin.getId();
        String name = SelectedDepartmentForSignin.getName();

        jLabel1.setText(name);

        InputStream icon = null;

        if (id == 1) {

            icon = this.getClass().getResourceAsStream("/resources/admin1.png");

        } else if (id == 2) {

            icon = this.getClass().getResourceAsStream("/resources/hr1 (2).png");

        } else if (id == 3) {

            icon = this.getClass().getResourceAsStream("/resources/inventory1.png");

        } else if (id == 4) {

            icon = this.getClass().getResourceAsStream("/resources/sales1.png");

        } else if (id == 5) {

            icon = this.getClass().getResourceAsStream("/resources/production1.png");

        } else if (id == 6) {

            icon = this.getClass().getResourceAsStream("/resources/testing1.png");

        } else if (id == 7) {

            icon = this.getClass().getResourceAsStream("/resources/research1.png");

        } else if (id == 8) {

            icon = this.getClass().getResourceAsStream("/resources/finance1.png");

        }

        if (icon != null) {

            try {
                BufferedImage bufferedImage = ImageIO.read(icon);
                jLabel2.setIcon(new ImageIcon(bufferedImage));

            } catch (IOException ex) {
                Logger.getLogger(SignInWindow.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("User Sign in");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("MV Boli", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Department name");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/sales1.png"))); // NOI18N

        jTextField1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jPasswordField1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setText("Sign in");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(102, 102, 102));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1)
                    .addComponent(jPasswordField1)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
        FirstWindow window = new FirstWindow();
        window.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         String username = jTextField1.getText();
        String password = String.valueOf(jPasswordField1.getPassword());
        SelectedDepartmentForSignin selectedDepartment = new SelectedDepartmentForSignin();

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your username", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your password", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {

                ResultSet usersRs = MySQL.executeSearch("SELECT * FROM `users` INNER JOIN `employees` ON `employees`.`nic`=`users`.`employees_nic` INNER JOIN `departments` "
                        + "ON `departments`.`id`=`employees`.`departments_id` WHERE `users`.`username`='" + username + "' AND `users`.`password`='" + password + "'");

                if (usersRs.next()) {
                    
                    System.out.println(SelectedDepartmentForSignin.getName());

                    String department = usersRs.getString("departments.name");

                   

                    if (SelectedDepartmentForSignin.getName().equals(department)) {

                        LoggedUser.setFname(usersRs.getString("employees.fname"));
                        LoggedUser.setLname(usersRs.getString("employees.lname"));
                        LoggedUser.setDepartment(usersRs.getString("departments.name"));
                        LoggedUser.setNic(usersRs.getString("employees.nic"));

                        home home = new home();
                        this.dispose();
                        home.setVisible(true);

                    } else {

                        JOptionPane.showMessageDialog(this, "Please sign into your department", "Warning", JOptionPane.WARNING_MESSAGE);

                    }

                } else {

                    JOptionPane.showMessageDialog(this, "Incorrect username or password. Please try again", "Warning", JOptionPane.WARNING_MESSAGE);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    public static void main(String args[]) {
        IntelliJTheme.setup(home.class.getResourceAsStream("/themes/GitHub.theme.json"));
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignInWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
