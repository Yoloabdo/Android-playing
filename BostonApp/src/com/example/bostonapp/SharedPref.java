package com.example.bostonapp;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPref extends Activity implements OnClickListener{
	EditText sharedData;
	TextView dataResult;
	static String fileName = "mySharedString";
	SharedPreferences someData;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shared_pref);
		intializ();
		
		someData = getSharedPreferences(fileName, 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.shared_pref, menu);
		return true;
	}
	
 private void intializ() {
	 Button save = (Button) findViewById(R.id.bSave);
	 Button load = (Button) findViewById(R.id.BLoad);
	 sharedData = (EditText) findViewById(R.id.etShared);
	 dataResult = (TextView) findViewById(R.id.tvShared);
	 
	 save.setOnClickListener(this);
	 load.setOnClickListener(this);
	 
	 
			 
	 
	}

@SuppressLint("CommitPrefEdits")
@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	switch (v.getId()) {
	case R.id.bSave:
		String stringData = sharedData.getText().toString();
		SharedPreferences.Editor editor = someData.edit();
		editor.putString("ourString", stringData);
		editor.commit();
		break;

	case R.id.BLoad:
		someData = getSharedPreferences(fileName, 0);
		String dataLoaded = someData.getString("ourString", "couldn't laod data");
		dataResult.setText(dataLoaded);
		break;

	default:
		break;
	}
	
}

}
