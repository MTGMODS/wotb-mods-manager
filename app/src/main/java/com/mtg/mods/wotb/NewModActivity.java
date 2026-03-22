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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.AdapterView;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;

public class NewModActivity extends AppCompatActivity {

    private double select = 0;
    private String file = "";

    private ArrayList<String> category = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> category2 = new ArrayList<>();
    private ArrayList<String> file1 = new ArrayList<>();

    private LinearLayout linear1;
    private LinearLayout linear2;
    private LinearLayout linear4;
    private LinearLayout linear3;
    private LinearLayout linear6;
    private TextView textview2;
    private EditText edittext1;
    private TextView textview4;
    private EditText edittext2;
    private TextView textview3;
    private Spinner spinner1;
    private LinearLayout linear5;
    private ImageView imageview5;
    private ImageView imageview4;

    private Intent i = new Intent();
    private SharedPreferences mod;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_new_mod);
        initialize(_savedInstanceState);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        linear2 = (LinearLayout) findViewById(R.id.linear2);
        linear4 = (LinearLayout) findViewById(R.id.linear4);
        linear3 = (LinearLayout) findViewById(R.id.linear3);
        linear6 = (LinearLayout) findViewById(R.id.linear6);
        textview2 = (TextView) findViewById(R.id.textview2);
        edittext1 = (EditText) findViewById(R.id.edittext1);
        textview4 = (TextView) findViewById(R.id.textview4);
        edittext2 = (EditText) findViewById(R.id.edittext2);
        textview3 = (TextView) findViewById(R.id.textview3);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        linear5 = (LinearLayout) findViewById(R.id.linear5);
        imageview5 = (ImageView) findViewById(R.id.imageview5);
        imageview4 = (ImageView) findViewById(R.id.imageview4);
        mod = getSharedPreferences("mod", Activity.MODE_PRIVATE);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
                final int _position = _param3;
                select = _position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> _param1) {

            }
        });


        imageview4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        imageview5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                mod.edit().putString("mod", edittext1.getText().toString()).commit();
                mod.edit().putString("author", edittext2.getText().toString()).commit();
                mod.edit().putString("category", String.valueOf((long)(select))).commit();
                if (edittext1.getText().toString().equals("")) {
                    SketchwareUtil.showMessage(getApplicationContext(), "Вы не указали название мода!");
                }
                else {
                    if (edittext2.getText().toString().equals("")) {
                        SketchwareUtil.showMessage(getApplicationContext(), "Вы не указали автора!");
                    }
                    else {
                        if (mod.getString("category", "").equals("0")) {
                            SketchwareUtil.showMessage(getApplicationContext(), "Вы не выбрали категорию мода!");
                        }
                        else if (mod.getString("category", "").equals("7")) {
                                i.setClass(getApplicationContext(), NewMod2BActivity.class);
                                startActivity(i);
                                overridePendingTransition(0, 0);
                            } else {
                                i.setClass(getApplicationContext(), NewMod2Activity.class);
                                startActivity(i);
                                overridePendingTransition(0, 0);
                            }

                        }
                    }
            }
        });
    }

    private void initializeLogic() {
        category.add("Не выбрано");
        category.add("Ангары");
        category.add("Прицелы");
        category.add("Шкурки");
        category.add("Ремоделинг");
        category.add("Лампы засвета");
        category.add("Интерфейс");
        category.add("Звуковые моды");
        category.add("Прочие моды");
        spinner1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, category));
        ((ArrayAdapter)spinner1.getAdapter()).notifyDataSetChanged();
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
        spinner1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)50, (int)5, 0xFFFFFFFF, 0xFF36393E));
        linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)50, (int)5, 0xFFFFFFFF, 0xFF36393E));
        linear3.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)50, (int)5, 0xFFFFFFFF, 0xFF36393E));
        linear4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)50, (int)5, 0xFFFFFFFF, 0xFF36393E));
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
