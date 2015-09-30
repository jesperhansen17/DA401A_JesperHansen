package jesperhansen.assignment1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteDayFragment extends Fragment {
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
