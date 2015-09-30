package jesperhansen.assignment4.questionDialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;
import jesperhansen.assignment4.R;


public class QuestionOneDialog extends DialogFragment implements Dialog.OnClickListener {

    private static final String TAG = "QuestionOneDialog";

    public QuestionOneDialog() {
        // Required empty public constructor
    }

    // Create an Dialog that is put above the Map that holds the question.
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder question = new AlertDialog.Builder(getActivity())
                .setTitle("Vilken stad är Sveriges största stad?")
                .setItems(new CharSequence[]{"Göteborg", "Stockholm", "Malmö"}, this);
        return question.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        // Get access to the vibrator and the mediaplayer
        Vibrator vibrate = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        MediaPlayer correctPlayer = MediaPlayer.create(getContext(), R.raw.correct_answer);     // Right answer plays an mp3 for enhancing the correct answer
        MediaPlayer wrongPlayer = MediaPlayer.create(getContext(), R.raw.wrong_answer);         // Wrong answer plays an mp3 for enhancing the wrong answer

        switch (which) {
            case 0:
                Toast.makeText(getContext(), "Fel svar", Toast.LENGTH_SHORT).show();
                //wrongPlayer.start();
                break;
            case 1:
                Toast.makeText(getContext(), "Rätt svar", Toast.LENGTH_SHORT).show();
                //correctPlayer.start();
                vibrate.vibrate(100);   // Only when the answer is correct an vibration starts
                break;
            case 2:
                Toast.makeText(getContext(), "Fel svar", Toast.LENGTH_SHORT).show();
                //wrongPlayer.start();
                break;
        }
    }
}
