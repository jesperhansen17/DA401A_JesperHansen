package jesperhansen.assignment5.MoviesFragment;

import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import jesperhansen.assignment5.R;

/**
 * MovieAdapter for bridging data with xml files
 */
public class MovieAdapter extends BaseAdapter {
    private List<Movie> mMovieList;
    private LayoutInflater mLayoutInflater;
    private SparseBooleanArray mSelectedMovies;

    /**
     * Constructor for the Adapter
     * @param mMovieList ArrayList holds Movie objects in a List
     * @param mLayoutInflater Context for the fragment
     */
    public MovieAdapter(List<Movie> mMovieList, LayoutInflater mLayoutInflater) {
        this.mMovieList = mMovieList;
        this.mLayoutInflater = mLayoutInflater;
        mSelectedMovies = new SparseBooleanArray();
    }

    @Override
    public int getCount() {
        return mMovieList.size();
    }

    @Override
    public Object getItem(int position) {
        return mMovieList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // If convertView is NULL, inflate the convertView
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.movie_item, parent, false);
        }

        // Connects the views with the right xml view
        ImageView posterImageView = (ImageView) convertView.findViewById(R.id.posterImageView);
        TextView titleTextView = (TextView) convertView.findViewById(R.id.movie_title_year);

        // Set poster, title and year for the fragment_movie_list.xml
        posterImageView.setImageDrawable(((Movie) getItem(position)).getPoster());
        titleTextView.setText(((Movie) getItem(position)).getTitle() + "\n" + ((Movie) getItem(position)).getYear());

        return convertView;
    }

    // Sends the pressed position and boolean as a true
    public void toggleSelection(int position) {
        selectView(position, !mSelectedMovies.get(position));
    }

    // If value is true add the position as key and value as value to an
    // SparseBooleanArray
    public void selectView(int position, boolean value) {
        if (value) {
            mSelectedMovies.put(position, value);
        } else {
            mSelectedMovies.delete(position);
        }
        // Notify adapter that data has changed
        notifyDataSetChanged();
    }

    // Return the SparseBooleanArray
    public SparseBooleanArray getSelectedMovies() {
        return mSelectedMovies;
    }

    // Removes the MovieObject from the ArrayList that holds the Movie object
    public void removeMovie(Movie checkedMovies) {
        mMovieList.remove(checkedMovies);

        // Notify adapter thet data has changed
        notifyDataSetChanged();
    }
}
