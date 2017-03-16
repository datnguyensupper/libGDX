package com.donick.pianotiles;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import java.util.Random;

/**
 * Created by dinonguyen on 3/16/2017.
 */
public class MainGameScene {
    Texture imgTile;
    private Array<Tile> arrayOfTiles;

    float gameWidth;
    float gameHeight;
    float deviceWidth;
    float deviceHeight;

    float tileWidth, tileHeight;
    float numberOfTile = 4;

    float tileSpeed = 200;

    Stage stage;

    Random random = new Random();

    boolean isDead = false;

    public MainGameScene(float _gameWidth, float _gameHeight, float _deviceWidth, float _deviceHeight ){
        gameWidth = _gameWidth;
        gameHeight = _gameHeight;
        deviceWidth = _deviceWidth;
        deviceHeight = _deviceHeight;

        imgTile = new Texture("tile.jpg");

        arrayOfTiles = new Array<Tile>();

        stage = new Stage(new StretchViewport(gameWidth,gameHeight));
        tileWidth = gameWidth/numberOfTile;
        tileHeight = gameHeight/numberOfTile;

        addEventListener();
    }

    void addEventListener(){
        stage.addListener(new InputListener(){

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                for(Tile tile: arrayOfTiles){
                    tile.touchMove(x,y);
                }

            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                for(int i = 0; i < arrayOfTiles.size; i++){
                    Tile tile = arrayOfTiles.get(i);
                    tile.touchUp(x,y);
                    if(tile.checkFinish()){
                        arrayOfTiles.removeValue(tile, true);
                        tile.removeFromState();
                        i--;
                    }

                }
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                for(Tile tile: arrayOfTiles){
                    tile.touchDown(x,y);
                }
                return true;
            }

        });

        Gdx.input.setInputProcessor(stage);
    }

    float checkLastTilePosition(){
        float lastTilePosition = gameHeight;
        for(Tile tile : arrayOfTiles){
            float nextTilePosition = tile.getY() + tile.getHeight();
            if(nextTilePosition > lastTilePosition){
                lastTilePosition = nextTilePosition;
            }
        }
        return lastTilePosition;
    }
    void checkAndSpawnNextTile(){
        float lastTilePosition = checkLastTilePosition();
        if(lastTilePosition <= gameHeight){

            // create tiles
            float randomX = random.nextInt(4) * tileWidth;
            createTitles(randomX,lastTilePosition);
        }
    }

    void createTitles(float x, float y){

        Tile tile = new Tile(x,y,tileWidth,tileHeight,stage,imgTile);
        arrayOfTiles.add(tile);
    }

    void checkDead(){
        float firstTilePosition = gameHeight;
        for(Tile tile : arrayOfTiles){
            float nextTilePosition = tile.getY();
            if(nextTilePosition < firstTilePosition){
                firstTilePosition = nextTilePosition;
            }
        }
        if(firstTilePosition < 0){
            isDead = true;
        }

    }


    public void render (float delta) {

        checkDead();
        if(!isDead) {
            for (Tile tile : arrayOfTiles) {
                tile.setY(tile.getY() - delta * tileSpeed);
            }
        }

        stage.act(); //Perform ui logic
        stage.draw(); //Draw the ui
        checkAndSpawnNextTile();
    }

    public void dispose () {
        imgTile.dispose();
    }

}
