package com.example.mriganga.booklistingapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by Mriganga on 5/26/2017.
 */
public class BooklistLoader extends AsyncTaskLoader<ArrayList<BookUtility>> {
    public static final String LOG_CAT = BooklistLoader.class.getSimpleName();
    String BOOK_MAIN_URL;
    public BooklistLoader(Context context,String  url) {
        super(context);
        this.BOOK_MAIN_URL = url;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }
    @Override
    public ArrayList<BookUtility> loadInBackground() {
        ArrayList<BookUtility> booklist= null;
        URL url = null;
        String json = null;
        url = makeurlobject(BOOK_MAIN_URL);

        try {
            json = makeHttprequest(url);
           if(json!=null){
                try {
                    booklist = ExtractBookUtility.Extractbookutility(json);
                } catch (JSONException e) {
                    Log.e(LOG_CAT,"couldnt extract book utility",e);
                }
            }
        } catch (IOException e) {
            Log.e(LOG_CAT,"background problem",e);
        }
        Log.v(LOG_CAT,""+json);
        return booklist;
    }
    public URL makeurlobject(String googlebooks){
        URL url1 = null;
        try {
            url1 = new URL(googlebooks);
        } catch (MalformedURLException e) {
            Log.e(LOG_CAT,"couldnt make url object",e);
        }
        return url1;
    }
    public String makeHttprequest(URL url)throws IOException{
        String json = null;
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try{
            urlConnection = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            Log.v(LOG_CAT,""+urlConnection.getResponseCode());
           // urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            json = readfrominput(inputStream);
        }
        catch (IOException e){
            Log.e(LOG_CAT,"couldnt make http request"+" "+urlConnection.getResponseCode(),e);
        }
        finally {
            if(json!=null)
                urlConnection.disconnect();
            if(inputStream!=null)
                inputStream.close();
        }
        return json;
    }
    public String readfrominput(InputStream inputStream)throws IOException{
        StringBuilder sb = new StringBuilder();
        String output = null;
        if(inputStream!=null){
            InputStreamReader isr = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            while(line!=null){
                sb.append(line);
                line = br.readLine();
            }
        }
        output = sb.toString();
        return output;
    }
}
