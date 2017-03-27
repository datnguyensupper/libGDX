package com.donick.pianotiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.Array;

/**
 * Created by nguyen on 3/22/2017.
 */
public class ScoreController {

    private int currentScore = 0;
    private int highScore = 0;
    Preferences prefs;


    ScoreController(){
        prefs = Gdx.app.getPreferences("Score Preferences");
        highScore = prefs.getInteger("highScore",0);
        currentScore = 0;
    }

    public void updateMaxScore(){
        if(currentScore > highScore){
            highScore = currentScore;
            prefs.putInteger("highScore",highScore);
            prefs.flush();
        }
    }

    public void increaseScore(){
        currentScore++;
    }

    public float getCurrentScore(){
        return currentScore;
    }

}
