package com.passionpham_eng88.basicenglishforyou;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class TourismActitvity extends AppCompatActivity {

    Button btnBack;
    ListView listView;
    UserCustomListView userAdapter;
    ArrayList<User> userArray = new ArrayList<User>();
    ArrayList<Integer> audio_list;
    MediaPlayer audio_player = new MediaPlayer();
    Button btnRunAll;
    Button btnStop;
    Thread timer;
    public void MessageMap()
    {
        btnBack = (Button)findViewById(R.id.btnBack);
        listView = (ListView)findViewById(R.id.listview);
        btnRunAll = (Button)findViewById(R.id.btnRunAll);
        btnStop = (Button)findViewById(R.id.btnStopRunAll);
    }

    public void Init()
    {
        userArray.add(new User("Do you speak English?"));
        userArray.add(new User("Where can I find a taxi/train?"));
        userArray.add(new User("Can you take me to the airport please?"));
        userArray.add(new User("Can you take me a photograph?"));
        userArray.add(new User("Where is the bank/restaurant?"));
        userArray.add(new User("Where can I get something to eat?"));
        userArray.add(new User("Where are you from"));

        audio_list = new ArrayList<Integer>();
        audio_list.add(R.raw.t1);
        audio_list.add(R.raw.t2);
        audio_list.add(R.raw.t3);
        audio_list.add(R.raw.t4);
        audio_list.add(R.raw.t5);
        audio_list.add(R.raw.t6);
        audio_list.add(R.raw.t7);
    }
    public void OpenURL(String str)
    {
        Uri uriUrl = Uri.parse(str);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }

    public  void PlayAudio(int position)
    {
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourism_actitvity);
        getSupportActionBar().setTitle("Đi du lich (Tourism)");

        MessageMap();
        Init();

        userAdapter = new UserCustomListView(TourismActitvity.this, R.layout.row,
                userArray);
        listView = (ListView) findViewById(R.id.listview);
        listView.setItemsCanFocus(false);
        listView.setAdapter(userAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {

                String content = userArray.get(position).GetStence();
                String country_code = getResources().getConfiguration().locale.getCountry();
                // Struct google translate
                // https://translate.google.com/#en/vi/love
                String trans = "https://translate.google.com/#en/" + country_code + "/" + content;
                //OpenURL(trans);
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
                Intent menuScreen = new Intent(TourismActitvity.this, MenuActivity.class);
                startActivity(menuScreen);
            }
        });

        btnRunAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timer = new Thread() {
                    public void run() {
                        try {
                            for (int i = 0; i < audio_list.size(); i++) {
                                PlayAudio(i);
                                sleep(3000);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                timer.start();
                timer.isInterrupted();
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timer != null)
                {
                    timer.interrupt();
                    audio_player.stop();
                }
            }
        });
    }
}
