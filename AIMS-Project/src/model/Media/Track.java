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
public class Track {
    private int track_id;
    private String track_name;
    
    public Track(String track_name) {
        this.track_name = track_name;
    }
    
    public Track(int track_id, String track_name) {
        this.track_id = track_id;
        this.track_name = track_name;
    }
    
    public void setTrackId(int id) {
        this.track_id = id;
    }
    
    public int getTrackId() {
        return track_id;
    }
    
    public String getTrackName() {
        return track_name;
    }
    
    public void setTrackName(String track_name) {
        this.track_name = track_name;
    }
    
    public void insertTrack() {
        String query = "INSERT INTO `Tracks` VALUES (" + track_id + ", '" + track_name + "');";
        try {
            ConnectSQL.sqlQueryUpdate(query);
        } catch (Exception e) {
            
        }
    }
}
