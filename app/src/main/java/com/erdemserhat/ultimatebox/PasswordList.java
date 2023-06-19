package com.erdemserhat.ultimatebox;

import com.erdemserhat.ultimatebox.Password;

import java.util.ArrayList;

public class PasswordList {
    private static PasswordList instance;
    private ArrayList<Password> passwordList;

    private PasswordList() {
        passwordList = new ArrayList<>();
    }

    public static synchronized PasswordList getInstance() {
        if (instance == null) {
            instance = new PasswordList();
        }
        return instance;
    }

    public ArrayList<Password> getPasswordList() {
        return passwordList;
    }

    public void addPassword(Password password) {
        passwordList.add(password);
    }

    public void removePassword(Password password) {
        passwordList.remove(password);
    }

    public int getLength(){
       return passwordList.size();
    }

    public void resetList(){
        passwordList = new ArrayList<>();
    }
}