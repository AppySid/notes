package com.test.notes.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.test.notes.bean.NotesBean;
import com.test.notes.R;
import com.test.notes.adapter.NotesAdapter;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements View.OnClickListener {


    private ArrayList<NotesBean> notesBeanList = new ArrayList<>();
    private RecyclerView recyclerView;
    private NotesAdapter mAdapter;
    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = (RecyclerView) findViewById(R.id.rvNotes);
        view = findViewById(R.id.zero_state_screen);
        mAdapter = new NotesAdapter(notesBeanList, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddEditNewNotes.newIntent(MainActivity.this,view.getTag()+"");
            }
        });
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setAdapter(mAdapter);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        view.setOnClickListener(this);
        fab.setOnClickListener(this);

    }

    private void loadNotesData() {
        initDb();
        notesBeanList.clear();
        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                view.setVisibility(View.GONE);
                NotesBean notesBean = dataSnapshot.getValue(NotesBean.class);
                Log.d("data","data :: "+dataSnapshot);
                notesBeanList.add(notesBean);
                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                Log.d("TAG","data changed");

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//


    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotesData();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.fab:
            case R.id.zero_state_screen:
                AddEditNewNotes.newIntent(MainActivity.this,"");

                break;
        }

    }
}
