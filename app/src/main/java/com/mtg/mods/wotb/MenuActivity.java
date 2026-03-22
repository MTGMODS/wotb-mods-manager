package com.mtg.mods.wotb;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import com.google.firebase.database.ValueEventListener;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class MenuActivity extends AppCompatActivity {
    private Timer _timer = new Timer();
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

    private String str = "";
    private String stop_s = "";
    private String stop_wot = "";
    private String message = "";
    private String message_en = "";
    private String message_wot = "";
    private String message_wot_en = "";
    private String assetFilename = "";
    private String assetSavePath = "";
    private String buildd = "";
    private String ads_code = "";
    private String ads_code_firebase = "";
    private String United = "";
    private String placementVideo = "";

    private ArrayList<HashMap<String, Object>> info = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> info2 = new ArrayList<>();

    private LinearLayout background;
    private LinearLayout first;
    private LinearLayout content;
    private LinearLayout linear1;
    private LinearLayout vverxyy;
    private ScrollView vscroll1;
    private LinearLayout linear61;
    private LinearLayout linear40;
    private LinearLayout linear90;
    private LinearLayout linear10;
    private LinearLayout linear28;
    private LinearLayout linear6;
    private LinearLayout linear33;
    private LinearLayout linear51;
    private LinearLayout linear73;
    private LinearLayout linear68;
    private LinearLayout linear72;
    private LinearLayout linear45;
    private LinearLayout linear25;
    private LinearLayout linear26;
    private LinearLayout linear46;
    private LinearLayout linear47;
    private TextView textview21;
    private LinearLayout linear49;
    private LinearLayout linear50;
    private TextView textview19;
    private TextView textview90;
    private TextView textview20;
    private TextView textview11;
    private LinearLayout linear11;
    private LinearLayout linear13;
    private TextView textview9;
    private TextView textview10;
    private LinearLayout linear29;
    private LinearLayout linear31;
    private TextView textview14;
    private TextView textview15;
    private LinearLayout linear20;
    private LinearLayout linear34;
    private TextView textview12;
    private TextView textview17;
    private LinearLayout linear43;
    private LinearLayout linear44;
    private TextView textview18;
    private TextView textview16;
    private LinearLayout linear52;
    private TextView textview22;
    private TextView textview33;
    private LinearLayout linear70;
    private LinearLayout linear71;
    private TextView textview30;
    private LinearLayout linear54;
    private LinearLayout linear56;
    private TextView textview24;
    private TextView textview34;
    private LinearLayout linear62;
    private LinearLayout linear63;
    private LinearLayout linear65;
    private ImageView imageview10;
    private TextView textview25;
    private ImageView imageview9;
    private TextView textview26;
    private ImageView imageview8;
    private TextView textview28;

    private Intent i = new Intent();
    private AlertDialog.Builder d;
    private Intent b = new Intent();
    private SharedPreferences network;
    private SharedPreferences settings;
    private AlertDialog.Builder bb;
    private DatabaseReference fdb = _firebase.getReference("Modpacks");
    private ChildEventListener _fdb_child_listener;
    private SharedPreferences dial;
    private SharedPreferences installer;
    private DatabaseReference fbs = _firebase.getReference("/");
    private ChildEventListener _fbs_child_listener;
    private ProgressDialog pd;
    private TimerTask t;
    private FirebaseAuth mAuth;
    private AlertDialog.Builder q;
    private SharedPreferences vip;
    private SharedPreferences login;
    private SharedPreferences login_user;
    private AlertDialog.Builder abb;
    private AlertDialog.Builder m;
    private AlertDialog.Builder abc;
    private ProgressDialog qq;
    private AlertDialog.Builder obama;
    private AlertDialog.Builder ads;
    private AlertDialog.Builder dialog;
    private Intent nn = new Intent();
    private AlertDialog.Builder p;

    String GameID = "4595401";
    String ADID = "Interstitial_Android";
    Boolean TestMode = false;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.menu);
        initialize(_savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        com.google.firebase.FirebaseApp.initializeApp(this);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {
        background = (LinearLayout) findViewById(R.id.background);
        first = (LinearLayout) findViewById(R.id.first);
        content = (LinearLayout) findViewById(R.id.content);
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        vverxyy = (LinearLayout) findViewById(R.id.vverxyy);
        vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
        linear61 = (LinearLayout) findViewById(R.id.linear61);
        linear40 = (LinearLayout) findViewById(R.id.linear40);
        linear10 = (LinearLayout) findViewById(R.id.linear10);
        linear28 = (LinearLayout) findViewById(R.id.linear28);
        linear6 = (LinearLayout) findViewById(R.id.linear6);
        linear33 = (LinearLayout) findViewById(R.id.linear33);
        linear51 = (LinearLayout) findViewById(R.id.linear51);
        linear73 = (LinearLayout) findViewById(R.id.linear73);
        linear68 = (LinearLayout) findViewById(R.id.linear68);
        linear72 = (LinearLayout) findViewById(R.id.linear72);
        linear45 = (LinearLayout) findViewById(R.id.linear45);
        linear25 = (LinearLayout) findViewById(R.id.linear25);
        linear26 = (LinearLayout) findViewById(R.id.linear26);
        linear46 = (LinearLayout) findViewById(R.id.linear46);
        linear47 = (LinearLayout) findViewById(R.id.linear47);
        textview21 = (TextView) findViewById(R.id.textview21);
        linear49 = (LinearLayout) findViewById(R.id.linear49);
        linear50 = (LinearLayout) findViewById(R.id.linear50);
        textview19 = (TextView) findViewById(R.id.textview19);
        textview20 = (TextView) findViewById(R.id.textview20);
        textview90 = (TextView) findViewById(R.id.textview90);
        textview11 = (TextView) findViewById(R.id.textview11);
        linear11 = (LinearLayout) findViewById(R.id.linear11);
        linear13 = (LinearLayout) findViewById(R.id.linear13);
        textview9 = (TextView) findViewById(R.id.textview9);
        textview10 = (TextView) findViewById(R.id.textview10);
        linear29 = (LinearLayout) findViewById(R.id.linear29);
        linear31 = (LinearLayout) findViewById(R.id.linear31);
        textview14 = (TextView) findViewById(R.id.textview14);
        textview15 = (TextView) findViewById(R.id.textview15);
        linear20 = (LinearLayout) findViewById(R.id.linear20);
        linear34 = (LinearLayout) findViewById(R.id.linear34);
        textview12 = (TextView) findViewById(R.id.textview12);
        textview17 = (TextView) findViewById(R.id.textview17);
        linear43 = (LinearLayout) findViewById(R.id.linear43);
        linear44 = (LinearLayout) findViewById(R.id.linear44);
        textview18 = (TextView) findViewById(R.id.textview18);
        textview16 = (TextView) findViewById(R.id.textview16);
        linear52 = (LinearLayout) findViewById(R.id.linear52);
        textview22 = (TextView) findViewById(R.id.textview22);
        textview33 = (TextView) findViewById(R.id.textview33);
        linear70 = (LinearLayout) findViewById(R.id.linear70);
        linear90 = (LinearLayout) findViewById(R.id.linear90);
        linear71 = (LinearLayout) findViewById(R.id.linear71);
        textview30 = (TextView) findViewById(R.id.textview30);
        linear54 = (LinearLayout) findViewById(R.id.linear54);
        linear56 = (LinearLayout) findViewById(R.id.linear56);
        textview24 = (TextView) findViewById(R.id.textview24);
        textview34 = (TextView) findViewById(R.id.textview34);
        linear62 = (LinearLayout) findViewById(R.id.linear62);
        linear63 = (LinearLayout) findViewById(R.id.linear63);
        linear65 = (LinearLayout) findViewById(R.id.linear65);
        imageview10 = (ImageView) findViewById(R.id.imageview10);
        textview25 = (TextView) findViewById(R.id.textview25);
        imageview9 = (ImageView) findViewById(R.id.imageview9);
        textview26 = (TextView) findViewById(R.id.textview26);
        imageview8 = (ImageView) findViewById(R.id.imageview8);
        textview28 = (TextView) findViewById(R.id.textview28);
        d = new AlertDialog.Builder(this);
        network = getSharedPreferences("network", Activity.MODE_PRIVATE);
        settings = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        login = getSharedPreferences("login", Activity.MODE_PRIVATE);
        login_user = getSharedPreferences("login_check", Activity.MODE_PRIVATE);
        bb = new AlertDialog.Builder(this);
        dial = getSharedPreferences("dialog", Activity.MODE_PRIVATE);
        installer = getSharedPreferences("installer", Activity.MODE_PRIVATE);
        q = new AlertDialog.Builder(this);
        vip = getSharedPreferences("vip", Activity.MODE_PRIVATE);
        abb = new AlertDialog.Builder(this);
        m = new AlertDialog.Builder(this);
        abc = new AlertDialog.Builder(this);
        obama = new AlertDialog.Builder(this);
        ads = new AlertDialog.Builder(this);
        dialog = new AlertDialog.Builder(this);
        p = new AlertDialog.Builder(this);

        textview19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (stop_s.contains("1")) {
                    if (settings.getString("language", "").contains("english")) {
                        d.setTitle("Attention!");
                        d.setIcon(R.drawable.ic_report_white);
                        d.setMessage(message_en);
                        d.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface _dialog, int _which) {

                            }
                        });
                        d.create().show();
                    }
                    else {
                        d.setTitle("Внимание!");
                        d.setIcon(R.drawable.ic_report_white);
                        d.setMessage(message);
                        d.setPositiveButton("Хорошо", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface _dialog, int _which) {

                            }
                        });
                        d.create().show();
                    }
                }
                else {
                    i.setClass(getApplicationContext(), SolyankaViewActivity.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                }
            }
        });

        textview20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (stop_wot.contains("1")) {
                    if (settings.getString("language", "").contains("english")) {
                        d.setTitle("Attention!");
                        d.setIcon(R.drawable.ic_report_white);
                        d.setMessage(message_wot_en);
                        d.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface _dialog, int _which) {

                            }
                        });
                        d.create().show();
                    }
                    else {
                        d.setTitle("Внимание!");
                        d.setIcon(R.drawable.ic_report_white);
                        d.setMessage(message_wot);
                        d.setPositiveButton("Хорошо", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface _dialog, int _which) {

                            }
                        });
                        d.create().show();
                    }
                }
                else {
                    i.setClass(getApplicationContext(), WotViewActivity.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                }
            }
        });

        linear11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), HangarsActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        linear13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), SightActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        textview9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), HangarsActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        textview10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {

                i.setClass(getApplicationContext(), SightActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        linear29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), SkinsActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        linear31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), RemodelingActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        textview14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {

                i.setClass(getApplicationContext(), SkinsActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        textview15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), RemodelingActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        linear20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), SixsenseActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        linear34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), InterfacesActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        textview12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), SixsenseActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        textview17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), InterfacesActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        linear43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), SoundsActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        linear44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), OthersActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        textview18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), SoundsActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        textview16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), OthersActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        linear52.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                t = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                b.setClass(getApplicationContext(), VipActivity.class);
                                startActivity(b);
                                overridePendingTransition(0, 0);
                            }
                        });
                    }
                };
                _timer.schedule(t, (int)(100));
            }
        });

        textview22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                t = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                b.setClass(getApplicationContext(), VipActivity.class);
                                startActivity(b);
                                overridePendingTransition(0, 0);
                            }
                        });
                    }
                };
                _timer.schedule(t, (int)(100));
            }
        });

        linear71.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), InfoActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        textview30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), InfoActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        linear54.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {

            }
        });

        linear90.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View _view) {

                if (login_user.getString("login", "").contains("yes")) {

                    if (login.getString("type", "").contains("admin")) {
                        i.setClass(getApplicationContext(), AdminPanelActivity.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                    } else {
                        i.setClass(getApplicationContext(), ModderPanelActivity.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                    }

                }
                else {
                    i.setClass(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                }

                return true;
            }
        });

        textview90.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View _view) {

                if (login_user.getString("login", "").contains("yes")) {

                    if (login.getString("type", "").contains("admin")) {
                        i.setClass(getApplicationContext(), AdminPanelActivity.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);                    } else {
                        i.setClass(getApplicationContext(), ModderPanelActivity.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                    }

                }
                else {
                    i.setClass(getApplicationContext(), LoginActivity.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                }

                return true;
            }
        });

        textview90.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), NewModActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        linear90.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), NewModActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        linear56.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                _ads_video();
            }
        });

        textview24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                _ads_video();
            }
        });

        textview34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                _ads_video();
            }
        });

        linear63.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                _play();
            }
        });

        linear65.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                nn.setClass(getApplicationContext(), SettingsActivity.class);
                startActivity(nn);
                overridePendingTransition(0, 0);
            }
        });

        imageview9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                _play();
            }
        });

        textview26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                _play();
            }
        });

        imageview8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                nn.setClass(getApplicationContext(), SettingsActivity.class);
                startActivity(nn);
                overridePendingTransition(0, 0);
            }
        });

        textview28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                nn.setClass(getApplicationContext(), SettingsActivity.class);
                startActivity(nn);
                overridePendingTransition(0, 0);
            }
        });

    }

    private void initializeLogic() {
        if (SketchwareUtil.isConnected(getApplicationContext())) {


            p = new AlertDialog.Builder(this,AlertDialog.THEME_TRADITIONAL);
            d = new AlertDialog.Builder(this,AlertDialog.THEME_TRADITIONAL);
            q = new AlertDialog.Builder(this,AlertDialog.THEME_TRADITIONAL);
            m = new AlertDialog.Builder(this,AlertDialog.THEME_DEVICE_DEFAULT_DARK);
            bb = new AlertDialog.Builder(this,AlertDialog.THEME_TRADITIONAL);
            abb = new AlertDialog.Builder(this,AlertDialog.THEME_TRADITIONAL);
            abc = new AlertDialog.Builder(this,AlertDialog.THEME_TRADITIONAL);
            obama = new AlertDialog.Builder(this,AlertDialog.THEME_TRADITIONAL);
            d.setCancelable(false);
            obama.setCancelable(false);
            bb.setCancelable(false);
            q.setCancelable(false);
            abb.setCancelable(false);
            abc.setCancelable(false);
            vscroll1.setHorizontalScrollBarEnabled(false);
            vscroll1.setVerticalScrollBarEnabled(false);
            vscroll1.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);

            fdb.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot _dataSnapshot) {
                    info = new ArrayList<>();
                    try {
                        GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                        for (DataSnapshot _data : _dataSnapshot.getChildren()) {
                            HashMap<String, Object> _map = _data.getValue(_ind);
                            info.add(_map);
                        }
                    }
                    catch (Exception _e) {
                        _e.printStackTrace();
                    }

                    stop_s = info.get((int) 0).get("stop").toString();
                    stop_wot = info.get((int) 1).get("stop").toString();
                    message_wot_en  = info.get((int) 1).get("message_en").toString();
                    message_wot  = info.get((int) 1).get("message").toString();
                    message_en  = info.get((int) 0).get("message_en").toString();
                    message  = info.get((int) 0).get("message").toString();

                }
                @Override
                public void onCancelled(DatabaseError _databaseError) {

                }
            });

            if (settings.getString("video_background", "").contains("true")) {
                RelativeLayout rl = new RelativeLayout(this); RelativeLayout.LayoutParams lparams = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT); rl.setLayoutParams(lparams); background.removeAllViews(); rl.addView(first); rl.addView(content); background.addView(rl);
                vidview = new VideoView(this);
                vidview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                linear1.addView(vidview);
                str = FileUtil.getExternalStorageDir().concat("/MTG MODS/.app/video.mp4");
                if (settings.getString("sound", "").contains("false")) {
                    _mute();
                }
                if (dial.getString("dialog", "").contains("yes")) {

                }
                else {
                    if (settings.getString("language", "").contains("english")) {
                        d.setTitle("Attention!");
                        d.setMessage("If you're having trouble playing a video in the background, disable it in the app's settings.\n\nP.S. You will never see this message again.");
                        d.setIcon(R.drawable.ic_report_problem_white);
                        d.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface _dialog, int _which) {
                                dial.edit().putString("dialog", "yes").commit();
                            }
                        });
                        d.create().show();
                    }
                    else {
                        d.setTitle("Внимание!");
                        d.setMessage("Если у вас наблюдаются проблемы с воспроизведением видеоролика на фоне, отключите его в настройках приложения.\n\nP.S. Это сообщение вы больше никогда не увидите.");
                        d.setIcon(R.drawable.ic_report_problem_white);
                        d.setPositiveButton("Хорошо", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface _dialog, int _which) {
                                dial.edit().putString("dialog", "yes").commit();
                            }
                        });
                        d.create().show();
                    }
                }
            } else {
                first.setVisibility(View.GONE);
            }

            if (settings.getString("ads", "").contains("false")) {
                linear72.setVisibility(View.VISIBLE);
            } else {
                UnityAds.initialize(MenuActivity.this, GameID, TestMode, new IUnityAdsInitializationListener() {
                    @Override
                    public void onInitializationComplete() {

                    }

                    @Override
                    public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

                    }
                });
            }

            if (settings.getString("language", "").contains("english")) {
                textview9.setText("Hangars");
                textview10.setText("Sights");
                textview90.setText("Add new mod");
                textview14.setText("Skins");
                textview15.setText("Remodeling");
                textview12.setText("Six\nSense");
                textview17.setText("Interface");
                textview18.setText("Sounds \nmods");
                textview16.setText("Other\nmods");
                textview21.setText("Modpacks");
                textview11.setText("Mods");
                textview19.setText("Narodnaya\nSolyanka");
                textview28.setText("Settings");
                textview26.setText("Launch game");
                textview25.setText("   Mods   ");
                textview24.setText("Watch ads");
                textview30.setText("About the app");
                textview33.setText("Additionally ");
                textview34.setText("(this will help me) ");
            }

            if (vip.getString("vip", "").contains("key")) {
                if (settings.getString("language", "").contains("russian")) {
                    linear51.setVisibility(View.VISIBLE);
                }
                else {
                    linear51.setVisibility(View.GONE);
                }
            } else {
                linear51.setVisibility(View.GONE);
            }

        }
        else {
            network.edit().putString("network", "other").commit();
            i.setClass(getApplicationContext(), NoNetworkActivity.class);
            startActivity(i);
            overridePendingTransition(0, 0);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            login_user.edit().putString("login", "yes").commit();
        } else {
            login_user.edit().putString("login", "no").commit();
        }
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        linear11.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
        linear90.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
        linear13.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
        linear29.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
        linear31.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
        linear20.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
        linear34.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
        linear43.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
        linear44.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
        linear49.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
        linear50.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
        linear52.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
        linear56.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
        linear71.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
        if (settings.getString("video_background", "").contains("true")) {
            vidview.setVideoURI(Uri.parse(str));
            vidview.start();
            vidview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { @Override public void onCompletion(MediaPlayer mp) {
                //do anything when video played
                vidview.start();
            } });
        }
    }

    @Override
    public void onBackPressed() {

    }
    public void _extra () {
    }
    VideoView vidview;
    MediaController mediaControls;
    {
    }

    public void _open_app (final String _app) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage(_app);
        startActivity(launchIntent);
        overridePendingTransition(0, 0);
    }

    public void _mute () {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);

    }

    AudioManager audioManager;

    private void nothing() {

    }

    public void _play () {

        textview26.setTextColor(0xFF2196F3);
        textview25.setTextColor(0xFFFFFFFF);
        imageview9.setImageResource(R.drawable.button_image_1);
        imageview10.setImageResource(R.drawable.ic_apps_white);

        if (settings.getString("language", "").contains("english")) {

            int ui_flags =
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            AlertDialog.Builder alert = new AlertDialog.Builder(MenuActivity.this);
            View dialog_play = getLayoutInflater().inflate(R.layout.play_en,null);
            alert.setView(dialog_play);
            final AlertDialog dialog = alert.create ();
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
            gd.setColor(Color.parseColor("#36393E"));
            gd.setCornerRadius(80);
            dialog.getWindow().getDecorView().setBackground(gd);
            dialog.getWindow().
                    setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            dialog.getWindow().getDecorView().setSystemUiVisibility(ui_flags);
            dialog.show();
            dialog.getWindow().
                    clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            final Button yes = dialog_play.findViewById(R.id.button1);
            final Button no = dialog_play.findViewById(R.id.button2);

            yes.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick( View dialogmain){
                    dialog.dismiss();
                    textview25.setTextColor(0xFF2196F3);
                    textview26.setTextColor(0xFFFFFFFF);
                    imageview9.setImageResource(R.drawable.ic_play_circle_outline_white);
                    imageview10.setImageResource(R.drawable.button_image_2);
                    _open_app("net.wargaming.wot.blitz");
                }
            });

            no.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick( View dialogmain){
                    dialog.dismiss();
                    textview25.setTextColor(0xFF2196F3);
                    textview26.setTextColor(0xFFFFFFFF);
                    imageview9.setImageResource(R.drawable.ic_play_circle_outline_white);
                    imageview10.setImageResource(R.drawable.button_image_2);

                }
            });

        } else {

            int ui_flags =
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            AlertDialog.Builder alert = new AlertDialog.Builder(MenuActivity.this);
            View dialog_play = getLayoutInflater().inflate(R.layout.play,null);
            alert.setView(dialog_play);
            final AlertDialog dialog = alert.create ();
            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
            gd.setColor(Color.parseColor("#36393E"));
            gd.setCornerRadius(80);
            dialog.getWindow().getDecorView().setBackground(gd);
            dialog.getWindow().
                    setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            dialog.getWindow().getDecorView().setSystemUiVisibility(ui_flags);
            dialog.show();
            dialog.getWindow().
                    clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
            final Button yes = dialog_play.findViewById(R.id.button1);
            final Button no = dialog_play.findViewById(R.id.button2);

            yes.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick( View dialogmain){
                    dialog.dismiss();
                    textview25.setTextColor(0xFF2196F3);
                    textview26.setTextColor(0xFFFFFFFF);
                    imageview9.setImageResource(R.drawable.ic_play_circle_outline_white);
                    imageview10.setImageResource(R.drawable.button_image_2);
                    _open_app("net.wargaming.wot.blitz");
                }
            });

            no.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick( View dialogmain){
                    dialog.dismiss();
                    textview25.setTextColor(0xFF2196F3);
                    textview26.setTextColor(0xFFFFFFFF);
                    imageview9.setImageResource(R.drawable.ic_play_circle_outline_white);
                    imageview10.setImageResource(R.drawable.button_image_2);

                }
            });
        }

    }

    public void _ads_video () {

        if (settings.getString("language", "").contains("english")) {
            Toast.makeText(this, "Loading ads...\nIf the ad does not appear, click again after 10 seconds", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Загрузка рекламы...\nЕсли реклама не появилась, нажмите еще раз через 10 секунд", Toast.LENGTH_SHORT).show();
        }
        IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
            @Override
            public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                UnityAds.load(ADID);
                UnityAds.show(MenuActivity.this, ADID);
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
        UnityAds.show(MenuActivity.this, ADID);

    }
}
