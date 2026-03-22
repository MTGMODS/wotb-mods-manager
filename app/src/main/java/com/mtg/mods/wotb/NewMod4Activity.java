package com.mtg.mods.wotb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;

import java.io.File;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class NewMod4Activity extends AppCompatActivity {


    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

    private HashMap<String, Object> verMap = new HashMap<>();

    private ArrayList<HashMap<String, Object>> map = new ArrayList<>();

    private LinearLayout linear1;
    private TextView textview1;
    private TextView textview2;

    private Intent i = new Intent();
    private DatabaseReference fdb = _firebase.getReference("Mods/Waiting");
    private ChildEventListener _fdb_child_listener;
    private SharedPreferences mod;
    private SharedPreferences settings;
    private Calendar cl = Calendar.getInstance();

    String GameID = "4595401";
    String ADID = "Interstitial_Android";
    Boolean TestMode = false;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_new_mod4);
        initialize(_savedInstanceState);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initializeLogic();


        textview2.setVisibility(View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        textview2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
    }

    private void initialize(Bundle _savedInstanceState) {

        settings = getSharedPreferences("settings", Activity.MODE_PRIVATE);

        linear1 = (LinearLayout) findViewById(R.id.linear1);
        textview1 = (TextView) findViewById(R.id.textview1);
        textview2 = (TextView) findViewById(R.id.textview2);
        mod = getSharedPreferences("mod", Activity.MODE_PRIVATE);

        textview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {

                i.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);

            }
        });

        if (settings.getString("ads", "").contains("false")) {

        }
        else {
            UnityAds.initialize(NewMod4Activity.this, GameID, TestMode, new IUnityAdsInitializationListener() {
                @Override
                public void onInitializationComplete() {

                }

                @Override
                public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

                }
            });
        }

        _fdb_child_listener = new ChildEventListener() {
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
        fdb.addChildEventListener(_fdb_child_listener);
    }

    private void initializeLogic() {
        cl = Calendar.getInstance();
        if (mod.getString("category", "").equals("7")) {

            fdb.addListenerForSingleValueEvent(new ValueEventListener() {
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
                    verMap = new HashMap<>();
                    verMap.put("mod", mod.getString("mod", ""));
                    verMap.put("author", mod.getString("author", ""));
                    verMap.put("category", mod.getString("category", ""));
                    verMap.put("video", mod.getString("video", ""));
                    verMap.put("file", mod.getString("file", ""));
                    verMap.put("date", new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime()));
                    fdb.push().updateChildren(verMap);
                    textview2.setVisibility(View.VISIBLE);
                    textview1.setTextSize(18);
                    textview1.setText("Мод отправлен на рассмотрение!\nВ скором времени он появится в приложении");
                    _ads();
                }
                @Override
                public void onCancelled(DatabaseError _databaseError) {
                }
            });
        }
        else {

            fdb.addListenerForSingleValueEvent(new ValueEventListener() {
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
                    verMap = new HashMap<>();
                    verMap.put("mod", mod.getString("mod", ""));
                    verMap.put("author", mod.getString("author", ""));
                    verMap.put("category", mod.getString("category", ""));
                    verMap.put("image", mod.getString("image", ""));
                    verMap.put("file", mod.getString("file", ""));
                    verMap.put("date", new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime()));
                    fdb.push().updateChildren(verMap);
                    textview2.setVisibility(View.VISIBLE);
                    textview1.setTextSize(18);
                    textview1.setText("Мод отправлен на рассмотрение!\nВ скором времени он появится в приложении");
                    _ads();
                }
                @Override
                public void onCancelled(DatabaseError _databaseError) {
                }
            });

        }
    }


    private void _ads() {

        if (settings.getString("ads", "").contains("false")) {

        }
        else {
            IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
                @Override
                public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                    UnityAds.load(ADID);
                    UnityAds.show(NewMod4Activity.this, ADID);
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
            UnityAds.show(NewMod4Activity.this, ADID);
        }

    }

}
