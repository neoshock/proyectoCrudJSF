/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.neoshock.models;

/**
 *
 * @author pideu
 */
public class UsuarioModel {
    private Long userId;
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String emailUser;
    private String detailUser;
    private String passwordUser;

    public UsuarioModel(long userId, String userName, String userFirstName, String userLastName, 
            String emailUser, String detailUser) {
        this.userId = userId;
        this.userName = userName;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.emailUser = emailUser;
        this.detailUser = detailUser;
    }

    public UsuarioModel() {
        
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getDetailUser() {
        return detailUser;
    }

    public void setDetailUser(String detailUser) {
        this.detailUser = detailUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }
        
}
