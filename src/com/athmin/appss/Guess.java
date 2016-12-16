package com.athmin.appss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;


/**
 * Created by HKN on 26.02.2015.
 */
public class Guess extends Activity {
    private ListView lst;
    private ArrayAdapter<String> adapter;
    private String[] hippodroms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guess);
        hippodroms=getResources().getStringArray(R.array.allscitys);
        adapter = new ArrayAdapter<String>(Guess.this, android.R.layout.simple_expandable_list_item_1, hippodroms);
        lst = (ListView) findViewById(R.id.lsts);
        lst.setAdapter(adapter);
        lst.setOnItemClickListener(clistener);
    }
    private AdapterView.OnItemClickListener clistener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent ints = new Intent(Guess.this, RaceInfo.class);
            ints.putExtra("hippod", hippodroms[i]);
            startActivity(ints);
        }
    };
    @Override
    protected void onResume(){
        super.onResume();
    }
    @Override
    protected void onPause(){
        super.onPause();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
