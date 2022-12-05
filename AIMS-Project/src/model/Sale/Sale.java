/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Sale;

import aims.DateService;
import db.ConnectSQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Media.Media;

/**
 *
 * @author hsnt
 */
public class Sale {
    private int sale_id;
    private String start_day;
    private String end_day;
    public  Sale(String start_day, String end_day, List<MediaSale> mediaSales){
        this.start_day = DateService.converTimestamp(start_day, "yyyy/MM/dd").toString();
        this.end_day = DateService.converTimestamp(end_day, "yyyy/MM/dd").toString();
        save();
        this.sale_id = getID();
        for(MediaSale mediaSale : mediaSales){
            mediaSale.setSale_id(sale_id);
            mediaSale.save();
        }  
    }

    public int getSale_id() {
        return sale_id;
    }

    public String getStart_day() {
        return start_day;
    }

    public String getEnd_day() {
        return end_day;
    }
    public String getState(){
        String fomat = "yyyy-MM-dd";
        String start_ = start_day.split(" ")[0];
        String end_ = end_day.split(" ")[0];
        String current_day = DateService.now(fomat);
        if(DateService.compareDate(start_, current_day, fomat) == 1){
            return "Not yet started";
        }else if (DateService.compareDate(current_day, end_, fomat) == 1){
            return "Sale completed";
        }
        return "Saling";
    }
    public  Sale(int sale_id, String start_day, String end_day){
        this.start_day = start_day;
        this.end_day = end_day;
        this.sale_id = sale_id; 
    }
    public static List<Sale> getAllSales(){
        List<Sale> sales = new ArrayList<Sale>();
        ResultSet rs = ConnectSQL.sqlQuery("SELECT * FROM `Sales` ORDER BY start_day DESC");
        try {
            while(rs.next()){
                int id = Integer.parseInt(rs.getString("sale_id"));
                String start_ = rs.getString("start_day");
                String end_ = rs.getString("end_day");
                Sale sale = new Sale(id, start_, end_);
                sales.add(sale);
            }
        } catch (SQLException ex) {
        }
        return sales.isEmpty() ? null: sales;
    }
    public List<MediaSale> getMediaSales(){
        List<MediaSale> mediaSales = new ArrayList<MediaSale>();
        ResultSet rs = ConnectSQL.sqlQuery("Select * from MediaSales where sale_id = " + this.sale_id);
        try {
            while(rs.next()){
                int media_id = Integer.parseInt(rs.getString("media_id"));
                int quantity =  Integer.parseInt(rs.getString("quantity"));
                int percent = Integer.parseInt(rs.getString("percent"));
                MediaSale mediaSale = new MediaSale(media_id, quantity, percent);
                mediaSales.add(mediaSale);
            }
        } catch (SQLException ex) {
        }
        return mediaSales.isEmpty() ? null: mediaSales;
        
    }
    public void save(){
        ConnectSQL.sqlQueryUpdate("insert into Sales "
                + "(`start_day`,`end_day`) "
                + "values('"+ start_day+ "','"+ end_day+ "')");
    }
    private int getID(){
        try {
            ResultSet rs = ConnectSQL.sqlQuery("select * from Sales order by sale_id desc");
            while(rs.next()){
                return Integer.parseInt(rs.getString("sale_id"));
            }
            return 0;
        } catch (SQLException ex) {
            return 0;
        }
    }
}
