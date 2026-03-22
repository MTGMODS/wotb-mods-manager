package com.mtg.mods.wotb;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.SparseBooleanArray;
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
import java.util.Timer;
import java.util.TimerTask;


public class LimitActivity extends AppCompatActivity {
    private Timer _timer = new Timer();

    private LinearLayout linear1;
    private ImageView imageview1;
    private TextView textview1;

    private Intent i = new Intent();
    private TimerTask t;
    private SharedPreferences settings;

    String GameID = "4595401";
    String ADID = "Interstitial_Android";
    Boolean TestMode = false;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.limit);
        initialize(_savedInstanceState);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initializeLogic();
        if (settings.getString("ads", "").contains("false")) {

        }
        else {
            UnityAds.initialize(LimitActivity.this, GameID, TestMode, new IUnityAdsInitializationListener() {
                @Override
                public void onInitializationComplete() {


                }

                @Override
                public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

                }
            });
        }

        if (settings.getString("language", "").contains("english")) {
            textview1.setText("Error connecting to the database!\n\nYou may have a weak Internet connection.\n\n Or the database is overloaded.\nThen try downloading 20:59 AM (UTC)");
        }
        if (settings.getString("ads", "").contains("false")) {

        }
        else {
            IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
                @Override
                public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                    UnityAds.load(ADID);
                    UnityAds.show(LimitActivity.this, ADID);
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
            UnityAds.show(LimitActivity.this, ADID);
        }
    }

    private void initialize(Bundle _savedInstanceState) {
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        imageview1 = (ImageView) findViewById(R.id.imageview1);
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

        imageview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {

            }
        });
    }

    private void initializeLogic() {

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
    @Deprecated
    public void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
    }


    @Deprecated
    public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
        ArrayList<Double> _result = new ArrayList<Double>();
        SparseBooleanArray _arr = _list.getCheckedItemPositions();
        for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
            if (_arr.valueAt(_iIdx))
                _result.add((double)_arr.keyAt(_iIdx));
        }
        return _result;
    }

}
