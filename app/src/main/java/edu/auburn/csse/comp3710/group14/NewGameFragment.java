package edu.auburn.csse.comp3710.group14;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

    private DatabaseHelper dbHelper;

    private ArrayList<Player> mPlayers;
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

        // add real games later
        mPlayers = new ArrayList<Player>();
        mGame = new Game();
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

            }
        });

        mGameSpinner = (Spinner) v.findViewById(R.id.game_spinner);
        mGameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                gameIndex = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mPlayerSpinner = (Spinner) v.findViewById(R.id.player_spinner);
        mPlayerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mPlayers.add((Player)adapterView.getItemAtPosition(i));
                refreshPlayerList();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        populateSpinners();

        return v;
    }

    private void populateSpinners(){
        ArrayAdapter<Game> gameArrayAdapter = new ArrayAdapter<Game>(getActivity(),
                android.R.layout.simple_spinner_item,
                dbHelper.getAllGames());
        mGameSpinner.setAdapter(gameArrayAdapter);
        mGameSpinner.setSelection(gameIndex);

        ArrayAdapter<Player> playerArrayAdapter = new ArrayAdapter<Player>(getActivity(),
                android.R.layout.simple_spinner_item,
                dbHelper.getAllPlayers());
        playerArrayAdapter.notifyDataSetChanged();
        mPlayerSpinner.setAdapter(playerArrayAdapter);
    }

    private void refreshPlayerList(){
        ArrayAdapter<Player> adapter = new ArrayAdapter<Player>(getActivity(),
                android.R.layout.simple_list_item_1,
                mPlayers);
        setListAdapter(adapter);
    }
}
