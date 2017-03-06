package com.donick.screamtogo;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.AudioDevice;
import com.badlogic.gdx.audio.AudioRecorder;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import sun.net.www.protocol.file.Handler;

import javax.sound.sampled.AudioFormat;
import java.util.jar.Manifest;

public class ScreamToGo extends ApplicationAdapter implements InputProcessor {

	short[] data;
	AudioRecorder recordingDevice;
	AudioDevice playbackDevice;
	SpriteBatch batch;
	Texture imgPlayer,imgObstacle,imgPlayBtn;
	ImageButton playButton;
	World world;
	Body bodyPlayer;
	Body bodyWallLeft;
	Body bodyObstacleNeed2Move = null;
	float nexXPositionbodyObstacleNeed2Move;
	Body bodyEdgeScreen;
	Box2DDebugRenderer debugRenderer;
	Matrix4 debugMatrix;
	OrthographicCamera camera;
	BitmapFont font,fontNormal;
	float gameWidth;
	float gameHeight;
	float deviceWidth;
	float deviceHeight;
	Body[] arrayObstacles;
	float yPositionOfObstacles;
	boolean isDead = true;
	float currentScore = 0;
	float highScore = 0;
	Preferences prefs;
	Stage stage;
	Label textPopupRestartPoint;
	Label textPopupRestartTutorial;
	public boolean canRecordAudio = false;
	public boolean appIsRunning = false;



	float widthObstacle = 500;
	float heightObstacle = 700;

	float torque = 0.0f;
	boolean drawSprite = true;

	final float PIXELS_TO_METERS = 100f;


	final short PHYSICS_ENTITY = 0x1;    // 0001
	final short WORLD_ENTITY = 0x1 << 1; // 0010 or 0x2 in hex

    float scaleRatio;

	@Override
	public void create () {
		prefs = Gdx.app.getPreferences("My Preferences");
        batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("carrier_command.xml"),Gdx.files.internal("carrier_command.png"),false);
		fontNormal = new BitmapFont(Gdx.files.internal("normal_font.xml"),Gdx.files.internal("normal_font.png"),false);
		fontNormal.setColor(Color.BLACK);
		font.setColor(0,0,0,1);
        imgPlayer = new Texture("player.png");
        imgObstacle = new Texture("obstacle.jpg");
		imgPlayBtn = new Texture("play-btn.png");

		deviceWidth = Gdx.graphics.getWidth();
		deviceHeight = Gdx.graphics.getHeight();
        gameWidth = 1920;
        gameHeight = 1080;
        scaleRatio = deviceHeight/gameHeight;

//		createPhysic();
//		createPhysic2();
		createPhysic3();
		createObstacle();
//		createWallLeft();
		setContactListener();
		createPopupGameOver();


        float aspectRatio = (float)gameWidth/(float)gameHeight;
        camera = new OrthographicCamera(gameHeight*aspectRatio,gameHeight);


		if(Gdx.app.getType() != Application.ApplicationType.Desktop &&
				canRecordAudio){
			recordAudio();
		}else{
//			recordAudioTest();
		}

//		restart();

		appIsRunning = true;
	}



	public void updateScoreTextUI(){

		font.draw(batch, "Survive in : " + (int)currentScore + " s", camera.position.x - gameWidth/2 + 10, camera.position.y+gameHeight/2-10);
	}

	public void createPopupGameOver(){

//		stage = new Stage();
//		Gdx.input.setInputProcessor(stage);
//		font = new BitmapFont();
//		Skin skin = new Skin();
//		TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
//		textButtonStyle.font = font;
//		textButtonStyle.up = skin.getDrawable("play-btn.png");
//		textButtonStyle.down = skin.getDrawable("play-btn.png");
//		textButtonStyle.checked = skin.getDrawable("play-btn.png");
//		TextButton button = new TextButton("Button1", textButtonStyle);
//		stage.addActor(button);


		stage = new Stage(new ScreenViewport());

		Drawable drawable = new TextureRegionDrawable(new TextureRegion(imgPlayBtn));
		playButton = new ImageButton(drawable);
//        playButton.setPosition(gameWidth/2,gameHeight/2);
        playButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                restart();
                System.out.println("button clicked");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//                restart();
//                System.out.println("button clicked");
                return true;
            }
        });
        stage.addListener(new InputListener(){
            @Override
            public boolean keyUp(InputEvent event, int keycode) {


                if(keycode == Input.Keys.RIGHT) {

//			body.setTransform(2,2,0);
//				body.setLinearVelocity(1f, 0f);
                    move();
                }
                if(keycode == Input.Keys.LEFT)
                    bodyPlayer.setLinearVelocity(-10f,0f);

                if(keycode == Input.Keys.UP)
                    jump();
//			body.applyForceToCenter(0f,10f,true);
                if(keycode == Input.Keys.DOWN)
                    bodyPlayer.applyForceToCenter(0f, -10f, true);

                return true;
            }
        });
        playButton.setPosition(deviceWidth/2 - playButton.getWidth()/2,deviceHeight/4);
		stage.addActor(playButton);
        Gdx.input.setInputProcessor(stage);



        Label.LabelStyle textStyle;
        textStyle = new Label.LabelStyle();
        textStyle.font = font;
        textStyle.fontColor = Color.BLACK;
        textPopupRestartPoint = new Label("",textStyle);
        textPopupRestartPoint.setAlignment(Align.center);
        textPopupRestartPoint.setPosition(deviceWidth/2,deviceHeight/2+deviceHeight/6);
//        text.setBounds(0,.2f,20,2);
        textPopupRestartPoint.setFontScale(2f*scaleRatio,2f*scaleRatio);
        stage.addActor(textPopupRestartPoint);

        textStyle = new Label.LabelStyle();
        textStyle.font = fontNormal;
        textStyle.fontColor = Color.BLACK;
        textPopupRestartTutorial = new Label("",textStyle);
        textPopupRestartTutorial.setAlignment(Align.bottom | Align.center );
        textPopupRestartTutorial.setPosition(deviceWidth/2,0);
//        text.setBounds(0,.2f,20,2);
        textPopupRestartTutorial.setFontScale(0.6f*scaleRatio,0.6f*scaleRatio);
        stage.addActor(textPopupRestartTutorial);


    }

	public void showPopupGameOver(){

		if(!isDead){
			return;
		}

//		stage.draw();

		highScore = prefs.getFloat("highScore",0);
		if(currentScore > highScore){
			highScore = currentScore;
            prefs.putFloat("highScore",highScore);
            prefs.flush();
		}

//		spriteBtnPlay.setPosition(camera.position.x- spriteBtnPlay.getWidth()/2,camera.position.y- spriteBtnPlay.getHeight()/2);

        textPopupRestartPoint.setText("Current survival : " + (int)currentScore  + " s\n\n"+"Best survival : " + (int)currentScore  + " s");
        textPopupRestartTutorial.setText("This is a voice game.\n" +
                "You can control the high note to avoid obstacles through your voice.\n" +
                "With different sound levels, it can move, small jump, or big jump.");
//		font.draw(batch, "Current survival : " + currentScore  + " s",camera.position.x-50, camera.position.y+20+100,200, Align.center,false);
//		font.draw(batch, "Best survival : " + currentScore  + " s",camera.position.x-50, camera.position.y-20+100,200, Align.center,false);
////        fontNormal.draw(batch, "Best survival : " + currentScore  + " s",camera.position.x-50, camera.position.y-20+100,200, Align.center,false);
//		fontNormal.draw(batch, "This is a voice game.\n" +
//				"You can control the high note to avoid obstacles through your voice.\n" +
//				"With different sound levels, it can move, small jump, or big jump.",camera.position.x-100, camera.position.y-200,200, Align.center,false);

        stage.act(); //Perform ui logic
//        playButton.draw(batch,1);
        stage.draw(); //Draw the ui


	}


	public void setObstaclePosition(Body obstacle,float xPosition){
		float yPosition = yPositionOfObstacles+
				MathUtils.random(-heightObstacle/6,heightObstacle/6);

		Sprite spriteObstacle = (Sprite)obstacle.getUserData();
		spriteObstacle.setPosition(xPosition,yPosition);
		obstacle.setTransform((spriteObstacle.getX() + spriteObstacle.getWidth()/2) /
						PIXELS_TO_METERS,
				(spriteObstacle.getY() + spriteObstacle.getHeight()/2) / PIXELS_TO_METERS,0);
	}

	public void restart(){

		if(!isDead){
			return;
		}
		currentScore = 0;
		Sprite spritePlayer = (Sprite)bodyPlayer.getUserData();
		spritePlayer.setPosition(spritePlayer.getWidth()/2 + 40 -gameWidth/2,-spritePlayer.getHeight()/2 -150);
		bodyPlayer.setLinearVelocity(0,0);
		bodyPlayer.applyForceToCenter(0,0,true);
		bodyPlayer.setTransform((spritePlayer.getX() + spritePlayer.getWidth()/2) /
						PIXELS_TO_METERS,
				(spritePlayer.getY() + spritePlayer.getHeight()/2) / PIXELS_TO_METERS,0);
		camera.position.x = -664;

		float firstX = -gameWidth+120;
		yPositionOfObstacles = -gameHeight/2-300;
		for(int i = 0; i < arrayObstacles.length; i++){

			Body bodyObstacle = arrayObstacles[i];
			Sprite spriteObstacle = (Sprite)bodyObstacle.getUserData();


			setObstaclePosition(bodyObstacle,firstX);

			firstX += (widthObstacle + ((Sprite)bodyPlayer.getUserData()).getWidth()*1.5);
		}

		isDead = false;
	}

	public void createPhysic3() {

		// Create two identical sprites slightly offset from each other vertically
		Sprite spritePlayer = new Sprite(imgPlayer);
        spritePlayer.setSize(spritePlayer.getWidth()*1.5f,spritePlayer.getHeight()*1.5f);

		world = new World(new Vector2(0, -4f),true);

		// Both bodies have identical shape
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(spritePlayer.getWidth()/2 / PIXELS_TO_METERS, spritePlayer.getHeight()
				/2 / PIXELS_TO_METERS);

		// Sprite1's Physics body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyPlayer = world.createBody(bodyDef);
		bodyPlayer.setUserData(spritePlayer);

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
		bodyPlayer.createFixture(fixtureDef);


		debugRenderer = new Box2DDebugRenderer();

		shape.dispose();
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
//									MathUtils.random(0,heightObstaclere/2)
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

		int numberOfObstacle = 6;

		arrayObstacles = new Body[numberOfObstacle];
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(widthObstacle/2 / PIXELS_TO_METERS, heightObstacle
				/2 / PIXELS_TO_METERS);
		for(int i = 0; i < numberOfObstacle; i++){



            Sprite spriteObstacle = new Sprite(imgObstacle);
            spriteObstacle.setSize(widthObstacle,heightObstacle);

			Body body2;
			BodyDef bodyDef2 = new BodyDef();
			bodyDef2.type = BodyDef.BodyType.StaticBody;
			body2 = world.createBody(bodyDef2);
			body2.setUserData(spriteObstacle);
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

			arrayObstacles[i] = body2;
			body2.setGravityScale(0);



		}

		shape.dispose();
	}

	public void checkDead(){
		if(isDead){
			return;
		}
		Sprite spritePlayer = (Sprite)bodyPlayer.getUserData();
//		System.out.println(spritePlayer.getY());
		if(spritePlayer.getY() < -800){
			isDead = true;
		}
	}

	public void checkObstacleOutOfScreen(){


		Body obstacle = null;
		float maxXObstaclePosition = 0;
		for(int i = 0; i < arrayObstacles.length; i++){

			Body bodyObstacle = arrayObstacles[i];

			float space = (bodyPlayer.getPosition().x- bodyObstacle.getPosition().x);
//			System.out.println("delta position " + space);
			if(space > 15 &&
					obstacle == null){
				obstacle = bodyObstacle;
			}
			float xPosition = bodyObstacle.getTransform().getPosition().x;
			if(xPosition > maxXObstaclePosition){
				maxXObstaclePosition = xPosition;
			}

		}

		if(obstacle != null) {
			bodyObstacleNeed2Move = obstacle;
			nexXPositionbodyObstacleNeed2Move = maxXObstaclePosition + (widthObstacle + ((Sprite)bodyPlayer.getUserData()).getWidth() * 1.5f) /
					PIXELS_TO_METERS;
		}

	}

	public void setContactListener(){

		world.setContactListener(new ContactListener() {
			@Override
			public void beginContact(Contact contact) {

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
					nexXPositionbodyObstacleNeed2Move = maxXObstaclePosition+ (widthObstacle+((Sprite)bodyPlayer.getUserData()).getWidth()*1.5f)/
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

	public  void adjustAfterJump(){
        float maxY = 400;
        Sprite spritePlayer = (Sprite) bodyPlayer.getUserData();
        if(spritePlayer.getY() > maxY){
            bodyPlayer.setLinearVelocity(0,0);
            spritePlayer.setY(maxY);
            bodyPlayer.setTransform((spritePlayer.getX() + spritePlayer.getWidth()/2) /
                            PIXELS_TO_METERS,
                    (spritePlayer.getY() + spritePlayer.getHeight()/2) / PIXELS_TO_METERS,0);
        }
    }
	public void jump(){
		if(isDead){
			return;
		}
		System.out.println("Jump");
		bodyPlayer.applyForceToCenter(0f,100f,true);
	}

	public void bigJump(){
		if(isDead){
			return;
		}
		System.out.println("big Jump");
		bodyPlayer.applyForceToCenter(0f,300f,true);
	}

	public void move(){
		if(isDead){
			return;
		}


//		System.out.println("Move");
		bodyPlayer.applyForceToCenter(50f,0f,true);
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

	public void requestPermission() {
		String[] perms = {"android.permission. WRITE_EXTERNAL_STORAGE"};

		int permsRequestCode = 200;
	}
	public void recordAudio(){

		int sampleRate = 16000 ; // 44100 for music


		int samples = 44100;
		int seconds = 1;
		boolean isMono = true;
		data = new short[sampleRate]; //0.5s

		playbackDevice = Gdx.audio.newAudioDevice(samples, isMono);
		recordingDevice = Gdx.audio.newAudioRecorder(samples, isMono);

		final int interval = 1000; // 1 Second
		Handler handler = new Handler();
		Runnable runnable = new Runnable(){
			public void run() {
				if(!isDead) {
					recordingDevice.read(data, 0, data.length);
					String result = "";
					int average = 0;
					for (int i = 0; i < data.length; i++) {
						average += data[i];
					}
//									   System.out.println("Voidce " + average);
					//-19 is average
					if (average > 40) {
//										   move();
						bigJump();
					} else if (average > 20) {
						move();
						jump();
					} else if (average > -19000) {
						currentScore += 0.3;
						move();
					}
				}
			}
		};

		handler.postAtTime(runnable, System.currentTimeMillis()+interval);
		handler.postDelayed(runnable, interval);

//		Timer.schedule(new Timer.Task(){
//						   @Override
//						   public void run() {
//							   new Thread(new Runnable() {
//								   @Override
//								   public void run() {
////								   	return;
//									   if(!isDead) {
//										   recordingDevice.read(data, 0, data.length);
//										   String result = "";
//										   int average = 0;
//										   for (int i = 0; i < data.length; i++) {
//											   average += data[i];
//										   }
////									   System.out.println("Voidce " + average);
//										   //-19 is average
//										   if (average > 40) {
////										   move();
//											   bigJump();
//										   } else if (average > 20) {
//											   move();
//											   jump();
//										   } else if (average > -19000) {
//											   currentScore += 0.3;
//											   move();
//										   }
//									   }
////									   System.out.println("Record: End ->" + average*1.0f/data.length);
//								   }
//							   }).start();
//						   }
//					   }
//				, 0.3f        //    (delay)
//				, 0.3f     //    (seconds)
//		);

//		recordingDevice.read(samples, 0, samples.length);
//		playbackDevice.writeSamples(samples, 0, samples.length);
//		recordingDevice.dispose();
//		playbackDevice.dispose();
	}

	public void showGameUI(){

		if(isDead){
			return;
		}
		Sprite spritePlayer = (Sprite)bodyPlayer.getUserData();
		// draw spritePlayer to body position
		batch.draw(
				spritePlayer,
				spritePlayer.getX(), spritePlayer.getY(),
				spritePlayer.getOriginX(), spritePlayer.getOriginY(),
				spritePlayer.getWidth(),spritePlayer.getHeight(),
				spritePlayer.getScaleX(),spritePlayer.getScaleY(),spritePlayer.getRotation());

		for(int i = 0; i < arrayObstacles.length; i++){

			Body bodyObstacle = arrayObstacles[i];
			Sprite spriteObstacle = (Sprite)bodyObstacle.getUserData();
			batch.draw(
					spriteObstacle,
					spriteObstacle.getX(),spriteObstacle.getY(),
					spriteObstacle.getOriginX(),spriteObstacle.getOriginY(),
					spriteObstacle.getWidth(),spriteObstacle.getHeight(),
					spriteObstacle.getScaleX(),spriteObstacle.getScaleY(),
					spriteObstacle.getRotation());


		}

		updateScoreTextUI();
	}



	@Override
	public void render () {
		camera.update();

		checkObstacleOutOfScreen();
		checkDead();
		if(bodyObstacleNeed2Move != null) {

			setObstaclePosition(bodyObstacleNeed2Move,nexXPositionbodyObstacleNeed2Move*PIXELS_TO_METERS);
			bodyObstacleNeed2Move = null;
		}
//		move();
		// Step the physics simulation forward at a rate of 60hz
		if(!isDead) {
			world.step(1f / 60f, 6, 2);
		}
		Sprite spritePlayer = (Sprite)bodyPlayer.getUserData();
		spritePlayer.setPosition((bodyPlayer.getPosition().x * PIXELS_TO_METERS) - spritePlayer.getWidth()/2 ,
				(bodyPlayer.getPosition().y * PIXELS_TO_METERS) -spritePlayer.getHeight()/2 );


		Gdx.gl.glClearColor(1f, 1f, 1f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		/**
		 * set camera position
		 */
		int basePositionX = -864;
		int deltaBetweenCameraAndPlayer = 200;
		if(spritePlayer.getX() >= (basePositionX+deltaBetweenCameraAndPlayer)){
			camera.position.x = spritePlayer.getX();
		}else{
			camera.position.x = (basePositionX+deltaBetweenCameraAndPlayer);
		}
		camera.position.y = -50;//sprite.getY();
//		System.out.println(camera.position.x);

//        stage.getViewport().update((int)camera.viewportWidth,(int)camera.viewportHeight);

		showGameUI();
		showPopupGameOver();

		batch.end();


		// Scale down the sprite batches projection matrix to box2D size
		debugMatrix = batch.getProjectionMatrix().cpy().scale(PIXELS_TO_METERS,
				PIXELS_TO_METERS, 0);
//		debugRenderer.render(world, debugMatrix);

	}
	
	@Override
	public void dispose () {
		// Hey, I actually did some clean up in a code sample!
		imgPlayer.dispose();
        imgObstacle.dispose();
		world.dispose();
		imgPlayBtn.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
	public boolean keyTyped(char character) {
		return false;
	}

	public boolean checkSpriteIsClick(int x, int y, Sprite sprite){

		x -= gameWidth/2;
		y -= gameHeight/2;
		float spriteX = sprite.getX() - camera.position.x + sprite.getWidth()/2;
		float spriteY = sprite.getY() - camera.position.y;

		System.out.print("x " +x + " y " + y);
		System.out.println("xbtn " + spriteX + " ybtn " + spriteY);

		boolean isInButton = (x > spriteX && x < spriteX + sprite.getWidth() && y > spriteY && y < spriteY + sprite.getHeight());
		return isInButton;
	}

	// On touch we apply force from the direction of the users touch.
	// This could result in the object "spinning"
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
//		bodyPlayer.applyForce(1f,1f,screenX/PIXELS_TO_METERS,screenY/PIXELS_TO_METERS,true);
		if(isDead){
//			restart();
//			checkSpriteIsClick(screenX,screenY,spriteBtnPlay);
		}
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
