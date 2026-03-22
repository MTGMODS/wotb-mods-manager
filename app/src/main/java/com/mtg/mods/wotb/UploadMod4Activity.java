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
import android.content.Intent;
import android.content.ClipData;
import android.app.Activity;
import android.content.SharedPreferences;
import java.util.Timer;
import java.util.TimerTask;
import android.net.Uri;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.content.pm.PackageManager;


public class UploadMod4Activity extends AppCompatActivity {
    public final int REQ_CD_FP = 101;
    private Timer _timer = new Timer();

    private String archive = "";

    private LinearLayout linear1;
    private TextView textview1;
    private LinearLayout linear2;
    private TextView textview2;

    private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
    private SharedPreferences sh;
    private TimerTask t;
    private Intent i = new Intent();
    private AlertDialog.Builder d;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_upload_mod4);
        initialize(_savedInstanceState);
        com.google.firebase.FirebaseApp.initializeApp(this);

    }

    private void initialize(Bundle _savedInstanceState) {
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        textview1 = (TextView) findViewById(R.id.textview1);
        linear2 = (LinearLayout) findViewById(R.id.linear2);
        textview2 = (TextView) findViewById(R.id.textview2);
        fp.setType("*/*");
        fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        sh = getSharedPreferences("mod", Activity.MODE_PRIVATE);
        d = new AlertDialog.Builder(this);

        textview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                startActivityForResult(fp, REQ_CD_FP);
            }
        });
    }


    @Override
    protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
        super.onActivityResult(_requestCode, _resultCode, _data);
        switch (_requestCode) {
            case REQ_CD_FP:
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
                    archive = _filePath.get((int)(0));
                    Toast.makeText(this, "Проверка архива...", Toast.LENGTH_SHORT).show();
                    t = new TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    _UnZip(archive, FileUtil.getExternalStorageDir().concat("/MTG MODS/test/"));
                                    t = new TimerTask() {
                                        @Override
                                        public void run() {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    if (FileUtil.isExistFile(FileUtil.getExternalStorageDir().concat("/MTG MODS/test/net.wargaming.wot.blitz"))) {
                                                        FileUtil.deleteFile(FileUtil.getExternalStorageDir().concat("/MTG MODS/test/net.wargaming.wot.blitz"));
                                                        sh.edit().putString("archive", archive).commit();
                                                        i.setClass(getApplicationContext(), UploadMod5Activity.class);
                                                        startActivity(i);
                                                        overridePendingTransition(0, 0);
                                                    }
                                                    else {
                                                        FileUtil.deleteFile(FileUtil.getExternalStorageDir().concat("/MTG MODS/test"));
                                                        d.setTitle("Неправильный архив");
                                                        d.setMessage("Архив должен распаковываться в \n/Android/data/ , поэтому внутри архива должен быть полный путь к моду\n\nПример: \n.zip/net.wargaming.wot.blitz/files/packs/3d/Maps/hangar_prem_2018/");
                                                        d.setPositiveButton("Хорошо", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface _dialog, int _which) {

                                                            }
                                                        });
                                                        d.create().show();
                                                    }
                                                }
                                            });
                                        }
                                    };
                                    _timer.schedule(t, (int)(2100));
                                }
                            });
                        }
                    };
                    _timer.schedule(t, (int)(1000));
                }
                else {
                    Toast.makeText(this, "Вы не выбрали архив", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
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


}
