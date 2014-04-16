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
    private ArrayList<Player> mPlayers;
    private GameSession mGameSession;

    private Button mEndGameButton;
    private TextView mGameTitle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // CHANGE THIS TO ACTUAL PLAYERS
        mPlayers = new ArrayList<Player>();
        mGameSession = new GameSession();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_player_game_list, parent, false);

        mGameTitle = (TextView) v.findViewById(R.id.current_game_title);
        // Set text of title
        mGameTitle.setText("");

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

    private class PlayerAdapter extends ArrayAdapter<Player> {
        public PlayerAdapter(ArrayList<Player> players) {
            super(getActivity(), 0, players);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_player, null);
            }

            Player p = getItem(position);
            // placeholder
            int color = 0;

            TextView playerColorTextView = (TextView) convertView.findViewById(R.id.player_color_swatch);
            playerColorTextView.setBackgroundColor(0);

            TextView playerNameTextView = (TextView) convertView.findViewById(R.id.player_name);
            playerNameTextView.setText(p.getName());

            Score scoreTest = new Score(0);
            TextView playerScoreTextView = (TextView) convertView.findViewById(R.id.current_player_score);
            playerScoreTextView.setText(scoreTest.getScore());
            // set score to player's color
            playerScoreTextView.setBackgroundColor(0);

            return convertView;
        }
    }
}
