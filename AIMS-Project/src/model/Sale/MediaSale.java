/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Sale;

import db.ConnectSQL;

/**
 *
 * @author hsnt
 */
public class MediaSale {
    private int sale_id;
    private int media_id;
    private int quantity;
    private int percent;
    public MediaSale(int media_id, int quantity, int percent) {
        this.media_id =  media_id;
        this.quantity = quantity;
        this.percent = percent;
    }

    public int getSale_id() {
        return sale_id;
    }

    public int getMedia_id() {
        return media_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPercent() {
        return percent;
    }
    public MediaSale(int media_id) {
        this.media_id =  media_id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }
    
    public void setSale_id(int sale_id) {
        this.sale_id = sale_id;
    }
    public void save(){
        ConnectSQL.sqlQueryUpdate("insert into MediaSales "
                + "(`sale_id`,`media_id`,`percent`,`quantity`) "
                + "values("+ sale_id+ ","+ media_id+ ","+ percent+ ","+ quantity+ ")");
    }
    
}
