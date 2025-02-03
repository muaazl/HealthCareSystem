package view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Manage_pharmacy_inventory extends javax.swing.JFrame {

    private Connection conn;

    public Manage_pharmacy_inventory() {
        initComponents();
        connectToDatabase();
        loadInventoryData();
        checkLowStock(); // Added: Check for low stock after loading
    }

    private void connectToDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/healthcare_db";
            String user = "root";
            String password = "";

            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database connection failed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadInventoryData() {
        try {
            String query = "SELECT * FROM inventory"; // Change 'inventory' to your table name
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("drug_id"),
                        rs.getString("drug_name"),
                        rs.getInt("quantity_stock"),
                        rs.getInt("min_stock_level"),
                        rs.getString("supplier_info"),
                        rs.getDouble("purchase_price"),
                        rs.getDouble("selling_price")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load inventory data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void checkLowStock() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        for (int row = 0; row < model.getRowCount(); row++) {
            int currentStock = (int) model.getValueAt(row, 2); // Quantity Of Stock
            int minStockLevel = (int) model.getValueAt(row, 3); // Minimum Stock Level
            String drugName = (String) model.getValueAt(row, 1); // Drug Name

            if (currentStock < minStockLevel) {
                JOptionPane.showMessageDialog(this,
                        "Low stock for " + drugName + ". Current stock: " + currentStock + ", Minimum level: " + minStockLevel + ".",
                        "Low Stock Warning",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        }
    }

    private void addDrug() {
        try {
            String query = "INSERT INTO inventory (drug_id, drug_name, quantity_stock, min_stock_level, supplier_info, purchase_price, selling_price) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);

            if (Inventory_id.getText().isEmpty() || Inventory_name.getText().isEmpty() ||
                    Inventory_stock.getText().isEmpty() || Inventory_level.getText().isEmpty() ||
                    Inventory_info.getText().isEmpty() || Inventory_purchase.getText().isEmpty() || Inventory_selling.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                pst.setInt(1, Integer.parseInt(Inventory_id.getText()));
                pst.setString(2, Inventory_name.getText());
                pst.setInt(3, Integer.parseInt(Inventory_stock.getText()));
                pst.setInt(4, Integer.parseInt(Inventory_level.getText()));
                pst.setString(5, Inventory_info.getText());
                pst.setDouble(6, Double.parseDouble(Inventory_purchase.getText()));
                pst.setDouble(7, Double.parseDouble(Inventory_selling.getText()));

            } catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(this, "Invalid number format", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Drug added successfully.");
            loadInventoryData();
            checkLowStock();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to add drug.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editDrug() {
        try {
            if (Inventory_id.getText().isEmpty() || Inventory_name.getText().isEmpty() ||
                    Inventory_stock.getText().isEmpty() || Inventory_level.getText().isEmpty() ||
                    Inventory_info.getText().isEmpty() || Inventory_purchase.getText().isEmpty() || Inventory_selling.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query = "UPDATE inventory SET drug_name = ?, quantity_stock = ?, min_stock_level = ?, supplier_info = ?, purchase_price = ?, selling_price = ? WHERE drug_id = ?";
            PreparedStatement pst = conn.prepareStatement(query);

            try {
                pst.setString(1, Inventory_name.getText());
                pst.setInt(2, Integer.parseInt(Inventory_stock.getText()));
                pst.setInt(3, Integer.parseInt(Inventory_level.getText()));
                pst.setString(4, Inventory_info.getText());
                pst.setDouble(5, Double.parseDouble(Inventory_purchase.getText()));
                pst.setDouble(6, Double.parseDouble(Inventory_selling.getText()));
                pst.setInt(7, Integer.parseInt(Inventory_id.getText()));
            } catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(this, "Invalid number format", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Drug updated successfully.");
            loadInventoryData();
            checkLowStock();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to update drug.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeDrug() {
        try {

            if (Inventory_id.getText().isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Please, fill the id field", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                String query = "DELETE FROM inventory WHERE drug_id = ?";
                PreparedStatement pst = conn.prepareStatement(query);
                pst.setInt(1, Integer.parseInt(Inventory_id.getText()));
                pst.executeUpdate();
                JOptionPane.showMessageDialog(this, "Drug removed successfully.");
            } catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(this, "Invalid number format", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            loadInventoryData();
            checkLowStock();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to remove drug.", "Error", JOptionPane.ERROR_MESSAGE);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        home = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        Inventory_add = new javax.swing.JButton();
        Inventory_edit = new javax.swing.JButton();
        Inventory_remove = new javax.swing.JButton();
        Inventory_id = new javax.swing.JTextField();
        Inventory_name = new javax.swing.JTextField();
        Inventory_stock = new javax.swing.JTextField();
        Inventory_level = new javax.swing.JTextField();
        Inventory_info = new javax.swing.JTextField();
        Inventory_purchase = new javax.swing.JTextField();
        Inventory_selling = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setToolTipText("");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Manage Pharmacy Inventory");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null},
                        {null, null, null, null, null, null, null}
                },
                new String [] {
                        "Drug ID", "Drug Name", "Quantity Of Stock", "Minimum Stock Level", "Supplier Information", "Purchase Price", "Selling Price"
                }
        ));
        jScrollPane1.setViewportView(jTable1);

        home.setText("Home");
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel2.setText("Add New Inventory");

        jLabel3.setText("Drug ID");

        jLabel4.setText("Drug Name");

        jLabel5.setText("Quantity Of Stock");

        jLabel6.setText("Minimum Stock Level");

        jLabel7.setText("Supplier Information");

        jLabel8.setText("Purchase Price");

        jLabel9.setText("Selling Price");

        Inventory_add.setText("Add");
        Inventory_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Inventory_addActionPerformed(evt);
            }
        });

        Inventory_edit.setText("Update");
        Inventory_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Inventory_editActionPerformed(evt);
            }
        });

        Inventory_remove.setText("Remove");
        Inventory_remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Inventory_removeActionPerformed(evt);
            }
        });

        Inventory_selling.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Inventory_sellingActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(176, 176, 176)
                                .addComponent(jLabel2)
                                .addContainerGap())
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(Inventory_add)
                                                .addGap(36, 36, 36)
                                                .addComponent(Inventory_edit)
                                                .addGap(30, 30, 30)
                                                .addComponent(Inventory_remove))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel5)
                                                        .addComponent(jLabel6)
                                                        .addComponent(jLabel7)
                                                        .addComponent(jLabel8)
                                                        .addComponent(jLabel9))
                                                .addGap(30, 30, 30)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(Inventory_name, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(Inventory_stock, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(Inventory_level, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(Inventory_info, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(Inventory_purchase, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(Inventory_selling, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                                        .addComponent(Inventory_id))
                                                .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(Inventory_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel4)
                                        .addComponent(Inventory_name, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel5)
                                        .addComponent(Inventory_stock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel6)
                                        .addComponent(Inventory_level, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(Inventory_info, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(Inventory_purchase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(Inventory_selling, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(51, 51, 51)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Inventory_add)
                                        .addComponent(Inventory_edit)
                                        .addComponent(Inventory_remove))
                                .addContainerGap(81, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(291, 291, 291)
                                                .addComponent(home))
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(home)
                                        .addComponent(jLabel1))
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 175, Short.MAX_VALUE))
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
        jLabel9.setForeground(new java.awt.Color(51, 51, 51));

        javax.swing.border.CompoundBorder fieldBorder = javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204,204,204)),javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        Inventory_id.setBorder(fieldBorder);
        Inventory_name.setBorder(fieldBorder);
        Inventory_stock.setBorder(fieldBorder);
        Inventory_level.setBorder(fieldBorder);
        Inventory_info.setBorder(fieldBorder);
        Inventory_purchase.setBorder(fieldBorder);
        Inventory_selling.setBorder(fieldBorder);

        jTable1.setGridColor(new java.awt.Color(220, 220, 220));
        jTable1.setBackground(java.awt.Color.WHITE);

        home.setBackground(new java.awt.Color(51, 153, 255));
        home.setForeground(java.awt.Color.WHITE);
        home.setFocusPainted(false);
        home.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,153,255)),javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));


        Inventory_add.setBackground(new java.awt.Color(51, 153, 255));
        Inventory_add.setForeground(java.awt.Color.WHITE);
        Inventory_add.setFocusPainted(false);
        Inventory_add.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,153,255)),javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        Inventory_edit.setBackground(new java.awt.Color(51, 153, 255));
        Inventory_edit.setForeground(java.awt.Color.WHITE);
        Inventory_edit.setFocusPainted(false);
        Inventory_edit.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,153,255)),javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        Inventory_remove.setBackground(new java.awt.Color(51, 153, 255));
        Inventory_remove.setForeground(java.awt.Color.WHITE);
        Inventory_remove.setFocusPainted(false);
        Inventory_remove.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51,153,255)),javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10)));

        // --- End of Custom UI Code ---

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        UI homeWindow = new UI();
        homeWindow.setVisible(true);

        // Close the current Manage_suppliers window
        this.dispose();
    }//GEN-LAST:event_homeActionPerformed

    private void Inventory_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Inventory_addActionPerformed
        addDrug();
    }//GEN-LAST:event_Inventory_addActionPerformed

    private void Inventory_editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Inventory_editActionPerformed
        editDrug();
    }//GEN-LAST:event_Inventory_editActionPerformed

    private void Inventory_removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Inventory_removeActionPerformed
        removeDrug();
    }//GEN-LAST:event_Inventory_removeActionPerformed

    private void Inventory_sellingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Inventory_sellingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Inventory_sellingActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new Manage_pharmacy_inventory().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Inventory_add;
    private javax.swing.JButton Inventory_edit;
    private javax.swing.JTextField Inventory_id;
    private javax.swing.JTextField Inventory_info;
    private javax.swing.JTextField Inventory_level;
    private javax.swing.JTextField Inventory_name;
    private javax.swing.JTextField Inventory_purchase;
    private javax.swing.JButton Inventory_remove;
    private javax.swing.JTextField Inventory_selling;
    private javax.swing.JTextField Inventory_stock;
    private javax.swing.JButton home;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}