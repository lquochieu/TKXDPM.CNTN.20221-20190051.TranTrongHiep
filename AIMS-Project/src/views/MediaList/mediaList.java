/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.MediaList;

import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;
import model.Media.Media;
import model.Media.MediaItem;

/**
 *
 * @author User
 */
public class mediaList extends JPanel{
       JPanel mediaListPanel;
      public  mediaList(ArrayList<MediaItem> listMedia) {
         setLayout(new GridLayout(0,1));
         setSize(1000,600);
//         mediaListPanel.setSize(840, 590);
//mediaListPanel = new JPanel(new GridLayout(5,4));
         mediaListPanel = new JPanel(new GridLayout(0,4));
         add(new JScrollPane(mediaListPanel));
        ArrayList<mediaItem> listItemPanel = new ArrayList<mediaItem>();
        for(int i = 0 ; i < listMedia.size(); i++)
        {
            mediaItem mediaItem = new mediaItem(listMedia.get(i));
            mediaItem.setSize(150, 200);
            listItemPanel.add(mediaItem);
        }  
        for(int i = 0 ; i < listItemPanel.size(); i++)
        {
            mediaListPanel.add(listItemPanel.get(i));

        }
    }
 
   
}
