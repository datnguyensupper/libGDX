package com.donick.pianotiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by nguyen on 3/21/2017.
 */
public class PlayingSoundAdvande {
    Music player;

    float start;
    float end;
    boolean isPlaying = false;

    PlayingSoundAdvande(FileHandle file){
        player = Gdx.audio.newMusic(file);
        player.play();
        player.pause();
    }

    void play(float _start, float _end){
        isPlaying = true;
        start = _start;
        end = _end;
        player.setPosition(_start);
        player.play();
    }

    boolean isPlaying(){
        return isPlaying;
    }

    void render(){
        if(isPlaying){
            if(player.getPosition() >= end){
                isPlaying = false;
                player.pause();
            }
        }
    }

    public void finishToNearest(){
        float duration = player.getPosition() - start;

        float newEnd = 0;
        if(duration < 0.5f)
            newEnd = start + 0.5f;
        else if (duration < 1.0f)
            newEnd = start + 1.0f;
        else if(duration < 1.5f)
            newEnd = start + 1.5f;
        else if(duration < 2.0f)
            newEnd = start + 2.0f;
        else if(duration < 2.5f)
            newEnd = start + 2.5f;
        else
            newEnd = start + 3.0f;

        if(newEnd < end)
            end = newEnd;

    }

    void dispose(){
        player.dispose();
    }
}
