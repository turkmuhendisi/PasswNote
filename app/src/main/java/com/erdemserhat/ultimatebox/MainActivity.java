package com.erdemserhat.ultimatebox;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;

import com.erdemserhat.ultimatebox.databinding.ActivityMainBinding;


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

        //Going to HomeFragment.xml whenever app is started.
        changeFragment(new HomeFragment());


        /**
         * SetOnClickListener for Navigation Menu....
         */

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.history:
                    //When history icon is clicked on the nav bar.
                    changeFragment(new HistoryFragment());

                    break;

                case R.id.profile:
                    //When profile icon is clicked on the nav bar.
                    changeFragment(new ProfileFragment());

                    break;

                case R.id.settings:
                    //When settings icon is clicked on the nav bar.
                    changeFragment(new SettingsFragment());

                    break;

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





