/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.User;

/**
 * @author thanhhff
 */
public class Account {
    private int id, lv;
    private String username, password;

    public Account() {
        id = lv = 0;
        username = "";
        password = "";
    }

    public Account(int id, int lv, String username, String password) {
        this.id = id;
        this.lv = lv;
        this.username = username;
        this.password = password;
    }
    
    public Account(int id, int lv, String username) {
        this.id = id;
        this.lv = lv;
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
