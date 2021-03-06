package com.example.android.newsapp;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import static android.content.ContentValues.TAG;

public class NewsAdapter extends ArrayAdapter<News> {

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the Arraylist is the data we want
     * to populate into the lists.
     *
     * @param context The current context. Used to inflate the layout file.
     * @param news    A List of News objects to display in a list
     */
    public NewsAdapter(Activity context, ArrayList<News> news) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for four TextViews, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, news);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        // Get the News object located at this position in the list
        News currentNews = getItem(position);

        // Find the TextView in the news_list_item.xml,with the ID article_name. And display the article title
        //in the TextView.
        TextView articleNameTextView = listItemView.findViewById(R.id.article_name);
        assert currentNews != null;
        String articleName=currentNews.getArticleName();
        articleNameTextView.setText(articleName);

        // Find the TextView in the news_list_item.xml,with the ID author. And display the author
        //in the TextView.
        TextView authorTextView = listItemView.findViewById(R.id.author);
        String author=currentNews.getAuthor();
        authorTextView.setText(author);

        // Find the TextView in the news_list_item.xml,with the ID date. And display the date
        //in the TextView. The desired formatting of the date (i.e. "Mar 3, 1984") is achieved with the method formatDate.
        TextView dateTextView = listItemView.findViewById(R.id.date);
        String date=formatDate(currentNews.getDate());
        dateTextView.setText(date);

        // Find the TextView in the news_list_item.xml,with the ID category. And display the category
        //in the TextView.
        TextView categoryTextView = listItemView.findViewById(R.id.category);
        String category=currentNews.getCategory();
        categoryTextView.setText(category);

        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }

    /**
     * Return the formatted date string (i.e. "Mar 3, 1984") from a date of String type.
     */
    private String formatDate(String date) {
        SimpleDateFormat input = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.UK);
        SimpleDateFormat output = new SimpleDateFormat("MMM d, yyy", Locale.UK);
        Date d= null;
        try
        {
            d = input.parse(date);
            return output.format(d);
        }catch (ParseException e){
            return "";
        }

    }

}
