package com.donick.pianotiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

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
    BitmapFont font;

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
    Tile(float x, float y,float _startMusicPosition, float _endMusicPosition, float tileWidth, float tileHeight,
         Stage stage, Texture img, boolean isStart){
        super();

        background = new Image(img);
        background.setSize(tileWidth, tileHeight+4);
        setSize(tileWidth, tileHeight);
        setPosition(x, y);
        this.addActor(background);

        if(isStart) {
            font = new BitmapFont(Gdx.files.internal("carrier_command.xml"), Gdx.files.internal("carrier_command.png"), false);
            Label.LabelStyle textStyle;
            textStyle = new Label.LabelStyle();
            textStyle.font = font;
            textStyle.fontColor = Color.BLACK;
            Label textPopupRestartPoint = new Label("", textStyle);
            textPopupRestartPoint.setText("S\n\nT\n\nA\n\nR\n\nT");
            textPopupRestartPoint.setAlignment(Align.center);
            textPopupRestartPoint.setPosition(0, 0);
            textPopupRestartPoint.setPosition(tileWidth / 2, tileHeight / 2);
            textPopupRestartPoint.setFontScale(1.5f, 1.2f);
            this.addActor(textPopupRestartPoint);
        }
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
        if(font != null) font.dispose();
        this.remove();
    }

}
