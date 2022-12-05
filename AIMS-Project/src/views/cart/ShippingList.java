/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.cart;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.Cart.ShippingInfo;

/**
 *
 * @author hsnt
 */
public class ShippingList extends JPanel{

    public static final int WIDTH = ShippingInfoPanel.WIDTH + 40;
    private JPanel listShipInfo = new JPanel(new GridLayout(0,1));
    private ButtonGroup buttonGroup = new ButtonGroup();
    private List<ShippingInfoPanel> listInfo = new ArrayList<ShippingInfoPanel>();
    public ShippingList(List<ShippingInfo> shippingInfos) {
        setLayout(new GridLayout(1,0));
        add(new JScrollPane(listShipInfo));
        int i = 0;
        for(ShippingInfo shipping : shippingInfos){        
            ShippingInfoPanel shippingInfo = new ShippingInfoPanel(shipping);
            listShipInfo.add(shippingInfo);
            buttonGroup.add(shippingInfo.getRadioButton());
            if (i == 0 ){
                shippingInfo.getRadioButton().setSelected(true);
                i = i + 1;
            }
            listInfo.add(shippingInfo);   
        }
    }
    public ShippingInfo selectedInfo(){
        for(ShippingInfoPanel shippingInfoPanel: listInfo){
            if(shippingInfoPanel.getRadioButton().isSelected()){
                return shippingInfoPanel.getShippingInfo();
            }
        }
        return null;
    }
}
