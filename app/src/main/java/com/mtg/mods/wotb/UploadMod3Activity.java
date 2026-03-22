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
import android.widget.ImageView;
import android.content.Intent;
import android.content.ClipData;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


public class UploadMod3Activity extends AppCompatActivity {

    public final int REQ_CD_FP1 = 101;

    private String image1 = "";
    private String image2 = "";

    private LinearLayout linear1;
    private TextView textview1;
    private LinearLayout linear2;
    private TextView textview2;
    private ImageView imageview1;
    private LinearLayout linear3;
    private ImageView imageview4;
    private ImageView imageview5;

    private Intent fp1 = new Intent(Intent.ACTION_GET_CONTENT);
    private Intent i = new Intent();
    private SharedPreferences sh;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_upload_mod3);
        initialize(_savedInstanceState);
        com.google.firebase.FirebaseApp.initializeApp(this);
    }


    private void initialize(Bundle _savedInstanceState) {
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        textview1 = (TextView) findViewById(R.id.textview1);
        linear2 = (LinearLayout) findViewById(R.id.linear2);
        textview2 = (TextView) findViewById(R.id.textview2);
        imageview1 = (ImageView) findViewById(R.id.imageview1);
        linear3 = (LinearLayout) findViewById(R.id.linear3);
        imageview4 = (ImageView) findViewById(R.id.imageview4);
        imageview5 = (ImageView) findViewById(R.id.imageview5);
        fp1.setType("image/*");
        fp1.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        sh = getSharedPreferences("mod", Activity.MODE_PRIVATE);

        imageview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                startActivityForResult(fp1, REQ_CD_FP1);
            }
        });

        imageview4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), UploadMod1Activity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        imageview5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), UploadMod4Activity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
        super.onActivityResult(_requestCode, _resultCode, _data);
        switch (_requestCode) {
            case REQ_CD_FP1:
                if (_resultCode == Activity.RESULT_OK) {
                    ArrayList<String> _filePath = new ArrayList<>();
                    if (_data != null) {
                        if (_data.getClipData() != null) {
                            for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
                                ClipData.Item _item = _data.getClipData().getItemAt(_index);
                                _filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
                            }
                        }
                        else {
                            _filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
                        }
                    }
                    textview2.setVisibility(View.GONE);
                    imageview1.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(_filePath.get((int)(0)), 1024, 1024));
                    sh.edit().putString("image", _filePath.get((int)(0))).commit();
                }
                else {
                    Toast.makeText(this, "Вы не выбрали скриншот!", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {

    }
}
