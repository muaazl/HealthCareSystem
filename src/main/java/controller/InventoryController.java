package controller;

import model.InventoryModel;
import util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryController {
    private Connection connection;


    public InventoryController(){
        this.connection = DatabaseConnection.getConnection();

    }


    public List<InventoryModel> getAllInventoryItems()  {

        List<InventoryModel> inventoryList = new ArrayList<>();
        String query = "SELECT * FROM inventory";

        try(  PreparedStatement stmt = connection.prepareStatement(query);
              ResultSet rs = stmt.executeQuery()){


            while(rs.next())
            {
                InventoryModel inventory = new InventoryModel(

                        rs.getInt("drug_id"),
                        rs.getString("drug_name"),
                        rs.getInt("quantity_stock"),
                        rs.getInt("min_stock_level"),
                        rs.getString("supplier_info"),
                        rs.getDouble("purchase_price"),
                        rs.getDouble("selling_price")

                );

                inventoryList.add(inventory);

            }


        }catch (SQLException e){
            e.printStackTrace();
        }


        return inventoryList;


    }

    public InventoryModel getInventoryItemById(int drug_id)  {

        InventoryModel inventory= null;
        String query ="SELECT * FROM inventory where drug_id = ?";
        try( PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1,drug_id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){

                inventory=  new InventoryModel(
                        rs.getInt("drug_id"),
                        rs.getString("drug_name"),
                        rs.getInt("quantity_stock"),
                        rs.getInt("min_stock_level"),
                        rs.getString("supplier_info"),
                        rs.getDouble("purchase_price"),
                        rs.getDouble("selling_price")
                );
            }


        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return inventory;
    }
    public void addInventoryItem(InventoryModel inventory)  {

        String query ="INSERT INTO inventory (drug_id, drug_name, quantity_stock, min_stock_level, supplier_info, purchase_price, selling_price) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try(   PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1,inventory.getDrugId());
            stmt.setString(2,inventory.getDrugName());
            stmt.setInt(3,inventory.getQuantityStock());
            stmt.setInt(4,inventory.getMinStockLevel());
            stmt.setString(5,inventory.getSupplierInfo());
            stmt.setDouble(6, inventory.getPurchasePrice());
            stmt.setDouble(7,inventory.getSellingPrice());

            stmt.executeUpdate();


        }

        catch(SQLException e) {
            e.printStackTrace();

        }
    }
    public void updateInventoryItem(InventoryModel inventory) {

        String query = "UPDATE inventory SET drug_name = ?, quantity_stock = ?, min_stock_level = ?, supplier_info = ?, purchase_price = ?, selling_price = ? WHERE drug_id = ?";
        try(  PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, inventory.getDrugName());
            stmt.setInt(2,inventory.getQuantityStock());
            stmt.setInt(3,inventory.getMinStockLevel());
            stmt.setString(4, inventory.getSupplierInfo());
            stmt.setDouble(5, inventory.getPurchasePrice());
            stmt.setDouble(6, inventory.getSellingPrice());
            stmt.setInt(7, inventory.getDrugId());

            stmt.executeUpdate();
        }

        catch(SQLException e) {

            e.printStackTrace();
        }

    }



    public void removeInventoryItem(int itemId) {
        String query = "DELETE FROM inventory WHERE drug_id = ?";

        try(  PreparedStatement stmt = connection.prepareStatement(query)){
            stmt.setInt(1, itemId);
            stmt.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();

        }
    }


}