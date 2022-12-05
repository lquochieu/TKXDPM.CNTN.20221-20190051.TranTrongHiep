/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Cart;

import db.ConnectSQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User.User;

/**
 *
 * @author hsnt
 */
public class Order {
    public static final int CANCEL = 1;
    public static final int SUCCESS = 2;
    public static final int DELIVERING = 3;
    private int order_id;
    private int ship_fee;
    private int order_state_id;
    private String shipping_info;
    private int user_id;
    private String card_number;
    private String created_at;
    
    public Order(int user_id,int ship_fee, String shipping_info, String card_number){
        this.user_id = user_id;
        this.ship_fee = ship_fee;
        this.shipping_info = shipping_info;
        this.card_number = card_number;
        this.order_state_id = DELIVERING;
        save();
        this.order_id = getID();
        User user = new User(user_id);
        for(CartItem cartItem : user.getCartItems()){
            OrderItem orderItem = new OrderItem(cartItem.getMedia_id(),order_id, cartItem.getPrice(), cartItem.getQuantity());
            orderItem.save();
            cartItem.delete();
        }
    }
    
    public Order(int order_id,int order_state_id,int user_id,int ship_fee, String shipping_info, String card_number,String created_at){
        this.user_id = user_id;
        this.ship_fee = ship_fee;
        this.shipping_info = shipping_info;
        this.card_number = card_number;
        this.order_state_id = order_state_id;
        this.order_id = order_id;
        this.created_at = created_at;
    }

    public int getOrder_id() {
        return order_id;
    }

    public String getCreated_at() {
        return created_at;
    }
    
    public String getCard_number() {
        return card_number;
    }
    
    public void save(){
        ConnectSQL.sqlQueryUpdate("insert into Orders "
                + "(`ship_fee`,`order_state_id`,`user_id`,`shipping_info`,`card_number`) "
                + "values("+ ship_fee+ ","+ order_state_id+ ","+ user_id+ ",'"+ shipping_info+ "','"+ card_number+ "')");
    }
    private int getID(){
        try {
            ResultSet rs = ConnectSQL.sqlQuery("select * from Orders order by order_id desc");
            while(rs.next()){
                return Integer.parseInt(rs.getString("order_id"));
            }
            return 0;
        } catch (SQLException ex) {
            return 0;
        }
    }

    public int getShip_fee() {
        return ship_fee;
    }

    public int getOrder_state_id() {
        return order_state_id;
    }

    public String getShipping_info() {
        return shipping_info;
    }
    public int getTotalBill(){
        ResultSet rs = ConnectSQL.sqlQuery("select sum(price*quantity) as sum from OrderItems where order_id = " + order_id);
        try {
            while(rs.next()){
                return Integer.parseInt(rs.getString("sum"));
            }
            return 0;
        } catch (SQLException ex) {
            return 0;
        }
    }
    public String getState(){
        switch(this.order_state_id){
            case DELIVERING:
                return "Delivering";
            case CANCEL:
                return "Cancel";
            case SUCCESS:
                return "Success";
        }
        return null;
    }
    public void cancel(){
        this.order_state_id = CANCEL;
        ConnectSQL.sqlQueryUpdate("Update Orders set order_state_id = " + CANCEL + " where order_id = " + order_id + " and order_state_id = " + DELIVERING);
    }
    public List<OrderItem> getOrderItems(){
        try {
            int index = 0;
            List<OrderItem> orderItems = new ArrayList<OrderItem>();
            ResultSet rs = ConnectSQL.sqlQuery("select * from OrderItems where order_id = " + order_id);
            while(rs.next()){
                int media_id;
                if(rs.getString("media_id") == null){
                    media_id = -1;
                }else{
                    media_id = Integer.parseInt(rs.getString("media_id"));
                }
                 
                int price = Integer.parseInt(rs.getString("price"));
                int quantity = Integer.parseInt(rs.getString("quantity"));
                OrderItem orderItem = new OrderItem(media_id, order_id, price, quantity);
                orderItems.add(orderItem);
                index++;
            }
            return index == 0 ? null : orderItems;
            
        } catch (NumberFormatException | SQLException e) {
        }
        return null;
    }
}
