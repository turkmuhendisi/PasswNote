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
    private Date createdDate;
    private int passwordId;

    /**
     * Creates an object of Password with the given parameters
     * @param content the content of password (password's itself)
     * @param title the title of password. (for example : My Instagram Account)
     * @param createdDate the crated date of password.
     */
    public Password(String content, String title,Date createdDate, int passwordId){
        this.content=new String(content);
        this.title=title;
        this.createdDate=createdDate;

        // processes about password id will be implemented based on other features.
        //...
        this.passwordId=0;
    }

    /**
     * Creates an object of Password with the given parameters
     * @param content the content of password (password's itself)
     * @param createdDate the title of password. (for example : My Instagram Account)
     */

    public Password(String content, Date createdDate){
        new Password(content,"Copied Password",new Date(),0);
    }


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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
