package jesperhansen.assignment1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if the FrameLayout container is not empty
        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment
            QuoteFragment quoteFragment = new QuoteFragment();

            // Add the fragment to the "fragment_container" FrameLayout
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, quoteFragment).commit();

            // LogCat
            Log.i("MainActivity", "OnCreate");
        }
    }

    /*
    onStart method created for LogCat
     */
    protected void onStart() {
        // LogCat
        Log.i("MainActivity", "OnStart");
        super.onStart();
    }

    /*
    onResume method created for LogCat
     */
    protected void onResume() {
        // LogCat
        Log.i("MainActivity", "OnResume");
        super.onResume();
    }

    /*
    onPause method created for LogCat
     */
    protected void onPause() {
        // LogCat
        Log.i("MainActivity", "OnPause");
        super.onPause();
    }

    /*
    onStop method created for LogCat
     */
    protected void onStop() {
        // LogCat
        Log.i("MainActivity", "OnStop");
        super.onStop();
    }

    /*
    onDestroy method created for LogCat
     */
    protected void onDestroy() {
        // LogCat
        Log.i("MainActivity", "OnDestroy");
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
