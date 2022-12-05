/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Media;

import db.ConnectSQL;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Toshiba T75
 */
public class DVD extends PhysicalGood {
    private String publication_date;
    private int runtime;
    private String subtitle;
    private int language_id;
    private String studio_name;
    private int dvd_type_id;
    private String writer_name;
    
    public DVD() {
        
    }
    
    public DVD(int id, String title, int value, int price, int sale_percent, int category, String image_path, String barcode, String description, int quantity, String date, int width, int height, int depth, int weight, int type, String director, int runtime, String studio, int language, String subtitle, String publication_date) {
        super(id, title, value, price, sale_percent, category, image_path, barcode, description, quantity, date, width, height, depth, weight);
        this.dvd_type_id = type;
        this.writer_name = director;
        this.runtime = runtime;
        this.studio_name = studio;
        this.language_id = language;
        this.subtitle = subtitle;
        this.publication_date = publication_date;
    }
    
    public DVD(String title, int value, int price, int sale_percent, int category, String image_path, String barcode, String description, int quantity, String date, int width, int height, int depth, int weight, int type, String director, int runtime, String studio, int language, String subtitle, String publication_date) {
        super(title, value, price, sale_percent, category, image_path, barcode, description, quantity, date, width, height, depth, weight);
        this.dvd_type_id = type;
        this.writer_name = director;
        this.runtime = runtime;
        this.studio_name = studio;
        this.language_id = language;
        this.subtitle = subtitle;
        this.publication_date = publication_date;
    }
    
    public String getPublicationDate() {
        return publication_date;
    }
    
    public void setPublicationDate(String date) {
        this.publication_date = date;
    }
    
    public int getRunTime() {
        return runtime;
    }
    
    public void setRunTime(int runtime) {
        this.runtime = runtime;
    }
    
    public String getSubtitle() {
        return subtitle;
    }
    
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    
    public int getLanguageId() {
        return language_id;
    }
    
    public void setLanguageId(int language) {
        this.language_id = language;
    }
    
    public String getStudioName(){ 
        return studio_name;
    }
    
    public void setStudioName(String studio_name) {
        this.studio_name = studio_name;
    }
    
    public int getDvdTypeId() {
        return dvd_type_id;
    }
    
    public void setDvdTypeID(int type) {
        this.dvd_type_id = type;
    }
    
    public String getWriterName() {
        return writer_name;
    }
    
    public void setWriterName(String writer_name) {
        this.writer_name = writer_name;
    }
    
    private void insertDVD() {
        String query = "INSERT INTO `Dvds` (publication_date, runtime, subtitle, media_id, language_id, studio_name, dvd_type_id, writer_name) VALUES ('" 
                + publication_date + "', " + runtime + ", '" + subtitle + "', " + this.getId() + ", " + language_id + ", '" + studio_name + "', " + dvd_type_id + ", '" + writer_name + "');";
        try {
            ConnectSQL.sqlQueryUpdate(query);
        } catch (Exception e) {
            
        }
    }

    @Override
    public void insert() {
        this.insertPhysical();
        this.insertDVD();
    }
    
    private void updateDVD() {
        String query = "UPDATE `Dvds` SET publication_date = '" + publication_date + "', runtime = " + runtime + ", subtitle = '" + subtitle + "', language_id = " +
                language_id + ", studio_name = '" + studio_name + "', dvd_type_id = " + dvd_type_id + ", writer_name = '" + writer_name + "' WHERE media_id = " + this.getId() + ";";
        try {
            ConnectSQL.sqlQueryUpdate(query);
        } catch (Exception e) {
            
        }
    }
    
    public void update() {
        this.updatePhysical();
        this.updateDVD();
    }
}
