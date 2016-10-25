package com.example.chmanish.nytimessearch.views;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.chmanish.nytimessearch.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * Created by chmanish on 10/22/16.
 */
public class ViewHolderImageText extends RecyclerView.ViewHolder{
    private TextView tvTitle;
    private DynamicHeightImageView ivImage;

    public Target target = new Target() {
        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            // Calculate the image ratio of the loaded bitmap
            float ratio = (float) bitmap.getHeight() / (float) bitmap.getWidth();
            // Set the ratio for the image
            ivImage.setHeightRatio(ratio);
            // Load the image into the view
            ivImage.setImageBitmap(bitmap);
        }

        @Override
        public void onBitmapFailed(Drawable d) {
            // Fires if bitmap couldn't be loaded.
        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }
    };

    public ViewHolderImageText(View v) {
        super(v);
        tvTitle = (TextView) v.findViewById(R.id.tvTitle);
        ivImage = (DynamicHeightImageView) v.findViewById(R.id.ivImage);
    }

    public TextView getTextView() {
        return tvTitle;
    }

    public void setTextView(TextView label1) {
        this.tvTitle = label1;
    }

    public DynamicHeightImageView getImageView() {
        return ivImage;
    }

    public void setImageView (DynamicHeightImageView iv) {
        this.ivImage = iv;
    }



}
