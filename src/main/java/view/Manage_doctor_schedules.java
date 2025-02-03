/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import org.jfree.data.time.Day;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class Manage_doctor_schedules extends javax.swing.JFrame {
    // Database connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/healthcare_db"; // Change this to your database URL
    private static final String USER = "root"; // Your database username
    private static final String PASSWORD = ""; // Your database password

    // Connection object
    private Connection conn;

    /**
     * Creates new form Manage_suppliers
     */

    


    /**
     * Creates new form Manage_doctorsa     */
    public Manage_doctor_schedules() {
        initComponents();
        connectToDatabase();
        loadDoctor_Schedules();
        
        
    }
    private void connectToDatabase() {
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to connect to the database.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    private void loadDoctor_Schedules() {
        try {
            String query = "SELECT * FROM doctor_schedules";
            System.out.println("Executing SQL Query: " + query);

            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
            while (rs.next()) {
                Object[] row = {
                        rs.getInt("Schedule_id"),   // Assuming Schedule_id is an integer
                        rs.getString("Doctor_id"),    // Corrected to getString for Doctor_id
                        rs.getString("Day_of_week"), // Corrected to getString
                        rs.getString("Status"),        // Corrected to getString
                        rs.getInt("Room_number")     // Assuming Room_number is an integer
                };
                model.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to load doctor schedules.", "Error", JOptionPane.ERROR_MESSAGE);
            System.err.println("SQL Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
    // Method to add a supplier
    private boolean isInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Method to add a supplier
    private void addDoctorSchedule() {
        String s_id = Schedule_id.getText();
        String d_id = Doctor_id.getText();
        String day_of_week = Day_of_week.getText();
        String status = Status.getText();
        String room_no = Room_number.getText();


        // Input validation: Check if s_id and room_no are integers
        if (!isInteger(s_id) || !isInteger(room_no)) {
            JOptionPane.showMessageDialog(this, "Schedule ID and Room Number must be valid integers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method early if invalid input.
        }
        if(d_id.isEmpty() || day_of_week.isEmpty() || status.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please fill all the fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query = "INSERT INTO doctor_schedules (Schedule_id, Doctor_id, Day_of_week, Status, Room_number) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, Integer.parseInt(s_id));
            pstmt.setString(2, d_id);
            pstmt.setString(3, Day_of_week.getText());
            pstmt.setString(4, Status.getText());
            pstmt.setInt(5, Integer.parseInt(room_no));
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Doctor Schedule added successfully.");
            loadDoctor_Schedules();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to add doctor schedule.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Method to remove a schedule
    private void removeDoctorSchedule() {
        String s_id = Schedule_id.getText();

        // Input validation: Check if s_id is an integer
        if (!isInteger(s_id)) {
            JOptionPane.showMessageDialog(this, "Schedule ID must be a valid integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method early if invalid input
        }

        String query = "DELETE FROM doctor_schedules WHERE Schedule_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, Integer.parseInt(s_id));
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Doctor Schedule removed successfully.");
            loadDoctor_Schedules();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to remove doctor schedule.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    // Method to edit a supplier
    private void editDoctorSchedule() {
        String s_id = Schedule_id.getText();
        String d_id = Doctor_id.getText();
        String day_of_week = Day_of_week.getText();
        String status = Status.getText();
        String room_no = Room_number.getText();

        // Input validation: Check if s_id and room_no are integers
        if (!isInteger(s_id) || !isInteger(room_no)) {
            JOptionPane.showMessageDialog(this, "Schedule ID and Room Number must be valid integers.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method early if invalid input
        }
        if(d_id.isEmpty() || day_of_week.isEmpty() || status.isEmpty()){
            JOptionPane.showMessageDialog(this, "Please fill all the fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String query = "UPDATE doctor_schedules SET Doctor_id = ?, Day_of_week = ?, Status = ?, Room_number = ? WHERE Schedule_id = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, d_id);
            pstmt.setString(2, day_of_week);
            pstmt.setString(3, status);
            pstmt.setInt(4, Integer.parseInt(room_no));
            pstmt.setInt(5, Integer.parseInt(s_id));
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Doctor Schedule updated successfully.");
            loadDoctor_Schedules();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Failed to update doctor schedule.", "Error", JOptionPane.ERROR_MESSAGE);
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
        jLabel2 = new javax.swing.JLabel();
        Home = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Schedule_id = new javax.swing.JTextField();
        Doctor_id = new javax.swing.JTextField();
        Day_of_week = new javax.swing.JTextField();
        Status = new javax.swing.JTextField();
        Room_number = new javax.swing.JTextField();
        Doctor_Schedule_add = new javax.swing.JButton();
        Doctor_Schedule_remove = new javax.swing.JButton();
        Doctor_Schedule_edit = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Manage Doctor Schedules");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Schedule List");

        Home.setBackground(new java.awt.Color(255, 204, 204));
        Home.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        Home.setText("Home");
        Home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HomeActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Schedule id");

        jLabel5.setText("Doctor id");

        jLabel6.setText("Day of Week");

        jLabel7.setText("Room Number");

        jLabel9.setText("Status");

        Schedule_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) { Schedule_idActionPerformed(evt); }
        });

        Doctor_Schedule_add.setText("Add");
        Doctor_Schedule_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Doctor_Schedule_addActionPerformed(evt);
            }
        });

        Doctor_Schedule_remove.setText("Remove");
        Doctor_Schedule_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Doctor_Schedule_removeActionPerformed(evt);
            }
        });

        Doctor_Schedule_edit.setText("Update");
        Doctor_Schedule_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Doctor_Schedule_editActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel6)
                                                        .addComponent(jLabel9)
                                                        .addComponent(jLabel7)) //jLabel8 removed
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(Status)
                                                        .addComponent(Room_number)
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                                .addGap(0, 6, Short.MAX_VALUE)
                                                                .addComponent(Schedule_id, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(Doctor_id, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(Day_of_week, javax.swing.GroupLayout.Alignment.TRAILING)) //Supplier_location removed
                                                .addGap(14, 14, 14))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(25, 25, 25)
                                                .addComponent(Doctor_Schedule_add)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(Doctor_Schedule_remove)
                                                .addGap(76, 76, 76))))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(164, 164, 164)
                                .addComponent(Doctor_Schedule_edit)
                                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(44, 44, 44)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(Schedule_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(Doctor_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(Day_of_week, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(Status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(Room_number, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE) // Removed the 18 from the next line
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE) // jLabel8 and Supplier_location removed
                                        .addComponent(Doctor_Schedule_add)
                                        .addComponent(Doctor_Schedule_remove)
                                        .addComponent(Doctor_Schedule_edit))
                                .addGap(66, 66, 66))
        );

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Add New Schedule Data");

        jTable2.setAutoCreateRowSorter(true);
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Schedule id", "Doctor id", "Day of Week", "Status", "Room Number"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(371, 371, 371)
                        .addComponent(jLabel1)
                        .addGap(277, 277, 277)
                        .addComponent(Home)))
                .addGap(18, 18, 18))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(230, 230, 230))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(Home))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(164, Short.MAX_VALUE))
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
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18));
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14));
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));

        Home.setBackground(new java.awt.Color(51, 153, 255));
        Home.setForeground(java.awt.Color.WHITE);
        Home.setFocusPainted(false);
        Home.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,153,255)),javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));



        Doctor_Schedule_add.setBackground(new java.awt.Color(51, 153, 255));
        Doctor_Schedule_add.setForeground(java.awt.Color.WHITE);
        Doctor_Schedule_add.setFocusPainted(false);
        Doctor_Schedule_add.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,153,255)),javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));


        Doctor_Schedule_remove.setBackground(new java.awt.Color(51, 153, 255));
        Doctor_Schedule_remove.setForeground(java.awt.Color.WHITE);
        Doctor_Schedule_remove.setFocusPainted(false);
        Doctor_Schedule_remove.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,153,255)),javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));


        Doctor_Schedule_edit.setBackground(new java.awt.Color(51, 153, 255));
        Doctor_Schedule_edit.setForeground(java.awt.Color.WHITE);
        Doctor_Schedule_edit.setFocusPainted(false);
        Doctor_Schedule_edit.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,153,255)),javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));


        javax.swing.border.CompoundBorder fieldBorder = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204,204,204)),javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));

        Schedule_id.setBorder(fieldBorder);
        Doctor_id.setBorder(fieldBorder);
        Day_of_week.setBorder(fieldBorder);
        Status.setBorder(fieldBorder);
        Room_number.setBorder(fieldBorder);


        jTable2.setGridColor(new java.awt.Color(220, 220, 220));
        jTable2.setBackground(java.awt.Color.WHITE);
        // --- End of Custom UI Code ---

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void HomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HomeActionPerformed
         this.dispose(); // Close current window
    new UI().setVisible(true);
    }//GEN-LAST:event_HomeActionPerformed

    private void Schedule_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Doctor_Schedule_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Doctor_Schedule_idActionPerformed

    private void Doctor_Schedule_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Doctor_Schedule_addActionPerformed
    addDoctorSchedule();
    
        
    }//GEN-LAST:event_Doctor_Schedule_addActionPerformed

    private void Doctor_Schedule_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Doctor_Schedule_removeActionPerformed
    removeDoctorSchedule();
    }//GEN-LAST:event_Doctor_Schedule_removeActionPerformed

    private void Doctor_Schedule_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Doctor_Schedule_editActionPerformed
     editDoctorSchedule();
    }//GEN-LAST:event_Doctor_Schedule_editActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
         java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new Manage_doctor_schedules().setVisible(true);
        }
    });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Home;
    private javax.swing.JButton Doctor_Schedule_add;
    private javax.swing.JButton Doctor_Schedule_edit;
    private javax.swing.JTextField Status;
    private javax.swing.JTextField Schedule_id;
    private javax.swing.JTextField Doctor_id;
    private javax.swing.JTextField Day_of_week;
    private javax.swing.JTextField Room_number;
    private javax.swing.JButton Doctor_Schedule_remove;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
