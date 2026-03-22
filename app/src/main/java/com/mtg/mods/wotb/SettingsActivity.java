package com.mtg.mods.wotb;

import static android.os.Build.VERSION.SDK_INT;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SettingsActivity extends AppCompatActivity {
    private Timer _timer = new Timer();
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

    private Uri muri;
    private String str = "";
    private String vip_key_check = "";
    private String vip_work = "";
    private String ads_code = "";
    private String ads_code_firebase = "";
    private String buildd = "";
    private String assetFilename = "";
    private String assetSavePath = "";
    private String disable_ads_key = "";
    private String ads_work = "";

    private ArrayList<HashMap<String, Object>> map = new ArrayList<>();

    private LinearLayout background;
    private LinearLayout first;
    private LinearLayout content;
    private LinearLayout linear1;
    private ScrollView vscroll4;
    private LinearLayout linear36;
    private LinearLayout linear19;
    private LinearLayout linear2;
    private LinearLayout probel1;
    private LinearLayout linear8;
    private LinearLayout linear9;
    private LinearLayout linear14;
    private LinearLayout linear15;
    private TextView language;
    private LinearLayout linear4;
    private ImageView imageview1;
    private TextView russian;
    private ImageView imageview2;
    private TextView english;
    private TextView textview10;
    private LinearLayout linear12;
    private LinearLayout linear20;
    private ImageView imageview9;
    private TextView textview11;
    private ImageView imageview10;
    private TextView textview12;
    private TextView textview17;
    private LinearLayout linear24;
    private ImageView imageview17;
    private TextView textview19;
    private ImageView imageview18;
    private TextView textview20;
    private LinearLayout linear41;
    private TextView textview27;
    private ImageView imageview27;
    private LinearLayout linear30;
    private LinearLayout linear40;
    private TextView textview26;
    private LinearLayout linear42;
    private ImageView imageview26;
    private ImageView imageview29;
    private LinearLayout linear16;
    private TextView textview14;
    private EditText edittext1;
    private TextView textview15;
    private LinearLayout linear37;
    private LinearLayout linear38;
    private LinearLayout linear39;
    private ImageView imageview22;
    private TextView textview25;
    private ImageView imageview23;
    private TextView textview24;
    private ImageView imageview24;
    private TextView textview23;

    private Intent i = new Intent();
    private ProgressDialog pd;
    private TimerTask t;
    private SharedPreferences settings;
    private SharedPreferences vip;
    private DatabaseReference fbs = _firebase.getReference("/");
    private ChildEventListener _fbs_child_listener;
    private AlertDialog.Builder d;
    private SharedPreferences installer;
    private ProgressDialog qq;
    private AlertDialog.Builder abc;
    private AlertDialog.Builder bb;
    private AlertDialog.Builder n;
    private AlertDialog.Builder o;
    private AlertDialog.Builder q;
    private SharedPreferences network;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.settings);
        initialize(_savedInstanceState);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initializeLogic();

    }


    private void initialize(Bundle _savedInstanceState) {
        background = (LinearLayout) findViewById(R.id.background);
        first = (LinearLayout) findViewById(R.id.first);
        content = (LinearLayout) findViewById(R.id.content);
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        vscroll4 = (ScrollView) findViewById(R.id.vscroll4);
        linear36 = (LinearLayout) findViewById(R.id.linear36);
        linear19 = (LinearLayout) findViewById(R.id.linear19);
        linear2 = (LinearLayout) findViewById(R.id.linear2);
        probel1 = (LinearLayout) findViewById(R.id.probel1);
        linear8 = (LinearLayout) findViewById(R.id.linear8);
        linear9 = (LinearLayout) findViewById(R.id.linear9);
        linear14 = (LinearLayout) findViewById(R.id.linear14);
        linear15 = (LinearLayout) findViewById(R.id.linear15);
        language = (TextView) findViewById(R.id.language);
        linear4 = (LinearLayout) findViewById(R.id.linear4);
        imageview1 = (ImageView) findViewById(R.id.imageview1);
        russian = (TextView) findViewById(R.id.russian);
        imageview2 = (ImageView) findViewById(R.id.imageview2);
        english = (TextView) findViewById(R.id.english);
        textview10 = (TextView) findViewById(R.id.textview10);
        linear12 = (LinearLayout) findViewById(R.id.linear12);
        linear20 = (LinearLayout) findViewById(R.id.linear20);
        imageview9 = (ImageView) findViewById(R.id.imageview9);
        textview11 = (TextView) findViewById(R.id.textview11);
        imageview10 = (ImageView) findViewById(R.id.imageview10);
        textview12 = (TextView) findViewById(R.id.textview12);
        textview17 = (TextView) findViewById(R.id.textview17);
        linear24 = (LinearLayout) findViewById(R.id.linear24);
        imageview17 = (ImageView) findViewById(R.id.imageview17);
        textview19 = (TextView) findViewById(R.id.textview19);
        imageview18 = (ImageView) findViewById(R.id.imageview18);
        textview20 = (TextView) findViewById(R.id.textview20);
        linear41 = (LinearLayout) findViewById(R.id.linear41);
        textview27 = (TextView) findViewById(R.id.textview27);
        imageview27 = (ImageView) findViewById(R.id.imageview27);
        linear30 = (LinearLayout) findViewById(R.id.linear30);
        linear40 = (LinearLayout) findViewById(R.id.linear40);
        textview26 = (TextView) findViewById(R.id.textview26);
        linear42 = (LinearLayout) findViewById(R.id.linear42);
        imageview26 = (ImageView) findViewById(R.id.imageview26);
        imageview29 = (ImageView) findViewById(R.id.imageview29);
        linear16 = (LinearLayout) findViewById(R.id.linear16);
        textview14 = (TextView) findViewById(R.id.textview14);
        edittext1 = (EditText) findViewById(R.id.edittext1);
        textview15 = (TextView) findViewById(R.id.textview15);
        linear37 = (LinearLayout) findViewById(R.id.linear37);
        linear38 = (LinearLayout) findViewById(R.id.linear38);
        linear39 = (LinearLayout) findViewById(R.id.linear39);
        imageview22 = (ImageView) findViewById(R.id.imageview22);
        textview25 = (TextView) findViewById(R.id.textview25);
        imageview23 = (ImageView) findViewById(R.id.imageview23);
        textview24 = (TextView) findViewById(R.id.textview24);
        imageview24 = (ImageView) findViewById(R.id.imageview24);
        textview23 = (TextView) findViewById(R.id.textview23);
        settings = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        vip = getSharedPreferences("vip", Activity.MODE_PRIVATE);
        d = new AlertDialog.Builder(this);
        installer = getSharedPreferences("installer", Activity.MODE_PRIVATE);
        abc = new AlertDialog.Builder(this);
        bb = new AlertDialog.Builder(this);
        n = new AlertDialog.Builder(this);
        o = new AlertDialog.Builder(this);
        q = new AlertDialog.Builder(this);
        network = getSharedPreferences("network ", Activity.MODE_PRIVATE);
        linear15.setVisibility(View.GONE);
        imageview29.setVisibility(View.GONE);

        imageview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                settings.edit().remove("language").commit();
                settings.edit().putString("language", "russian").commit();
                imageview1.setImageResource(R.drawable.ic_radio_button_on_white);
                imageview2.setImageResource(R.drawable.ic_radio_button_off_white);
                language.setText("Язык интерфейса");
                textview10.setText("Видео на фоне");
                textview11.setText("Включено");
                textview12.setText("Отключено");
                textview19.setText("Включено");
                textview20.setText("Отключено");
                textview17.setText("Звук");
                textview25.setText("   Моды   ");
                textview24.setText("  Играть  ");
                textview23.setText("Настройки");
                textview27.setText("Удаление модов");
                textview26.setText("Отключение рекламы ");
                imageview29.setVisibility(View.VISIBLE);
            }
        });

        imageview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                settings.edit().remove("language").commit();
                settings.edit().putString("language", "english").commit();
                linear15.setVisibility(View.GONE);
                imageview1.setImageResource(R.drawable.ic_radio_button_off_white);
                imageview2.setImageResource(R.drawable.ic_radio_button_on_white);
                language.setText("Interface language");
                textview10.setText("Video in background");
                textview17.setText("Sound");
                textview11.setText("Included");
                textview12.setText("Disabled");
                textview19.setText("Included");
                textview20.setText("Disabled");
                textview23.setText("Settings");
                textview25.setText("   Mods   ");
                textview24.setText("Launch game");
                textview27.setText("Deleting mods");
                textview26.setText("Disabling ads");
                imageview29.setVisibility(View.GONE);
            }
        });

        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {

            }
        });

        imageview9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (settings.getString("video_background", "").contains("true")) {

                }
                else {
                    settings.edit().putString("video_background", "true").commit();
                    imageview10.setImageResource(R.drawable.ic_radio_button_off_white);
                    imageview9.setImageResource(R.drawable.ic_radio_button_on_white);
                    linear20.setVisibility(View.VISIBLE);
                    i.setClass(getApplicationContext(), SettingsActivity.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                }
            }
        });

        imageview10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                settings.edit().remove("video_background").commit();
                settings.edit().putString("video_background", "false").commit();
                imageview9.setImageResource(R.drawable.ic_radio_button_off_white);
                imageview10.setImageResource(R.drawable.ic_radio_button_on_white);
                linear20.setVisibility(View.GONE);
                i.setClass(getApplicationContext(), SettingsActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        linear24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {

            }
        });

        imageview17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                settings.edit().remove("sound").commit();
                settings.edit().putString("sound", "true").commit();
                imageview18.setImageResource(R.drawable.ic_radio_button_off_white);
                imageview17.setImageResource(R.drawable.ic_radio_button_on_white);
                audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 100, 0);

            }

            AudioManager audioManager;

            private void nothing() {

            }
        });

        imageview18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                settings.edit().remove("sound").commit();
                settings.edit().putString("sound", "false").commit();
                imageview17.setImageResource(R.drawable.ic_radio_button_off_white);
                imageview18.setImageResource(R.drawable.ic_radio_button_on_white);
                _mute();
            }
        });

        imageview27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/Android/data/net.wargaming.wot.blitz/files/packs/dvpl_file_info_cache.txt"))) {

                    if (Build.VERSION.SDK_INT > 29) {

                        if (settings.getString("language", "").contains("english")) {

                            int ui_flags =
                                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                                            View.SYSTEM_UI_FLAG_FULLSCREEN |
                                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                            AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);
                            View dialog4 = getLayoutInflater().inflate(R.layout.delete_accept_en,null);
                            alert.setView(dialog4);
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
                            final Button btn3 = dialog4.findViewById(R.id.button1);
                            final Button btn4 = dialog4.findViewById(R.id.button2);

                            btn3.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick( View dialogmain){
                                    dialog.dismiss();

                                    muri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fnet.wargaming.wot.blitz%2Ffiles%2Fpacks/document/primary%3AAndroid%2Fdata%2Fnet.wargaming.wot.blitz%2Ffiles%2Fpacks%2Fdvpl_file_info_cache.txt");
                                    try{
                                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), muri);
                                        muri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fnet.wargaming.wot.blitz%2Ffiles%2Fpacks/document/primary%3AAndroid%2Fdata%2Fnet.wargaming.wot.blitz%2Ffiles%2Fpacks%2FUI");
                                        try{
                                            DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), muri);
                                            _delete_success();
                                        } catch (FileNotFoundException e) {
                                            _delete_success();
                                        }
                                    } catch (FileNotFoundException e) {
                                        SketchwareUtil.showMessage(getApplicationContext(), "Error: no access");
                                        _restore_dialog();
                                    }


                                }

                            });

                            btn4.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick( View dialogmain){
                                    dialog.dismiss();
                                }

                            });

                        }
                        else {

                            int ui_flags =
                                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                                            View.SYSTEM_UI_FLAG_FULLSCREEN |
                                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                            AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);
                            View dialog4 = getLayoutInflater().inflate(R.layout.delete_accept,null);
                            alert.setView(dialog4);
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
                            final Button btn3 = dialog4.findViewById(R.id.button1);
                            final Button btn4 = dialog4.findViewById(R.id.button2);

                            btn3.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick( View dialogmain){
                                    dialog.dismiss();

                                    muri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fnet.wargaming.wot.blitz%2Ffiles%2Fpacks/document/primary%3AAndroid%2Fdata%2Fnet.wargaming.wot.blitz%2Ffiles%2Fpacks%2Fdvpl_file_info_cache.txt");
                                    try{
                                        DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), muri);
                                        muri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fnet.wargaming.wot.blitz%2Ffiles%2Fpacks/document/primary%3AAndroid%2Fdata%2Fnet.wargaming.wot.blitz%2Ffiles%2Fpacks%2FUI");
                                        try{
                                            DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), muri);
                                            _delete_success();
                                        } catch (FileNotFoundException e) {
                                            _delete_success();
                                        }
                                    } catch (FileNotFoundException e) {
                                        SketchwareUtil.showMessage(getApplicationContext(), "Ошибка: нет доступа");
                                        _restore_dialog();
                                    }


                                }

                            });

                            btn4.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick( View dialogmain){
                                    dialog.dismiss();
                                }

                            });

                        }

                    } else {

                        if (settings.getString("language", "").contains("english")) {

                            AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);
                            View dialog6 = getLayoutInflater().inflate(R.layout.delete_accept_en,null);
                            alert.setView(dialog6);
                            final AlertDialog dialog = alert.create ();
                            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                            android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
                            gd.setColor(Color.parseColor("#36393E"));
                            gd.setCornerRadius(80);
                            dialog.getWindow().getDecorView().setBackground(gd);
                            dialog.show();
                            final Button btn3 = dialog6.findViewById(R.id.button1);
                            final Button btn4 = dialog6.findViewById(R.id.button2);

                            btn3.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick( View dialogmain){
                                    dialog.dismiss();
                                    FileUtil.deleteFile(FileUtil.getExternalStorageDir().concat("/Android/data/net.wargaming.wot.blitz/files/packs/UI"));
                                    FileUtil.deleteFile(FileUtil.getExternalStorageDir().concat("/Android/data/net.wargaming.wot.blitz/files/packs/dvpl_file_info_cache.txt"));
                                    _delete_success();
                                }

                            });

                            btn4.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick( View dialogmain){
                                    dialog.dismiss();
                                }

                            });
                        }
                        else {

                            AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);
                            View dialog4 = getLayoutInflater().inflate(R.layout.delete_accept,null);
                            alert.setView(dialog4);
                            final AlertDialog dialog = alert.create ();
                            dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                            android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
                            gd.setColor(Color.parseColor("#36393E"));
                            gd.setCornerRadius(80);
                            dialog.getWindow().getDecorView().setBackground(gd);
                            dialog.show();
                            final Button btn3 = dialog4.findViewById(R.id.button1);
                            final Button btn4 = dialog4.findViewById(R.id.button2);

                            btn3.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick( View dialogmain){
                                    dialog.dismiss();
                                    FileUtil.deleteFile(FileUtil.getExternalStorageDir().concat("/Android/data/net.wargaming.wot.blitz/files/packs/UI"));
                                    FileUtil.deleteFile(FileUtil.getExternalStorageDir().concat("/Android/data/net.wargaming.wot.blitz/files/packs/dvpl_file_info_cache.txt"));
                                    _delete_success();
                                }

                            });

                            btn4.setOnClickListener(new View.OnClickListener(){
                                @Override
                                public void onClick( View dialogmain){
                                    dialog.dismiss();
                                }

                            });


                        }

                    }

                }
                else {
                    _restore_dialog();
                }

            }
        });

        textview26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {

            }
        });

        imageview26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {

                if (settings.getString("language", "").contains("russian")) {

                    int ui_flags =
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                                    View.SYSTEM_UI_FLAG_FULLSCREEN |
                                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                    AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);
                    View dialog1 = getLayoutInflater().inflate(R.layout.custom_dialog,null);
                    alert.setView(dialog1);
                    final AlertDialog dialog = alert.create ();
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
                    gd.setColor(Color.parseColor("#000000"));
                    gd.setCornerRadius(80);
                    dialog.getWindow().getDecorView().setBackground(gd);
                    dialog.getWindow().
                            setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                    dialog.getWindow().getDecorView().setSystemUiVisibility(ui_flags);
                    dialog.show();
                    dialog.getWindow().
                            clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                    final EditText entertext = (EditText) dialog1.findViewById(R.id.edittext1);
                    final Button save = dialog1.findViewById(R.id.button1);
                    final ImageView img =(ImageView) dialog1.findViewById(R.id.imageview1);
                    final LinearLayout linear = (LinearLayout) dialog1.findViewById (R.id.linear1);
                    entertext.setFocusableInTouchMode(true);
                    entertext.addTextChangedListener(new TextWatcher() {

                        @Override

                        public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

                            final String _charSeq = _param1.toString();

                            ///code


                        }



                        @Override

                        public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {



                        }



                        @Override

                        public void afterTextChanged(Editable _param1) {



                        }

                    });
                    save.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick( View dialogmain){
                            dialog.dismiss();
                            ads_code = entertext.getText().toString();
                            if (ads_code.contains(ads_code_firebase)) {

                                linear30.setVisibility(View.GONE);

                                settings.edit().remove("ads").commit();

                                settings.edit().putString("ads", "false").commit();
                                SketchwareUtil.showMessage(getApplicationContext(), "Поздравляю, реклама отключена!");

                                i.setClass(getApplicationContext(), SettingsActivity.class);
                                startActivity(i);
                                overridePendingTransition(0, 0);

                            }

                            else {

                                SketchwareUtil.showMessage(getApplicationContext(), "Данный код не действителен!");

                            }
                        }

                    });
                }
                if (settings.getString("language", "").contains("english")) {

                    int ui_flags =
                            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                                    View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                                    View.SYSTEM_UI_FLAG_FULLSCREEN |
                                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
                    AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);
                    View dialog1 = getLayoutInflater().inflate(R.layout.custom_dialog5,null);
                    alert.setView(dialog1);
                    final AlertDialog dialog = alert.create ();
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                    android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
                    gd.setColor(Color.parseColor("#000000"));
                    gd.setCornerRadius(80);
                    dialog.getWindow().getDecorView().setBackground(gd);
                    dialog.getWindow().
                            setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                                    WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                    dialog.getWindow().getDecorView().setSystemUiVisibility(ui_flags);
                    dialog.show();
                    dialog.getWindow().
                            clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
                    final EditText entertext = (EditText) dialog1.findViewById(R.id.edittext1);
                    final Button save = dialog1.findViewById(R.id.button1);
                    final ImageView img =(ImageView) dialog1.findViewById(R.id.imageview1);
                    final LinearLayout linear = (LinearLayout) dialog1.findViewById (R.id.linear1);
                    entertext.setFocusableInTouchMode(true);
                    entertext.addTextChangedListener(new TextWatcher() {

                        @Override

                        public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

                            final String _charSeq = _param1.toString();

                            ///code


                        }



                        @Override

                        public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {



                        }



                        @Override

                        public void afterTextChanged(Editable _param1) {



                        }

                    });
                    save.setOnClickListener(new View.OnClickListener(){
                        @Override
                        public void onClick( View dialogmain){
                            dialog.dismiss();
                            ads_code = entertext.getText().toString();
                            if (ads_code.contains(ads_code_firebase)) {

                                linear30.setVisibility(View.GONE);

                                settings.edit().remove("ads").commit();

                                settings.edit().putString("ads", "false").commit();
                                SketchwareUtil.showMessage(getApplicationContext(), "Congratulations, ads are disabled!");

                                i.setClass(getApplicationContext(), SettingsActivity.class);
                                startActivity(i);
                                overridePendingTransition(0, 0);

                            }

                            else {

                                SketchwareUtil.showMessage(getApplicationContext(), "This code is not valid!");

                            }
                        }

                    });
                }

            }
        });

        imageview29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), MiniGameActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        edittext1.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
                final String _charSeq = _param1.toString();

            }

            @Override
            public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

            }

            @Override
            public void afterTextChanged(Editable _param1) {

            }
        });

        textview15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                pd = new ProgressDialog(SettingsActivity.this);
                pd.setTitle("Пожалуйста подождите...");
                pd.setMessage("Проверка данных...");
                pd.setMax((int)2);
                pd.setProgress((int)2);
                pd.show();
                t = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pd.dismiss();
                                if (edittext1.getText().toString().equals(vip_key_check)) {
                                    linear15.setVisibility(View.GONE);
                                    vip.edit().putString("vip", "key".concat(edittext1.getText().toString())).commit();
                                    o.setTitle("Поздравляю!");
                                    o.setIcon(R.drawable.ic_verified_user_white);
                                    o.setMessage("Теперь вам доступны VIP моды!");
                                    o.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface _dialog, int _which) {

                                        }
                                    });
                                    o.create().show();
                                }
                                else {
                                    o.setTitle("Ошибка!");
                                    o.setIcon(R.drawable.ic_report_problem_white);
                                    o.setMessage("Введённый вами ключ не действителен!");
                                    o.setPositiveButton("Ок", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface _dialog, int _which) {

                                        }
                                    });
                                    o.create().show();
                                }
                            }
                        });
                    }
                };
                _timer.schedule(t, (int)(2000));
            }
        });

        linear37.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        linear38.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                _play();
            }
        });

        imageview22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        textview25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        imageview23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                _play();
            }
        });

        textview24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                _play();
            }
        });

        _fbs_child_listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);

            }

            @Override
            public void onChildChanged(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);

            }

            @Override
            public void onChildMoved(DataSnapshot _param1, String _param2) {

            }

            @Override
            public void onChildRemoved(DataSnapshot _param1) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);

            }

            @Override
            public void onCancelled(DatabaseError _param1) {
                final int _errorCode = _param1.getCode();
                final String _errorMessage = _param1.getMessage();

            }
        };
        fbs.addChildEventListener(_fbs_child_listener);

    }

    private void _delete_success() {

        if (settings.getString("language", "").contains("english")) {
            int ui_flags =
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);
            View dialog5 = getLayoutInflater().inflate(R.layout.delete_success_en,null);
            alert.setView(dialog5);
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
            final Button btn5 = dialog5.findViewById(R.id.button1);

            btn5.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick( View dialogmain){
                    dialog.dismiss();
                    _open_app("net.wargaming.wot.blitz");
                }

            });
        }
        else {

            int ui_flags =
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);
            View dialog5 = getLayoutInflater().inflate(R.layout.delete_success,null);
            alert.setView(dialog5);
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
            final Button btn5 = dialog5.findViewById(R.id.button1);

            btn5.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick( View dialogmain){
                    dialog.dismiss();
                    _open_app("net.wargaming.wot.blitz");
                }

            });

        }

    }

    private void _restore_dialog() {

        if (settings.getString("language", "").contains("english")) {

            int ui_flags =
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);
            View dialog3 = getLayoutInflater().inflate(R.layout.restore_mods_en,null);
            alert.setView(dialog3);
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
            final Button btn2 = dialog3.findViewById(R.id.button1);
            btn2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick( View dialogmain){
                    dialog.dismiss();
                }

            });
        }
        else {
            int ui_flags =
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);
            View dialog2 = getLayoutInflater().inflate(R.layout.restore_mods,null);
            alert.setView(dialog2);
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
            final Button btn2 = dialog2.findViewById(R.id.button1);

            btn2.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick( View dialogmain){
                    dialog.dismiss();
                }

            });
        }
    }

    private void initializeLogic() {

        if (isConnected()) {

            if (SDK_INT >= Build.VERSION_CODES.R) {
                probel1.setVisibility(View.VISIBLE);
            }
            d = new AlertDialog.Builder(this,AlertDialog.THEME_TRADITIONAL);
            d.setCancelable(false);
            n = new AlertDialog.Builder(this,AlertDialog.THEME_TRADITIONAL);
            n.setCancelable(false);
            abc = new AlertDialog.Builder(this,AlertDialog.THEME_TRADITIONAL);
            abc.setCancelable(false);
            bb = new AlertDialog.Builder(this,AlertDialog.THEME_TRADITIONAL);
            bb.setCancelable(false);
            o = new AlertDialog.Builder(this,AlertDialog.THEME_TRADITIONAL);
            o.setCancelable(false);
            q = new AlertDialog.Builder(this,AlertDialog.THEME_TRADITIONAL);
            q.setCancelable(false);
            vscroll4.setHorizontalScrollBarEnabled(false);
            vscroll4.setVerticalScrollBarEnabled(false);
            vscroll4.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);

            if (settings.getString("ads", "").contains("false")) {
                linear30.setVisibility(View.GONE);
            }

            if (settings.getString("language", "").contains("english")) {
                imageview1.setImageResource(R.drawable.ic_radio_button_off_white);
                imageview2.setImageResource(R.drawable.ic_radio_button_on_white);
                language.setText("Interface language");
                textview10.setText("Video in background");
                textview17.setText("Sound");
                textview11.setText("Included");
                textview12.setText("Disabled");
                textview19.setText("Included");
                textview20.setText("Disabled");
                textview23.setText("Settings");
                textview25.setText("   Mods   ");
                textview24.setText("Launch game");
                textview27.setText("Deleting mods");
                textview26.setText("Disabling ads");
                imageview29.setVisibility(View.GONE);
            } else {
                imageview2.setImageResource(R.drawable.ic_radio_button_off_white);
                imageview1.setImageResource(R.drawable.ic_radio_button_on_white);
            }

            if (vip.getString("vip", "").contains("key")) {
                linear15.setVisibility(View.GONE);
            } else {

                fbs.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot _dataSnapshot) {
                        map = new ArrayList<>();
                        try {
                            GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                            for (DataSnapshot _data : _dataSnapshot.getChildren()) {
                                HashMap<String, Object> _map = _data.getValue(_ind);
                                map.add(_map);
                            }
                        }
                        catch (Exception _e) {
                            _e.printStackTrace();
                        }
                        vip_key_check = map.get((int)0).get("vip").toString();
                        vip_work = map.get((int)0).get("vip_work").toString();
                        if (vip_work.contains("1")) {
                            if (settings.getString("language", "").contains("russian")) {
                                linear15.setVisibility(View.VISIBLE);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError _databaseError) {
                    }
                });

            }

            if (settings.getString("video_background", "").contains("true")) {
                RelativeLayout rl = new RelativeLayout(this); RelativeLayout.LayoutParams lparams = new RelativeLayout.LayoutParams( RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT); rl.setLayoutParams(lparams); background.removeAllViews(); rl.addView(first); rl.addView(content); background.addView(rl);
                vidview = new VideoView(this);
                vidview.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                linear1.addView(vidview);
                str = FileUtil.getExternalStorageDir().concat("/MTG MODS/.app/video.mp4");
                imageview9.setImageResource(R.drawable.ic_radio_button_on_white);
                imageview10.setImageResource(R.drawable.ic_radio_button_off_white);
                if (settings.getString("sound", "").contains("false")) {
                    _mute();
                    imageview18.setImageResource(R.drawable.ic_radio_button_on_white);
                    imageview17.setImageResource(R.drawable.ic_radio_button_off_white);
                }
                else {
                    imageview17.setImageResource(R.drawable.ic_radio_button_on_white);
                    imageview18.setImageResource(R.drawable.ic_radio_button_off_white);
                }
            } else {
                first.setVisibility(View.GONE);
                linear20.setVisibility(View.GONE);
                imageview10.setImageResource(R.drawable.ic_radio_button_on_white);
                imageview9.setImageResource(R.drawable.ic_radio_button_off_white);
            }

        } else {
            network.edit().putString("network", "settings").commit();
            i.setClass(getApplicationContext(), NoNetworkActivity.class);
            startActivity(i);
            overridePendingTransition(0, 0);
        }

    }

    @Override
    public void onStart() {
        super.onStart();
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        if (settings.getString("video_background", "").contains("true")) {
            vidview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { @Override public void onCompletion(MediaPlayer mp) {
                //do anything when video played
                vidview.start();
            } });
            vidview.setVideoURI(Uri.parse(str));
            vidview.start();
        }
        textview15.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)90, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
        linear16.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)90, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
        linear40.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)90, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
        linear41.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)90, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
    }

    public void _extra () {
    }
    VideoView vidview;
    MediaController mediaControls;
    {
    }

    public void _mute () {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);

    }

    AudioManager audioManager;

    private void nothing() {

    }

    public void _open_app (final String _app) {
        Intent launchIntent = getPackageManager().getLaunchIntentForPackage(_app);
        startActivity(launchIntent);
        overridePendingTransition(0, 0);
    }

    public void _play () {
        imageview23.setImageResource(R.drawable.button_image_1);
        textview24.setTextColor(0xFF2196F3);
        textview23.setTextColor(0xFFFFFFFF);
        imageview24.setImageResource(R.drawable.ic_settings_white);

        if (settings.getString("language", "").contains("english")) {

            int ui_flags =
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);
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
                    textview23.setTextColor(0xFF2196F3);
                    imageview24.setImageResource(R.drawable.button_image_3);
                    textview24.setTextColor(0xFFFFFFFF);
                    imageview23.setImageResource(R.drawable.ic_play_circle_outline_white);
                    _open_app("net.wargaming.wot.blitz");
                }
            });

            no.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick( View dialogmain){
                    dialog.dismiss();
                    textview23.setTextColor(0xFF2196F3);
                    imageview24.setImageResource(R.drawable.button_image_3);
                    textview24.setTextColor(0xFFFFFFFF);
                    imageview23.setImageResource(R.drawable.ic_play_circle_outline_white);



                }
            });

        }
        else {

            int ui_flags =
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                            View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            AlertDialog.Builder alert = new AlertDialog.Builder(SettingsActivity.this);
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
                    textview23.setTextColor(0xFF2196F3);
                    imageview24.setImageResource(R.drawable.button_image_3);
                    textview24.setTextColor(0xFFFFFFFF);
                    imageview23.setImageResource(R.drawable.ic_play_circle_outline_white);
                    _open_app("net.wargaming.wot.blitz");
                }
            });

            no.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick( View dialogmain){
                    dialog.dismiss();
                    textview23.setTextColor(0xFF2196F3);
                    imageview24.setImageResource(R.drawable.button_image_3);
                    textview24.setTextColor(0xFFFFFFFF);
                    imageview23.setImageResource(R.drawable.ic_play_circle_outline_white);

                }
            });

        }

    }

    private boolean isConnected() {

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo!=null && networkInfo.isConnected();

    }


}
