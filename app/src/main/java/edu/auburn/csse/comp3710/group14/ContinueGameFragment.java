package edu.auburn.csse.comp3710.group14;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by eturner on 4/16/14.
 */
public class ContinueGameFragment extends ListFragment {

    public interface ContinueGameHost {
        public void continueGame(long gamesession_id);
    }

    private ContinueGameHost mCallback;

    private ArrayList<GameSession> mGameSessions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());

        // get unfinished GameSessions
        mGameSessions = dbHelper.getAllUnfinishedGameSessions();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_continue_game, parent, false);

        GameSessionAdapter adapter = new GameSessionAdapter(mGameSessions);
        setListAdapter(adapter);

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallback = (ContinueGameHost) activity;
    }

    private class GameSessionAdapter extends ArrayAdapter<GameSession> {
        DatabaseHelper dbHelper = new DatabaseHelper(getActivity());
        public GameSessionAdapter(ArrayList<GameSession> gameSessions) {
            super(getActivity(), 0, gameSessions);
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent){
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater()
                        .inflate(R.layout.list_item_gamesession, null);
            }

            final GameSession gameSession = getItem(position);

            TextView gameSessionGameTextView = (TextView) convertView.findViewById(R.id.list_gamesession_game_name);
            Game gameSession_Game = dbHelper.getGameFromGameSessionId(gameSession.getId());
            gameSessionGameTextView.setText(gameSession_Game.getName());

            TextView gameSessionStartTextView = (TextView) convertView.findViewById(R.id.list_gamesession_start);
            gameSessionStartTextView.setText(gameSession.toString());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallback.continueGame(gameSession.getId());
                }
            });

            return convertView;
        }
    }
}
