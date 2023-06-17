package com.erdemserhat.ultimatebox;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryPasswordAdapter extends RecyclerView.Adapter<HistoryPasswordAdapter.PasswordHolder>{
    @NonNull
    @Override
    public PasswordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PasswordHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PasswordHolder extends RecyclerView.ViewHolder{
        public PasswordHolder(View itemView){
            super(itemView);
        }
    }
}
