package com.example.bostonapp;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class ExternalData extends Activity implements OnItemSelectedListener {
	private TextView canWrite, canRead;
	private String state;
	Spinner spinn;
	Boolean canW, canR;
	
	String[] paths = {"Music", "Pictures", "Download"};
	
	File path = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_external_data);

		canRead = (TextView) findViewById(R.id.tvRead);
		canWrite = (TextView) findViewById(R.id.tvWrite);

		state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)) {

			canWrite.setText("true");
			canRead.setText("true");
			canR = canW = true;

		} else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {

			canWrite.setText("false");
			canRead.setText("true");
			canW = false;
			canR = true;

		} else {
			canWrite.setText("false");
			canRead.setText("false");
			canR = canW = false;

		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalData.this, android.R.layout.simple_spinner_item, paths);
		spinn = (Spinner) findViewById(R.id.spinner1);
		spinn.setAdapter(adapter);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.external_data, menu);
		return true;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		int position = spinn.getSelectedItemPosition();
		switch (position) {
		case 0:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;

		case 1:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

			break;

		case 2:
			path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

			break;
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}

}
