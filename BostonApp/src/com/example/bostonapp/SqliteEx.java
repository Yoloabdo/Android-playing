package com.example.bostonapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SqliteEx extends Activity implements OnClickListener {
	Button sqlUp, sqlViw;
	EditText name, hotness;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sqlite_ex);

		sqlUp = (Button) findViewById(R.id.bUpdateSql);
		sqlViw = (Button) findViewById(R.id.bSqlView);

		name = (EditText) findViewById(R.id.etName);
		hotness = (EditText) findViewById(R.id.etHotness);

		sqlUp.setOnClickListener(this);
		sqlViw.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sqlite_ex, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		boolean didItWork;
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.bUpdateSql:
			didItWork = true;
			try {
				String sqlname = name.getText().toString();
				String sqlhot = hotness.getText().toString();
				
				HotOrNot entry = new HotOrNot(SqliteEx.this);
				entry.open();
				entry.createEntry(sqlname, sqlhot);
				entry.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				didItWork = false;
				dialogeMessage(e.toString(),"FAIL");
			}finally{
				if (didItWork) {
					dialogeMessage("Succeed", "Heck Ya!");
				}
			}
			break;
		case R.id.bSqlView:
			Intent results = new Intent(SqliteEx.this, SqlView.class);
			startActivity(results);
			break;
		default:
			break;
		}

	}

	private void dialogeMessage(String text, String Title) {
		Dialog d = new Dialog(this);
		d.setTitle(Title);
		TextView tv = new TextView(this);
		tv.setText(text);
		d.setContentView(tv);
		d.show();
	}

}
