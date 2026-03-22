package com.mtg.mods.wotb;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.onesignal.OneSignal;

import java.util.ArrayList;
import java.util.HashMap;


public class UpdaterActivity extends AppCompatActivity {

    private static final String ONESIGNAL_APP_ID = "4622b57b-86f9-458e-a31a-c6777b81820b";
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
    private String version = "";
    private String url = "";
    private String update_en = "";
    private String update = "";
    private String appversion = "";
    private String url_original = "";
    private String check_original = "";
    private String stop = "";
    private HashMap<String, Object> map8 = new HashMap<>();
    private String check_launches = "";
    private double users = 0;
    private double launchess = 0;
    private HashMap<String, Object> map9 = new HashMap<>();
    private ArrayList<HashMap<String, Object>> info = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> map2 = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> map3 = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> map4 = new ArrayList<>();
    private LinearLayout content;
    private TextView textview2;
    private Intent i = new Intent();
    private AlertDialog.Builder d;
    private ProgressDialog pd;
    private DatabaseReference fb = _firebase.getReference("/");
    private ChildEventListener _fb_child_listener;
    private DatabaseReference fdb = _firebase.getReference("Launches");
    private ChildEventListener _fdb_child_listener;
    private DatabaseReference fbs = _firebase.getReference("Manage");
    private ChildEventListener _fbs_child_listener;
    private DatabaseReference fdb1 = _firebase.getReference("Users");
    private ChildEventListener _fdb1_child_listener;
    private DatabaseReference fbsU = _firebase.getReference("UpdateCheck");
    private ChildEventListener _fbsU_child_listener;
    private SharedPreferences sh;
    private SharedPreferences network;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_updater);
        com.google.firebase.FirebaseApp.initializeApp(this);

        content = (LinearLayout) findViewById(R.id.content);
        textview2 = (TextView) findViewById(R.id.textview2);
        d = new AlertDialog.Builder(this,AlertDialog.THEME_TRADITIONAL);
        d.setCancelable(false);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        network = getSharedPreferences("network", Activity.MODE_PRIVATE);
        settings = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        sh = getSharedPreferences("users", Activity.MODE_PRIVATE);

        if (isConnected()) {

            OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE);
            OneSignal.initWithContext(this);
            OneSignal.setAppId(ONESIGNAL_APP_ID);

            appversion = "null";
            try{
                android.content.pm.PackageInfo pInfo = UpdaterActivity.this.getPackageManager().getPackageInfo(getPackageName(), 0);
                appversion = pInfo.versionName;
            } catch (android.content.pm.PackageManager.NameNotFoundException e){
                e.printStackTrace();
            }
            _check_stop();
        } else {
            network.edit().putString("network", "update").commit();
            i.setClass(getApplicationContext(), NoNetworkActivity.class);
            startActivity(i);
            overridePendingTransition(0, 0);
        }

    }

    private void _check_stop() {

        fbs.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot _dataSnapshot) {
                map4 = new ArrayList<>();
                try {
                    GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                    for (DataSnapshot _data : _dataSnapshot.getChildren()) {
                        HashMap<String, Object> _map = _data.getValue(_ind);
                        map4.add(_map);
                    }
                }
                catch (Exception _e) {
                    _e.printStackTrace();
                }
                stop = map4.get((int)0).get("stop").toString();
                if (stop.contains("1")) {
                    i.setClass(getApplicationContext(), StopActivity.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                }
                else {
                    _check_update();
                }
            }
            @Override
            public void onCancelled(DatabaseError _databaseError) {
            }
        });

    }

    private void _check_update() {

        fbsU.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot _dataSnapshot) {
                map3 = new ArrayList<>();
                try {
                    GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                    for (DataSnapshot _data : _dataSnapshot.getChildren()) {
                        HashMap<String, Object> _map = _data.getValue(_ind);
                        map3.add(_map);
                    }
                }
                catch (Exception _e) {
                    _e.printStackTrace();
                }
                update = map3.get((int)0).get("update").toString();
                update_en = map3.get((int)0).get("update_en").toString();
                url = map3.get((int)0).get("update_url").toString();
                version = map3.get((int)0).get("version").toString();
                if (appversion.equals(version)) {



//                    for demo, disable usage statictic
                    i.setClass(getApplicationContext(), MenuActivity.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);

//                    fb.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot _dataSnapshot) {
//                            info = new ArrayList<>();
//                            try {
//                                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
//                                for (DataSnapshot _data : _dataSnapshot.getChildren()) {
//                                    HashMap<String, Object> _map = _data.getValue(_ind);
//                                    info.add(_map);
//                                }
//                            }
//                            catch (Exception _e) {
//                                _e.printStackTrace();
//                            }
//                            check_launches = info.get((int)0).get("check_launches").toString();
//                            if (sh.getString("check", "").contains("true")) {
//                                if (check_launches.contains("1")) {
//                                    _launches();
//                                }
//                                else {
//                                    i.setClass(getApplicationContext(), MenuActivity.class);
//                                    startActivity(i);
//                                    overridePendingTransition(0, 0);
//                                }
//                            }
//                            else {
//                                fdb1.addListenerForSingleValueEvent(new ValueEventListener() {
//                                    @Override
//                                    public void onDataChange(DataSnapshot _dataSnapshot) {
//                                        map = new ArrayList<>();
//                                        try {
//                                            GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
//                                            for (DataSnapshot _data : _dataSnapshot.getChildren()) {
//                                                HashMap<String, Object> _map = _data.getValue(_ind);
//                                                map.add(_map);
//                                            }
//                                        }
//                                        catch (Exception _e) {
//                                            _e.printStackTrace();
//                                        }
//                                        users = Double.parseDouble(map.get((int)0).get("users").toString());
//                                        users = users + 1;
//                                        map8 = new HashMap<>();
//                                        map8.put("users", String.valueOf((long)(users)));
//                                        fb.child("Users").removeValue();
//                                        fdb1.push().updateChildren(map8);
//                                        sh.edit().putString("check", "true").commit();
//                                        if (check_launches.contains("1")) {
//                                            _launches();
//                                        }
//                                        else {
//                                            i.setClass(getApplicationContext(), MenuActivity.class);
//                                            startActivity(i);
//                                            overridePendingTransition(0, 0);
//                                        }
//                                    }
//                                    @Override
//                                    public void onCancelled(DatabaseError _databaseError) {
//                                    }
//                                });
//                            }
//                        }
//                        @Override
//                        public void onCancelled(DatabaseError _databaseError) {
//                        }
//                    });



                }
                else {
                    d.setIcon(R.drawable.ic_vertical_align_bottom_white);
                    if (settings.getString("language", "").contains("english")) {
                        d.setTitle("Application update required");
                        d.setMessage("A new, improved and optimized version has been released.\n\nWhat's new: (version ".concat(version.concat(")\n".concat(update_en))));
                        d.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface _dialog, int _which) {
                                _update_click();
                            }
                        });
                    }
                    else {
                        d.setTitle("Требуется обновление");
                        d.setMessage("Вышла новая, более улучшенная и  оптимизированная версия.\n\nЧто нового: (версия ".concat(version.concat(")\n".concat(update))));
                        d.setPositiveButton("Обновить", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface _dialog, int _which) {
                                _update_click();
                            }
                        });
                    }
                    d.create().show();
                }
            }
            @Override
            public void onCancelled(DatabaseError _databaseError) {
            }
        });

    }

    @Override
    public void onBackPressed() {

    }

    public void _update_click () {

        i.setAction(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
        overridePendingTransition(0, 0);
    }

    private boolean isConnected(){

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo!=null && networkInfo.isConnected();
    }

    public void _launches () {
        fdb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot _dataSnapshot) {
                map2 = new ArrayList<>();
                try {
                    GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                    for (DataSnapshot _data : _dataSnapshot.getChildren()) {
                        HashMap<String, Object> _map = _data.getValue(_ind);
                        map2.add(_map);
                    }
                }
                catch (Exception _e) {
                    _e.printStackTrace();
                }
                launchess = Double.parseDouble(map2.get((int)0).get("launches").toString());
                launchess = launchess + 1;
                map9 = new HashMap<>();
                map9.put("launches", String.valueOf((long)(launchess)));
                fb.child("Launches").removeValue();
                fdb.push().updateChildren(map9);
                i.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
            @Override
            public void onCancelled(DatabaseError _databaseError) {
            }
        });
    }

}
