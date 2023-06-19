package com.erdemserhat.ultimatebox;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SavedPasswords#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SavedPasswords extends Fragment {

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
}