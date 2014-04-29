package edu.auburn.csse.comp3710.group14;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by eturner on 4/14/14.
 */
public class NewGameFragment extends ListFragment {
    public interface NewGameHost {
        public void startGame(long gameSessionId);
    }

    private NewGameHost mCallback;

    private DatabaseHelper dbHelper;

    private ArrayList<Player> mPlayers;
    private ArrayList<Player> mSelectedPlayers;
    private Game mGame;

    private int gameIndex;

    private Button mAddGameButton;
    private Button mAddPlayerButton;
    private Button mStartGameButton;
    private Spinner mGameSpinner;
    private Spinner mPlayerSpinner;
    private ListView mPlayerListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DatabaseHelper(getActivity());

        gameIndex = 0;

        mGame = new Game();
        mSelectedPlayers = new ArrayList<Player>();
    }

    @Override
    public void onResume(){
        super.onResume();
        populateSpinners();
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
                DatabaseHelper dbHelper = new DatabaseHelper(getActivity());

                //Create gamesessions entry
                long gamesession_id = dbHelper.createGameSession();

                //Create gamesession_games entry
                dbHelper.createGameSessionGame(gamesession_id, mGame.getId());

                //Create gamesession_player_scores entry
                for (Player player : mSelectedPlayers) {
                    dbHelper.addPlayerToGameSession(player.getId(), gamesession_id);
                }

                mCallback.startGame(gamesession_id);
            }
        });

        mGameSpinner = (Spinner) v.findViewById(R.id.game_spinner);
        mGameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gameIndex = i;
                mGame = (Game) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        populateSpinners();

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (NewGameHost) activity;
    }

    private void populateSpinners(){
        ArrayAdapter<Game> gameArrayAdapter = new ArrayAdapter<Game>(getActivity(),
                android.R.layout.simple_spinner_item,
                dbHelper.getAllGames());
        mGameSpinner.setAdapter(gameArrayAdapter);
        mGameSpinner.setSelection(gameIndex);

        PlayerSelectAdapter adapter = new PlayerSelectAdapter(dbHelper.getAllPlayers());
        setListAdapter(adapter);
    }

    private class PlayerSelectAdapter extends ArrayAdapter<Player> {
        public PlayerSelectAdapter(ArrayList<Player> players) {
            super(getActivity(), 0, players);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_select_player, null);
            }
            final Player p = getItem(position);

            TextView playerNameTextView = (TextView) convertView.findViewById(R.id.select_player_textview);
            playerNameTextView.setText(p.getName());

            final CheckBox playerSelectedCheckBox = (CheckBox) convertView.findViewById(R.id.select_player_checkbox);
            if (mSelectedPlayers.contains(p))
                playerSelectedCheckBox.setChecked(true);
            else
                playerSelectedCheckBox.setChecked(false);

            playerSelectedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (playerSelectedCheckBox.isChecked()) {
                        mSelectedPlayers.add(p);
                    } else {
                        mSelectedPlayers.remove(p);
                    }
                }
            });
            return convertView;
        }
    }
}
