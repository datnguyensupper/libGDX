package com.donick.screamtogo;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import java.util.Random;

/**
 * Created by dinonguyen on 3/11/2017.
 */
public class EnemyController {

    Texture imgThorn, imgEnemyMusicNote;
    final float PIXELS_TO_METERS = 100f;

    EnemyController(){

        imgThorn = new Texture("thorn.png");
        imgEnemyMusicNote = new Texture("enemy_note.png");
    }

    Body createRandomEnemy(World world, float x, float gameHeight){
        Random random = new Random();
        int randomInt = random.nextInt(2);
        if(randomInt == 0){
            return createEnemyCloud(world,x,gameHeight);
        }
        return createEnemyNote(world,x,gameHeight);
    }

    Body createEnemyCloud(World world, float x, float gameHeight){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(imgThorn.getWidth()/2 / PIXELS_TO_METERS, imgThorn.getHeight()
                /2 / PIXELS_TO_METERS);

        Sprite spriteEnemy = new Sprite(imgThorn);
        spriteEnemy.setPosition(x,gameHeight/2-spriteEnemy.getHeight()*2/3.0f);

        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.type = BodyDef.BodyType.StaticBody;
        bodyDef2.position.set(
                new Vector2(
                        (spriteEnemy.getX() + spriteEnemy.getWidth()/2) / PIXELS_TO_METERS,
                        (spriteEnemy.getY() + spriteEnemy.getHeight()/2) / PIXELS_TO_METERS));
        Body bodyEnemy = world.createBody(bodyDef2);


        bodyEnemy.setUserData(spriteEnemy);
        // Sprite2
        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.shape = shape;
        fixtureDef2.density = 0f;
        fixtureDef2.restitution = 0f;
        bodyEnemy.createFixture(fixtureDef2);


        bodyEnemy.setGravityScale(0);

        shape.dispose();

        return bodyEnemy;
    }

    Body createEnemyNote(World world, float x, float gameHeight){
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(imgEnemyMusicNote.getWidth()/2 / PIXELS_TO_METERS, imgEnemyMusicNote.getHeight()
                /2 / PIXELS_TO_METERS);

        Sprite spriteEnemy = new Sprite(imgEnemyMusicNote);
        spriteEnemy.setPosition(x-2000+100,gameHeight/2);
//        spriteEnemy.setScale(0.5f);

        BodyDef bodyDef2 = new BodyDef();
        bodyDef2.type = BodyDef.BodyType.DynamicBody;
        bodyDef2.position.set(
                new Vector2(
                        (spriteEnemy.getX() + spriteEnemy.getWidth()/2) / PIXELS_TO_METERS,
                        (spriteEnemy.getY() + spriteEnemy.getHeight()/2) / PIXELS_TO_METERS));
        Body bodyEnemy = world.createBody(bodyDef2);


        bodyEnemy.setUserData(spriteEnemy);
        // Sprite2
        FixtureDef fixtureDef2 = new FixtureDef();
        fixtureDef2.shape = shape;
        fixtureDef2.density = 0f;
        fixtureDef2.restitution = 0f;
        bodyEnemy.createFixture(fixtureDef2);


        shape.dispose();

        return bodyEnemy;
    }

    public void dispose () {
        // Hey, I actually did some clean up in a code sample!
        imgThorn.dispose();
    }
}
