package com.mtg.mods.wotb;

import android.app.Activity;
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
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.DialogFragment;
import android.provider.DocumentsContract.Document;

import androidx.appcompat.app.AppCompatActivity;
import androidx.documentfile.provider.DocumentFile;
import android.provider.DocumentsContract;
import android.database.*;
import java.util.zip.*;
import java.io.*;

public class SAF_PermissionActivity extends AppCompatActivity {

    private  Uri muri;
    private  DocumentFile mfile;
    private  DocumentFile mfile1;
    private  Uri uri2;
    private  static final int NEW_FOLDER_REQUEST_CODE = 43;

    private LinearLayout linear2;
    private SharedPreferences sp;
    private Button button1;
    private TextView text2;

    private Intent i = new Intent();

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_saf_permission);
        sp = getSharedPreferences("URI", Activity.MODE_PRIVATE);
        text2 = (TextView) findViewById(R.id.text2);
        linear2 = (LinearLayout) findViewById(R.id.linear2);
        button1 = (Button) findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                _button();
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                muri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AMTG%20MODS/document/primary%3AMTG%20MODS%2Ftemp");
                try{
                    DocumentsContract.deleteDocument(getApplicationContext().getContentResolver(), muri);

                } catch (FileNotFoundException e) {

                }
            }
        });
        FileUtil.makeDir(FileUtil.getExternalStorageDir());
        FileUtil.deleteFile(FileUtil.getExternalStorageDir().concat("/MTG MODS"));
        FileUtil.writeFile(FileUtil.getExternalStorageDir().concat("/MTG MODS/.app/temp"), ".");
        FileUtil.deleteFile(FileUtil.getExternalStorageDir().concat("/MTG MODS/.app/temp"));

        muri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fnet.wargaming.wot.blitz%2Ffiles%2Fpacks/document/primary%3AAndroid%2Fdata%2Fnet.wargaming.wot.blitz%2Ffiles%2Fpacks");
        mfile = DocumentFile.fromTreeUri(this, muri);
        if (mfile.canRead() && mfile.canWrite()) {
            text2.setText("Выдано разрешений: 3/4");
            muri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AMTG%20MODS/document/primary%3AMTG%20MODS");
            mfile = DocumentFile.fromTreeUri(this, muri);
            if (mfile.canRead() && mfile.canWrite()) {
                i.setClass(getApplicationContext(), LogoActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        }

    }

    private void _button() {
        try {
            muri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fnet.wargaming.wot.blitz%2Ffiles%2Fpacks/document/primary%3AAndroid%2Fdata%2Fnet.wargaming.wot.blitz%2Ffiles%2Fpacks");
            mfile = DocumentFile.fromTreeUri(this, muri);

            if (!(mfile.canRead() && mfile.canWrite())) {
                muri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fnet.wargaming.wot.blitz%2Ffiles%2Fpacks/document/primary%3AAndroid%2Fdata%2Fnet.wargaming.wot.blitz%2Ffiles%2Fpacks");
                _askPermission(button1);
            }
            else {
                muri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AMTG%20MODS/document/primary%3AMTG%20MODS");
                mfile = DocumentFile.fromTreeUri(this, muri);

                if (!(mfile.canRead() && mfile.canWrite())) {
                    muri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AMTG%20MODS/document/primary%3AMTG%20MODS");
                    _askPermission(button1);
                }
            }
        } catch(Exception e) {

        }
    }

    @Override
    protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
        super.onActivityResult(_requestCode, _resultCode, _data);

        if (_resultCode == Activity.RESULT_OK) {
            if (_data != null) {
                muri = _data.getData();
                if (Uri.decode(muri.toString()).endsWith(":")) {
                    SketchwareUtil.showMessage(getApplicationContext(), "Необходимо выбрать именно эту папку!");
                    _askPermission(button1);
                } else {
                    getContentResolver().takePersistableUriPermission(muri, Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                    try {
                        muri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AAndroid%2Fdata%2Fnet.wargaming.wot.blitz%2Ffiles%2Fpacks/document/primary%3AAndroid%2Fdata%2Fnet.wargaming.wot.blitz%2Ffiles%2Fpacks");
                        mfile = DocumentFile.fromTreeUri(this, muri);

                        if (!(mfile.canRead() && mfile.canWrite())) {

                        }
                        else {
                            text2.setText("Выдано разрешений: 3/4");
                            sp.edit().putString("FOLDER_1", muri.toString()).commit();
                            muri = Uri.parse("content://com.android.externalstorage.documents/tree/primary%3AMTG%20MODS/document/primary%3AMTG%20MODS");
                            mfile = DocumentFile.fromTreeUri(this, muri);

                            if (!(mfile.canRead() && mfile.canWrite())) {

                            }
                            else {
                                sp.edit().putString("FOLDER_2", muri.toString()).commit();
                                i.setClass(getApplicationContext(), LogoActivity.class);
                                startActivity(i);
                                overridePendingTransition(0, 0);
                            }
                        }
                    } catch(Exception e) {

                    }
                }

            } else {

            }
        } else {
            SketchwareUtil.showMessage(getApplicationContext(), "Вы не предоставили разрешение!");
            _askPermission(button1);
            finishAffinity();
        }
    }

    public void _askPermission (final View _view) {
        i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        i.setAction(Intent.ACTION_OPEN_DOCUMENT_TREE);
        i.putExtra(DocumentsContract.EXTRA_INITIAL_URI, muri);
        startActivityForResult(i, NEW_FOLDER_REQUEST_CODE);
    }



}
