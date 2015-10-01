package jesperhansen.assignment5.QuoteFragment;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import jesperhansen.assignment5.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteDayFragment extends android.support.v4.app.Fragment {
    public QuoteDayFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // LogCat
        Log.i("QuoteDayFragment", "OnCreateView");

        // Find the textViews
        View v = inflater.inflate(R.layout.fragment_quote_day, container, false);
        TextView quoteView = (TextView)v.findViewById(R.id.randomQuote);
        TextView dateView = (TextView)v.findViewById(R.id.quoteTime);

        // Get an Random Quote from the Class Helpers to set the textView
        quoteView.setText(Helpers.getRandomQuote(getActivity().getApplicationContext()));

        // Get todays date from the Class Helpers to set the textView
        dateView.setText(Helpers.setDate(getActivity().getApplicationContext()));

        return v;
    }


}
