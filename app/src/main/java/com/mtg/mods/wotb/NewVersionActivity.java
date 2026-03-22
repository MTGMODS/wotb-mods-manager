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
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
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


public class NewVersionActivity extends AppCompatActivity {
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

    private HashMap<String, Object> map = new HashMap<>();

    private Intent i = new Intent();

    private LinearLayout linear2;
    private ScrollView vscroll1;
    private LinearLayout linear1;
    private TextView textview2;
    private EditText edittext2;
    private TextView textview1;
    private EditText edittext1;
    private TextView textview3;
    private EditText edittext3;
    private EditText edittext4;
    private Button button2;

    private DatabaseReference fbs = _firebase.getReference("/");
    private ChildEventListener _fbs_child_listener;
    private DatabaseReference fbs1 = _firebase.getReference("UpdateCheck");
    private ChildEventListener _fbs1_child_listener;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.activity_new_version);
        initialize(_savedInstanceState);
        com.google.firebase.FirebaseApp.initializeApp(this);
    }

    private void initialize(Bundle _savedInstanceState) {
        linear2 = (LinearLayout) findViewById(R.id.linear2);
        vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
        linear1 = (LinearLayout) findViewById(R.id.linear1);
        textview2 = (TextView) findViewById(R.id.textview2);
        edittext2 = (EditText) findViewById(R.id.edittext2);
        textview1 = (TextView) findViewById(R.id.textview1);
        edittext1 = (EditText) findViewById(R.id.edittext1);
        textview3 = (TextView) findViewById(R.id.textview3);
        edittext3 = (EditText) findViewById(R.id.edittext3);
        edittext4 = (EditText) findViewById(R.id.edittext4);
        button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (edittext1.getText().toString().equals("")) {
                    Toast.makeText(NewVersionActivity.this, "Не указан код версии", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (edittext2.getText().toString().equals("")) {
                        Toast.makeText(NewVersionActivity.this, "Не указана ссылка", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (edittext3.getText().toString().equals("")) {
                            Toast.makeText(NewVersionActivity.this, "Не указано что нового\"", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            if (edittext4.getText().toString().equals("")) {
                                Toast.makeText(NewVersionActivity.this, "Не указано что нового (en)", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                fbs.child("UpdateCheck").removeValue();
                                map = new HashMap<>();
                                map.put("version", edittext1.getText().toString());
                                map.put("update_url", edittext2.getText().toString());
                                map.put("update", edittext3.getText().toString());
                                map.put("update_en", edittext4.getText().toString());
                                fbs1.push().updateChildren(map);
                                Toast.makeText(NewVersionActivity.this, "Обновление добавлено!", Toast.LENGTH_SHORT).show();
                                i.setClass(getApplicationContext(), ModderPanelActivity.class);
                                startActivity(i);
                                overridePendingTransition(0,0);
                            }
                        }
                    }
                }
            }
        });

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

        _fbs1_child_listener = new ChildEventListener() {
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
        fbs1.addChildEventListener(_fbs1_child_listener);
    }

}