package jesperhansen.assignment5.MoviesFragment;


import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import jesperhansen.assignment5.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends Fragment {
    // A ArrayList that holds an List of Movie objects
    public static List<Movie> mMovieList = new ArrayList<>();
    private MovieAdapter mMovieAdapter;
    private List<Integer> mMarkedMovies = new ArrayList<>();

    public MoviesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Array that get the Arrays of movie information from movies.xml
        TypedArray movies = getResources().obtainTypedArray(R.array.movies);

        // Loop through movies and put the information in a Movie object. That then are added into the mMovieList
        for (int i = 0; i < movies.length(); i++) {
            TypedArray movieArray = getResources().obtainTypedArray(movies.getResourceId(i, 0));

            // Create a new Movie object
            Movie movie = new Movie(movieArray.getString(0), movieArray.getString(1), movieArray.getString(2), movieArray.getDrawable(3), movieArray.getDrawable(4));

            // Add the movie object to the mMovieList
            mMovieList.add(movie);
            movieArray.recycle();
        }
        movies.recycle();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_movies, container, false);

        // Connect a gridview to the gridview in the XML
        final GridView gridView = (GridView) v.findViewById(R.id.movie_grid_view);

        // Create a Adapter for bridging the data to the layout
        mMovieAdapter = new MovieAdapter(mMovieList, getActivity().getLayoutInflater());

        gridView.setAdapter(mMovieAdapter);

        gridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
        gridView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                final int selectCount = gridView.getCheckedItemCount();

                mode.setTitle(selectCount + " is selected");

                mMovieAdapter.toggleSelection(position);
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.getMenuInflater().inflate(R.menu.movie_menu, menu);

                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_delete_icon:
                        SparseBooleanArray selected = mMovieAdapter.getSelectedMovies();

                        for (int i = (selected.size() - 1); i >= 0; i--) {
                            if (selected.valueAt(i)) {
                                Movie selectedMovies = (Movie) mMovieAdapter.getItem(selected.keyAt(i));
                                mMovieAdapter.removeMovie(selectedMovies);
                            }
                        }
                        mode.finish();
                        return true;
                    default:
                        return false;
                }
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });

        //gridView.setOnItemClickListener(new GridClick());

        return v;
    }

    private class GridClick implements AdapterView.OnItemClickListener {
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.i("GridClick", "Clicked");

            // Create a QuoteDayFragment for replacing when button is pressed
            MovieFragment movieFragment;

            // Create a new MovieFragment with a bundle that holds which poster that was pressed
            movieFragment = MovieFragment.newInstance(position);

            // Create a FragmentTransaction
            getFragmentManager().beginTransaction().replace(R.id.view_pager, movieFragment).addToBackStack(null).commit();
        }
    }

}
