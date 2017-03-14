package com.donick.pianotiles;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class PianoTiles extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img,imgTile;

	float gameWidth;
	float gameHeight;
	float deviceWidth;
	float deviceHeight;
	float scaleRatio;
	OrthographicCamera camera;

	private Array<Float> arrayOfTiles;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		imgTile = new Texture("tile.jpg");

		deviceWidth = Gdx.graphics.getWidth();
		deviceHeight = Gdx.graphics.getHeight();
		gameWidth = 900;
		gameHeight = 1600;
		scaleRatio = deviceHeight/gameHeight;
		camera = new OrthographicCamera(gameWidth,gameHeight);

		arrayOfTiles = new Array<Float>();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(img,-img.getWidth()/2,-img.getHeight()/2);
		batch.end();

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		imgTile.dispose();
	}
}
