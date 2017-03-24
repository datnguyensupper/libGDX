package com.donick.pianotiles;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
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
    Texture imgTile,imgDotTile,imgBG;

    private Array<NodeInfo> nodeArray;
    private Array<Tile> arrayOfTiles;
    private  Array<PlayingSoundAdvande> arrayOfPlayer = null;

    float gameWidth;
    float gameHeight;
    float deviceWidth;
    float deviceHeight;

    float tileWidth, tileHeight;
    float numberOfTile = 4;

    float tileSpeed = 400;

    Stage stage;

    Random random = new Random();

    boolean isStartGame = false;
    boolean isDead = false;
//    boolean isGod = false;
    boolean isGod = true;


    public MainGameScene(float _gameWidth, float _gameHeight, float _deviceWidth, float _deviceHeight ){
        gameWidth = _gameWidth;
        gameHeight = _gameHeight;
        deviceWidth = _deviceWidth;
        deviceHeight = _deviceHeight;

        imgTile = new Texture("tile.jpg");
        imgBG = new Texture("space-1.jpg");
        imgDotTile = new Texture("dot.png");

        arrayOfTiles = new Array<Tile>();

        stage = new Stage(new StretchViewport(gameWidth,gameHeight));

        Image background = new Image(imgBG);
        stage.addActor(background);

        tileWidth = 90;//gameWidth/numberOfTile;
        tileHeight = 160;//gameHeight/numberOfTile;
        tileWidth = gameWidth/numberOfTile;
        tileHeight = gameHeight/numberOfTile;

        addEventListener();
        createArrayOfPlayer();
        createNodeArray();
        createFirst4Tile();
    }

    void createArrayOfPlayer(){
        int numberOfPlayer = 4;
        arrayOfPlayer = new Array<PlayingSoundAdvande>();
        for(int i = 0; i < numberOfPlayer; i++){
            PlayingSoundAdvande player = new PlayingSoundAdvande(Gdx.files.internal("music/Level1_InspirationalPianoMusic.mp3"));
            arrayOfPlayer.add(player);
        }
    }

    void createNodeArray(){
        nodeArray = NodeController.createNodeArraySong1();
    }

    void addEventListener(){
        stage.addListener(new InputListener(){

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if(!isStartGame){
                    return;
                }
                for(Tile tile: arrayOfTiles){
                    tile.touchMove(x,y);
                }

            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                if(!isStartGame){
                    Tile tile = arrayOfTiles.get(0);
                    tile.touchUp(x,y);
                    if(tile.checkFinish()){
                        arrayOfTiles.removeValue(tile, true);
                        tile.removeFromState();
                        isStartGame = true;
                    }
                    return;
                }
                boolean isWin = false;
                for(int i = 0; i < arrayOfTiles.size; i++){
                    Tile tile = arrayOfTiles.get(i);
                    tile.touchUp(x,y);
                    if(tile.checkFinish()){
                        arrayOfTiles.removeValue(tile, true);
                        tile.removeFromState();
                        i--;
                        isWin = true;
                    }

                }
                if(isWin == false){
                    doDead();
                }
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                if(!isStartGame){
                    Tile tile = arrayOfTiles.get(0);
                    if (tile.touchDown(x, y)) playerSoundByNextFreePlayer(tile);
                }else {
                    Tile chosenTile = null;
                    for (Tile tile : arrayOfTiles) {
                        if (tile.touchDown(x, y)) chosenTile = tile;
                    }
                    if (chosenTile != null) {
                        playerSoundByNextFreePlayer(chosenTile);
                    }
                }
                return true;
            }

        });

        Gdx.input.setInputProcessor(stage);
    }

    void playerSoundByNextFreePlayer(Tile tile){
        PlayingSoundAdvande chosenPlayer = null;
        if(arrayOfPlayer != null) {
            for (int i = 0; i < arrayOfPlayer.size; i++) {
                PlayingSoundAdvande player = arrayOfPlayer.get(i);
                if (!player.isPlaying()) {
                    chosenPlayer = player;
                    break;
                }
            }
        }
        if(chosenPlayer != null){
            chosenPlayer.play(tile.startMusicPosition,tile.endMusicPosition);
        }
    }

    void checkAndSpawnNextTile(){
        float lastTilePosition = gameHeight;
        for(Tile tile : arrayOfTiles){
            float nextTilePosition = tile.getY() + tile.getHeight();
            if(nextTilePosition > lastTilePosition){
                lastTilePosition = nextTilePosition;
            }
        }

        if(lastTilePosition <= gameHeight){
            // create tiles
            float randomX = random.nextInt(4) * tileWidth;
            createTitles(randomX,lastTilePosition,false);
        }
    }

    void createFirst4Tile(){
        for(int i = 0; i < 4; i++){
            float randomX = random.nextInt(4) * tileWidth;
            createTitles(randomX,tileHeight*i, i==0);
        }
    }

    void createTitles(float x, float y, boolean isStart){
        if(nodeArray.size == 0) return ;

        NodeInfo nodeInfo = nodeArray.get(0);
        nodeArray.removeValue(nodeInfo,true);
        //tab tile
        Tile tile = new Tile(x,y,nodeInfo.startTime,nodeInfo.endTime,tileWidth,tileHeight,stage,imgTile,isStart);

        // hold tile
//        Tile tile = new Tile(x,y,startPosition,2,tileWidth,tileHeight,stage,imgTile,imgDotTile,tileSpeed);
        arrayOfTiles.add(tile);
        if(nodeArray.size <= 25)
            tileSpeed = 400*3f;
        else if(nodeArray.size <= 50)
            tileSpeed = 400*2.5f;
        else if(nodeArray.size <= 100)
            tileSpeed = 400*2f;
        else if(nodeArray.size <= 125)
            tileSpeed = 400*1.5f;
    }

    void doDead(){
        if(!isGod) {
            isDead = true;
        }
    }

    void checkDead(){
        float firstTilePosition = gameHeight;
        Tile deleteTile = null;
        for(Tile tile : arrayOfTiles){
            float nextTilePosition = tile.getY();
            if(nextTilePosition < firstTilePosition){
                firstTilePosition = nextTilePosition;
                deleteTile = tile;
            }
        }
        if(firstTilePosition < 0){
            doDead();
            deleteTile.removeFromState();
            arrayOfTiles.removeValue(deleteTile, true);

        }

    }


    public void render (float delta) {

//        if(delta > 1){
//            delta /= 60f;
//        }
//        delta = 0.01f;
        checkDead();
        if(!isDead && isStartGame) {
//            System.out.println(tileSpeed);
            for (Tile tile : arrayOfTiles) {
                tile.moveDown(tileSpeed,delta);
                tile.render();
            }
            if(arrayOfPlayer != null)  for (int i = 0; i < arrayOfPlayer.size; i++) arrayOfPlayer.get(i).render();
            checkAndSpawnNextTile();
        }

        stage.act(); //Perform ui logic
        stage.draw(); //Draw the ui
    }

    public void dispose () {
        imgTile.dispose();
        imgBG.dispose();
        imgDotTile.dispose();
        for(int i = 0; i < arrayOfPlayer.size; i++){
            PlayingSoundAdvande player = arrayOfPlayer.get(i);
            player.dispose();
        }
    }

}
