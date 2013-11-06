package com.example.bostonapp;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class SqlView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sql_view);
		TextView tv = (TextView) findViewById(R.id.tvInfo);
		HotOrNot info = new HotOrNot(this);
		info.open();
		String data = info.getData();
		info.close();
		tv.setText(data);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sql_view, menu);
		return true;
	}

}
