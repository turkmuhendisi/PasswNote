package com.erdemserhat.ultimatebox;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public  class CustomDialog extends Dialog {
    private EditText title;
    private Button saveButton;
    private Button cancelButton;

    public CustomDialog(@NonNull Context context) {
        super(context);
    }

    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
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
                //...
                dismiss();
            }
        });


        //TODO: implements save button details...
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //...
                dismiss();
            }
        });



    }
}
