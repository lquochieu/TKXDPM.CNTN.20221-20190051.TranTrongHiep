/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.mediaAdmin;
import model.Media.*;
import controller.Media.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Toshiba T75
 */
public class mediaTest {
    JFrame jFrame = new JFrame();

    public mediaTest(JPanel panel) {
        
        jFrame.setSize(1200, 700);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
        panel.setBounds(0,0, panel.getWidth(), panel.getHeight());
        jFrame.add(panel);
        
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }
    
    public static void main(String[] args) { 
        ArrayList<Media> medias = Media.getAllMedia();
        new mediaTest(new MediaPanel(medias));
    }
}

// notes: chua cap nhat lai man hinh sau khi add, update hoac delete
// chua hien thi nhu thay noi