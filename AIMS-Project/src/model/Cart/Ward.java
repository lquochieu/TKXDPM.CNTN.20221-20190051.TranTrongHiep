package model.Cart;

import db.ConnectSQL;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hsnt
 */
public class Ward {
    private int ward_id;
    private String ward_name;
    private Ward(){
        
    }
    public Ward(int ward_id) {
        this.ward_id = ward_id;
        try {
            ResultSet rs = ConnectSQL.sqlQuery("select * from Wards where ward_id = " + ward_id);
            while (rs.next()) {
                this.ward_name = rs.getString("ward_name");
            }
        } catch (SQLException ex) {            
        }
    }

    public int getWard_id() {
        return ward_id;
    }
    
    public static Ward getWardbyName(String name){
        try {
            ResultSet rs = ConnectSQL.sqlQuery("select * from Wards where ward_name = '" + name + "'");
            while (rs.next()) {
                Ward ward = new Ward();
                ward.ward_id = Integer.parseInt(rs.getString("ward_id"));
                ward.ward_name = rs.getString("ward_name");
                return ward;
            }
            return null;
        } catch (SQLException ex) {
            return null;
        }
    }
    public String getWard() {
        return this.ward_name;
    }
    public String getDistrict() {
        try {
            ResultSet rs = ConnectSQL.sqlQuery("select * from Wards join Districts using (district_id) where ward_id = " + ward_id);
            while (rs.next()) {
                return rs.getString("district_name");
            }
            return null;
        } catch (SQLException ex) {
            return null;
        }
    }
    public int getDistrictID() {
        try {
            ResultSet rs = ConnectSQL.sqlQuery("select * from Wards join Districts using (district_id) where ward_id = " + ward_id);
            while (rs.next()) {
                return Integer.parseInt(rs.getString("district_id"));
            }
            return -1;
        } catch (SQLException ex) {
            return -1;
        }
    }
    public String getProvince(){
        try {
            ResultSet rs = ConnectSQL.sqlQuery("select * from Wards join Districts using (district_id) join Provinces using (province_id) where ward_id = " + ward_id);
            while (rs.next()) {
                return rs.getString("province_name");
            }
            return null;
        } catch (SQLException ex) {
            return null;
        }
    }
    public void save(){
        
    }
}
