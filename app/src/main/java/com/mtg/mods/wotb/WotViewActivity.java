package com.mtg.mods.wotb;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


public class WotViewActivity extends AppCompatActivity {
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

    private String video = "";

    private ArrayList<HashMap<String, Object>> info = new ArrayList<>();

    private LinearLayout background;
    private LinearLayout first;
    private LinearLayout content;
    private LinearLayout linear1;
    private TextView textview1;
    private TextView textview2;
    private YouTubePlayerView youtube1;
    private TextView textview4;
    private TextView textview5;

    private DatabaseReference fbs = _firebase.getReference("Modpacks");
    private ChildEventListener _fbs_child_listener;
    private Intent i = new Intent();
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.wot_view);
        com.google.firebase.FirebaseApp.initializeApp(this);

        background = (LinearLayout) findViewById(R.id.background);
        first = (LinearLayout) findViewById(R.id.first);
        content = (LinearLayout) findViewById(R.id.content);
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        textview1 = (TextView) findViewById(R.id.textview1);
        textview2 = (TextView) findViewById(R.id.textview2);
        youtube1 = (YouTubePlayerView) findViewById(R.id.youtube1);
        textview4 = (TextView) findViewById(R.id.textview4);
        textview5 = (TextView) findViewById(R.id.textview5);
        settings = getSharedPreferences("settings", Activity.MODE_PRIVATE);

        textview4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {

            }
        });

        textview5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
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

        if (SketchwareUtil.isConnected(getApplicationContext())) {
            first.setVisibility(View.GONE);
            fbs.addListenerForSingleValueEvent(new ValueEventListener() {
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
                    video = info.get((int)1).get("video").toString();
                    youtube1.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                        @Override
                        public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                            String videoId = video;
                            youTubePlayer.cueVideo(videoId, 0);
                            getLifecycle().addObserver(youtube1);
                        }
                    });
                }
                @Override
                public void onCancelled(DatabaseError _databaseError) {
                }
            });
            if (settings.getString("language", "").contains("english")) {
                textview1.setText("Modpack \n\"WOT MODPACK\"");
                textview2.setText("Modpack overview:");
                textview4.setText("  Start installation  ");
                textview5.setText("  Back  ");
            }
        }
        else {
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
        textview4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
        textview5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
    }

    public void _TransationManager (final View _view, final String _transitionName, final Intent _intent) {
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        _view.setTransitionName(_transitionName); android.app.ActivityOptions optionsCompat = android.app.ActivityOptions.makeSceneTransitionAnimation(WotViewActivity.this, _view, _transitionName); startActivity(_intent, optionsCompat.toBundle());
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
