package com.erdemserhat.ultimatebox.random_password_generator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.erdemserhat.ultimatebox.R;

public class SyncronizationAdapter extends RecyclerView.Adapter<SyncronizationAdapter.ViewHolder> {

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.recycler_synchronization_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SyncronizationAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView syncTextView;
        private final TextView importTextView;
        private final TextView exportTextView;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            syncTextView = itemView.findViewById(R.id.syncTextView);
            importTextView = itemView.findViewById(R.id.importTextView);
            exportTextView = itemView.findViewById(R.id.exportTextView);

        }

    }
}
