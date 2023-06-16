package com.erdemserhat.ultimatebox;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.erdemserhat.ultimatebox.random_password_generator.*;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Toast;
import com.erdemserhat.ultimatebox.databinding.ActivityMainBinding;



public class   MainActivity extends AppCompatActivity {

    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    /**View binding data member assignment*/
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**Some Implementation for view binding process*/

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        /**
         * Assigning the default config.
         */
        binding.numberSwitch.setChecked(true);
        binding.characterSwitch.setChecked(true);
        binding.specialSwitch.setChecked(true);
        changeFragment(new HomeFragment());


        /**
         * When user long clicks the password area, the current text of warning view will be copied to clipboard
         */

        binding.warning.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                String password = binding.warning.getText().toString();
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                clipboardManager.setText(password);
                Toast.makeText(MainActivity.this, R.string.infoCopiedText, Toast.LENGTH_SHORT).show();
                return true;

            }
        });
        /**
         * SetOnClickListener for Navigation Menu....
         */

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){

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




            return  true;
        });



    }

    public void generate(View view){
        //Animation
        view.startAnimation(buttonClick);
        boolean isCharacter=binding.characterSwitch.isChecked();
        boolean isNumerical =binding.numberSwitch.isChecked();
        boolean isSpecialCharacter=binding.specialSwitch.isChecked();
        String pwLengthTemp = binding.pwLenght.getText().toString();
        int passwordLength;
        if(pwLengthTemp.isEmpty()) {
            passwordLength=32;
        }else{
            passwordLength = Integer.parseInt(pwLengthTemp);
        }

        if(isCharacter || isSpecialCharacter || isNumerical){

            if(passwordLength<4){
                binding.warning.setText(R.string.infoDigitLess);
                return;
            }else if(passwordLength>10000){
                binding.warning.setText(R.string.infoDigitMore);
                return;

            }
            Generator generator = new Generator(isNumerical,isCharacter,isSpecialCharacter,passwordLength);
            String password = generator.createPassword();
            binding.pwTitle.setVisibility(View.VISIBLE);
            binding.warning.setText(password);


            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            clipboardManager.setText(password);
            Toast.makeText(this, R.string.infoCopiedText, Toast.LENGTH_SHORT).show();
        }else{

            binding.warning.setText(R.string.infoRadioButton);
        }

        }
    /**
     * This method changes the current fragment with the fragment which comes as parameter
     */
    private void changeFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();

    }





}





