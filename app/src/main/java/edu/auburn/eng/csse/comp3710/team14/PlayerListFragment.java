package edu.auburn.eng.csse.comp3710.team14;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

import edu.auburn.csse.comp3710.group14.R;

/**
 * Created by eturner on 4/14/14.
 */
public class PlayerListFragment extends ListFragment {

    public interface PlayerListHost {
        public void endGame();
    }
    public static final String EXTRA_GAME_SESSION_ID = "edu.auburn.csse.comp3710.group14.gameSessionId";

    private PlayerListHost mCallback;

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
        mGameSession = dbHelper.getGameSessionFromId(gameSessionId);
        mGame = dbHelper.getGameFromGameSessionId(gameSessionId);
        mPlayers = dbHelper.getPlayersFromGameSessionId(gameSessionId);
        Collections.sort(mPlayers);
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
                DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
                dbHelper.endGame(mGameSession.getId());
                mCallback.endGame();
            }
        });

        PlayerAdapter adapter = new PlayerAdapter(mPlayers);
        setListAdapter(adapter);

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (PlayerListHost) activity;
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

            TextView playerNameTextView = (TextView) convertView.findViewById(R.id.list_player_name);
            playerNameTextView.setText(p.getName());

            Score playerScore = dbHelper.getScoreFromPlayerAndGameSessionId(p.getId(),
                    mGameSession.getId());
            TextView playerScoreTextView = (TextView) convertView.findViewById(R.id.current_player_score);
            playerScoreTextView.setText(String.valueOf(playerScore.getScore()));

            Button increaseScoreButton = (Button) convertView.findViewById(R.id.increase_player_score);
            increaseScoreButton.setOnClickListener(new ScoreIncrementClickListener(position, true, playerScoreTextView));

            Button decreaseScoreButton = (Button) convertView.findViewById(R.id.decrease_player_score);
            decreaseScoreButton.setOnClickListener(new ScoreIncrementClickListener(position, false, playerScoreTextView));

            return convertView;
        }
    }

    private class ScoreIncrementClickListener implements View.OnClickListener {
        private int position;
        private boolean increase;
        private TextView scoreText;

        public ScoreIncrementClickListener(int position, boolean increase, TextView scoreText){
            this.position = position;
            this.increase = increase;
            this.scoreText = scoreText;
        }

        @Override
        public void onClick(View v){
            DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
            Player player = mPlayers.get(position);
            if (increase)
                dbHelper.updatePlayerScore(mGameSession.getId(), player.getId(), 1);
            else
                dbHelper.updatePlayerScore(mGameSession.getId(), player.getId(), -1);
            Score newScore = dbHelper.getScoreFromPlayerAndGameSessionId(player.getId(), mGameSession.getId());
            scoreText.setText(String.valueOf(newScore.getScore()));

            v.invalidate();
        }

    }
}
