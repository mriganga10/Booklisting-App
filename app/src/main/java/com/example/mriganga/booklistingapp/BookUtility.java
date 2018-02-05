package com.example.mriganga.booklistingapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Mriganga on 5/22/2017.
 */
public class BookUtility {
    String title=null;
    String author=null;
    String publisher=null;
    String published_date=null;
    String category=null;
    String Description=null;
    String pagecount=null;
    String imageurl=null;
    String epub_available = null;
    String pdf_available =null;
    String epub_download_link=null;
    String pdf_download_link=null;
 //   String Buy_link=null;
  //  String Retail_price=null;
    float rating= -5;
    String preview_link = null;
    String language = null;

    public void setPdf_available(String pdf_available) {
        this.pdf_available = pdf_available;
    }

    public void setEpub_available(String epub_available) {
        this.epub_available = epub_available;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPreview_link(String preview_link) {
        this.preview_link = preview_link;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublished_date(String published_date) {
        this.published_date = published_date;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setPagecount(String pagecount) {
        this.pagecount = pagecount;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public void setEpub_download_link(String epub_download_link) {
        this.epub_download_link = epub_download_link;
    }

    public void setPdf_download_link(String pdf_download_link) {
        this.pdf_download_link = pdf_download_link;
    }

    //public void setBuy_link(String buy_link) {
   //     Buy_link = buy_link;
   // }

   // public void setRetail_price(String retail_price) {
  //      Retail_price = retail_price;
   // }

   // public String getRetail_price() {
   //     return Retail_price;
   // }

   // public String getBuy_link() {

   //     return Buy_link;
  //  }

    public String getPdf_download_link() {

        return pdf_download_link;
    }

    public String getEpub_download_link() {

        return epub_download_link;
    }

    public String getImageurl() {

        return imageurl;
    }

    public float getRating() {
        return rating;
    }

    public String getPagecount() {

        return pagecount;
    }

    public String getDescription() {

        return Description;
    }

    public String getCategory() {

        return category;
    }

    public String getPublished_date() {

        return published_date;
    }

    public String getPublisher() {

        return publisher;
    }

    public String getAuthor() {

        return author;
    }

    public String getTitle() {

        return title;
    }

    public String getPreview_link() {
        return preview_link;
    }

    public String getLanguage() {
        return language;
    }

    public String getEpub_available() {
        return epub_available;
    }

    public String getPdf_available() {
        return pdf_available;
    }

}
