package com.example.httpproject;

import com.example.bostonapp.Splash;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
