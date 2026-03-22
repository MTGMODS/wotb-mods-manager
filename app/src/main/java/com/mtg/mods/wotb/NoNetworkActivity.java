package com.mtg.mods.wotb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class NoNetworkActivity extends AppCompatActivity {
    private Timer _timer = new Timer();
    private LinearLayout linear1;
    private ImageView imageview1;
    private TextView textview2;
    private TimerTask t;
    private AlertDialog.Builder d;
    private Intent i = new Intent();
    private SharedPreferences network;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.no_network);
        initialize(_savedInstanceState);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        imageview1 = (ImageView) findViewById(R.id.imageview1);
        textview2 = (TextView) findViewById(R.id.textview2);
        d = new AlertDialog.Builder(this);
        network = getSharedPreferences("network", Activity.MODE_PRIVATE);
        settings = getSharedPreferences("settings", Activity.MODE_PRIVATE);
    }

    private void initializeLogic() {
        if (settings.getString("language", "").contains("english")) {
            textview2.setText("No internet connection\n\nConnect to the network\nWi-Fi or 3G/4G");
        }
        t = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isConnected()){
                            t.cancel();
                            if (network.getString("network", "").equals("update")) {
                                network.edit().remove("network").commit();
                                i.setClass(getApplicationContext(), UpdaterActivity.class);
                                startActivity(i);
                                overridePendingTransition(0, 0);
                            }
                            else {
                                if (network.getString("network", "").equals("other")) {
                                    network.edit().remove("network").commit();
                                    i.setClass(getApplicationContext(), MenuActivity.class);
                                    startActivity(i);
                                    overridePendingTransition(0, 0);
                                }
                                else {
                                    if (network.getString("network", "").equals("down_update")) {
                                        network.edit().remove("network").commit();
                                        i.setClass(getApplicationContext(), DownloadUpdateActivity.class);
                                        startActivity(i);
                                        overridePendingTransition(0, 0);
                                    }
                                    else {
                                        if (network.getString("network", "").equals("game")) {
                                            network.edit().remove("network").commit();
                                            i.setClass(getApplicationContext(), MiniGameActivity.class);
                                            startActivity(i);
                                            overridePendingTransition(0, 0);
                                        }
                                        else {
                                            if (network.getString("network", "").equals("settings")) {
                                                network.edit().remove("network").commit();
                                                i.setClass(getApplicationContext(), SettingsActivity.class);
                                                startActivity(i);
                                                overridePendingTransition(0, 0);
                                            }
                                            else {
                                                i.setClass(getApplicationContext(), LogoActivity.class);
                                                startActivity(i);
                                                overridePendingTransition(0, 0);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                });
            }
        };
        _timer.scheduleAtFixedRate(t, (int)(3000), (int)(3000));
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
    public int getLocationX(View _v) {
        int _location[] = new int[2];
        _v.getLocationInWindow(_location);
        return _location[0];
    }

    @Deprecated
    public int getLocationY(View _v) {
        int _location[] = new int[2];
        _v.getLocationInWindow(_location);
        return _location[1];
    }

    private boolean isConnected(){

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo!=null && networkInfo.isConnected();


    }

    @Deprecated
    public int getRandom(int _min, int _max) {
        Random random = new Random();
        return random.nextInt(_max - _min + 1) + _min;
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

    @Deprecated
    public float getDip(int _input) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
    }

    @Deprecated
    public int getDisplayWidthPixels() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    @Deprecated
    public int getDisplayHeightPixels() {
        return getResources().getDisplayMetrics().heightPixels;
    }
}
