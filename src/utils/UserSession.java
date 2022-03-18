/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import models.User;

/**
 *
 * @author PC2
 */
public final class UserSession {

    private static UserSession instance;

    private User user;
    private String token;

    private UserSession(User user, String token) {
        this.user = user;
        this.token = token;
    }

    public static UserSession getInstace(User user, String token) {
        if (instance == null || (instance != null && token != null)) {
            instance = new UserSession(user, token);
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void clearUserSession() {
        this.setUser(null);// or null
        this.setToken("");// or null
    }

}
