package com.erdemserhat.ultimatebox;

import static android.content.Context.CLIPBOARD_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.erdemserhat.ultimatebox.random_password_generator.*;

import android.content.ClipboardManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.AdapterView;
import android.widget.Toast;

import com.erdemserhat.ultimatebox.databinding.ActivityMainBinding;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erdemserhat.ultimatebox.databinding.ActivityMainBinding;
import com.erdemserhat.ultimatebox.databinding.FragmentHomeBinding;

import java.util.Date;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    //To use alpha animation an object of AlphaAnimation class is instantiated below.
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    //To use view binding feature, reference (binding) of FragmentHomeBinding class is declared below.
    private FragmentHomeBinding binding;


    /**
     * Note ; You should set up view binding settings here.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate
     *                           any views in the fragment,
     * @param container          If non-null, this is the parent view that the fragment's
     *                           UI should be attached to.  The fragment should not add the view itself,
     *                           but this can be used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //To use view binding feature and bind fragment_home.xml and HomeFragment.java files, some operations are implemented below.
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    /**
     * You can use binding object to reach views in the body of the method.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     *                           from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you are free to use binding object to manipulate views.

        //Some default setting for switches.
        binding.characterSwitch.setChecked(true);
        binding.numberSwitch.setChecked(true);
        binding.specialSwitch.setChecked(true);


        //When generate button is clicked by the user;
        binding.generateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //these processes will be run..
                generate(v);
            }
        });


        /**
         * When user long clicks the password area, the current text of warning view will be copied to clipboard
         */
        binding.warning.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                String password = binding.warning.getText().toString();
                ClipboardManager clipboardManager = (ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
                clipboardManager.setText(password);
                Toast.makeText(requireContext(), R.string.infoCopiedText, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        //New Feature about save ps.

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CustomDialog customDialog = new CustomDialog(getContext());
                //NOTE: Interface  design pattern is used.
                customDialog.setCustomDialogListener(new CustomDialogListener() {
                    @Override
                    public void onSaveClicked() {
                        String pwTitle;
                        try{
                            pwTitle = customDialog.getPasswordTitle();
                            if(pwTitle==null || pwTitle.isEmpty() || pwTitle.isBlank()) {
                                throw new IllegalArgumentException();
                            }else{
                                //TODO: save the password to db.
                                //...
                                //Password password = new Password(binding.warning.getText().toString(),pwTitle,new Date());
                                //continue to here
                            }


                        }catch (IllegalArgumentException e){
                            e.printStackTrace();
                            Toast.makeText(getContext(),"Please enter a title", Toast.LENGTH_SHORT).show();
                        }


                    }


                    @Override
                    public void onCancelClicked() {
                        //TODO: Implement the body (when save button is clicked in the dialog)

                    }
                });

                customDialog.show();


            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    /**
     * @param view
     */
    public void generate(View view) {
        //Animation
        view.startAnimation(buttonClick);
        binding.saveButton.setVisibility(View.VISIBLE);

        //Getting switch  and password length options.
        boolean isCharacter = binding.characterSwitch.isChecked();
        boolean isNumerical = binding.numberSwitch.isChecked();
        boolean isSpecialCharacter = binding.specialSwitch.isChecked();
        String pwLengthTemp = binding.pwLenght.getText().toString();

        //If password length(pwLength) is empty, 32 value is taken as default, otherwise a different value should be entered from user so this value is kept.
        int passwordLength;
        if (pwLengthTemp.isEmpty()) {
            passwordLength = 32;
        } else {
            passwordLength = Integer.parseInt(pwLengthTemp);
        }

        //If at least one option is selected by user, if body will be run.
        if (isCharacter || isSpecialCharacter || isNumerical) {

            //Some conditions are controlled.
            if (passwordLength < 4) {
                binding.warning.setText(R.string.infoDigitLess);
                //if the conditions is not provided, function finishes. (by return)
                return;
            } else if (passwordLength > 10000) {
                //if the conditions is not provided, function finishes. (by return)
                binding.warning.setText(R.string.infoDigitMore);
                return;
            }

            //If whole conditions are provided, password generate process starts ;
            Generator generator = new Generator(isNumerical, isCharacter, isSpecialCharacter, passwordLength);
            //the password which has provides the options of user generated and is kept as value in the "password" object
            String password = generator.createPassword();
            //a sign will be visible to make user inform about where generated password is.
            binding.pwTitle.setVisibility(View.VISIBLE);
            //generated password will be visible related view.
            binding.warning.setText(password);

            //If everything is ok, generated password is copied to clipboard automatically.

            /**
             * Deprecated Feature.
            ClipboardManager clipboardManager = (ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("password text", password);
            clipboardManager.setPrimaryClip(clip);
            Toast.makeText(HomeFragment.this.getContext(), R.string.infoCopiedText, Toast.LENGTH_SHORT).show();

             */
        } else {

            binding.warning.setText(R.string.infoRadioButton);
        }

    }
/**
    public void save(View view){
        CustomDialog customDialog = new CustomDialog(HomeFragment.this.requireContext());
        customDialog.show();



    }

*/
}