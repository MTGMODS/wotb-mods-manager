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
import android.content.Intent;
import android.net.Uri;
import android.app.Activity;
import android.content.SharedPreferences;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ChildEventListener;
import com.bumptech.glide.Glide;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;

public class SkinsViewActivity extends AppCompatActivity {

    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

    private ArrayList<HashMap<String, Object>> map = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();

    private LinearLayout linear2;
    private ListView listview1;

    private Intent i = new Intent();
    private SharedPreferences edit_mod;
    private DatabaseReference fdb = _firebase.getReference("Mods/Skins");
    private ChildEventListener _fdb_child_listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sight_view);
        com.google.firebase.FirebaseApp.initializeApp(this);

        linear2 = (LinearLayout) findViewById(R.id.linear2);
        listview1 = (ListView) findViewById(R.id.listview1);
        edit_mod = getSharedPreferences("edit_mod", Activity.MODE_PRIVATE);

        _fdb_child_listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);
                fdb.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot _dataSnapshot) {
                        map = new ArrayList<>();
                        try {
                            GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                            for (DataSnapshot _data : _dataSnapshot.getChildren()) {
                                HashMap<String, Object> _map = _data.getValue(_ind);
                                map.add(_map);
                            }
                        }
                        catch (Exception _e) {
                            _e.printStackTrace();
                        }
                        list.add(_childKey);
                        listview1.setAdapter(new Listview1Adapter(map));
                        ((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(DatabaseError _databaseError) {
                    }
                });
            }

            @Override
            public void onChildChanged(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);
                fdb.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot _dataSnapshot) {
                        map = new ArrayList<>();
                        try {
                            GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                            for (DataSnapshot _data : _dataSnapshot.getChildren()) {
                                HashMap<String, Object> _map = _data.getValue(_ind);
                                map.add(_map);
                            }
                        }
                        catch (Exception _e) {
                            _e.printStackTrace();
                        }
                        listview1.setAdapter(new Listview1Adapter(map));
                        ((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(DatabaseError _databaseError) {
                    }
                });
            }

            @Override
            public void onChildMoved(DataSnapshot _param1, String _param2) {

            }

            @Override
            public void onChildRemoved(DataSnapshot _param1) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);
                fdb.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot _dataSnapshot) {
                        map = new ArrayList<>();
                        try {
                            GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {};
                            for (DataSnapshot _data : _dataSnapshot.getChildren()) {
                                HashMap<String, Object> _map = _data.getValue(_ind);
                                map.add(_map);
                            }
                        }
                        catch (Exception _e) {
                            _e.printStackTrace();
                        }
                        list.add(_childKey);
                        listview1.setAdapter(new Listview1Adapter(map));
                        ((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
                    }
                    @Override
                    public void onCancelled(DatabaseError _databaseError) {
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError _param1) {
                final int _errorCode = _param1.getCode();
                final String _errorMessage = _param1.getMessage();

            }
        };
        fdb.addChildEventListener(_fdb_child_listener);


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
                _view = _inflater.inflate(R.layout.mod, null);
            }

            final LinearLayout linear1 = (LinearLayout) _view.findViewById(R.id.linear1);
            final ImageView imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
            final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
            final LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
            final Button button1 = (Button) _view.findViewById(R.id.button1);
            final TextView textview2 = (TextView) _view.findViewById(R.id.textview2);
            final TextView textview3 = (TextView) _view.findViewById(R.id.textview3);
            final TextView textview4 = (TextView) _view.findViewById(R.id.textview4);

            linear1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b, int c, int d) { this.setCornerRadius(a); this.setStroke(b, c); this.setColor(d); return this; } }.getIns((int)0, (int)5, 0xFFFFFFFF, Color.TRANSPARENT));
            Glide.with(getApplicationContext()).load(Uri.parse(_data.get((int)_position).get("image").toString())).into(imageview1);
            textview1.setText("".concat(_data.get((int)_position).get("mod").toString()));
            textview2.setText("Автор мода: ".concat(_data.get((int)_position).get("author").toString()));
            textview3.setText("Добавлено: ".concat(_data.get((int)_position).get("date").toString()));
            textview4.setText("Актуально для версии ".concat(_data.get((int)_position).get("version").toString()));
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View _view) {
                    edit_mod.edit().putString("mod", _data.get((int)_position).get("mod").toString()).commit();
                    edit_mod.edit().putString("author", _data.get((int)_position).get("author").toString()).commit();
                    edit_mod.edit().putString("image", _data.get((int)_position).get("image").toString()).commit();
                    edit_mod.edit().putString("url", _data.get((int)_position).get("url").toString()).commit();
                    edit_mod.edit().putString("version", _data.get((int)_position).get("version").toString()).commit();
                    edit_mod.edit().putString("date", _data.get((int)_position).get("date").toString()).commit();
                    edit_mod.edit().putString("position", list.get((int)(_position))).commit();
                    i.setClass(getApplicationContext(), SkinsEditActivity.class);
                    startActivity(i);
                }
            });

            return _view;
        }
    }

}