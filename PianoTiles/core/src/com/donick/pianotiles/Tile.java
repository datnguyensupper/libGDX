package com.donick.pianotiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.awt.geom.Point2D;

/**
 * Created by dinonguyen on 3/16/2017.
 */
public class Tile extends Image{
    boolean startTouch;
    private boolean isFinish = false;
    private boolean isFail = false;

    Tile(float x, float y, float tileWidth, float tileHeight, Stage stage, Texture img){
        super(img);
        setSize(tileWidth, tileHeight);
        setPosition(x, y);

        stage.addActor(this);
    }

    private boolean isInRegion(float x, float y){
        float minX = getX();
        float maxX = getX() + getWidth();
        float minY = getY();
        float maxY = getY() + getHeight();
        return (x < maxX && x > minX && y < maxY && y > minY );
    }

    public void touchMove(float x, float y){
        if(startTouch){
            if(!isInRegion(x,y)){
                isFail = true;
            }
        }
    }
    public void touchUp(float x, float y){
        if(startTouch && !isFail){
            isFinish = true;
        }
    }

    public void touchDown(float x, float y){
        if(isInRegion(x,y)){
            startTouch = true;
        }
    }

    public boolean checkFinish(){
        return isFinish;
    }

    public boolean checkFail(){
        return isFail;
    }

    public void removeFromState(){
        this.remove();
    }
}
