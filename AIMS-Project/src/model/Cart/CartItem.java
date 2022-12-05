/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Cart;

import db.ConnectSQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.Media.PhysicalGood;
import model.User.User;

/**
 *
 * @author hsnt
 */
public class CartItem {
    private int media_id;
    private int quantity;
    private int price;
    private int user_id;
    
    public static int getShipFee(int user_id,int district_id){
        
        try {
            int totalBill = 0;
            User user = new User(user_id);
            List<CartItem> cartItems = user.getCartItems();
            totalBill = cartItems.stream().map((cartItem) -> cartItem.getPrice() * cartItem.getQuantity()).reduce(totalBill, Integer::sum);
            if(totalBill >= 100000){
                return 0;
            }   
            int weight1 = 0,weight2 = 0;
            ResultSet rs = ConnectSQL.sqlQuery("SELECT p.height*p.depth*p.width as weight2, p.weight*c.quantity as weight1 FROM CartItems c join Medias m using (media_id) join PhysicalGoods p using ( media_id) where user_id = "+user_id+" and p.weight*c.quantity = (SELECT max(p.weight*c.quantity) as max_depth FROM CartItems c join Medias m using (media_id) join PhysicalGoods p using ( media_id) where user_id = "+user_id+")");
            while(rs.next()){
                weight1 = Integer.parseInt(rs.getString("weight1"));
                weight2 = Integer.parseInt(rs.getString("weight2"));
            } 
            float bill = 0;
            float weigh = weight1/1000 + weight2/6000;
            if(ShippingInfo.checkShip(district_id)){
                if(weigh <= 3.0){
                    bill = 22000;
                }else{
                    bill = (float) (22000 + (weigh-3.0)/0.5*2500);                    
                }
            }else{
                if(weigh <= 3.0){
                    bill = 30000;
                }else{
                    bill = (float) (30000 + (weigh-3.0)/0.5*2500);                    
                }
            } 
            return (int) bill;
        } catch (Exception ex) {
            return 0;
        }
    }
    public CartItem(int media_id,int quantily, int price,int user_id) {
        this.media_id = media_id;
        this.price = price;
        this.quantity = quantily;
        this.user_id = user_id;
    }  
    public int getMedia_id() {
        return media_id;
    }
    public int getMaxQuantity(){
        return PhysicalGood.getQuantityByID(this.media_id);
    }
    public int getUser_id() {
        return user_id;
    }
    
    public String getMediaName(){
        try {
            ResultSet rs = ConnectSQL.sqlQuery("select * from Medias where media_id = " + this.media_id);
            while(rs.next()){
                return rs.getString("title");
            }
            return null;
        } catch (SQLException e) {
            return null;
        }        
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }
    public void update(int quantily){
        this.quantity = quantily;
        ConnectSQL.sqlUpdate("update CartItems set quantity = " + this.quantity + " where user_id = " + user_id + " and media_id = " + media_id);
    }
    public void delete(){
        ConnectSQL.sqlQueryUpdate("delete from CartItems where media_id = " + media_id + " and user_id = " + user_id);
    }
    public static void creat(int user_id, int media_id, int price){
        try {
            boolean flag = false;
            ResultSet rs = ConnectSQL.sqlQuery("Select * from CartItems where media_id = " + media_id + " and user_id = " + user_id);
            int quantity  = 1;
            while(rs.next()){
                quantity += Integer.parseInt(rs.getString("quantity"));
                flag = true;
            }
            if(flag){
                ConnectSQL.sqlUpdate("update CartItems set quantity = " + quantity + " where user_id = " + user_id + " and media_id = " + media_id);
            }else{
                ConnectSQL.sqlQueryUpdate("insert into CartItems (media_id,user_id, quantity, price) "
                        + "values ("+media_id + "," + user_id + ","+quantity + "," +price +")");
            }
        } catch (Exception e) {
        }
    }
    public static void creat(int user_id, int media_id, int price, int quantity){
        try {
            boolean flag = false;
            ResultSet rs = ConnectSQL.sqlQuery("Select * from CartItems where media_id = " + media_id + " and user_id = " + user_id);
            int quantity_tmp  = 0;
            while(rs.next()){
                quantity_tmp += Integer.parseInt(rs.getString("quantity"));
                flag = true;
            }
            if(quantity == 0) return;
            if(flag){
                quantity += quantity_tmp; 
                ConnectSQL.sqlUpdate("update CartItems set quantity = " + quantity + " where user_id = " + user_id + " and media_id = " + media_id);
            }else{
                ConnectSQL.sqlQueryUpdate("insert into CartItems (media_id,user_id, quantity, price) "
                        + "values ("+media_id + "," + user_id + ","+quantity + "," +price +")");
            }
        } catch (Exception e) {
        }
    }
}
