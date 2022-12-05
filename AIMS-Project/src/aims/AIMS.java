/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aims;

import db.ConnectSQL;
import javax.swing.JDialog;
import model.User.Account;
import views.HomeUser.PopUp;
import views.account.Login;


/**
 *
 * @author thanhhff
 */
public class AIMS {
    public static Account account = new Account();
    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
        // TODO code application logic here
        
//        Login login = new Login();
//        login.setVisible(true);
        JDialog jDialog = new JDialog();
        jDialog.setLayout(null);
        jDialog.setSize(PopUp.WIDTH,PopUp.HEIGHT);
        PopUp popUp = new PopUp();
        jDialog.add(popUp);
        jDialog.setLocationRelativeTo(null);
        jDialog.setUndecorated(true);
        popUp.setBounds(0, 0, PopUp.WIDTH, PopUp.HEIGHT);        
        jDialog.setVisible(true);        
        try {
            Thread.sleep(2000);
            jDialog.dispose();
            Home home = new Home();
            home.setLocationRelativeTo(null);
            home.setVisible(true);
        } catch (Exception e) {
        }
//        Home home = new Home();
//        home.setVisible(true);
        
    }
    
}
