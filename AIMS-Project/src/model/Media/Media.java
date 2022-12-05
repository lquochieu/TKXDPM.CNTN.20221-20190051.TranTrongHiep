/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Media;

import db.ConnectSQL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static aims.AIMS.account;
import controller.User.UserController;
import javax.swing.JOptionPane;

/**
 *
 * @author hsnt
 */
public abstract class Media {
    private int id;
    private String title;
    private int value;
    private int price;
    private int sale_percent;
    private int category_id;
    private String image_path;
    
    public Media() {
        title = "";
        price = 0;
        value = 0;
        sale_percent = 0;
        category_id = 0;
        image_path = "";
    }
    
    public Media(int id, String title, int value, int price, int category, String image_path) {
        this.id = id;
        this.title = title;
        this.value = value;
        this.price = price;
        this.sale_percent = 0;
        this.category_id = category;
        this.image_path = image_path;
    }
    
//    (title, value, price, sale_percent, category, image_path)
    public Media(String title, int value, int price, int sale_percent, int category, String image_path) {
        this.title = title;
        this.value = value;
        this.price = price;
        this.sale_percent = 0;
        this.category_id = category;
        this.image_path = image_path;
    }
    
     public Media(int id, String title, int value, int price, int category) {
        this.id = id;
        this.title = title;
        this.value = value;
        this.price = price;
        this.sale_percent = 0;
        this.category_id = category;
    }
    public Media(int id, String title, int value, int price, int sale_percent, int category, String image_path) {
        this.id = id;
        this.title = title;
        this.value = value;
        this.price = price;
        this.sale_percent = sale_percent;
        this.category_id = category;
        this.image_path = image_path;
    }
    public void updateMedia(Media media)
    {
        this.id = media.getId();
        this.title = media.getTitle();
        this.value = media.getValue();
        this.price = media.getPrice();
        this.sale_percent = media.getSalePercent();
        this.category_id = media.getCategoryId();
    }
    public static String getTitlebyID(int media_id){
        ResultSet rs = ConnectSQL.sqlQuery("Select * from Medias where media_id = " + media_id);
        try {
            while(rs.next()){
                return rs.getString("title");
            }
            return "NaN";
        } catch (SQLException ex) {
            return null;
        }
    }
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSalePercent() {
        return sale_percent;
    }

    public void setSalePercent(int sale_percent) {
        this.sale_percent = sale_percent;
    }

    public int getCategoryId() {
        return category_id;
    }

    public void setCategory(int category_id) {
        this.category_id = category_id;
    }
    public static List<Media> searchByTitle(String title){
        List<Media> medias = getAllMedia();
        List<Media> result =  new ArrayList<Media>();
        for(Media media: medias){
            if(media.title.toLowerCase().contains(title.toLowerCase())){
                result.add(media);
            }
        }
        return result.size() == 0 ? null: result;
    }
    public String getImagePath() {
        return image_path;
    }
    
    public void setImagePath(String image_path) {
        this.image_path = image_path;
    }
    public static String getImagePathbyID(int media_id){
        String result = "";
        try {
            ResultSet rs = ConnectSQL.sqlQuery("Select image_path from Medias where media_id = " + media_id );
            while(rs.next()){
                result = rs.getString("image_path");
            }
            return result;
        } catch (Exception e) {
            return result;
        }
    }
    public static int getNumberOfMedia() {
        try {
            int result = 0;
            String query = "SELECT COUNT(*) FROM `Medias`;";
            ResultSet rs = ConnectSQL.sqlQuery(query);

            if (rs.next()) {
                result = rs.getInt(1);
            }

            return result;
        } catch (Exception e) {
            return 0;
        }
        
    }
    
    public static int getMaxID() {
        try {
            int result = 0;
            String query = "SELECT MAX(media_id) FROM `Medias`;";
            ResultSet rs = ConnectSQL.sqlQuery(query);
            
            if (rs.next()) {
                result = rs.getInt(1);
            }
            return result;
        } catch (Exception e) {
            return 0;
        }
    }
    public static int getCountMedia() {
        try {
            int result = 0;
            String query = "SELECT COUNT(media_id) FROM `Medias`;";
            ResultSet rs = ConnectSQL.sqlQuery(query);
            
            if (rs.next()) {
                result = rs.getInt(1);
            }
            return result;
        } catch (Exception e) {
            return 0;
        }
    }
    
    protected int insertMedia() {
        String media_query = "INSERT INTO `Medias` (title, value, price, sale_percent, category_id, image_path) VALUES ('" + title + "', " + value + ", " + price + ", " + sale_percent
                                + ", " + category_id + ", '" + image_path + "');";
        
        try {
            int result = ConnectSQL.sqlUpdate(media_query);
            String history_query = "INSERT INTO `ActionsHistoryNew` (action_id, user_id, date, media_id) values (1, " + account.getId() + ", now(), " + getMaxID() + ");";
            try {
                ConnectSQL.sqlQueryUpdate(history_query);
            } catch (Exception e) {
                
            }
            return getMaxID();
        } catch (Exception e) {
            return -1;
        }
    }
    
    public abstract void insert();
    
    protected void updateMedia() {
        String media_query = "UPDATE `Medias` SET title = '" + getTitle() + "', value = " + getValue() + ", price = " + getPrice() + 
                ", category_id = " + getCategoryId() + ", image_path = '" + getImagePath() + "' WHERE media_id = " + this.getId() + ";";
        
        try {
            ConnectSQL.sqlQueryUpdate(media_query);
            int hour;
            hour = getLatestEditTimeOfMedia(this.getId());
            if (hour >= 2) {
                String history_query = "INSERT INTO `ActionsHistoryNew` (action_id, user_id, date, media_id) values (2, " + account.getId() + ", now(), " + this.getId() + ");";
                try {
                    ConnectSQL.sqlQueryUpdate(history_query);
                } catch (Exception e) {

                }
            } else {
                System.out.println("Time from adding: " + hour);
            }
            
//        System.out.println("User id: " + account.getId());
        } catch (Exception e) {
        }
        
//        System.out.println("Number of actions: " + UserController.getNumberOfActionsByCurrentAdmin());
    }
    
    public abstract void update();
    
    public static void deleteMedia(int media_id) {
        String delete_query = "DELETE FROM `Medias` WHERE media_id = " + media_id + ";";
        try {
            ConnectSQL.sqlQueryUpdate(delete_query);
            String history_query = "INSERT INTO `ActionsHistoryNew` (action_id, user_id, date, media_id) values (3, " + account.getId() + ", now(), " + media_id + ");";
            try {
                ConnectSQL.sqlQueryUpdate(history_query);
            } catch (Exception e) {
                
            }
        } catch (Exception e) {
            
        }
    }
    
    public static void deleteListMedias(ArrayList<Integer> media_ids) {
        String delete_query = "DELETE FROM `Medias` WHERE media_id = ";
        String real_query;
        
        for (int media_id: media_ids) {
            try {
                real_query = delete_query + media_id;
                ConnectSQL.sqlQueryUpdate(real_query);
                String history_query = "INSERT INTO `ActionsHistoryNew` (action_id, user_id, date, media_id) values (3, " + account.getId() + ", now(), " + media_id + ");";
                try {
                    ConnectSQL.sqlQueryUpdate(history_query);
                } catch (Exception e) {

                }
            } catch (Exception e) {
                
            }
        }
    }
    
    public static int getLatestEditTimeOfMedia(int media_id) {
        int result = 0;
        String query = "SELECT TIMEDIFF(now(), date) as diff FROM ActionsHistoryNew WHERE action_id = 1 AND media_id = " + media_id;
        try {
            ResultSet rs = ConnectSQL.sqlQuery(query);
            if (rs.next()) {
                String datetime = rs.getString("diff");
                result = Integer.parseInt(datetime.split(":")[0]);
            }
        } catch (Exception e) {
            
        }
        return result;
    }
    
    public static ArrayList<Media> getMediasByTitle(String searchText) {
        ArrayList<Media> medias = new ArrayList<Media>();
        String query = "SELECT * FROM `Medias`, `PhysicalGoods` WHERE `Medias`.`media_id` = `PhysicalGoods`.`media_id` AND title LIKE '%" + searchText + "%';";
        try {
            ResultSet rs = ConnectSQL.sqlQuery(query);
            while (rs.next()) {
                Media media;
                int media_id = rs.getInt("media_id");
                String title = rs.getString("title");
                int value = rs.getInt("value");
                int price = rs.getInt("price");
                int category_id = rs.getInt("category_id");
                String image_path = rs.getString("image_path");
                int sale_percent = 0;
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                String input_day = rs.getString("input_day").split(" ")[0];
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                int depth = rs.getInt("depth");
                int weight = rs.getInt("weight");
                String barcode = rs.getString("barcode");
                
                switch (category_id) {
                    case 1:
                        String book_query = "SELECT * FROM `Books` WHERE media_id = " + media_id + ";";
                        String book_publication_date = null;
                        int page_number = -1;
                        int cover_type_id = -1;
                        String publisher_name = null;
                        int language_id = -1;
                        String author_name = null;
                        String genre = null;
                        try {
                            ResultSet book_rs = ConnectSQL.sqlQuery(book_query);
                            if (book_rs.next()) {
                                book_publication_date = book_rs.getString("publication_date").split(" ")[0];
                                page_number = book_rs.getInt("page_number");
                                cover_type_id = book_rs.getInt("cover_type_id");
                                publisher_name = book_rs.getString("publisher_name");
                                language_id = book_rs.getInt("language_id");
                                author_name = book_rs.getString("author_name");
                                genre = book_rs.getString("genre");
                            }
                        } catch (Exception e) {
                            
                        }
                        media = new Book(media_id, title, value, price, sale_percent, category_id, image_path, barcode, description, quantity, input_day, width, height, depth, weight, author_name, cover_type_id, publisher_name, book_publication_date, page_number, language_id, genre);
                        medias.add(media);
                        break;
                    case 2:
                        String dvd_query = "SELECT * FROM `DVDs` WHERE media_id = " + media_id + ";";
                        String dvd_publication_date = null;
                        int runtime = -1;
                        String subtitle = null;
                        int dvd_language_id = -1;
                        String studio_name = null;
                        int dvd_type_id = -1;
                        String writer_name = null;
                        try {
                            ResultSet dvd_rs = ConnectSQL.sqlQuery(dvd_query);
                            if (dvd_rs.next()) {
                                dvd_publication_date = dvd_rs.getString("publication_date").split(" ")[0];
                                runtime = dvd_rs.getInt("runtime");
                                subtitle = dvd_rs.getString("subtitle");
                                dvd_language_id = dvd_rs.getInt("language_id");
                                studio_name = dvd_rs.getString("studio_name");
                                dvd_type_id = dvd_rs.getInt("dvd_type_id");
                                writer_name = dvd_rs.getString("writer_name");
                            }
                        } catch (Exception e) {
                            
                        }
                        media = new DVD(media_id, title, value, price, sale_percent, category_id, image_path, barcode, description, quantity, input_day, width, height, depth, weight, dvd_type_id, writer_name, runtime, studio_name, dvd_language_id, subtitle, dvd_publication_date);
                        medias.add(media);
                        break;
                    case 3:
                        String cd_query = "SELECT * FROM `CDs` WHERE media_id = " + media_id + ";";
                        String cd_publication_date = null;
                        String record_label_name = null;
                        String artist_name = null;
                        String cd_genre = null;
                        String track_list = null;
                        try {
                            ResultSet cd_rs = ConnectSQL.sqlQuery(cd_query);
                            if (cd_rs.next()) {
                                cd_publication_date = cd_rs.getString("publication_date").split(" ")[0];
                                record_label_name = cd_rs.getString("record_label_name");
                                artist_name = cd_rs.getString("artist_name");
                                cd_genre = cd_rs.getString("genre");
                                track_list = cd_rs.getString("track_list");
                            }
                        } catch(Exception e) {
                            
                        }
                        media = new CD(media_id, title, value, price, sale_percent, category_id, image_path, barcode, description, quantity, input_day, width, height, depth, weight, artist_name, record_label_name, cd_publication_date, cd_genre, track_list);
                        medias.add(media);
                        break;
                    case 4:
                        String lp_query = "SELECT * FROM `LPs` WHERE media_id = " + media_id + ";";
                        String lp_publication_date = null;
                        String lp_record_label_name = null;
                        String lp_artist_name = null;
                        String lp_genre = null;
                        String lp_track_list = null;
                        try {
                            ResultSet lp_rs = ConnectSQL.sqlQuery(lp_query);
                            if (lp_rs.next()) {
                                lp_publication_date = lp_rs.getString("publication_date").split(" ")[0];
                                lp_record_label_name = lp_rs.getString("record_label_name");
                                lp_artist_name = lp_rs.getString("artist_name");
                                lp_genre = lp_rs.getString("genre");
                                lp_track_list = lp_rs.getString("track_list");
                            }
                        } catch(Exception e) {
                            
                        }
                        media = new LP(media_id, title, value, price, sale_percent, category_id, image_path, barcode, description, quantity, input_day, width, height, depth, weight, lp_artist_name, lp_record_label_name, lp_publication_date, lp_genre, lp_track_list);
                        medias.add(media);
                        break;
                }
                
            }
        } catch (Exception e) {
            return medias;
        }
        
        return medias;
    }
    
    public static ArrayList<Media> getAllMedia() {
        ArrayList<Media> medias = new ArrayList<Media>();
        
        String query = "SELECT * FROM `Medias`, `PhysicalGoods` WHERE `Medias`.`media_id` = `PhysicalGoods`.`media_id`;";
        try {
            ResultSet rs = ConnectSQL.sqlQuery(query);
            while (rs.next()) {
                Media media;
                int media_id = rs.getInt("media_id");
                String title = rs.getString("title");
                int value = rs.getInt("value");
                int price = rs.getInt("price");
                int category_id = rs.getInt("category_id");
                String image_path = rs.getString("image_path");
                int sale_percent = 0;
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                String input_day = rs.getString("input_day").split(" ")[0];
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                int depth = rs.getInt("depth");
                int weight = rs.getInt("weight");
                String barcode = rs.getString("barcode");
                
                switch (category_id) {
                    case 1:
                        String book_query = "SELECT * FROM `Books` WHERE media_id = " + media_id + ";";
                        String book_publication_date = null;
                        int page_number = -1;
                        int cover_type_id = -1;
                        String publisher_name = null;
                        int language_id = -1;
                        String author_name = null;
                        String genre = null;
                        try {
                            ResultSet book_rs = ConnectSQL.sqlQuery(book_query);
                            if (book_rs.next()) {
                                book_publication_date = book_rs.getString("publication_date").split(" ")[0];
                                page_number = book_rs.getInt("page_number");
                                cover_type_id = book_rs.getInt("cover_type_id");
                                publisher_name = book_rs.getString("publisher_name");
                                language_id = book_rs.getInt("language_id");
                                author_name = book_rs.getString("author_name");
                                genre = book_rs.getString("genre");
                            }
                        } catch (Exception e) {
                            
                        }
                        media = new Book(media_id, title, value, price, sale_percent, category_id, image_path, barcode, description, quantity, input_day, width, height, depth, weight, author_name, cover_type_id, publisher_name, book_publication_date, page_number, language_id, genre);
                        medias.add(media);
                        break;
                    case 2:
                        String dvd_query = "SELECT * FROM `DVDs` WHERE media_id = " + media_id + ";";
                        String dvd_publication_date = null;
                        int runtime = -1;
                        String subtitle = null;
                        int dvd_language_id = -1;
                        String studio_name = null;
                        int dvd_type_id = -1;
                        String writer_name = null;
                        try {
                            ResultSet dvd_rs = ConnectSQL.sqlQuery(dvd_query);
                            if (dvd_rs.next()) {
                                dvd_publication_date = dvd_rs.getString("publication_date").split(" ")[0];
                                runtime = dvd_rs.getInt("runtime");
                                subtitle = dvd_rs.getString("subtitle");
                                dvd_language_id = dvd_rs.getInt("language_id");
                                studio_name = dvd_rs.getString("studio_name");
                                dvd_type_id = dvd_rs.getInt("dvd_type_id");
                                writer_name = dvd_rs.getString("writer_name");
                            }
                        } catch (Exception e) {
                            
                        }
                        media = new DVD(media_id, title, value, price, sale_percent, category_id, image_path, barcode, description, quantity, input_day, width, height, depth, weight, dvd_type_id, writer_name, runtime, studio_name, dvd_language_id, subtitle, dvd_publication_date);
                        medias.add(media);
                        break;
                    case 3:
                        String cd_query = "SELECT * FROM `CDs` WHERE media_id = " + media_id + ";";
                        String cd_publication_date = null;
                        String record_label_name = null;
                        String artist_name = null;
                        String cd_genre = null;
                        String track_list = null;
                        try {
                            ResultSet cd_rs = ConnectSQL.sqlQuery(cd_query);
                            if (cd_rs.next()) {
                                cd_publication_date = cd_rs.getString("publication_date").split(" ")[0];
                                record_label_name = cd_rs.getString("record_label_name");
                                artist_name = cd_rs.getString("artist_name");
                                cd_genre = cd_rs.getString("genre");
                                track_list = cd_rs.getString("track_list");
                            }
                        } catch(Exception e) {
                            
                        }
                        media = new CD(media_id, title, value, price, sale_percent, category_id, image_path, barcode, description, quantity, input_day, width, height, depth, weight, artist_name, record_label_name, cd_publication_date, cd_genre, track_list);
                        medias.add(media);
                        break;
                    case 4:
                        String lp_query = "SELECT * FROM `LPs` WHERE media_id = " + media_id + ";";
                        String lp_publication_date = null;
                        String lp_record_label_name = null;
                        String lp_artist_name = null;
                        String lp_genre = null;
                        String lp_track_list = null;
                        try {
                            ResultSet lp_rs = ConnectSQL.sqlQuery(lp_query);
                            if (lp_rs.next()) {
                                lp_publication_date = lp_rs.getString("publication_date").split(" ")[0];
                                lp_record_label_name = lp_rs.getString("record_label_name");
                                lp_artist_name = lp_rs.getString("artist_name");
                                lp_genre = lp_rs.getString("genre");
                                lp_track_list = lp_rs.getString("track_list");
                            }
                        } catch(Exception e) {
                            
                        }
                        media = new LP(media_id, title, value, price, sale_percent, category_id, image_path, barcode, description, quantity, input_day, width, height, depth, weight, lp_artist_name, lp_record_label_name, lp_publication_date, lp_genre, lp_track_list);
                        medias.add(media);
                        break;
                }
                
            }
        } catch (Exception e) {
            return medias;
        }
        
        return medias;
    }
    public static ArrayList<Media> getAllMedia(int start, int end) {
        ArrayList<Media> medias = new ArrayList<Media>();
        
        String query = "SELECT * FROM `Medias` join `PhysicalGoods` using ( media_id ) ORDER BY `Medias`.media_id DESC LIMIT "+ start + "," + end;
        try {
            ResultSet rs = ConnectSQL.sqlQuery(query);
            while (rs.next()) {
                Media media;
                int media_id = rs.getInt("media_id");
                String title = rs.getString("title");
                int value = rs.getInt("value");
                int price = rs.getInt("price");
                int category_id = rs.getInt("category_id");
                String image_path = rs.getString("image_path");
                int sale_percent = 0;
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                String input_day = rs.getString("input_day").split(" ")[0];
                int width = rs.getInt("width");
                int height = rs.getInt("height");
                int depth = rs.getInt("depth");
                int weight = rs.getInt("weight");
                String barcode = rs.getString("barcode");
                
                switch (category_id) {
                    case 1:
                        String book_query = "SELECT * FROM `Books` WHERE media_id = " + media_id + ";";
                        String book_publication_date = null;
                        int page_number = -1;
                        int cover_type_id = -1;
                        String publisher_name = null;
                        int language_id = -1;
                        String author_name = null;
                        String genre = null;
                        try {
                            ResultSet book_rs = ConnectSQL.sqlQuery(book_query);
                            if (book_rs.next()) {
                                book_publication_date = book_rs.getString("publication_date").split(" ")[0];
                                page_number = book_rs.getInt("page_number");
                                cover_type_id = book_rs.getInt("cover_type_id");
                                publisher_name = book_rs.getString("publisher_name");
                                language_id = book_rs.getInt("language_id");
                                author_name = book_rs.getString("author_name");
                                genre = book_rs.getString("genre");
                            }
                        } catch (Exception e) {
                            
                        }
                        media = new Book(media_id, title, value, price, sale_percent, category_id, image_path, barcode, description, quantity, input_day, width, height, depth, weight, author_name, cover_type_id, publisher_name, book_publication_date, page_number, language_id, genre);
                        medias.add(media);
                        break;
                    case 2:
                        String dvd_query = "SELECT * FROM `DVDs` WHERE media_id = " + media_id + ";";
                        String dvd_publication_date = null;
                        int runtime = -1;
                        String subtitle = null;
                        int dvd_language_id = -1;
                        String studio_name = null;
                        int dvd_type_id = -1;
                        String writer_name = null;
                        try {
                            ResultSet dvd_rs = ConnectSQL.sqlQuery(dvd_query);
                            if (dvd_rs.next()) {
                                dvd_publication_date = dvd_rs.getString("publication_date").split(" ")[0];
                                runtime = dvd_rs.getInt("runtime");
                                subtitle = dvd_rs.getString("subtitle");
                                dvd_language_id = dvd_rs.getInt("language_id");
                                studio_name = dvd_rs.getString("studio_name");
                                dvd_type_id = dvd_rs.getInt("dvd_type_id");
                                writer_name = dvd_rs.getString("writer_name");
                            }
                        } catch (Exception e) {
                            
                        }
                        media = new DVD(media_id, title, value, price, sale_percent, category_id, image_path, barcode, description, quantity, input_day, width, height, depth, weight, dvd_type_id, writer_name, runtime, studio_name, dvd_language_id, subtitle, dvd_publication_date);
                        medias.add(media);
                        break;
                    case 3:
                        String cd_query = "SELECT * FROM `CDs` WHERE media_id = " + media_id + ";";
                        String cd_publication_date = null;
                        String record_label_name = null;
                        String artist_name = null;
                        String cd_genre = null;
                        String track_list = null;
                        try {
                            ResultSet cd_rs = ConnectSQL.sqlQuery(cd_query);
                            if (cd_rs.next()) {
                                cd_publication_date = cd_rs.getString("publication_date").split(" ")[0];
                                record_label_name = cd_rs.getString("record_label_name");
                                artist_name = cd_rs.getString("artist_name");
                                cd_genre = cd_rs.getString("genre");
                                track_list = cd_rs.getString("track_list");
                            }
                        } catch(Exception e) {
                            
                        }
                        media = new CD(media_id, title, value, price, sale_percent, category_id, image_path, barcode, description, quantity, input_day, width, height, depth, weight, artist_name, record_label_name, cd_publication_date, cd_genre, track_list);
                        medias.add(media);
                        break;
                    case 4:
                        String lp_query = "SELECT * FROM `LPs` WHERE media_id = " + media_id + ";";
                        String lp_publication_date = null;
                        String lp_record_label_name = null;
                        String lp_artist_name = null;
                        String lp_genre = null;
                        String lp_track_list = null;
                        try {
                            ResultSet lp_rs = ConnectSQL.sqlQuery(lp_query);
                            if (lp_rs.next()) {
                                lp_publication_date = lp_rs.getString("publication_date").split(" ")[0];
                                lp_record_label_name = lp_rs.getString("record_label_name");
                                lp_artist_name = lp_rs.getString("artist_name");
                                lp_genre = lp_rs.getString("genre");
                                lp_track_list = lp_rs.getString("track_list");
                            }
                        } catch(Exception e) {
                            
                        }
                        media = new LP(media_id, title, value, price, sale_percent, category_id, image_path, barcode, description, quantity, input_day, width, height, depth, weight, lp_artist_name, lp_record_label_name, lp_publication_date, lp_genre, lp_track_list);
                        medias.add(media);
                        break;
                }
                
            }
        } catch (Exception e) {
            return medias;
        }
        
        return medias;
    }
}
