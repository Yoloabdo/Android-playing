package com.example.bostonapp;

import android.os.Bundle;
import android.app.Activity;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_text);
		
		Button chkCmd = (Button) findViewById(R.id.bCommand);
		final ToggleButton tButton = (ToggleButton) findViewById(R.id.tbPassword);
		final EditText input = (EditText) findViewById(R.id.etCommands);
		final TextView display = (TextView) findViewById(R.id.etTV);
		
		tButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (tButton.isChecked()) {
					input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
				}
				else{
					input.setInputType(InputType.TYPE_CLASS_TEXT);

				}
			}
		});
		
		chkCmd.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String check = input.getText().toString();
				if (check.contentEquals("left")) {
					display.setGravity(Gravity.LEFT);
				} else if (check.contentEquals("right")){
					display.setGravity(Gravity.RIGHT);
				} else if (check.contentEquals("center")){
					display.setGravity(Gravity.CENTER);

				}
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.text, menu);
		return true;
	}

}
