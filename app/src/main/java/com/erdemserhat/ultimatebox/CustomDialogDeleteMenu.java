package com.erdemserhat.ultimatebox;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


    //Contractors.
    public class CustomDialogDeleteMenu extends Dialog {
    public CustomDialogDeleteMenu(@NonNull Context context) {
        super(context);
    }

    public CustomDialogDeleteMenu(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected CustomDialogDeleteMenu(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

        // End of Contractors.
}
