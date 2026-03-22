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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class UploadMod2Activity extends AppCompatActivity {

    private LinearLayout linear1;
    private TextView textview1;
    private LinearLayout linear2;
    private LinearLayout linear3;
    private TextView textview2;
    private EditText edittext1;
    private ImageView imageview1;
    private ImageView imageview2;

    private Intent i = new Intent();
    private SharedPreferences sh;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_upload_mod2);
        initialize(_savedInstanceState);
        com.google.firebase.FirebaseApp.initializeApp(this);
    }

    private void initialize(Bundle _savedInstanceState) {
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        textview1 = (TextView) findViewById(R.id.textview1);
        linear2 = (LinearLayout) findViewById(R.id.linear2);
        linear3 = (LinearLayout) findViewById(R.id.linear3);
        textview2 = (TextView) findViewById(R.id.textview2);
        edittext1 = (EditText) findViewById(R.id.edittext1);
        imageview1 = (ImageView) findViewById(R.id.imageview1);
        imageview2 = (ImageView) findViewById(R.id.imageview2);
        sh = getSharedPreferences("mod", Activity.MODE_PRIVATE);

        imageview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), MainActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        imageview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (edittext1.getText().toString().equals("")) {
                    Toast.makeText(UploadMod2Activity.this, "Вы не указали ссылку на видео!", Toast.LENGTH_SHORT).show();
                }
                else {
                    sh.edit().putString("video", edittext1.getText().toString()).commit();
                    i.setClass(getApplicationContext(), UploadMod4Activity.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                }
            }
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, 0xFF36393E));
    }

    @Override
    public void onBackPressed() {

    }
}
