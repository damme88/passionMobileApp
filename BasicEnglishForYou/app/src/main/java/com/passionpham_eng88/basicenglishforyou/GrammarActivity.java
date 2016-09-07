package com.passionpham_eng88.basicenglishforyou;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class GrammarActivity extends AppCompatActivity {

    Button btnBack;
    ListView listView;
    UserCustomListView userAdapter;
    ArrayList<User> userArray = new ArrayList<User>();
    public void MessageMap()
    {
        btnBack = (Button)findViewById(R.id.grammarBack);
        listView = (ListView)findViewById(R.id.grammarListView);
    }
    public void Init()
    {
        getSupportActionBar().setTitle("NGỮ PHÁP CƠ BẢN");
        userArray.add(new User("HIỆN TẠI ĐƠN"));
        userArray.add(new User("HIÊN TẠI TIẾP DIỄN"));
        userArray.add(new User("HIỆN TẠI HOÀN THÀNH"));
        userArray.add(new User("HIỆN TẠI HOÀN THÀNH TIẾP DIỄN"));
        userArray.add(new User("QUÁ KHỨ ĐƠN"));
        userArray.add(new User("QUÁ KHỨ TIẾP DIỄN"));
        userArray.add(new User("QUÁ KHỨ HOÀN THÀNH"));
        userArray.add(new User("TƯƠNG LAI ĐƠN"));
        userArray.add(new User("TƯƠNG LAI HOÀN THÀNH"));
        userArray.add(new User("TƯƠNG LAI TIẾP DIỄN"));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grammar);
        MessageMap();
        Init();
        userAdapter = new UserCustomListView(GrammarActivity.this, R.layout.row, userArray);
        listView = (ListView) findViewById(R.id.grammarListView);
        listView.setItemsCanFocus(false);
        listView.setAdapter(userAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {

                Intent grammarGroup = new Intent(GrammarActivity.this, GramGroupActivity.class);
                grammarGroup.putExtra("Type", position);
                startActivity(grammarGroup);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userArray.size() > 0) {
                    userArray.clear();
                }
                finishAffinity();
                Intent menuScreen = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(menuScreen);
            }
        });
    }
}
