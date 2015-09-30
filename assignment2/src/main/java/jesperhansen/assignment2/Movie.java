package jesperhansen.assignment2;

import android.graphics.drawable.Drawable;

/**
 * Class contains information about a movie.
 */
public class Movie {
    private String title;
    private String year;
    private String summary;
    private Drawable fanart;
    private Drawable poster;

    /**
     * Constructor for the Movie class
     * @param title Title of the movie
     * @param year Year the movie was released
     * @param summary Summary of the movie
     * @param fanart Image for the movie fanart
     * @param poster Image for the movie poster
     */
    public Movie(String title, String year, String summary, Drawable fanart, Drawable poster) {
        this.title = title;
        this.year = year;
        this.summary = summary;
        this.fanart = fanart;
        this.poster = poster;
    }

    /**
     * Method returns the movie title
     * @return Movie title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Method sets movie title
     * @param title New movie title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Metohod returns the year the movie was released
     * @return Year of release
     */
    public String getYear() {
        return year;
    }

    /**
     * Method sets year the movie was released
     * @param year Year movie was released
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     * Method gets the movie summary
     * @return Movie summary
     */
    public String getSummary() {
        return summary;
    }

    /**
     * Method sets movie summary
     * @param summary Movie summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * Method gets movie fanart
     * @return Movie fanart
     */
    public Drawable getFanart() {
        return fanart;
    }

    /**
     * Method sets the movie fanart
     * @param fanart Image, Movie fanart
     */
    public void setFanart(Drawable fanart) {
        this.fanart = fanart;
    }

    /**
     * Method gets movie poster
     * @return Image, movies poster
     */
    public Drawable getPoster() {
        return poster;
    }

    /**
     * Method sets movie poster
     * @param poster Image, movie poster
     */
    public void setPoster(Drawable poster) {
        this.poster = poster;
    }
}
