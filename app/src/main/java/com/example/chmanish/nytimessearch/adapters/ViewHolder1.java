package com.example.chmanish.nytimessearch.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chmanish.nytimessearch.R;

/**
 * Created by chmanish on 10/22/16.
 */
public class ViewHolder1 extends RecyclerView.ViewHolder {
    private TextView tvTitle;
    private ImageView ivImage;


    public ViewHolder1(View v) {
        super(v);
        tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        ivImage = (ImageView) v.findViewById(R.id.ivImage);
    }

    public TextView getTextView() {
        return tvTitle;
    }

    public void setTextView(TextView label1) {
        this.tvTitle = label1;
    }

    public ImageView getImageView() {
        return ivImage;
    }

    public void setImageView (ImageView iv) {
        this.ivImage = iv;
    }

}
