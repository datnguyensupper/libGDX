package com.donick.pianotiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by dinonguyen on 3/28/2017.
 */
public class PopupController {
    Texture imgBG;

    PopupController(){

        imgBG = new Texture("dot.png");
    }

    public void createPopupGameOver(Group parent){

        Image bg = new Image(imgBG);
        parent.addActor(bg);
    }

    public void dispose(){
        imgBG.dispose();
    }

}
