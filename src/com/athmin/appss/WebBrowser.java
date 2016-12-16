package com.athmin.appss;

import android.app.Activity;
import android.content.Intent;
import android.os.*;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by HKN on 25.02.2015.
 */
public class WebBrowser extends Activity {
    private final String url="http://www.tjk.mobi/#/v/emiContainerPageProgram";
    private WebView browsers;
    private Button sp;
    private Button bk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webbrowser);
        browsers=(WebView)findViewById(R.id.browser);
        sp=(Button)findViewById(R.id.sp);
        sp.setOnClickListener(blistener);
        bk=(Button)findViewById(R.id.bk);
        bk.setOnClickListener(tlistener);
        SetBrowserSettings(true);
        Toast.makeText(getApplicationContext(),"BÜLTEN YÜKLENİRKEN LÜTFEN BEKLEYİN",Toast.LENGTH_LONG).show();
    }
    public void SetBrowserSettings(boolean approve){
        browsers.getSettings().setJavaScriptEnabled(approve);
        browsers.getSettings().setUseWideViewPort(approve);
        browsers.getSettings().setLoadWithOverviewMode(approve);
        browsers.getSettings().setDomStorageEnabled(approve);
        browsers.loadUrl(url);
        browsers.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                browsers.loadUrl(url);
                return true;
            }
        });

    }
    private View.OnClickListener blistener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent ip=new Intent(WebBrowser.this,Guess.class);
            startActivity(ip);
        }
    };
    private View.OnClickListener tlistener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent irs=new Intent(WebBrowser.this,Guess.class);
            startActivity(irs);
        }
    };
    @Override
    public void onBackPressed(){
        if(this.isFinishing()) {
            finish();

        }
        super.onBackPressed();
    }
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
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void finish() {
        super.finish();
    }
}
