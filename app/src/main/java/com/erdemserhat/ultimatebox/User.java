package com.erdemserhat.ultimatebox;

import java.util.List;

public class User {

    private String username;
    private String password;
    private int userId;
    private List<Password> passwordList;

    /**
     * If user is not registered, use this constructor to instantiate a local user.
     * @param passwordList the list of user's passwords.
     */
    public User(List <Password> passwordList){
        this.username="unRegistered";
        this.password=null;
        this.passwordList=passwordList;
        //TODO : implement id assignment processes.
        this.userId=0;

    }

    /**
     * If user attempts to register global host, use this constructor to make user register host.
     * @param username the nickname of user.
     * @param password the password of user, user uses this password to login global host.
     * @param userId the id of user, this id will be generated automatically.
     * @param passwordList the list of passwords that the user has.
     */
    public User(String username, String password, int userId, List<Password> passwordList) {
        this.username = username;
        this.password = password;
        this.passwordList = passwordList;

        //TODO : implement id assignment processes.
        this.userId=0;

    }
}
