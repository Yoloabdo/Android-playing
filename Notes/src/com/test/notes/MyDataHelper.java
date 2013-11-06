package com.test.notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;


public class MyDataHelper extends SQLiteOpenHelper  {
	
	public MyDataHelper(Context context) {  
		 super(context, DATABASE_NAME, null, DATABASE_VERSION);  
		}  

	public static final int DATABASE_VERSION = 1;  
	public static final String DATABASE_NAME = "UserNotes.db";  
	
	private static final String CREATE_STR = "CREATE TABLE " +   
			 DBItem.TABLE + " (" + DBItem._ID +   
			 " INTEGER PRIMARY KEY AUTOINCREMENT, " +  
			 DBItem.NOTE_COL + " TEXT);";  
	
	public static class DBItem implements BaseColumns {  
		 public static final String TABLE = "notes";  
		 public static final String NOTE_COL = "note";  
		}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		 db.execSQL(CREATE_STR);  

		
	}

	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
		 db.execSQL("DROP TABLE IF EXISTS " + DBItem.TABLE);  
		 onCreate(db);  
		}  
	
	public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
		 onUpgrade(db, oldVersion, newVersion);  
		}  
	
}