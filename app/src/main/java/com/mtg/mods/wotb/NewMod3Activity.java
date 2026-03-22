package com.mtg.mods.wotb;

import androidx.appcompat.app.AppCompatActivity;
import android.os.*;
import android.widget.*;
import android.graphics.drawable.*;
import android.util.*;
import java.util.*;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Intent;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;

public class NewMod3Activity extends AppCompatActivity {

    private String image1 = "";
    private String image2 = "";

    private LinearLayout linear1;
    private TextView textview1;
    private TextView textview2;
    private LinearLayout linear4;
    private LinearLayout linear3;
    private EditText edittext1;
    private ImageView imageview4;
    private ImageView imageview5;

    private Intent i = new Intent();
    private SharedPreferences mod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_mod3);
        com.google.firebase.FirebaseApp.initializeApp(this);
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        textview1 = (TextView) findViewById(R.id.textview1);
        textview2 = (TextView) findViewById(R.id.textview2);
        linear4 = (LinearLayout) findViewById(R.id.linear4);
        linear3 = (LinearLayout) findViewById(R.id.linear3);
        edittext1 = (EditText) findViewById(R.id.edittext1);
        imageview4 = (ImageView) findViewById(R.id.imageview4);
        imageview5 = (ImageView) findViewById(R.id.imageview5);
        mod = getSharedPreferences("mod", Activity.MODE_PRIVATE);

        imageview4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), NewMod2Activity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        imageview5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (edittext1.getText().toString().equals("")) {
                    SketchwareUtil.showMessage(getApplicationContext(), "Вы не указали ссылку!");
                }
                else {
                    mod.edit().putString("file", edittext1.getText().toString()).commit();
                    i.setClass(getApplicationContext(), NewMod4Activity.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        linear4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)50, (int)5, 0xFFFFFFFF, 0xFF36393E));
    }

    @Override
    public void onBackPressed() {

    }


}