package jesperhansen.assignment5.QuoteFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import jesperhansen.assignment5.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment {


    public QuoteFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // LogCat
        Log.i("QuoteFragment", "OnCreate");

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // LogCat
        Log.i("QuoteFragment", "OnCreateView");

        // Set the Fragment view to v
        View v = inflater.inflate(R.layout.fragment_quote, container, false);

        // Create a button view
        View button_view = v.findViewById(R.id.btn);

        // TODO: Not currently working, needs fix before I can press an MoviePoster
        //button_view.setOnClickListener(new ButtonClick());
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    /*
        Private inner class that handles the ButtonClick to change Fragment
         */
    private class ButtonClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // LogCat
            Log.i("QuoteFragment", "Clicked button");

            // Create a QuoteDayFragment for replacing when button is pressed
            QuoteDayFragment quoteDayFragment = new QuoteDayFragment();

            // Create a FragmentTransaction
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            // Replace current fragment with quoteDayFragment
            transaction.replace(R.id.fragment_container, quoteDayFragment);

            // Add current fragment to the back stack
            transaction.addToBackStack(null);

            transaction.commit();
        }
    }

}
