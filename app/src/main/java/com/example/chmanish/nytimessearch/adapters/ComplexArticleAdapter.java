package com.example.chmanish.nytimessearch.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chmanish.nytimessearch.R;
import com.example.chmanish.nytimessearch.models.Article;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by chmanish on 10/22/16.
 */
public class ComplexArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int TEXT_ONLY = 0, IMAGE = 1;
    // Store a member variable for the articles
    private List<Article> mArticles;
    // Store the context for easy access
    private Context mContext;

    // Pass in the article array into the constructor
    public ComplexArticleAdapter(Context context, List<Article> articles) {
        mArticles = articles;
        mContext = context;
    }

    // Easy access to the context object in the recyclerview
    private Context getContext() {
        return mContext;
    }

    //Returns the view type of the item at position for the purposes of view recycling.
    @Override
    public int getItemViewType(int position) {
        boolean isStringEmpty = (mArticles.get(position).getThumbNail()).isEmpty();
        int val;
        if (!isStringEmpty) {
            val = IMAGE;
        } else {
            val = TEXT_ONLY;
        }
        return val;
    }

    /**
     * This method creates different RecyclerView.ViewHolder objects based on the item view type.\
     *
     * @param viewGroup ViewGroup container for the item
     * @param viewType type of view to be inflated
     * @return viewHolder to be inflated
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case IMAGE:
                View v1 = inflater.inflate(R.layout.item_article_image_text, viewGroup, false);
                viewHolder = new ViewHolder1(v1);
                break;
            case TEXT_ONLY:
                View v2 = inflater.inflate(R.layout.item_article_text, viewGroup, false);
                viewHolder = new ViewHolder2(v2);
                break;
            default:
                View v3 = inflater.inflate(R.layout.item_article_text, viewGroup, false);
                viewHolder = new ViewHolder2(v3);
                break;

        }
        return viewHolder;
    }

    /**
     * This method internally calls onBindViewHolder(ViewHolder, int) to update the
     * RecyclerView.ViewHolder contents with the item at the given position
     * and also sets up some private fields to be used by RecyclerView.
     *
     * @param viewHolder The type of RecyclerView.ViewHolder to populate
     * @param position Item position in the viewgroup.
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        Article article = mArticles.get(position);
        switch (viewHolder.getItemViewType()) {
            case IMAGE:
                ViewHolder1 vh1 = (ViewHolder1) viewHolder;
                vh1.getTextView().setText(article.getHeadline());
                vh1.getImageView().setImageResource(0);
                String thumbnail = article.getThumbNail();
                Picasso.with(getContext()).load(thumbnail).into(vh1.getImageView());
                break;
            default: //case TEXT_ONLY:
                ViewHolder2 vh2 = (ViewHolder2) viewHolder;
                vh2.getTextView().setText(article.getHeadline());

                break;

        }
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mArticles.size();
    }


}
