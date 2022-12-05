/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.User;

import db.ConnectSQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cart.CartItem;
import model.Cart.Order;
import model.Cart.ShippingInfo;

/**
 *
 * @author hsnt
 */
public class User {
    private int user_id;
    
    public User() {
        this.user_id = 1;
    }
    public User(int user_id){
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }
    
    public List<ShippingInfo>  getShippingList(){
        try {
            int index = 0;
            List<ShippingInfo> shippingInfos = new ArrayList<ShippingInfo>();            
            ResultSet rs = ConnectSQL.sqlQuery("select * from ShippingInfos where user_id = " + user_id);
            while(rs.next()){                
                int tmp = Integer.parseInt(rs.getString("shipping_info_id"));
                ShippingInfo shippingInfo = new ShippingInfo(tmp);
                shippingInfos.add(shippingInfo);
                index++;
            }
            return index == 0 ? null : shippingInfos;
        } catch (SQLException ex) {
            return null;
        }
    }
    public List<CartItem> getCartItems(){
        try {
            int index = 0;
            List<CartItem> cartItems = new ArrayList<CartItem>();
            ResultSet rs = ConnectSQL.sqlQuery("select * from CartItems where user_id = " + this.user_id);
            while(rs.next()){
                int media_id = Integer.parseInt(rs.getString("media_id"));
                int price = Integer.parseInt(rs.getString("price"));
                int quantity = Integer.parseInt(rs.getString("quantity"));
                CartItem cartItem = new CartItem(media_id, quantity, price, this.user_id);
                cartItems.add(cartItem);
                index++;
            }
            return index == 0 ? null : cartItems;
            
        } catch (NumberFormatException | SQLException e) {
        }
        return null;
    }
    public List<Order> getOrders(){
        try {
            int index = 0;
            List<Order> orders = new ArrayList<Order>();
            ResultSet rs = ConnectSQL.sqlQuery("select * from Orders where user_id = " + this.user_id);
            while(rs.next()){
                int order_id = Integer.parseInt(rs.getString("order_id"));
                int ship_fee = Integer.parseInt(rs.getString("ship_fee"));
                int order_state_id = Integer.parseInt(rs.getString("order_state_id"));
                String shipping_info = rs.getString("shipping_info");
                String card_number = rs.getString("card_number");
                String created_at = rs.getString("created_at");
                Order order = new Order(order_id, order_state_id, user_id, ship_fee, shipping_info, card_number,created_at);
                orders.add(order);
                index++;
            }
            
            return index == 0 ? null : orders;
            
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int [] stateOrder(){
        List<Order> orders = this.getOrders();
        int[] result = {0,0,0,0,0,0};
        for(Order order: orders){
            switch(order.getOrder_state_id()){
                case Order.SUCCESS:
                    result[0]++;
                    result[1] += order.getTotalBill() + order.getShip_fee();
                    break;
                case Order.CANCEL:
                    result[2]++;
                    result[3] += order.getTotalBill() + order.getShip_fee();
                    break;
                case Order.DELIVERING:
                    result[4]++;
                    result[5] += order.getTotalBill() + order.getShip_fee();
                    break;
            }
        }
        return result;
    }
    public static int [] stateAllOrder(){
        List<Order> orders = getAllOrders();
        int[] result = {0,0,0,0,0,0};
        for(Order order: orders){
            switch(order.getOrder_state_id()){
                case Order.SUCCESS:
                    result[0]++;
                    result[1] += order.getTotalBill() + order.getShip_fee();
                    break;
                case Order.CANCEL:
                    result[2]++;
                    result[3] += order.getTotalBill() + order.getShip_fee();
                    break;
                case Order.DELIVERING:
                    result[4]++;
                    result[5] += order.getTotalBill() + order.getShip_fee();
                    break;
            }
        }
        return result;
    }
    public static List<Order> getAllOrders(){
        try {
            int index = 0;
            List<Order> orders = new ArrayList<Order>();
            ResultSet rs = ConnectSQL.sqlQuery("select * from Orders");
            while(rs.next()){
                int order_id = Integer.parseInt(rs.getString("order_id"));
                int ship_fee = Integer.parseInt(rs.getString("ship_fee"));
                int order_state_id = Integer.parseInt(rs.getString("order_state_id"));
                String shipping_info = rs.getString("shipping_info");
                int userID = Integer.parseInt(rs.getString("user_id"));
                String card_number = rs.getString("card_number");
                String created_at = rs.getString("created_at");
                Order order = new Order(order_id, order_state_id, userID, ship_fee, shipping_info, card_number,created_at);
                orders.add(order);
                index++;
            }
            
            return index == 0 ? null : orders;
            
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
