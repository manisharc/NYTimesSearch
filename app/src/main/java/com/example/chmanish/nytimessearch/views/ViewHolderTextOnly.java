package com.example.chmanish.nytimessearch.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.chmanish.nytimessearch.R;

/**
 * Created by chmanish on 10/22/16.
 */
public class ViewHolderTextOnly extends RecyclerView.ViewHolder{
    private TextView tvTitle;

    // Define listener member variable
    private static OnItemClickListener listener;
    // Define the listener interface
    public interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
    // Define the method that allows the parent activity or fragment to define the listener
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public ViewHolderTextOnly(View v) {
        super(v);
        tvTitle = (TextView) v.findViewById(R.id.tvTitle);

        // Setup the click listener
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Triggers click upwards to the adapter on click
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(itemView, position);
                    }
                }
            }
        });
    }

    public TextView getTextView() {
        return tvTitle;
    }

    public void setTextView(TextView label1) {
        this.tvTitle = label1;
    }



}
