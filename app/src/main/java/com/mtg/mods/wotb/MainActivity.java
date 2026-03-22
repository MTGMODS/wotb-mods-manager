package com.mtg.mods.wotb;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.os.Build.VERSION.SDK_INT;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer _timer = new Timer();

    private Intent i = new Intent();
    private TimerTask t;

    private Button btn;
    private TextView text;
    private TextView text2;

    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        text = (TextView) findViewById(R.id.text);
        text2 = (TextView) findViewById(R.id.text2);
        settings = getSharedPreferences("settings", Activity.MODE_PRIVATE);

        if (SDK_INT <= Build.VERSION_CODES.Q) {

            text2.setVisibility(View.GONE);

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                    || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                i.setClass(getApplicationContext(), LogoActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }

        }

        if (SDK_INT >= Build.VERSION_CODES.R) {

            text.setText("Для работы приложения необходимо выдать разрешения на доступ к памяти\n\n\n");

            if (Environment.isExternalStorageManager()) {

                text2.setText("Выдано разрешений: 1/4");


                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

//                  disable SAF for demo app:
//                  i.setClass(getApplicationContext(), SAF_PermissionActivity.class);

                    i.setClass(getApplicationContext(), LogoActivity.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                }

            }
        }


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {

                if (SDK_INT >= Build.VERSION_CODES.R) {

                    if (Environment.isExternalStorageManager()) {
                        Toast.makeText(MainActivity.this, "Предоставьте разрешение", Toast.LENGTH_SHORT).show();
                        _check();
                    } else {
                        try {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                            intent.addCategory("android.intent.category.DEFAULT");
                            intent.setData(Uri.parse(String.format("package:%s", new Object[]{getApplicationContext().getPackageName()})));
                            startActivityForResult(intent, 2000);
                        } catch (Exception e) {
                            Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                            startActivityForResult(intent, 2000);

                        }
                    }

                } else {
                    _check();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 333) {
            if (grantResults.length > 0) {
                boolean write = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean read = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                if (read && write) {

                } else {
                    Toast.makeText(this, "Вы не предоставили разрешение!", Toast.LENGTH_SHORT).show();

                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2000) {
            if (SDK_INT >= Build.VERSION_CODES.R) {
                if (Environment.isExternalStorageManager()) {
                    text2.setText("Выдано разрешений: 1/4");
                } else {
                    Toast.makeText(this, "Выберите MTG MODS и предоставьте разрешение", Toast.LENGTH_SHORT).show();
                }
            } else {
                _check();
            }
        }
    }

    private void _check() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
            t = new TimerTask() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            _check();
                        }
                    });
                }
            };
            _timer.schedule(t, (int) (2000));
        } else {

            if (SDK_INT >= Build.VERSION_CODES.R) {

                FileUtil.makeDir(FileUtil.getExternalStorageDir());
                FileUtil.deleteFile(FileUtil.getExternalStorageDir().concat("/MTG MODS"));
                FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/MTG MODS/.app/temp"), ".");
                FileUtil.deleteFile(FileUtil.getExternalStorageDir().concat("/MTG MODS/.app/temp"));

                settings.edit().putString("language", "russian").commit();
                settings.edit().putString("video_background", "true").commit();

//                disable SAF actions for demo app
//                i.setClass(getApplicationContext(), SAF_PermissionActivity.class);
                i.setClass(getApplicationContext(), LogoActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);


            } else {

                FileUtil.makeDir(FileUtil.getExternalStorageDir());
                FileUtil.deleteFile(FileUtil.getExternalStorageDir().concat("/MTG MODS"));
                FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/MTG MODS/.app/temp"), ".");
                FileUtil.deleteFile(FileUtil.getExternalStorageDir().concat("/MTG MODS/.app/temp"));

                settings.edit().putString("language", "russian").commit();
                settings.edit().putString("video_background", "true").commit();

                i.setClass(getApplicationContext(), LogoActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);


            }

        }
    }
}