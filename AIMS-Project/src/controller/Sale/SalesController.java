/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Sale;

import java.util.List;
import model.Sale.MediaSale;
import model.Sale.Sale;

/**
 *
 * @author hsnt
 */
public class SalesController {
    public static void create(List<MediaSale> mediaSales, String start_day, String end_day){
        Sale sale = new Sale(start_day, end_day, mediaSales);
    }
}
