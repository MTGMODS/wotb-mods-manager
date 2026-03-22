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
import java.util.HashMap;
import java.util.ArrayList;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ProgressBar;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.OnProgressListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Continuation;
import android.net.Uri;
import java.io.File;
import android.app.Activity;
import android.content.SharedPreferences;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.content.Intent;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


public class UploadMod6Activity extends AppCompatActivity {

    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
    private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();

    private String urlzip = "";
    private String urlimage = "";
    private HashMap<String, Object> map = new HashMap<>();

    private ArrayList<HashMap<String, Object>> listmap = new ArrayList<>();

    private LinearLayout linear1;
    private LinearLayout linear2;
    private TextView textview1;
    private ProgressBar progressbar1;
    private TextView textview2;

    private StorageReference fbs = _firebase_storage.getReference("Mods/Hangars");
    private OnCompleteListener<Uri> _fbs_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _fbs_download_success_listener;
    private OnSuccessListener _fbs_delete_success_listener;
    private OnProgressListener _fbs_upload_progress_listener;
    private OnProgressListener _fbs_download_progress_listener;
    private OnFailureListener _fbs_failure_listener;
    private SharedPreferences sh;
    private Calendar cl = Calendar.getInstance();
    private StorageReference fbs2 = _firebase_storage.getReference("Mods/Hangars");
    private OnCompleteListener<Uri> _fbs2_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _fbs2_download_success_listener;
    private OnSuccessListener _fbs2_delete_success_listener;
    private OnProgressListener _fbs2_upload_progress_listener;
    private OnProgressListener _fbs2_download_progress_listener;
    private OnFailureListener _fbs2_failure_listener;
    private DatabaseReference fbs0 = _firebase.getReference("Mods/Hangars");
    private ChildEventListener _fbs0_child_listener;
    private DatabaseReference fbs01 = _firebase.getReference("Mods/Sight");
    private ChildEventListener _fbs01_child_listener;
    private StorageReference sight = _firebase_storage.getReference("Mods/Sight");
    private OnCompleteListener<Uri> _sight_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _sight_download_success_listener;
    private OnSuccessListener _sight_delete_success_listener;
    private OnProgressListener _sight_upload_progress_listener;
    private OnProgressListener _sight_download_progress_listener;
    private OnFailureListener _sight_failure_listener;
    private StorageReference sight2 = _firebase_storage.getReference("Mods/Sight");
    private OnCompleteListener<Uri> _sight2_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _sight2_download_success_listener;
    private OnSuccessListener _sight2_delete_success_listener;
    private OnProgressListener _sight2_upload_progress_listener;
    private OnProgressListener _sight2_download_progress_listener;
    private OnFailureListener _sight2_failure_listener;
    private DatabaseReference fbs02 = _firebase.getReference("Mods/Skins");
    private ChildEventListener _fbs02_child_listener;
    private StorageReference skins = _firebase_storage.getReference("Mods/Skins");
    private OnCompleteListener<Uri> _skins_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _skins_download_success_listener;
    private OnSuccessListener _skins_delete_success_listener;
    private OnProgressListener _skins_upload_progress_listener;
    private OnProgressListener _skins_download_progress_listener;
    private OnFailureListener _skins_failure_listener;
    private StorageReference skins2 = _firebase_storage.getReference("Mods/Skins");
    private OnCompleteListener<Uri> _skins2_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _skins2_download_success_listener;
    private OnSuccessListener _skins2_delete_success_listener;
    private OnProgressListener _skins2_upload_progress_listener;
    private OnProgressListener _skins2_download_progress_listener;
    private OnFailureListener _skins2_failure_listener;
    private DatabaseReference fbs03 = _firebase.getReference("Mods/Remodeling");
    private ChildEventListener _fbs03_child_listener;
    private StorageReference remod = _firebase_storage.getReference("Mods/Remodeling");
    private OnCompleteListener<Uri> _remod_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _remod_download_success_listener;
    private OnSuccessListener _remod_delete_success_listener;
    private OnProgressListener _remod_upload_progress_listener;
    private OnProgressListener _remod_download_progress_listener;
    private OnFailureListener _remod_failure_listener;
    private StorageReference remod2 = _firebase_storage.getReference("Mods/Remodeling");
    private OnCompleteListener<Uri> _remod2_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _remod2_download_success_listener;
    private OnSuccessListener _remod2_delete_success_listener;
    private OnProgressListener _remod2_upload_progress_listener;
    private OnProgressListener _remod2_download_progress_listener;
    private OnFailureListener _remod2_failure_listener;
    private DatabaseReference fbs04 = _firebase.getReference("Mods/SixSense");
    private ChildEventListener _fbs04_child_listener;
    private StorageReference six = _firebase_storage.getReference("Mods/SixSense");
    private OnCompleteListener<Uri> _six_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _six_download_success_listener;
    private OnSuccessListener _six_delete_success_listener;
    private OnProgressListener _six_upload_progress_listener;
    private OnProgressListener _six_download_progress_listener;
    private OnFailureListener _six_failure_listener;
    private StorageReference six2 = _firebase_storage.getReference("Mods/SixSense");
    private OnCompleteListener<Uri> _six2_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _six2_download_success_listener;
    private OnSuccessListener _six2_delete_success_listener;
    private OnProgressListener _six2_upload_progress_listener;
    private OnProgressListener _six2_download_progress_listener;
    private OnFailureListener _six2_failure_listener;
    private StorageReference interface1 = _firebase_storage.getReference("Mods/Interface");
    private OnCompleteListener<Uri> _interface1_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _interface1_download_success_listener;
    private OnSuccessListener _interface1_delete_success_listener;
    private OnProgressListener _interface1_upload_progress_listener;
    private OnProgressListener _interface1_download_progress_listener;
    private OnFailureListener _interface1_failure_listener;
    private StorageReference interface2 = _firebase_storage.getReference("Mods/Interface");
    private OnCompleteListener<Uri> _interface2_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _interface2_download_success_listener;
    private OnSuccessListener _interface2_delete_success_listener;
    private OnProgressListener _interface2_upload_progress_listener;
    private OnProgressListener _interface2_download_progress_listener;
    private OnFailureListener _interface2_failure_listener;
    private DatabaseReference fbs05 = _firebase.getReference("Mods/Interface");
    private ChildEventListener _fbs05_child_listener;
    private DatabaseReference fbs06 = _firebase.getReference("Mods/Sounds");
    private ChildEventListener _fbs06_child_listener;
    private StorageReference sounds = _firebase_storage.getReference("Mods/Sounds");
    private OnCompleteListener<Uri> _sounds_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _sounds_download_success_listener;
    private OnSuccessListener _sounds_delete_success_listener;
    private OnProgressListener _sounds_upload_progress_listener;
    private OnProgressListener _sounds_download_progress_listener;
    private OnFailureListener _sounds_failure_listener;
    private DatabaseReference fbs07 = _firebase.getReference("Mods/Others");
    private ChildEventListener _fbs07_child_listener;
    private StorageReference other1 = _firebase_storage.getReference("Mods/Others");
    private OnCompleteListener<Uri> _other1_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _other1_download_success_listener;
    private OnSuccessListener _other1_delete_success_listener;
    private OnProgressListener _other1_upload_progress_listener;
    private OnProgressListener _other1_download_progress_listener;
    private OnFailureListener _other1_failure_listener;
    private StorageReference other2 = _firebase_storage.getReference("Mods/Others");
    private OnCompleteListener<Uri> _other2_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _other2_download_success_listener;
    private OnSuccessListener _other2_delete_success_listener;
    private OnProgressListener _other2_upload_progress_listener;
    private OnProgressListener _other2_download_progress_listener;
    private OnFailureListener _other2_failure_listener;
    private Intent i = new Intent();
    private StorageReference vip1 = _firebase_storage.getReference("Mods/VIP");
    private OnCompleteListener<Uri> _vip1_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _vip1_download_success_listener;
    private OnSuccessListener _vip1_delete_success_listener;
    private OnProgressListener _vip1_upload_progress_listener;
    private OnProgressListener _vip1_download_progress_listener;
    private OnFailureListener _vip1_failure_listener;
    private StorageReference vip2 = _firebase_storage.getReference("Mods/VIP");
    private OnCompleteListener<Uri> _vip2_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _vip2_download_success_listener;
    private OnSuccessListener _vip2_delete_success_listener;
    private OnProgressListener _vip2_upload_progress_listener;
    private OnProgressListener _vip2_download_progress_listener;
    private OnFailureListener _vip2_failure_listener;
    private DatabaseReference vip_fbs = _firebase.getReference("Mods/Vip");
    private ChildEventListener _vip_fbs_child_listener;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_upload_mod6);
        initialize(_savedInstanceState);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        linear2 = (LinearLayout) findViewById(R.id.linear2);
        textview1 = (TextView) findViewById(R.id.textview1);
        progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
        textview2 = (TextView) findViewById(R.id.textview2);
        sh = getSharedPreferences("mod", Activity.MODE_PRIVATE);

        _fbs_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                progressbar1.setProgress((int)_progressValue);
                textview2.setText(String.valueOf((long)(_progressValue)).concat("%"));
                textview1.setText("Выгрузка файлов...\n[1 из 2]");
            }
        };

        _fbs_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();

            }
        };

        _fbs_upload_success_listener = new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> _param1) {
                final String _downloadUrl = _param1.getResult().toString();
                urlzip = _downloadUrl;
                fbs2.child("image".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".png")).putFile(Uri.fromFile(new File(sh.getString("image", "")))).addOnFailureListener(_fbs2_failure_listener).addOnProgressListener(_fbs2_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                        return fbs2.child("image".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".png")).getDownloadUrl();
                    }}).addOnCompleteListener(_fbs2_upload_success_listener);
            }
        };

        _fbs_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
                final long _totalByteCount = _param1.getTotalByteCount();

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

        _fbs2_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                progressbar1.setProgress((int)_progressValue);
                textview2.setText(String.valueOf((long)(_progressValue)).concat("%"));
                textview1.setText("Выгрузка файлов...\n[2 из 2]");
            }
        };

        _fbs2_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();

            }
        };

        _fbs2_upload_success_listener = new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> _param1) {
                final String _downloadUrl = _param1.getResult().toString();
                textview1.setText("Публикация мода...");
                textview2.setVisibility(View.GONE);
                progressbar1.setVisibility(View.GONE);
                map = new HashMap<>();
                map.put("image", _downloadUrl);
                map.put("url", urlzip);
                map.put("mod", sh.getString("mod", ""));
                map.put("author", sh.getString("author", ""));
                map.put("version", sh.getString("version", ""));
                map.put("date", new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime()));
                fbs0.push().updateChildren(map);
                Toast.makeText(UploadMod6Activity.this, "Мод успешно добавлен!", Toast.LENGTH_SHORT).show();
                i.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        };

        _fbs2_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
                final long _totalByteCount = _param1.getTotalByteCount();

            }
        };

        _fbs2_delete_success_listener = new OnSuccessListener() {
            @Override
            public void onSuccess(Object _param1) {

            }
        };

        _fbs2_failure_listener = new OnFailureListener() {
            @Override
            public void onFailure(Exception _param1) {
                final String _message = _param1.getMessage();

            }
        };

        _fbs0_child_listener = new ChildEventListener() {
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
        fbs0.addChildEventListener(_fbs0_child_listener);

        _fbs01_child_listener = new ChildEventListener() {
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
        fbs01.addChildEventListener(_fbs01_child_listener);

        _sight_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                progressbar1.setProgress((int)_progressValue);
                textview2.setText(String.valueOf((long)(_progressValue)).concat("%"));
                textview1.setText("Выгрузка файлов...\n[1 из 2]");
            }
        };

        _sight_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();

            }
        };

        _sight_upload_success_listener = new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> _param1) {
                final String _downloadUrl = _param1.getResult().toString();
                urlzip = _downloadUrl;
                sight2.child("image".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".png")).putFile(Uri.fromFile(new File(sh.getString("image", "")))).addOnFailureListener(_sight2_failure_listener).addOnProgressListener(_sight2_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                        return sight2.child("image".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".png")).getDownloadUrl();
                    }}).addOnCompleteListener(_sight2_upload_success_listener);
            }
        };

        _sight_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
                final long _totalByteCount = _param1.getTotalByteCount();

            }
        };

        _sight_delete_success_listener = new OnSuccessListener() {
            @Override
            public void onSuccess(Object _param1) {

            }
        };

        _sight_failure_listener = new OnFailureListener() {
            @Override
            public void onFailure(Exception _param1) {
                final String _message = _param1.getMessage();

            }
        };

        _sight2_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                progressbar1.setProgress((int)_progressValue);
                textview2.setText(String.valueOf((long)(_progressValue)).concat("%"));
                textview1.setText("Выгрузка файлов...\n[2 из 2]");
            }
        };

        _sight2_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();

            }
        };

        _sight2_upload_success_listener = new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> _param1) {
                final String _downloadUrl = _param1.getResult().toString();
                textview1.setText("Публикация мода...");
                textview2.setVisibility(View.GONE);
                progressbar1.setVisibility(View.GONE);
                map = new HashMap<>();
                map.put("image", _downloadUrl);
                map.put("url", urlzip);
                map.put("mod", sh.getString("mod", ""));
                map.put("author", sh.getString("author", ""));
                map.put("version", sh.getString("version", ""));
                map.put("date", new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime()));
                fbs01.push().updateChildren(map);
                Toast.makeText(UploadMod6Activity.this, "Мод успешно добавлен!", Toast.LENGTH_SHORT).show();
                i.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        };

        _sight2_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
                final long _totalByteCount = _param1.getTotalByteCount();

            }
        };

        _sight2_delete_success_listener = new OnSuccessListener() {
            @Override
            public void onSuccess(Object _param1) {

            }
        };

        _sight2_failure_listener = new OnFailureListener() {
            @Override
            public void onFailure(Exception _param1) {
                final String _message = _param1.getMessage();

            }
        };

        _fbs02_child_listener = new ChildEventListener() {
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
        fbs02.addChildEventListener(_fbs02_child_listener);

        _skins_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                progressbar1.setProgress((int)_progressValue);
                textview2.setText(String.valueOf((long)(_progressValue)).concat("%"));
                textview1.setText("Выгрузка файлов...\n[1 из 2]");
            }
        };

        _skins_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();

            }
        };

        _skins_upload_success_listener = new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> _param1) {
                final String _downloadUrl = _param1.getResult().toString();
                urlzip = _downloadUrl;
                skins2.child("image".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".png")).putFile(Uri.fromFile(new File(sh.getString("image", "")))).addOnFailureListener(_skins2_failure_listener).addOnProgressListener(_skins2_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                        return skins2.child("image".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".png")).getDownloadUrl();
                    }}).addOnCompleteListener(_skins2_upload_success_listener);
            }
        };

        _skins_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
                final long _totalByteCount = _param1.getTotalByteCount();

            }
        };

        _skins_delete_success_listener = new OnSuccessListener() {
            @Override
            public void onSuccess(Object _param1) {

            }
        };

        _skins_failure_listener = new OnFailureListener() {
            @Override
            public void onFailure(Exception _param1) {
                final String _message = _param1.getMessage();

            }
        };

        _skins2_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                progressbar1.setProgress((int)_progressValue);
                textview2.setText(String.valueOf((long)(_progressValue)).concat("%"));
                textview1.setText("Выгрузка файлов...\n[2 из 2]");
            }
        };

        _skins2_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();

            }
        };

        _skins2_upload_success_listener = new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> _param1) {
                final String _downloadUrl = _param1.getResult().toString();
                textview1.setText("Публикация мода...");
                textview2.setVisibility(View.GONE);
                progressbar1.setVisibility(View.GONE);
                map = new HashMap<>();
                map.put("image", _downloadUrl);
                map.put("url", urlzip);
                map.put("mod", sh.getString("mod", ""));
                map.put("author", sh.getString("author", ""));
                map.put("version", sh.getString("version", ""));
                map.put("date", new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime()));
                fbs02.push().updateChildren(map);
                Toast.makeText(UploadMod6Activity.this, "Мод успешно добавлен!", Toast.LENGTH_SHORT).show();
                i.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        };

        _skins2_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
                final long _totalByteCount = _param1.getTotalByteCount();

            }
        };

        _skins2_delete_success_listener = new OnSuccessListener() {
            @Override
            public void onSuccess(Object _param1) {

            }
        };

        _skins2_failure_listener = new OnFailureListener() {
            @Override
            public void onFailure(Exception _param1) {
                final String _message = _param1.getMessage();

            }
        };

        _fbs03_child_listener = new ChildEventListener() {
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
        fbs03.addChildEventListener(_fbs03_child_listener);

        _remod_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                progressbar1.setProgress((int)_progressValue);
                textview2.setText(String.valueOf((long)(_progressValue)).concat("%"));
                textview1.setText("Выгрузка файлов...\n[1 из 2]");
            }
        };

        _remod_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();

            }
        };

        _remod_upload_success_listener = new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> _param1) {
                final String _downloadUrl = _param1.getResult().toString();
                urlzip = _downloadUrl;
                remod2.child("image".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".png")).putFile(Uri.fromFile(new File(sh.getString("image", "")))).addOnFailureListener(_remod2_failure_listener).addOnProgressListener(_remod2_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                        return remod2.child("image".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".png")).getDownloadUrl();
                    }}).addOnCompleteListener(_remod2_upload_success_listener);
            }
        };

        _remod_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
                final long _totalByteCount = _param1.getTotalByteCount();

            }
        };

        _remod_delete_success_listener = new OnSuccessListener() {
            @Override
            public void onSuccess(Object _param1) {

            }
        };

        _remod_failure_listener = new OnFailureListener() {
            @Override
            public void onFailure(Exception _param1) {
                final String _message = _param1.getMessage();

            }
        };

        _remod2_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                progressbar1.setProgress((int)_progressValue);
                textview2.setText(String.valueOf((long)(_progressValue)).concat("%"));
                textview1.setText("Выгрузка файлов...\n[2 из 2]");
            }
        };

        _remod2_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();

            }
        };

        _remod2_upload_success_listener = new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> _param1) {
                final String _downloadUrl = _param1.getResult().toString();
                textview1.setText("Публикация мода...");
                textview2.setVisibility(View.GONE);
                progressbar1.setVisibility(View.GONE);
                map = new HashMap<>();
                map.put("image", _downloadUrl);
                map.put("url", urlzip);
                map.put("mod", sh.getString("mod", ""));
                map.put("author", sh.getString("author", ""));
                map.put("version", sh.getString("version", ""));
                map.put("date", new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime()));
                fbs03.push().updateChildren(map);
                Toast.makeText(UploadMod6Activity.this, "Мод успешно добавлен!", Toast.LENGTH_SHORT).show();
                i.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        };

        _remod2_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
                final long _totalByteCount = _param1.getTotalByteCount();

            }
        };

        _remod2_delete_success_listener = new OnSuccessListener() {
            @Override
            public void onSuccess(Object _param1) {

            }
        };

        _remod2_failure_listener = new OnFailureListener() {
            @Override
            public void onFailure(Exception _param1) {
                final String _message = _param1.getMessage();

            }
        };

        _fbs04_child_listener = new ChildEventListener() {
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
        fbs04.addChildEventListener(_fbs04_child_listener);

        _six_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                progressbar1.setProgress((int)_progressValue);
                textview2.setText(String.valueOf((long)(_progressValue)).concat("%"));
                textview1.setText("Выгрузка файлов...\n[1 из 2]");
            }
        };

        _six_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();

            }
        };

        _six_upload_success_listener = new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> _param1) {
                final String _downloadUrl = _param1.getResult().toString();
                urlzip = _downloadUrl;
                six2.child("image".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".png")).putFile(Uri.fromFile(new File(sh.getString("image", "")))).addOnFailureListener(_six2_failure_listener).addOnProgressListener(_six2_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                        return six2.child("image".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".png")).getDownloadUrl();
                    }}).addOnCompleteListener(_six2_upload_success_listener);
            }
        };

        _six_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
                final long _totalByteCount = _param1.getTotalByteCount();

            }
        };

        _six_delete_success_listener = new OnSuccessListener() {
            @Override
            public void onSuccess(Object _param1) {

            }
        };

        _six_failure_listener = new OnFailureListener() {
            @Override
            public void onFailure(Exception _param1) {
                final String _message = _param1.getMessage();

            }
        };

        _six2_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                progressbar1.setProgress((int)_progressValue);
                textview2.setText(String.valueOf((long)(_progressValue)).concat("%"));
                textview1.setText("Выгрузка файлов...\n[2 из 2]");
            }
        };

        _six2_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();

            }
        };

        _six2_upload_success_listener = new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> _param1) {
                final String _downloadUrl = _param1.getResult().toString();
                textview1.setText("Публикация мода...");
                textview2.setVisibility(View.GONE);
                progressbar1.setVisibility(View.GONE);
                map = new HashMap<>();
                map.put("image", _downloadUrl);
                map.put("url", urlzip);
                map.put("mod", sh.getString("mod", ""));
                map.put("author", sh.getString("author", ""));
                map.put("version", sh.getString("version", ""));
                map.put("date", new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime()));
                fbs04.push().updateChildren(map);
                Toast.makeText(UploadMod6Activity.this, "Мод успешно добавлен!", Toast.LENGTH_SHORT).show();
                i.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        };

        _six2_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
                final long _totalByteCount = _param1.getTotalByteCount();

            }
        };

        _six2_delete_success_listener = new OnSuccessListener() {
            @Override
            public void onSuccess(Object _param1) {

            }
        };

        _six2_failure_listener = new OnFailureListener() {
            @Override
            public void onFailure(Exception _param1) {
                final String _message = _param1.getMessage();

            }
        };

        _interface1_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                progressbar1.setProgress((int)_progressValue);
                textview2.setText(String.valueOf((long)(_progressValue)).concat("%"));
                textview1.setText("Выгрузка файлов...\n[1 из 2]");
            }
        };

        _interface1_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();

            }
        };

        _interface1_upload_success_listener = new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> _param1) {
                final String _downloadUrl = _param1.getResult().toString();
                urlzip = _downloadUrl;
                interface2.child("image".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".png")).putFile(Uri.fromFile(new File(sh.getString("image", "")))).addOnFailureListener(_interface2_failure_listener).addOnProgressListener(_interface2_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                        return interface2.child("image".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".png")).getDownloadUrl();
                    }}).addOnCompleteListener(_interface2_upload_success_listener);
            }
        };

        _interface1_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
                final long _totalByteCount = _param1.getTotalByteCount();

            }
        };

        _interface1_delete_success_listener = new OnSuccessListener() {
            @Override
            public void onSuccess(Object _param1) {

            }
        };

        _interface1_failure_listener = new OnFailureListener() {
            @Override
            public void onFailure(Exception _param1) {
                final String _message = _param1.getMessage();

            }
        };

        _interface2_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                progressbar1.setProgress((int)_progressValue);
                textview2.setText(String.valueOf((long)(_progressValue)).concat("%"));
                textview1.setText("Выгрузка файлов...\n[2 из 2]");
            }
        };

        _interface2_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();

            }
        };

        _interface2_upload_success_listener = new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> _param1) {
                final String _downloadUrl = _param1.getResult().toString();
                textview1.setText("Публикация мода...");
                textview2.setVisibility(View.GONE);
                progressbar1.setVisibility(View.GONE);
                map = new HashMap<>();
                map.put("image", _downloadUrl);
                map.put("url", urlzip);
                map.put("mod", sh.getString("mod", ""));
                map.put("author", sh.getString("author", ""));
                map.put("version", sh.getString("version", ""));
                map.put("date", new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime()));
                fbs05.push().updateChildren(map);
                Toast.makeText(UploadMod6Activity.this, "Мод успешно добавлен!", Toast.LENGTH_SHORT).show();
                i.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        };

        _interface2_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
                final long _totalByteCount = _param1.getTotalByteCount();

            }
        };

        _interface2_delete_success_listener = new OnSuccessListener() {
            @Override
            public void onSuccess(Object _param1) {

            }
        };

        _interface2_failure_listener = new OnFailureListener() {
            @Override
            public void onFailure(Exception _param1) {
                final String _message = _param1.getMessage();

            }
        };

        _fbs05_child_listener = new ChildEventListener() {
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
        fbs05.addChildEventListener(_fbs05_child_listener);

        _fbs06_child_listener = new ChildEventListener() {
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
        fbs06.addChildEventListener(_fbs06_child_listener);

        _sounds_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                progressbar1.setProgress((int)_progressValue);
                textview2.setText(String.valueOf((long)(_progressValue)).concat("%"));
                textview1.setText("Выгрузка файлов...\n[1 из 1]");
            }
        };

        _sounds_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();

            }
        };

        _sounds_upload_success_listener = new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> _param1) {
                final String _downloadUrl = _param1.getResult().toString();
                textview1.setText("Публикация мода...");
                textview2.setVisibility(View.GONE);
                progressbar1.setVisibility(View.GONE);
                map = new HashMap<>();
                map.put("url", _downloadUrl);
                map.put("mod", sh.getString("mod", ""));
                map.put("author", sh.getString("author", ""));
                map.put("version", sh.getString("version", ""));
                map.put("video", sh.getString("video", ""));
                map.put("date", new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime()));
                fbs06.push().updateChildren(map);
                Toast.makeText(UploadMod6Activity.this, "Мод успешно добавлен!", Toast.LENGTH_SHORT).show();
                i.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        };

        _sounds_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
                final long _totalByteCount = _param1.getTotalByteCount();

            }
        };

        _sounds_delete_success_listener = new OnSuccessListener() {
            @Override
            public void onSuccess(Object _param1) {

            }
        };

        _sounds_failure_listener = new OnFailureListener() {
            @Override
            public void onFailure(Exception _param1) {
                final String _message = _param1.getMessage();

            }
        };

        _fbs07_child_listener = new ChildEventListener() {
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
        fbs07.addChildEventListener(_fbs07_child_listener);

        _other1_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                progressbar1.setProgress((int)_progressValue);
                textview2.setText(String.valueOf((long)(_progressValue)).concat("%"));
                textview1.setText("Выгрузка файлов...\n[1 из 2]");
            }
        };

        _other1_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();

            }
        };

        _other1_upload_success_listener = new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> _param1) {
                final String _downloadUrl = _param1.getResult().toString();
                urlzip = _downloadUrl;
                other2.child("image".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".png")).putFile(Uri.fromFile(new File(sh.getString("image", "")))).addOnFailureListener(_other2_failure_listener).addOnProgressListener(_other2_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                        return other2.child("image".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".png")).getDownloadUrl();
                    }}).addOnCompleteListener(_other2_upload_success_listener);
            }
        };

        _other1_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
                final long _totalByteCount = _param1.getTotalByteCount();

            }
        };

        _other1_delete_success_listener = new OnSuccessListener() {
            @Override
            public void onSuccess(Object _param1) {

            }
        };

        _other1_failure_listener = new OnFailureListener() {
            @Override
            public void onFailure(Exception _param1) {
                final String _message = _param1.getMessage();

            }
        };

        _other2_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                progressbar1.setProgress((int)_progressValue);
                textview2.setText(String.valueOf((long)(_progressValue)).concat("%"));
                textview1.setText("Выгрузка файлов...\n[2 из 2]");
            }
        };

        _other2_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();

            }
        };

        _other2_upload_success_listener = new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> _param1) {
                final String _downloadUrl = _param1.getResult().toString();
                textview1.setText("Публикация мода...");
                textview2.setVisibility(View.GONE);
                progressbar1.setVisibility(View.GONE);
                map = new HashMap<>();
                map.put("image", _downloadUrl);
                map.put("url", urlzip);
                map.put("mod", sh.getString("mod", ""));
                map.put("author", sh.getString("author", ""));
                map.put("version", sh.getString("version", ""));
                map.put("date", new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime()));
                fbs07.push().updateChildren(map);
                Toast.makeText(UploadMod6Activity.this, "Мод успешно добавлен!", Toast.LENGTH_SHORT).show();
                i.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        };

        _other2_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
                final long _totalByteCount = _param1.getTotalByteCount();

            }
        };

        _other2_delete_success_listener = new OnSuccessListener() {
            @Override
            public void onSuccess(Object _param1) {

            }
        };

        _other2_failure_listener = new OnFailureListener() {
            @Override
            public void onFailure(Exception _param1) {
                final String _message = _param1.getMessage();

            }
        };

        _vip1_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                progressbar1.setProgress((int)_progressValue);
                textview2.setText(String.valueOf((long)(_progressValue)).concat("%"));
                textview1.setText("Выгрузка файлов...\n[1 из 2]");
            }
        };

        _vip1_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();

            }
        };

        _vip1_upload_success_listener = new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> _param1) {
                final String _downloadUrl = _param1.getResult().toString();
                urlzip = _downloadUrl;
                vip2.child("image".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".png")).putFile(Uri.fromFile(new File(sh.getString("image", "")))).addOnFailureListener(_vip2_failure_listener).addOnProgressListener(_vip2_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                        return vip2.child("image".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".png")).getDownloadUrl();
                    }}).addOnCompleteListener(_vip2_upload_success_listener);
            }
        };

        _vip1_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
                final long _totalByteCount = _param1.getTotalByteCount();

            }
        };

        _vip1_delete_success_listener = new OnSuccessListener() {
            @Override
            public void onSuccess(Object _param1) {

            }
        };

        _vip1_failure_listener = new OnFailureListener() {
            @Override
            public void onFailure(Exception _param1) {
                final String _message = _param1.getMessage();

            }
        };

        _vip2_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                progressbar1.setProgress((int)_progressValue);
                textview2.setText(String.valueOf((long)(_progressValue)).concat("%"));
                textview1.setText("Выгрузка файлов...\n[2 из 2]");
            }
        };

        _vip2_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();

            }
        };

        _vip2_upload_success_listener = new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> _param1) {
                final String _downloadUrl = _param1.getResult().toString();
                textview1.setText("Публикация мода...");
                textview2.setVisibility(View.GONE);
                progressbar1.setVisibility(View.GONE);
                map = new HashMap<>();
                map.put("image", _downloadUrl);
                map.put("url", urlzip);
                map.put("mod", sh.getString("mod", ""));
                map.put("author", sh.getString("author", ""));
                map.put("version", sh.getString("version", ""));
                map.put("date", new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime()));
                vip_fbs.push().updateChildren(map);
                Toast.makeText(UploadMod6Activity.this, "Мод успешно добавлен!", Toast.LENGTH_SHORT).show();
                i.setClass(getApplicationContext(), MenuActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        };

        _vip2_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
                final long _totalByteCount = _param1.getTotalByteCount();

            }
        };

        _vip2_delete_success_listener = new OnSuccessListener() {
            @Override
            public void onSuccess(Object _param1) {

            }
        };

        _vip2_failure_listener = new OnFailureListener() {
            @Override
            public void onFailure(Exception _param1) {
                final String _message = _param1.getMessage();

            }
        };

        _vip_fbs_child_listener = new ChildEventListener() {
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
        vip_fbs.addChildEventListener(_vip_fbs_child_listener);
    }

    public static boolean isConnected(Context _context) {
        ConnectivityManager _connectivityManager = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo _activeNetworkInfo = _connectivityManager.getActiveNetworkInfo();
        return _activeNetworkInfo != null && _activeNetworkInfo.isConnected();
    }

    private void initializeLogic() {
        linear2.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, 0xFF000000));
        cl = Calendar.getInstance();
        if (isConnected(getApplicationContext())) {
            if (sh.getString("category", "").equals("1")) {
                fbs.child("mod".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".zip")).putFile(Uri.fromFile(new File(sh.getString("archive", "")))).addOnFailureListener(_fbs_failure_listener).addOnProgressListener(_fbs_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                        return fbs.child("mod".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".zip")).getDownloadUrl();
                    }}).addOnCompleteListener(_fbs_upload_success_listener);
            }
            else {
                if (sh.getString("category", "").equals("2")) {
                    sight.child("mod".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".zip")).putFile(Uri.fromFile(new File(sh.getString("archive", "")))).addOnFailureListener(_sight_failure_listener).addOnProgressListener(_sight_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                            return sight.child("mod".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".zip")).getDownloadUrl();
                        }}).addOnCompleteListener(_sight_upload_success_listener);
                }
                else {
                    if (sh.getString("category", "").equals("3")) {
                        skins.child("mod".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".zip")).putFile(Uri.fromFile(new File(sh.getString("archive", "")))).addOnFailureListener(_skins_failure_listener).addOnProgressListener(_skins_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                                return skins.child("mod".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".zip")).getDownloadUrl();
                            }}).addOnCompleteListener(_skins_upload_success_listener);
                    }
                    else {
                        if (sh.getString("category", "").equals("4")) {
                            remod.child("mod".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".zip")).putFile(Uri.fromFile(new File(sh.getString("archive", "")))).addOnFailureListener(_remod_failure_listener).addOnProgressListener(_remod_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                @Override
                                public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                                    return remod.child("mod".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".zip")).getDownloadUrl();
                                }}).addOnCompleteListener(_remod_upload_success_listener);
                        }
                        else {
                            if (sh.getString("category", "").equals("5")) {
                                six.child("mod".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".zip")).putFile(Uri.fromFile(new File(sh.getString("archive", "")))).addOnFailureListener(_six_failure_listener).addOnProgressListener(_six_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                    @Override
                                    public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                                        return six.child("mod".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".zip")).getDownloadUrl();
                                    }}).addOnCompleteListener(_six_upload_success_listener);
                            }
                            else {
                                if (sh.getString("category", "").equals("6")) {
                                    interface1.child("mod".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".zip")).putFile(Uri.fromFile(new File(sh.getString("archive", "")))).addOnFailureListener(_interface1_failure_listener).addOnProgressListener(_interface1_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                        @Override
                                        public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                                            return interface1.child("mod".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".zip")).getDownloadUrl();
                                        }}).addOnCompleteListener(_interface1_upload_success_listener);
                                }
                                else {
                                    if (sh.getString("category", "").equals("7")) {
                                        sounds.child("mod".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".zip")).putFile(Uri.fromFile(new File(sh.getString("archive", "")))).addOnFailureListener(_sounds_failure_listener).addOnProgressListener(_sounds_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                            @Override
                                            public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                                                return sounds.child("mod".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".zip")).getDownloadUrl();
                                            }}).addOnCompleteListener(_sounds_upload_success_listener);
                                    }
                                    else {
                                        if (sh.getString("category", "").equals("8")) {
                                            other1.child("mod".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".zip")).putFile(Uri.fromFile(new File(sh.getString("archive", "")))).addOnFailureListener(_other1_failure_listener).addOnProgressListener(_other1_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                                @Override
                                                public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                                                    return other1.child("mod".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".zip")).getDownloadUrl();
                                                }}).addOnCompleteListener(_other1_upload_success_listener);
                                        }
                                        else {
                                            vip1.child("mod".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".zip")).putFile(Uri.fromFile(new File(sh.getString("archive", "")))).addOnFailureListener(_vip1_failure_listener).addOnProgressListener(_vip1_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                                                @Override
                                                public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                                                    return vip1.child("mod".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".zip")).getDownloadUrl();
                                                }}).addOnCompleteListener(_vip1_upload_success_listener);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else {
            i.setClass(getApplicationContext(), NoNetworkActivity.class);
            startActivity(i);
            overridePendingTransition(0,0);
        }
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
