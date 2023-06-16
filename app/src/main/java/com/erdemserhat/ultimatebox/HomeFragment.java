package com.erdemserhat.ultimatebox;

import static android.content.Context.CLIPBOARD_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.Toast;

import com.erdemserhat.ultimatebox.databinding.ActivityMainBinding;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.erdemserhat.ultimatebox.databinding.ActivityMainBinding;
import com.erdemserhat.ultimatebox.databinding.FragmentHomeBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        // Burada binding nesnesini kullanarak layout içindeki view'lara erişebilirsiniz
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Butona tıklandığında yapılacak işlemler
                generate(v);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Fragment view'i yok edildiğinde binding nesnesini null'a ayarla
        //binding = null;
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
        /**
         * Assigning the default config.
         */



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


    }


    public void generate(View view) {
        //Animation
        view.startAnimation(buttonClick);
        boolean isCharacter = binding.characterSwitch.isChecked();
        boolean isNumerical = binding.numberSwitch.isChecked();
        boolean isSpecialCharacter = binding.specialSwitch.isChecked();
        String pwLengthTemp = binding.pwLenght.getText().toString();
        int passwordLength;
        if (pwLengthTemp.isEmpty()) {
            passwordLength = 32;
        } else {
            passwordLength = Integer.parseInt(pwLengthTemp);
        }

        if (isCharacter || isSpecialCharacter || isNumerical) {

            if (passwordLength < 4) {
                binding.warning.setText(R.string.infoDigitLess);
                return;
            } else if (passwordLength > 10000) {
                binding.warning.setText(R.string.infoDigitMore);
                return;

            }
            Generator generator = new Generator(isNumerical, isCharacter, isSpecialCharacter, passwordLength);
            String password = generator.createPassword();
            binding.pwTitle.setVisibility(View.VISIBLE);
            binding.warning.setText(password);


            ClipboardManager clipboardManager = (ClipboardManager) requireContext().getSystemService(Context.CLIPBOARD_SERVICE);
            clipboardManager.setText(password);
            Toast.makeText(HomeFragment.this.getContext(), R.string.infoCopiedText, Toast.LENGTH_SHORT).show();
        } else {

            binding.warning.setText(R.string.infoRadioButton);
        }

    }


}