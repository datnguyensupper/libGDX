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
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Queue;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


import javax.sound.sampled.AudioFormat;
import java.util.LinkedList;
import java.util.jar.Manifest;

public class ScreamToGo extends ApplicationAdapter implements InputProcessor {

	short[] data;
	AudioRecorder recordingDevice = null;
//	AudioDevice playbackDevice;
	SpriteBatch batch;
	Texture imgPlayer,imgObstacle,imgPlayBtn,imgTestBtn, imgNormalEye, imgSadEye;
	ImageButton playButton;
	Group player;
	Image normalEyes;
	Image sadEyes;
	World world;
	Body bodyPlayer;
	Body bodyWallLeft;
	Body bodyObstacleNeed2Move = null;
	float nexXPositionbodyObstacleNeed2Move;
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
	Label textPopupRestartPoint;
	Label textPopupRestartTutorial;
	public boolean canRecordAudio = false;
	public boolean appIsRunning = false;

	Stage stageGameOver;
	Stage stageMainGame;

	boolean isDebug = false;
//	boolean isDebug = true;
	int debugModeCount = 0;


	float maxTimerForRecording = 0.5f;
	float currentCountTimerForRecording = 0f;

	Device deviceType = Device.ASUS;

	float widthObstacle = 500;
	float heightObstacle = 700;

	float torque = 0.0f;
	boolean drawSprite = true;

	final float PIXELS_TO_METERS = 100f;


	final short PHYSICS_ENTITY = 0x1;    // 0001
	final short WORLD_ENTITY = 0x1 << 1; // 0010 or 0x2 in hex

    float scaleRatio;
	private Array<Float> arrayOfVoice1;
	private Array<Float> arrayOfVoice2;

	float minDeltaVoice;
	float maxDeltaVoice;
	float maxRecorder = 0;
	float minRecorder = 0;
	float currentVoice = 0;

	public enum Device {
		A7,
		ASUS
	}
	@Override
	public void create () {
		prefs = Gdx.app.getPreferences("My Preferences");
        batch = new SpriteBatch();
		font = new BitmapFont(Gdx.files.internal("carrier_command.xml"),Gdx.files.internal("carrier_command.png"),false);
		fontNormal = new BitmapFont(Gdx.files.internal("normal_font.xml"),Gdx.files.internal("normal_font.png"),false);
		fontNormal.setColor(Color.BLACK);
		font.setColor(0,0,0,1);
        imgPlayer = new Texture("player.png");
		imgNormalEye = new Texture("normal_eyes.png");
		imgSadEye = new Texture("sad_eyes.png");
        imgObstacle = new Texture("obstacle.jpg");
		imgPlayBtn = new Texture("play-btn.png");
		imgTestBtn = new Texture("testBtn.jpg");
		arrayOfVoice1 = new Array<Float>();
		arrayOfVoice2 = new Array<Float>();

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
		createMainGameUI();
		createPopupGameOver();





		if(Gdx.app.getType() != Application.ApplicationType.Desktop &&
				canRecordAudio){
			createRecordAudio();
		}else{
//			recordAudioTest();
		}

//		restart();

		appIsRunning = true;
	}



	public void updateScoreTextUI(){

		if(!isDebug) {
			font.draw(batch, "Survive in : " + (int) currentScore + " s", camera.position.x - gameWidth / 2 + 10, camera.position.y + gameHeight / 2 - 10);
		}else{
			font.draw(batch, "current voice " + currentVoice, camera.position.x - gameWidth / 2 + 10, camera.position.y + gameHeight / 2 - 10);
		}
	}

	public void createMainGameUI(){
		float aspectRatio = (float)gameWidth/(float)gameHeight;
		camera = new OrthographicCamera(gameHeight*aspectRatio,gameHeight);

		stageMainGame = new Stage(new StretchViewport(gameHeight*aspectRatio,gameHeight));


		player = new Group();

		normalEyes = new Image(imgNormalEye);
		normalEyes.setPosition(25,90);
		player.addActor(normalEyes);

		sadEyes = new Image(imgSadEye);
		sadEyes.setPosition(25,90);
		player.addActor(sadEyes);
		sadEyes.setVisible(false);



		stageMainGame.addActor(player);
	}

	public void createPopupGameOver(){


		stageGameOver = new Stage(new ScreenViewport());

		Drawable drawable = new TextureRegionDrawable(new TextureRegion(imgPlayBtn));
		playButton = new ImageButton(drawable);
		playButton.setPosition(deviceWidth/2 - playButton.getWidth()/2,deviceHeight/4);
        playButton.addListener(new InputListener(){
            @Override
            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                restart();
                System.out.println("button clicked");
            }
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
		stageGameOver.addActor(playButton);

		ImageButton testBtn = new ImageButton(new TextureRegionDrawable(new TextureRegion(imgTestBtn)));
		testBtn.setPosition(deviceWidth - testBtn.getWidth(),deviceHeight-testBtn.getHeight());
		testBtn.addListener(new InputListener(){
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
				debugModeCount++;
				if(debugModeCount >= 10){
					isDebug = true;
				}
			}
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}
		});
		stageGameOver.addActor(testBtn);



		stageGameOver.addListener(new InputListener(){
            @Override
            public boolean keyUp(InputEvent event, int keycode) {
            	if(Gdx.app.getType() != Application.ApplicationType.Desktop ){
            		return false;
				}
                if(keycode == Input.Keys.RIGHT) {
                    move();
                }
                if(keycode == Input.Keys.LEFT)
                    bodyPlayer.setLinearVelocity(-10f,0f);
                if(keycode == Input.Keys.UP)
                    jump();
                if(keycode == Input.Keys.DOWN)
                    bodyPlayer.applyForceToCenter(0f, -10f, true);

                return false;
            }
        });

        Gdx.input.setInputProcessor(stageGameOver);



        Label.LabelStyle textStyle;
        textStyle = new Label.LabelStyle();
        textStyle.font = font;
        textStyle.fontColor = Color.BLACK;
        textPopupRestartPoint = new Label("",textStyle);
        textPopupRestartPoint.setAlignment(Align.center);
        textPopupRestartPoint.setPosition(deviceWidth/2,deviceHeight/2+deviceHeight/6);
//        text.setBounds(0,.2f,20,2);
        textPopupRestartPoint.setFontScale(2f*scaleRatio,2f*scaleRatio);
		stageGameOver.addActor(textPopupRestartPoint);

        textStyle = new Label.LabelStyle();
        textStyle.font = fontNormal;
        textStyle.fontColor = Color.BLACK;
        textPopupRestartTutorial = new Label("",textStyle);
        textPopupRestartTutorial.setAlignment(Align.bottom | Align.center );
        textPopupRestartTutorial.setPosition(deviceWidth/2,0);
//        text.setBounds(0,.2f,20,2);
        textPopupRestartTutorial.setFontScale(0.6f*scaleRatio,0.6f*scaleRatio);
		stageGameOver.addActor(textPopupRestartTutorial);


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

		stageGameOver.act(); //Perform ui logic
		stageGameOver.draw(); //Draw the ui


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
		createPlayerBody();

		currentScore = 0;
		Sprite spritePlayer = (Sprite)bodyPlayer.getUserData();
		spritePlayer.setPosition(spritePlayer.getWidth()/2 + 40 -gameWidth/2,-spritePlayer.getHeight()/2 -150);
		player.setPosition(gameWidth/2 + spritePlayer.getX()-camera.position.x,spritePlayer.getY()+gameHeight/2-camera.position.y);


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

		arrayOfVoice1.clear();
		arrayOfVoice2.clear();
		minDeltaVoice = 0;
		maxDeltaVoice = 0;
		isDead = false;
	}

	public void createPlayerBody(){

		Sprite spritePlayer;
		if(bodyPlayer != null){
			spritePlayer = (Sprite)bodyPlayer.getUserData();
			world.destroyBody(bodyPlayer);

		}else{
			spritePlayer = new Sprite(imgPlayer);
			spritePlayer.setSize(spritePlayer.getWidth()*1.5f,spritePlayer.getHeight()*1.5f);
		}


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

		shape.dispose();

	}

	public void createPhysic3() {

		world = new World(new Vector2(0, -4f),true);

		createPlayerBody();

		debugRenderer = new Box2DDebugRenderer();
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
			debugModeCount = 0;
			isDebug = false;
		}else if(spritePlayer.getY()< -400 ||
				bodyPlayer.getLinearVelocity().y<-2){
			sadEyes.setVisible(true);
			normalEyes.setVisible(false);
		}else {
			sadEyes.setVisible(false);
			normalEyes.setVisible(true);
		}
	}

	public void checkObstacleOutOfScreen(){

		if(bodyPlayer == null){
			return;
		}

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
		if(isDead || isDebug){
			return;
		}
		System.out.println("Jump");
		bodyPlayer.applyForceToCenter(0f,100f,true);
	}

	public void bigJump(){
		if(isDead || isDebug){
			return;
		}
		System.out.println("big Jump");
		bodyPlayer.applyForceToCenter(0f,200f,true);
	}

	public void move(){
		if(isDead || isDebug){
			return;
		}

		System.out.println("move");

//		System.out.println("Move");

		currentScore += 0.3;
		bodyPlayer.applyForceToCenter(50f,0f,true);

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

	public float adjustValue(float voice){
		if(arrayOfVoice1.size < 3) {
			arrayOfVoice1.add(new Float(voice));
		}else{
			arrayOfVoice1.removeIndex(0);
			arrayOfVoice1.add(new Float(voice));
		}
		float min = arrayOfVoice1.get(0).floatValue();
		float max = arrayOfVoice1.get(0).floatValue();
		for(Float v: arrayOfVoice1) {
			float vF = v.floatValue();
			if(vF < min){
				min = vF;
			}else if(vF > max){
				max = vF;
			}
		}

		float delta = (max - min);
		if(arrayOfVoice2.size < 3) {
			arrayOfVoice2.add(new Float(delta));
		}else{
			arrayOfVoice2.removeIndex(0);
			arrayOfVoice2.add(new Float(delta));
		}
		minDeltaVoice = arrayOfVoice2.get(0).floatValue();
		maxDeltaVoice = arrayOfVoice2.get(0).floatValue();
		for(Float v: arrayOfVoice2) {
			float vF = v.floatValue();
			if(vF < minDeltaVoice){
				minDeltaVoice = vF;
			}else if(vF > maxDeltaVoice){
				maxDeltaVoice = vF;
			}
		}
		return delta;
	}

	public float adjustValue2(float voice){
		if(arrayOfVoice1.size < 5) {
			arrayOfVoice1.add(new Float(voice));
		}else{
			arrayOfVoice1.removeIndex(0);
			arrayOfVoice1.add(new Float(voice));
		}
		minDeltaVoice = arrayOfVoice1.get(0).floatValue();
		maxDeltaVoice = arrayOfVoice1.get(0).floatValue();
		for(Float v: arrayOfVoice1) {
			float vF = v.floatValue();
			if(vF < minDeltaVoice){
				minDeltaVoice = vF;
			}else if(vF > maxDeltaVoice){
				maxDeltaVoice = vF;
			}
		}
		return voice;
	}

	public float adjustValue3(float voice){
		if(arrayOfVoice1.size < 5) {
			arrayOfVoice1.add(new Float(voice));
		}else{
			arrayOfVoice1.removeIndex(0);
			arrayOfVoice1.add(new Float(voice));
		}
		minDeltaVoice = arrayOfVoice1.get(0).floatValue();
		maxDeltaVoice = arrayOfVoice1.get(0).floatValue();
		for(Float v: arrayOfVoice1) {
			float vF = v.floatValue();
			if(vF < minDeltaVoice){
				minDeltaVoice = vF;
			}else if(vF > maxDeltaVoice){
				maxDeltaVoice = vF;
			}
		}

		if(minDeltaVoice < -16){
			deviceType = Device.A7;
		}else{
			deviceType = Device.ASUS;
		}
		return voice;
	}

	public boolean isMin(float voice){
		if (deviceType == Device.A7){
			return (voice < -20);
		}
		return (voice >= -1);
	}

	public boolean isMiddle(float voice){
		if (deviceType == Device.A7){
			return (voice < 0 && voice > -12);
		}
		return (voice > 0 || voice < -5);
	}

	public boolean isMax(float voice){
		if (deviceType == Device.A7){
			return (voice > 0 || voice < -30);
		}
		return (voice > 5 || voice < -10);
	}

	public void recordAudio(){

		if(!isDead && recordingDevice != null) {
			recordingDevice.read(data, 0, data.length);
			String result = "";
			int average = 0;
			int numberOfDevide = 0;
			for (int i = 0; i < data.length; i++) {
				average += data[i];
				if(data[i] != 0){
					numberOfDevide++;
				}
			}
			if(numberOfDevide != 0) {
				average /= numberOfDevide;
			}


			currentVoice = adjustValue3(average);
			float min = minRecorder;
			float middle = (maxRecorder+minRecorder)/2;
			float max = maxRecorder;
			System.out.println("delta "  + currentVoice);



			//-19 is average
			if (isMax(currentVoice)) {
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						bigJump();
					}
				});
			} else if (isMiddle(currentVoice)) {
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						move();
						jump();
					}
				});
			} else if (isMin(currentVoice)) {
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {
						move();
					}
				});
			}
		}
	}
	public void createRecordAudio(){

		int sampleRate = 16000 ; // 44100 for music


		int samples = 44100;
		int seconds = 1;
		boolean isMono = true;
		data = new short[sampleRate]; //0.5s

//		playbackDevice = Gdx.audio.newAudioDevice(samples, isMono);
		recordingDevice = Gdx.audio.newAudioRecorder(samples, isMono);
	}

	public void showGameUI(){

		if(isDead){
			return;
		}
		Sprite spritePlayer = (Sprite)bodyPlayer.getUserData();

		spritePlayer.setPosition((bodyPlayer.getPosition().x * PIXELS_TO_METERS) - spritePlayer.getWidth()/2 ,
				(bodyPlayer.getPosition().y * PIXELS_TO_METERS) -spritePlayer.getHeight()/2 );
		player.setPosition(gameWidth/2 + spritePlayer.getX()-camera.position.x,spritePlayer.getY()+gameHeight/2-camera.position.y);

		// draw spritePlayer to body position
		spritePlayer.draw(batch);

		for(int i = 0; i < arrayObstacles.length; i++){

			Body bodyObstacle = arrayObstacles[i];
			Sprite spriteObstacle = (Sprite)bodyObstacle.getUserData();
			spriteObstacle.draw(batch);
		}




		updateScoreTextUI();
	}



	@Override
	public void render () {

		float deltaTime = Gdx.graphics.getDeltaTime();
		if(currentCountTimerForRecording >= maxTimerForRecording){

			minDeltaVoice = 0;
			new Thread(new Runnable() {
				@Override
				public void run() {
					recordAudio();
				}
			}).start();
			currentCountTimerForRecording = 0;
		}else{
			currentCountTimerForRecording+=deltaTime;
		}
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


		Gdx.gl.glClearColor(1f, 1f, 1f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(camera.combined);
		batch.begin();


		showGameUI();
		showPopupGameOver();


		/**
		 * set camera position
		 */
		Sprite spritePlayer = (Sprite)bodyPlayer.getUserData();
		int basePositionX = -864;
		int deltaBetweenCameraAndPlayer = 200;
		if(spritePlayer.getX() >= (basePositionX+deltaBetweenCameraAndPlayer)){
			camera.position.x = spritePlayer.getX();
		}else{
			camera.position.x = (basePositionX+deltaBetweenCameraAndPlayer);
		}
		camera.position.y = -50;


		batch.end();

		if(!isDead) {
			stageMainGame.act(); //Perform ui logic
			stageMainGame.draw(); //Draw the ui
		}

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
		imgTestBtn.dispose();
		recordingDevice.dispose();
		imgNormalEye.dispose();
		imgSadEye.dispose();
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
