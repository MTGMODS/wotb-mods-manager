package com.mtg.mods.wotb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextView textview4;
    private EditText edittext1;
    private EditText edittext2;

    private FirebaseAuth mAuth;

    private AlertDialog.Builder d;

    private SharedPreferences login;
    private Intent i = new Intent();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        login = getSharedPreferences("login", Activity.MODE_PRIVATE);
        textview4 = (TextView) findViewById(R.id.textview4);
        edittext1 = (EditText) findViewById(R.id.edittext1);
        edittext2 = (EditText) findViewById(R.id.edittext2);

        d = new AlertDialog.Builder(this,AlertDialog.THEME_TRADITIONAL);
        d.setCancelable(false);

        textview4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _login();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        textview4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
    }


    private void _login() {

        String email = edittext1.getText().toString();
        String pass = edittext2.getText().toString();

        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {



                    if (email.equals("bogdanmarger007@gmail.com")) {
                        login.edit().putString("type", "admin").commit();
                        i.setClass(getApplicationContext(),  ModderPanelActivity.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                    } else if (email.equals("mtg@mods.com")) {
                        login.edit().putString("type", "admin").commit();
                        i.setClass(getApplicationContext(),  ModderPanelActivity.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                    } else {
                        login.edit().putString("type", "modder").commit();
                        i.setClass(getApplicationContext(), ModderPanelActivity.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                    }

                } else {
                    d.setMessage("Вы ввели неверную почту или неверный пароль");
                    d.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface _dialog, int _which) {

                        }
                    });
                    d.create().show();
                }

            }
        });

    }

}