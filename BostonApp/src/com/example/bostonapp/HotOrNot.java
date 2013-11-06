package com.example.bostonapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//DAta Helper, Creator, etc.
public class HotOrNot {
	public static final String KEY_ROWID = "id";
	public static final String KEY_NAME = "persons_name";
	public static final String KEY_HOTNESS = "persons_hotness";
	
	private static final String DATABASE_NAME = "hotOrNotDb";
	private static final String DATABASE_TABLE = "peopleTable";
	private static final int DATABASE_VERSION = 1;
	
	private DBhelper ourHelper;
	private Context ourContext;
	private SQLiteDatabase ourDB;
	
	private static class DBhelper extends SQLiteOpenHelper{

		public DBhelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
			
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			
			db.execSQL("CREATE TABLE " + 
			DATABASE_TABLE + " (" + KEY_ROWID + " INTEGER PRIMARY KEY, "
			+ KEY_NAME + " TEXT NOT NULL, " +
			KEY_HOTNESS + " TEXT NOT NULL)"
			);
			
			
			
		}
		

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			onCreate(db);
		}
		
	}
	
	public HotOrNot(Context c) {
		ourContext = c;
		
	}
	public HotOrNot open() throws SQLException{
		ourHelper = new DBhelper(ourContext);
		ourDB = ourHelper.getWritableDatabase();
		return this;
	}
	
	public void close(){
		ourHelper.close();
	}
	public void createEntry(String sqlname, String sqlhot) {
		// TODO Auto-generated method stub
		ContentValues cv = new ContentValues();
		cv.put(KEY_NAME, sqlname);
		cv.put(KEY_HOTNESS, sqlhot);
		ourDB.insert(DATABASE_TABLE, null, cv);
		
		
	}
	public String getData() {
		// TODO Auto-generated method stub
		String[] coulmns = new String[] {KEY_ROWID, KEY_NAME,KEY_HOTNESS};
		Cursor c = ourDB.query(DATABASE_TABLE, coulmns, null, null, null, null, null);
		String result = "";
		int Iraw = c.getColumnIndex(KEY_ROWID);
		int iName = c.getColumnIndex(KEY_NAME);
		int iHotness = c.getColumnIndex(KEY_HOTNESS);
		
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
			result = result + c.getString(Iraw) + " " + c.getString(iName)+ " " + c.getString(iHotness)+"\n";
			
		}
		
		return result;
	}
}