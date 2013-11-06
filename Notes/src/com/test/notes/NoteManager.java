package com.test.notes;

import java.util.ArrayList;  
import java.util.List;  
  
import android.content.ContentValues;  
import android.content.Context;  
import android.database.Cursor;  
import android.database.sqlite.SQLiteDatabase;  
import android.database.sqlite.SQLiteOpenHelper;  

public class NoteManager {
	public NoteManager(Context context){  
		 noteHelper = new MyDataHelper(context);  
		}  
	private SQLiteOpenHelper noteHelper;  
	private SQLiteDatabase noteDB;  
	
	public List<Note> getNotes(){  
		List<Note> notes = new ArrayList<Note>();  
		noteDB = noteHelper.getReadableDatabase();
		
		Cursor noteCursor = noteDB.query(MyDataHelper.DBItem.TABLE,   
				 null, null, null, null, null, null); 
		
		while(noteCursor.moveToNext()){  
			int newID = noteCursor.getInt(  
					 noteCursor.getColumnIndexOrThrow(MyDataHelper.DBItem._ID));  
					String newText = noteCursor.getString(  
					 noteCursor.getColumnIndexOrThrow(MyDataHelper.DBItem.NOTE_COL));
					Note newNote = new Note();  
					newNote.setNoteID(newID);  
					newNote.setNoteText(newText);  
					notes.add(newNote);  

		}  
		noteCursor.close();  
		noteDB.close();  
		return notes;  
		  
	}  
	
	public long addNewNote(Note addedNote){  
		noteDB = noteHelper.getWritableDatabase();  
		ContentValues noteValues = new ContentValues();  
		noteValues.put  
		 (MyDataHelper.DBItem.NOTE_COL, addedNote.getNoteText());  
		long added = noteDB.insertOrThrow  
				 (MyDataHelper.DBItem.TABLE, null, noteValues);  
		noteDB.close();  
		return added;  
	} 
	
	public int deleteNote(long noteID){  
		noteDB = noteHelper.getWritableDatabase();  
		String[] params = {""+noteID};  
		
		int deleted = noteDB.delete(MyDataHelper.DBItem.TABLE, MyDataHelper.DBItem._ID+" = ?", params);  
		noteDB.close();  
		return deleted;  


	}  
}
