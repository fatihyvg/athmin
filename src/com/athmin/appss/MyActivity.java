package com.athmin.appss;

import android.app.Activity;
import android.content.Intent;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.*;

import android.util.Base64;
import android.util.Log;
import com.facebook.*;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import android.content.pm.Signature;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    private LoginButton loginbutton;
    private UiLifecycleHelper helper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        helper=new UiLifecycleHelper(this,scallback);
        helper.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        loginbutton=(LoginButton)findViewById(R.id.flogin);
        loginbutton.setUserInfoChangedCallback(stusercall);

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.athmin.appss",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
             Log.d("namenotfoundexception",e.getMessage());
        } catch (NoSuchAlgorithmException e) {
             Log.d("suchalgrotihmexception",e.getMessage());
        }
    }
    private LoginButton.UserInfoChangedCallback stusercall=new LoginButton.UserInfoChangedCallback() {
        @Override
        public void onUserInfoFetched(GraphUser user) {
            if(user != null){
                     Intent i=new Intent(MyActivity.this,WebBrowser.class);
                     startActivity(i);
            }else{

            }
        }
    };
    private Session.StatusCallback scallback=new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            if(state.isOpened()){
                Log.d("facebookactivity","facebook session open");
            }else if(state.isClosed()){
                Log.d("facebookactivity",exception.getMessage());
            }
        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        helper.onResume();
        Session.getActiveSession().isOpened();
    }

    @Override
    protected void onPause() {
        super.onPause();
        helper.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        helper.onDestroy();
    }
    @Override
    protected void onStop(){
        super.onStop();
        helper.onStop();
    }
    @Override
    protected void onStart(){
        super.onStart();

    }
    @Override
    protected void onRestart(){
        super.onRestart();
    }
    @Override
    public void finish(){
        super.finish();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        helper.onSaveInstanceState(outState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        helper.onActivityResult(requestCode,resultCode,data);
    }
}
