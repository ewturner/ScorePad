package edu.auburn.csse.comp3710.group14;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by eturner on 4/14/14.
 */
public class PlayerListFragment extends ListFragment {
    private ArrayList<Player> mPlayers;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // CHANGE THIS TO ACTUAL PLAYERS
        mPlayers = new ArrayList<Player>();
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

            TextView playerColorTextView = (TextView) convertView.findViewById(R.id.player_color_swatch);
            playerColorTextView.setBackgroundColor(Integer.parseInt(p.getPlayerColor().getHexColor()));

            TextView playerNameTextView = (TextView) convertView.findViewById(R.id.player_name);
            playerNameTextView.setText(p.getName());

            TextView playerScoreTextView = (TextView) convertView.findViewById(R.id.current_player_score);
            playerScoreTextView.setText(p.getPlayerScore().toString());

            return convertView;
        }
    }
}
