package com.mtg.mods.wotb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class MiniGameActivity extends AppCompatActivity {
    private Timer _timer = new Timer();
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

    private double random = 0;
    private double winners = 0;
    private HashMap<String, Object> variable_map = new HashMap<>();
    private String tempcode = "";
    private String temp1 = "";


    private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> map2 = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> map3 = new ArrayList<>();

    private LinearLayout linear1;
    private TextView textview1;
    private Button button2;
    private Button button1;
    private LinearLayout linear2;
    private TextView textview3;
    private Button button3;
    private Button button5;
    private Button button4;
    private LinearLayout linear3;

    private Intent i = new Intent();
    private AlertDialog.Builder d;
    private TimerTask t;
    private DatabaseReference fbs = _firebase.getReference("Randomaizer");
    private ChildEventListener _fbs_child_listener;
    private DatabaseReference fbs3 = _firebase.getReference("Randomaizer/tempcode");
    private ChildEventListener _fbs3_child_listener;
    private Calendar cl = Calendar.getInstance();
    private SharedPreferences network;
    private SharedPreferences settings;
    private SharedPreferences temp;

    String GameID = "4595401";
    String ADID = "Interstitial_Android";
    Boolean TestMode = false;


    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.mini_game);
        initialize(_savedInstanceState);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initializeLogic();

        UnityAds.initialize(MiniGameActivity.this, GameID, TestMode, new IUnityAdsInitializationListener() {
            @Override
            public void onInitializationComplete() {

            }

            @Override
            public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

            }
        });
    }


    private void initialize(Bundle _savedInstanceState) {
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        textview1 = (TextView) findViewById(R.id.textview1);
        button2 = (Button) findViewById(R.id.button2);
        button1 = (Button) findViewById(R.id.button1);
        linear2 = (LinearLayout) findViewById(R.id.linear2);
        textview3 = (TextView) findViewById(R.id.textview3);
        button3 = (Button) findViewById(R.id.button3);
        button5 = (Button) findViewById(R.id.button5);
        button4 = (Button) findViewById(R.id.button4);
        linear3 = (LinearLayout) findViewById(R.id.linear3);
        d = new AlertDialog.Builder(this);
        network = getSharedPreferences("network", Activity.MODE_PRIVATE);
        settings = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        temp = getSharedPreferences("temptemp", Activity.MODE_PRIVATE);

        textview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                textview1.setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
                button1.setVisibility(View.GONE);
                button5.setVisibility(View.GONE);
                linear2.setVisibility(View.VISIBLE);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {

            }
        });

        button3.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View _view) {

                return true;
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (temp.getString("temp", "").contains("0")) {
                    temp.edit().putString("temp", "1").commit();
                    _click();
                }
                else {
                    if (temp.getString("temp", "").contains("1")) {
                        temp.edit().putString("temp", "2").commit();
                        _click();
                    }
                    else {
                        if (temp.getString("temp", "").contains("2")) {
                            temp.edit().putString("temp", "3").commit();
                            _click();
                        }
                        else {
                            if (temp.getString("temp", "").contains("3")) {
                                temp.edit().putString("temp", "4").commit();
                                _click();
                            }
                            else {
                                if (temp.getString("temp", "").contains("4")) {
                                    temp.edit().putString("temp", "5").commit();
                                    _click();
                                } else {
                                    if (temp.getString("temp", "").contains("5")) {
                                        temp.edit().putString("temp", "0").commit();
                                        _click();
                                        _ads_video();
                                    } else {
                                        temp.edit().putString("temp", "0").commit();
                                        _click();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                button3.setVisibility(View.GONE);
                textview3.setVisibility(View.GONE);
                cl = Calendar.getInstance();
                button5.setText(Build.MANUFACTURER.concat(" ".concat(Build.MODEL)));
                variable_map = new HashMap<>();
                variable_map.put("date", new SimpleDateFormat("hh:mm dd.MM.yyyy").format(cl.getTime()));
                variable_map.put("time", temp1.concat(" , ".concat(new SimpleDateFormat("hh:mm:ss").format(cl.getTime()))));
                variable_map.put("phone", button5.getText().toString());
                fbs3.push().updateChildren(variable_map);
                d.setTitle("Поздравляю!");
                d.setMessage("Теперь вся реклама в приложении отключена.");
                d.setPositiveButton("Хорошо", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {
                        i.setClass(getApplicationContext(), SettingsActivity.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                    }
                });
                d.create().show();
                settings.edit().putString("ads", "false").commit();
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), SettingsActivity.class);
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

        _fbs3_child_listener = new ChildEventListener() {
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
        fbs3.addChildEventListener(_fbs3_child_listener);
    }

    private void initializeLogic() {
        if (SketchwareUtil.isConnected(getApplicationContext())) {
            d = new AlertDialog.Builder(this,AlertDialog.THEME_TRADITIONAL);
            d.setCancelable(false);
            linear2.setVisibility(View.GONE);
            cl = Calendar.getInstance();
            temp1 = new SimpleDateFormat("hh:mm:ss").format(cl.getTime());
        }
        else {
            network.edit().putString("network", "game").commit();
            i.setClass(getApplicationContext(), NoNetworkActivity.class);
            startActivity(i);
            overridePendingTransition(0, 0);
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
    public void onStart() {
        super.onStart();
        button1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFF9800, 0xFFFF9800));
        button2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFF9800, 0xFFFF9800));
        button3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFF9800, 0xFFFF9800));
        button4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFF9800, 0xFFFF9800));
        button5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFF9800, 0xFFFF9800));
    }
    public void _click () {
        random = SketchwareUtil.getRandom((int)(1), (int)(10000));
        if (random > 9994) {
            if (SketchwareUtil.isConnected(getApplicationContext())) {
                textview3.setText("Выпало число: ".concat(String.valueOf((long)(random))));
                button3.setVisibility(View.GONE);
                button4.setVisibility(View.GONE);
                button5.setVisibility(View.VISIBLE);
            } else {
                network.edit().putString("network", "game").commit();
                i.setClass(getApplicationContext(), NoNetworkActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        }
        else {
            _pause();
        }
    }


    public void _pause () {
        button3.setVisibility(View.GONE);
        textview3.setText("Выпало число: ".concat(String.valueOf((long)(random)).concat("\nПауза: 5 секунд")));
        t = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textview3.setText("Выпало число: ".concat(String.valueOf((long)(random)).concat("\nПауза: 4 секунды")));
                        t = new TimerTask() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        textview3.setText("Выпало число: ".concat(String.valueOf((long)(random)).concat("\nПауза: 3 секунды")));
                                        t = new TimerTask() {
                                            @Override
                                            public void run() {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        textview3.setText("Выпало число: ".concat(String.valueOf((long)(random)).concat("\nПауза: 2 секунды")));
                                                        t = new TimerTask() {
                                                            @Override
                                                            public void run() {
                                                                runOnUiThread(new Runnable() {
                                                                    @Override
                                                                    public void run() {
                                                                        textview3.setText("Выпало число: ".concat(String.valueOf((long)(random)).concat("\nПауза: 1 секунда")));
                                                                        t = new TimerTask() {
                                                                            @Override
                                                                            public void run() {
                                                                                runOnUiThread(new Runnable() {
                                                                                    @Override
                                                                                    public void run() {
                                                                                        textview3.setText("Выпало число: ".concat(String.valueOf((long)(random))));
                                                                                        button3.setVisibility(View.VISIBLE);
                                                                                    }
                                                                                });
                                                                            }
                                                                        };
                                                                        _timer.schedule(t, (int)(1000));
                                                                    }
                                                                });
                                                            }
                                                        };
                                                        _timer.schedule(t, (int)(1000));
                                                    }
                                                });
                                            }
                                        };
                                        _timer.schedule(t, (int)(1000));
                                    }
                                });
                            }
                        };
                        _timer.schedule(t, (int)(1000));
                    }
                });
            }
        };
        _timer.schedule(t, (int)(1000));
    }


    public void _ads_video () {
        IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
            @Override
            public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                UnityAds.load(ADID);
                UnityAds.show(MiniGameActivity.this, ADID);
            }

            @Override
            public void onUnityAdsShowStart(String s) {

            }

            @Override
            public void onUnityAdsShowClick(String s) {

            }

            @Override
            public void onUnityAdsShowComplete(String s, UnityAds.UnityAdsShowCompletionState state) {
                if (state.equals(UnityAds.UnityAdsShowCompletionState.COMPLETED)) {
                    Toast.makeText(MiniGameActivity.this, "Спасибо за просмотр", Toast.LENGTH_SHORT).show();
                }
                else if (state.equals(UnityAds.UnityAdsShowCompletionState.SKIPPED)) {
                    Toast.makeText(MiniGameActivity.this, "Вы пропустили!", Toast.LENGTH_SHORT).show();
                }

            }
        };
        UnityAds.load(ADID);
        UnityAds.show(MiniGameActivity.this, ADID);
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
