/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Media;

import db.ConnectSQL;
import java.util.ArrayList;

/**
 *
 * @author Toshiba T75
 */
public class LP extends PhysicalGood {
    private String artist_name;
    private String record_label_name;
    private String publication_date;
    private String genre;
    private String track_list;
    
    public LP() {
        
    }
    
    public LP(int id, String title, int value, int price, int sale_percent, int category, String image_path, String barcode, String description, int quantity, String date, int width, int height, int depth, int weight, String artist, String record_label, String publication_date, String genre, String track_list) {
        super(id, title, value, price, sale_percent, category, image_path, barcode, description, quantity, date, width, height, depth, weight);
        this.artist_name = artist;
        this.record_label_name = record_label;
        this.publication_date = publication_date;
        this.genre = genre;
        this.track_list =  track_list;
    }
    
    public LP(String title, int value, int price, int sale_percent, int category, String image_path, String barcode, String description, int quantity, String date, int width, int height, int depth, int weight, String artist, String record_label, String publication_date, String genre, String track_list) {
        super(title, value, price, sale_percent, category, image_path, barcode, description, quantity, date, width, height, depth, weight);
        this.artist_name = artist;
        this.record_label_name = record_label;
        this.publication_date = publication_date;
        this.genre = genre;
        this.track_list =  track_list;
    }
    
    public String getArtistName() {
        return artist_name;
    }
    
    public void setArtistName(String artist) {
        this.artist_name = artist;
    }
    
    public String getRecordLabelName() {
        return record_label_name;
    }
    
    public void setRecordLabelName(String record) {
        this.record_label_name = record;
    }
    
    public String getPublicationDate() {
        return publication_date;
    }
    
    public void setPublicationDate(String date) {
        this.publication_date = date;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public String getTrackList() {
        return track_list;
    }
    
    public void setTrackList(String track_list) {
        this.track_list = track_list;
    }
    
    private void insertLP() {
        String query = "INSERT INTO `Lps` (publication_date, media_id, record_label_name, artist_name, genre, track_list) VALUES ('" 
                + publication_date + "', " + this.getId() + ", '" + record_label_name + "', '" + artist_name + "', '" + genre + "', '" + track_list + "');";
        try {
            ConnectSQL.sqlQueryUpdate(query);
        } catch (Exception e) {
            
        }
    }
    
    public void insert() {
        this.insertPhysical();
        this.insertLP();
    }
    
    private void updateLP() {
        String query = "UPDATE `Lps` SET publication_date = '" + publication_date + "', record_label_name = '" + record_label_name + "', artist_name = '" + artist_name + "', genre = '" + genre 
                + "', track_list = '" + track_list + "' WHERE media_id = " + this.getId();
        try {
            ConnectSQL.sqlQueryUpdate(query);
        } catch (Exception e) {
            
        }
      }
    
    public void update() {
        this.updatePhysical();
        this.updateLP();
    }
}
