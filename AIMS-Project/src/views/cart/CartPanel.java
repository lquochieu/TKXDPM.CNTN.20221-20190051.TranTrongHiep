/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.cart;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import aims.FormatNumber;
import controller.Cart.CartController;
import java.util.List;
import model.Cart.CartItem;
import model.User.User;

/**
 *
 * @author hsnt
 */
public class CartPanel extends JPanel {

    private CartList cartList;
    private DeliveryPanel deliveryPanel;
    private BillPanel billPanel;
    private JButton checkoutButton;
    private int totalBill;
    private User user;
    
    public CartPanel(User user) {
        this.user = user;
        setLayout(null);
        setSize(1000, 600);
        List<CartItem> cartItems = user.getCartItems();
        if (cartItems != null) {
            cartList = new CartList(user.getCartItems());
            deliveryPanel = new DeliveryPanel(user);
            billPanel = new BillPanel();
            checkoutButton = new JButton();
            totalBill = cartList.getTotalAll();

            changeBill();
//            cartList.setBounds(12, 12, CartList.MAX_WIDTH, CartList.MAX_HEIGHT);
//            if (cartList.getMediaCount() < 3) {
//                cartList.setBounds(12, 12, CartList.MAX_WIDTH, (CartItemPanel.HEIGHT + 20) * cartList.getMediaCount());
//            }
            changeSizeCartList();

            cartList.getchangeMedia().forEach((button) -> {
                button.addActionListener((ActionEvent e) -> {
                    if (button.getName().contains("+")) {
                        int select = JOptionPane.showConfirmDialog(null, "You're sure?");
                        if (select == JOptionPane.YES_OPTION) {
                            totalBill -= Integer.parseInt(button.getName());
                            CartItemPanel cartItemPanel = (CartItemPanel) button.getParent();
                            CartItem cartItem = cartItemPanel.getCartItem();
                            cartList.deleteObj(cartItemPanel);
                            CartController.deleteCartItem(cartItem);
                            changeSizeCartList();
                            this.updateUI();
                        }                        
                    } else {
                        int price = Integer.parseInt(button.getName());
                        CartItemPanel cartItemPanel = (CartItemPanel) button.getParent();
                        if(price < 0 && cartItemPanel.getQuantity() == 1){
                            CartItem cartItem = cartItemPanel.getCartItem();
                            cartList.deleteObj(cartItemPanel);
                            CartController.deleteCartItem(cartItem);              
                            changeSizeCartList();
                            this.updateUI();
                        }
                        if(price < 0 || cartItemPanel.isFlag())
                            totalBill += price;
                        
                    }
                    changeBill();
                });
            });

            deliveryPanel.setBounds(CartList.MAX_WIDTH + 30, 12, DeliveryPanel.WIDTH, DeliveryPanel.HEIGHT);
            billPanel.setBounds(CartList.MAX_WIDTH + 30, DeliveryPanel.WIDTH + 30 + 30, BillPanel.WIDTH, BillPanel.HEIGHT);

            checkoutButton.setText("Checkout");
            checkoutButton.setFocusPainted(false);
            checkoutButton.setBounds(CartList.MAX_WIDTH + 30, 12 + DeliveryPanel.HEIGHT + 30 + 30 + BillPanel.HEIGHT, DeliveryPanel.WIDTH, 35);

            

            add(checkoutButton);
            add(cartList);
            add(deliveryPanel);
            add(billPanel);
        } else {
            JOptionPane.showMessageDialog(null, "Cart empty");
        }
    }
    public DeliveryPanel getDelivery(){
        return deliveryPanel;
    }
    private void changeSizeCartList(){
        switch(cartList.getMediaCount()){
            case 1:
                cartList.setBounds(12, 12, CartList.MAX_WIDTH, CartItemPanel.HEIGHT + 20);
                break;
            case 2: 
                cartList.setBounds(12, 12, CartList.MAX_WIDTH, CartItemPanel.HEIGHT * 2 + 40);
                break;
            default:
                cartList.setBounds(12, 12, CartList.MAX_WIDTH, CartList.MAX_HEIGHT);
                break;
        }
    }
    public CartList getCartList(){
        return cartList;
    }
    public JButton getCheckOut(){
        return checkoutButton;
    }
    public int getTotal(){
        return totalBill;
    }
    private void changeBill() {
        billPanel.getTotalBillLabel().setName("" + totalBill);
        billPanel.getTotalBillLabel().setText(FormatNumber.formatString("" + totalBill) + " VND");
        int provisial = (int)(totalBill/1.1);
        billPanel.getProvisialLabel().setName("" + provisial);
        billPanel.getProvisialLabel().setText(FormatNumber.formatString("" + provisial) + " VND");
        
        billPanel.getVatLabel().setText(FormatNumber.formatString("" + (totalBill - provisial)) + " VND");
    }
}
