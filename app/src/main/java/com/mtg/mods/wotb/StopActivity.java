package com.mtg.mods.wotb;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class StopActivity extends AppCompatActivity {
    private Timer _timer = new Timer();

    private LinearLayout linear1;
    private ImageView imageview1;
    private ImageView imageview2;
    private TextView textview1;

    private Intent i = new Intent();
    private Intent b = new Intent();
    private SharedPreferences settings;
    private TimerTask t;

    String GameID = "4595401";
    String ADID = "Interstitial_Android";
    Boolean TestMode = false;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.stop);
        initialize(_savedInstanceState);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        imageview1 = (ImageView) findViewById(R.id.imageview1);
        imageview2 = (ImageView) findViewById(R.id.imageview2);
        textview1 = (TextView) findViewById(R.id.textview1);
        settings = getSharedPreferences("settings", Activity.MODE_PRIVATE);

        imageview1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View _view) {
                if (settings.getString("language", "").contains("english")) {
                    SketchwareUtil.showMessage(getApplicationContext(), "Force login...");
                }
                else {
                    SketchwareUtil.showMessage(getApplicationContext(), "Принудительный вход...");
                }
                t = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                i.setClass(getApplicationContext(), MenuActivity.class);
                                startActivity(i);
                                overridePendingTransition(0, 0);
                            }
                        });
                    }
                };
                _timer.schedule(t, (int)(3000));
                return true;
            }
        });

        imageview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                b.setAction(Intent.ACTION_VIEW);
                b.setData(Uri.parse("https://discord.gg/uUZbxgEByu"));
                startActivity(b);
            }
        });
    }

    private void initializeLogic() {
        if (settings.getString("language", "").contains("english")) {
            textview1.setText("The app is temporarily unavailable\n\nTechnical work in progress\n\n\n\nOur Discord Community:");
        }
        if (settings.getString("ads", "").contains("false")) {

        }
        else {
            UnityAds.initialize(StopActivity.this, GameID, TestMode, new IUnityAdsInitializationListener() {
                @Override
                public void onInitializationComplete() {


                }

                @Override
                public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

                }
            });
        }

        if (settings.getString("ads", "").contains("false")) {

        }
        else {
            IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
                @Override
                public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                    UnityAds.load(ADID);
                    UnityAds.show(StopActivity.this, ADID);
                }

                @Override
                public void onUnityAdsShowStart(String s) {

                }

                @Override
                public void onUnityAdsShowClick(String s) {

                }

                @Override
                public void onUnityAdsShowComplete(String s, UnityAds.UnityAdsShowCompletionState state) {

                }
            };
            UnityAds.load(ADID);
            UnityAds.show(StopActivity.this, ADID);
        }
    }

    @Override
    protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
        super.onActivityResult(_requestCode, _resultCode, _data);
        switch (_requestCode) {

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {

    }



}
