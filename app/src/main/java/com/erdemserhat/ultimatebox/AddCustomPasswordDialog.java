package com.erdemserhat.ultimatebox;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

public class AddCustomPasswordDialog extends Dialog {

    //Declaring the Edit Text views
    private EditText passwordTitleEditText;
    private EditText passwordContentEditText;
    private Button saveButton;
    private Button cancelButton;
    private CustomDialogListener customDialogListener;


    public AddCustomPasswordDialog(@NonNull Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Inflating the xml with current class
        setContentView(R.layout.add_custom_password_dialog);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        //Assigning the the Edit Texts.

        passwordTitleEditText=findViewById(R.id.AddCustomPasswordTitle);
        passwordContentEditText=findViewById(R.id.AddCustomPasswordContent);
        saveButton= findViewById(R.id.AddCustomPasswordSaveButton);
        cancelButton= findViewById(R.id.AddCustomPasswordCancelButton);

        //When save button is clicked...

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialogListener.onSaveClicked();

            }
        });

        //When cancel button is clicked...

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialogListener.onCancelClicked();
            }
        });


    }

    //Getters and Setters

    public CustomDialogListener getCustomDialogListener() {
        return customDialogListener;
    }

    public void setCustomDialogListener(CustomDialogListener customDialogListener) {
        this.customDialogListener = customDialogListener;
    }

    public EditText getPasswordTitleEditText() {
        return passwordTitleEditText;
    }

    public void setPasswordTitleEditText(EditText passwordTitleEditText) {
        this.passwordTitleEditText = passwordTitleEditText;
    }

    public EditText getPasswordContentEditText() {
        return passwordContentEditText;
    }

    public void setPasswordContentEditText(EditText passwordContentEditText) {
        this.passwordContentEditText = passwordContentEditText;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public void setSaveButton(Button saveButton) {
        this.saveButton = saveButton;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(Button cancelButton) {
        this.cancelButton = cancelButton;
    }
}
