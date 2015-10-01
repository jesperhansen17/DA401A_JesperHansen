package jesperhansen.assignment5.QuoteFragment;

import android.content.Context;
import java.util.Calendar;
import java.util.Random;

import jesperhansen.assignment5.R;


public class Helpers {
    private static String stringMonth;
    private static String[] quoteArray;
    private static String[] months;
    /**
     * Static method that returns a random quote
     * @param context Applications context
     * @return A randomized quote
     */
    public static String getRandomQuote(Context context) {
        // Get the quotes with help of ID and put it in a private array
        quoteArray = context.getResources().getStringArray(R.array.quoteArray);
        return quoteArray[new Random().nextInt(quoteArray.length)];
    }

    /**
     * Static method that returns a String with todays date
     * @param context Applications context
     * @return A String with today's date
     */
    public static String setDate(Context context) {
        // Get the months with help of ID and put it in a private array
        months = context.getResources().getStringArray(R.array.months);

        // Get today's year, month and day
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Converts the months number into January, February.....December
        switch (month) {
            case 0:
                stringMonth = months[0];
                break;
            case 1:
                stringMonth = months[1];
                break;
            case 2:
                stringMonth = months[2];
                break;
            case 3:
                stringMonth = months[3];
                break;
            case 4:
                stringMonth = months[4];
                break;
            case 5:
                stringMonth = months[5];
                break;
            case 6:
                stringMonth = months[6];
                break;
            case 7:
                stringMonth = months[7];
                break;
            case 8:
                stringMonth = months[8];
                break;
            case 9:
                stringMonth = months[9];
                break;
            case 10:
                stringMonth = months[10];
                break;
            case 11:
                stringMonth = months[11];
                break;
            default:
                stringMonth = "invalidMonth";
                break;
        }

        return Integer.toString(day) + " " + stringMonth + " " + Integer.toString(year);
    }
}