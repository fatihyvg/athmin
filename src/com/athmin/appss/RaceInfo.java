package com.athmin.appss;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;

/**
 * Created by HKN on 07.03.2015.
 */
public class RaceInfo extends Activity {
    private GridView lsts;
    private ArrayAdapter<String> adapter;
    private String[] lists;
    private String getinf;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.race);
        lists=getResources().getStringArray(R.array.allrace);
        adapter=new ArrayAdapter<String>(RaceInfo.this,android.R.layout.simple_expandable_list_item_1,lists);
        lsts=(GridView)findViewById(R.id.racelist);
        lsts.setOnItemClickListener(listener);
        lsts.setAdapter(adapter);
        getinf=getIntent().getStringExtra("hippod");
    }
    private AdapterView.OnItemClickListener listener=new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            Intent ip=new Intent(RaceInfo.this,LastChoose.class);
            ip.putExtra("racename",lists[i]);
            ip.putExtra("sethipod",getinf);
            startActivity(ip);
        }
    };
}
