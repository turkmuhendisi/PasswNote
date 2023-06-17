package com.erdemserhat.ultimatebox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.erdemserhat.ultimatebox.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    //View binding data member assignment
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**Some Implementation for view binding process*/

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        //Going to HomeFragment.xml
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


            }
            return true;
        });


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





