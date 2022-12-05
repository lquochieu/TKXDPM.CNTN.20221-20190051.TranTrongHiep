/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aims;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hsnt
 */
public class PaymentService {

    public static boolean payment(String cart_number, int totalBill){
        List<String> data =  new ArrayList<String>();
        boolean flag = false;
        try {
            File myObj = new File("src/aims/card.txt");
            try (Scanner myReader = new Scanner(myObj)) {
                while (myReader.hasNextLine()) {
                    String tmp = myReader.nextLine();
                    if (!tmp.equals("")) {
                        String[] splitString = tmp.split(" ");
                        if(splitString[0].equals(cart_number)){
                            int balance = Integer.parseInt(splitString[1]);
                            if(balance >= totalBill){
                                balance -= totalBill;
                                flag = true;
                            }
                            data.add(splitString[0] + " " + balance); 
                        }else{
                            data.add(tmp);
                        }                     
                    }        
                }
            }
            try (FileWriter myWriter = new FileWriter("src/aims/card.txt")) {
                for(String tmp : data){
                    myWriter.write(tmp);
                }
                myWriter.close();
            }
            return flag;
        } catch (Exception e) {
            return flag;
        }
    }
    public static boolean refund(String cart_number, int totalBill){
        List<String> data =  new ArrayList<String>();
        boolean flag = false;
        try {
            File myObj = new File("src/aims/card.txt");
            try (Scanner myReader = new Scanner(myObj)) {
                while (myReader.hasNextLine()) {
                    String tmp = myReader.nextLine();
                    if (!tmp.equals("")) {
                        String[] splitString = tmp.split(" ");
                        if(splitString[0].equals(cart_number)){
                            int balance = Integer.parseInt(splitString[1]);
                            balance += totalBill;
                            flag = true;
                            data.add(splitString[0] + " " + balance); 
                        }else{
                            data.add(tmp);
                        }                     
                    }        
                }
            }
            try (FileWriter myWriter = new FileWriter("src/aims/card.txt")) {
                for(String tmp : data){
                    myWriter.write(tmp);
                }
                myWriter.close();
            }
            return flag;
        } catch (Exception e) {
            return flag;
        }
    }
    public static void reset(){
        List<String> data =  new ArrayList<String>();
        try {
            File myObj = new File("src/aims/card.txt");
            try (Scanner myReader = new Scanner(myObj)) {
                while (myReader.hasNextLine()) {
                    String tmp = myReader.nextLine();
                    if (!tmp.equals("")) {
                        String[] splitString = tmp.split(" ");
                        data.add(splitString[0] + " " + 1000000);
                    }        
                }
            }
            try (FileWriter myWriter = new FileWriter("src/aims/card.txt")) {
                for(String tmp : data){
                    myWriter.write(tmp);
                }
                myWriter.close();
            } catch (IOException ex) {
                Logger.getLogger(PaymentService.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException e) {
        }
    }
}
