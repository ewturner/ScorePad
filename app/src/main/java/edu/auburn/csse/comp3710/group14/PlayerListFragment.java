package edu.auburn.csse.comp3710.group14;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by eturner on 4/14/14.
 */
public class PlayerListFragment extends ListFragment {
    public static final String EXTRA_GAME_SESSION_ID = "edu.auburn.csse.comp3710.group14.gameSessionId";

    private ArrayList<Player> mPlayers;
    private GameSession mGameSession;
    private Game mGame;

    private Button mEndGameButton;
    private TextView mGameTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());

        long gameSessionId = getArguments().getLong(EXTRA_GAME_SESSION_ID);
        // CHANGE THIS TO ACTUAL PLAYERS
        mGameSession = dbHelper.getGameSessionFromId(gameSessionId);
        mGame = dbHelper.getGameFromGameSessionId(gameSessionId);
        mPlayers = dbHelper.getPlayersFromGameSessionId(gameSessionId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_player_game_list, parent, false);

        mGameTitle = (TextView) v.findViewById(R.id.current_game_title);
        // Set text of title
        mGameTitle.setText(mGame.getName());

        mEndGameButton = (Button) v.findViewById(R.id.end_game_button);
        mEndGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // End the Game
            }
        });

        PlayerAdapter adapter = new PlayerAdapter(mPlayers);
        setListAdapter(adapter);

        return v;
    }

    public static final PlayerListFragment newInstance(long gameSessionId) {
        PlayerListFragment fragment = new PlayerListFragment();
        Bundle bundle = new Bundle(1);
        bundle.putLong(EXTRA_GAME_SESSION_ID, gameSessionId);
        fragment.setArguments(bundle);
        return fragment;
    }

    private class PlayerAdapter extends ArrayAdapter<Player> {
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        public PlayerAdapter(ArrayList<Player> players) {
            super(getActivity(), 0, players);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_player, null);
            }

            Player p = getItem(position);

            TextView playerColorTextView = (TextView) convertView.findViewById(R.id.player_color_swatch);
            //Color playerColor = dbHelper.getPlayerColor(mGameSession.getId(), p.getId());
            //playerColorTextView.setBackgroundColor(playerColor.getColor());

            TextView playerNameTextView = (TextView) convertView.findViewById(R.id.list_player_name);
            playerNameTextView.setText(p.getName());

            Score playerScore = dbHelper.getScoreFromPlayerAndGameSessionId(p.getId(),
                    mGameSession.getId());
            final TextView playerScoreTextView = (TextView) convertView.findViewById(R.id.current_player_score);
            playerScoreTextView.setText(String.valueOf(playerScore.getScore()));

            Button increaseScoreButton = (Button) convertView.findViewById(R.id.increase_player_score);
            increaseScoreButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Player player = mPlayers.get(position);
                    dbHelper.updatePlayerScore(mGameSession.getId(), player.getId(), 1);
                    Score newScore = dbHelper.getScoreFromPlayerAndGameSessionId(player.getId(), mGameSession.getId());
                    playerScoreTextView.setText(String.valueOf(newScore.getScore()));
                }
            });

            return convertView;
        }
    }
}
