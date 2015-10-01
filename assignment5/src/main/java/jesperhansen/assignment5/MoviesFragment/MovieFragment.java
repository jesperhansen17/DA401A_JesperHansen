package jesperhansen.assignment5.MoviesFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import jesperhansen.assignment5.R;


/**
 * Fragment that view the fanart, summary, title and year of the selected movie
 */
public class MovieFragment extends Fragment {
    public MovieFragment() {
        // Required empty public constructor
    }

    /**
     * Method that returns creates a new MovieFragment that bundles the position in the gridview
     * the poster was touched
     * @param index Position of the pressed poster
     * @return MovieFragment
     */
    public static MovieFragment newInstance(int index) {
        MovieFragment movieFragmentInstance = new MovieFragment();

        // Supply index as an input
        Bundle args = new Bundle();
        args.putInt("index", index);
        movieFragmentInstance.setArguments(args);

        return movieFragmentInstance;
    }

    /*
     * Method returns the position of the pressed poster in the gridview
     */
    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movie, container, false);

        // Find all the views that the fragment_movie.xml contains
        TextView summaryView = (TextView)v.findViewById(R.id.summary_textView);
        TextView titleView = (TextView)v.findViewById(R.id.title_year_imageView);
        ImageView imageView = (ImageView)v.findViewById(R.id.fanart_imageView);

        // Put information from the movie objects in mMovieList into the views
        summaryView.setText(MoviesFragment.mMovieList.get(getShownIndex()).getSummary());
        imageView.setImageDrawable(MoviesFragment.mMovieList.get(getShownIndex()).getFanart());
        titleView.setText(MoviesFragment.mMovieList.get(getShownIndex()).getTitle() + "\n" + MoviesFragment.mMovieList.get(getShownIndex()).getYear());

        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}

