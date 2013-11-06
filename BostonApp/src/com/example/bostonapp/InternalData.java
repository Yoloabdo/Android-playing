package com.example.bostonapp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InternalData extends Activity implements OnClickListener {
	EditText sharedData;
	TextView dataResult;
	FileOutputStream fos;
	String fileNAme = "InternalString";
	String collected;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_shared_pref);
		intializ();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.internal_data, menu);
		return true;
	}

	private void intializ() {
		Button save = (Button) findViewById(R.id.bSave);
		Button load = (Button) findViewById(R.id.BLoad);
		sharedData = (EditText) findViewById(R.id.etShared);
		dataResult = (TextView) findViewById(R.id.tvShared);

		save.setOnClickListener(this);
		load.setOnClickListener(this);

		try {
			fos = openFileOutput(fileNAme, Context.MODE_PRIVATE);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressLint("CommitPrefEdits")
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {

		case R.id.bSave:
			String data = sharedData.getText().toString();
			/*
			 * // Saving data via file File f = new File(fileNAme);
			 * 
			 * try { fos = new FileOutputStream(f); fos.close();
			 * 
			 * } catch (IOException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); }
			 */

			try {
				fos = openFileOutput(fileNAme, Context.MODE_PRIVATE);
				fos.write(data.getBytes());
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		case R.id.BLoad:
			new loadSomeStuff().execute(fileNAme);
		}
	}
	public class loadSomeStuff extends AsyncTask<String, Integer, String>{
		ProgressDialog dialog;
		protected void onPreExcute(String f){
			//Example of setting variables 
			dialog = new ProgressDialog(InternalData.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMax(100);
			dialog.show();
		}
		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
		collected = null;
		FileInputStream fis = null;
		
		for (int i = 0; i < 20; i++) {
			publishProgress(5);
			
			try {
				Thread.sleep(88);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		dialog.dismiss();

		try {
			fis = openFileInput(fileNAme);
			byte[] dataArray = new byte[fis.available()];
			while (fis.read(dataArray) != -1) {
				collected = new String(dataArray);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fis.close();
				return collected;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

			return null;
		}
		
		protected void onProgressUpdate(Integer ... progres) {
			dialog.incrementProgressBy(progres[0]);
		}
		
		protected void onPostExcute(String result){
			dataResult.setText(result);
		}
	}

}
