package edu.auburn.csse.comp3710.group14;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;


public class MainActivity extends FragmentActivity implements MainMenuFragment.MainMenuHost, NewGameFragment.NewGameHost {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new MainMenuFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }

    }

    @Override
    public void createNewGame() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new NewGameFragment();

        fm.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void continueGame() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new ContinueGameFragment();

        fm.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void viewHighScores() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = new HighScoresFragment();

        fm.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();

    }

    // NewGameFragment.NewGameHost methods
    @Override
    public void startGame(long gameSessionId) {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = PlayerListFragment.newInstance(gameSessionId);

        fm.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}