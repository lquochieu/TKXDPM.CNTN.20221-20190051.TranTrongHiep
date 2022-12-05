/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Cart;

import aims.PaymentService;
import model.Cart.Order;
/**
 *
 * @author hsnt
 */
public class OrderController {
    
    public static boolean refund(Order order){
        if(PaymentService.refund(order.getCard_number(), order.getTotalBill() + order.getShip_fee())){
            order.cancel();
            return true;
        }
        return false;
    }
}
