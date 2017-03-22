package com.donick.pianotiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
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

    float startMusicPosition;
    float endMusicPosition;
    private boolean startTouch;
    private boolean isFinish = false;
    private boolean isFail = false;
    private TileType type;
    private float touchDuration;
    private Image imageDot;
    private Image background;

    private Vector2 previousTouch = Vector2.Zero;

    /**
     * create tab tile
     * @param x
     * @param y
     * @param tileWidth
     * @param tileHeight
     * @param stage
     * @param img
     */
    Tile(float x, float y,float _startMusicPosition, float _endMusicPosition, float tileWidth, float tileHeight, Stage stage, Texture img){
        super();

//        x = 450;y = 800;
        background = new Image(img);
        background.setSize(tileWidth, tileHeight);
        setSize(tileWidth, tileHeight);
        setPosition(x, y);
        this.addActor(background);

        stage.addActor(this);

        type = TileType.TILE_TAB;
        startMusicPosition = _startMusicPosition;
        endMusicPosition = _endMusicPosition;
    }

    /**
     * create long press tile
     * @param x
     * @param y
     * @param _startMusicPosition
     * @param _endMusicPosition
     * @param tileWidth
     * @param tileHeight
     * @param stage
     * @param img
     * @param dot
     * @param speed
     */
    Tile(float x, float y,float _startMusicPosition,float _endMusicPosition, float tileWidth, float tileHeight, Stage stage, Texture img, Texture dot, float speed){
        super();
        touchDuration = _endMusicPosition-_startMusicPosition;
        float availableHeigh = Math.max(speed * touchDuration,tileHeight);

        setSize(tileWidth, availableHeigh);

        background = new Image(img);
        background.setSize(tileWidth, availableHeigh);
        this.addActor(background);

        imageDot = new Image(dot);
        imageDot.setPosition(tileWidth/2, 10);
        addActor(imageDot);

        setPosition(x, y);
        stage.addActor(this);
        type = TileType.TILE_HOLD;
        startMusicPosition = _startMusicPosition;
        endMusicPosition = _endMusicPosition;
    }


    private boolean isInRegion(float x, float y){
        float minX = getX();
        float maxX = getX() + getWidth();
        float minY = getY();
        float maxY = getY() + getHeight();
        return (x < maxX && x > minX && y < maxY && y > minY );
    }

    public void moveDown(float speed, float delta){
        float translate = delta*speed;
        setY(getY() - translate);
    }

    public void updatePositionDot(float y){
        if(type != TileType.TILE_HOLD) return;

        float yPosition = y - getY();
        imageDot.setY(yPosition);
    }

    public void touchMove(float x, float y){
        if(startTouch){
            if(!isInRegion(x,y)){
//                isFail = true;
                isFinish = true;
            }else{
                previousTouch.x = x;
                previousTouch.y = y;
            }
        }
    }
    public void touchUp(float x, float y){
        if(startTouch && !isFail){
            isFinish = true;
        }
    }

    public boolean touchDown(float x, float y){
        if(isInRegion(x,y)){
            startTouch = true;
            previousTouch.x = x;
            previousTouch.y = y;
            return true;
        }
        return false;
    }

    public void render(){
        if(startTouch){
            updatePositionDot(previousTouch.y);
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
