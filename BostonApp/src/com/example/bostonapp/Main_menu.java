package com.example.bostonapp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main_menu extends ListActivity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
		
//		//fullScreen
//		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//				WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		
		
		MenuInflater blowup = getMenuInflater();
		blowup.inflate(R.menu.cool_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);
		switch (item.getItemId()) {
		case R.id.aboutUs:
			Class about;
			try {
				about = Class.forName("com.example.bostonapp.AboutUS");
				Intent i = new Intent(Main_menu.this, about);
				startActivity(i);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
			
		case R.id.Prefences:
			try {
				Class prefs = Class.forName("com.example.bostonapp.Prefences");
				Intent i = new Intent(Main_menu.this, prefs);
				startActivity(i);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case R.id.Exit:
			finish();
			break;
		}
		
		return false;
	}
	

	String classes[] = { "StartingPoint", "TextPlay", "Email", "Camera",
			"HttpExample", "Tabss" , "Flibber", "SharedPref", "InternalData", "ExternalData", "ListGen"
			,"SqliteEx", "XmlParser"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(Main_menu.this,
				android.R.layout.simple_list_item_1, classes));

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		String cheeze = classes[position];

		try {
			Class ourClass = Class.forName("com.example.bostonapp." + cheeze);
			Intent ourIntent = new Intent(Main_menu.this, ourClass);
			startActivity(ourIntent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
