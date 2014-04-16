package edu.auburn.csse.comp3710.group14;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by eturner on 4/14/14.
 */
public class NewGameFragment extends ListFragment {
    public interface NewGameHost {
        public void startGame();
    }

    private ArrayList<Player> mPlayers;

    private Button mAddGameButton;
    private Button mAddPlayerButton;
    private Button mStartGameButton;
    private Spinner mGameSpinner;
    private Spinner mPlayerSpinner;
    private ListView mPlayerListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // add real games later
        mPlayers = new ArrayList<Player>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_game, parent, false);

        mAddGameButton = (Button) v.findViewById(R.id.add_game_button);
        mAddGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display alert to add game
                FragmentManager fm = getFragmentManager();
                NewGameDialogFragment newGameDialog = new NewGameDialogFragment();
                newGameDialog.show(fm, null);
            }
        });

        mAddPlayerButton = (Button) v.findViewById(R.id.add_player_button);
        mAddPlayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display alert to add player
                FragmentManager fm = getFragmentManager();
                NewPlayerDialogFragment newPlayerDialog = new NewPlayerDialogFragment();
                newPlayerDialog.show(fm, null);
            }
        });

        mStartGameButton = (Button) v.findViewById(R.id.start_game_button);
        mStartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mGameSpinner = (Spinner) v.findViewById(R.id.game_spinner);
        // set spinner content
        mPlayerSpinner = (Spinner) v.findViewById(R.id.player_spinner);
        // set spinner content

        ArrayAdapter<Player> adapter = new ArrayAdapter<Player>(getActivity(),
                android.R.layout.simple_list_item_1,
                mPlayers);

        setListAdapter(adapter);

        return v;
    }
}
