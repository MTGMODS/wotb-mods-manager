package com.mtg.mods.wotb;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class DownloadUpdateActivity extends AppCompatActivity {
    private Timer _timer = new Timer();
    private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();

    private String version = "";

    private LinearLayout linear1;
    private TextView textview1;
    private ProgressBar progressbar1;
    private TextView textview2;

    private StorageReference fbs = _firebase_storage.getReference("New");
    private OnCompleteListener<Uri> _fbs_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _fbs_download_success_listener;
    private OnSuccessListener _fbs_delete_success_listener;
    private OnProgressListener _fbs_upload_progress_listener;
    private OnProgressListener _fbs_download_progress_listener;
    private OnFailureListener _fbs_failure_listener;
    private Intent i = new Intent();
    private SharedPreferences settings;
    private SharedPreferences network;
    private TimerTask t;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.download_update);
        initialize(_savedInstanceState);
        com.google.firebase.FirebaseApp.initializeApp(this);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
        }
        else {
            initializeLogic();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            initializeLogic();
        }
    }

    private void initialize(Bundle _savedInstanceState) {
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        textview1 = (TextView) findViewById(R.id.textview1);
        progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
        textview2 = (TextView) findViewById(R.id.textview2);
        settings = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        network = getSharedPreferences("network", Activity.MODE_PRIVATE);

        _fbs_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();

            }
        };

        _fbs_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                textview2.setText(String.valueOf((long)(_progressValue)).concat("%"));
                progressbar1.setProgress((int)_progressValue);
                t = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (textview2.getText().toString().equals("0%")) {
                                    i.setClass(getApplicationContext(), LimitActivity.class);
                                    startActivity(i);
                                    overridePendingTransition(0, 0);
                                }
                            }
                        });
                    }
                };
                _timer.schedule(t, (int)(20000));
            }
        };

        _fbs_upload_success_listener = new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> _param1) {
                final String _downloadUrl = _param1.getResult().toString();

            }
        };

        _fbs_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
                final long _totalByteCount = _param1.getTotalByteCount();
                try{
                    progressbar1.setVisibility(View.GONE);
                    textview1.setVisibility(View.GONE);
                    textview2.setVisibility(View.GONE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        Uri uri = androidx.core.content.FileProvider.getUriForFile(getApplicationContext(),
                                DownloadUpdateActivity.this.getPackageName() + ".provider", new java.io.File(FileUtil.getExternalStorageDir().concat("/MTG MODS/update(".concat(version.concat(").apk")))));
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.setDataAndType(uri, "application/vnd.android.package-archive");
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setDataAndType(Uri.fromFile( new java.io.File(FileUtil.getExternalStorageDir().concat("/MTG MODS/update(".concat(version.concat(").apk"))))),
                                "application/vnd.android.package-archive");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                    SketchwareUtil.showMessage(getApplicationContext(), "Install apk:\n".concat("/MTG MODS/update(".concat(version.concat(").apk"))));
                }catch(Exception e){
                    SketchwareUtil.showMessage(getApplicationContext(), "Install apk:\n".concat("/MTG MODS/update(".concat(version.concat(").apk"))));
                    finishAffinity();
                }
            }
        };

        _fbs_delete_success_listener = new OnSuccessListener() {
            @Override
            public void onSuccess(Object _param1) {

            }
        };

        _fbs_failure_listener = new OnFailureListener() {
            @Override
            public void onFailure(Exception _param1) {
                final String _message = _param1.getMessage();

            }
        };

    }

    private void initializeLogic() {
        if (isConnected()){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            if (settings.getString("language", "").contains("english")) {
                textview1.setText("Downloading update");
            }
            version = getIntent().getStringExtra("version");
                _firebase_storage.getReferenceFromUrl(getIntent().getStringExtra("url")).getFile(new File(FileUtil.getExternalStorageDir().concat("/MTG MODS/update(".concat(version.concat(").apk"))))).addOnSuccessListener(_fbs_download_success_listener).addOnFailureListener(_fbs_failure_listener).addOnProgressListener(_fbs_download_progress_listener);
        }
        else {
            network.edit().putString("network", "down_update").commit();
            i.setClass(getApplicationContext(), NoNetworkActivity.class);
            startActivity(i);
            overridePendingTransition(0, 0);
        }
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
    public void onBackPressed() {

    }
    @Deprecated
    public void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
    }

    @Deprecated
    public int getLocationX(View _v) {
        int _location[] = new int[2];
        _v.getLocationInWindow(_location);
        return _location[0];
    }

    @Deprecated
    public int getLocationY(View _v) {
        int _location[] = new int[2];
        _v.getLocationInWindow(_location);
        return _location[1];
    }

    @Deprecated
    public int getRandom(int _min, int _max) {
        Random random = new Random();
        return random.nextInt(_max - _min + 1) + _min;
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

    @Deprecated
    public float getDip(int _input) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
    }

    @Deprecated
    public int getDisplayWidthPixels() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    @Deprecated
    public int getDisplayHeightPixels() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    private boolean isConnected(){

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo!=null && networkInfo.isConnected();


    }
}
