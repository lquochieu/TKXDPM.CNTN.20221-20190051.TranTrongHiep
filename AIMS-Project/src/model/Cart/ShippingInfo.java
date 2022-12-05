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

/**
 *
 * @author hsnt
 */
public class ShippingInfo {

    private String name;
    private String phone;
    private String delivery_instruction;
    private int user_id;
    private int shipping_info_id;
    private Ward ward;
    private static int [] shipUrban = {1,2,3,4,5,6,7,8,9,19,21,268,760,761,762,763,764,765,766,767,768,769,770,771,772};
    public ShippingInfo(int shipping_info_id) {
        this.shipping_info_id = shipping_info_id;
        try {
            ResultSet rs = ConnectSQL.sqlQuery("select * from ShippingInfos where shipping_info_id = " + this.shipping_info_id);
            while (rs.next()) {
                this.ward = new Ward(Integer.parseInt(rs.getString("ward_id")));
                this.name = rs.getString("name");
                this.phone = rs.getString("phone");
                this.user_id = Integer.parseInt(rs.getString("user_id"));
                this.delivery_instruction = rs.getString("delivery_instruction");
            }
        } catch (SQLException ex) {

        }
    }
    
    public static boolean checkShip(int district_id){
        for(int i : shipUrban){
            if( i == district_id){
                return true;
            }
        }
        return false;
    }
    public ShippingInfo(String name, String phone, int user_id, Ward ward) {
        this.ward = ward;
        this.name = name;
        this.phone = phone;
        this.user_id = user_id;
    }

    public void setDelivery_instruction(String delivery_instruction) {
        this.delivery_instruction = delivery_instruction;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDelivery_instruction() {
        return delivery_instruction;
    }

    public int getUser_id() {
        return user_id;
    }

    public Ward getWardObject() {
        return ward;
    }

    public void create() {
        ConnectSQL.sqlUpdate(
                "Insert into ShippingInfos (`user_id`,`ward_id`,`name`,`phone`) "
                + "values (" + user_id + "," + ward.getWard_id() + ",'" + name + "','" + phone + "')");

    }
    public static List<String> getProvinces(){
        List<String> provinces = new ArrayList<String>();
        try {
            ResultSet rs = ConnectSQL.sqlQuery("Select * from Provinces");
            while (rs.next()) {
                String name = rs.getString("province_name");
                provinces.add(name);
            }
            
        } catch (SQLException ex) {
            
        }
        return provinces.isEmpty() ? null: provinces;
    }
    public static List<String> getDistricts(String province){
        List<String> districts = new ArrayList<String>();
        try {
            ResultSet rs = ConnectSQL.sqlQuery("Select * from Districts where province_id = " + getProvinceID(province));
            while (rs.next()) {
                String name = rs.getString("district_name");
                districts.add(name);
            }
            
        } catch (SQLException ex) {
            
        }
        return districts.isEmpty() ? null: districts;
    }
    public static List<String> getWards(String district){
        List<String> wards = new ArrayList<String>();
        try {
            ResultSet rs = ConnectSQL.sqlQuery("Select * from Wards where district_id = " + getDistrictID(district));
            while (rs.next()) {
                String name = rs.getString("ward_name");
                wards.add(name);
            }
            
        } catch (SQLException ex) {
            
        }
        return wards.isEmpty() ? null: wards;
    }
    public static int getProvinceID(String province){
        try {
            ResultSet rs = ConnectSQL.sqlQuery("Select province_id from Provinces where province_name like '" + province + "'");
            String idProvince = "";
            while (rs.next()) {
                idProvince = rs.getString("province_id");
            }
            return Integer.parseInt(idProvince);
        } catch (SQLException ex) {
            
        }
        return 0;
    }
    public static int getDistrictID(String district){
        try {
            ResultSet rs = ConnectSQL.sqlQuery("Select district_id from Districts where district_name like '" + district + "'");
            String idDistrict = "";
            while (rs.next()) {
                idDistrict = rs.getString("district_id");
            }
            return Integer.parseInt(idDistrict);
        } catch (SQLException ex) {
            
        }
        return 0;
    }
}
