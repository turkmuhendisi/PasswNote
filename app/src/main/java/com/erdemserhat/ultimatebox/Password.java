package com.erdemserhat.ultimatebox;

import java.util.Date;

/**
 * Password class represents the password dataset; if the user generates any password and attempts either copy and save it,
 * an object of Password class will be created.
 */
public class Password {

    //Data members (fields)
    private String content;
    private String title;
    private String createdDate;
    private int passwordId;

    /**
     * Creates an object of Password with the given parameters
     * @param content the content of password (password's itself)
     * @param title the title of password. (for example : My Instagram Account)
     * @param createdDate the crated date of password.
     */
    public Password(String content, String title,String createdDate, int passwordId){
        this.content=new String(content);
        this.title=title;
        this.createdDate=createdDate;
        this.passwordId=passwordId;

        // processes about password id will be implemented based on other features.
        //...

    }

    /**
     * Creates an object of Password with the given parameters
     * @param content the content of password (password's itself)
     * @param createdDate the title of password. (for example : My Instagram Account)
     */


    //Getters and setters

    /**
     * To take password's content, call this method.
     * @return the password's itself.
     */
    public String getContent() {
        return this.content;
    }

    /**
     * To set a new password content
     * @param content the password content which you want to change with previous password.
     */
    public void setContent(String content) {
        this.content = new String(content);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getPasswordId() {
        return this.passwordId;
    }

    public void setPasswordId(int passwordId) {
        this.passwordId = passwordId;
    }
}
