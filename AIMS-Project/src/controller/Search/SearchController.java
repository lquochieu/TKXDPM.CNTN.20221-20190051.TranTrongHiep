/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.Search;

import db.ConnectSQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Media.Media;
import model.Media.MediaItem;


/**
 *
 * @author User
 */
public class SearchController {
    public static List<Media> searchByTitle(String title){
        return Media.searchByTitle(title);
    }
    public static void SearchRandom(ArrayList<MediaItem> list)
    {   
        
        String sql;
        sql = "SELECT* FROM Medias ORDER BY media_id DESC LIMIT 0,20";
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);            
            while (rs.next()) {           
                MediaItem media = new MediaItem();
                media.setTitle(rs.getString(2));
                media.setPrice(rs.getInt(4));
                media.setValue(rs.getInt(3));
                media.setCategory(rs.getInt(5));
                media.setId(rs.getInt(1));
                media.setSalePercent(0);
                media.setImagePath(rs.getString(6));
                list.add(media);
            }
           
        } catch (SQLException ex) {
        }
         sql = "SELECT* FROM MediaSales";
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);           
            while (rs.next()) {
               for(int i = 0 ; i < list.size(); i++)
               {
                 if(list.get(i).getId() == rs.getInt(2))
                     list.get(i).setSalePercent(rs.getInt(4));
               }
            }
           
        } catch (SQLException ex) {
        }
         sql = "SELECT * FROM PhysicalGoods";
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);           
            while (rs.next()) {
               for(int i = 0 ; i < list.size(); i++)
               {
                 if(list.get(i).getId() == rs.getInt("media_id"))
                     list.get(i).setQuantity(rs.getInt("quantity"));
               }
            }
           
        } catch (SQLException ex) {
        }
     }
     public static void SearchBook(ArrayList<MediaItem> list)
     {
        String sql;
        sql = "SELECT* FROM Medias Join Books Where Medias.media_id = Books.media_id LIMIT 0,20"; 
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);
            
            while (rs.next()) {
                MediaItem media = new MediaItem(){};
                media.setTitle(rs.getString("title"));
                media.setPrice(rs.getInt(4));
                media.setValue(rs.getInt(3));
                media.setCategory(rs.getInt(5));
                media.setId(rs.getInt(1));
                media.setSalePercent(0);
                media.setImagePath(rs.getString(6));
                list.add(media);
            }
           
        } catch (SQLException ex) {
        }
          sql = "SELECT* FROM MediaSales";
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);           
            while (rs.next()) {
               for(int i = 0 ; i < list.size(); i++)
               {
                 if(list.get(i).getId() == rs.getInt(2))
                     list.get(i).setSalePercent(rs.getInt(4));
               }
            }
           
        } catch (SQLException ex) {
        }
         sql = "SELECT* FROM Physicalgoods";
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);           
            while (rs.next()) {
               for(int i = 0 ; i < list.size(); i++)
               {
                 if(list.get(i).getId() == rs.getInt(9))
                     list.get(i).setQuantity(rs.getInt(10));
               }
            }
           
        } catch (SQLException ex) {
        }
     }

    public static void SearchCd(ArrayList<MediaItem> list) {
         String sql;
        sql = "SELECT* FROM Medias Join CDs Where Medias.media_id = CDs.media_id LIMIT 0,20"; 
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);
            
            while (rs.next()) {
                MediaItem media = new MediaItem(){};
                media.setTitle(rs.getString(2));
                media.setPrice(rs.getInt(4));
                media.setValue(rs.getInt(3));
                media.setCategory(rs.getInt(5));
                media.setId(rs.getInt(1));
                media.setSalePercent(0);
                media.setImagePath(rs.getString(6));
                list.add(media);
            }
           
        } catch (SQLException ex) {
        }
          sql = "SELECT* FROM Mediasales";
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);           
            while (rs.next()) {
               for(int i = 0 ; i < list.size(); i++)
               {
                 if(list.get(i).getId() == rs.getInt(2))
                     list.get(i).setSalePercent(rs.getInt(4));
               }
            }
           
        } catch (SQLException ex) {
        }
         sql = "SELECT* FROM Physicalgoods";
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);           
            while (rs.next()) {
               for(int i = 0 ; i < list.size(); i++)
               {
                 if(list.get(i).getId() == rs.getInt(9))
                     list.get(i).setQuantity(rs.getInt(10));
               }
            }
           
        } catch (SQLException ex) {
        }
    }

    public static void SearchDvd(ArrayList<MediaItem> list) {
         String sql;
        sql = "SELECT* FROM medias Join dvds Where medias.media_id = dvds.media_id LIMIT 0,20"; 
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);
            
            while (rs.next()) {
                MediaItem media = new MediaItem();
                media.setTitle(rs.getString(2));
                media.setPrice(rs.getInt(4));
                media.setValue(rs.getInt(3));
                media.setCategory(rs.getInt(5));
                media.setId(rs.getInt(1));
                media.setSalePercent(0);
                media.setImagePath(rs.getString(6));
                list.add(media);
            }
           
        } catch (SQLException ex) {
        }
          sql = "SELECT* FROM Mediasales";
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);           
            while (rs.next()) {
               for(int i = 0 ; i < list.size(); i++)
               {
                 if(list.get(i).getId() == rs.getInt(2))
                     list.get(i).setSalePercent(rs.getInt(4));
               }
            }
           
        } catch (SQLException ex) {
        }
         sql = "SELECT* FROM Physicalgoods";
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);           
            while (rs.next()) {
               for(int i = 0 ; i < list.size(); i++)
               {
                 if(list.get(i).getId() == rs.getInt(9))
                     list.get(i).setQuantity(rs.getInt(10));
               }
            }
           
        } catch (SQLException ex) {
        }
    }

    public static void SearchLp(ArrayList<MediaItem> list) {
         String sql;
        sql = "SELECT* FROM medias Join lps Where medias.media_id = lps.media_id LIMIT 0,20"; 
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);
            
            while (rs.next()) {
                MediaItem media = new MediaItem(){};
                media.setTitle(rs.getString(2));
                media.setPrice(rs.getInt(4));
                media.setValue(rs.getInt(3));
                media.setCategory(rs.getInt(5));
                media.setId(rs.getInt(1));
                media.setSalePercent(0);
                media.setImagePath(rs.getString(6));
                list.add(media);
            }
           
        } catch (SQLException ex) {
        }
          sql = "SELECT* FROM Mediasales";
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);           
            while (rs.next()) {
               for(int i = 0 ; i < list.size(); i++)
               {
                 if(list.get(i).getId() == rs.getInt(2))
                     list.get(i).setSalePercent(rs.getInt(4));
               }
            }
           
        } catch (SQLException ex) {
        }
         sql = "SELECT* FROM Physicalgoods";
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);           
            while (rs.next()) {
               for(int i = 0 ; i < list.size(); i++)
               {
                 if(list.get(i).getId() == rs.getInt(9))
                     list.get(i).setQuantity(rs.getInt(10));
               }
            }
           
        } catch (SQLException ex) {
        }
    }
    public static void SearchUser(ArrayList<MediaItem> list,String search) {
         String sql;
        sql = "SELECT* FROM Medias  Where medias.title LIKE '%" + search + "%'LIMIT 0,20";
        System.out.print(search);
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);
            
            while (rs.next()) {
                MediaItem media = new MediaItem(){};
                media.setTitle(rs.getString(2));
                media.setPrice(rs.getInt(4));
                media.setValue(rs.getInt(3));
                media.setCategory(rs.getInt(5));
                media.setId(rs.getInt(1));
                media.setSalePercent(0);
                media.setImagePath(rs.getString(6));
                list.add(media);
            }
           
        } catch (SQLException ex) {
        }
          sql = "SELECT* FROM Mediasales";
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);           
            while (rs.next()) {
               for(int i = 0 ; i < list.size(); i++)
               {
                 if(list.get(i).getId() == rs.getInt(2))
                     list.get(i).setSalePercent(rs.getInt(4));
               }
            }
           
        } catch (SQLException ex) {
        }
         sql = "SELECT* FROM Physicalgoods";
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);           
            while (rs.next()) {
               for(int i = 0 ; i < list.size(); i++)
               {
                 if(list.get(i).getId() == rs.getInt(9))
                     list.get(i).setQuantity(rs.getInt(10));
               }
            }
           
        } catch (SQLException ex) {
        }
    }
      public static void SearchNext(ArrayList<MediaItem> list,String search,int status, int page) {
         String sql;
         switch(status)
         {
              case 1:
               sql =  "SELECT* FROM Medias ORDER BY media_id DESC LIMIT "+ page*20 + ",20";
               break;
              case 2:
               sql =  "SELECT* FROM medias  Where medias.title LIKE '%" + search + "%'LIMIT "+ page*20 + ",20";
               break;  
              case 3:
               sql =  "SELECT* FROM medias Join books Where medias.media_id = books.media_id LIMIT "+ page*20 + ",20";
               break;
               case 5:
               sql =  "SELECT* FROM medias Join dvds Where medias.media_id = dvds.media_id LIMIT "+ page*20 + ",20";
               break;
               case 4:
               sql =  "SELECT* FROM medias Join cds Where medias.media_id = cds.media_id LIMIT "+ page*20 + ",20";
               break;
               case 6:
               sql =  "SELECT* FROM medias Join lps Where medias.media_id = lps.media_id LIMIT "+ page*20 + ",20";
               break;
               default:
               sql = "";
               break;
         }
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);
            
            while (rs.next()) {
                MediaItem media = new MediaItem(){};
                media.setTitle(rs.getString(2));
                media.setPrice(rs.getInt(4));
                media.setValue(rs.getInt(3));
                media.setCategory(rs.getInt(5));
                media.setId(rs.getInt(1));
                media.setSalePercent(0);
                media.setImagePath(rs.getString(6));
                list.add(media);
            }
           
        } catch (SQLException ex) {
        }
          sql = "SELECT* FROM MediaSales";
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);           
            while (rs.next()) {
               for(int i = 0 ; i < list.size(); i++)
               {
                 if(list.get(i).getId() == rs.getInt(2))
                     list.get(i).setSalePercent(rs.getInt(4));
               }
            }
           
        } catch (SQLException ex) {
        }
         sql = "SELECT* FROM Physicalgoods";
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);           
            while (rs.next()) {
               for(int i = 0 ; i < list.size(); i++)
               {
                 if(list.get(i).getId() == rs.getInt(9))
                     list.get(i).setQuantity(rs.getInt(10));
               }
            }
           
        } catch (SQLException ex) {
        }
    }
      public static void SearchPrevious(ArrayList<MediaItem> list,String search,int status, int page) {
         String sql;
         if(page == 1) sql = "";
         else
         {
         switch(status)
         {
              case 1:
               sql =  "SELECT* FROM Medias ORDER BY media_id DESC LIMIT "+ (page-2)*20 + ",20";
               break;
              case 2:
               sql =  "SELECT* FROM medias  Where medias.title LIKE '%" + search + "%'LIMIT "+ (page-2)*20 + ",20";
               break;  
              case 3:
               sql =  "SELECT* FROM medias Join books Where medias.media_id = books.media_id LIMIT "+ (page-2)*20 + ",20";
               break;
               case 5:
               sql =  "SELECT* FROM medias Join dvds Where medias.media_id = dvds.media_id LIMIT "+ (page-2)*20 + ",20";
               break;
               case 4:
               sql =  "SELECT* FROM medias Join cds Where medias.media_id = cds.media_id LIMIT "+ (page-2)*20 + ",20";
               break;
               case 6:
               sql =  "SELECT* FROM medias Join lps Where medias.media_id = lps.media_id LIMIT "+ (page-2)*20 + ",20";
               break;
               default:
               sql = "";
               break;
         }
         }
         
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);
            
            while (rs.next()) {
                MediaItem media = new MediaItem(){};
                media.setTitle(rs.getString(2));
                media.setPrice(rs.getInt(4));
                media.setValue(rs.getInt(3));
                media.setCategory(rs.getInt(5));
                media.setId(rs.getInt(1));
                media.setSalePercent(0);
                media.setImagePath(rs.getString(6));
                list.add(media);
            }
           
        } catch (SQLException ex) {
        }
          sql = "SELECT* FROM Mediasales";
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);           
            while (rs.next()) {
               for(int i = 0 ; i < list.size(); i++)
               {
                 if(list.get(i).getId() == rs.getInt(2))
                     list.get(i).setSalePercent(rs.getInt(4));
               }
            }
           
        } catch (SQLException ex) {
        }
         sql = "SELECT* FROM Physicalgoods";
         try {
            ResultSet rs = ConnectSQL.sqlQuery(sql);           
            while (rs.next()) {
               for(int i = 0 ; i < list.size(); i++)
               {
                 if(list.get(i).getId() == rs.getInt(9))
                     list.get(i).setQuantity(rs.getInt(10));
               }
            }
           
        } catch (SQLException ex) {
        }
    }
}
