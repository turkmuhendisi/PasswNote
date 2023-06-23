package com.erdemserhat.ultimatebox;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
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
    private String previousTitle;
    private String previousContent;
    private String editedTitle;
    private String editedContent;

    private  CustomDialogListener listener;


    /**
     * Default constructor for instantiate a reference of CustomDialogEditMenu class
     * @param context the content which you use this custom dialog.
     */
    public CustomDialogEditMenu(Context context) {
        super(context);
        this.previousContent= editedContent.toString();
        this.previousTitle= editedTitle.toString();
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

    public CustomDialogListener getListener() {
        return listener;
    }

    public void setListener(CustomDialogListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Setting class's content's view.
        setContentView(R.layout.custom_dialog_edit_menu);

        //Assigning the related views.

        editContent=findViewById(R.id.titleEditMenu);
        editTitle=findViewById(R.id.contentEditMenu);
        saveButton=findViewById(R.id.saveEditMenu);
        cancelButton=findViewById(R.id.cancelEditMenu);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //listener.onSaveClicked();
                editedTitle=editTitle.toString();
                editedContent=editedContent.toString();
                String alertMessage=previousTitle +" --> " + editedTitle +"\n"+
                        previousContent +" --> " + editedContent;
                String alertTitle="Do you approve the changes?";

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle(alertTitle);
                builder.setMessage(alertMessage);
                //Continue.......





            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //listener.onCancelClicked();
            }
        });


    }
}
