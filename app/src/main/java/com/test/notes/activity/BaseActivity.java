package com.test.notes.activity;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.test.notes.constants.FirebaseNodes;

/**
 * Created by Seeinside on 11/12/17.
 */

public class BaseActivity  extends AppCompatActivity {

   protected DatabaseReference dbRef;

    static {
        //To support offline more
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

   public void initDb(){

       Log.d("Init db","db all set");
       dbRef = FirebaseDatabase.getInstance().getReference(FirebaseNodes.Notes);
       dbRef.keepSynced(true);
   }

}
