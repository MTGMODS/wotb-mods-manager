package com.mtg.mods.wotb;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import com.unity3d.ads.IUnityAdsInitializationListener;
import com.unity3d.ads.IUnityAdsShowListener;
import com.unity3d.ads.UnityAds;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class SolyankaActivity extends AppCompatActivity {
    private Timer _timer = new Timer();
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
    private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();

    private String url = "";
    private String settings = "";
    private String str = "";
    private String buildd = "";
    private String original = "";
    private String version = "";

    private ArrayList<HashMap<String, Object>> info = new ArrayList<>();

    private LinearLayout background;
    private LinearLayout first;
    private LinearLayout content;
    private LinearLayout linear1;
    private TextView textview1;
    private ProgressBar progressbar1;
    private TextView textview2;
    private TextView textview5;
    private LinearLayout linear2;
    private ScrollView vscroll1;
    private LinearLayout linear4;
    private TextView textview3;
    private ImageView imageview1;
    private TextView textview4;
    private TextView textview6;

    private StorageReference fbss = _firebase_storage.getReference("Files");
    private OnCompleteListener<Uri> _fbss_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _fbss_download_success_listener;
    private OnSuccessListener _fbss_delete_success_listener;
    private OnProgressListener _fbss_upload_progress_listener;
    private OnProgressListener _fbss_download_progress_listener;
    private OnFailureListener _fbss_failure_listener;
    private AlertDialog.Builder d;
    private DatabaseReference fbs = _firebase.getReference("Modpacks");
    private ChildEventListener _fbs_child_listener;
    private TimerTask t;
    private Intent i = new Intent();
    private SharedPreferences modpacks;
    private SharedPreferences settings_sp;
    private SharedPreferences installer;

    String GameID = "4595401";
    String ADID = "Interstitial_Android";
    Boolean TestMode = false;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.solyanka);
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
        background = (LinearLayout) findViewById(R.id.background);
        first = (LinearLayout) findViewById(R.id.first);
        content = (LinearLayout) findViewById(R.id.content);
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        textview1 = (TextView) findViewById(R.id.textview1);
        progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
        textview2 = (TextView) findViewById(R.id.textview2);
        textview5 = (TextView) findViewById(R.id.textview5);
        linear2 = (LinearLayout) findViewById(R.id.linear2);
        vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
        linear4 = (LinearLayout) findViewById(R.id.linear4);
        textview3 = (TextView) findViewById(R.id.textview3);
        imageview1 = (ImageView) findViewById(R.id.imageview1);
        textview4 = (TextView) findViewById(R.id.textview4);
        textview6 = (TextView) findViewById(R.id.textview6);
        d = new AlertDialog.Builder(this);
        modpacks = getSharedPreferences("modpacks", Activity.MODE_PRIVATE);
        settings_sp = getSharedPreferences("settings", Activity.MODE_PRIVATE);
        installer = getSharedPreferences("installer", Activity.MODE_PRIVATE);

        textview5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        textview6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        _fbss_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();

            }
        };

        _fbss_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                progressbar1.setProgress((int)_progressValue);
                textview2.setText(String.valueOf((long)(_progressValue)).concat("%"));
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

        _fbss_upload_success_listener = new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> _param1) {
                final String _downloadUrl = _param1.getResult().toString();

            }
        };

        _fbss_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
                final long _totalByteCount = _param1.getTotalByteCount();
                if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/Android/data/net.wargaming.wot.blitz/files/packs/dvpl_file_info_cache.txt"))) {
                    if (Build.VERSION.SDK_INT > 29) {
                        i.setClass(getApplicationContext(), Android11ModpackActivity.class);
                        startActivity(i);
                        overridePendingTransition(0, 0);
                    }
                    else {
                        linear2.setVisibility(View.GONE);
                        progressbar1.setVisibility(View.GONE);
                        textview2.setVisibility(View.GONE);
                        if (settings_sp.getString("language", "").contains("english")) {
                            textview1.setText("Installing the modpack...");
                        }
                        else {
                            textview1.setText("Установка модпака...");
                        }
                        t = new TimerTask() {
                            @Override
                            public void run() {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        _UnZip(FileUtil.getExternalStorageDir().concat("/MTG MODS/modpack.zip"), FileUtil.getExternalStorageDir().concat("/Android/data/"));
                                        t = new TimerTask() {
                                            @Override
                                            public void run() {
                                                runOnUiThread(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        modpacks.edit().putString("solyanka", version).commit();
                                                        installer.edit().putString("modpack", "solyanka").commit();
                                                        if (settings_sp.getString("language", "").contains("english")) {
                                                            textview1.setText("Modpack successfully installed!");
                                                        }
                                                        else {
                                                            textview1.setText("Модпак успешно установлен!");
                                                        }
                                                        textview5.setVisibility(View.VISIBLE);
                                                        if (settings_sp.getString("ads", "").contains("false")) {

                                                        }
                                                        else {
                                                            IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
                                                                @Override
                                                                public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                                                                    UnityAds.load(ADID);
                                                                    UnityAds.show(SolyankaActivity.this, ADID);
                                                                }

                                                                @Override
                                                                public void onUnityAdsShowStart(String s) {

                                                                }

                                                                @Override
                                                                public void onUnityAdsShowClick(String s) {

                                                                }

                                                                @Override
                                                                public void onUnityAdsShowComplete(String s, UnityAds.UnityAdsShowCompletionState state) {

                                                                }
                                                            };
                                                            UnityAds.load(ADID);
                                                            UnityAds.show(SolyankaActivity.this, ADID);
                                                        }
                                                    }
                                                });
                                            }
                                        };
                                        _timer.schedule(t, (int)(10000));
                                    }
                                });
                            }
                        };
                        _timer.schedule(t, (int)(500));
                    }
                }
                else {
                    progressbar1.setVisibility(View.GONE);
                    textview1.setVisibility(View.GONE);
                    textview2.setVisibility(View.GONE);
                    linear2.setVisibility(View.VISIBLE);
                    if (settings_sp.getString("language", "").contains("english")) {
                        textview3.setText("Failed to install the modpack because the cache was not found, most likely the blitz is installed on an external memory card.\n\nReinstall the game by selecting \"Internal memory\" during installation");
                        textview4.setText("P.S. You can install the modpack yourself\n\nIn the memory of your device, in the MTG MODS folder, there is an modpack.zip archive\nIt must be unpacked along the way:\n/Android/data/");
                    }
                }
            }
        };

        _fbss_delete_success_listener = new OnSuccessListener() {
            @Override
            public void onSuccess(Object _param1) {

            }
        };

        _fbss_failure_listener = new OnFailureListener() {
            @Override
            public void onFailure(Exception _param1) {
                final String _message = _param1.getMessage();

            }
        };

        _fbs_child_listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);

            }

            @Override
            public void onChildChanged(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);

            }

            @Override
            public void onChildMoved(DataSnapshot _param1, String _param2) {

            }

            @Override
            public void onChildRemoved(DataSnapshot _param1) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);

            }

            @Override
            public void onCancelled(DatabaseError _param1) {
                final int _errorCode = _param1.getCode();
                final String _errorMessage = _param1.getMessage();

            }
        };
        fbs.addChildEventListener(_fbs_child_listener);
    }

    private void initializeLogic() {
        if (SketchwareUtil.isConnected(getApplicationContext())) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            buildd = Build.VERSION.RELEASE;
            d = new AlertDialog.Builder(this,AlertDialog.THEME_TRADITIONAL);
            d.setCancelable(false);
            linear2.setVisibility(View.GONE);
            textview5.setVisibility(View.GONE);
            first.setVisibility(View.GONE);
            if (settings_sp.getString("ads", "").contains("false")) {

            }
            else {
                UnityAds.initialize(SolyankaActivity.this, GameID, TestMode, new IUnityAdsInitializationListener() {
                    @Override
                    public void onInitializationComplete() {


                    }

                    @Override
                    public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

                    }
                });
            }
            if (settings_sp.getString("language", "").contains("english")) {
                textview1.setText("Downloading modpack...");
                textview5.setText("  Back  ");
                textview6.setText("  Back  ");
            }
            fbs.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot _dataSnapshot) {
                    info = new ArrayList<>();
                    try {
                        GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                        for (DataSnapshot _data : _dataSnapshot.getChildren()) {
                            HashMap<String, Object> _map = _data.getValue(_ind);
                            info.add(_map);
                        }
                    }
                    catch (Exception _e) {
                        _e.printStackTrace();
                    }
                    url = info.get((int)0).get("url").toString();
                    version = info.get((int)0).get("version").toString();
                    original = modpacks.getString("solyanka", "");
                    if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/MTG MODS/modpack.zip"))) {
                        if (version.equals(original)) {
                            if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/Android/data/net.wargaming.wot.blitz/files/packs/dvpl_file_info_cache.txt"))) {
                                if (Build.VERSION.SDK_INT > 29) {
                                    if (settings_sp.getString("language", "").contains("english")) {
                                        d.setTitle("Android ".concat(buildd.concat(" detected!")));
                                        d.setIcon(R.drawable.ic_report_problem_white);
                                        d.setMessage("Unfortunately, automatic installation of the modpack is not possible on your device.\n\nIn the memory of your device, in the MTG MODS folder there is an archive modpack.zip\n\nIt must be unpacked along the way:\n/Android/data/");
                                        d.create().show();
                                    }
                                    else {
                                        d.setTitle("Обнаружен Android ".concat(buildd.concat("!")));
                                        d.setIcon(R.drawable.ic_report_problem_white);
                                        d.setMessage("К сожалению, автоматическая установка модпака не возможна на вашем устройстве.\n\nВ памяти вашего устройства, в папке MTG MODS есть архив modpack.zip\n\nЕго необходимо распаковать по пути:\n/Android/data/");
                                        d.create().show();
                                    }
                                }
                                else {
                                    progressbar1.setVisibility(View.GONE);
                                    textview2.setVisibility(View.GONE);
                                    if (settings_sp.getString("language", "").contains("english")) {
                                        textview1.setText("Installing the modpack...");
                                    }
                                    else {
                                        textview1.setText("Установка модпака...");
                                    }
                                    t = new TimerTask() {
                                        @Override
                                        public void run() {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    _UnZip(FileUtil.getExternalStorageDir().concat("/MTG MODS/modpack.zip"), FileUtil.getExternalStorageDir().concat("/Android/data/"));
                                                    t = new TimerTask() {
                                                        @Override
                                                        public void run() {
                                                            runOnUiThread(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    modpacks.edit().putString("solyanka", version).commit();
                                                                    installer.edit().putString("modpack", "solyanka").commit();
                                                                    if (settings_sp.getString("language", "").contains("english")) {
                                                                        textview1.setText("Modpack successfully installed!");
                                                                    }
                                                                    else {
                                                                        textview1.setText("Модпак успешно установлен!");
                                                                    }
                                                                    textview5.setVisibility(View.VISIBLE);
                                                                    t = new TimerTask() {
                                                                        @Override
                                                                        public void run() {
                                                                            runOnUiThread(new Runnable() {
                                                                                @Override
                                                                                public void run() {
                                                                                    if (settings_sp.getString("ads", "").contains("false")) {

                                                                                    }
                                                                                    else {
                                                                                        IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
                                                                                            @Override
                                                                                            public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                                                                                                UnityAds.load(ADID);
                                                                                                UnityAds.show(SolyankaActivity.this, ADID);
                                                                                            }

                                                                                            @Override
                                                                                            public void onUnityAdsShowStart(String s) {

                                                                                            }

                                                                                            @Override
                                                                                            public void onUnityAdsShowClick(String s) {

                                                                                            }

                                                                                            @Override
                                                                                            public void onUnityAdsShowComplete(String s, UnityAds.UnityAdsShowCompletionState state) {

                                                                                            }
                                                                                        };
                                                                                        UnityAds.load(ADID);
                                                                                        UnityAds.show(SolyankaActivity.this, ADID);
                                                                                    }
                                                                                }
                                                                            });
                                                                        }
                                                                    };
                                                                    _timer.schedule(t, (int)(50));
                                                                }
                                                            });
                                                        }
                                                    };
                                                    _timer.schedule(t, (int)(15000));
                                                }
                                            });
                                        }
                                    };
                                    _timer.schedule(t, (int)(1000));
                                }
                            }
                            else {
                                if (settings_sp.getString("language", "").contains("english")) {
                                    linear2.setVisibility(View.VISIBLE);
                                    textview3.setText("Failed to install the modpack because the cache was not found, most likely the blitz is installed on an external memory card.\n\nReinstall the game by selecting \"Internal memory\" during installation");
                                    textview4.setText("P.S. You can install the modpack yourself\n\nIn the memory of your device, in the MTG MODS folder, there is an modpack.zip archive\nIt must be unpacked along the way:\n/Android/data/");
                                }
                                else {
                                    linear2.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                        else {
                            _firebase_storage.getReferenceFromUrl(url).getFile(new File(FileUtil.getExternalStorageDir().concat("/MTG MODS/modpack.zip"))).addOnSuccessListener(_fbss_download_success_listener).addOnFailureListener(_fbss_failure_listener).addOnProgressListener(_fbss_download_progress_listener);
                        }
                    }
                    else {
                        _firebase_storage.getReferenceFromUrl(url).getFile(new File(FileUtil.getExternalStorageDir().concat("/MTG MODS/modpack.zip"))).addOnSuccessListener(_fbss_download_success_listener).addOnFailureListener(_fbss_failure_listener).addOnProgressListener(_fbss_download_progress_listener);
                    }
                }
                @Override
                public void onCancelled(DatabaseError _databaseError) {
                }
            });
        }
        else {
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
    public void onStart() {
        super.onStart();
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        textview5.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
        textview6.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
    }

    @Override
    public void onBackPressed() {

    }
    public void _UnZip (final String _fileZip, final String _destDir) {
        try
        {
            java.io.File outdir = new java.io.File(_destDir);
            java.util.zip.ZipInputStream zin = new java.util.zip.ZipInputStream(new java.io.FileInputStream(_fileZip));
            java.util.zip.ZipEntry entry;
            String name, dir;
            while ((entry = zin.getNextEntry()) != null)
            {
                name = entry.getName();
                if(entry.isDirectory())
                {
                    mkdirs(outdir, name);
                    continue;
                }

                /* this part is necessary because file entry can come before
                 * directory entry where is file located
                 * i.e.:
                 * /foo/foo.txt
                 * /foo/
                 */

                dir = dirpart(name);
                if(dir != null)
                    mkdirs(outdir, dir);

                extractFile(zin, outdir, name);
            }
            zin.close();
        }
        catch (java.io.IOException e)
        {
            e.printStackTrace();
        }
    }
    private static void extractFile(java.util.zip.ZipInputStream in, java.io.File outdir, String name) throws java.io.IOException
    {
        byte[] buffer = new byte[4096];
        java.io.BufferedOutputStream out = new java.io.BufferedOutputStream(new java.io.FileOutputStream(new java.io.File(outdir, name)));
        int count = -1;
        while ((count = in.read(buffer)) != -1)
            out.write(buffer, 0, count);
        out.close();
    }

    private static void mkdirs(java.io.File outdir, String path)
    {
        java.io.File d = new java.io.File(outdir, path);
        if(!d.exists())
            d.mkdirs();
    }

    private static String dirpart(String name)
    {
        int s = name.lastIndexOf(java.io.File.separatorChar);
        return s == -1 ? null : name.substring(0, s);
    }


    public void _extra () {
    }
    VideoView vidview;
    MediaController mediaControls;
    {
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
}
