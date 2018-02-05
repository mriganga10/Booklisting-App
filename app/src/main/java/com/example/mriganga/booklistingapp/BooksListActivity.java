package com.example.mriganga.booklistingapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class BooksListActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<BookUtility>> {
    public static BookUtility bookUtility;
    final String LOG_TAG = BooksListActivity.class.getSimpleName();
    TextView emptyview;
    private String search_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books_list);
        //search_url = extracturl();
        ConnectivityManager cm = (ConnectivityManager) BooksListActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected == true && !getIntent().getExtras().getString("url").isEmpty()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(0, null, BooksListActivity.this);
        }
        // Log.v(LOG_TAG,""+search_url);
        else {
            if (getIntent().getExtras().getString("url").isEmpty()) {
                ProgressBar pb = (ProgressBar) findViewById(R.id.progress);
                pb.setVisibility(View.GONE);
                TextView t = (TextView) findViewById(R.id.no_internet_connection);
                t.setText("Please provide valid Input");
            } else {
                ProgressBar pb = (ProgressBar) findViewById(R.id.progress);
                pb.setVisibility(View.GONE);
                TextView t = (TextView) findViewById(R.id.no_internet_connection);
                t.setText("NO INTERNET CONNECTION");
            }
        }
    }

    public String extracturl() {
        String basicurl = "https://www.googleapis.com/books/v1/volumes?q=";
        String book_name = getIntent().getExtras().getString("url");
        String main_url = basicurl;
        String[] intermediate_name = book_name.split(" ");
        for (int i = 0; i < intermediate_name.length; i++) {
            if (i != 0)
                main_url = "" + main_url + "%20" + intermediate_name[i];
            else
                main_url = "" + main_url + intermediate_name[i];
        }
        return main_url;
    }

    public void updateui(ArrayList<BookUtility> bookUtilities) {
        ArrayList<BookUtility> booklist = bookUtilities;
        final BookAdapter bookAdapter = new BookAdapter(BooksListActivity.this, R.layout.first_layout, booklist);
        ListView lv = (ListView) findViewById(R.id.list);
        lv.setAdapter(bookAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                bookUtility = bookAdapter.getItem(i);
                Intent j = new Intent(BooksListActivity.this, Second_layout.class);
                startActivity(j);
            }
        });
    }

    @Override
    public Loader<ArrayList<BookUtility>> onCreateLoader(int i, Bundle bundle) {
        search_url = extracturl();
        return new BooklistLoader(this, search_url);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<BookUtility>> loader, ArrayList<BookUtility> o) {
            ProgressBar pb = (ProgressBar) findViewById(R.id.progress);
            pb.setVisibility(View.GONE);
        updateui(o);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<BookUtility>> loader) {

    }
}
