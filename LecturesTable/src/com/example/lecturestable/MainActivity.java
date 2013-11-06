package com.example.lecturestable;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends android.support.v4.app.FragmentActivity {
	private String[] mPlanetTitles;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private CharSequence mTitle;
	private CharSequence mDrawerTitle;
	private ActionBarDrawerToggle mDrawerToggle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mPlanetTitles = getResources().getStringArray(R.array.days_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// Set the adapter for the list view
		mDrawerList.setAdapter(new ArrayAdapter<T>(this,
				R.layout.drawer_list_item, mPlanetTitles));

		mDrawerList.setOnItemClickListener(new DrawerItemClickListner());

		mTitle = mDrawerTitle = getTitle();
		mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
                ) {

			/** Called when a drawer has settled in a completely closed state. */
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			/** Called when a drawer has settled in a completely open state. */
			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};

		// Set the drawer toggle as the DrawerListener
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
	}
	
	

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}
	@Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
	
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Pass the event to ActionBarDrawerToggle, if it returns
	        // true, then it has handled the app icon touch event
	        if (mDrawerToggle.onOptionsItemSelected(item)) {
	          return true;
	        }
	        switch (item.getItemId()) {
	        // Respond to the action bar's Up/Home button
	        case android.R.id.home:
	            Intent upIntent = NavUtils.getParentActivityIntent(this);
	            if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
	                // This activity is NOT part of this app's task, so create a new task
	                // when navigating up, with a synthesized back stack.
	                TaskStackBuilder.create(this)
	                        // Add all of this activity's parents to the back stack
	                        .addNextIntentWithParentStack(upIntent)
	                        // Navigate up to the closest parent
	                        .startActivities();
	            } else {
	                // This activity is part of this app's task, so simply
	                // navigate up to the logical parent activity.
	                NavUtils.navigateUpTo(this, upIntent);
	            }
	            return true;
	        }

	        return super.onOptionsItemSelected(item);
	    }



	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		
		boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
		return super.onPrepareOptionsMenu(menu);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressWarnings("unused")
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			selectItem(position);
		}

		// Swaps fragments in the main content View.

		private void selectItem(int position) {
			// Creating a new fragment and specifying the day to show.
			Fragment fragment = new DayFragment();
			Bundle args = new Bundle();
			args.putInt(DayFragment.ARG_DAYS_NUMBER, position);
			fragment.setArguments(args);

			// Highlight the Selected item, update the title, and close the
			// drawer

			FragmentManager fragManger = getSupportFragmentManager();
			fragManger.beginTransaction().replace(R.id.content_frame, fragment)
					.commit();

			// Highlight the selected item, update the title, and close the
			// drawer
			mDrawerList.setItemChecked(position, true);
			setTitle(mPlanetTitles[position]);
			mDrawerLayout.closeDrawer(mDrawerList);

		}

		public void setTitle(CharSequence title) {
			mTitle = title;
			getActionBar().setTitle(mTitle);
		}

	}
}
