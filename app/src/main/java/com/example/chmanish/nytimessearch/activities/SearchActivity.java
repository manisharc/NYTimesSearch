package com.example.chmanish.nytimessearch.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.chmanish.nytimessearch.R;
import com.example.chmanish.nytimessearch.SpacesItemDecoration;
import com.example.chmanish.nytimessearch.adapters.ArticleAdapter;
import com.example.chmanish.nytimessearch.adapters.EndlessRecyclerViewScrollListener;
import com.example.chmanish.nytimessearch.fragments.EditFilterDialogFragment;
import com.example.chmanish.nytimessearch.models.Article;
import com.example.chmanish.nytimessearch.models.Filter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class SearchActivity extends AppCompatActivity implements EditFilterDialogFragment.EditFilterDialogListener {
    //GridView gvResults;
    Filter filterSet;
    ArrayList<Article> articles;
    ArticleAdapter adapter;
    static String querySavedForPagination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("New York Times Search");
        setupViews();
    }

    public void setupViews(){
        RecyclerView rvArticles = (RecyclerView) findViewById(R.id.rvResults);
        SpacesItemDecoration decoration = new SpacesItemDecoration(16);
        rvArticles.addItemDecoration(decoration);
        articles = new ArrayList<>();
        adapter = new ArticleAdapter(this, articles);
        rvArticles.setAdapter(adapter);
        // First param is number of columns and second param is orientation i.e Vertical or Horizontal
        StaggeredGridLayoutManager gridLayoutManager =
                new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        // Attach the layout manager to the recycler view
        rvArticles.setLayoutManager(gridLayoutManager);
        //rvArticles.setLayoutManager(new LinearLayoutManager(this));


        //hook up listener for grid click
        adapter.setOnItemClickListener(new ArticleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i = new Intent(getApplicationContext(), ArticleActivity.class);
                Article article = articles.get(position);
                i.putExtra("url", article.getWebUrl());
                startActivity(i);
            }
        });

        // Attach the listener to the AdapterView onCreate
        rvArticles.addOnScrollListener(new EndlessRecyclerViewScrollListener(gridLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to your AdapterView
                //customLoadMoreDataFromApi(page);
                makeQueryAndUpdateView(page-1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // perform query here
                //adapter.clear();
                //adapter.notifyDataSetChanged();
                querySavedForPagination = query;
                makeQueryAndUpdateView(0);

                // workaround to avoid issues with some emulators and keyboard devices firing twice if a keyboard enter is used
                // see https://code.google.com/p/android/issues/detail?id=24599
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_filter) {
            FragmentManager fm = getSupportFragmentManager();
            EditFilterDialogFragment editNameDialogFragment = EditFilterDialogFragment.newInstance();
            editNameDialogFragment.show(fm, "fragment_edit_filter");

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFinishEditDialog(Filter filterValues) {
        filterSet = new Filter(filterValues);

    }

    public void makeQueryAndUpdateView(int page){
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.nytimes.com/svc/search/v2/articlesearch.json";

        RequestParams params = new RequestParams();
        params.put("api-key", "0615376bbf4c4801a036cccbc32d58f0");
        params.put("page", page);
        params.put("q", querySavedForPagination);

        if(filterSet != null){
            if(filterSet.getDate() != null)
                params.put("begin_date", filterSet.getDate());
            if (filterSet.isSortOldest())
                params.put("sort", "oldest");
            if(filterSet.getNewsDeskString() != null){
                params.put("fq", filterSet.getNewsDeskString());
            }

        }
        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray articleJsonResults = null;
                try{
                    int curSize = adapter.getItemCount();
                    articleJsonResults = response.getJSONObject("response").getJSONArray("docs");

                    articles.addAll(Article.fromJSONArray(articleJsonResults));
                    adapter.notifyItemRangeInserted(curSize, (Article.fromJSONArray(articleJsonResults)).size());
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });


    }

}
