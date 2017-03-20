package com.donick.pianotiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.awt.geom.Point2D;

/**
 * Created by dinonguyen on 3/16/2017.
 */
public class Tile extends Image{

    public enum TileType{
        TILE_TAB,
        TILE_HOLD
    }

    boolean startTouch;
    private boolean isFinish = false;
    private boolean isFail = false;
    private TileType type;
    private float touchDuration;
    private Image imageDot;
    private Group parent;

    /**
     * create tab tile
     * @param x
     * @param y
     * @param tileWidth
     * @param tileHeight
     * @param stage
     * @param img
     */
    Tile(float x, float y, float tileWidth, float tileHeight, Stage stage, Texture img){
        super(img);
        setSize(tileWidth, tileHeight);
//        setPosition(x, y);

        parent = new Group();
        parent.setPosition(x,y);
        parent.addActor(this);

        stage.addActor(parent);

        type = TileType.TILE_TAB;
    }

    /**
     * create long press tile
     * @param x
     * @param y
     * @param tileWidth
     * @param tileHeight
     * @param stage
     * @param img
     * @param duration
     * @param speed
     */
    Tile(float x, float y, float tileWidth, float tileHeight, Stage stage, Texture img, Texture dot, float duration, float speed){
        super(img);
        touchDuration = duration;
        float availableHeigh = speed * duration;

        setSize(tileWidth, Math.max(availableHeigh, tileHeight));

        parent = new Group();
        parent.setPosition(x,y);
        parent.addActor(this);

        imageDot = new Image(dot);
        imageDot.setPosition(tileWidth/2, 10);
//        addAc


        stage.addActor(parent);
        type = TileType.TILE_HOLD;
    }




    private boolean isInRegion(float x, float y){
        float minX = getX();
        float maxX = getX() + getWidth();
        float minY = getY();
        float maxY = getY() + getHeight();
        return (x < maxX && x > minX && y < maxY && y > minY );
    }

    @Override
    public float getX(){
        return parent.getX();
    }

    @Override
    public float getY(){
        float yPosition = parent.getY();
        return yPosition;
    }

    @Override
    public void setY(float newY){

        parent.setY(newY);
    }

    public void moveDown(float speed, float delta){
        float translate = delta*speed;
        setY(getY() - translate);
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
