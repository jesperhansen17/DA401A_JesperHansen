package jesperhansen.assignment3;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteListFragment extends Fragment {

    // A ArrayList that holds an List of Quotes
    private List<String> mQuoteList = new ArrayList<>();

    // Progressbar that is VISIBLE only when the AsyncTask is running
    private ProgressBar mProgressBar;

    // The address to the QuoteGenerator
    private static String url = "https://api.github.com/zen?access_token=0f892e365071c7e778a020e463d715b8ccb816f5";

    public QuoteListFragment() {
        // Required empty public constructor
    }


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("QuoteListFragment", "Start onCreateView");
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_quote_list, container, false);

        // Set the ListView, Progressbar and FAB from XML to different views
        ListView listView = (ListView) v.findViewById(R.id.quote_list_view);
        android.support.design.widget.FloatingActionButton button = (android.support.design.widget.FloatingActionButton) v.findViewById(R.id.floating_button);
        mProgressBar = (ProgressBar) v.findViewById(R.id.progress_bar);

        // Set Progressbar as Indeterminate
        mProgressBar.setIndeterminate(true);

        // Add the Quotelist to the Adapter
        QuoteAdapter quoteAdapter = new QuoteAdapter(mQuoteList, getActivity().getLayoutInflater());

        // Set the QuoteAdapter to the listview
        listView.setAdapter(quoteAdapter);

        // Add an ClickListener to the FAB that starts an AsyncTask to download a Quote
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Starts the AsyncTask
                    new DownloadQuoteTask().execute(url);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return v;
    }

    private class DownloadQuoteTask extends AsyncTask<String, Integer, String> {
        protected void onPreExecute() {
            super.onPreExecute();
            // When the AsyncTask starts, set the Progressbar to VISIBLE
            mProgressBar.setVisibility(View.VISIBLE);
        }

        protected String doInBackground(String... urls) {
            try {
                // Calls a method in DownloadQuote that retrieves the Quote from internet
                return new DownloadQuote().downloadQuoteFromURL(urls[0]);
            } catch (IOException e) {
                return "Invalid URL";
            }
        }

        protected void onPostExecute(String quote) {
            // After the AsyncTask, set the Progressbar to GONE
            mProgressBar.setVisibility(View.GONE);
            // Add the downloaded quote to the List of Quotes
            mQuoteList.add(0, quote);


        }
    }
}
