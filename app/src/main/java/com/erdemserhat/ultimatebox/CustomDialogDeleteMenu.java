package com.erdemserhat.ultimatebox;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


    //Contractors.
    public class CustomDialogDeleteMenu extends Dialog {

        private Button deleteButton;
        private Button cancelButton;
        private TextView deleteWarningText;
        private String passwordTitle;
        private CustomDialogDeleteMenuListener customDialogDeleteMenuListener;
        private String tempMsg;



    public CustomDialogDeleteMenu(Context context) {
        super(context);
        tempMsg=context.getApplicationContext().getResources().getString(R.string.delete_prompt);


    }

    // End of Contractors.


        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            //Setting class's content's view.
            setContentView(R.layout.custom_dialog_delete_menu);
            getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

            //Assigning view component's id.

            deleteButton=findViewById(R.id.deleteButton);
            cancelButton=findViewById(R.id.cancelButton);
            deleteWarningText=findViewById(R.id.delete_warning_text);
            deleteWarningText.setText(passwordTitle+" "+tempMsg);


            //Delete Button Implementation..
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    customDialogDeleteMenuListener.onDelete();
                }
            });

            //Cancel Button Implementation..
            cancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    customDialogDeleteMenuListener.onCancel();
                }
            });


        }

        //Getters and Setters


        public Button getDeleteButton() {
            return deleteButton;
        }

        public void setDeleteButton(Button deleteButton) {
            this.deleteButton = deleteButton;
        }

        public Button getCancelButton() {
            return cancelButton;
        }

        public void setCancelButton(Button cancelButton) {
            this.cancelButton = cancelButton;
        }

        public TextView getDeleteWarningText() {
            return deleteWarningText;
        }

        public void setDeleteWarningText(TextView deleteWarningText) {
            this.deleteWarningText = deleteWarningText;
        }

        public CustomDialogDeleteMenuListener getCustomDialogDeleteMenuListener() {
            return customDialogDeleteMenuListener;
        }

        public void setCustomDialogDeleteMenuListener(CustomDialogDeleteMenuListener customDialogDeleteMenuListener) {
            this.customDialogDeleteMenuListener = customDialogDeleteMenuListener;
        }

        public void setPasswordTitle(String passwordTitle){
        this.passwordTitle=passwordTitle;
        }
    }
