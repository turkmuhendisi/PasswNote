package com.erdemserhat.ultimatebox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.erdemserhat.ultimatebox.databinding.ActivityMainBinding;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.Objects;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    //To use view binding feature, reference (binding) of ActivityMainBinding class is declared below.
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Some Implementation for view binding process*
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //area for work

        /*SwitchMaterial themeButton = findViewById(R.id.themeButton);
        //Objects.requireNonNull(getSupportActionBar()).setTitle("LIGHT-NIGHT MODE SWITCH");
        themeButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    buttonView.setText("Night Mode");
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    buttonView.setText("Light Mode");
                }
            }
        });

        boolean isNightModeOn = AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES;
        themeButton.setChecked(isNightModeOn);
        if (isNightModeOn) {
            themeButton.setText("Night Mode");
        } else {
            themeButton.setText("Light Mode");
        }*/


        //Going to HomeFragment.xml and updating database whenever app is started.
        DatabaseHelper databaseHelper = new DatabaseHelper(getApplicationContext());
        databaseHelper.updatePasswordData(getApplicationContext());
        changeFragment(new HomeFragment());

        /**
         * SetOnClickListener for Navigation Menu....
         */

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {

                /**

                case R.id.profile:
                    //When profile icon is clicked on the nav bar.
                    changeFragment(new ProfileFragment());

                    break;

                case R.id.settings:
                    //When settings icon is clicked on the nav bar.
                    changeFragment(new SettingsFragment());

                    break;
                 */

                case R.id.home:
                    changeFragment(new HomeFragment());
                    break;

                case R.id.savedPasswords:
                    changeFragment(new SavedPasswords());
                    break;


            }
            return true;
        });

        //Local Database Set-Up

        //Deprecated Feature
/**

 try{
 DatabaseHelper databaseHelper = DatabaseHelper.getInstance(this.getApplicationContext());
 SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
 ContentValues values = new ContentValues();

 values.put("content", "example pas21212sword");
 values.put("title", "ex title");
 values.put("date", "24 jun");

 sqLiteDatabase.insert("passwords", null, values);




 Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM passwords", null);

 int contentIx=cursor.getColumnIndex("content");
 int titleIx=cursor.getColumnIndex("title");
 int dateIx=cursor.getColumnIndex("date");
 int idIx=cursor.getColumnIndex("id");

 while(cursor.moveToNext()){
 System.out.println("Content: "+cursor.getString(contentIx));
 System.out.println("Title: "+cursor.getString(titleIx));
 System.out.println("Date: "+cursor.getString(dateIx));
 System.out.println("Id: " +cursor.getInt(idIx));
 System.out.println("---------------------------------------------");


 }




 }catch (Exception e){
 e.printStackTrace();

 }
 */



    }

    /**
     * This method changes the current fragment with the fragment which comes as parameter
     */
    private void changeFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();

    }




}





