package model.Cart;

import db.ConnectSQL;
import model.Media.Media;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hsnt
 */
public class OrderItem {
    private int media_id;
    private int order_id;
    private int price;
    private int quantity;

    public OrderItem(int media_id, int order_id, int price, int quantity) {
        this.media_id = media_id;
        this.order_id = order_id;
        this.price = price;
        this.quantity = quantity;
    }
    public void save(){
        ConnectSQL.sqlQueryUpdate("insert into OrderItems "
                + "(`order_id`,`media_id`,`price`,`quantity`) "
                + "values("+ order_id+ ","+ media_id+ ","+ price+ ","+ quantity+ ")");
    }
    public String getMediaTitle(){
        return Media.getTitlebyID(media_id);
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }
    
}
