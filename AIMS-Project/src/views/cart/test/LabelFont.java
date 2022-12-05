/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.cart.test;

/**
 *
 * @author hsnt
 */
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class LabelFont {

   public static void main(String[] args) {
      Font oldLabelFont = UIManager.getFont("Label.font");
      UIManager.put("Label.font", oldLabelFont.deriveFont(Font.PLAIN));

      JFrame f = new JFrame("LabelFont Test");
      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      f.getContentPane().setLayout(new FlowLayout());

      JLabel df = new JLabel("Default JLabel font");
      f.getContentPane().add(df);

      JLabel ef = new JLabel("Font explicitly set");
      ef.setFont(oldLabelFont);
      f.getContentPane().add(ef);

      f.pack();
      f.setVisible(true);
   }
}
