package edu.auburn.csse.comp3710.group14;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import java.sql.SQLException;


public class MainActivity extends FragmentActivity implements MainMenuFragment.MainMenuHost {
    private GameSessionDataSource datasource;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null){
            fragment = new MainMenuFragment();
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit();
        }

        datasource = new GameSessionDataSource(this);
        try {
            datasource.open();
        }
        catch (SQLException e){
            Log.d("EXCEPT", e.toString());
        }
    }

    @Override
    public void createNewGame() {

    }

    @Override
    public void continueGame() {

    }

    @Override
    public void viewHighScores() {

    }
}