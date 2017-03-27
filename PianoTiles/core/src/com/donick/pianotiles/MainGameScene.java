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

    //tmp value
    float minTilePosition;

    Stage stage;
    Group notesGroup;

    Random random = new Random();

    boolean isStartGame = false;
    boolean isDead = false;
    boolean isGod = false;
//    boolean isGod = true;

    ScoreController scoreController;
    BitmapFont scoreFont;
    Label scoreText;


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

        notesGroup = new Group();
        stage.addActor(notesGroup);

        tileWidth = 90;//gameWidth/numberOfTile;
        tileHeight = 160;//gameHeight/numberOfTile;
        tileWidth = gameWidth/numberOfTile;
        tileHeight = gameHeight/numberOfTile;

        scoreController = new ScoreController();

        createScoreText();
        addEventListener();
        createArrayOfPlayer();
        createNodeArray();
        createFirst4Tile();
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
        scoreText.setFontScale(2f, 2f);
        stage.addActor(scoreText);


    }

    public void updateScoreText(){
        scoreText.setText((int)scoreController.getCurrentScore() + "");
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
                if(!isStartGame || isDead){
                    return;
                }
                for(Tile tile: arrayOfTiles){
                    tile.touchMove(x,y);
                }

            }

            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

                if(isDead) return;
                if(!isStartGame){
                    Tile tile = arrayOfTiles.get(0);
                    tile.touchUp(x,y);
                    if(tile.checkFinish()){
                        arrayOfTiles.removeValue(tile, true);
                        tile.removeFromState();
                        isStartGame = true;
                        scoreController.increaseScore();
                        updateScoreText();
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
                        scoreController.increaseScore();
                        updateScoreText();
                    }

                }
                if(isWin == false){
                    doDead();
                }
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

                if(isDead) return false;
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
        Tile tile = new Tile(x,y,nodeInfo.startTime,nodeInfo.endTime,tileWidth,tileHeight,notesGroup,imgTile,isStart);

        // hold tile
//        Tile tile = new Tile(x,y,startPosition,2,tileWidth,tileHeight,notesGroup,imgTile,imgDotTile,tileSpeed);
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
        minTilePosition = gameHeight;
        Tile deleteTile = null;
        for(Tile tile : arrayOfTiles){
            float nextTilePosition = tile.getY();
            if(nextTilePosition < minTilePosition){
                minTilePosition = nextTilePosition;
                deleteTile = tile;
            }
        }
        if(minTilePosition < -100){
            doDead();
            deleteTile.doDead();
//            deleteTile.removeFromState();
//            arrayOfTiles.removeValue(deleteTile, true);

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
//            System.out.println(tileSpeed);
            for (Tile tile : arrayOfTiles) {
                if(!isDead) tile.moveDown(tileSpeed, delta);
                else if(checkCanMoveUp()) tile.moveUp(tileSpeed, delta);
                tile.render();
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
        imgBG.dispose();
        imgDotTile.dispose();
        for(int i = 0; i < arrayOfPlayer.size; i++){
            PlayingSoundAdvande player = arrayOfPlayer.get(i);
            player.dispose();
        }
    }

}
