package jesperhansen.assignment4;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import jesperhansen.assignment4.questionDialog.QuestionFourDialog;
import jesperhansen.assignment4.questionDialog.QuestionOneDialog;
import jesperhansen.assignment4.questionDialog.QuestionThreeDialog;
import jesperhansen.assignment4.questionDialog.QuestionTwoDialog;

public class MapsActivity extends FragmentActivity implements
        GoogleApiClient.ConnectionCallbacks, LocationListener {

    private static final String TAG = "MapsActivity";
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private GoogleApiClient mGoogleApiClient;

    private Location myLocation;                // Location for getting the latest location from the API
    private LocationRequest mLocation;          // LocationRequest sets which interval the GPS should update and more....
    private boolean mRequestingLocationUpdates; // Boolean for starting and stoping the GPS location request

    private ArrayList<LatLng> questionMarkerPos = new ArrayList<LatLng>();  // ArrayList that holds Latitude and Longitude for the markers
    private ArrayList<Marker> questionMarker = new ArrayList<>();           // ArrayList that holds the actual marker
    private final double DISTANCE_FROM_MARKER = 0.003;                      // Final double that is added or withdrawn from Lat and Log for setting the border
                                                                            // for when the user can interact with the marker

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connect GoogleApiClient to Google Maps Api
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        // True for starting the GPS location request
        mRequestingLocationUpdates = true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        // Starts the Api connection
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Disconnects the Api connection
        mGoogleApiClient.disconnect();
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Stops the LocationUpdates
        mRequestingLocationUpdates = false;
        pauseLocationUpdates();
    }


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();

        // Starts the LocationUpdates again after the application is resumed
        if (mGoogleApiClient.isConnected() && !mRequestingLocationUpdates) {
            startLocationUpdates();
        }
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        // Enables the button that zooms in on the device in the map view
        mMap.setMyLocationEnabled(true);

        // Create 4 questionsPositions that is placed on the map according to the latitude and the longitude
        LatLng question1 = new LatLng(55.5988184, 12.9768380);
        LatLng question2 = new LatLng(55.6043309, 12.9828123);
        LatLng question3 = new LatLng(55.6054826, 12.9914584);
        LatLng question4 = new LatLng(55.6100120, 12.9948799);

        // Add questionPositions to the ArrayList that holds the markers GPS coordinates
        questionMarkerPos.add(question1);
        questionMarkerPos.add(question2);
        questionMarkerPos.add(question3);
        questionMarkerPos.add(question4);

        // Iterate through the GPS coordinates and create Markers on those coordinates, also add
        // the Markers in an ArrayList
        for (int i = 0; i < questionMarkerPos.size(); i++) {
            questionMarker.add(mMap.addMarker(new MarkerOptions()
                    .position(questionMarkerPos.get(i))
                    .title("QuestionNotActivated")
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker_question))));
        }

        // Add an OnMarkerClickListener to the markers, I separate which marker I pressed by getting
        // the title from the pressed marker. Then I respond different on each of the four markers with
        // the pressed markers title. Depending on which marker that was pressed I create an new DialogFragment
        // that holds the question
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            public boolean onMarkerClick(Marker marker) {

                // A FragmentTransaction for handling the Fragments
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                // Decide which marker that was pressed be getting the marker title
                switch (marker.getTitle()) {
                    case "Question1":

                        QuestionOneDialog questionOneDialog = new QuestionOneDialog();  // Create a new DialogFragment, that holds the question for the marker
                        questionOneDialog.show(fragmentTransaction, "Question1");       // Show the Fragment with help of the fragmentTransaction
                        break;
                    case "Question2":
                        QuestionTwoDialog questionTwoDialog = new QuestionTwoDialog();
                        questionTwoDialog.show(fragmentTransaction, "Question2");
                        break;
                    case "Question3":
                        QuestionThreeDialog questionThreeDialog = new QuestionThreeDialog();
                        questionThreeDialog.show(fragmentTransaction, "Question3");
                        break;
                    case "Question4":
                        QuestionFourDialog questionFourDialog = new QuestionFourDialog();
                        questionFourDialog.show(fragmentTransaction, "Question4");
                        break;
                }
                return true;
            }
        });
    }

    /**
     * Method that executes when a connection to the GoogleApiClient is made
     * It get's the last location and zooms in on the device location
     * Creates an LocationRequest and set's location update interval to between 5 seconds and 1 seconds
     * @param bundle
     */
    @Override
    public void onConnected(Bundle bundle) {
        myLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLocation.getLatitude(), myLocation.getLongitude()), 13F));

        mLocation = new LocationRequest();
        mLocation.setInterval(5000);
        mLocation.setFastestInterval(1000);
        mLocation.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        startLocationUpdates();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    /**
     * onLocationChanged method is called every 5 second (hopefully, may differ because of other running services)
     * or when the device has moved
     * @param location
     */
    @Override
    public void onLocationChanged(Location location) {

        // Iterate over every marker
        for (int i = 0; i < questionMarker.size(); i++) {
            // I get each marker position and add a number to the coordinate to move four new position a distance from the origin position,
            // using this I create an border between the four new points. And if current location is in the grid around the marker then the user
            // will be able to press the marker and a question will appear.
            //                  .
            //               -     -
            //             -         -      A grid around the marker(represented by a *)
            //          .       *       .   If the device is inside the grid, the marker will
            //             -         -      be clickable
            //               -     -
            //                  .
            if ((location.getLatitude() < (questionMarkerPos.get(i).latitude + DISTANCE_FROM_MARKER) && location.getLatitude() > (questionMarkerPos.get(i).latitude - DISTANCE_FROM_MARKER)) && ((location.getLongitude() < questionMarkerPos.get(i).longitude + DISTANCE_FROM_MARKER) && (location.getLongitude() > questionMarkerPos.get(i).longitude - DISTANCE_FROM_MARKER))) {
                questionMarker.get(i).setTitle("Question" + (i+1));        // Set the title so the onClickListener can interact with the marker
                questionMarker.get(i).setSnippet("Press for question!");
                questionMarker.get(i).showInfoWindow();                    // Shows the title and the snippet
            } else {
                questionMarker.get(i).setTitle("QuestionNotActivated");
                questionMarker.get(i).hideInfoWindow();
            }
        }
    }

    /**
     * Method that is called when the application should starts update the location
     */
    protected void startLocationUpdates() {
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocation, this);
    }

    /**
     * Method that is called when the application should stop update the location
     */
    protected void pauseLocationUpdates() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
    }

    public void hideMarker() {
        questionMarker.get(0).hideInfoWindow();
    }
}
