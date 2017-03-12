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
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
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
import java.beans.FeatureDescriptor;
import java.util.LinkedList;
import java.util.jar.Manifest;

public class ScreamToGo extends ApplicationAdapter implements InputProcessor {

	short[] data;
	AudioRecorder recordingDevice = null;
//	AudioDevice playbackDevice;
	SpriteBatch batch;
	Texture imgPlayer,imgObstacle,imgPlayBtn,imgTestBtn, imgNormalEye, imgSadEye, imgWing, imgLeg, imgPlayerDead,imgHowToPlay;
	ImageButton playButton;
	Group player;
	Image playerImage,normalEyes, sadEyes, wing, leg1, leg2;
	int wingDirection = 1;
	int legDirection = 1;
	World world;
	Body bodyPlayer;
	Body bodyEnemy;
	FixtureDef fixturePlayer;
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
	float startSpritePlayerPosition = 0;
	float currentScore = 0;
	float highScore = 0;
	Preferences prefs;
	Label textPopupRestartPoint;
	Label textPopupRestartTutorial;
	public boolean canRecordAudio = false;
	public boolean appIsRunning = false;

	Stage stageGameOver;
	Stage stageMainGame;

	EnemyController enemyController;

	boolean isDebug = false;
//	boolean isDebug = true;
	int debugModeCount = 0;


	float maxTimerForRecording = 0.5f;
	float currentCountTimerForRecording = 0f;
	float gapBetween2Obtacles;

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
		ASUS,
		Oppo
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
		imgPlayerDead = new Texture("player_dead.png");
		imgNormalEye = new Texture("normal_eyes.png");
		imgSadEye = new Texture("sad_eyes.png");
		imgWing = new Texture("wing_note.png");
		imgLeg = new Texture("leg.png");
        imgObstacle = new Texture("obstacle.jpg");
		imgPlayBtn = new Texture("play-btn.png");
		imgTestBtn = new Texture("testBtn.jpg");
		imgHowToPlay = new Texture("howtoplay.jpg");
		arrayOfVoice1 = new Array<Float>();
		arrayOfVoice2 = new Array<Float>();

		deviceWidth = Gdx.graphics.getWidth();
		deviceHeight = Gdx.graphics.getHeight();
        gameWidth = 1920;
        gameHeight = 1080;
        scaleRatio = deviceHeight/gameHeight;

		enemyController = new EnemyController();

		gapBetween2Obtacles = imgPlayer.getWidth()*1.5f*1.3f;
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
			font.draw(batch, "Survive in : " + (int) currentScore + " m", camera.position.x - gameWidth / 2 + 10, camera.position.y + gameHeight / 2 - 10);
		}else{
			font.draw(batch, "current voice " + currentVoice, camera.position.x - gameWidth / 2 + 10, camera.position.y + gameHeight / 2 - 10);
		}
	}

	public void createMainGameUI(){
		float aspectRatio = (float)gameWidth/(float)gameHeight;
		camera = new OrthographicCamera(gameHeight*aspectRatio,gameHeight);

		stageMainGame = new Stage(new StretchViewport(gameHeight*aspectRatio,gameHeight));


		player = new Group();

		playerImage = new Image(imgPlayer);
		playerImage.setScale(1.5f);
		player.addActor(playerImage);

		normalEyes = new Image(imgNormalEye);
		normalEyes.setPosition(25,90);
		player.addActor(normalEyes);

		sadEyes = new Image(imgSadEye);
		sadEyes.setPosition(25,90);
		player.addActor(sadEyes);
		sadEyes.setVisible(false);

		wing = new Image(imgWing);
		wing.setScaleY(0.7f);
		wing.setOrigin(imgWing.getWidth()*2/3.0f,imgWing.getHeight()-20);
		wing.setPosition(0,20);
		player.addActor(wing);

		leg2 = new Image(imgLeg);
		leg2.setOrigin(imgLeg.getWidth()/2,imgLeg.getHeight());
		leg2.setPosition(80,-18);
		leg2.setRotation(5);
		player.addActor(leg2);

		leg1 = new Image(imgLeg);
		leg1.setOrigin(imgLeg.getWidth()/2,imgLeg.getHeight());
		leg1.setPosition(20,-18);
		leg1.setRotation(-5);
		player.addActorAt(0,leg1);


		stageMainGame.addActor(player);
	}

	public void createPopupGameOver(){


		stageGameOver = new Stage(new ScreenViewport());

		Drawable drawable = new TextureRegionDrawable(new TextureRegion(imgPlayBtn));
		playButton = new ImageButton(drawable);
		playButton.setPosition(deviceWidth/2 - playButton.getWidth()/2,deviceHeight/2);
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
        textPopupRestartPoint.setAlignment(Align.center|Align.top);
        textPopupRestartPoint.setPosition(deviceWidth/2,deviceHeight-100);
//        text.setBounds(0,.2f,20,2);
        textPopupRestartPoint.setFontScale(2f*scaleRatio,2f*scaleRatio);
		stageGameOver.addActor(textPopupRestartPoint);

        textStyle = new Label.LabelStyle();
        textStyle.font = fontNormal;
        textStyle.fontColor = Color.BLACK;
        textPopupRestartTutorial = new Label("",textStyle);
        textPopupRestartTutorial.setAlignment(Align.center|Align.right);
        textPopupRestartTutorial.setPosition(deviceWidth/2,deviceHeight/4);
//        text.setBounds(0,.2f,20,2);
        textPopupRestartTutorial.setFontScale(0.6f*scaleRatio,0.6f*scaleRatio);

		Image howToPlay = new Image(imgHowToPlay);
		howToPlay.setPosition(deviceWidth/2 + 50,deviceHeight/4 - howToPlay.getHeight()/2.0f);
		stageGameOver.addActor(howToPlay);

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

        textPopupRestartPoint.setText("Current survival : " + (int)currentScore  + " m\n\n"+"Best survival : " + (int)highScore  + " m");
        textPopupRestartTutorial.setText(
        		"This is a voice game. You can control the high \n" +
                "note to avoid obstacles through your voice.\n" +
                "With different sound levels,\n"+
				" it can move, small jump, or big jump.");

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

		playerImage.setDrawable(new SpriteDrawable(new Sprite(imgPlayer)));

		currentScore = 0;
		Sprite spritePlayer = (Sprite)bodyPlayer.getUserData();
		spritePlayer.setPosition(spritePlayer.getWidth()/2 + 40 -gameWidth/2,-spritePlayer.getHeight()/2 -150);
		startSpritePlayerPosition = spritePlayer.getX();
		player.setPosition(gameWidth/2 + spritePlayer.getX()-camera.position.x,spritePlayer.getY()+gameHeight/2-camera.position.y);

//		createEnemy(spritePlayer.getX());
		destroyEnemy();

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
			setObstaclePosition(bodyObstacle,firstX);
			firstX += (widthObstacle + gapBetween2Obtacles);
		}

		arrayOfVoice1.clear();
		arrayOfVoice2.clear();
		minDeltaVoice = 0;
		maxDeltaVoice = 0;
		isDead = false;
	}

	public void destroyEnemy(){
		if(bodyEnemy != null) {
			world.destroyBody(bodyEnemy);
			bodyEnemy = null;
		}
	}

	public void createEnemy(float x){

		if(currentScore < 20){
			return;
		}
		if(bodyEnemy != null){
			float space = (bodyPlayer.getPosition().x- bodyEnemy.getPosition().x);
//			System.out.println("delta position " + space);
			if(space > 15) {
				destroyEnemy();
			}else{
				return;
			}

		}

		bodyEnemy = enemyController.createRandomEnemy(world,x,gameHeight);

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
		shape.setAsBox(spritePlayer.getWidth()/2 / PIXELS_TO_METERS,
				(spritePlayer.getHeight()
				+10)/2 / PIXELS_TO_METERS);

		// Sprite1's Physics body
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyDef.BodyType.DynamicBody;
		bodyPlayer = world.createBody(bodyDef);
		bodyPlayer.setUserData(spritePlayer);

		// Sprite1
		fixturePlayer = new FixtureDef();
		fixturePlayer.shape = shape;
//		fixtureDef.density = 0.1f;
//		fixtureDef.restitution = 0.5f;
		fixturePlayer.density = 0f;
		fixturePlayer.restitution = 0f;
//		fixturePlayer.filter.categoryBits = PHYSICS_ENTITY;
//		fixturePlayer.filter.maskBits = WORLD_ENTITY;
//		fixtureDef.isSensor = true;
		bodyPlayer.createFixture(fixturePlayer);

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
			nexXPositionbodyObstacleNeed2Move = maxXObstaclePosition + (widthObstacle/2 + gapBetween2Obtacles) /
					PIXELS_TO_METERS;
		}

	}

	public void setContactListener(){

		world.setContactListener(new ContactListener() {
			@Override
			public void beginContact(Contact contact) {


				if((contact.getFixtureA().getBody() == bodyPlayer &&
						contact.getFixtureB().getBody() == bodyEnemy) ||
				(contact.getFixtureB().getBody() == bodyPlayer &&
						contact.getFixtureA().getBody() == bodyEnemy)){
					// falling down forever
					Filter boxBreakFilter = new Filter();
					boxBreakFilter.categoryBits = 1;
					boxBreakFilter.groupIndex = 2;
					boxBreakFilter.maskBits = (short)0;
					bodyPlayer.getFixtureList().get(0).setFilterData(boxBreakFilter);


					playerImage.setDrawable(new SpriteDrawable(new Sprite(imgPlayerDead)));
//					playerImage.setColor(Color.BLUE);
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

//		currentScore += 0.3;
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
		}else if(minDeltaVoice <= -4 ){
			deviceType = Device.Oppo;
		}
		return voice;
	}

	public boolean isMin(float voice){
		if (deviceType == Device.A7){
			return (voice < -20);
		}else if(deviceType == Device.Oppo){
			return (voice >= 0 || voice < -10 );
		}
		return (voice >= -1);
	}

	public boolean isMiddle(float voice){
		if (deviceType == Device.A7){
			return (voice < 0 && voice > -12);
		}else if(deviceType == Device.Oppo){
			return (voice < -40);
		}
		return (voice > 0 || voice < -5);
	}

	public boolean isMax(float voice){
		if (deviceType == Device.A7){
			return (voice > 0 || voice < -30);
		}else if(deviceType == Device.Oppo){
			return (voice < -150 || voice >= 2);
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


			currentVoice = adjustValue(average);
			System.out.println("delta "  + currentVoice + " device " + deviceType);



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
				(bodyPlayer.getPosition().y * PIXELS_TO_METERS+7) -spritePlayer.getHeight()/2 );
		player.setPosition(gameWidth/2 + spritePlayer.getX()-camera.position.x,spritePlayer.getY()+gameHeight/2-camera.position.y);

		// draw spritePlayer to body position
//		spritePlayer.draw(batch);

		for(int i = 0; i < arrayObstacles.length; i++){
			Body bodyObstacle = arrayObstacles[i];
			Sprite spriteObstacle = (Sprite)bodyObstacle.getUserData();
			spriteObstacle.draw(batch);
		}

		if(bodyEnemy != null){
			Sprite spriteEnemy = (Sprite)bodyEnemy.getUserData();
			spriteEnemy.setPosition(
					bodyEnemy.getPosition().x*PIXELS_TO_METERS-spriteEnemy.getWidth()/2,
					bodyEnemy.getPosition().y*PIXELS_TO_METERS-spriteEnemy.getHeight()/2);
			spriteEnemy.draw(batch);
		}


		if(bodyPlayer.getLinearVelocity().y > 0){
			wing.setRotation(wing.getRotation()+2*wingDirection);
			if(wing.getRotation() > 20 || wing.getRotation() < -20){
				wingDirection = (-wingDirection);
			}
			leg1.setRotation(0);
			leg2.setRotation(0);
		}else if(bodyPlayer.getLinearVelocity().y == 0) {
			if (bodyPlayer.getLinearVelocity().x > 0) {
				leg1.setRotation(leg1.getRotation() + 1 * legDirection);
				leg2.setRotation(leg2.getRotation() - 1 * legDirection);
				if (leg1.getRotation() > 20 || leg1.getRotation() < -20) {
					legDirection = -legDirection;
				}
			}
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
			createEnemy(nexXPositionbodyObstacleNeed2Move*PIXELS_TO_METERS);
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

		currentScore = (spritePlayer.getX()-startSpritePlayerPosition)/100.0f;

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
		imgPlayerDead.dispose();
        imgObstacle.dispose();
		world.dispose();
		imgPlayBtn.dispose();
		imgTestBtn.dispose();
		recordingDevice.dispose();
		imgNormalEye.dispose();
		imgSadEye.dispose();
		imgWing.dispose();
		imgLeg.dispose();
		imgHowToPlay.dispose();

		enemyController.dispose();
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
