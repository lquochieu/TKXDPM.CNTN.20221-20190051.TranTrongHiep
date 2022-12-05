/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Media;

import db.ConnectSQL;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Toshiba T75
 */
public class Book extends PhysicalGood {
    private String author;
    private int cover_type_id;
    private String publisher;
    private String publication_date;
    private int page_number;
    private int language_id;
    private String genre;
    
    public Book() {
        
    }
    
    public Book(int id, String title, int value, int price, int sale_percent, int category, String image_path, String barcode, String description, int quantity, String input_day, int width, int height, int depth, int weight, String author, int cover_type_id, String publisher, String publication_date, int page_number, int language, String genre) {
        super(id, title, value, price, sale_percent, category, image_path, barcode, description, quantity, input_day, width, height, depth, weight);
        this.author = author;
        this.cover_type_id = cover_type_id;
        this.publisher = publisher;
        this.publication_date = publication_date;
        this.page_number = page_number;
        this.language_id = language;
        this.genre = genre;
    }
    
    public Book(String title, int value, int price, int sale_percent, int category, String image_path, String barcode, String description, int quantity, String input_day, int width, int height, int depth, int weight, String author, int cover_type_id, String publisher, String publication_date, int page_number, int language, String genre) {
        super(title, value, price, sale_percent, category, image_path, barcode, description, quantity, input_day, width, height, depth, weight);
        this.author = author;
        this.cover_type_id = cover_type_id;
        this.publisher = publisher;
        this.publication_date = publication_date;
        this.page_number = page_number;
        this.language_id = language;
        this.genre = genre;
    }
    
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getCoverTypeID() {
        return cover_type_id;
    }

    public void setCoverTypeId(int cover_type_id) {
        this.cover_type_id = cover_type_id;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(String publication_date) {
        this.publication_date = publication_date;
    }

    public int getPageNumber() {
        return page_number;
    }

    public void setPageNumber(int page_number) {
        this.page_number = page_number;
    }

    public int getLanguageId() {
        return language_id;
    }

    public void setLanguage(int language) {
        this.language_id = language;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public static ArrayList<Book> getAllBooks() {
        ArrayList<Book> books = new ArrayList<Book>();
        String query = "SELECT `Medias`.title, `Medias`.value, `Medias`.price, `Medias`.category_id, `Medias`.image_path, `Physicalgoods`.*, `Books`.* FROM `Books` LEFT JOIN `Physicalgoods` ON `Books`.media_id = `Physicalgoods`.media_id LEFT JOIN `Medias` ON `Physicalgoods`.media_id = `Medias`.media_id;";

        try {
            ResultSet rs = ConnectSQL.sqlQuery(query);
        
            while (rs.next()) {
                String title = rs.getString(1);
                int value = rs.getInt(2);
                int price = rs.getInt(3);
                int category_id = rs.getInt(4);
                String image_path = rs.getString(5);
                String description = rs.getString(6);
                int quantity = rs.getInt(7);
                String input_day = rs.getString(9);
                int media_id = rs.getInt(10);
                int width = rs.getInt(11);
                int height = rs.getInt(12);
                int depth = rs.getInt(13);
                int weight = rs.getInt(14);
                String barcode = rs.getString(15);
                String publication_date = rs.getString(16);
                int page_number = rs.getInt(17);
                int cover_type_id = rs.getInt(19);
                String publisher_name = rs.getString(20);
                int language_id = rs.getInt(21);
                String author_name = rs.getString(22);
                String genre = rs.getString(23);
                int sale_percent = 0;
                Book book = new Book(media_id, title, value, price, sale_percent, category_id, image_path, barcode, description, quantity, input_day, width, height, depth, weight, author_name, cover_type_id, publisher_name, publication_date, page_number, language_id, genre);
                books.add(book);
            }
            return books;
        } catch (Exception e) {
            return books;
        }
    }
    
    private void insertBook() {
        String book_query = "INSERT INTO `Books` (publication_date, page_number, media_id, cover_type_id, publisher_name, language_id, author_name, genre) VALUES ('"
                + publication_date + "', " + page_number + ", " + this.getId() + ", " + cover_type_id + ", '" + publisher + "', " + language_id + ", '" + author +"', '" + genre + "');";
        
        try {
            ConnectSQL.sqlQueryUpdate(book_query);
        } catch (Exception e) {
        }
    }
    
    public void insert() {
        this.insertPhysical();
        this.insertBook();
    }
    
    private void updateBook() {
        String book_query = "UPDATE `Books` SET publication_date = '" + publication_date + "', page_number = " + page_number + ", cover_type_id = " +
                cover_type_id + ", publisher_name = '" + publisher + "', language_id = " + language_id + ", author_name = '" + author + "', genre = '" + genre + 
                "' WHERE media_id = " + this.getId();
        try {
            ConnectSQL.sqlQueryUpdate(book_query);
        } catch (Exception e) {
            
        }
        
    }
    
    public void update() {
        this.updatePhysical();
        this.updateBook();
    }
}
