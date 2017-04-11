package com.donick.pianotiles;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;


import java.util.Random;

/**
 * Created by dinonguyen on 3/16/2017.
 */
public class MainGameScene {
    Texture imgTile,imgDotTile,imgBG,imgTileObstacle;

    private Array<NodeInfo> nodeArray = null;
    private Array<Tile> arrayOfTiles;
    private  Array<PlayingSoundAdvande> arrayOfPlayer = null;

    float gameWidth;
    float gameHeight;
    float deviceWidth;
    float deviceHeight;
    float timeCount4ShowPopupGameOver = -1;

    float tileWidth, tileHeight;
    float numberOfTile = 4;

    float tileSpeedBase = 700;
    float tileSpeed = tileSpeedBase;

    //tmp value
    float minTilePosition;

    Stage stage;
    Group notesGroup;

    Random random = new Random();

    boolean isStartGame = false;
    boolean isDead = false;
//    boolean isGod = false;
    boolean isGod = true;

    ScoreController scoreController;
    PopupController popupController;
    BitmapFont scoreFont;
    Label scoreText;

    int numberOfNodePlayed = 0;


    public MainGameScene(float _gameWidth, float _gameHeight, float _deviceWidth, float _deviceHeight ){

        gameWidth = _gameWidth;
        gameHeight = _gameHeight;
        deviceWidth = _deviceWidth;
        deviceHeight = _deviceHeight;

        imgTile = new Texture("tile.jpg");
        imgTileObstacle = new Texture("badlogic.jpg");
        imgBG = new Texture("space-1.jpg");
        imgDotTile = new Texture("dot.png");

        arrayOfTiles = new Array<Tile>();

        stage = new Stage(new StretchViewport(gameWidth,gameHeight));

        Image background = new Image(imgBG);
        stage.addActor(background);

        notesGroup = new Group();
        stage.addActor(notesGroup);

        tileWidth = 90;//gameWidth/numberOfTile;
        tileHeight = 160;//gameHeight/numberOfTile;
        tileWidth = gameWidth/numberOfTile;
        tileHeight = gameHeight/numberOfTile;

        scoreController = new ScoreController();
        popupController = new PopupController();

        createScoreText();
        addEventListener();
        createArrayOfPlayer();
        createNodeArray();
        createFirst4Tile();
        createPopupGameOver();

    }

    void restart(){
        isDead = false;
        isStartGame = false;
        popupController.gameOverPopup.setVisible(false);
        scoreController.resetScore();
        createNodeArray();
        createFirst4Tile();
        updateScoreText();
        numberOfNodePlayed = 0;
        tileSpeed = tileSpeedBase;
    }

    void createPopupGameOver(){

        Group gameOverPopup = new Group();
        stage.addActor(gameOverPopup);
        gameOverPopup.setVisible(false);
        popupController.createPopupGameOver(gameOverPopup,gameWidth,gameHeight,new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                //restart game
                restart();
            }
        },scoreFont,scoreController.getCurrentScore());
    }

    void createScoreText(){

        scoreFont = new BitmapFont(Gdx.files.internal("font_point.fnt"), Gdx.files.internal("font_point.png"), false);
        Label.LabelStyle textStyle;
        textStyle = new Label.LabelStyle();
        textStyle.font = scoreFont;
//        textStyle.fontColor = Color.BLACK;
        scoreText = new Label("", textStyle);
        scoreText.setText("0");
        scoreText.setAlignment(Align.center);
        scoreText.setPosition(0, 0);
        scoreText.setPosition(gameWidth/2, gameHeight-70);
        scoreText.setFontScale(2f, 2.5f);
        stage.addActor(scoreText);

    }

    public void updateScoreText(){
        scoreText.setText((int)scoreController.getCurrentScore() + "");
    }

    void createArrayOfPlayer(){
        int numberOfPlayer = 6;
        arrayOfPlayer = new Array<PlayingSoundAdvande>();
        for(int i = 0; i < numberOfPlayer; i++){
            PlayingSoundAdvande player = new PlayingSoundAdvande(Gdx.files.internal("music/PianoNote.mp3"));
            arrayOfPlayer.add(player);
        }
    }

    void createNodeArray(){
        if(nodeArray != null) nodeArray.clear();
        nodeArray = NodeController.createNodeArraySong1();
    }

    boolean checkFinishAndRemoveTile(Tile tile){
        boolean isFinish = tile.checkFinish();

        if(isFinish){
            if(!tile.isObstacleTile()){
//                removeTile(tile);
                scoreController.increaseScore();
                updateScoreText();
            }
        }

        return isFinish;
    }

    void addEventListener(){
        stage.addListener(new InputListener(){

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                if(!isStartGame || isDead){
                    return;
                }
                for(Tile tile: arrayOfTiles){
                    tile.touchMove(x,y, pointer);
                }

                for(int i = 0; i < arrayOfTiles.size; i++){
                    Tile tile = arrayOfTiles.get(i);
                    if(checkFinishAndRemoveTile(tile)){
                        if(tile.isObstacleTile()){
                            tile.doDead();
                            doDead();
                        }else {
                            i--;

                        }
                    }
                }

            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                if(isDead) return;
                if(!isStartGame){
                    Tile tile = arrayOfTiles.get(0);
                    tile.touchUp(x,y, pointer);

                    if(checkFinishAndRemoveTile(tile))
                        isStartGame = true;

                    return;
                }

                for(int i = 0; i < arrayOfTiles.size; i++){
                    Tile tile = arrayOfTiles.get(i);
                    tile.touchUp(x,y, pointer);
                    if(checkFinishAndRemoveTile(tile)){
                        if(tile.isObstacleTile()){
                            tile.doDead();
                            doDead();
                        }else {
                            i--;

                        }
                    }
                }

            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {


                if(isDead) return false;
                if(!isStartGame){
                    Tile tile = arrayOfTiles.get(0);
                    if (tile.touchDown(x, y, pointer)) playerSoundByNextFreePlayer(tile);
                }else {
                    Tile chosenTile = null;
                    for (Tile tile : arrayOfTiles) {
                        if (tile.touchDown(x, y, pointer)) chosenTile = tile;
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
            for (int i = 0; i < arrayOfPlayer.size-1; i++) {
                PlayingSoundAdvande player = arrayOfPlayer.get(i);
                if (!player.isPlaying()) {
                    chosenPlayer = player;
                    break;
                }
            }
        }
        if(chosenPlayer == null) chosenPlayer = arrayOfPlayer.get(arrayOfPlayer.size-1);
        if(chosenPlayer != null){
            chosenPlayer.play(tile.startMusicPosition,tile.endMusicPosition);
        }


    }

    void checkAndSpawnNextTile(){

        float lastTilePosition = getLastTilePosition();
        if(lastTilePosition <= gameHeight){
            // create tiles
            createTitles(lastTilePosition,false);
        }
    }

    float getLastTilePosition(){
        float lastTilePosition = 0;
        for(Tile tile : arrayOfTiles){
            float nextTilePosition = tile.getY() + tile.getHeight();
            if(nextTilePosition > lastTilePosition){
                lastTilePosition = nextTilePosition;
            }
        }
        return lastTilePosition;
    }

    void createFirst4Tile(){
        while(arrayOfTiles.size > 0){
            Tile tile = arrayOfTiles.get(0);
            arrayOfTiles.removeValue(tile, true);
            tile.removeFromState();
        }

        for(int i = 0; i < 4; i++){
            float lastTilePosition = getLastTilePosition();
            createTitles(lastTilePosition, i==0);
        }
    }

    void createTitles(float y, boolean isStart){
        if(isDead) return;
        if(nodeArray.size == 0) {
            doDead();
            return;
        }

        int numberOfTileOneRow = 4;
        int xID = random.nextInt(numberOfTileOneRow);
        float x = xID * tileWidth;

        NodeInfo nodeInfo = nodeArray.get(0);
        nodeArray.removeValue(nodeInfo,true);
        //tab tile
//        float x, float y,float _startMusicPosition,float _endMusicPosition, float tileWidth, float tileHeight, Group notesGroup, Texture img, Texture dot, float speed,boolean isStart
        Tile tile = new Tile(x,y,nodeInfo.startTime,nodeInfo.endTime,tileWidth,tileHeight,notesGroup,imgTile,imgDotTile,tileSpeed,isStart);
        arrayOfTiles.add(tile);
        for(int i = 0; i <numberOfTileOneRow;i++){
            if(i != xID){
//                Tile tileObstacle = new Tile(i*tileWidth,y,tileWidth,tileHeight,notesGroup,imgTileObstacle);
                Tile tileObstacle = new Tile(i*tileWidth,y,0,0,tileWidth,tileHeight,notesGroup,imgTile,null,tileSpeed,false);
                arrayOfTiles.add(tileObstacle);
            }
        }
        // hold tile
//        Tile tile = new Tile(x,y,startPosition,2,tileWidth,tileHeight,notesGroup,imgTile,imgDotTile,tileSpeed);
        if(!isGod) {

            if (scoreController.getCurrentScore() >= 125)
                tileSpeed = tileSpeedBase * 1.75f;
            else if (scoreController.getCurrentScore() >= 100)
                tileSpeed = tileSpeedBase * 1.5f;
            else if (scoreController.getCurrentScore() >= 50)
                tileSpeed = tileSpeedBase * 1.25f;
            else if (scoreController.getCurrentScore() >= 25)
                tileSpeed = tileSpeedBase * 1.125f;
            else
                tileSpeed = tileSpeedBase;

//            System.out.println("tileSpeed " + tileSpeed);

        }
    }

    void doDead(){
        if(!isGod) {
            isDead = true;
            timeCount4ShowPopupGameOver = 0;
        }
    }

    void removeTile(Tile tile){
        arrayOfTiles.removeValue(tile, true);
        tile.removeFromState();
    }

    void checkDead(){
        minTilePosition = gameHeight;
        Tile deleteTile = null;
        for(int i = 0; i < arrayOfTiles.size; i++){
            Tile tile = arrayOfTiles.get(i);
            if(tile.isDead()){
                if(tile.getY() + tile.getHeight() < 0){
                    removeTile(tile);
                }
                continue;
            }
            float nextTilePosition = tile.getRealYPosition();
            if(nextTilePosition < minTilePosition){
                minTilePosition = nextTilePosition;
                deleteTile = tile;
            }
        }
        if(minTilePosition < -100){
            if(deleteTile.isObstacleTile()){
                deleteTile.removeFromState();
                arrayOfTiles.removeValue(deleteTile, true);
            }else{
                deleteTile.doDead();
                doDead();
            }

        }

    }

    boolean checkCanMoveUp(){
        return (minTilePosition <= -1);
    }


    public void render (float delta) {

//        if(delta > 1){
//            delta /= 60f;
//        }
//        delta = 0.01f;

        checkDead();
        if(isStartGame) {
            if(timeCount4ShowPopupGameOver >= 2){
                popupController.gameOverPopup.setVisible(true);
                popupController.scoreTextGameOverPopup.setText(""+scoreController.getCurrentScore());
                timeCount4ShowPopupGameOver = -1;
            }
//            System.out.println(tileSpeed);
            for (Tile tile : arrayOfTiles) {
                if(!isDead) tile.moveDown(tileSpeed, delta);
                else if(checkCanMoveUp()) {
                    tile.moveUp(tileSpeed, delta);
                }
                tile.render();
            }
            if(isDead && !checkCanMoveUp() && timeCount4ShowPopupGameOver >= 0){
                timeCount4ShowPopupGameOver+=delta;
            }
            if(!isDead) {
                checkAndSpawnNextTile();
            }
        }
        if(arrayOfPlayer != null)  for (int i = 0; i < arrayOfPlayer.size; i++) arrayOfPlayer.get(i).render();

        stage.act(); //Perform ui logic
        stage.draw(); //Draw the ui
    }

    public void dispose () {
        imgTile.dispose();
        imgTileObstacle.dispose();
        imgBG.dispose();
        imgDotTile.dispose();
        popupController.dispose();
        for(int i = 0; i < arrayOfPlayer.size; i++){
            PlayingSoundAdvande player = arrayOfPlayer.get(i);
            player.dispose();
        }
    }

}
