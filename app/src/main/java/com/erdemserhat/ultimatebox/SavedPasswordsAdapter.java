package com.erdemserhat.ultimatebox;

import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erdemserhat.ultimatebox.Password;
import com.erdemserhat.ultimatebox.PasswordList;

public class SavedPasswordsAdapter extends RecyclerView.Adapter<SavedPasswordsAdapter.PasswordHolder> {

    private PasswordHolder holder;
    private int position;

    @NonNull
    @Override


    public PasswordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        /*
         * Information about inflate method ;
         * In Android Studio, the inflate function is used to convert a layout file from an XML file into a Java class.
         * This function is widely used especially for creating user interface elements such as
         * activities, fragments and customized views.
         * The inflate function is a method of the LayoutInflater class. Used to generate a layout file from an XML source.
         * This action makes interface elements defined in a particular XML file (e.g. buttons, text fields, images)
         * available to the Java code.
         * For example, when you want to set a layout file using the setContentView method in an activity or fragment,
         * you can create that file using the inflate function. As shown in a sample code snippet like below:
         *
         * LayoutInflater inflater = LayoutInflater.from(context);
         * View rootView = inflater.inflate(R.layout.activity_main, container, false);
         *
         * In the above snippet, the layout file R.layout.activity_main is converted to a View object with the inflate function.
         * This conversion will make the elements in the layout file accessible and then available on the rootView.
         *
         */


        //We should inflate xml file to use it's views like buttons and texts.
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_saved_passwords_row, parent, false);

        //returning an object of PasswordHolder class.
        return new PasswordHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PasswordHolder holder, int position) {
        //Get data
        PasswordList passwordList = PasswordList.getInstance();
        Password password = passwordList.getPasswordList().get(position);
        //show data
        holder.bind(password);
        //Delete Button Implementation
        holder.itemView.findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {

            //Implementing the onClick method to give as parameter to setOnClickListener method.
            @Override
            public void onClick(View view) {




                //Getting Database
                DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                SQLiteDatabase database = databaseHelper.getWritableDatabase();
                //Assigning the related password's id.
                int passwordId = password.getPasswordId();

                //Committing the delete instruction
                database.delete("passwords", "id = ?", new String[]{String.valueOf(passwordId)});
                //Deprecated Block either delete or exeSQL works.
                //database.execSQL("DELETE FROM passwords WHERE id ="+passwordId);

                //Closing database.
                database.close();

                //Updating the data with current database.
                databaseHelper.updatePasswordData(view.getContext());

                // removing the related item on the Recycler View.
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, passwordList.getLength());
            }
        });
    }

    @Override
    public int getItemCount() {
        PasswordList passwordList = PasswordList.getInstance();
        return passwordList.getLength();
    }

    /**
     * Password Holder is a class that stores the data and view represented for each password in the RecyclerView.
     */

    public class PasswordHolder extends RecyclerView.ViewHolder {
        private final TextView titleTextView;
        private final TextView dateTextView;
        private final TextView contentTextView;

        public PasswordHolder(View itemView) {
            super(itemView);
            //Accessing views.
            titleTextView = itemView.findViewById(R.id.titleRecycler);
            dateTextView = itemView.findViewById(R.id.dateRecycler);
            contentTextView = itemView.findViewById(R.id.passwordContentRecycler);
        }

        public void bind(Password password) {
            // Assigning views to their values.
            titleTextView.setText(password.getTitle());
            dateTextView.setText(password.getCreatedDate());
            contentTextView.setText(password.getContent());
        }
    }
}
