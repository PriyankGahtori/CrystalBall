package com.example.crystalball;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.crystalball.ShakeDetector.OnShakeListener;

public class MainActivity extends Activity {
	
	private CrystalBall mCrystalBall = new CrystalBall();
	private TextView mdisplayAnswer;
	//private Button mClick;
	private ImageView mCrystalBallImage;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private ShakeDetector mShakeDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         
        
        mdisplayAnswer = (TextView) findViewById(R.id.textView1);
        mSensorManager =  (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer =  mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector(new OnShakeListener() {
			
			@Override
			public void onShake() {
				// TODO Auto-generated method stub
				handleNewAnswer();
								
			}
		});
        
      }
    
    @Override
    public void onResume(){
    	super.onResume();
    	mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    	
    }
    
    @Override
    public void onPause(){
    	super.onPause();
    	mSensorManager.unregisterListener(mShakeDetector);
    	
    }
    
    
      /*  mClick=(Button) findViewById(R.id.button1);
        
        mClick.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				handleNewAnswer();
				
			}
			*/

	
    private void handleNewAnswer() {
		String ans = mCrystalBall.getAnswer();
		
		mdisplayAnswer.setText(ans);
		animateCrystalBall();
		animateAnswer();
		playSound();
	}
		
   
    
    private void animateCrystalBall(){
    	mCrystalBallImage = (ImageView) findViewById(R.id.imageView1);
    	mCrystalBallImage.setImageResource(R.drawable.ball_animation);
    	AnimationDrawable ballAnimation = (AnimationDrawable) mCrystalBallImage.getDrawable();
    	if(ballAnimation.isRunning()){
    		ballAnimation.stop();
    	}
    	ballAnimation.start();
    }
    
    private void animateAnswer()
    {
    	AlphaAnimation fadeInAnimation = new AlphaAnimation(0,1);
    	fadeInAnimation.setDuration(1500);
    	fadeInAnimation.setFillAfter(true);
    	mdisplayAnswer.setAnimation(fadeInAnimation);
    }
    
    private void playSound()
    {
    	MediaPlayer player = MediaPlayer.create(this,R.raw.crystal_ball);
    	player.start();
    	player.setOnCompletionListener(new OnCompletionListener() {
			
			@Override
			public void onCompletion(MediaPlayer mp) {
				// TODO Auto-generated method stub
				mp.release();
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
