package com.example.bostonapp;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity {
	MediaPlayer ourSong;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		ourSong = MediaPlayer.create(Splash.this, R.raw.splashsound);
		SharedPreferences getprPrefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		boolean music = getprPrefs.getBoolean("checkBox", true);
		if (music) {
			ourSong.start();

		}

		Thread timer = new Thread() {

			@Override
			public void run() {
				try {
					SharedPreferences gettime = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
					String time = gettime.getString("splash", "5000");
					int timeS = Integer.parseInt(time);
					sleep(timeS);
				} catch (InterruptedException e) {
					// TODO: handle exception
					e.printStackTrace();
				} finally {
					try {
						Class ourClass = Class.forName("com.example.bostonapp.Main_menu");
						Intent startPoint = new Intent(Splash.this, ourClass);
						startActivity(startPoint);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					

				}
			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSong.stop();
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		finish();
	}

}
