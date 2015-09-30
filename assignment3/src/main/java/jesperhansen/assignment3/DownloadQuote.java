package jesperhansen.assignment3;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadQuote {
    public DownloadQuote() {
        // Empty Constructor
    }

    // Method for creating an HTTP connection and a InputStream
    public String downloadQuoteFromURL(String myurl) throws IOException {
        Log.i("DownLoad", "First Method");
        try {
            URL url = new URL(myurl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            try {
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                return createListOfQuote(inputStream);
            } finally {
                Log.i("DownLoad", "First Method Finally");
                httpURLConnection.disconnect();
            }
        } catch (IOException e) {
            Log.i("DownLoad", "First Method Catch");
            e.printStackTrace();
        }
        return null;
    }

    // Method for for retrieving the Quote from the InputStream
    public String createListOfQuote(InputStream inputStream) {
        Log.i("DownLoad", "Second Method");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
