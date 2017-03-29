package com.donick.pianotiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

/**
 * Created by dinonguyen on 3/28/2017.
 */
public class PopupController {

    Texture imgBG,imgPlayBtn,imgRocket;

    Group gameOverPopup;
    Label scoreTextGameOverPopup;

    PopupController(){

        imgBG = new Texture("space_popup_bg.jpg");
        imgPlayBtn = new Texture("replay_btn.png");
        imgRocket = new Texture("space_rocket.png");
    }

    public void createPopupGameOver(Group parent, float gameWidth, float gameHeight, final InputListener listener, BitmapFont scoreFont, int score){
        gameOverPopup = parent;

        Image bg = new Image(imgBG);
        parent.addActor(bg);

        Image rocket = new Image(imgRocket);
        rocket.setPosition(gameWidth/2-rocket.getWidth()/2,gameHeight/2-rocket.getHeight()/2);
        parent.addActor(rocket);
        Image rocket2 = new Image(imgRocket);
        rocket2.setPosition(rocket.getX()-rocket2.getWidth()-50,gameHeight/2-rocket2.getHeight()/2);
        parent.addActor(rocket2);
        rocket2 = new Image(imgRocket);
        rocket2.setPosition(rocket.getX()+rocket2.getWidth()+50,gameHeight/2-rocket2.getHeight()/2);
        parent.addActor(rocket2);

        Label.LabelStyle textStyle;
        textStyle = new Label.LabelStyle();
        textStyle.font = scoreFont;
//        textStyle.fontColor = Color.BLACK;
        Label scoreText = new Label("" + score, textStyle);
        scoreText.setText("10");
        scoreText.setAlignment(Align.center);
        scoreText.setWrap(true);
        scoreText.setWidth(gameWidth-50);
        scoreText.setPosition(0, 0);
        scoreText.setPosition(gameWidth/2-scoreText.getWidth()/2f, gameHeight*3/4.0f);
        scoreText.setFontScale(3f, 4f);
        scoreTextGameOverPopup = scoreText;
        parent.addActor(scoreText);

        Drawable drawable = new TextureRegionDrawable(new TextureRegion(imgPlayBtn));
        final ImageButton playButton = new ImageButton(drawable);
        playButton.setPosition(gameWidth/2 - playButton.getWidth()/2,gameHeight/4);
        playButton.scaleBy(0.1f);
        final float playButtonWidth = playButton.getWidth();
        final float playButtonHeight = playButton.getHeight();
        final float playButtonX = playButton.getX();
        final float playButtonY = playButton.getY();
        playButton.setOrigin(playButtonWidth/2.0f,playButtonHeight/2.0f);
        playButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                playButton.setSize(playButtonWidth,playButtonHeight);
                playButton.setPosition(playButtonX,playButtonY);
                listener.touchUp(event,x,y,pointer,button);
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                playButton.setSize(playButtonWidth*0.9f,playButtonHeight*0.9f);
                playButton.setPosition(playButtonX+playButtonWidth*0.05f,playButtonY+playButtonHeight*0.05f);

                return true;
            }
        });
        parent.addActor(playButton);

    }

    public void dispose(){

        imgBG.dispose();
        imgPlayBtn.dispose();
    }

}
