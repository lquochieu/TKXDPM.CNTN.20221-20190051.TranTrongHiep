/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.mediaAdmin;

import views.Admin.Sales.*;
import java.awt.GridLayout;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.Media.Media;

/**
 *
 * @author hsnt
 */
public class MediaList extends JPanel{
    
    public static int WIDTH = 700;
    public static int HEIGHT = 500;
    private JPanel mediaJPanel;
    private List<MediaItemPanel> mediaItemPanels;

    public MediaList(List<Media> medias) {
        setLayout(new GridLayout(1,0));
        mediaJPanel = new JPanel(new GridLayout(0,1));
        mediaItemPanels = new ArrayList<MediaItemPanel>();
        add(new JScrollPane(mediaJPanel));
        for(Media media : medias){
            MediaItemPanel mediaItemPanel = new MediaItemPanel(media);
            mediaItemPanels.add(mediaItemPanel);
            this.addObj(mediaItemPanel); 
        } 
        setSize(WIDTH,HEIGHT);
    }
    public void addObj(JPanel object){
        mediaJPanel.add(object);
    }
    public List<MediaItemPanel> getMediaItemPanels(){
        return mediaItemPanels;
    }
    
}
