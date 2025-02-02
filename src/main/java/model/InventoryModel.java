package model;

public class InventoryModel {
    private int drug_id;
    private String drug_name;
    private int quantity_stock;
    private int min_stock_level;
    private String supplier_info;
    private double purchase_price;
    private double selling_price;


    public InventoryModel(int drug_id, String drug_name, int quantity_stock, int min_stock_level,String supplier_info, double purchase_price, double selling_price){

        this.drug_id=drug_id;
        this.drug_name=drug_name;
        this.quantity_stock = quantity_stock;
        this.min_stock_level=min_stock_level;
        this.supplier_info = supplier_info;
        this.purchase_price= purchase_price;
        this.selling_price = selling_price;

    }
    public int getDrugId() {
        return drug_id;
    }

    public void setDrugId(int drug_id) {
        this.drug_id = drug_id;
    }

    public String getDrugName() {
        return drug_name;
    }

    public void setDrugName(String drug_name) {
        this.drug_name = drug_name;
    }

    public int getQuantityStock() {
        return quantity_stock;
    }
    public void setQuantityStock(int quantity_stock) {
        this.quantity_stock= quantity_stock;
    }
    public int getMinStockLevel() {
        return min_stock_level;
    }
    public void setMinStockLevel(int min_stock_level) {
        this.min_stock_level = min_stock_level;
    }
    public String getSupplierInfo() {
        return supplier_info;
    }
    public void setSupplierInfo(String supplier_info) {
        this.supplier_info = supplier_info;
    }

    public double getPurchasePrice() {
        return purchase_price;
    }

    public void setPurchasePrice(double purchase_price) {
        this.purchase_price=purchase_price;
    }


    public double getSellingPrice() {
        return selling_price;
    }
    public void setSellingPrice(double selling_price) {
        this.selling_price = selling_price;
    }



}
