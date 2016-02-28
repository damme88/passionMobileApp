package com.passionpham_eng88.basicenglishforyou;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class HelpActivity extends AppCompatActivity {

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
    public void Init() {
        userArray.add(new User("Can you help me?\r\n(Giúp tôi)"));
        userArray.add(new User("I need a doctor?\r\n(Tôi cần bác sĩ)"));
        userArray.add(new User("Can you do me a favor? \r\n(Bạn có thể giúp tôi một việc ko)"));
        userArray.add(new User("Can you please say that again\r\n(Bạn có thể nói lại điều đó ko)"));
        userArray.add(new User("Does anyone here speak English ?\r\n(Ở đây có ai nói tiếng anh ko)"));
        userArray.add(new User("Please speak more slowly\r\n(Hãy nói chậm hơn)"));
        userArray.add(new User("please call the police\r\n(Hãy gọi công an)"));

        audio_list = new ArrayList<Integer>();
        audio_list.add(R.raw.h1);
        audio_list.add(R.raw.h2);
        audio_list.add(R.raw.h3);
        audio_list.add(R.raw.h4);
        audio_list.add(R.raw.h5);
        audio_list.add(R.raw.h6);
    }

    public void OpenURL(String str)
    {
        Uri uriUrl = Uri.parse(str);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
            getSupportActionBar().setTitle("Cần sự giúp đỡ (Help me)");
            MessageMap();
            Init();
            userAdapter = new UserCustomListView(HelpActivity.this, R.layout.row,
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
                    Intent menuScreen = new Intent(HelpActivity.this, MenuActivity.class);
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
