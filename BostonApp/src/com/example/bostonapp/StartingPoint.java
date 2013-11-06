package com.example.bostonapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class StartingPoint extends Activity implements OnClickListener {
	int counter;
	Button addOne, addTen, substract;
	TextView display;
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		counter = 0;
		addOne = (Button) findViewById(R.id.bAdd);
		addTen = (Button) findViewById(R.id.bAddTen);
		substract = (Button) findViewById(R.id.Bsub);
		display = (TextView) findViewById(R.id.tvDisplay);

		// intialize view
		display.setText("Your total is: " + counter);

		// click methods
		addOne.setOnClickListener(this);
		addTen.setOnClickListener(this);
		substract.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch (v.getId()) {
		case R.id.bAdd:
			counter++;
			display.setText("Your total is: " + counter);
			break;
		case R.id.bAddTen:
			counter += 10;
			display.setText("Your total is: " + counter);
			break;
		case R.id.Bsub:
			counter--;
			display.setText("Your total is: " + counter);
			break;
		default:
			break;
		}

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);

	}

}
