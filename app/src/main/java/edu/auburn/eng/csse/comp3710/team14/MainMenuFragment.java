package edu.auburn.eng.csse.comp3710.team14;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.auburn.csse.comp3710.group14.R;

/**
 * Created by eturner on 4/14/14.
 */
public class MainMenuFragment extends Fragment {

    public interface MainMenuHost {
        public void createNewGame();

        public void continueGame();
    }

    private MainMenuHost mCallback;

    private Button mNewGameButton;
    private Button mContinueGameButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, parent, false);

        mNewGameButton = (Button) v.findViewById(R.id.new_game_button);
        mNewGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.createNewGame();
            }
        });

        mContinueGameButton = (Button) v.findViewById(R.id.continue_game_button);
        mContinueGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.continueGame();
            }
        });

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mCallback = (MainMenuHost) activity;
    }
}
