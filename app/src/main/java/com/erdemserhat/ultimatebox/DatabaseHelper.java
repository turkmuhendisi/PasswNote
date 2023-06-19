package com.erdemserhat.ultimatebox;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    //Data members
    private static final String DATABASE_NAME="local_password_database.db";
    private static final int DATABASE_VERSION=1;

    private static DatabaseHelper instance;

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context.getApplicationContext());
        }
        return instance;
    }


    //Default Constructor
    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS passwords(content VARCHAR, title VARCHAR, date VARCHAR, id INTEGER PRIMARY KEY)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // database upgrade processes
    }

    public void updatePasswordData(Context context){

        DatabaseHelper databaseHelper = new DatabaseHelper(context.getApplicationContext());
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM passwords", null);
        int contentIx=cursor.getColumnIndex("content");
        int titleIx=cursor.getColumnIndex("title");
        int dateIx=cursor.getColumnIndex("date");
        int idIx=cursor.getColumnIndex("id");

        PasswordList passwordList =PasswordList.getInstance();
        passwordList.resetList();

        while(cursor.moveToNext()){

            String content =cursor.getString(contentIx);
            String title=cursor.getString(titleIx);
            String date=cursor.getString(dateIx);
            int id =cursor.getInt(idIx);

            passwordList.addPassword(new Password(content,title,date,id));
        }


    }
}
