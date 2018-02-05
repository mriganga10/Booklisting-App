package com.example.mriganga.booklistingapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Mriganga on 5/22/2017.
 */
class ExtractBookUtility {
    private final static String LOG_EXTRACTBOOKUTILITY = ExtractBookUtility.class.getSimpleName();
    private ExtractBookUtility(){

    }
    public static ArrayList<BookUtility> Extractbookutility(String api_string) throws JSONException {
        ArrayList<BookUtility> bookUtilitiesdata = new ArrayList<BookUtility>();
        JSONObject root = null;
        try {
            root = new JSONObject(api_string);
        }
        catch (JSONException e){
            Log.e(LOG_EXTRACTBOOKUTILITY,"ERROR FETCHING JSON OBJECT FROM API STRING",e);
        }
        if(root!=null){
            JSONArray items = root.getJSONArray("items");
            String title=null;String author;String language = null;String epub_available = null;String pdf_available =null;String publisher=null;String published_date=null;String category;String Description=null;String pagecount=null;String imageurl=null;String epub_download_link=null;String pdf_download_link=null;String Buy_link=null;String Retail_price=null;float rating= -5;String preview_link = null;
            for(int i=0;i<items.length();i++){
                title=null;author="";language =null;epub_available = null;pdf_available =null;preview_link = null;publisher=null;published_date=null;category="";Description=null;pagecount=null;imageurl=null;epub_download_link=null;pdf_download_link=null;Buy_link=null;Retail_price=null;rating = -5;
                JSONObject book_items = items.getJSONObject(i);
                JSONObject volume_info = book_items.getJSONObject("volumeInfo");

                try{//get book title
                    title = volume_info.getString("title");
                }
                catch (JSONException e){
                    Log.e(LOG_EXTRACTBOOKUTILITY,"couldnt fetch title",e);
                    title= null;
                }

                try{//get author
                    JSONArray book_author = volume_info.getJSONArray("authors");
                    for(int j=0;j<book_author.length();j++){
                        String authors;
                        authors = book_author.getString(j);
                        if(j!=0)
                        author = ""+author+"\n"+authors;
                        else
                            author = author+authors;
                    }
                }
                catch (JSONException e){
                    Log.e(LOG_EXTRACTBOOKUTILITY,"couldnt fetch book author",e);
                    author = null;
                }
                try{//get description
                    Description = volume_info.getString("description");
                }
                catch (JSONException e){
                    Log.e(LOG_EXTRACTBOOKUTILITY,"description set fetching error",e);
                    Description = null;
                }
                try{//get publisher name
                    publisher = volume_info.getString("publisher");
                }
                catch (JSONException e){
                    Log.e(LOG_EXTRACTBOOKUTILITY,"couldnt fetch publisher",e);
                    publisher = null;
                }

                try{//get published date
                    published_date = volume_info.getString("publishedDate");
                }
                catch (JSONException e){
                    Log.e(LOG_EXTRACTBOOKUTILITY,"couldnt fetch published date",e);
                    published_date = null;
                }

                try{//get category of book
                    JSONArray category_array = volume_info.getJSONArray("categories");
                    for(int j=0;j<category_array.length();j++){
                        String categorys;
                        categorys = category_array.getString(j);
                        if(j!=0)
                        category  = ""+category+"\n"+categorys;
                        else
                            category  = category+categorys;
                    }
                }
                catch (JSONException e){
                    Log.e(LOG_EXTRACTBOOKUTILITY,"couldnt fetch category",e);
                    category = null;
                }

                try {// get no of pages
                    pagecount = volume_info.getString("pageCount");
                }
                catch(JSONException e){
                    Log.e(LOG_EXTRACTBOOKUTILITY,"couldnt fetch pagecount",e);
                    pagecount = null;
                }

                try{//get image url
                    JSONObject imageurlobject = volume_info.getJSONObject("imageLinks");
                    imageurl = imageurlobject.getString("thumbnail");
                }
                catch (JSONException e) {
                    Log.e(LOG_EXTRACTBOOKUTILITY, "couldnt fetch image url", e);
                    imageurl = null;
                }

                try{//get preview link
                    preview_link = volume_info.getString("previewLink");
                }
                catch (JSONException e){
                    Log.e(LOG_EXTRACTBOOKUTILITY,"couldnt fetch preview link",e);
                    preview_link = null;
                }

                try{//get language
                    language = volume_info.getString("language");
                }
                catch (JSONException e){
                    Log.e(LOG_EXTRACTBOOKUTILITY,"language set fetching error",e);
                    language = null;
                }
                try {//get rating of books
                    double rating_count = volume_info.getDouble("averageRating");
                    rating = (float)rating_count;
                }
                catch(JSONException e){
                    Log.e(LOG_EXTRACTBOOKUTILITY,"couldnt fetch rating of book",e);
                    rating = -5;
                }
                JSONObject access = book_items.getJSONObject("accessInfo");

               /* try{//get epub availability
                    JSONObject epubobject = access.getJSONObject("epub");
                    if(epubobject.getBoolean("isAvailable")){
                        epub_available = "EPUB AVAILABLE";
                    }
                }
                catch (JSONException e){
                    Log.e(LOG_EXTRACTBOOKUTILITY,"epub availability fetching error",e);
                    epub_available = null;
                }

                try{//get epub download link
                    JSONObject epubobject = access.getJSONObject("epub");
                    epub_download_link = epubobject.getString("acsTokenLink");
                }
                catch(JSONException e){
                    Log.e(LOG_EXTRACTBOOKUTILITY,"epub download link not available",e);
                    epub_download_link = null;
                }
                ///////////////////
                try{//get pdf availability
                    JSONObject pdfobject = access.getJSONObject("pdf");
                    if(pdfobject.getBoolean("isAvailable")){
                        pdf_available = "PDF AVAILABLE";
                    }
                }
                catch (JSONException e){
                    Log.e(LOG_EXTRACTBOOKUTILITY,"pdf availability fetching error",e);
                    pdf_available = null;
                }

                try{//get pdf download link
                    JSONObject pdfobject = access.getJSONObject("pdf");
                    pdf_download_link = pdfobject.getString("acsTokenLink");
                }
                catch(JSONException e){
                    Log.e(LOG_EXTRACTBOOKUTILITY,"pdf download link not available",e);
                    pdf_download_link = null;
                }
               */

                BookUtility util = new BookUtility();
                util.setAuthor(author);
                util.setCategory(category);
                util.setDescription(Description);
               // util.setEpub_available(epub_available);
                util.setImageurl(imageurl);
                util.setLanguage(language);
               // util.setEpub_download_link(epub_download_link);
              //  util.setPdf_available(pdf_available);
               // util.setPdf_download_link(pdf_download_link);
                util.setPreview_link(preview_link);
                util.setRating(rating);
                util.setPublished_date(published_date);
                util.setPublisher(publisher);
                util.setTitle(title);
                util.setPagecount(pagecount);
                bookUtilitiesdata.add(util);
            }
        }
        return bookUtilitiesdata;
    }
}
