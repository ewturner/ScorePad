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
public class HighScoresFragment extends ListFragment {

    private ArrayList<Score> mScores;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScores = new ArrayList<Score>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_high_scores, parent, false);

        ScoreAdapter adapter = new ScoreAdapter(mScores);
        setListAdapter(adapter);

        return v;
    }

    private class ScoreAdapter extends ArrayAdapter<Score> {

        public ScoreAdapter(ArrayList<Score> scores) {
            super(getActivity(), 0, scores);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Get rank, player name, and score for each score and put in list item

            return convertView;
        }
    }
}
