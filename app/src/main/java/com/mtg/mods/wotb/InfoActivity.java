package com.mtg.mods.wotb;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class InfoActivity extends AppCompatActivity {
	private Timer _timer = new Timer();
	
	private String str = "";
	private double check = 0;
	
	private LinearLayout linear1;
	private ScrollView vscroll1;
	private LinearLayout linear3;
	private TextView textview1;
	private TextView textview19;
	private LinearLayout linear4;
	private TextView textview2;
	private ImageView imageview1;
	private TextView textview3;
	private ImageView imageview2;
	private TextView textview4;
	private ImageView imageview3;
	
	private Intent i = new Intent();
	private TimerTask t;
	private SharedPreferences settings;
	private Intent b = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.info);
		initialize(_savedInstanceState);
		com.google.firebase.FirebaseApp.initializeApp(this);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		textview1 = (TextView) findViewById(R.id.textview1);
		textview19 = (TextView) findViewById(R.id.textview19);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		textview2 = (TextView) findViewById(R.id.textview2);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		textview3 = (TextView) findViewById(R.id.textview3);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		textview4 = (TextView) findViewById(R.id.textview4);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		settings = getSharedPreferences("settings", Activity.MODE_PRIVATE);
		
		textview19.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				i.setClass(getApplicationContext(), MenuActivity.class);
				startActivity(i);
				overridePendingTransition(0, 0);
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				b.setAction(Intent.ACTION_VIEW);
				b.setData(Uri.parse("https://discord.gg/uUZbxgEByu"));
				startActivity(b);
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				b.setAction(Intent.ACTION_VIEW);
				b.setData(Uri.parse("https://youtube.com/c/bogdanua_channel"));
				startActivity(b);
			}
		});
		
		imageview3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				b.setAction(Intent.ACTION_VIEW);
				b.setData(Uri.parse("https://new.donatepay.ru/en/@172523"));
				startActivity(b);
			}
		});
	}
	
	private void initializeLogic() {
		vscroll1.setHorizontalScrollBarEnabled(false);
		vscroll1.setVerticalScrollBarEnabled(false);
		vscroll1.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
		if (settings.getString("language", "").contains("english")) {
			textview1.setText("MTG MODS ©\n\nAutomatic mod installer for World of Tanks Blitz\n\n\nApplication developer:\n Master_tanka_Grille15 (MTG MODS)\n");
			textview19.setText("   Back   ");
			textview2.setText("Our Discord Community:");
			textview3.setText("YouTube channel:");
			textview4.setText("For those wishing to support financially:");
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
		textview19.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT)); }
}
