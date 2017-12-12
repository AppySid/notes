package com.test.notes.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.test.notes.bean.NotesBean;
import com.test.notes.R;

/*
* If notes id is passed as param -> goes to edit mode
* else
* Goes to add new note mode
*
*
* */

public class AddEditNewNotes extends BaseActivity {

    private static final String NOTES_ID ="NOTES_ID" ;
    EditText etNotes,etNotesTitle;
    TextView title;

    NotesBean notesBean;
    String notesId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_new_notes);

        initView();
        initData();
    }

    private void initData() {
        initDb();
        notesId=getIntent().getStringExtra(NOTES_ID);
        if(!notesId.equals("")){
            title.setText("Edit Notes");
            dbRef.child(notesId).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    notesBean = dataSnapshot.getValue(NotesBean.class);
                    etNotes.setText(notesBean.getNotes());
                    etNotesTitle.setText(notesBean.getTitle());


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }else{

            title.setText("Add Notes");

        }


    }

    private void initView() {

        etNotes = findViewById(R.id.etNotes);
        etNotesTitle = findViewById(R.id.etNotesTitle);
        title = findViewById(R.id.toolbar_title);

    }


    public void saveNotes(View view) {

        NotesBean notes = new NotesBean();
        notes.setNotes(etNotes.getText()+"");
        notes.setTitle(etNotesTitle.getText()+"");
        notes.setNotesId(notesId.equals("")?dbRef.push().getKey():notesId);
        dbRef.child(notes.getNotesId()).setValue(notes);
        finish();

    }


    public static void newIntent(Activity mainActivity, String notesId) {

        Intent intent = new Intent(mainActivity,AddEditNewNotes.class);
        intent.putExtra(NOTES_ID,notesId);
        mainActivity.startActivity(intent);


    }
}
