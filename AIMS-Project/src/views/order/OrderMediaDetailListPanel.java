/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.order;

import java.awt.GridLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.Cart.OrderItem;

/**
 *
 * @author hsnt
 */
public class OrderMediaDetailListPanel extends JPanel{
    
    public static int WIDTH = 700;
    public static int HEIGHT = 460;
    
    private JPanel orderJPanel;

    public OrderMediaDetailListPanel(List<OrderItem> orderItems) {
        setBorder(null);
        setLayout(new GridLayout(1,0));
        orderJPanel = new JPanel(new GridLayout(0,1));
        add(new JScrollPane(orderJPanel));
        for(OrderItem orderItem : orderItems){
            OrderMediaDetailPanel orderMediaDetailPanel = new OrderMediaDetailPanel(orderItem);
            this.addObj(orderMediaDetailPanel); 
        } 
        
    }
    public void addObj(JPanel object){
        orderJPanel.add(object);
    }
}
