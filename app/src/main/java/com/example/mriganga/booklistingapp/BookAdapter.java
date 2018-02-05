package com.example.mriganga.booklistingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Mriganga on 5/26/2017.
 */
public class BookAdapter extends ArrayAdapter<BookUtility> {
    public BookAdapter(Context context, int resource, ArrayList<BookUtility> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View list_item_view = convertView;
        if(list_item_view==null){
            list_item_view = LayoutInflater.from(getContext()).inflate(R.layout.first_layout,parent,false);
        }
        BookUtility bookUtility= getItem(position);
        if(bookUtility.getTitle()!=null) {
            TextView title = (TextView) list_item_view.findViewById(R.id.title);
            title.setText(bookUtility.getTitle());
        }
        else{
            TextView title = (TextView) list_item_view.findViewById(R.id.title);
            title.setVisibility(View.GONE);
        }
        if(bookUtility.getAuthor()!=null) {
            TextView author = (TextView) list_item_view.findViewById(R.id.author);
            author.setText(bookUtility.getAuthor());
        }
        else{
            TextView author = (TextView) list_item_view.findViewById(R.id.author);
            author.setVisibility(View.GONE);
        }
        if(bookUtility.getCategory()!=null) {
            TextView category = (TextView) list_item_view.findViewById(R.id.category);
            category.setText(bookUtility.getCategory());
        }
        else{
            TextView category = (TextView) list_item_view.findViewById(R.id.category);
            category.setVisibility(View.GONE);
        }
        return list_item_view;
    }
}
