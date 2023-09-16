package com.erdemserhat.ultimatebox;

import static android.content.Context.CLIPBOARD_SERVICE;
import static androidx.core.content.ContentProviderCompat.requireContext;
import static androidx.core.content.ContextCompat.getSystemService;

import static java.security.AccessController.getContext;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erdemserhat.ultimatebox.Password;
import com.erdemserhat.ultimatebox.PasswordList;

public class SavedPasswordsAdapter extends RecyclerView.Adapter<SavedPasswordsAdapter.PasswordHolder> {

    private PasswordHolder holder;
    private int position;

    @NonNull
    @Override


    public PasswordHolder onCreateViewHolder(ViewGroup parent, int viewType) {

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
    public void onBindViewHolder(PasswordHolder holder, @SuppressLint("RecyclerView") int position) {
        //Get data
        PasswordList passwordList = PasswordList.getInstance();
        Password password = passwordList.getPasswordList().get(position);
        //show data
        holder.bind(password);
        //Delete Button Implementation
        holder.itemView.findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialogDeleteMenu customDialogDeleteMenu = new CustomDialogDeleteMenu(view.getContext());
                    //customDialogDeleteMenu.getDeleteWarningText().setText("serhat");
                customDialogDeleteMenu.setPasswordTitle(password.getTitle().toString());


                customDialogDeleteMenu.setCustomDialogDeleteMenuListener(new CustomDialogDeleteMenuListener() {
                    @Override
                    public void onDelete() {

                        //When delete button is clicked.
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
                        customDialogDeleteMenu.cancel();

                    }


                    @Override
                    public void onCancel() {

                        customDialogDeleteMenu.cancel();

                    }
                });

                customDialogDeleteMenu.show();







            }


        });


        //When edit button is clicked by the user.

        holder.itemView.findViewById(R.id.editButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Reading related data set number
                Password passwordTemp = PasswordList.getInstance().getPasswordList().get(position);
                CustomDialogEditMenu customDialogEditMenu = new CustomDialogEditMenu(view.getContext());
                customDialogEditMenu.setCustomDialogEditMenuListener(new CustomDialogEditMenuListener() {
                    @Override
                    public void onSaveClicked() {
                        /**
                         * TEMP CHANGES
                         */

                        String newTitle=customDialogEditMenu.getEditTitle().getText().toString();
                        String newContent=customDialogEditMenu.getEditContent().getText().toString();
                        String tableName="passwords";

                        int passwordId = PasswordList.getInstance().getPasswordList().get(position).getPasswordId();
                        //Updating dataset
                        DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                        SQLiteDatabase database = databaseHelper.getWritableDatabase();
                        String sql = "UPDATE " + tableName + " SET title = ?, content = ? WHERE id = ?";
                        SQLiteStatement statement = database.compileStatement(sql);
                        statement.bindString(1,newTitle);
                        statement.bindString(2,newContent);
                        statement.bindLong(3,passwordId);
                        statement.executeUpdateDelete();
                        database.close();
                        databaseHelper.updatePasswordData(view.getContext());
                        notifyDataSetChanged();
                        onCancelClicked();




                       AlertDialog.Builder alBuilder = new AlertDialog.Builder(view.getContext());
                       String title = "Are you sure about modifications ?";
                       String message ="Title will be ;" +"\n"+ passwordTemp.getTitle()+" --> "+customDialogEditMenu.getEditTitle().getText()+"\n"+
                               "Content will be ;" +"\n" +passwordTemp.getContent()+" --> "+customDialogEditMenu.getEditContent().getText();
                       alBuilder.setTitle(title);
                       alBuilder.setMessage(message);
                       alBuilder.setPositiveButton("I'm sure", new DialogInterface.OnClickListener() {
                           //If the user is sure about modifications.

                           @SuppressLint("NotifyDataSetChanged")
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               //Getting some necessary info.
                               String newTitle=customDialogEditMenu.getEditTitle().getText().toString();
                               String newContent=customDialogEditMenu.getEditContent().getText().toString();
                               String tableName="passwords";

                               int passwordId = PasswordList.getInstance().getPasswordList().get(position).getPasswordId();
                               //Updating dataset
                               DatabaseHelper databaseHelper = new DatabaseHelper(view.getContext());
                               SQLiteDatabase database = databaseHelper.getWritableDatabase();
                               String sql = "UPDATE " + tableName + " SET title = ?, content = ? WHERE id = ?";
                               SQLiteStatement statement = database.compileStatement(sql);
                               statement.bindString(1,newTitle);
                               statement.bindString(2,newContent);
                               statement.bindLong(3,passwordId);
                               statement.executeUpdateDelete();
                               database.close();
                               databaseHelper.updatePasswordData(view.getContext());
                               notifyDataSetChanged();
                               onCancelClicked();


                           }
                       });
                     alBuilder.setNegativeButton("Not yet", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialogInterface, int i) {
                             alBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                 @Override
                                 public void onCancel(DialogInterface dialogInterface) {

                                 }
                             });
                         }
                     });
                    // alBuilder.show();


                    }

                    @Override
                    public void onCancelClicked() {
                        customDialogEditMenu.cancel();

                    }

                    @Override
                    public void onWriteOldInformation() {
                        //Saving old data to ask user.
                        customDialogEditMenu.getEditContent().setText(passwordTemp.getContent());
                        customDialogEditMenu.getEditTitle().setText(passwordTemp.getTitle());


                    }
                });customDialogEditMenu.show();
            }
        });

        //Copy Icon Edition

        holder.itemView.findViewById(R.id.copyIcon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager) (view.getContext()).getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Copy", password.getContent().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(view.getContext(), view.getContext().getResources().getString(R.string.infoCopiedText), Toast.LENGTH_SHORT).show();
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
