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
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.app.AlertDialog;
import android.content.DialogInterface;
import java.util.Timer;
import java.util.TimerTask;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.AdapterView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;

public class EditModActivity extends AppCompatActivity {

    private Timer _timer = new Timer();
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

    private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
    private ArrayList<String> spinner = new ArrayList<>();

    private LinearLayout linear1;
    private TextView textview1;
    private Spinner spinner1;
    private Button button1;

    private Intent i = new Intent();
    private DatabaseReference fbs = _firebase.getReference("/");
    private ChildEventListener _fbs_child_listener;
    private AlertDialog.Builder d;
    private TimerTask t;
    private SharedPreferences position;
    private SharedPreferences login;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_edit_mod);
        initialize(_savedInstanceState);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        textview1 = (TextView) findViewById(R.id.textview1);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        button1 = (Button) findViewById(R.id.button1);
        d = new AlertDialog.Builder(this);
        position = getSharedPreferences("position", Activity.MODE_PRIVATE);
        login = getSharedPreferences("login", Activity.MODE_PRIVATE);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
                final int _position = _param3;
                position.edit().putString("position", String.valueOf((long)(_position))).commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> _param1) {

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (position.getString("position", "").equals("0")) {
                    Toast.makeText(EditModActivity.this, "Не выбрана категория мода!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (position.getString("position", "").equals("1")) {
                        i.setClass(getApplicationContext(), HangarViewActivity.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                    }
                    else {
                        if (position.getString("position", "").equals("2")) {
                            i.setClass(getApplicationContext(), SightViewActivity.class);
                            startActivity(i);
                            overridePendingTransition(0, 0);
                        }
                        else {
                            if (position.getString("position", "").equals("3")) {
                                i.setClass(getApplicationContext(), SkinsViewActivity.class);
                                startActivity(i);
                                overridePendingTransition(0, 0);
                            }
                            else {
                                if (position.getString("position", "").equals("4")) {
                                    i.setClass(getApplicationContext(), RemodViewActivity.class);
                                    startActivity(i);
                                    overridePendingTransition(0, 0);
                                }
                                else {
                                    if (position.getString("position", "").equals("5")) {
                                        i.setClass(getApplicationContext(), SixSenseViewActivity.class);
                                        startActivity(i);
                                        overridePendingTransition(0, 0);
                                    }
                                    else {
                                        if (position.getString("position", "").equals("6")) {
                                            i.setClass(getApplicationContext(), InterfacesViewActivity.class);
                                            startActivity(i);
                                            overridePendingTransition(0, 0);
                                        }
                                        else {
                                            if (position.getString("position", "").equals("7")) {

                                                startActivity(i);
                                                overridePendingTransition(0, 0);
                                            }
                                            else {
                                                if (position.getString("position", "").equals("8")) {

                                                    startActivity(i);
                                                    overridePendingTransition(0, 0);
                                                }
                                                else {
                                                    if (position.getString("position", "").equals("9")) {

                                                        startActivity(i);
                                                        overridePendingTransition(0, 0);
                                                    }
                                                    else {
                                                        Toast.makeText(EditModActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                                                        finishAffinity();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
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

    private void initializeLogic() {
        spinner.add("Не выбрано");
        spinner.add("Ангары");
        spinner.add("Прицелы");
        spinner.add("Шкурки");
        spinner.add("Ремоделинг");
        spinner.add("Лампы засвета");
        spinner.add("Интерфейс");
        spinner.add("Звуковые моды");
        spinner.add("Прочие моды");
        if (login.getString("type", "").contains("admin")) {
            spinner.add("VIP моды");
        }
        spinner1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, spinner));
        ((ArrayAdapter)spinner1.getAdapter()).notifyDataSetChanged();
    }

}