package com.donick.screamtogo;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Vector2;

import javax.sound.sampled.AudioFormat;

public class ScreamToGo extends ApplicationAdapter implements InputProcessor {

	short[] data;
	AudioRecorder recordingDevice;
	AudioDevice playbackDevice;
	SpriteBatch batch;
	Sprite sprite;
	Texture img;
	World world;
	Body body;
	Body bodyWallLeft;
	Body bodyObstacleNeed2Move = null;
	float nexXPositionbodyObstacleNeed2Move;
	Body bodyEdgeScreen;
	Box2DDebugRenderer debugRenderer;
	Matrix4 debugMatrix;
	OrthographicCamera camera;
	BitmapFont font;
	float gameWidth;
	float gameHeight;
	Body[] arrayObstacles;



	float widthObstacle = 500;
	float heightObstacle = 500;

	float torque = 0.0f;
	boolean drawSprite = true;

	final float PIXELS_TO_METERS = 100f;


	final short PHYSICS_ENTITY = 0x1;    // 0001
	final short WORLD_ENTITY = 0x1 << 1; // 0010 or 0x2 in hex

	@Override
	public void create () {


		gameWidth = Gdx.graphics.getWidth();
		gameHeight = Gdx.graphics.getHeight();

//		createPhysic();
//		createPhysic2();
		createPhysic3();
		createObstacle();
		createWallLeft();
		setContactListener();


		if(Gdx.app.getType() != Application.ApplicationType.Desktop){
			recordAudio();
		}else{
//			recordAudioTest();
		}
		Gdx.input.setInputProcessor(this);
	}

	public void createPhysic3() {
		batch = new SpriteBatch();
		img = new Texture("player.png");

		// Create two identical sprites slightly offset from each other vertically
		sprite = new Sprite(img);
		sprite.setPosition(sprite.getWidth()/2 + 40 -gameWidth/2,-sprite.getHeight()/2 -200);

		world = new World(new Vector2(0, -4f),true);

		// Both bodies have identical shape
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(sprite.getWidth()/2 / PIXELS_TO_METERS, sprite.getHeight()
				/2 / PIXELS_TO_METERS);

		// Sprite1's Physics body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyDef.position.set((sprite.getX() + sprite.getWidth()/2) /
						PIXELS_TO_METERS,
				(sprite.getY() + sprite.getHeight()/2) / PIXELS_TO_METERS);
		body = world.createBody(bodyDef);

		// Sprite1
		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.shape = shape;
//		fixtureDef.density = 0.1f;
//		fixtureDef.restitution = 0.5f;
		fixtureDef.density = 0f;
		fixtureDef.restitution = 0f;
//		fixtureDef.filter.categoryBits = PHYSICS_ENTITY;
//		fixtureDef.filter.maskBits = WORLD_ENTITY;
//		fixtureDef.isSensor = true;
		body.createFixture(fixtureDef);


		shape.dispose();


//		float w = Gdx.graphics.getWidth()/PIXELS_TO_METERS;
//		float h = Gdx.graphics.getHeight()/PIXELS_TO_METERS;
//		// Now the physics body of the bottom edge of the screen
//		BodyDef bodyDef3 = new BodyDef();
//		bodyDef3.type = BodyDef.BodyType.StaticBody;
//		bodyDef3.position.set(0,0);
//		FixtureDef fixtureDef3 = new FixtureDef();
//		fixtureDef3.filter.categoryBits = WORLD_ENTITY;
//		fixtureDef3.filter.maskBits = PHYSICS_ENTITY;
//		EdgeShape edgeShape = new EdgeShape();
//		edgeShape.set(-w/2,-h/2,w/2,-h/2);
//		fixtureDef3.shape = edgeShape;
//		bodyEdgeScreen = world.createBody(bodyDef3);
//		bodyEdgeScreen.createFixture(fixtureDef3);
//		edgeShape.dispose();

		debugRenderer = new Box2DDebugRenderer();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.
				getHeight());

	}

	public void createWallLeft(){

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(10 / PIXELS_TO_METERS, gameHeight
				/2 / PIXELS_TO_METERS);

		BodyDef bodyDef2 = new BodyDef();
		bodyDef2.type = BodyDef.BodyType.DynamicBody;
//		bodyDef2.type = BodyDef.BodyType.KinematicBody;
//			bodyDef2.type = BodyDef.BodyType.DynamicBody;
		bodyDef2.position.set(-gameWidth/2 /
						PIXELS_TO_METERS,
				(
						0
//									MathUtils.random(0,heightObstacle/2)
				) / PIXELS_TO_METERS);
		bodyWallLeft = world.createBody(bodyDef2);
		// Sprite2
		FixtureDef fixtureDef2 = new FixtureDef();
		fixtureDef2.shape = shape;
//		fixtureDef2.density = 0.1f;
//		fixtureDef2.restitution = 0.5f;
		fixtureDef2.density = 0f;
		fixtureDef2.restitution = 0f;
		fixtureDef2.isSensor = true;
		fixtureDef2.filter.categoryBits = WORLD_ENTITY;
//		fixtureDef2.filter.maskBits = WORLD_ENTITY;
		bodyWallLeft.createFixture(fixtureDef2);
		bodyWallLeft.setGravityScale(0);

		shape.dispose();

	}

	public void createObstacle(){



		arrayObstacles = new Body[4];
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(widthObstacle/2 / PIXELS_TO_METERS, heightObstacle
				/2 / PIXELS_TO_METERS);
		float firstX = 60-gameWidth/2+widthObstacle/2;
		float firstY = -gameHeight/2;
		for(int i = 0; i < 4; i++){

			Body body2;
			BodyDef bodyDef2 = new BodyDef();
			bodyDef2.type = BodyDef.BodyType.StaticBody;
//			bodyDef2.type = BodyDef.BodyType.DynamicBody;
			bodyDef2.position.set(firstX /
							PIXELS_TO_METERS,
					(
							firstY+0
//									MathUtils.random(0,heightObstacle/2)
					) / PIXELS_TO_METERS);
			body2 = world.createBody(bodyDef2);
			// Sprite2
			FixtureDef fixtureDef2 = new FixtureDef();
			fixtureDef2.shape = shape;
//		fixtureDef2.density = 0.1f;
//		fixtureDef2.restitution = 0.5f;
			fixtureDef2.density = 0f;
			fixtureDef2.restitution = 0f;
//			fixtureDef2.isSensor = true;
//		fixtureDef2.filter.categoryBits = WORLD_ENTITY;
//		fixtureDef2.filter.maskBits = WORLD_ENTITY;
			body2.createFixture(fixtureDef2);

			firstX += (widthObstacle + sprite.getWidth()*1.5);

			arrayObstacles[i] = body2;
			body2.setGravityScale(0);

		}

		shape.dispose();
	}

	public void checkObstacleOutOfScreen(){
		Body obstacle = null;
		float maxXObstaclePosition = 0;
		for(int i = 0; i < arrayObstacles.length; i++){

			Body body2 = arrayObstacles[i];
			if(body2.getPosition().x < -gameWidth/2/PIXELS_TO_METERS){
				obstacle = body2;
			}
			float xPosition = body2.getTransform().getPosition().x;
			if(xPosition > maxXObstaclePosition){
				maxXObstaclePosition = xPosition;
			}

		}

		if(obstacle != null) {
			bodyObstacleNeed2Move = obstacle;
			nexXPositionbodyObstacleNeed2Move = maxXObstaclePosition + (widthObstacle + sprite.getWidth() * 1.5f) /
					PIXELS_TO_METERS;
		}

	}

	public void setContactListener(){

		world.setContactListener(new ContactListener() {
			@Override
			public void beginContact(Contact contact) {

				System.out.println("sdf: dsfs");
				if(((contact.getFixtureA().getBody() == bodyWallLeft)||
					contact.getFixtureB().getBody() == bodyWallLeft) &&
						bodyObstacleNeed2Move == null) {
					Body obstacle = (contact.getFixtureA().getBody() == bodyWallLeft)?contact.getFixtureB().getBody():contact.getFixtureA().getBody();
					float maxXObstaclePosition = 0;
					for(int i = 0; i < arrayObstacles.length; i++){

						Body body2 = arrayObstacles[i];
						float xPosition = body2.getTransform().getPosition().x;
						if(xPosition > maxXObstaclePosition){
							maxXObstaclePosition = xPosition;
						}

					}

					bodyObstacleNeed2Move = obstacle;
					nexXPositionbodyObstacleNeed2Move = maxXObstaclePosition+ (widthObstacle+sprite.getWidth()*1.5f)/
							PIXELS_TO_METERS;
				}
			}

			@Override
			public void endContact(Contact contact) {
			}

			@Override
			public void preSolve(Contact contact, Manifold oldManifold) {
			}

			@Override
			public void postSolve(Contact contact, ContactImpulse impulse) {
			}
		});

	}

	public void jump(){
		body.applyForceToCenter(0f,50f,true);
		if(sprite.getY() > 0){
			body.setLinearVelocity(0,0);
			sprite.setY(0);
			body.setTransform((sprite.getX() + sprite.getWidth()/2) /
							PIXELS_TO_METERS,
					(sprite.getY() + sprite.getHeight()/2) / PIXELS_TO_METERS,0);
		}
	}

	public void move(){
		camera.translate(5,0);
//		for(int i = 0; i < arrayObstacles.length; i++){
//
//			Body body2 = arrayObstacles[i];
//			body2.applyForceToCenter(-1f,0f,true);
//
//			body2.setTransform(body2.getTransform().getPosition().x- 1/
//							PIXELS_TO_METERS,
//					body2.getTransform().getPosition().y,0);
//
//		}

	}

	public void recordAudioTest(){
		Timer.schedule(new Timer.Task(){
						   @Override
						   public void run() {
							   new Thread(new Runnable() {
								   @Override
								   public void run() {
//									   jump();
								   }
							   }).start();
						   }
					   }
				, 1.5f        //    (delay)
				, 1.5f     //    (seconds)
		);
	}
	public void recordAudio(){
		int samples = 44100;
		int seconds = 1;
		boolean isMono = true;
		data = new short[samples/4]; //0.5s

		playbackDevice = Gdx.audio.newAudioDevice(samples, isMono);
		recordingDevice = Gdx.audio.newAudioRecorder(samples, isMono);

		Timer.schedule(new Timer.Task(){
						   @Override
						   public void run() {
							   new Thread(new Runnable() {
								   @Override
								   public void run() {
//								   	return;
									   System.out.println("Record: Start");
									   recordingDevice.read(data, 0, data.length);
									   String result = "";
									   int average = 0;
									   for(int i = 0; i < data.length; i++) {
										   average += data[i];
									   }
									   if(average > -1.8){

										   System.out.println("jump");
										   body.setLinearVelocity(0f,2);
									   }

//									   System.out.println("Record: End ->" + average*1.0f/data.length);
								   }
							   }).start();
						   }
					   }
				, 0.5f        //    (delay)
				, 0.5f     //    (seconds)
		);

//		recordingDevice.read(samples, 0, samples.length);
//		playbackDevice.writeSamples(samples, 0, samples.length);
//		recordingDevice.dispose();
//		playbackDevice.dispose();
	}

	@Override
	public void render () {
		camera.update();
		checkObstacleOutOfScreen();
		if(bodyObstacleNeed2Move != null) {
			bodyObstacleNeed2Move.setTransform(nexXPositionbodyObstacleNeed2Move,
					bodyObstacleNeed2Move.getTransform().getPosition().y, 0);
			bodyObstacleNeed2Move = null;
		}
		move();
		// Step the physics simulation forward at a rate of 60hz
		world.step(1f/60f, 6, 2);

		sprite.setPosition((body.getPosition().x * PIXELS_TO_METERS) - sprite.
						getWidth()/2 ,
				(body.getPosition().y * PIXELS_TO_METERS) -sprite.getHeight()/2 );


		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		batch.draw(sprite, sprite.getX(), sprite.getY(),sprite.getOriginX(),
				sprite.getOriginY(),
				sprite.getWidth(),sprite.getHeight(),sprite.getScaleX(),sprite.
						getScaleY(),sprite.getRotation());

		batch.end();


		// Scale down the sprite batches projection matrix to box2D size
		debugMatrix = batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS,
				PIXELS_TO_METERS, 0);
		debugRenderer.render(world, debugMatrix);

	}
	
	@Override
	public void dispose () {
		// Hey, I actually did some clean up in a code sample!
		img.dispose();
		world.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {


		if(keycode == Input.Keys.RIGHT) {

//			body.setTransform(2,2,0);
//				body.setLinearVelocity(1f, 0f);
			move();
		}
		if(keycode == Input.Keys.LEFT)
			body.setLinearVelocity(-1f,0f);

		if(keycode == Input.Keys.UP)
			jump();
//			body.applyForceToCenter(0f,10f,true);
		if(keycode == Input.Keys.DOWN)
			body.applyForceToCenter(0f, -10f, true);

		// On brackets ( [ ] ) apply torque, either clock or counterclockwise
		if(keycode == Input.Keys.RIGHT_BRACKET)
			torque += 0.1f;
		if(keycode == Input.Keys.LEFT_BRACKET)
			torque -= 0.1f;

		// Remove the torque using backslash /
		if(keycode == Input.Keys.BACKSLASH)
			torque = 0.0f;

		// If user hits spacebar, reset everything back to normal
		if(keycode == Input.Keys.SPACE|| keycode == Input.Keys.NUM_2) {
			body.setLinearVelocity(0f, 0f);
			body.setAngularVelocity(0f);
			torque = 0f;
			sprite.setPosition(0f,0f);
			body.setTransform(0f,0f,0f);
		}

		if(keycode == Input.Keys.COMMA) {
			body.getFixtureList().first().setRestitution(body.getFixtureList().first().getRestitution()-0.1f);
		}
		if(keycode == Input.Keys.PERIOD) {
			body.getFixtureList().first().setRestitution(body.getFixtureList().first().getRestitution()+0.1f);
		}
		if(keycode == Input.Keys.ESCAPE || keycode == Input.Keys.NUM_1)
			drawSprite = !drawSprite;

		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}


	// On touch we apply force from the direction of the users touch.
	// This could result in the object "spinning"
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		body.applyForce(1f,1f,screenX/PIXELS_TO_METERS,screenY/PIXELS_TO_METERS,true);
		//body.applyTorque(0.4f,true);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
