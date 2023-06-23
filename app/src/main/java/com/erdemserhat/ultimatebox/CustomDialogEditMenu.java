package com.erdemserhat.ultimatebox;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
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


    }
}
