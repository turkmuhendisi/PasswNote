package com.erdemserhat.ultimatebox;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CustomDialogEditMenu extends Dialog {
    /*
    Declared to necessary views to assign, declared here because we need to use whole views anywhere of this class.
     */

    private EditText editContent;
    private EditText editTitle;
    private Button saveButton;
    private Button cancelButton;
    //pw data's

    //Previous data
    private String previousTitle;
    private String previousContent;
    //Edited datas
    private String editedTitle;
    private String editedContent;

    private  CustomDialogEditMenuListener customDialogEditMenuListener;


    /**
     * Default constructor for instantiate a reference of CustomDialogEditMenu class
     * @param context the content which you use this custom dialog.
     */
    public CustomDialogEditMenu(Context context) {
        super(context);

    }

    public String getPreviousTitle() {
        return previousTitle;
    }

    public void setPreviousTitle(String previousTitle) {
        this.previousTitle = previousTitle;
    }

    public String getPreviousContent() {
        return previousContent;
    }

    public void setPreviousContent(String previousContent) {
        this.previousContent = previousContent;
    }

    public String getEditedTitle() {
        return editedTitle;
    }

    public void setEditedTitle(String editedTitle) {
        this.editedTitle = editedTitle;
    }

    public String getEditedContent() {
        return editedContent;
    }

    public void setEditedContent(String editedContent) {
        this.editedContent = editedContent;
    }


    public EditText getEditContent() {
        return editContent;
    }

    public void setEditContent(EditText editContent) {
        this.editContent = editContent;
    }

    public EditText getEditTitle() {
        return editTitle;
    }

    public void setEditTitle(EditText editTitle) {
        this.editTitle = editTitle;
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

    public CustomDialogEditMenuListener getCustomDialogEditMenuListener() {
        return customDialogEditMenuListener;
    }

    public void setCustomDialogEditMenuListener(CustomDialogEditMenuListener customDialogEditMenuListener) {
        this.customDialogEditMenuListener = customDialogEditMenuListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting class's content's view.
        setContentView(R.layout.custom_dialog_edit_menu);

        //Assigning the related views.

        editContent=findViewById(R.id.contentEditMenu);
        editTitle=findViewById(R.id.titleEditMenu);
        saveButton=findViewById(R.id.saveEditMenu);
        cancelButton=findViewById(R.id.cancelEditMenu);
        customDialogEditMenuListener.onWriteOldInformation();



        //Save button implementation...
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               customDialogEditMenuListener.onSaveClicked();


            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customDialogEditMenuListener.onCancelClicked();
            }
        });


    }

}
