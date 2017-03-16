package com.donick.pianotiles;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public class PianoTiles extends ApplicationAdapter {
	SpriteBatch batch;

	float gameWidth;
	float gameHeight;
	float deviceWidth;
	float deviceHeight;
	float scaleRatio;
	OrthographicCamera camera;

	MainGameScene mainGame;
	
	@Override
	public void create () {
		batch = new SpriteBatch();

		deviceWidth = Gdx.graphics.getWidth();
		deviceHeight = Gdx.graphics.getHeight();
		gameWidth = 900;
		gameHeight = 1600;
		scaleRatio = deviceHeight/gameHeight;
		camera = new OrthographicCamera(gameWidth,gameHeight);

		mainGame = new MainGameScene(gameWidth, gameHeight, deviceWidth, deviceHeight);
	}


	@Override
	public void render () {
		float deltaTime = Gdx.graphics.getDeltaTime();

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.end();

		mainGame.render(deltaTime);

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		mainGame.dispose();
	}
}
