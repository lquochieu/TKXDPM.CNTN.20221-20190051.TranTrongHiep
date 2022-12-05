package controller.User;

import static aims.AIMS.account;
import db.ConnectSQL;
import model.User.Account;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Cart.ShippingInfo;
import model.Cart.Ward;
import model.User.User;

public class UserController {

    public boolean CheckLogin(Account acc) {
        boolean check = false;
        String sql;
        sql = "SELECT* FROM Users WHERE email = '" + acc.getUsername() + "' AND PASSWORD = '" + acc.getPassword() + "'";
        try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);

            while (rs.next()) {
                check = true;
            }
        } catch (SQLException ex) {
        }
        return check;
    }

    public int CreateAccount(Account acc) {
        String sql = "INSERT INTO Users (email, password, `is_admin?`) VALUES ('" + acc.getUsername() + "', '" + acc.getPassword() + "', '" + acc.getLv() + "')";
        int insert = ConnectSQL.sqlUpdate(sql);

        return insert;
    }
    
    public int isAdmin(Account acc){
        int level = 0;
        String sql = "SELECT `is_admin?` FROM Users WHERE email = '" + acc.getUsername() + "'";
        
        try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);
            
            while (rs.next()) {
               level = rs.getInt(1);
            }
        } catch (SQLException ex) {
        }
        
        return level;
    }
    
    public int getUserID(Account acc)
    {
        int user_id = 1;
        String sql = "SELECT user_id from Users WHERE email = '" + acc.getUsername() + "'";
        
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);
            
            while (rs.next()) {
               user_id = rs.getInt(1);
            }
        } catch (SQLException ex) {
        }
        
        return user_id;
    }
    
    
    public Account getAccount(int user_id){
        Account acc = null;
        
        String sql = "Select * from Users where user_id = '" + user_id + "'";
        
        try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);
            while (rs.next())
            {
                acc = new Account(rs.getInt(1), rs.getInt(4), rs.getString(2), rs.getString(3));
            }
        }catch (SQLException ex) {
        
        }
        
        return acc;
    }
    
    public ArrayList<Account> getListAccount(){
        ArrayList<Account> arrAccount = new ArrayList<>();
    
        String sql = "SELECT * FROM `Users`";
        
        try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);

            while (rs.next()) {
                Account acc = new Account(rs.getInt(1), rs.getInt(4), rs.getString(2));
                arrAccount.add(acc);
                
            }
        } catch (SQLException ex) {
        }
       
        return arrAccount;
    }
    
    public boolean deleteAccount(ArrayList<Integer> ListAccount){
        boolean check = true;
        
        String sql;
        for (int user_id : ListAccount) {
            sql = "Delete From Users Where user_id = '" + user_id + "'";
            int returnValue = ConnectSQL.sqlUpdate(sql);
            if (returnValue == 0)
                return false;
        }
        return check;
    }
    
    public boolean updateAccount(Account acc)
    {
        String sql = "UPDATE Users SET password = '" + acc.getPassword() + "', `is_admin?` = '" + acc.getLv() +  "' WHERE user_id = '"  + acc.getId() + "'";
        
        int insert = ConnectSQL.sqlUpdate(sql);
        if (insert == 0)
            return false;
        return true;
        
    }
    
     public boolean blockAccount(ArrayList<Integer> ListAccount, int is_admin){
        boolean check = true;
        
        String sql;
        for (int user_id : ListAccount) {
            sql = "UPDATE Users SET `is_admin?` = '" + is_admin + "' Where user_id = '" + user_id + "'";
            int returnValue = ConnectSQL.sqlUpdate(sql);
            if (returnValue == 0)
                return false;
        }
        return check;
    }
     
      public ShippingInfo getShipInfor(int user_id) {
        ShippingInfo shipInfor = null;
        try {
            ResultSet rs = ConnectSQL.sqlQuery("select * from ShippingInfos where user_id = " + user_id);
            while (rs.next()) {
                shipInfor = new ShippingInfo(rs.getString("name"), rs.getString("phone"), Integer.parseInt(rs.getString("user_id")), new Ward(Integer.parseInt(rs.getString("ward_id"))));
            }
        } catch (SQLException ex) {
        }
        return shipInfor;
    }
      
    public static int getNumberOfActionsByCurrentAdmin() {
        String query = "SELECT COUNT(DISTINCT media_id) FROM `ActionsHistoryNew` WHERE user_id = " + account.getId() + " and action_id <> 1;";
        int result = 0;
        try {
            ResultSet rs = ConnectSQL.sqlQuery(query);
            if (rs.next()) {
                result = rs.getInt(1);
            }
        } catch (Exception e) {
            
        }
        return result;
    }
   
}
