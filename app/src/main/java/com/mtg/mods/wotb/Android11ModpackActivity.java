package com.mtg.mods.wotb;

import androidx.appcompat.app.AppCompatActivity;

import android.app.*;
import android.os.Build;
import android.os.Bundle;
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
import android.widget.Button;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;

public class Android11ModpackActivity extends AppCompatActivity {

    private LinearLayout linear1;
    private TextView textview1;
    private TextView textview2;
    private TextView textview5;

    private String build = "1";

    private SharedPreferences settings;
    private Intent i = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android11_modpack);

        build = Build.VERSION.RELEASE;

        linear1 = (LinearLayout) findViewById(R.id.linear1);
        textview1 = (TextView) findViewById(R.id.textview1);
        textview2 = (TextView) findViewById(R.id.textview2);
        textview5 = (TextView) findViewById(R.id.textview5);
        settings = getSharedPreferences("settings", Activity.MODE_PRIVATE);

        if (settings.getString("language", "").contains("english")) {
            textview1.setText("Android ".concat(build.concat(" detected!")));
            textview2.setText("Unfortunately, automatic unpacking of the archive is not possible on your device.\n\nIn the memory of your device, in the MTG MODS folder there is an archive modpack.zip\n\nIt must be unpacked along the way:\n/Android/data/");
            textview5.setText("  Back  ");
        } else {
            textview1.setText("Обнаружен Android ".concat(build.concat("!")));
        }

        textview5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });
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
        textview5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT)); }

}

