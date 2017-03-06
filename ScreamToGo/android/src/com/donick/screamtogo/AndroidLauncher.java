package com.donick.screamtogo;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.donick.screamtogo.ScreamToGo;

public class AndroidLauncher extends AndroidApplication {

	private ScreamToGo listener;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

		listener = new ScreamToGo();
		initialize(listener, config);
		grantPermission();
	}
	public void grantPermission(){
		String[] perms = {"android.permission.RECORD_AUDIO"};

		int permsRequestCode = 200;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
			requestPermissions(perms, permsRequestCode);
		}else{

			listener.canRecordAudio = true;
		}
	}
	@Override
	public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults){

		switch(permsRequestCode){

			case 200:

				boolean writeAccepted = grantResults[0]== PackageManager.PERMISSION_GRANTED;
				if(writeAccepted) {
					if(listener.appIsRunning){
						listener.recordAudio();
					}else {
						listener.canRecordAudio = true;
					}
				}
				break;

		}

	}
}
