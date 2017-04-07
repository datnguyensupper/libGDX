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

    void dispose(){
        player.dispose();
    }
}
