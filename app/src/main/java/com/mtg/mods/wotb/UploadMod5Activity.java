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
import android.app.Activity;
import android.content.SharedPreferences;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class UploadMod5Activity extends AppCompatActivity {

    private LinearLayout linear1;

    private SharedPreferences sh;
    private AlertDialog.Builder d;
    private Intent i = new Intent();

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_upload_mod5);
        initialize(_savedInstanceState);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        sh = getSharedPreferences("mod", Activity.MODE_PRIVATE);
        d = new AlertDialog.Builder(this);
    }

    private void initializeLogic() {
        d.setCancelable(false);
        d.setTitle("Проверка данных");
        if (sh.getString("category", "").equals("1")) {
            d.setMessage("Название мода: ".concat(sh.getString("mod", "").concat("\nАвтор мода: ".concat(sh.getString("author", "").concat("\nКатегория мода: ".concat("Ангары".concat("\nАрхив: ".concat(sh.getString("archive", "").replace("/storage/emulated/0/", "/").concat("\n\nВсё верно?")))))))));
        }
        else {
            if (sh.getString("category", "").equals("2")) {
                d.setMessage("Название мода: ".concat(sh.getString("mod", "").concat("\nАвтор мода: ".concat(sh.getString("author", "").concat("\nКатегория мода: ".concat("Прицелы".concat("\nАрхив: ".concat(sh.getString("archive", "").replace("/storage/emulated/0/", "/").concat("\n\nВсё верно?")))))))));
            }
            else {
                if (sh.getString("category", "").equals("3")) {
                    d.setMessage("Название мода: ".concat(sh.getString("mod", "").concat("\nАвтор мода: ".concat(sh.getString("author", "").concat("\nКатегория мода: ".concat("Шкурки".concat("\nАрхив: ".concat(sh.getString("archive", "").replace("/storage/emulated/0/", "/").concat("\n\nВсё верно?")))))))));
                }
                else {
                    if (sh.getString("category", "").equals("4")) {
                        d.setMessage("Название мода: ".concat(sh.getString("mod", "").concat("\nАвтор мода: ".concat(sh.getString("author", "").concat("\nКатегория мода: ".concat("Ремоделинг".concat("\nАрхив: ".concat(sh.getString("archive", "").replace("/storage/emulated/0/", "/").concat("\n\nВсё верно?")))))))));
                    }
                    else {
                        if (sh.getString("category", "").equals("5")) {
                            d.setMessage("Название мода: ".concat(sh.getString("mod", "").concat("\nАвтор мода: ".concat(sh.getString("author", "").concat("\nКатегория мода: ".concat("Лампы засвета".concat("\nАрхив: ".concat(sh.getString("archive", "").replace("/storage/emulated/0/", "/").concat("\n\nВсё верно?")))))))));
                        }
                        else {
                            if (sh.getString("category", "").equals("6")) {
                                d.setMessage("Название мода: ".concat(sh.getString("mod", "").concat("\nАвтор мода: ".concat(sh.getString("author", "").concat("\nКатегория мода: ".concat("Интерфейс".concat("\nАрхив: ".concat(sh.getString("archive", "").replace("/storage/emulated/0/", "/").concat("\n\nВсё верно?")))))))));
                            }
                            else {
                                if (sh.getString("category", "").equals("7")) {
                                    d.setMessage("Название мода: ".concat(sh.getString("mod", "").concat("\nАвтор мода: ".concat(sh.getString("author", "").concat("\nКатегория мода: ".concat("Звуковые моды".concat("\nВидеообзор мода: ".concat(sh.getString("video", "").concat("\nАрхив: ".concat(sh.getString("archive", "").replace("/storage/emulated/0/", "/").concat("\n\nВсё верно?")))))))))));
                                }
                                else {
                                    if (sh.getString("category", "").equals("8")) {
                                        d.setMessage("Название мода: ".concat(sh.getString("mod", "").concat("\nАвтор мода: ".concat(sh.getString("author", "").concat("\nКатегория мода: ".concat("Прочие моды".concat("\nАрхив: ".concat(sh.getString("archive", "").replace("/storage/emulated/0/", "/").concat("\n\nВсё верно?")))))))));
                                    }
                                    else {
                                        if (sh.getString("category", "").equals("9")) {
                                            d.setMessage("Название мода: ".concat(sh.getString("mod", "").concat("\nАвтор мода: ".concat(sh.getString("author", "").concat("\nКатегория мода: ".concat("VIP".concat("\nАрхив: ".concat(sh.getString("archive", "").replace("/storage/emulated/0/", "/").concat("\n\nВсё верно?")))))))));
                                        }
                                        else {
                                            Toast.makeText(this, "Ошибка №1", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        d.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface _dialog, int _which) {
                i.setClass(getApplicationContext(), UploadMod6Activity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });
        d.setNegativeButton("Нет, изменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface _dialog, int _which) {
                i.setClass(getApplicationContext(), UploadMod1Activity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
            }
        });
        d.create().show();
    }


    @Override
    public void onBackPressed() {

    }

}
