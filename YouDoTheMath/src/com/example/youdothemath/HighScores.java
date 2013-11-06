package com.example.youdothemath;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.content.SharedPreferences;
import android.widget.TextView;

public class HighScores extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_high);
		
		TextView scoreView = (TextView)findViewById(R.id.high_scores_list);
		SharedPreferences scorePrefs = getSharedPreferences(PlayGame.GAME_PREFS, 0);
		
		String[] savedScores = scorePrefs.getString("highScores", "").split("\\|");
		
		StringBuilder scoreBuild = new StringBuilder("");
		for(String score : savedScores){
		    scoreBuild.append(score+"\n");
		}
		
		scoreView.setText(scoreBuild.toString());

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.high_score, menu);
		return true;
	}

}
