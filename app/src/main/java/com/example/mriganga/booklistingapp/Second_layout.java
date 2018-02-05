package com.example.mriganga.booklistingapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class Second_layout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_layout);
        final BookUtility bookuttt = BooksListActivity.bookUtility;
        if(bookuttt.getImageurl()!=null) {
            new DownloadImageTask((ImageView) findViewById(R.id.image))
                    .execute(bookuttt.getImageurl());
        }
        if(bookuttt.getTitle()!=null) {
            TextView title = (TextView) findViewById(R.id.second_title);
            title.setText(bookuttt.getTitle());
        }
        else{
            TextView title = (TextView) findViewById(R.id.second_title);
            title.setVisibility(View.GONE);
        }
        if(bookuttt.getAuthor()!=null) {
            TextView author = (TextView) findViewById(R.id.second_author);
            author.setText(bookuttt.getAuthor());
        }
        else{
            View author = (TextView) findViewById(R.id.second_author);
            author.setVisibility(View.GONE);
        }
        if(bookuttt.getCategory()!=null) {
            TextView category = (TextView) findViewById(R.id.second_category);
            category.setText(bookuttt.getCategory());
        }
        else{
            TextView category = (TextView) findViewById(R.id.second_category);
            category.setVisibility(View.GONE);
        }
        if(bookuttt.getLanguage()!=null) {
            TextView language = (TextView) findViewById(R.id.second_language);
            language.setText("Language: "+bookuttt.getLanguage());
        }
        else{
            TextView language = (TextView) findViewById(R.id.second_language);
            language.setVisibility(View.GONE);
        }
        if(bookuttt.getDescription()!=null) {
            TextView description = (TextView) findViewById(R.id.second_description);
            description.setText(bookuttt.getDescription());
        }
        else{
            TextView description = (TextView) findViewById(R.id.second_description);
            description.setVisibility(View.GONE);
        }
        if(bookuttt.getPagecount()!=null) {
            TextView nop = (TextView) findViewById(R.id.second_no_of_page);
            nop.setText("NO OF PAGES: "+bookuttt.getPagecount());
        }
        else{
            TextView nop = (TextView) findViewById(R.id.second_no_of_page);
            nop.setVisibility(View.GONE);
        }
        if(bookuttt.getPublisher()!=null) {
             TextView  pu = (TextView)findViewById(R.id.second_publisher);
            pu.setText(bookuttt.getPublisher());
        }
        else{
            TextView  pu = (TextView)findViewById(R.id.second_publisher);
            pu.setVisibility(View.GONE);
        }
        if(bookuttt.getPublished_date()!=null) {
            TextView pd = (TextView) findViewById(R.id.second_publisher_date);
            pd.setText(bookuttt.getPublished_date());
        }
        else{
            TextView pd = (TextView) findViewById(R.id.second_publisher_date);
            pd.setVisibility(View.GONE);
        }
        if(bookuttt.getRating()!=-5){
            RatingBar r =(RatingBar)findViewById(R.id.second_rating);
            r.setMax(5);
            r.setNumStars((int)bookuttt.getRating());
            r.setRating(bookuttt.getRating());
        }
        else{
            RatingBar r =(RatingBar)findViewById(R.id.second_rating);
            r.setVisibility(View.GONE);
        }
        Button preview = (Button)findViewById(R.id.second_preview);
        Button preview_link = (Button) findViewById(R.id.second_preview_link);
        if(bookuttt.getPreview_link()!=null) {
            preview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Convert the String URL into a URI object (to pass into the Intent constructor)
                    Uri bookUri = Uri.parse(bookuttt.getPreview_link());

                    // Create a new intent to view the earthquake URI
                    Intent websiteIntent = new Intent(Intent.ACTION_VIEW, bookUri);

                    // Send the intent to launch a new activity
                    startActivity(websiteIntent);
                }
            });

            preview_link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Second_layout.this,Last_Activity.class);
                    i.putExtra("link",bookuttt.getPreview_link());
                    startActivity(i);
                }

            });
        }
        else{
            preview.setVisibility(View.GONE);
            preview_link.setVisibility(View.GONE);
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(Bitmap.createScaledBitmap(result, 400, 400, false));
        }


    }
}
