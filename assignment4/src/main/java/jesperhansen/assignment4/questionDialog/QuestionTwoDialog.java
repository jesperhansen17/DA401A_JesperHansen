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


public class QuestionTwoDialog extends DialogFragment implements Dialog.OnClickListener {

    public QuestionTwoDialog() {
        // Required empty public constructor
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder question = new AlertDialog.Builder(getActivity())
                .setTitle("Vad heter Malmös bästa fotbollslag den 25 september 2015?")
                .setItems(new CharSequence[] {"Malmö City", "IFK Malmö", "Malmö FF"}, this);
        return question.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        Vibrator vibrate = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);
        MediaPlayer correctPlayer = MediaPlayer.create(getContext(), R.raw.correct_answer);
        MediaPlayer wrongPlayer = MediaPlayer.create(getContext(), R.raw.wrong_answer);

        switch (which) {
            case 0:
                Toast.makeText(getContext(), "Fel svar", Toast.LENGTH_SHORT).show();

                //wrongPlayer.start();
                break;
            case 1:
                Toast.makeText(getContext(), "Fel svar", Toast.LENGTH_SHORT).show();

                //wrongPlayer.start();
                break;
            case 2:
                Toast.makeText(getContext(), "Rätt svar", Toast.LENGTH_SHORT).show();

                //correctPlayer.start();
                vibrate.vibrate(100);
                break;
        }
    }
}
