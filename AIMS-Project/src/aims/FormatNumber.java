/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aims;

import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 *
 * @author hsnt
 */
public class FormatNumber {

    private static final DecimalFormat decimalFormat = new DecimalFormat();

    public FormatNumber() {
        decimalFormat.setGroupingUsed(true);
        decimalFormat.setGroupingSize(3);
    }

    public static String formatString(String string) {
        return decimalFormat.format(Integer.parseInt(string)) + "";
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean checkDateNumber(String date){
        if (date.matches("[0-9]{2}/[0-9]{2}")){
            LocalDate currentDate = LocalDate.now();
            int month = Integer.parseInt(date.split("/")[0]);
            int year = Integer.parseInt(date.split("/")[1]);
            int current_year = currentDate.getYear() % 100;
            int current_month = currentDate.getMonthValue();
            if(month >= 1 && month <= 12){   
                if(year > current_year){
                    return true;
                }else if(year == current_year){
                    if(month > current_month)
                        return true;
                }
            }
        }
        return false;
    }
    public static void main(String[] args) {

       
    }
}
