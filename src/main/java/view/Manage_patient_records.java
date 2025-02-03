/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author j
 */
public class Manage_patient_records extends javax.swing.JFrame {
    private Connection conn;
    /**
     * Creates new form Manage_patient_records
     */
    public Manage_patient_records() {
        initComponents();
        connectToDatabase();
        populateTable();
    }
    private void connectToDatabase() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare_db", "root", "");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to connect to the database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populateTable() {
        try {
            String query = "SELECT * FROM patients";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                Object[] row = {
                        rs.getString("Patient_id"),
                        rs.getString("Patient_name"),
                        rs.getString("Patient_email"),
                        rs.getString("Patient_no"),
                        rs.getString("Patient_since"),
                        rs.getString("Patient_address")
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to fetch customer data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<String> getPatientIds() {
        List<String> patientIds = new ArrayList<>();
        try {
            String query = "SELECT Patient_id FROM patients";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                patientIds.add(rs.getString("Patient_id"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to fetch customer IDs: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return patientIds;
    }

    private void addPatient() {
        if (!validateFields()) {
            return;
        }
        try {
            String query = "INSERT INTO patients (Patient_id, Patient_name, Patient_email, Patient_no, Patient_since, Patient_address) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, Patient_id.getText());
            pst.setString(2, Patient_name.getText());
            pst.setString(3, Patient_email.getText());
            pst.setString(4, Patient_no.getText());
            pst.setString(5, Patient_since.getText());
            pst.setString(6, Patient_address.getText());
            pst.executeUpdate();
            populateTable();
            clearFields();
            sendNotification(Patient_id.getText(), "Your account has been created.");
            JOptionPane.showMessageDialog(this, "Patient added successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error adding patient: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editPatient() {
        if (!validateFields()) {
            return;
        }
        try {
            String query = "UPDATE patients SET Patient_name = ?, Patient_email = ?, Patient_no = ?, Patient_since = ?, Patient_address = ? WHERE Patient_id = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, Patient_name.getText());
            pst.setString(2, Patient_email.getText());
            pst.setString(3, Patient_no.getText());
            pst.setString(4, Patient_since.getText());
            pst.setString(5, Patient_address.getText());
            pst.setString(6, Patient_id.getText());
            pst.executeUpdate();
            populateTable();
            clearFields();
            sendNotification(Patient_id.getText(), "Your account details have been updated.");
            JOptionPane.showMessageDialog(this, "Patient updated successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating patient: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removePatient() {
        if (Patient_id.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Patient ID cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            String query = "DELETE FROM patients WHERE Patient_id = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, Patient_id.getText());
            pst.executeUpdate();
            populateTable();
            clearFields();
            sendNotification(Patient_id.getText(), "Your account has been deleted.");
            JOptionPane.showMessageDialog(this, "Patient removed successfully!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error removing patient: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private boolean validateFields() {
        if (Patient_id.getText().isEmpty() || Patient_name.getText().isEmpty() ||
                Patient_email.getText().isEmpty() || Patient_no.getText().isEmpty() ||
                Patient_since.getText().isEmpty() || Patient_address.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled out.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        return true;
    }

    private void clearFields() {
        Patient_id.setText("");
        Patient_name.setText("");
        Patient_email.setText("");
        Patient_no.setText("");
        Patient_since.setText("");
        Patient_address.setText("");
    }

    private void sendNotification(String patientId, String message) {
        // Implement your notification logic here
        // For example, sending an email or SMS
        // This is a placeholder implementation
        JOptionPane.showMessageDialog(this, "Sent Notification to patient via Email: " + patientId, "Notification", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        home = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Patient_id = new javax.swing.JTextField();
        Patient_name = new javax.swing.JTextField();
        Patient_email = new javax.swing.JTextField();
        Patient_no = new javax.swing.JTextField();
        Patient_address = new javax.swing.JTextField();
        Patient_since = new javax.swing.JTextField();
        Patient_add = new javax.swing.JButton();
        Patient_edit = new javax.swing.JButton();
        Patient_remove = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 204, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel1.setText("Manage Patients Records");

        home.setBackground(new java.awt.Color(255, 255, 51));
        home.setText("Home");
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });

        jTable3.setBackground(new java.awt.Color(204, 255, 255));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null}
                },
                new String [] {
                        "Patient ID", "Name", "Email", "Contact no", "Patient Since", "Address"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable3);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("ADD NEW PATIENT");

        jLabel3.setText("Patient ID ");

        jLabel4.setText("Name");

        jLabel5.setText("Email");

        jLabel6.setText("Contact No");

        jLabel7.setText("Address");

        jLabel8.setText("Patient Since");

        Patient_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Patient_idActionPerformed(evt);
            }
        });

        Patient_add.setText("Add");
        Patient_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Patient_addActionPerformed(evt);
            }
        });

        Patient_edit.setText("Edit");
        Patient_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Patient_editActionPerformed(evt);
            }
        });

        Patient_remove.setText("Remove");
        Patient_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Patient_removeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jLabel8)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Patient_since))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(Patient_add)
                                                .addGap(18, 18, 18)
                                                .addComponent(Patient_edit)
                                                .addGap(18, 18, 18)
                                                .addComponent(Patient_remove)
                                                .addGap(0, 139, Short.MAX_VALUE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel6)
                                                        .addComponent(jLabel7))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(jLabel2)
                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                        .addComponent(Patient_id)
                                                        .addComponent(Patient_name)
                                                        .addComponent(Patient_email)
                                                        .addComponent(Patient_no)
                                                        .addComponent(Patient_address))))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(Patient_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(Patient_name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(Patient_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(Patient_no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(Patient_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(Patient_since, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(64, 64, 64)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Patient_add)
                                        .addComponent(Patient_edit)
                                        .addComponent(Patient_remove))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel1)
                                                .addGap(331, 331, 331)
                                                .addComponent(home)
                                                .addGap(40, 40, 40))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 553, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(15, 15, 15)
                                                .addComponent(home))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(29, 29, 29)
                                                .addComponent(jLabel1)))
                                .addGap(36, 36, 36)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
                                .addContainerGap(144, Short.MAX_VALUE))
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

        // --- Start of Custom UI Code ---

        getContentPane().setBackground(new java.awt.Color(240, 240, 240));

        jPanel1.setBackground(new java.awt.Color(250, 249, 246));
        jPanel2.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        jPanel2.setBorder(javax.swing.BorderFactory.createEmptyBorder());


        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12));
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));


        home.setBackground(new java.awt.Color(51, 153, 255));
        home.setForeground(java.awt.Color.WHITE);
        home.setFocusPainted(false);
        home.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,153,255)),javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));


        Patient_add.setBackground(new java.awt.Color(51, 153, 255));
        Patient_add.setForeground(java.awt.Color.WHITE);
        Patient_add.setFocusPainted(false);
        Patient_add.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,153,255)),javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        Patient_edit.setBackground(new java.awt.Color(51, 153, 255));
        Patient_edit.setForeground(java.awt.Color.WHITE);
        Patient_edit.setFocusPainted(false);
        Patient_edit.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,153,255)),javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));


        Patient_remove.setBackground(new java.awt.Color(51, 153, 255));
        Patient_remove.setForeground(java.awt.Color.WHITE);
        Patient_remove.setFocusPainted(false);
        Patient_remove.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,153,255)),javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));


        javax.swing.border.CompoundBorder fieldBorder = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204,204,204)),javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Patient_id.setBorder(fieldBorder);
        Patient_name.setBorder(fieldBorder);
        Patient_email.setBorder(fieldBorder);
        Patient_no.setBorder(fieldBorder);
        Patient_address.setBorder(fieldBorder);
        Patient_since.setBorder(fieldBorder);

        jTable3.setGridColor(new java.awt.Color(220, 220, 220));
        jTable3.setBackground(java.awt.Color.WHITE);
        // --- End of Custom UI Code ---

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        // TODO add your handling code here:
        UI homeWindow = new UI();
        homeWindow.setVisible(true);

        // Close the current window
        this.dispose();
    }//GEN-LAST:event_homeActionPerformed

    private void Patient_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Customer_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Patient_idActionPerformed

    private void Patient_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Customer_addActionPerformed
        addPatient();
    }//GEN-LAST:event_Patient_addActionPerformed

    private void Patient_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Customer_removeActionPerformed
        removePatient();
    }//GEN-LAST:event_Patient_removeActionPerformed

    private void Patient_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Customer_editActionPerformed
        editPatient();
    }//GEN-LAST:event_Patient_editActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Manage_patient_records().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Patient_add;
    private javax.swing.JTextField Patient_address;
    private javax.swing.JButton Patient_edit;
    private javax.swing.JTextField Patient_email;
    private javax.swing.JTextField Patient_id;
    private javax.swing.JTextField Patient_name;
    private javax.swing.JTextField Patient_no;
    private javax.swing.JButton Patient_remove;
    private javax.swing.JTextField Patient_since;
    private javax.swing.JButton home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable3;
    // End of variables declaration//GEN-END:variables
}
