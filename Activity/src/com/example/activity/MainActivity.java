package com.example.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final EditText name = (EditText)findViewById(R.id.etName);
		final EditText age = (EditText)findViewById(R.id.etAge);
		Button send = (Button)findViewById(R.id.button1);
		
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent newIntent = new Intent(getApplicationContext(), Reciver.class);
				String passedName = name.getText().toString();
				String passedAge = age.getText().toString();
				
				newIntent.putExtra("name", passedName);
				newIntent.putExtra("age", passedAge);
				startActivity(newIntent);
				
				
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
