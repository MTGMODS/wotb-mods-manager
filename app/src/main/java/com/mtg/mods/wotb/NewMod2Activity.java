package com.mtg.mods.wotb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;

import android.Manifest;
import android.app.*;
import android.content.pm.PackageManager;
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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;

public class NewMod2Activity extends AppCompatActivity {

    private TextView textview1;
    private EditText edittext1;
    private ImageView imageview4;
    private ImageView imageview5;
    private LinearLayout linear4;

    private Intent i = new Intent();
    private SharedPreferences mod;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_new_mod2);
        com.google.firebase.FirebaseApp.initializeApp(this);

        linear4 = (LinearLayout) findViewById(R.id.linear4);
        textview1 = (TextView) findViewById(R.id.textview1);
        imageview4 = (ImageView) findViewById(R.id.imageview4);
        imageview5 = (ImageView) findViewById(R.id.imageview5);
        edittext1 = (EditText) findViewById(R.id.edittext1);
        mod = getSharedPreferences("mod", Activity.MODE_PRIVATE);


        imageview4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), NewModActivity.class);
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
                    mod.edit().putString("image", edittext1.getText().toString()).commit();
                    i.setClass(getApplicationContext(), NewMod3Activity.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                }
            }
        });
    }


    @Override
    public void onBackPressed() {

    }

    @Override
    public void onStart() {
        super.onStart();
        linear4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)50, (int)5, 0xFFFFFFFF, 0xFF36393E));
    }

}
