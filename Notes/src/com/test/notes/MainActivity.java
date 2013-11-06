package com.test.notes;

import java.util.List;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends ListActivity {
	private NoteManager noteMan;  
	private ListView theList;  
	private int notePosn;  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		noteMan = new NoteManager(getApplicationContext());  
		List<Note> existingNotes = noteMan.getNotes();  
		
		ArrayAdapter<Note> noteAdapt = new ArrayAdapter<Note>(  
				 this, android.R.layout.simple_list_item_1, existingNotes);  
		setListAdapter(noteAdapt);  
		theList = getListView();  
		
		theList.setOnItemClickListener(new OnItemClickListener(){  
			 @Override  
			 public void onItemClick(AdapterView<?> arg0, View view,  
			  int position, long id) {  
				 notePosn=position;  
				 AlertDialog.Builder deleteAlert = new AlertDialog.Builder(MainActivity.this);  
				 deleteAlert.setTitle("Delete Note");  
				 deleteAlert.setMessage("Do you want to delete this note?");
				 deleteAlert.setPositiveButton("OK",   
						 new DialogInterface.OnClickListener() {  
						 public void onClick(DialogInterface dialog, int whichButton) {  
						  @SuppressWarnings("unchecked")
						ArrayAdapter<Note> noteAdapt = (ArrayAdapter<Note>)  
						    MainActivity.this.getListAdapter();  
						  Note clickedNote = (Note)noteAdapt.getItem(notePosn);  
						  @SuppressWarnings("unused")
						int noteDeleted = noteMan.deleteNote  
						   (clickedNote.getNoteID());  
						  noteAdapt.remove(clickedNote);  
						  noteAdapt.notifyDataSetChanged();  
						  setListAdapter(noteAdapt);  
						 }  
						});  
				 deleteAlert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {  
					 public void onClick(DialogInterface dialog, int which){  
					  dialog.cancel();  
					 }  
					});  
				 deleteAlert.show();  

			}  
			});  


	}
	
	public void addNewNote(View v){  
		AlertDialog.Builder addAlert = new AlertDialog.Builder(this);  
		addAlert.setTitle("New Note");  
		addAlert.setMessage("Enter your note:");  
		final EditText noteIn = new EditText(this);  
		addAlert.setView(noteIn);  
		addAlert.setPositiveButton("OK",   
				 new DialogInterface.OnClickListener() {  
				 public void onClick(DialogInterface dialog, int whichButton) {  
				  String noteInput = noteIn.getText().toString();  
				  Note inputNote = new Note();  
				  inputNote.setNoteText(noteInput);  
				  long addedID = noteMan.addNewNote(inputNote);  
				  inputNote.setNoteID(addedID);  
				  @SuppressWarnings("unchecked")
				ArrayAdapter<Note> noteAdapt = (ArrayAdapter<Note>)  
				    MainActivity.this.getListAdapter();  
				  noteAdapt.add(inputNote);  
				 }  
				});  
		addAlert.setNegativeButton("Cancel",   
				 new DialogInterface.OnClickListener() {  
				 public void onClick(DialogInterface dialog, int which){  
				  dialog.cancel();  
				 }  
				});  
		addAlert.show();  

	}  
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
