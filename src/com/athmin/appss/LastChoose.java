package com.athmin.appss;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import com.facebook.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by HKN on 07.03.2015.
 */
public class LastChoose extends Activity {
    private GridView lsts;
    private TextView ginf;
//    private LinearLayout layout;
//    private LinearLayout.LayoutParams params;
    private Dialog pdialog;
    private ArrayAdapter<String> adapter;
    private String[] slist;
    private String a, b;
    private int res;
    private UiLifecycleHelper helpers;
    private static final List<String> PERMİSSİONS = Arrays.asList("publish_actions");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helpers = new UiLifecycleHelper(this, callback);
        helpers.onCreate(savedInstanceState);
        setContentView(R.layout.lastchose);
        slist = getResources().getStringArray(R.array.allayaks);
        adapter = new ArrayAdapter<String>(LastChoose.this, android.R.layout.simple_expandable_list_item_1, slist);
        lsts = (GridView) findViewById(R.id.lastlist);
        ginf = (TextView) findViewById(R.id.getguess);
        lsts.setOnItemClickListener(listener);
        lsts.setAdapter(adapter);
        a = getIntent().getStringExtra("racename");
        b = getIntent().getStringExtra("sethipod");
    }

    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            if (state.isOpened()) {
                Log.d("facebookactivity", "facebook session open");
            } else if (state.isClosed()) {
                Log.d("facebookactivity", "facebook session close");
            }
        }
    };

    public void Sendİnfo(String app) {
        if (checkpermissions()) {
            Request req = Request.newStatusUpdateRequest(Session.getActiveSession(), app, new Request.Callback() {
                @Override
                public void onCompleted(Response response) {
                    if (response.getError() == null) {
                        Toast.makeText(getApplicationContext(), "Tahmin Paylaşımınız Yapıldı", Toast.LENGTH_LONG).show();
                    }

                }
            });
            req.executeAsync();
        } else {
            requestpermissions();
        }
    }

    public boolean checkpermissions() {
        Session ses = Session.getActiveSession();
        if (ses != null) {
            return ses.getPermissions().contains("publish_actions");
        } else {
            return false;
        }
    }

    public void requestpermissions() {
        Session ses = Session.getActiveSession();
        if (ses != null) {
            ses.requestNewPublishPermissions(new Session.NewPermissionsRequest(this, PERMİSSİONS));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        helpers.onResume();
        Session.getActiveSession().isOpened();
    }

    @Override
    protected void onPause() {
        super.onPause();
        helpers.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helpers.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        helpers.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestcode, int resultcode, Intent data) {
        super.onActivityResult(requestcode, resultcode, data);
        helpers.onActivityResult(requestcode, resultcode, data);
    }

    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            new ASYNCMıssıons().execute(Integer.valueOf(slist[i]));
        }
    };
    private class ASYNCMıssıons extends AsyncTask<Integer, Void, Integer> {
        @Override
        protected Integer doInBackground(Integer... voids) {
            try {
                Random rnd = new Random();
                res = rnd.nextInt(Integer.valueOf(voids[0])) + 1;


            } catch (NumberFormatException e) {
                Log.d("numberformat exception", e.getMessage());
            }
            return res;
        }

        @Override
        protected void onPreExecute() {;
            pdialog = new Dialog(LastChoose.this,R.style.dialog_thene);
            pdialog.setCancelable(false);
            pdialog.setContentView(getLayoutInflater().inflate(R.layout.guesssplash,null));
            pdialog.show();

        }

        @Override
        protected void onPostExecute(final Integer aVoid) {
            Thread thr=new Thread(){
                @Override
                public void run() {
                   try {
                      sleep(3000);
                      pdialog.dismiss();
                     } catch (InterruptedException e) {
                   Log.d("interrupt exception", e.getMessage());
                 }
            }
         };
         thr.start();
          if(pdialog != null){
              ginf.setText("Tahmin Edilen="+String.valueOf(aVoid) + ".Ayak");
              Sendİnfo("ATHMİN UYGULAMASI İLE TAHMİN YAPTI\n"+b + " " + " " + a + ".Koşu" + " " + " " + "Tahmin Edilen Ayak=" + String.valueOf(aVoid));
          }
        }
    }
}
