package com.donick.pianotiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;

/**
 * Created by nguyen on 3/22/2017.
 */
public class ScoreController {

    private float currentScore;
    private float maxScore;
    Preferences prefs;

    ScoreController(){
        prefs = Gdx.app.getPreferences("Score Preferences");
        maxScore = prefs.getFloat("maxScore",0);
        currentScore = 0;
    }

    public void updateMaxScore(){

    }
}
