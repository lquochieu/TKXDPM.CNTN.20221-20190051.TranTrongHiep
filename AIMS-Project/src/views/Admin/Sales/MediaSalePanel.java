/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Admin.Sales;

import aims.DateService;
import aims.FormatNumber;
import controller.Sale.SalesController;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import model.Media.Media;
import model.Sale.MediaSale;

/**
 *
 * @author hsnt
 */
public class MediaSalePanel extends javax.swing.JPanel {

    /**
     * Creates new form MediaSalePanel
     */
    public static int WIDTH = 788;
    public static int HEIGHT = 637;
    private List<MediaSaleItemPanel> mediaSaleItemPanels;
    private List<MediaSale> mediaSales;
    public MediaSalePanel(List<Media> medias) {
        initComponents();
        setSize(WIDTH, HEIGHT);
        mediaSales = new ArrayList<MediaSale>();
        MediaSaleList  mediaSaleList = new MediaSaleList(medias);
        mediaSaleList.setBounds(40, 80, mediaSaleList.getWidth(), mediaSaleList.getHeight());
        mediaSaleItemPanels = mediaSaleList.getMediaSaleItemPanels();
        changeValue(mediaSaleItemPanels);
        start_day.getModel().setDate(DateService.currentYear(), DateService.currentMonth(), DateService.currentDay());
        start_day.getModel().setSelected(true);
        end_day.getModel().setDate(DateService.currentYear(), DateService.currentMonth(), DateService.currentDay() + 7);
        end_day.getModel().setSelected(true);
        add(mediaSaleList);
    }
    public List<MediaSale> getMediaSales(){
        return mediaSales;
    }
    public  boolean checkDay(){
        if(DateService.compareDate(getEndDate() , getStartDate(), "yyyy/MM/dd") == 1){
            return true;
        }
        return false;
    }
    public JButton getSubmit() {
        return submit;
    }

    public List<MediaSaleItemPanel> getMediaSaleItemPanels() {
        return mediaSaleItemPanels;
    }
    
    public String getStartDate(){
        return this.start_day.getFormattedTextField().getText();
    }
    public String getEndDate(){
        return this.end_day.getFormattedTextField().getText();
    }
    private int getValueNumber(JTextField textField) {
        try {
            int value = Integer.parseInt(textField.getText());
            return value;
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    private void addPlaceholder(JTextField obj, String string){
        obj.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (obj.getText().equals(string)) {
                    obj.setText("");
                    obj.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (obj.getText().isEmpty()) {
                    obj.setForeground(Color.GRAY);
                    obj.setText(string);
                }
            }
        });
    }
    private void changeValue(List<MediaSaleItemPanel> mediaSaleItemPanels) {
        percentAll.getDocument().addDocumentListener(new DocumentListener() {
           
            public void changedUpdate(DocumentEvent e) {
                warn();
            }

            public void removeUpdate(DocumentEvent e) {
                warn();
            }

            public void insertUpdate(DocumentEvent e) {
                warn();
            }

            public void warn() {
//                System.out.println("Test");
                int precent = getValueNumber(percentAll);
                if (precent == 0) {
                    percentAll.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));                    
                    for(MediaSaleItemPanel mediaSaleItemPanel: mediaSaleItemPanels){
                        mediaSaleItemPanel.setSaleValue(precent);
                    }
                } else if (precent == -1) {
                    percentAll.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                } else {
                    percentAll.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));                    
                    for(MediaSaleItemPanel mediaSaleItemPanel: mediaSaleItemPanels){
                        mediaSaleItemPanel.setSaleValue(precent);
                    }
                }
            }
        });
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        percentAll = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        submit = new javax.swing.JButton();
        start_day = new org.jdatepicker.JDatePicker();
        end_day = new org.jdatepicker.JDatePicker();

        setMinimumSize(new java.awt.Dimension(788, 633));
        setLayout(null);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setText("Media Saling");
        add(jLabel1);
        jLabel1.setBounds(309, 12, 170, 21);

        jLabel2.setText("Start date: ");
        add(jLabel2);
        jLabel2.setBounds(40, 54, 76, 17);

        jLabel3.setText("End date: ");
        add(jLabel3);
        jLabel3.setBounds(309, 54, 69, 17);

        jLabel4.setText("Sale off all: ");
        add(jLabel4);
        jLabel4.setBounds(591, 54, 79, 17);

        percentAll.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        percentAll.setText("0");
        add(percentAll);
        percentAll.setBounds(682, 51, 41, 23);

        jLabel5.setText("%");
        add(jLabel5);
        jLabel5.setBounds(735, 54, 13, 17);

        submit.setText("Submit");
        add(submit);
        submit.setBounds(340, 590, 90, 35);
        add(start_day);
        start_day.setBounds(120, 50, 180, 40);
        add(end_day);
        end_day.setBounds(390, 50, 180, 37);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdatepicker.JDatePicker end_day;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField percentAll;
    private org.jdatepicker.JDatePicker start_day;
    private javax.swing.JButton submit;
    // End of variables declaration//GEN-END:variables
}
