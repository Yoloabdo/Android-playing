package com.example.bostonapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;

public class Tabss extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabs_test);

		TabHost th = (TabHost) findViewById(R.id.tabo1);
		
/*
		TabHost th2 = (TabHost) findViewById(R.id.tab2);
		TabHost th3 = (TabHost) findViewById(R.id.tab3);

		th2.setup();
		th3.setup();

		TabSpec specs2 = th2.newTabSpec("tag2");
		TabSpec specs3 = th3.newTabSpec("tag3");

		specs2.setContent(R.id.tab2);
		specs3.setContent(R.id.tab3);

		specs2.setIndicator("Tab2");
		specs3.setIndicator("Add a tab");

		th2.addTab(specs2);
		th3.addTab(specs3);
*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tabs_test, menu);
		return true;
	}

}
