/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MediaList;

import controller.Search.SearchController;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Media.Media;
import model.Media.MediaItem;
import model.Media.PhysicalGood;

/**
 *
 * @author User
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
       MediaItem media = new MediaItem();
       media.setCategory(0);
       media.setPrice(15000);
       media.setTitle("conan");
       media.setId(3);
       
       mediaListPanel mediaItem = new  mediaListPanel();
     //    ArrayList<MediaItem> list = new ArrayList<MediaItem>();
    //     SearchController.SearchRandom(list);
       //  System.out.print(list.size());
       mediaTest test = new mediaTest(mediaItem);
    }
}
