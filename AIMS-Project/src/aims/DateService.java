/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aims;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hsnt
 */
public class DateService {
    public static int currentMonth(){
        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH);
    }
    public static int currentDay(){
        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }
    public static int currentYear(){
        Date date= new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR);
    }
    public static String now(String formatDate) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(formatDate);
        return sdf.format(cal.getTime());
    }
    public static int compareDate(String date1, String date2, String formatDate){
        try {
            SimpleDateFormat format = new SimpleDateFormat(formatDate);
            
            Date d1 = format.parse(date1);
            Date d2 = format.parse(date2);
            
            if (d1.compareTo(d2) < 0) {
                return -1;
            }
            if(d1.compareTo(d2) == 0){
                return 0;
            }
            return 1;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return 2;
        }        
    }
    public static Timestamp converTimestamp(String date,String format){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            Date parsedDate = dateFormat.parse(date);
            return new java.sql.Timestamp(parsedDate.getTime());
        } catch (Exception e) { 
            return null;
        }
    }
    public static void main(String[] args) {
//        System.out.println(converTimestamp("2020/12/24", "yyyy/MM/dd"));
    }
}
