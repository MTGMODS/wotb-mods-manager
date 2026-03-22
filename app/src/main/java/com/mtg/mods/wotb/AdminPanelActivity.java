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
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Button;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class AdminPanelActivity extends AppCompatActivity {
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

    private String stop = "";
    private HashMap<String, Object> map_stop = new HashMap<>();
    private String url = "";
    private HashMap<String, Object> map_reset = new HashMap<>();

    private ArrayList<HashMap<String, Object>> map1 = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> map2 = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> map3 = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> map4 = new ArrayList<>();

    private LinearLayout linear2;
    private ScrollView vscroll1;
    private LinearLayout linear1;
    private TextView textview7;
    private TextView textview6;
    private Button button4;
    private TextView textview4;
    private TextView textview2;
    private TextView textview3;
    private Button button5;
    private Button button6;
    private Button button1;
    private Button button2;
    private Button button3;

    private DatabaseReference fbs1 = _firebase.getReference("Launches");
    private ChildEventListener _fbs1_child_listener;
    private DatabaseReference fbs2 = _firebase.getReference("Manage");
    private ChildEventListener _fbs2_child_listener;
    private DatabaseReference fbs3 = _firebase.getReference("Users");
    private ChildEventListener _fbs3_child_listener;
    private DatabaseReference fbs4 = _firebase.getReference("UpdateCheck");
    private ChildEventListener _fbs4_child_listener;
    private DatabaseReference fbs = _firebase.getReference("/");
    private ChildEventListener _fbs_child_listener;
    private Intent i = new Intent();
    private AlertDialog.Builder d;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_admin_panel);
        initialize(_savedInstanceState);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {
        linear2 = (LinearLayout) findViewById(R.id.linear2);
        vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        textview7 = (TextView) findViewById(R.id.textview7);
        textview6 = (TextView) findViewById(R.id.textview6);
        button4 = (Button) findViewById(R.id.button4);
        textview4 = (TextView) findViewById(R.id.textview4);
        textview2 = (TextView) findViewById(R.id.textview2);
        textview3 = (TextView) findViewById(R.id.textview3);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        d = new AlertDialog.Builder(this);

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                fbs2.addListenerForSingleValueEvent(new ValueEventListener() {
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
                        stop = map2.get((int)0).get("stop").toString();
                        if (stop.contains("1")) {
                            map_stop = new HashMap<>();
                            map_stop.put("stop", "0");
                            fbs.child("Manage").removeValue();
                            fbs2.push().updateChildren(map_stop);
                            button4.setText("  Остановить  ");
                            textview6.setText("Приложение работает");
                            textview6.setTextColor(0xFF4CAF50);
                        }
                        else {
                            if (stop.contains("0")) {
                                map_stop = new HashMap<>();
                                map_stop.put("stop", "1");
                                fbs.child("Manage").removeValue();
                                fbs2.push().updateChildren(map_stop);
                                button4.setText("  Включить  ");
                                textview6.setText("Приложение остановлено");
                                textview6.setTextColor(0xFFF44336);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError _databaseError) {
                    }
                });
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                d.setTitle("Сброс количества подсчётов");
                d.setMessage("З - запусков\nЗ+П - запусков и пользователей");
                d.setPositiveButton("З", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {
                        fbs.child("Launches").removeValue();
                        map_reset = new HashMap<>();
                        map_reset.put("launches", "0");
                        fbs1.push().updateChildren(map_reset);
                        i.setClass(getApplicationContext(), AdminPanelActivity.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);

                    }
                });
                d.setNegativeButton("З+П", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {
                        fbs.child("Launches").removeValue();
                        fbs.child("Users").removeValue();
                        map_reset = new HashMap<>();
                        map_reset.put("launches", "0");
                        fbs1.push().updateChildren(map_reset);
                        map_reset = new HashMap<>();
                        map_reset.put("users", "0");
                        fbs3.push().updateChildren(map_reset);
                        i.setClass(getApplicationContext(), AdminPanelActivity.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);

                    }
                });
                d.create().show();
            }
        });

        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), NewVersionActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), ModsWaitingActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), UploadMod1Activity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), EditModActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        _fbs1_child_listener = new ChildEventListener() {
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
        fbs1.addChildEventListener(_fbs1_child_listener);

        _fbs2_child_listener = new ChildEventListener() {
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
        fbs2.addChildEventListener(_fbs2_child_listener);

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

        _fbs4_child_listener = new ChildEventListener() {
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
        fbs4.addChildEventListener(_fbs4_child_listener);

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

    private void initializeLogic() {
        fbs1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot _dataSnapshot) {
                map1 = new ArrayList<>();
                try {
                    GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                    for (DataSnapshot _data : _dataSnapshot.getChildren()) {
                        HashMap<String, Object> _map = _data.getValue(_ind);
                        map1.add(_map);
                    }
                }
                catch (Exception _e) {
                    _e.printStackTrace();
                }
                textview2.setText("Сделано запусков: ".concat(map1.get((int)0).get("launches").toString()));
            }
            @Override
            public void onCancelled(DatabaseError _databaseError) {
            }
        });
        fbs2.addListenerForSingleValueEvent(new ValueEventListener() {
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
                stop = map2.get((int)0).get("stop").toString();
                if (stop.contains("1")) {
                    button4.setText("  Включить  ");
                    textview6.setText("Приложение остановлено");
                    textview6.setTextColor(0xFFF44336);
                }
                else {
                    button4.setText("  Остановить  ");
                    textview6.setText("Приложение работает");
                    textview6.setTextColor(0xFF4CAF50);
                }
            }
            @Override
            public void onCancelled(DatabaseError _databaseError) {
            }
        });
        fbs3.addListenerForSingleValueEvent(new ValueEventListener() {
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
                textview3.setText("Пользователей: ".concat(map3.get((int)0).get("users").toString()));
            }
            @Override
            public void onCancelled(DatabaseError _databaseError) {
            }
        });
        fbs4.addListenerForSingleValueEvent(new ValueEventListener() {
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
                textview4.setText("Версия MTG MODS: ".concat(map4.get((int)0).get("version").toString()));
            }
            @Override
            public void onCancelled(DatabaseError _databaseError) {
            }
        });
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
