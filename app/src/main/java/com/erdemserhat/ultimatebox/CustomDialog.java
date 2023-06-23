package com.erdemserhat.ultimatebox;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public   class CustomDialog extends Dialog {
    //Data members.
    private EditText title;
    private Button saveButton;
    private Button cancelButton;
    //Call back design for manipulating data in a class.
    private CustomDialogListener listener;

    // Needed Getters and Setters



    public void setCustomDialogListener(CustomDialogListener listener) {
        this.listener = listener;
    }

    public String  getPasswordTitle(){
        return title.getText().toString();
    }

    //Default Constructor
    public CustomDialog(Context context,CustomDialogListener listener) {
        super(context);
        this.listener=listener;

    }

    public CustomDialog(Context context) {
        super(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog_layout);

        //Initializing views.
        title=findViewById(R.id.titleEditText);
        saveButton=findViewById(R.id.saveButton);
        cancelButton=findViewById(R.id.cancelButton);


        //TODO: implements save button details...
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                listener.onSaveClicked();
                dismiss();


            }
        });


        //TODO: implements save button details...
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCancelClicked();
                dismiss();
            }
        });



    }

    //When buttons are touched by the user.
    private void handleSaveClicked() {
        if (listener != null) {
            listener.onSaveClicked();
        }
    }

    private void handleCancelClicked() {
        if (listener != null) {
            listener.onCancelClicked();
        }
    }

}
