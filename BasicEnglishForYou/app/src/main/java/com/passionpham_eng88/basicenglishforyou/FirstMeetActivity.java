package com.passionpham_eng88.basicenglishforyou;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;

public class FirstMeetActivity extends AppCompatActivity {

    Button btnBack;
    Button btnRunAll;
    Button btnStop;
    Thread timer;
    ListView listView;
    UserCustomListView userAdapter;
    ArrayList<User> userArray = new ArrayList<User>();
    ArrayList<Integer> audio_list;
    MediaPlayer audio_player = new MediaPlayer();

    public void MessageMap()
    {
        btnRunAll = (Button)findViewById(R.id.fmPlayAll);
        btnStop = (Button)findViewById(R.id.fmStopAll);
        btnBack = (Button)findViewById(R.id.fmBack);
        listView = (ListView)findViewById(R.id.fmListView);
    }

    public void Init()
    {
        userArray.add(new User("Hello, Hi, Good morning.\r\n(Xin chào)"));
        userArray.add(new User("My name is David.\r\n(Tôi tên là David)"));
        userArray.add(new User("Are you American?\r\n(Bạn là người mỹ phải ko)"));
        userArray.add(new User("Where are you from?\r\n(Bạn từ đâu đến)"));
        userArray.add(new User("Do you speak English?\r\n(Bạn có biết nói tiếng anh ko)"));
        userArray.add(new User("How are you?\r\n(Bạn khỏe chứ)"));
        userArray.add(new User("What is your job?\r\n(Bạn làm nghề gì)"));
        userArray.add(new User("Do you like it here\r\n(Bạn thick nơi đây chứ)"));
        userArray.add(new User("I'd like you to meet Marry\r\n(Tối muốn bạn gặp gỡ Marry)"));
        userArray.add(new User("Nice to meet you?\r\n(Rất vui được gặp bạn)"));
        userArray.add(new User("Please sit down? \r\n(Mời ngồi)"));
        userArray.add(new User("Sorry, I did not hear clearly \r\n(Xin lỗi, tôi không nghe rõ)"));
        userArray.add(new User("Please, Speak more slowly \r\n(Vui lòng nói chậm hơn)"));
        userArray.add(new User("What does this mean?\r\n(Điều đó nghĩa là gì)"));

        audio_list = new ArrayList<Integer>();
        audio_list.add(R.raw.fm1);
        audio_list.add(R.raw.fm2);
        audio_list.add(R.raw.fm3);
        audio_list.add(R.raw.fm4);
        audio_list.add(R.raw.fm5);
        audio_list.add(R.raw.fm6);
        audio_list.add(R.raw.fm7);
        audio_list.add(R.raw.fm8);
        audio_list.add(R.raw.fm9);
        audio_list.add(R.raw.fm10);
        audio_list.add(R.raw.fm11);
        audio_list.add(R.raw.fm12);
        audio_list.add(R.raw.fm13);
        audio_list.add(R.raw.fm14);
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
        setContentView(R.layout.activity_first_meet);
        getSupportActionBar().setTitle("Lần đầu gặp gỡ (First Meet)");
        MessageMap();
        Init();
        userAdapter = new UserCustomListView(FirstMeetActivity.this, R.layout.row, userArray);
        listView = (ListView) findViewById(R.id.fmListView);
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
                Intent menuScreen = new Intent(FirstMeetActivity.this, MenuActivity.class);
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
