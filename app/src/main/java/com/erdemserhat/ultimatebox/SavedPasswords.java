package com.erdemserhat.ultimatebox;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.time.Duration;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SavedPasswords#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SavedPasswords extends Fragment {
    Button addCustomButton;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SavedPasswords() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SavedPasswords.
     */
    // TODO: Rename and change types and number of parameters
    public static SavedPasswords newInstance(String param1, String param2) {
        SavedPasswords fragment = new SavedPasswords();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        //Recycler View processes...

        //Declaring and inflating current view and fragment_saved_passwords.xml
        View view = inflater.inflate(R.layout.fragment_saved_passwords, container, false);
        //Declaring recyclerView reference to it's view by using "view" reference.
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        // Setting LayoutManager (such as , LinearLayoutManager, GridLayoutManager, StaggeredGridLayoutManager)
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(layoutManager);

        //Creating an adapter reference from SavedPasswordsAdapter class.
        SavedPasswordsAdapter adapter = new SavedPasswordsAdapter();
        //Setting adapter
        recyclerView.setAdapter(adapter);







        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View addCustomPasswordButton = view.getRootView().findViewById(R.id.addCustomPasswordButton);
        addCustomPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Processes when add button is clicked...
                AddCustomPasswordDialog addCustomPasswordDialog = new AddCustomPasswordDialog(view.getContext());
                addCustomPasswordDialog.setCustomDialogListener(new CustomDialogListener() {
                    @Override
                    public void onSaveClicked() {
                        String title = addCustomPasswordDialog.getPasswordContentEditText().getText().toString();
                        String content = addCustomPasswordDialog.getPasswordContentEditText().getText().toString();
                        if(title.isBlank() || title.isBlank() || content.isBlank() || content.isBlank()){
                            Toast.makeText(view.getContext(),"Please enter valid values",Toast.LENGTH_SHORT).show();
                        }else{
                            //When values are valid below processes will be executed....
                            DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                            SQLiteDatabase database = databaseHelper.getWritableDatabase();
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("title",title);
                            contentValues.put("content",content);
                            database.insert("passwords",null,contentValues);
                            databaseHelper.updatePasswordData(view.getContext());
                            Toast.makeText(view.getContext(),"The password has been saved",Toast.LENGTH_SHORT).show();
                            addCustomPasswordDialog.cancel();


                        }


                    }

                    @Override
                    public void onCancelClicked() {
                        addCustomPasswordDialog.cancel();

                    }
                });
                addCustomPasswordDialog.show();


            }
        });




    }
}