package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import util.DatabaseConnection;
import javax.swing.*;


public class BookAppointments extends javax.swing.JFrame {

    private Connection conn;

    public BookAppointments() {
        initComponents();
        connectToDatabase();
        populateJobTable();
        populateCustomerComboBox();
        populateEmployeeComboBox();
    }

    private void connectToDatabase() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/healthcare_db", "root", "");
            System.out.println("Database connection successful!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to connect to the database: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void populateJobTable() {
        DefaultTableModel model = (DefaultTableModel) JobTable.getModel();
        model.setRowCount(0); // Clear existing rows

        try {
            System.out.println("populateJobTable started");
            String sql = "SELECT Appointment_id, Patient_id, Doctor_id, Appointment_description, Appointment_status, Appointment_date FROM appointments";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            System.out.println("SQL Query executed: " + sql);

            while (rs.next()) {
                String appointmentId = rs.getString("Appointment_id");
                String patientId = rs.getString("Patient_id");
                String doctorId = rs.getString("Doctor_id");
                String appointmentDescription = rs.getString("Appointment_description");
                String appointmentStatus = rs.getString("Appointment_status");
                String appointmentDate = rs.getString("Appointment_date");

                model.addRow(new Object[]{appointmentId, patientId, doctorId, appointmentDescription, appointmentStatus, appointmentDate});
            }
            System.out.println("Data loaded to table");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error populating appointment data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void populateCustomerComboBox() {
        try {
            System.out.println("populateCustomerComboBox started");
            String sql = "SELECT Patient_id FROM patients";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            System.out.println("SQL Query executed: " + sql);

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            while (rs.next()) {
                model.addElement(rs.getString("Patient_id"));
            }
            Patient_id.setModel(model);
            System.out.println("Customer combobox loaded");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error populating patient data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    private void populateEmployeeComboBox() {
        try {
            System.out.println("populateEmployeeComboBox started");
            String sql = "SELECT Doctor_id FROM doctors";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            System.out.println("SQL Query executed: " + sql);

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            while (rs.next()) {
                model.addElement(rs.getString("Doctor_id"));
            }
            Doctor_id.setModel(model);
            System.out.println("Employee combobox loaded");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error populating doctor data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        BookPanel = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        Patient_id = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        Doctor_id = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        Appointment_description = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        Appointment_status = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        Appointment_date = new javax.swing.JTextField();
        Update_btn = new javax.swing.JButton();
        Book_btn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        JobTable = new javax.swing.JTable();
        home = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Book Appointment");

        BookPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setText("Patient ID");

        Patient_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Patient_idActionPerformed(evt);
            }
        });

        jLabel3.setText("Doctor ID");

        Doctor_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Doctor_idActionPerformed(evt);
            }
        });

        jLabel4.setText("Appointment Description");

        jLabel5.setText("Appointment Status");

        Appointment_status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "Confirmed", "Cancelled", "Completed" }));

        jLabel6.setText("Appointment Date");

        Update_btn.setText("Update");
        Update_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Update_btnActionPerformed(evt);
            }
        });

        Book_btn.setText("Book");
        Book_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Book_btnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AllocatePanelLayout = new javax.swing.GroupLayout(BookPanel);
        BookPanel.setLayout(AllocatePanelLayout);
        AllocatePanelLayout.setHorizontalGroup(
                AllocatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(AllocatePanelLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(AllocatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AllocatePanelLayout.createSequentialGroup()
                                                .addGroup(AllocatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(AllocatePanelLayout.createSequentialGroup()
                                                                .addComponent(jLabel2)
                                                                .addGap(33, 33, 33)
                                                                .addComponent(Patient_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(AllocatePanelLayout.createSequentialGroup()
                                                                .addGroup(AllocatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel4)
                                                                        .addComponent(jLabel5)
                                                                        .addComponent(jLabel6))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(AllocatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(Appointment_description)
                                                                        .addGroup(AllocatePanelLayout.createSequentialGroup()
                                                                                .addGroup(AllocatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                                        .addComponent(Appointment_status, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                        .addComponent(Appointment_date))
                                                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                                .addContainerGap())
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AllocatePanelLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel3)
                                                .addGap(33, 33, 33)
                                                .addComponent(Doctor_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(186, 186, 186))))
                        .addGroup(AllocatePanelLayout.createSequentialGroup()
                                .addGap(37, 37, 37)
                                .addComponent(Book_btn)
                                .addGap(40, 40, 40)
                                .addComponent(Update_btn)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        AllocatePanelLayout.setVerticalGroup(
                AllocatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(AllocatePanelLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(AllocatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(Patient_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(AllocatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(Doctor_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(AllocatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(Appointment_description, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(AllocatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(Appointment_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(AllocatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(Appointment_date, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(52, 52, 52)
                                .addGroup(AllocatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Update_btn)
                                        .addComponent(Book_btn))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JobTable.setModel(new javax.swing.table.DefaultTableModel(
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
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null},
                        {null, null, null, null, null, null}
                },
                new String [] {
                        "Appointment id", "Patient id", "Doctor id", "Appointment Description", "Appointment Status", "Appointment Date"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(JobTable);

        home.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        home.setText("Home");
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(BookPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                                .addContainerGap())
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(401, 401, 401)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(home)
                                .addGap(160, 160, 160))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addComponent(home))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(BookPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        BookPanel.setBackground(new java.awt.Color(250, 250, 250));
        jPanel1.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        BookPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24));
        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));


        home.setBackground(new java.awt.Color(51, 153, 255));
        home.setForeground(java.awt.Color.WHITE);
        home.setFocusPainted(false);
        home.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,153,255)),javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        Book_btn.setBackground(new java.awt.Color(51, 153, 255));
        Book_btn.setForeground(java.awt.Color.WHITE);
        Book_btn.setFocusPainted(false);
        Book_btn.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,153,255)),javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));


        Update_btn.setBackground(new java.awt.Color(51, 153, 255));
        Update_btn.setForeground(java.awt.Color.WHITE);
        Update_btn.setFocusPainted(false);
        Update_btn.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,153,255)),javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        javax.swing.border.CompoundBorder fieldBorder = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204,204,204)),javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Appointment_description.setBorder(fieldBorder);
        Appointment_date.setBorder(fieldBorder);

        JobTable.setGridColor(new java.awt.Color(220, 220, 220));
        JobTable.setBackground(java.awt.Color.WHITE);


        // --- End of Custom UI Code ---

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Doctor_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Employee_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Employee_idActionPerformed

    private void Patient_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Customer_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Customer_idActionPerformed

    private void sendNotification(String patientId, String message) {
        // Implement your notification logic here
        // For example, sending an email or SMS
        // This is a placeholder implementation
        JOptionPane.showMessageDialog(this, "Sent Notification to patient (id="+ patientId+ ") with message:" + message, "Notification", JOptionPane.INFORMATION_MESSAGE);

    }

    private void Book_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Allocate_btnActionPerformed
        try {
            String sql = "INSERT INTO appointments (Patient_id, Doctor_id, Appointment_description, Appointment_status, Appointment_date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            if(Patient_id.getSelectedItem() == null || Doctor_id.getSelectedItem() == null ||
                    Appointment_description.getText().isEmpty() || Appointment_date.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please, fill all the fields");
                return;
            }

            String patient_id =  (String) Patient_id.getSelectedItem();

            stmt.setString(1, patient_id );
            stmt.setString(2, (String) Doctor_id.getSelectedItem());
            stmt.setString(3, Appointment_description.getText());
            stmt.setString(4, (String) Appointment_status.getSelectedItem());
            stmt.setString(5, Appointment_date.getText());


            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Appointment booked successfully.");
                sendNotification(patient_id,"Your appointment was successfully created!" );

                SwingUtilities.invokeLater(this::populateJobTable); // Refresh the table after insertion

            } else {
                JOptionPane.showMessageDialog(this, "Failed to book appointment.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error booking appointment: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_Book_btnActionPerformed

    private void Update_btnActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            int selectedRow = JobTable.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(this, "Please select an appointment to update.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String appointmentId = (String) JobTable.getValueAt(selectedRow, 0);


            String sql = "UPDATE appointments SET Patient_id = ?, Doctor_id = ?, Appointment_description = ?, Appointment_status = ?, Appointment_date = ? WHERE Appointment_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);

            if(Patient_id.getSelectedItem() == null || Doctor_id.getSelectedItem() == null ||
                    Appointment_description.getText().isEmpty() || Appointment_date.getText().isEmpty()){
                JOptionPane.showMessageDialog(this, "Please, fill all the fields");
                return;
            }
            String patient_id = (String) Patient_id.getSelectedItem();

            stmt.setString(1,patient_id);
            stmt.setString(2, (String) Doctor_id.getSelectedItem());
            stmt.setString(3, Appointment_description.getText());
            stmt.setString(4, (String) Appointment_status.getSelectedItem());
            stmt.setString(5, Appointment_date.getText());
            stmt.setString(6,appointmentId);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Appointment updated successfully.");
                sendNotification(patient_id, "Your appointment was successfully updated!");

                SwingUtilities.invokeLater(this::populateJobTable);
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update appointment or appointment not found", "Update Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error updating appointment data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_Update_btnActionPerformed

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        UI homeWindow = new UI();
        homeWindow.setVisible(true);

        // Close the current Manage_suppliers window
        this.dispose();
    }//GEN-LAST:event_homeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new BookAppointments().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BookPanel;
    private javax.swing.JButton Book_btn;
    private javax.swing.JComboBox<String> Patient_id;
    private javax.swing.JComboBox<String> Doctor_id;
    private javax.swing.JTable JobTable;
    private javax.swing.JTextField Appointment_date;
    private javax.swing.JTextField Appointment_description;
    private javax.swing.JComboBox<String> Appointment_status;
    private javax.swing.JButton Update_btn;
    private javax.swing.JButton home;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}