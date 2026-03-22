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
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.Intent;
import android.net.Uri;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.content.ClipData;
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
import java.io.File;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;

public class SixSenseEditActivity extends AppCompatActivity {

    public final int REQ_CD_FP_IMAGE = 101;
    public final int REQ_CD_FP_ARCHIVE = 102;
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
    private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();

    private String position = "";
    private HashMap<String, Object> mapVar = new HashMap<>();
    private String date = "";
    private String new_image_url = "";
    private String name_new_image = "";
    private String name_arhive_new = "";

    private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
    private ArrayList<String> file = new ArrayList<>();

    private LinearLayout linear2;
    private ScrollView vscroll1;
    private LinearLayout linear1;
    private ScrollView vscroll2;
    private LinearLayout linear3;
    private TextView textview1;
    private EditText edittext1;
    private TextView textview2;
    private EditText edittext2;
    private TextView textview4;
    private EditText edittext4;
    private Button button3;
    private Button button4;
    private Button button5;
    private LinearLayout linear4;
    private Button button1;
    private Button button2;

    private SharedPreferences edit_mod;
    private Intent i = new Intent();
    private Calendar cl = Calendar.getInstance();
    private Intent fp_image = new Intent(Intent.ACTION_GET_CONTENT);
    private StorageReference fbs = _firebase_storage.getReference("Mods/SixSense");
    private OnCompleteListener<Uri> _fbs_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _fbs_download_success_listener;
    private OnSuccessListener _fbs_delete_success_listener;
    private OnProgressListener _fbs_upload_progress_listener;
    private OnProgressListener _fbs_download_progress_listener;
    private OnFailureListener _fbs_failure_listener;
    private DatabaseReference fdb = _firebase.getReference("Mods/SixSense");
    private ChildEventListener _fdb_child_listener;
    private StorageReference fbs2 = _firebase_storage.getReference("Mods/SixSense");
    private OnCompleteListener<Uri> _fbs2_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _fbs2_download_success_listener;
    private OnSuccessListener _fbs2_delete_success_listener;
    private OnProgressListener _fbs2_upload_progress_listener;
    private OnProgressListener _fbs2_download_progress_listener;
    private OnFailureListener _fbs2_failure_listener;
    private Intent fp_archive = new Intent(Intent.ACTION_GET_CONTENT);

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_six_sense_edit);
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
        linear2 = (LinearLayout) findViewById(R.id.linear2);
        vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        vscroll2 = (ScrollView) findViewById(R.id.vscroll2);
        linear3 = (LinearLayout) findViewById(R.id.linear3);
        textview1 = (TextView) findViewById(R.id.textview1);
        edittext1 = (EditText) findViewById(R.id.edittext1);
        textview2 = (TextView) findViewById(R.id.textview2);
        edittext2 = (EditText) findViewById(R.id.edittext2);
        textview4 = (TextView) findViewById(R.id.textview4);
        edittext4 = (EditText) findViewById(R.id.edittext4);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        linear4 = (LinearLayout) findViewById(R.id.linear4);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        edit_mod = getSharedPreferences("edit_mod", Activity.MODE_PRIVATE);
        fp_image.setType("image/*");
        fp_image.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        fp_archive.setType("*/*");
        fp_archive.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                startActivityForResult(fp_image, REQ_CD_FP_IMAGE);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                startActivityForResult(fp_archive, REQ_CD_FP_ARCHIVE);
            }
        });

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                fdb.child(position).removeValue();
                Toast.makeText(SixSenseEditActivity.this, "Мод удалён!", Toast.LENGTH_SHORT).show();
                _firebase_storage.getReferenceFromUrl(edit_mod.getString("image", "")).delete().addOnSuccessListener(_fbs_delete_success_listener).addOnFailureListener(_fbs_failure_listener);
                _firebase_storage.getReferenceFromUrl(edit_mod.getString("url", "")).delete().addOnSuccessListener(_fbs2_delete_success_listener).addOnFailureListener(_fbs2_failure_listener);
                i.setClass(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                cl = Calendar.getInstance();
                mapVar = new HashMap<>();
                mapVar.put("mod", edittext1.getText().toString());
                mapVar.put("author", edittext2.getText().toString());
                mapVar.put("version", edittext4.getText().toString());
                mapVar.put("date", new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime()));
                fdb.child(position).updateChildren(mapVar);
                mapVar.clear();
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                i.setClass(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });

        _fbs_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                button3.setText(String.valueOf((long)(_progressValue)).concat("%"));
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
                mapVar = new HashMap<>();
                mapVar.put("image", _downloadUrl);
                mapVar.put("date", new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime()));
                fdb.child(position).updateChildren(mapVar);
                _firebase_storage.getReferenceFromUrl(edit_mod.getString("image", "")).delete().addOnSuccessListener(_fbs_delete_success_listener).addOnFailureListener(_fbs_failure_listener);
                Toast.makeText(SixSenseEditActivity.this, "Скриншот заменён!", Toast.LENGTH_SHORT).show();
                mapVar.clear();
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

        _fdb_child_listener = new ChildEventListener() {
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
        fdb.addChildEventListener(_fdb_child_listener);

        _fbs2_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                button4.setText(String.valueOf((long)(_progressValue)).concat("%"));
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
                mapVar = new HashMap<>();
                mapVar.put("url", _downloadUrl);
                mapVar.put("date", new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime()));
                fdb.child(position).updateChildren(mapVar);
                _firebase_storage.getReferenceFromUrl(edit_mod.getString("url", "")).delete().addOnSuccessListener(_fbs2_delete_success_listener).addOnFailureListener(_fbs2_failure_listener);
                Toast.makeText(SixSenseEditActivity.this, "Архив заменён!", Toast.LENGTH_SHORT).show();
                mapVar.clear();
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
    }

    private void initializeLogic() {
        position = edit_mod.getString("position", "");
        date = edit_mod.getString("date", "");
        edittext1.setText(edit_mod.getString("mod", ""));
        edittext2.setText(edit_mod.getString("author", ""));
        edittext4.setText(edit_mod.getString("version", ""));
    }

    @Override
    protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
        super.onActivityResult(_requestCode, _resultCode, _data);
        switch (_requestCode) {
            case REQ_CD_FP_IMAGE:
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
                    cl = Calendar.getInstance();
                    name_new_image = "image".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".png");
                    fbs.child(name_new_image).putFile(Uri.fromFile(new File(_filePath.get((int)(0))))).addOnFailureListener(_fbs_failure_listener).addOnProgressListener(_fbs_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                            return fbs.child(name_new_image).getDownloadUrl();
                        }}).addOnCompleteListener(_fbs_upload_success_listener);
                }
                else {

                }
                break;

            case REQ_CD_FP_ARCHIVE:
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
                    cl = Calendar.getInstance();
                    fbs2.child("mod".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".zip")).putFile(Uri.fromFile(new File(_filePath.get((int)(0))))).addOnFailureListener(_fbs2_failure_listener).addOnProgressListener(_fbs2_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                            return fbs2.child("mod".concat(new SimpleDateFormat("yyyy.MM.dd HH:mm").format(cl.getTime())).concat(".zip")).getDownloadUrl();
                        }}).addOnCompleteListener(_fbs2_upload_success_listener);
                }
                else {

                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        linear4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
    }

}
