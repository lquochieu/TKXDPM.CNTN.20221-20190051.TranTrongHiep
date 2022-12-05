/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Media;

import db.ConnectSQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Toshiba T75
 */
public abstract class PhysicalGood extends Media {
    private String barcode;
    private String description;
    private int quantity;
    private String input_day;
    private int width;
    private int height;
    private int depth;
    private int weight;
    
    public PhysicalGood() {
        super();
    }

    public PhysicalGood(int id, String title, int value, int price, int sale_percent, int category, String image_path, String barcode, String description, int quantity, String input_day, int width, int height, int depth, int weight) {
        super(id, title, value, price, sale_percent, category, image_path);
        this.barcode = barcode;
        this.description = description;
        this.quantity = quantity;
        this.input_day = input_day;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.weight = weight;
    }
    
    public PhysicalGood(String title, int value, int price, int sale_percent, int category, String image_path, String barcode, String description, int quantity, String input_day, int width, int height, int depth, int weight) {
        super(title, value, price, sale_percent, category, image_path);
        this.barcode = barcode;
        this.description = description;
        this.quantity = quantity;
        this.input_day = input_day;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.weight = weight;
    }
    public static int getQuantityByID(int media_id){
        ResultSet rs = ConnectSQL.sqlQuery("Select quantity from PhysicalGoods where media_id = " + media_id);
        try {
            while(rs.next()){
                return Integer.parseInt(rs.getString("quantity"));
            }
            return 0;
        } catch (SQLException ex) {
            return 0;
        }
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getInputDay() {
        return input_day;
    }

    public void setInputDay(String input_day) {
        this.input_day = input_day;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public int getWeight() {
        return weight;
    }
    
    public void setWeight(int weight) {
        this.weight = weight;
    }
    
    protected void insertPhysical() {
        this.setId(this.insertMedia());
        
        String physical_query = "INSERT INTO `PhysicalGoods` (description, quantity, input_day, media_id, width, height, depth, weight, barcode) VALUES ('" +
                    description + "', " + quantity + ", '" + input_day + "', " + this.getId() + ", " + width + ", " + height + ", " + depth + ", " + weight + ", '" + barcode + "');";
        
        try {
            ConnectSQL.sqlQueryUpdate(physical_query);
        } catch (Exception e) {
        }
    }
    
    protected void updatePhysical() {
        this.updateMedia();
        String physical_query = "UPDATE `PhysicalGoods` SET description = '" + getDescription() + "', quantity = " + getQuantity() + ", input_day = '" + 
                getInputDay() + "', width = " + getWidth() + ", height = " + getHeight() + ", depth = " + getDepth() + ", weight = " + getWeight() 
                + ", barcode = '" + getBarcode() + "' WHERE media_id = " + this.getId();
        try {
            ConnectSQL.sqlQueryUpdate(physical_query);
        } catch (Exception e) {
            
        }
    }
    
}
