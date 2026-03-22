package com.mtg.mods.wotb;

import static android.os.Build.VERSION.SDK_INT;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;


public class  LogoActivity extends AppCompatActivity {

    private String assetFilename = "";
    private String assetSavePath = "";

    private Intent i = new Intent();

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.logo);


        if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/MTG MODS/.app/video.mp4"))) {
            i.setClass(getApplicationContext(), UpdaterActivity.class);
            startActivity(i);
            overridePendingTransition(0, 0);
        } else {
            _CopyFromAssets("video.mp4", FileUtil.getExternalStorageDir().concat("/MTG MODS/.app/"));
            i.setClass(getApplicationContext(), UpdaterActivity.class);
            startActivity(i);
            overridePendingTransition(0, 0);
        }

    }

    public void _CopyFromAssets (final String _filen, final String _topathh) {
        if (FileUtil.isDirectory(_topathh)) {
            FileUtil.writeFile("Dont remove", "its For storage Permission");
            assetFilename = _filen;
            assetSavePath = _topathh;
            try{
                int count;
                java.io.InputStream input= this.getAssets().open(assetFilename);
                java.io.OutputStream output = new  java.io.FileOutputStream(assetSavePath+"/"+assetFilename);
                byte data[] = new byte[1024];
                while ((count = input.read(data))>0) {
                    output.write(data, 0, count);
                }
                output.flush();
                output.close();
                input.close();

            }catch(Exception e){

            }
        }
        else {

        }
    }
}
