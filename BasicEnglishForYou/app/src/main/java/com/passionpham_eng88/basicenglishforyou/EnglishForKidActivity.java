package com.passionpham_eng88.basicenglishforyou;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class EnglishForKidActivity extends AppCompatActivity {

    Button btnBack;
    Button btnGuide;
    ListView ltView;
    UserCustomListView userAdapter;
    ArrayList<User> userArray = new ArrayList<User>();
    public void Init()
    {
        getSupportActionBar().setTitle("Tiếng Anh Cho Bé");
        userArray.add(new User("1.Going To The Zoo"));
        userArray.add(new User("2.Christmas Time"));
        userArray.add(new User("3.She goes to the Nurse"));
        userArray.add(new User("4.Waiting"));
        userArray.add(new User("5.Chores"));
        userArray.add(new User("6.The First Song"));
        userArray.add(new User("7.Visiting the Doctor"));
        userArray.add(new User("8.Different Foods"));
        userArray.add(new User("9.A Lot of Ants"));
        userArray.add(new User("10.Petting Zoo"));
    }

    public void MessageMap()
    {
        btnBack = (Button)findViewById(R.id.kidBack);
        btnGuide = (Button)findViewById(R.id.kidGuide);
        ltView = (ListView)findViewById(R.id.kidListView);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_english_for_kid);

        MessageMap();
        Init();
        userAdapter = new UserCustomListView(EnglishForKidActivity.this, R.layout.row, userArray);
        ltView = (ListView) findViewById(R.id.kidListView);
        ltView.setItemsCanFocus(false);
        ltView.setAdapter(userAdapter);
        ltView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {

                Intent runSingleAudio = new Intent(EnglishForKidActivity.this, RunSingleAudioActivity.class);
                runSingleAudio.putExtra("Type", position);
                startActivity(runSingleAudio);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userArray.size() > 0) {
                    userArray.clear();
                }
                finishAffinity();
                Intent menuScreen = new Intent(EnglishForKidActivity.this, MenuActivity.class);
                startActivity(menuScreen);
            }
        });

        btnGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Intent wordGroupScreen = new Intent(getApplicationContext(), AboutActivity.class);
                wordGroupScreen.putExtra("Type", 1);
                startActivity(wordGroupScreen);
            }
        });
    }
}
