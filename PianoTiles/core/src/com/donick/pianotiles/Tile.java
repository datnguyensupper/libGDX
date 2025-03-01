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
        TILE_OBSTACLE,
        TILE_HOLD,
        TILE_BLANK,
    }

    float startMusicPosition;
    float endMusicPosition;
    private boolean startTouch;
    private boolean isFinish = false;
    private boolean isFail = false;
    private TileType type;
    private Image imageDot;
    private Image background = null;
    private BitmapFont font;
    private boolean isDead = false;
    int countFlashForDeadMode = 0;
    int pointer = -1;
    PlayingSoundAdvande soundAdvande = null;
    int point = 1;

    private Vector2 previousTouch = Vector2.Zero;

    /**
     *
     * @param x
     * @param y
     * @param _startMusicPosition
     * @param _endMusicPosition
     * @param tileWidth
     * @param tileHeight
     * @param notesGroup
     * @param img
     * @param dot
     * @param speed
     * @param isStart
     */
    Tile(float x, float y,float _startMusicPosition,float _endMusicPosition, float tileWidth, float tileHeight, Group notesGroup, Texture img, Texture dot, float speed,boolean isStart){
        if(_startMusicPosition < 0){
            //obstacle node
            updateTile( x, y, tileWidth, tileHeight, notesGroup,img);
            type = TileType.TILE_BLANK;
        }else if(_startMusicPosition == _endMusicPosition){
            //blank node
            updateTile( x, y, tileWidth, tileHeight, notesGroup,img);
        }
            else if(_endMusicPosition - _startMusicPosition <= 0.5){
            // short node
            updateTile( x, y,_startMusicPosition, _endMusicPosition, tileWidth, tileHeight, notesGroup, img, isStart);
        }else{
            // long node
            updateTile( x, y,_startMusicPosition,_endMusicPosition, tileWidth, tileHeight, notesGroup, img, dot, speed);
        }
    }


    /**
     * create tab tile
     * @param x
     * @param y
     * @param tileWidth
     * @param tileHeight
     * @param notesGroup
     * @param img
     */
    private void updateTile(float x, float y,float _startMusicPosition, float _endMusicPosition, float tileWidth, float tileHeight,
         Group notesGroup, Texture img,boolean isStart ){

        int extraSize = -10;
        background = new Image(img);
        background.setSize(tileWidth, tileHeight+extraSize);
        background.setY(-extraSize/2);
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
        notesGroup.addActor(this);
//        this.setZIndex(2);

        type = TileType.TILE_TAB;
        startMusicPosition = _startMusicPosition;
        endMusicPosition = _endMusicPosition;
    }

    /**
     * create tab tile
     * @param x
     * @param y
     * @param tileWidth
     * @param tileHeight
     * @param notesGroup
     */
    private void updateTile(float x, float y,float tileWidth, float tileHeight, Group notesGroup, Texture img){
        int extraSize = -10;
        background = new Image(img);
        background.setVisible(false);
        background.setColor(Color.RED);
        background.setSize(tileWidth, tileHeight+extraSize);
        background.setY(-extraSize/2);
        this.addActor(background);


        setSize(tileWidth, tileHeight);
        setPosition(x, y);

        notesGroup.addActor(this);
//        this.setZIndex(2);

        type = TileType.TILE_OBSTACLE;

    }

    /**
     * create long press tile
     * @param x
     * @param y
     * @param _startMusicPosition
     * @param _endMusicPosition
     * @param tileWidth
     * @param tileHeight
     * @param notesGroup
     * @param img
     * @param dot
     * @param speed
     */
    private void updateTile(float x, float y,float _startMusicPosition,float _endMusicPosition, float tileWidth, float tileHeight, Group notesGroup, Texture img, Texture dot, float speed){

        float availableHeigh = (_endMusicPosition-_startMusicPosition)*tileHeight/0.5f;

        setSize(tileWidth, availableHeigh);

        int extraSize = -10;
        background = new Image(img);
        background.setSize(tileWidth, availableHeigh+extraSize);
        this.addActor(background);

        imageDot = new Image(dot);
        imageDot.setPosition(tileWidth/2-imageDot.getWidth()/2, 10);
        addActor(imageDot);

        setPosition(x, y);
        notesGroup.addActor(this);
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

    private boolean isInXRegion(float x){
        float minX = getX();
        float maxX = getX() + getWidth();
        return (x < maxX && x > minX);
    }

    public void moveDown(float speed, float delta){
        float translate = delta*speed;
        setY(getY() - translate);
    }

    public void moveUp(float speed, float delta){
        float translate = -delta*speed;
        setY(getY() - translate);
    }

    public void updatePositionDot(float y){
        if(type != TileType.TILE_HOLD) return;

        float yPosition = Math.max(y - getY(),imageDot.getHeight()/2 );
        yPosition = Math.min(yPosition,getHeight()-imageDot.getHeight()/2 );
        if(imageDot != null && !isFinish) {
            imageDot.setY(yPosition-imageDot.getHeight()/2);
        }
    }

    public void setSoundAdvande(PlayingSoundAdvande _soundAdvande){
        soundAdvande = _soundAdvande;
    }

    void doFinish(){
        isFinish = true;

        if(soundAdvande != null){
            float duration = soundAdvande.finishToNearest();
            if(duration > 0.5) {
                point = Math.max(1, (int) (duration / 0.5f));
            }
        }
    }

    public void touchMove(float x, float y, int _pointer){
        if(isDead || isFinish) return;
        if(startTouch && pointer == _pointer){
            if(!isInRegion(x,y)){
//                isFail = true;
                doFinish();
            }else{
                previousTouch.x = x;
                previousTouch.y = y;
            }
        }
    }
    public void touchUp(float x, float y, int _pointer){
        if(isDead || isFinish) return;

        if(startTouch && !isFail && pointer == _pointer){
            doFinish();
        }
    }

    public boolean touchDown(float x, float y, int _pointer){
        if(isDead || isFinish) return false;

        if(isInRegion(x,y)){
            startTouch = true;
            pointer = _pointer;
            previousTouch.x = x;
            previousTouch.y = y;
            return true;
        }
        return false;
    }

    public void doDead(){
        isDead = true;
    }

    public void render(){
        if(isDead) {
            if(background != null && type != TileType.TILE_BLANK) {
                if (countFlashForDeadMode < 10) background.setVisible(false);
                else background.setVisible(true);
            }
            if(countFlashForDeadMode > 20) countFlashForDeadMode = 0;
            countFlashForDeadMode++;
            return;
        }else if((isFinish || startTouch) && type != TileType.TILE_OBSTACLE && type != TileType.TILE_BLANK){
            background.getColor().a = 0.5f;
        }
        if(startTouch){
            updatePositionDot(previousTouch.y);
        }
    }

    public boolean isDead(){
        return isDead;
    }

    public boolean checkFinish(){
        return isFinish;
    }

    public boolean checkFail(){
        return isFail;
    }

    public boolean isObstacleTile(){
        return type == TileType.TILE_OBSTACLE;
    }

    public int getPoint(){
        return point;
    }

    public void resetPoint(){
        point = 0;
    }
    public void removeFromState(){
        if(font != null) font.dispose();
        soundAdvande = null;
        this.remove();
    }

    public float getRealYPosition(){
        if(type == TileType.TILE_TAB || type == TileType.TILE_OBSTACLE) return getY();
        else if(type == TileType.TILE_HOLD) return getY() + imageDot.getY();
        return getY();
    }

}
