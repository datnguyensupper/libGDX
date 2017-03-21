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
public class Tile extends Group{

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
    private Image background;
    private  float ratio;

    /**
     * create tab tile
     * @param x
     * @param y
     * @param tileWidth
     * @param tileHeight
     * @param stage
     * @param img
     */
    Tile(float x, float y,float _ratio, float tileWidth, float tileHeight, Stage stage, Texture img){
        super();
        ratio = 1;// _ratio;

//        x = 450;y = 800;
        background = new Image(img);
        background.setSize(tileWidth, tileHeight);
        setSize(tileWidth, tileHeight);
        setPosition(x, y);
        this.addActor(background);

        stage.addActor(this);

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
    Tile(float x, float y,float ratio, float tileWidth, float tileHeight, Stage stage, Texture img, Texture dot, float duration, float speed){
//        super(img);
//        touchDuration = duration;
//        float availableHeigh = speed * duration;
//
//        setSize(tileWidth, Math.max(availableHeigh, tileHeight));
//
//        parent = new Group();
//        parent.setPosition(x,y);
//        parent.addActor(this);
//
//        imageDot = new Image(dot);
//        imageDot.setPosition(tileWidth/2, 10);
////        addAc
//
//
//        stage.addActor(parent);
//        type = TileType.TILE_HOLD;
    }

//    @Override
//    public float getHeight(){
//        return background.getHeight();
//    }


    private boolean isInRegion(float x, float y){
        float minX = getX();
        float maxX = getX() + getWidth();
        float minY = getY();
        float maxY = getY() + getHeight();
        return (x < maxX && x > minX && y < maxY && y > minY );
    }

//    @Override
//    public float getX(){
//        return parent.getX();
//    }
//
//    @Override
//    public float getY(){
//        float yPosition = parent.getY();
//        return yPosition/ratio;
//    }
//
//    @Override
//    public void setY(float newY){
//
//        parent.setY(newY*ratio);
//    }
//
//    @Override
//    public void setPosition(float newX, float newY){
//        setX(newX);
//        setY(newY);
//    }

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
