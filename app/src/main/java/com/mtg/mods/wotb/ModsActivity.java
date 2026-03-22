package com.mtg.mods.wotb;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class ModsActivity extends AppCompatActivity {
    private Timer _timer = new Timer();
    private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();

    private String buildd = "";
    private String str = "";
    private String hangar = "";
    private String check_hangar = "";

    private LinearLayout background;
    private LinearLayout first;
    private LinearLayout content;
    private LinearLayout linear1;
    private LinearLayout linear2;
    private TextView textview2;
    private ProgressBar progressbar1;
    private TextView textview3;
    private TextView textview7;
    private ScrollView vscroll1;
    private LinearLayout linear4;
    private TextView textview5;
    private ImageView imageview2;
    private TextView textview6;
    private TextView textview8;

    private StorageReference fbs = _firebase_storage.getReference("Mods");
    private OnCompleteListener<Uri> _fbs_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _fbs_download_success_listener;
    private OnSuccessListener _fbs_delete_success_listener;
    private OnProgressListener _fbs_upload_progress_listener;
    private OnProgressListener _fbs_download_progress_listener;
    private OnFailureListener _fbs_failure_listener;
    private TimerTask t;
    private SharedPreferences sh;
    private AlertDialog.Builder d;
    private Intent i = new Intent();
    private SharedPreferences settings;

    String GameID = "4595401";
    String ADID = "Interstitial_Android";
    Boolean TestMode = false;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.mods);
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
        linear2 = (LinearLayout) findViewById(R.id.linear2);
        textview2 = (TextView) findViewById(R.id.textview2);
        progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
        textview3 = (TextView) findViewById(R.id.textview3);
        textview7 = (TextView) findViewById(R.id.textview7);
        vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
        linear4 = (LinearLayout) findViewById(R.id.linear4);
        textview5 = (TextView) findViewById(R.id.textview5);
        imageview2 = (ImageView) findViewById(R.id.imageview2);
        textview6 = (TextView) findViewById(R.id.textview6);
        textview8 = (TextView) findViewById(R.id.textview8);
        sh = getSharedPreferences("mods", Activity.MODE_PRIVATE);
        d = new AlertDialog.Builder(this);
        settings = getSharedPreferences("settings", Activity.MODE_PRIVATE);

        textview7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

        textview8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });

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
                progressbar1.setProgress((int)_progressValue);
                textview3.setText(String.valueOf((long)(_progressValue)).concat("%"));
                sh.edit().remove("url").commit();
                sh.edit().remove("image").commit();
                sh.edit().remove("category").commit();
                t = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (textview3.getText().toString().equals("0%")) {
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
                progressbar1.setVisibility(View.GONE);
                textview3.setVisibility(View.GONE);
                if (Build.VERSION.SDK_INT > 29) {
                    i.setClass(getApplicationContext(), Android11ModActivity.class);
                    startActivity(i);
                    overridePendingTransition(0, 0);
                } else {
                    if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/Android/data/net.wargaming.wot.blitz/files/packs/dvpl_file_info_cache.txt"))) {
                        if (settings.getString("language", "").contains("english")) {
                            textview2.setText("Installing mod...");
                        }
                        else {
                            textview2.setText("Установка мода...");
                        }
                        if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/MTG MODS/hangars"))) {
                            FileUtil.deleteFile(FileUtil.getExternalStorageDir().concat("/MTG MODS/hangars"));
                            t = new TimerTask() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            _UnZip(FileUtil.getExternalStorageDir().concat("/MTG MODS/mod.zip"), FileUtil.getExternalStorageDir().concat("/Android/data/"));
                                            if (settings.getString("language", "").contains("english")) {
                                                SketchwareUtil.showMessage(getApplicationContext(), "Please wait...");
                                            }
                                            else {
                                                SketchwareUtil.showMessage(getApplicationContext(), "Пожалуйста, подождите...");
                                            }
                                            t = new TimerTask() {
                                                @Override
                                                public void run() {
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            FileUtil.deleteFile(FileUtil.getExternalStorageDir().concat("/Android/data/net.wargaming.wot.blitz/files/packs/3d/Maps/hangar"));
                                                            t = new TimerTask() {
                                                                @Override
                                                                public void run() {
                                                                    runOnUiThread(new Runnable() {
                                                                        @Override
                                                                        public void run() {
                                                                            _rename_in(FileUtil.getExternalStorageDir().concat("/Android/data/net.wargaming.wot.blitz/files/packs/3d/Maps"), "hangar_prem_2018", "hangar");
                                                                            t = new TimerTask() {
                                                                                @Override
                                                                                public void run() {
                                                                                    runOnUiThread(new Runnable() {
                                                                                        @Override
                                                                                        public void run() {
                                                                                            _UnZip(FileUtil.getExternalStorageDir().concat("/MTG MODS/mod.zip"), FileUtil.getExternalStorageDir().concat("/Android/data/"));
                                                                                            t = new TimerTask() {
                                                                                                @Override
                                                                                                public void run() {
                                                                                                    runOnUiThread(new Runnable() {
                                                                                                        @Override
                                                                                                        public void run() {
                                                                                                            FileUtil.deleteFile(FileUtil.getExternalStorageDir().concat("/MTG MODS/mod.zip"));
                                                                                                            textview7.setVisibility(View.VISIBLE);
                                                                                                            if (settings.getString("language", "").contains("english")) {
                                                                                                                textview2.setText("Mod installed!");
                                                                                                            }
                                                                                                            else {
                                                                                                                textview2.setText("Мод установлен!");
                                                                                                            }
                                                                                                            if (settings.getString("ads", "").contains("false")) {

                                                                                                            }
                                                                                                            else {
                                                                                                                IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
                                                                                                                    @Override
                                                                                                                    public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                                                                                                                        UnityAds.load(ADID);
                                                                                                                        UnityAds.show(ModsActivity.this, ADID);
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
                                                                                                                UnityAds.show(ModsActivity.this, ADID);
                                                                                                            }
                                                                                                            textview7.setVisibility(View.VISIBLE);
                                                                                                        }
                                                                                                    });
                                                                                                }
                                                                                            };
                                                                                            _timer.schedule(t, (int)(50));
                                                                                        }
                                                                                    });
                                                                                }
                                                                            };
                                                                            _timer.schedule(t, (int)(50));
                                                                        }
                                                                    });
                                                                }
                                                            };
                                                            _timer.schedule(t, (int)(500));
                                                        }
                                                    });
                                                }
                                            };
                                            _timer.schedule(t, (int)(50));
                                        }
                                    });
                                }
                            };
                            _timer.schedule(t, (int)(500));
                        }
                        else {
                            t = new TimerTask() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            _UnZip(FileUtil.getExternalStorageDir().concat("/MTG MODS/mod.zip"), FileUtil.getExternalStorageDir().concat("/Android/data/"));
                                            t = new TimerTask() {
                                                @Override
                                                public void run() {
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            FileUtil.deleteFile(FileUtil.getExternalStorageDir().concat("/MTG MODS/mod.zip"));
                                                            if (settings.getString("language", "").contains("english")) {
                                                                textview2.setText("Mod installed!");
                                                            }
                                                            else {
                                                                textview2.setText("Мод установлен!");
                                                            }
                                                            textview7.setVisibility(View.VISIBLE);
                                                            if (settings.getString("ads", "").contains("false")) {

                                                            }
                                                            else {
                                                                IUnityAdsShowListener iUnityAdsShowListener = new IUnityAdsShowListener() {
                                                                    @Override
                                                                    public void onUnityAdsShowFailure(String s, UnityAds.UnityAdsShowError unityAdsShowError, String s1) {
                                                                        UnityAds.load(ADID);
                                                                        UnityAds.show(ModsActivity.this, ADID);
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
                                                                UnityAds.show(ModsActivity.this, ADID);
                                                            }
                                                        }
                                                    });
                                                }
                                            };
                                            _timer.schedule(t, (int)(3000));
                                        }
                                    });
                                }
                            };
                            _timer.schedule(t, (int)(500));
                        }
                    }
                    else {
                        vscroll1.setVisibility(View.VISIBLE);
                        textview2.setVisibility(View.GONE);
                        textview3.setVisibility(View.GONE);
                        progressbar1.setVisibility(View.GONE);
                        if (settings.getString("language", "").contains("english")) {
                            textview5.setText("Failed to install the mod, because the cache was not found, most likely the blitz is installed on an external memory card.\n\nReinstall the game by selecting \"Internal memory\" during installation");
                            textview6.setText("P.S. You can install the mod manually.\n\nIn the memory of your device, in the MTG MODS folder, there is an mod.zip archive\nIt must be unpacked along the way:\n/Android/data/");
                        }
                    }
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
            d = new AlertDialog.Builder(this,AlertDialog.THEME_TRADITIONAL);
            d.setCancelable(false);
            buildd = Build.VERSION.RELEASE;
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            if (settings.getString("ads", "").contains("false")) {

            }
            else {
                UnityAds.initialize(ModsActivity.this, GameID, TestMode, new IUnityAdsInitializationListener() {
                    @Override
                    public void onInitializationComplete() {


                    }

                    @Override
                    public void onInitializationFailed(UnityAds.UnityAdsInitializationError unityAdsInitializationError, String s) {

                    }
                });
            }
            if (settings.getString("language", "").contains("english")) {
                textview2.setText("Downloading the mod...");
                textview7.setText("  Back  ");
                textview8.setText("  Back  ");
            }
            _firebase_storage.getReferenceFromUrl(sh.getString("url", "")).getFile(new File(FileUtil.getExternalStorageDir().concat("/MTG MODS/mod.zip"))).addOnSuccessListener(_fbs_download_success_listener).addOnFailureListener(_fbs_failure_listener).addOnProgressListener(_fbs_download_progress_listener);
            vscroll1.setVisibility(View.GONE);
            textview7.setVisibility(View.GONE);
            first.setVisibility(View.GONE);
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
    public void onBackPressed() {

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
        textview7.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
        textview8.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
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


    public void _rename_in (final String _directory, final String _file1, final String _file2) {
        java.io.File directory = new
                java.io.File(_directory);

        java.io.File oldfile = new
                java.io.File(directory, _file1);
        java.io.File newfile = new
                java.io.File(directory, _file2);

        oldfile.renameTo(newfile);
    }


    public void _Zip (final String _from, final String _to) {
        if (FileUtil.isExistFile(_to)) {
            FileUtil.deleteFile(_to);
        }
        try {
            ZipDirMain(_from, _to);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    private static void ZipDirMain(
            String infile,
            String outfile)
            throws java.io.IOException {

        java.io.FileOutputStream fos = new java.io.FileOutputStream(outfile);
        java.util.zip.ZipOutputStream zipOut = new java.util.zip.ZipOutputStream(fos);

        java.io.File fileToZip = new java.io.File(infile);

        zipFile(fileToZip, fileToZip.getName(), zipOut);

        zipOut.close();
        fos.close();
    }

    private static void zipFile(
            java.io.File fileToZip,
            String fileName,
            java.util.zip.ZipOutputStream zipOut)
            throws java.io.IOException {

        if (fileToZip.isHidden()) {
            //return;
        }

        if (fileToZip.isDirectory()) {
            if (fileName.endsWith("/")) {
                zipOut.putNextEntry(new java.util.zip.ZipEntry(fileName));
            } else {
                zipOut.putNextEntry(new java.util.zip.ZipEntry(fileName + "/"));
            }
            zipOut.closeEntry();
            java.io.File[] children = fileToZip.listFiles();
            for (java.io.File childFile : children) {
                zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
            }
            return;
        }

        java.util.zip.ZipEntry zipEntry = new java.util.zip.ZipEntry(fileName);
        zipOut.putNextEntry(zipEntry);

        java.io.FileInputStream fis = new java.io.FileInputStream(fileToZip);

        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        zipOut.write(bytes);
        fis.close();
        zipOut.closeEntry();
    }


    public void _mute () {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);

    }

    AudioManager audioManager;

    private void nothing() {

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

    private boolean isConnected(){

        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo!=null && networkInfo.isConnected();


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
