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

public class ListeningActivity extends AppCompatActivity {

    Button btnBack;
    Button btnStop;
    Thread timer;
    ListView ltView;
    UserCustomListView userAdapter;
    ArrayList<User> userArray = new ArrayList<User>();
    MediaPlayer audio_player = new MediaPlayer();
    ArrayList<Integer> audio_list = new ArrayList<Integer>();
    public void Init()
    {
        getSupportActionBar().setTitle("Luyện Nghe");
        userArray.add(new User("First Now Fall"));
        userArray.add(new User("My First Pet"));
        userArray.add(new User("Mark's Big Game"));
        userArray.add(new User("My Flower Garden"));
        userArray.add(new User("Easter Egg Hun"));
        userArray.add(new User("Going Camping"));
        userArray.add(new User("Jennifer the Firefighter"));
        userArray.add(new User("My House"));
        userArray.add(new User("Lesson 1"));
        userArray.add(new User("Lesson 2"));
        userArray.add(new User("Lesson 3"));
        userArray.add(new User("Lesson 4"));
        userArray.add(new User("Lesson 5"));
        userArray.add(new User("Lesson 6"));
        userArray.add(new User("Lesson 7"));
        userArray.add(new User("Lesson 8"));
        userArray.add(new User("Lesson 9"));
        userArray.add(new User("Lesson 10"));
        userArray.add(new User("Lesson 11"));
        userArray.add(new User("Lesson 12"));
        userArray.add(new User("Lesson 13"));
        userArray.add(new User("Lesson 14"));
        userArray.add(new User("Lesson 15"));
        userArray.add(new User("Lesson 16"));
        userArray.add(new User("Lesson 17"));
        userArray.add(new User("Lesson 18"));
        userArray.add(new User("Lesson 19"));
        userArray.add(new User("Lesson 20"));

        audio_list = new ArrayList<Integer>();
        audio_list.add(R.raw.lifirstsnowfall);
        audio_list.add(R.raw.limyfirstpet);
        audio_list.add(R.raw.limarksbiggame);
        audio_list.add(R.raw.limyflowergarden);
        audio_list.add(R.raw.lieasteregghun);
        audio_list.add(R.raw.ligoingcamping);
        audio_list.add(R.raw.lijenniferthefirefighter);
        audio_list.add(R.raw.limyhouse);
        audio_list.add(R.raw.listeneng1);
        audio_list.add(R.raw.listeneng2);
        audio_list.add(R.raw.listeneng3);
        audio_list.add(R.raw.listeneng4);
        audio_list.add(R.raw.listeneng5);
        audio_list.add(R.raw.listeneng6);
        audio_list.add(R.raw.listeneng7);
        audio_list.add(R.raw.listeneng8);
        audio_list.add(R.raw.listeneng9);
        audio_list.add(R.raw.listeneng10);
        audio_list.add(R.raw.listeneng11);
        audio_list.add(R.raw.listeneng12);
        audio_list.add(R.raw.listeneng13);
        audio_list.add(R.raw.listeneng14);
        audio_list.add(R.raw.listeneng15);
        audio_list.add(R.raw.listeneng16);
        audio_list.add(R.raw.listeneng17);
        audio_list.add(R.raw.listeneng18);
        audio_list.add(R.raw.listeneng19);
        audio_list.add(R.raw.listeneng20);

    }

    public void MessageMap()
    {
        btnBack = (Button)findViewById(R.id.liBack);
        btnStop = (Button)findViewById(R.id.liStopAll);
        ltView = (ListView)findViewById(R.id.liListView);
    }

    public  void PlayAudio(int position)
    {
        if (position < audio_list.size())
        {
            if (audio_player.isPlaying())
            {
                audio_player.stop();
            }
            audio_player = MediaPlayer.create(getApplicationContext(), audio_list.get(position));
            audio_player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    // TODO Auto-generated method stub
                    mp.stop();
                }
            });
            if (audio_player.isPlaying() == false)
            {
                audio_player.start();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listening);
        MessageMap();
        Init();
        userAdapter = new UserCustomListView(ListeningActivity.this, R.layout.row, userArray);
        ltView = (ListView) findViewById(R.id.liListView);
        ltView.setItemsCanFocus(false);
        ltView.setAdapter(userAdapter);
        ltView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (timer != null) {
                    if (timer.isInterrupted() == false) {
                        timer.interrupt();
                    } else {
                        audio_player.release();
                    }
                } else {
                    if (audio_player.isPlaying()) {
                        audio_player.stop();
                    }
                }
                if (audio_list.size() > 0) {
                    audio_list.clear();
                }
                if (userArray.size() > 0) {
                    userArray.clear();
                }

                if (userArray.size() > 0) {
                    userArray.clear();
                }
                finishAffinity();
                Intent menuScreen = new Intent(getApplicationContext(), MenuActivity.class);
                startActivity(menuScreen);
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer != null) {
                    timer.interrupt();
                }
                if (audio_player.isPlaying())
                {
                    audio_player.stop();
                }
            }
        });
    }
}
