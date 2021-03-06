package edu.auburn.eng.csse.comp3710.team14;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import edu.auburn.csse.comp3710.group14.R;

/**
 * Created by eturner on 4/14/14.
 */
public class NewGameDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final LayoutInflater layoutInflater = getActivity().getLayoutInflater();

        final View layout = layoutInflater.inflate(R.layout.dialog_add_game, null);
        builder.setView(layout)
                .setPositiveButton(R.string.add_game, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // Add the game
                        DatabaseHelper dbHelp = new DatabaseHelper(layoutInflater.getContext());

                        EditText gameInput = (EditText)layout.findViewById(R.id.game_name);

                        String gameName = gameInput.getText().toString();

                        if(gameName != "" && gameName != null){
                            Game newGame = new Game(gameName);
                            dbHelp.createGame(newGame);

                            getActivity().startActivityForResult(getActivity().getIntent(), 10);
                        }

                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        NewGameDialogFragment.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }
}
