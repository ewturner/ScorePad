package edu.auburn.csse.comp3710.group14;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by eturner on 4/16/14.
 */
public class ContinueGameFragment extends ListFragment {

    private ArrayList<GameSession> mGameSessions;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get unfinished GameSessions
        mGameSessions = new ArrayList<GameSession>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_continue_game, parent, false);

        ArrayAdapter<GameSession> adapter = new ArrayAdapter<GameSession>(getActivity(),
                android.R.layout.simple_list_item_1,
                mGameSessions);
        setListAdapter(adapter);

        return v;
    }
}
