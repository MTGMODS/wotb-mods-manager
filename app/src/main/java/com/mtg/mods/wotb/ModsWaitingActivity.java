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
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import android.app.Activity;
import android.content.SharedPreferences;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class ModsWaitingActivity extends AppCompatActivity {
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

    private ArrayList<HashMap<String, Object>> list_map = new ArrayList<>();

    private LinearLayout linear1;
    private ListView listview1;

    private DatabaseReference fdb = _firebase.getReference("Mods/Waiting");
    private ChildEventListener _fdb_child_listener;
    private SharedPreferences new_sh;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_mods_waiting);
        initialize(_savedInstanceState);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        listview1 = (ListView) findViewById(R.id.listview1);
        new_sh = getSharedPreferences("new_mod", Activity.MODE_PRIVATE);

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
    }

    private void initializeLogic() {
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        listview1.setHorizontalScrollBarEnabled(false);
        listview1.setVerticalScrollBarEnabled(false);
        listview1.setOverScrollMode(ScrollView.OVER_SCROLL_NEVER);
        listview1.setDivider(null);
        listview1.setDividerHeight(0);
        fdb.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot _dataSnapshot) {
                list_map = new ArrayList<>();
                try {
                    GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                    for (DataSnapshot _data : _dataSnapshot.getChildren()) {
                        HashMap<String, Object> _map = _data.getValue(_ind);
                        list_map.add(_map);
                    }
                }
                catch (Exception _e) {
                    _e.printStackTrace();
                }
                listview1.setAdapter(new Listview1Adapter(list_map));
                ((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError _databaseError) {
            }
        });
    }

    public class Listview1Adapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;
        public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
            _data = _arr;
        }

        @Override
        public int getCount() {
            return _data.size();
        }

        @Override
        public HashMap<String, Object> getItem(int _index) {
            return _data.get(_index);
        }

        @Override
        public long getItemId(int _index) {
            return _index;
        }


        @Override
        public View getView(final int _position, View _v, ViewGroup _container) {
            LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View _view = _v;
            if (_view == null) {
                _view = _inflater.inflate(R.layout.mod_waiting, null);
            }

            final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
            final LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
            final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
            final TextView textview5 = (TextView) _view.findViewById(R.id.textview5);
            final TextView textview2 = (TextView) _view.findViewById(R.id.textview2);
            final TextView textview3 = (TextView) _view.findViewById(R.id.textview3);
            final TextView textview4 = (TextView) _view.findViewById(R.id.textview4);

            textview4.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)80, (int)5, 0xFFFFFFFF, 0xFF36393E));
            linear1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)0, (int)5, 0xFFFFFFFF, 0xFF36393E));
            textview1.setText(_data.get((int)_position).get("mod").toString());
            textview2.setText("Автор: ".concat(_data.get((int)_position).get("author").toString()));
            textview3.setText("Заявка отправлена: ".concat(_data.get((int)_position).get("date").toString()));
            textview5.setText(_data.get((int)_position).get("category").toString());
            if (textview5.getText().toString().contains("1")) {
                textview5.setText("Категория: Ангары");
            }
            else {
                if (textview5.getText().toString().contains("2")) {
                    textview5.setText("Категория: Прицелы");
                }
                else {
                    if (textview5.getText().toString().contains("3")) {
                        textview5.setText("Категория: Шкурки");
                    }
                    else {
                        if (textview5.getText().toString().contains("4")) {
                            textview5.setText("Категория: Ремоделинг");
                        }
                        else {
                            if (textview5.getText().toString().contains("5")) {
                                textview5.setText("Категория: Лампы засвета");
                            }
                            else {
                                if (textview5.getText().toString().contains("6")) {
                                    textview5.setText("Категория: Интерфейс");
                                }
                                else {
                                    if (textview5.getText().toString().contains("7")) {
                                        textview5.setText("Категория: Звуковые моды");
                                    }
                                    else {
                                        textview5.setText("Категория: Прочие моды");
                                    }
                                }
                            }
                        }
                    }
                }
            }
            textview4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View _view) {
                    new_sh.edit().putString("category", _data.get((int)_position).get("category").toString()).commit();
                    new_sh.edit().putString("mod", _data.get((int)_position).get("mod").toString()).commit();
                    new_sh.edit().putString("author", _data.get((int)_position).get("author").toString()).commit();
                    new_sh.edit().putString("date", _data.get((int)_position).get("date").toString()).commit();
                    new_sh.edit().putString("file", _data.get((int)_position).get("author").toString()).commit();
                    if (textview5.getText().toString().contains("7")) {
                        new_sh.edit().putString("video", _data.get((int)_position).get("video").toString()).commit();
                    }
                    else {
                        new_sh.edit().putString("image", _data.get((int)_position).get("image").toString()).commit();
                    }
                }
            });

            return _view;
        }
    }
}
