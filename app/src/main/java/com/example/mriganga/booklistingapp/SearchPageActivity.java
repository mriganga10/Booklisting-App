package com.example.mriganga.booklistingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        Button b = (Button)findViewById(R.id.search_button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText insert_text = (EditText)findViewById(R.id.insert_text);
                Intent i = new Intent(SearchPageActivity.this,BooksListActivity.class);
                i.putExtra("url",insert_text.getText().toString());
                startActivity(i);
            }
        });
    }
}
